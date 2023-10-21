package graphs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GraphIsSourceNodeTest {
	
	@Test
	void testSourceNode() {
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
		graph.addEdge(4, 3, 2);
		graph.addEdge(4, 5, 6);
		
		assertTrue(graph.isSourceNode(1));
		assertFalse(graph.isSourceNode(2));
		assertFalse(graph.isSourceNode(3));
		assertFalse(graph.isSourceNode(4));
		assertFalse(graph.isSourceNode(5));
	}
}
