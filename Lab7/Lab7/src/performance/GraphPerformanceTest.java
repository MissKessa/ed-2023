package performance;

import graphs.Graph;

public class GraphPerformanceTest {
	/**
	 * Returns a graph of Integer elements containing n nodes. Every node is connected with each other by and edge of weight calculated as a random value
	 */
	public static Graph<Integer> initGraph(long n){
		
		if (n<=0)
			throw new IllegalArgumentException("The number must be greater than 0");
		Graph<Integer> graph = new Graph<Integer>((int) n);
		for (int i=0; i<n; i++) {
			graph.addNode(i);
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				TestBench.doNothing(n);
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
		Graph<Integer> graph = initGraph(n);
		TestBench.doNothing(n);
		graph.floyd(graph.getSize());
	}
}
