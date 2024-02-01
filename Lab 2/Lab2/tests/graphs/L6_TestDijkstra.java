package graphs;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;


public class L6_TestDijkstra {
	@Test
	public void testDijkstra1() throws Exception {
		Graph<Integer> graph = new Graph<Integer>(6);

		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);

		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 5, 10);
		graph.addEdge(1, 4, 3);
		graph.addEdge(2, 3, 5);
		graph.addEdge(2, 2, 4);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 3, 2);
		graph.addEdge(4, 5, 6);

		DijkstraDataClass result = graph.dijkstra(1);

		
		Assert.assertArrayEquals(new double[] { 0.0, 1.0, 5.0, 3.0, 6.0 }, result.getdDijkstra(), 0);
		Assert.assertArrayEquals(new int[] { 0, 0, 3, 0, 2 }, result.getpDijkstra());
		
		result = graph.dijkstra(2);
		Assert.assertArrayEquals(new int[] { Graph.EMPTY, 1, 1, Graph.EMPTY, 2 }, result.getpDijkstra());
		Assert.assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 0.0, 5.0, Double.POSITIVE_INFINITY, 6.0 },
				result.getdDijkstra(), 0);
		result = graph.dijkstra(3);

		Assert.assertArrayEquals(
				new double[] { Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY, 1.0 },
				result.getdDijkstra(), 0);
		result = graph.dijkstra(4);

		Assert.assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 2.0, 0.0, 3.0 },
				result.getdDijkstra(), 0);
		result = graph.dijkstra(5);

		Assert.assertArrayEquals(new int[] { Graph.EMPTY,  Graph.EMPTY,  Graph.EMPTY, Graph.EMPTY, 4 }, result.getpDijkstra());
		Assert.assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
				Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0 }, result.getdDijkstra(), 0);

		Assert.assertNull(graph.dijkstra(6));

		// Añadimos otra arista
		graph.addEdge(5, 2, 1);

		result = graph.dijkstra(1);
		Assert.assertArrayEquals(new int[] { 0, 0, 3, 0, 2 }, result.getpDijkstra());
		Assert.assertArrayEquals(new double[] { 0.0, 1.0, 5.0, 3.0, 6.0 }, result.getdDijkstra(), 0);

		result = graph.dijkstra(2);
		Assert.assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 0.0, 5.0, Double.POSITIVE_INFINITY, 6.0 },
				result.getdDijkstra(), 0);

		result = graph.dijkstra(3);
		Assert.assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 2.0, 0.0, Double.POSITIVE_INFINITY, 1.0 },
				result.getdDijkstra(), 0);

		result = graph.dijkstra(4);

		Assert.assertArrayEquals(new int[] { Graph.EMPTY, 4, 3, 3, 2 }, result.getpDijkstra());
		Assert.assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 4.0, 2.0, 0.0, 3.0 }, result.getdDijkstra(),
				0);

		result = graph.dijkstra(5);
		Assert.assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 1.0, 6.0, Double.POSITIVE_INFINITY, 0.0 },
				result.getdDijkstra(), 0);

		// Añadimos un nodo aislado
		graph.addNode(6);

		result = graph.dijkstra(1);

		Assert.assertArrayEquals(new int[] {0 , 0, 3, 0, 2, Graph.EMPTY }, result.getpDijkstra());
		Assert.assertArrayEquals(new double[] { 0.0, 1.0, 5.0, 3.0, 6.0, Double.POSITIVE_INFINITY },
				result.getdDijkstra(), 0);

	}

}