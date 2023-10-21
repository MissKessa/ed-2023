package graphs;

public class Graph<T> {
	public static final int INDEX_NOT_FOUND = -1;
	public static final int EMPTY = -1;
	public static final double INFINITE = Double.POSITIVE_INFINITY; // Infinite cost (the arrival node cannot be reached
																	// from the starting node)
	int size;
	T[] nodes;
	boolean[][] edges; // adjacency matrix
	double[][] weights;
	double[][] A; // A matrix for floyd with the lowest min cost
	int[][] P; // P matrix store the pivots (the intermediate node to get throught to get the
				// min cost path)

	/**
	 * 
	 * @param maxSize is the maximum capacity of the graph (maximum number of nodes)
	 */
	public Graph(int maxSize) {
		if (maxSize <= 0)
			throw new IllegalArgumentException("The maximum size cannot be less than or equal to 0");
		size = 0;
		edges = new boolean[maxSize][maxSize];
		weights = new double[maxSize][maxSize]; // it fills it with False
		nodes = (T[]) new Object[maxSize];
		A = new double[maxSize][maxSize];
		P = new int[maxSize][maxSize];
	}

	/**
	 * @param node is the given node to check its index on the list of nodes
	 * @return the index of the given node. If the node is not in the list of nodes,
	 *         it returns INDEX_NOT_FOUND
	 * @throws NullPointerException if the node is null
	 */
	public int getNode(T node) { // O(n)
		if (node == null)
			throw new NullPointerException("The node cannot be null");

		for (int i = 0; i < size; i++) {
			if (node.equals(nodes[i]))
				return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * @return the current number of nodes
	 */
	public int getSize() {
		return size;
	}

	public boolean[][] getEdges() {
		return edges;
	}

	public double[][] getWeight() {
		return weights;
	}

	/**
	 * @param origin      is the source of the edge
	 * @param destination is the arrival of the edge
	 * @return the weight of the arc that links source to destination. If the edge
	 *         doesn't an edge between them, it returns INDEX_NOT_FOUND
	 * @throws ElementNotPresentException if source or destination are not present
	 *                                    in the graph
	 * @throws NullPointerException       if origin or destination is null
	 */
	public double getEdge(T origin, T destination) {
		if (origin == null || destination == null)
			throw new NullPointerException("The origin or the destination cannot be null");

		int positionOrigin = getNode(origin);
		int positionDestination = getNode(destination);

		if (positionOrigin == INDEX_NOT_FOUND || positionDestination == INDEX_NOT_FOUND)
			throw new ElementNotPresentException("The origin or destination is not in the graph");

		if (!existsEdge(origin, destination))
			return INDEX_NOT_FOUND;

		return weights[positionOrigin][positionDestination];

	}

	/**
	 * @param origin      is the source of the edge
	 * @param destination is the arrival of the edge
	 * @return if there exists an edge between them.
	 * @throws NullPointerException if origin or destination is null
	 */
	public boolean existsEdge(T origin, T destination) {
		if (origin == null || destination == null)
			throw new NullPointerException("The origin or the destination cannot be null");

		int positionOrigin = getNode(origin);
		int positionDestination = getNode(destination);

		if (positionOrigin == INDEX_NOT_FOUND || positionDestination == INDEX_NOT_FOUND)
			return false;

		return edges[positionOrigin][positionDestination];
	}

	/**
	 * Inserts a new node that is passed as a parameter.
	 * 
	 * @param node is the node to insert
	 * @return true if the node is inserted. Returns false if it already exists (it
	 *         doesn't insert it).
	 * @throws NullPointerException   if it receives a null node
	 * @throws FullStructuredExcepion if it doesn't fit (it is not inserted)
	 */
	public boolean addNode(T node) {
		if (node == null)
			throw new NullPointerException("The node cannot be null");

		if (size == nodes.length)
			throw new FullStructureException("The list of nodes is full"); // or IllegalStateExcepion

		if (getNode(node) != INDEX_NOT_FOUND)
			return false;

		nodes[size] = node;

		// Lazy remove
		for (int i = 0; i < size; i++) {
			edges[i][size] = false;
			edges[size][i] = false;
			weights[i][size] = 0.0;
			weights[size][i] = 0.0;
		}
		size++;
		return true;
	}

	/**
	 * Inserts an edge with the indicated weight (>0) between 2 nodes, source and
	 * destination.
	 * 
	 * @param origin      is the source of the edge
	 * @param destination is the arrival of the edge
	 * @param weight      is the value of the edge
	 * @return true if it's inserted. Returns false if the arc(edge) already exists.
	 * @throws ElementNoPresentException if the source or destination nodes doesn't
	 *                                   exist
	 * @throws IllegalArgumentException  if the weight is invalid
	 */
	public boolean addEdge(T origin, T destination, double weight) {
		if (origin == null || destination == null)
			throw new NullPointerException("The origin or the destination cannot be null");

		if (weight <= 0)
			throw new IllegalArgumentException("The weight cannot be 0 or negative");

		int positionOrigin = getNode(origin);
		int positionDestination = getNode(destination);

		if (positionOrigin == INDEX_NOT_FOUND || positionDestination == INDEX_NOT_FOUND)
			throw new ElementNotPresentException("The origin or destination is not in the graph");

		if (existsEdge(origin, destination))
			return false;

		edges[positionOrigin][positionDestination] = true;
		weights[positionOrigin][positionDestination] = weight;
		return true;
	}

	public boolean removeEdge(T origin, T destination) {
		if (origin == null || destination == null)
			throw new NullPointerException("The origin or the destination cannot be null");

		int positionOrigin = getNode(origin);
		int positionDestination = getNode(destination);

		if (positionOrigin == INDEX_NOT_FOUND || positionDestination == INDEX_NOT_FOUND)
			throw new ElementNotPresentException("The origin or destination is not in the graph");

		if (!existsEdge(origin, destination))
			return false;

		edges[positionOrigin][positionDestination] = false;
		weights[positionOrigin][positionDestination] = 0.0;
		return true;
	}

	/**
	 * If the node doesn't belong to the graph returns false decrement the actual
	 * size of a graph Swicth he deleted node with the last node stored in the array
	 * Now, the index of this switched last node is the index of the deleted node,
	 * you need to update
	 * 
	 * @param node
	 * @return
	 */
	public boolean removeNode(T node) { // O(n)
		if (node == null)
			throw new NullPointerException("The node cannot be null");

		int delIndex = getNode(node);
		if (delIndex == INDEX_NOT_FOUND)
			return false;

		int lastIndex = size - 1;
		size--;
		nodes[delIndex] = nodes[lastIndex];

		for (int i = 0; i < size; i++) { // to not move the corner
			edges[i][delIndex] = edges[i][lastIndex]; // move horizontally
			edges[delIndex][i] = edges[lastIndex][i];
			weights[i][delIndex] = weights[i][lastIndex];
			weights[delIndex][i] = weights[lastIndex][i];
		}
		edges[delIndex][delIndex] = edges[lastIndex][lastIndex];
		weights[delIndex][delIndex] = weights[lastIndex][lastIndex];

		return true;
	}

	/**
	 * 
	 * @param element is the starting node
	 * @return a String with the first depth traversal of a graph starting at the
	 *         given element. Returns null when the element is not in the graph
	 */
	public String traverseGraphDF(T element) { // better return T[]
		boolean[] visited = new boolean[getSize()];
		int indexElement = getNode(element); // Retrieve the index of starting node

		if (indexElement == INDEX_NOT_FOUND) // Return null if the element is not in the graph
			return null;

		return DFPrint(indexElement, visited);
	}

	/**
	 * 
	 * @param currentIndex is the index of the current node in the first depth
	 *                     traversal
	 * @param visited      is an array that indicates if the nodes are visited or
	 *                     not
	 * @return a String with the first depth traversal of a graph starting at the
	 *         given element
	 */
	private String DFPrint(int currentIndex, boolean[] visited) { // Depth first path
		visited[currentIndex] = true;
		String path = nodes[currentIndex] + "-";

		for (int child = 0; child < size; child++) { // First finds the child with lower index
			if (edges[currentIndex][child] && !visited[child]) {
				path += DFPrint(child, visited);
			}
		}
		return path;
	}

	public void floyd(int limit) {
//		if (limit>size)
		initsFloyd(limit);
		for (int pivot = 0; pivot < limit; pivot++) {
			for (int from = 0; from < limit; from++) {
				for (int to = 0; to < limit; to++) {
					double newCost = A[from][pivot] + A[pivot][to];

					if (newCost < A[from][to]) {
						A[from][to] = newCost;
						P[from][to] = pivot;
					}
				}
			}
		}
	}

	private void initsFloyd(int limit) {
		for (int from = 0; from < limit; from++) {
			for (int to = 0; to < limit; to++) {
				if (!edges[from][to])
					A[from][to] = INFINITE;
				else
					A[from][to] = weights[from][to];

				if (from == to) // Cost from the same node is 0
					A[from][to] = 0;

				P[from][to] = EMPTY;
			}
		}
	}

	public double[][] getA() {
		return A;
	}

	public int[][] getP() {
		return P;
	}

	public String printFloydPath(T from, T to) {
		int indexFromElement = getNode(from); // Retrieve the index of starting node
		int indexToElement = getNode(to); // Retrieve the index of end node
		if (indexFromElement == INDEX_NOT_FOUND || indexToElement == INDEX_NOT_FOUND) // Return null if the element is
																						// not in the graph
			throw new ElementNotPresentException("The starting node and the final node must be in teh graph");

		floyd(getSize());
		String path = FPPrint(indexFromElement, indexToElement);

		if (path.contains("_NO_PATH_FOUND_TO_"))
			return "_NO_PATH_FOUND_TO_";
		// System.out.println("" + null); =null
		return path;
	}

	private String FPPrint(int indexFromElement, int indexToElement) {
		String path = "";

		int pivotIndex = P[indexFromElement][indexToElement];
		if (pivotIndex == EMPTY) {
			if (A[indexFromElement][indexToElement] != INFINITE)
				return "" /* + nodes[indexFromElement] */;
			return "_NO_PATH_FOUND_TO_";
		}
		if (!existsEdge(nodes[indexFromElement], nodes[pivotIndex])) {
			path += FPPrint(indexFromElement, pivotIndex);
		}

		path += nodes[pivotIndex] + FPPrint(pivotIndex, indexToElement);
		return path;
	}

}
