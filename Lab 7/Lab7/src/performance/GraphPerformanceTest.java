package performance;

import graphs.Graph;

public class GraphPerformanceTest {
	/**
	 * Returns a graph of Integer elements containing n nodes. Every node is connected with each other by and edge of weight calculated as a random value
	 */
	public static Graph<Integer> initGraph(long n){
		TestBench.doNothing(n);
		if (n<=0)
			throw new IllegalArgumentException("The number must be greater than 0");
		Graph<Integer> graph = new Graph<Integer>((int) n);
		for (int i=0; i<n; i++) {
			graph.addNode(i);
		}
		for (int i=0; i<n; i++) {
			System.out.println("initGraph " + i);
			for (int j=0; j<n; j++) {
				
				graph.addEdge(i, j, Math.random());
			}
		}
		return graph;
	}
	
	/**
	 * Calls to the initGraph(n) method and runs the Dijkstra algorithm on the resulting graph
	 * @param n
	 */
	public static void runDijkstra(long n) {
		Graph<Integer> graph = initGraph(n);
		TestBench.doNothing(n);
		graph.dijkstra(0);
		
	}
	
	/**
	 * Calls to the initGraph(n) method and runs the Floyd algorithm on the resulting graph
	 * @param n
	 */
	public static void runFloyd(long n) {
		System.out.println("ITERACION " + n);
		Graph<Integer> graph = initGraph(n);
		System.out.println("initGraph terminado.");
		TestBench.doNothing(n);
		System.out.println("Antes de llamar a floyd.");
		graph.floyd(graph.getSize());
		System.out.println("Despues de llamar a floyd.");
	}
}
