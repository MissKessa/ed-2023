package p2Grafos;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Profesores ED 2023
 * @version 2023-24
 */
public class Graph<T> {
	private static final int EDGE_WEIGHT = 1;

	/**
	 * Constante infinito
	 */
	protected static final double Inf=Double.POSITIVE_INFINITY;

	private static final int INDEX_NOT_FOUND = -1;
	public static final double INFINITE = Double.POSITIVE_INFINITY;
	
	/**
	 * Vector de nodos
	 */
	protected T[] nodes; // Vector de nodos
	/**
	 * Matriz de aristas
	 */
	protected boolean[][] edges; // matriz de aristas
	/**
	 * Matriz de pesos
	 */
	protected double[][] weights; // matriz de pesos

	/**
	 * Numero de elementos en un momento dado
	 */
	protected int numNodes; // numero de elementos en un momento dado

	protected double[][]A;
	protected int[][]P;
	/**
	 * 
	 * @param tam
	 *            Numero maximo de nodos del grafo
	 */
	@SuppressWarnings("unchecked")
	public Graph(int tam) {
		nodes = (T[]) new Object[tam];
		numNodes = 0;
		edges = new boolean[tam][tam];
		weights = new double[tam][tam];
		A= new double[tam][tam];
		P= new int[tam][tam];
	}

	public int getNumMaxNodes() {
		return nodes.length;
	}
	
	protected int getNumNodes() {
		return numNodes;
	}

	protected T[] getNodes() {
		return nodes;
	}

	protected boolean[][] getEdges() {
		return edges;
	}

	protected double[][] getWeights() {
		return weights;
	}

	protected double[][] getWeight() { // para compatibilidad con pruebas ingles
		return getWeights();
	}


	/**
	 * Obtiene el indice de un nodo en el vector de nodos
	 * 
	 * @param node que es el nodo que se busca
	 * @return la posicion del nodo en el vector, -1 si no se encuentra
	 */
	protected int getNodeEx(T node) {
		for (int i = 0; i < numNodes; i++) {
			if (nodes[i].equals(node)) {
				return i;
			}
		}
		return -1;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return numNodes;
	}

	/**
	 * Inserta un nuevo nodo que se le pasa como parametro.
	 * Siempre lo inserta, no se controlan casos en que no lo pueda hacer
	 * 
	 * @param node el nodo que se quiere insertar
	 * @return true siempre
	 */
	public boolean addNodeEx(T node) {
		nodes[numNodes] = node;
		for (int i = 0; i <= numNodes; i++) {
			edges[numNodes][i] = false;
			edges[i][numNodes] = false;
		}
		numNodes++;
		return true;
	}


	public void addNode(T node) {
		addNodeEx(node);
	}

	/**
	 * Inserta una arista entre dos nodos con el peso indicado 
	 * Devuelve true siempre
	 * No comprueba nada. 
	 * @param source
	 *            nodo origen
	 * @param target
	 *            nodo destino
	 * @param edgeWeight
	 *            peso de la arista
	 * @return true siempre
	 */
	public boolean addEdgeEx(T source, T target, double edgeWeight) {
		int posOrigen = getNodeEx(source);
		int posDestino = getNodeEx(target);
		edges[posOrigen][posDestino] = true;
		weights[posOrigen][posDestino] = edgeWeight;
		return true;
	}

	public void addEdge(T start, T arrival, double weight) {
		addEdgeEx(start, arrival, weight);
		
	}

	public int getNode(T node) {
		return getNodeEx(node);
	}

	/**
	 * Borra la arista del grafo que conecta dos nodos.
	 * Siempre la borra sin comprobar nada
	 * 
	 * @param source Nodo origen de la arista
	 * @param target Nodo destino de la arista
	 * @return true siempre
	 */
	public boolean removeEdgeEx(T source, T target) {
		int posOrigen = getNodeEx(source);
		int posDestino = getNodeEx(target);
		edges[posOrigen][posDestino] = false;
		return true;
	}

	/**
	 * Devuelve el peso de la arista que conecta dos nodos.
	 * No comprueba nada...
	 *  
	 * @param source Nodo origen de la arista
	 * @param target Nodo destino de la arista
	 * @return El peso de la arista
	 */
	public double getEdgeEx(T source, T target) {
		int posOrigen = getNodeEx(source);
		int posDestino = getNodeEx(target);
		return weights[posOrigen][posDestino];
	}

