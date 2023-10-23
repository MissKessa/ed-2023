package p2Grafos;

import java.text.DecimalFormat;

/**
 * @author Profesores ED 2023
 * @version 2023-24
 */
public class GraphEx2023<T> {
	public static final String ERROR_NOT_EDGES_FOR_RECIPROCITY = "There are no edges to calculate the reciprocity rate";

	public static final String ERROR_NOT_ENOUGH_NODES_FOR_RECIPROCITY = "There must be at least 2 nodes to calculate the reciprocity rate";

	public static final String ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH = "The node is not in the graph";

	/**
	 * Constante infinito
	 */
	protected static final double Inf=Double.POSITIVE_INFINITY;
	
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

	
	/**
	 * 
	 * @param tam
	 *            Numero maximo de nodos del grafo
	 */
	@SuppressWarnings("unchecked")
	public GraphEx2023(int tam) {
		nodes = (T[]) new Object[tam];
		numNodes = 0;
		edges = new boolean[tam][tam];
		weights = new double[tam][tam];
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
	
	/**
	 * 
	 * @return the percentage (between 0 and 1) of reciprocal edges over the total number of edges. An edge from u to v is reciprocal if there exits another edge from v to u
	 * @throws IllegalStateException when the percentage cannot b calculated
	 */
	public double reciprocityRate() {
		if (numNodes <=1)
			throw new IllegalArgumentException(ERROR_NOT_ENOUGH_NODES_FOR_RECIPROCITY);
		double percentage=0;
		int numberOfEdges=0;
		boolean[][] edgesProcessed = new boolean[numNodes][numNodes];
		for (int i=0; i<numNodes; i++) {
			for (int j =0; j<numNodes; j++) {
				if (edges[i][j] && !edgesProcessed[i][j]) {
					percentage+=reciprocityRateEdge(i,j);
					numberOfEdges++;
					edgesProcessed[i][j]=true;
					edgesProcessed[j][i]=true;

				}
			}
		}
		
		if (numberOfEdges==0)
			throw new IllegalArgumentException(ERROR_NOT_EDGES_FOR_RECIPROCITY);
		return percentage/numberOfEdges;
	}
	
	/**
	 * 
	 * @param v is the index of the source node
	 * @param u is the index of the destination node
	 * @return if an edge is reciprocal
	 */
	private double reciprocityRateEdge (int v, int u) {
		double percentage;
	
		if (edges[v][u]&& edges[u][v])
			percentage=1;
		else
			percentage=0;
		return percentage;
	}
	
	/**
	 * 
	 * @param element
	 * @return the number of edges whose destination is the element
	 * @throws IllegalArgumentException when the element is not part of the graph
	 */
	public int inDegree (T element) {
		int degree=0;
		int index = getNodeEx(element);
		
		if (index == -1)
			throw new IllegalArgumentException(ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH);
		for (int i=0; i<numNodes;i++) {
			if (edges[i][index])
				degree++;
		}
		return degree;
	}
	
	/**
	 * 
	 * @param element
	 * @return the number of edges whose source is the element
	 * @throws IllegalArgumentException when the element is not part of the graph
	 */
	public int outDegree (T element) {
		int degree=0;
		int index = getNodeEx(element);
		
		if (index == -1)
			throw new IllegalArgumentException(ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH);
		
		for (int i=0; i<numNodes;i++) {
			if (edges[index][i])
				degree++;
		}
		return degree;
	}
	
	/**
	 * 
	 * @return whether the graph is balanced or not
	 */
	public boolean isBalanced() {
		for (int i =0; i<numNodes; i++) {
			if (!isBalanced(nodes[i]))
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param element is the node to check if it's balanced or not
	 * @return whether the element is balanced or not (inDegree==outDegree)
	 */
	private boolean isBalanced(T element) {
		return outDegree(element)==inDegree(element);
	}
}