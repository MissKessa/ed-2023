package graphs;

public class Graph<T> {
<<<<<<< HEAD
	private static final String NO_PATH_FOUND = "_NO_PATH_FOUND_TO_";
=======
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
	public static final int UNREACHABLE_NODE = -1;
	public static final int INDEX_NOT_FOUND = UNREACHABLE_NODE;
	public static final int EMPTY = UNREACHABLE_NODE;
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
<<<<<<< HEAD
	 * @param element is the given node to check its index on the list of nodes
=======
	 * @param node is the given node to check its index on the list of nodes
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
	 * @return the index of the given node. If the node is not in the list of nodes,
	 *         it returns INDEX_NOT_FOUND
	 * @throws NullPointerException if the node is null
	 */
<<<<<<< HEAD
	public int getNode(T element) { // O(n)
		if (element == null)
			throw new NullPointerException("The node cannot be null");

		for (int i = 0; i < size; i++) {
			if (element.equals(nodes[i]))
=======
	public int getNode(T node) { // O(n)
		if (node == null)
			throw new NullPointerException("The node cannot be null");

		for (int i = 0; i < size; i++) {
			if (node.equals(nodes[i]))
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
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

<<<<<<< HEAD
	/**
	 * 
	 * @return the edges matrix
	 */
=======
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
	public boolean[][] getEdges() {
		return edges;
	}

<<<<<<< HEAD
	/**
	 * 
	 * @return the weights matrix
	 */
=======
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
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
<<<<<<< HEAD
	 * @param element is the node to insert
=======
	 * @param node is the node to insert
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
	 * @return true if the node is inserted. Returns false if it already exists (it
	 *         doesn't insert it).
	 * @throws NullPointerException   if it receives a null node
	 * @throws FullStructuredExcepion if it doesn't fit (it is not inserted)
	 */
<<<<<<< HEAD
	public boolean addNode(T element) {
		if (element == null)
=======
	public boolean addNode(T node) {
		if (node == null)
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
			throw new NullPointerException("The node cannot be null");

		if (size == nodes.length)
			throw new FullStructureException("The list of nodes is full"); // or IllegalStateExcepion

<<<<<<< HEAD
		if (getNode(element) != INDEX_NOT_FOUND) // already in the graph
			return false;

		nodes[size] = element;
=======
		if (getNode(node) != INDEX_NOT_FOUND)
			return false;

		nodes[size] = node;
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368

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

<<<<<<< HEAD
	/**
	 * Delete the edge of the graph that connects two nodes.
	 * 
	 * @param origin      is the departure node of the edge to be removed
	 * @param destination is the arrival node of the edge to be removed
	 * @return true if the edge exists and it's deleted. If the edge to delete does
	 *         not exist, it returns false.
	 * @throws ElementNotPresentException if the origin or destination nodes do not
	 *                                    exist
	 * @throws NullPointerException       if the origin or destination nodes are
	 *                                    null
	 */
=======
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
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
<<<<<<< HEAD
	 * The given element is deleted from the nodes, edges and weights matrices
	 * 
	 * @param element is the node to be removed
	 * @return true if the element exists, it is deleted from the node vector as
	 *         well as the edges it is a part of. If the node does not exist, it
	 *         returns false.
	 * @throws NullPointerException if the element is null
	 */
	public boolean removeNode(T element) { // O(n)
		if (element == null)
			throw new NullPointerException("The node cannot be null");

		int delIndex = getNode(element);
=======
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
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
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

	/**
<<<<<<< HEAD
	 * Calculates floyd and calculates the minimum cost and pivots in A and P
	 * 
	 * @param limit is the limit on the length of A and P to calculate floyd
	 * @throws IllegalArgumentException if the limit is greater than the size of the
	 *                                  graph
	 */
	public void floyd(int limit) {
		if (limit > size)
			throw new IllegalArgumentException("The limit must be less than or equal to the size of the graph");
=======
	 * Initializes the A and P matrix for floyd. 
	 * @param limit
	 */
	public void floyd(int limit) {
//		if (limit>size)
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
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

<<<<<<< HEAD
	/**
	 * Initializes the A and P matrices for floyd.
	 * 
	 * @param limit is the limit on the length of A and P to calculate floyd
	 */
=======
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
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

<<<<<<< HEAD
	/**
	 * 
	 * @return A matrix of floyd
	 */
=======
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
	public double[][] getA() {
		return A;
	}

<<<<<<< HEAD
	/**
	 * 
	 * @return P matrix of floyd
	 */
=======
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
	public int[][] getP() {
		return P;
	}

<<<<<<< HEAD
	/**
	 * 
	 * @param from is the departure node
	 * @param to   is the arrival node
	 * @return the path minimum cost path from the departure node to the arrival
	 *         node (without the value of the departure and arrival). If there is no
	 *         path, it returns NO_PATH_FOUND
	 * @throws ElementNotPresentException if the departure or arrival are not in the
	 *                                    graph
	 */
=======
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
	public String printFloydPath(T from, T to) {
		int indexFromElement = getNode(from); // Retrieve the index of starting node
		int indexToElement = getNode(to); // Retrieve the index of end node
		if (indexFromElement == INDEX_NOT_FOUND || indexToElement == INDEX_NOT_FOUND) // Return null if the element is
																						// not in the graph
<<<<<<< HEAD
			throw new ElementNotPresentException("The starting node and the final node must be in the graph");

		// floyd(getSize());
		String path = FPPrint(indexFromElement, indexToElement);

		if (path.contains(NO_PATH_FOUND))
			return NO_PATH_FOUND;

		return path;
	}

	/**
	 * 
	 * @param indexFromElement is the index of the departure
	 * @param indexToElement   is the index of the arrival
	 * @return the path minimum cost path from the departure node to the arrival
	 *         node (without the value of the departure and arrival).
	 */
	private String FPPrint(int indexFromElement, int indexToElement) { // exponential complexity
=======
			throw new ElementNotPresentException("The starting node and the final node must be in teh graph");

		//floyd(getSize());
		String path = FPPrint(indexFromElement, indexToElement);

		if (path.contains("_NO_PATH_FOUND_TO_"))
			return "_NO_PATH_FOUND_TO_";
		// System.out.println("" + null); =null
		return path;
	}

	private String FPPrint(int indexFromElement, int indexToElement) { //exponenial complexity
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
		String path = "";

		int pivotIndex = P[indexFromElement][indexToElement];
		if (pivotIndex == EMPTY) {
			if (A[indexFromElement][indexToElement] != INFINITE)
				return "" /* + nodes[indexFromElement] */;
<<<<<<< HEAD
			return NO_PATH_FOUND;
=======
			return "_NO_PATH_FOUND_TO_";
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
		}
		if (!existsEdge(nodes[indexFromElement], nodes[pivotIndex])) {
			path += FPPrint(indexFromElement, pivotIndex);
		}

		path += nodes[pivotIndex] + FPPrint(pivotIndex, indexToElement);
		return path;
	}
<<<<<<< HEAD

	/**
	 * Initializes the array d and pD for dijkstra
	 * 
	 * @param d            is the minimum cost array
	 * @param pD           is the pivots array
	 * @param elementIndex is the index of the node we are calculating dijkstra for
	 */
	// Complexity: O(size)
	private void initsDijkstra(double[] d, int[] pD, int elementIndex) { // d and pD are instantiated already (fill with
																			// 0s)
		for (int to = 0; to < size; to++) {
			if (edges[elementIndex][to]) {
				d[to] = weights[elementIndex][to];
				pD[to] = elementIndex;
			} else {
				d[to] = INFINITE;
				pD[to] = EMPTY;
			}
		}
		d[elementIndex] = 0;
		pD[elementIndex] = elementIndex;
	}

	/**
	 * 
	 * @param element is the node for which we are calculating dijkstra
	 * @return a DijkstraDataClass with the matrices d and pD calculated. If the
	 *         element doesn't exist it returns null
	 */
	// Complexity: O(size^2)
	public DijkstraDataClass dijkstra(T element) { // return an object with all the information we want to return
													// because
													// Java doesn't allow multiple returns: return d, pD
		if (getNode(element) == INDEX_NOT_FOUND)
			return null;
		double[] d = new double[getSize()];
		int[] pD = new int[getSize()];
		int startIndex = getNode(element);

		initsDijkstra(d, pD, startIndex);
		boolean[] s = new boolean[getSize()];

		s[startIndex] = true;

		int pivot = getPivot(s, d); // returns the index of the best pivot: cheapest reach from the start, and has
									// not been used it. It returns -1 when they are not more valid pivots (when S
									// is all trues, or a node that cannot reached)
		while (pivot != EMPTY) { // O(Reachable nodes -1): it's going to be more linear if here is a few of
									// reachable nodes. it will be exponential if
			s[pivot] = true;
			for (int to = 0; to < size; to++) {
				double newCost = d[pivot] + weights[pivot][to];
				double oldCost = d[to];

				if (edges[pivot][to] && oldCost > newCost) {
					d[to] = newCost;
					pD[to] = pivot;
				}
			}
			pivot = getPivot(s, d); // update pivot
		}

		return new DijkstraDataClass(getSize(), startIndex, d, pD);
	}

	/**
	 * 
	 * @param s is the matrix that contains what nodes have been used as pivots
	 * @param d is the d matrix of dijkstra which contains the minimum costs
	 * @return the index of the pivot for dijkstra. If there is not more pivots
	 *         available it returns EMPTY
	 */
	private int getPivot(boolean[] s, double[] d) { // O(size)
		int indexPivot = EMPTY;
		double costPivot = INFINITE;

		for (int i = 0; i < s.length; i++) {
			if (s[i])
				continue;
			if (d[i] < costPivot) {
				costPivot = d[i];
				indexPivot = i;
			}
		}
		return indexPivot;
	}

	/**
	 * 
	 * @param element is the node we are checking if it's a source node or not
	 * @return true if the node is a source node (doesn't have a parent, but it has
	 *         children)
	 * @throws IllegalArgumentException if the element is not in the graph
	 */
	public boolean isSourceNode(T element) {
		int indexNode = getNode(element);
		if (indexNode == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("The node is not in the graph");

		boolean hasChildren = false;
		for (int i = 0; i < nodes.length; i++) {
			if (edges[i][indexNode]) // cannot have parent
				return false;
			if (edges[indexNode][i]) // must have children
				hasChildren = true;
		}
		return hasChildren;
	}

	/**
	 * 
	 * @param element is the node we are checking if it's a drain node or not
	 * @return true if the node is a drain node (has parents, but it doesn't have
	 *         children)
	 * @throws IllegalArgumentException if the element is not in the graph
	 */
	public boolean isDrainNode(T element) {
		int indexNode = getNode(element);
		if (indexNode == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("The node is not in the graph");

		boolean hasParent = false;
		for (int i = 0; i < nodes.length; i++) {
			if (edges[indexNode][i]) // cannot have children
				return false;

			if (edges[i][indexNode]) // must have a parent
				hasParent = true;
=======
	
	/**
	 * Initializes the array d and pD for dijkstra
	 * @param d
	 * @param pD
	 * @param startIndex
	 */
	/*
	 * Complexity: O(size)
	 */
	public void initsDijkstra(double[] d, int[] pD, int startIndex) { //d and pD are instantiated already (fill with 0s)
		for (int to=0; to<size; to++) {
			if(edges[startIndex][to]) {
				d[to]=weights[startIndex][to];
				pD[to]=startIndex;
			} else {
				d[to]=INFINITE;
				pD[to]=EMPTY;
			}
		}
		d[startIndex]=0;
		pD[startIndex]=startIndex;
	}

	/*
	 * Complexity: O(size^2)
	 */
	public DijkstraDataClass dijkstra(T start) { //return an object with all the information we want to return because Java doesn't allow multiple returns: return d, pD
		if (getNode(start)== INDEX_NOT_FOUND)
			return null;
		double[] d = new double[getSize()];
		int[] pD = new int[getSize()];
		int startIndex =getNode(start);
		
		initsDijkstra(d, pD, startIndex);
		boolean[] s = new boolean[getSize()];
		
		s[startIndex]=true;
		
		int pivot = getPivot(s, d); //returns the index of the best pivot: cheapest reach from the start, and has not been used et. It returns -1 when they are not more valid pivots (when S is all trues, or a node that cannot reached)
		while (pivot!=EMPTY) { // O(Reachable nodes -1): it's going to be more linear if here is a few of reachables nodes. it will be exponential if
			s[pivot]=true;
			for (int to=0; to<size; to++) {
				double newCost = d[pivot]+weights[pivot][to];
				double oldCost =d[to];
				
				if (edges[pivot][to] && oldCost>newCost) {
					d[to]=newCost;
					pD[to]=pivot;
				}
			}
			pivot = getPivot(s,d); //update pivot
		}
		
		return new DijkstraDataClass(getSize(), startIndex,d, pD);
	}

	private int getPivot(boolean[] s, double[] d) { //O(size)
		int indexPivot=EMPTY;
		double costPivot=INFINITE;
		
		for (int i = 0; i<s.length;i++) {
			if (s[i])
				continue;
			if (d[i]<costPivot) {
				costPivot = d[i];
				indexPivot = i;	
			}			
		}
		return indexPivot;
	}
	
	public boolean isSourceNode(T node) {
		int indexNode = getNode(node);
		if (indexNode== INDEX_NOT_FOUND)
			throw new IllegalArgumentException("The node is not in the graph");
		
		boolean hasChildren=false;
		for (int i=0; i<nodes.length ; i++) {
			if (edges[i][indexNode]) //cannot have parent
				return false;
			if (edges[indexNode][i]) //must have children
				hasChildren=true;
		}
		return hasChildren;
	}
	
	public boolean isDrainNode(T node) {
		int indexNode = getNode(node);
		if (indexNode== INDEX_NOT_FOUND)
			throw new IllegalArgumentException("The node is not in the graph");
		
		boolean hasParent=false;
		for (int i=0; i<nodes.length ; i++) {
			if (edges[indexNode][i]) //cannot have children
				return false;
			
			if (edges[i][indexNode]) //must have a parent
				hasParent=true;
>>>>>>> bd3aafa4aef08f2c5fdd3499e2349ddc5a11c368
		}
		return hasParent;
	}

}