	public boolean removeNode(T node) {
		if (node == null)
			throw new NullPointerException("The node cannot be null");
	
		int delIndex = getNode(node);
		if (delIndex == INDEX_NOT_FOUND)
			return false;
	
		int lastIndex = numNodes - 1;
		numNodes--;
		nodes[delIndex] = nodes[lastIndex];
	
		for (int i = 0; i < numNodes; i++) { // to not move the corner
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
	 * @return Devuelve un String con la informacion del grafo usando StringBuilder
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		StringBuilder cadena = new StringBuilder();
		
		cadena.append("NODES\n");
		for (int i = 0; i < numNodes; i++) {
			cadena.append(nodes[i].toString() + "\t");
		}
		cadena.append("\n\nEDGES\n");
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (edges[i][j])
					cadena.append("T\t");
				else
					cadena.append("F\t");
			}
			cadena.append("\n");
		}
		cadena.append("\nWEIGHTS\n");
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
	
				cadena.append((edges[i][j]?df.format(weights[i][j]):"-") + "\t");
			}
			cadena.append("\n");
		}
	
		return cadena.toString();
	}

	public String BFPrint(T start) {
		boolean visited[]= new boolean[getNumNodes()];
		int indexStart = getNodeEx(start);
		ArrayList <Integer> nodesToProcess = new ArrayList<Integer>(getNumNodes());
		String print="";
	
		
		nodesToProcess.add(indexStart);
		while (!nodesToProcess.isEmpty()) {
			int indexNodeProcessed = nodesToProcess.get(0);
			
			if (!visited[indexNodeProcessed]) {
				nodesToProcess.add(indexNodeProcessed);
				visited[indexNodeProcessed]=true;
				
				for (int i=0; i<getNumNodes();i++) {
					if (edges[indexNodeProcessed][i]) {
						nodesToProcess.add(i);
					}
				}
				print+=nodes[indexNodeProcessed]+"-";
			}
			nodesToProcess.remove(0);
		}
		
		return print;
	}


	public T getCenter() {
		floyd();
		double[] eccentricity = new double[getNumNodes()];
		
		for (int col=0; col<getNumNodes();col++) {
			double maxCost = A[0][col];
			
			for (int row=0; row<getNumNodes();row++) {
				if (A[row][col]>maxCost) {
					maxCost=A[row][col];
				}
			}
			eccentricity[col]=maxCost;
			
		}
		int indexOfCenterNode=0;
		double minimumCost=eccentricity[0];
		for (int i=0; i<eccentricity.length;i++) {
			if (eccentricity[i]<minimumCost)
				indexOfCenterNode=i;
		}
		return nodes[indexOfCenterNode];
	}

	private void floyd() {
		initsfloyd();
		for (int pivot=0; pivot<getNumNodes();pivot++) {
			for (int i=0; i<getNumNodes();i++) {
				for (int j=0; j<getNumNodes();j++) {
					
					if (A[i][j]>A[i][pivot]+A[pivot][j]) {
						A[i][j]=A[i][pivot]+A[pivot][j];
						P[i][j]=pivot;
					}
				}
			}
		}
		
	}

	private void initsfloyd() {
		for (int i=0; i<getNumNodes();i++) {
			for (int j=0; j<getNumNodes();j++) {
				if (edges[i][j])
					A[i][j]=weights[i][j];
				else
					A[i][j]= INFINITE;
				
				if (i == j) // !!!!!!!! Cost from the same node is 0
					A[i][j]=0;
				P[i][j]=INDEX_NOT_FOUND;
			}
		}
		
	}

	public void print() {
		// TODO Auto-generated method stub
		
	}

	public int shortestPathLength(T start, T arrival) {
		initsShortestPathLength();
		shortestPath();
		int indexStart=getNode(start);
		int indexArrival=getNode(arrival);
		if (indexStart==INDEX_NOT_FOUND || indexArrival==INDEX_NOT_FOUND){
			throw new IllegalArgumentException("they are not in the graph");
		}
		return (int)A[indexStart][indexArrival];
	}
	
	private void shortestPath() {
		initsShortestPathLength();
		for (int pivot=0; pivot<getNumNodes();pivot++) {
			for (int i=0; i<getNumNodes();i++) {
				for (int j=0; j<getNumNodes();j++) {
					
					if (A[i][j]>A[i][pivot]+A[pivot][j]) {
						A[i][j]=A[i][pivot]+A[pivot][j];
						P[i][j]=pivot;
					}
				}
			}
		}
		
	}
	
	private void initsShortestPathLength() {
		for (int i=0; i<getNumNodes();i++) {
			for (int j=0; j<getNumNodes();j++) {
				if (edges[i][j])
					A[i][j]=EDGE_WEIGHT;
				else
					A[i][j]= INFINITE;
				
				if (i == j) // !!!!!!!! Cost from the same node is 0
					A[i][j]=0;
				P[i][j]=INDEX_NOT_FOUND;
			}
		}
		
	}
}