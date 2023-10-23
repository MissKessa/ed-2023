package p2Grafos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GraphEx2023ReciprocityRateTest {

	/**
	 * GIVEN: a graph with no nodes
	 * WHEN: calculating the reciprocityRate
	 * THEN: an IllegalArgumentException is thrown because there is not enough nodes
	 */
	@Test
	void noNodes() {
		GraphEx2023<String> graph = new GraphEx2023<>(5);
		try {
		graph.reciprocityRate();
		} catch(IllegalArgumentException ex) {
			assertEquals (ex.getMessage(),graph.ERROR_NOT_ENOUGH_NODES_FOR_RECIPROCITY);
		}
	}
	
	/**
	 * GIVEN: a graph with one node
	 * WHEN: calculating the reciprocityRate
	 * THEN: an IllegalArgumentException is thrown because there is not enough nodes
	 */
	@Test
	void oneNode() {
		GraphEx2023<String> graph = new GraphEx2023<>(5);
		graph.addNodeEx("A");
		try {
		graph.reciprocityRate();
		} catch(IllegalArgumentException ex) {
			assertEquals (ex.getMessage(),graph.ERROR_NOT_ENOUGH_NODES_FOR_RECIPROCITY);
		}
	}
	
	/**
	 * GIVEN: a graph with no edges but at least 2 nodes
	 * WHEN: calculating the reciprocityRate
	 * THEN: an IllegalArgumentException is thrown because there is no edges
	 */
	@Test
	void noEdges() {
		GraphEx2023<String> graph = new GraphEx2023<>(5);
		graph.addNodeEx("A");
		graph.addNodeEx("B");
		
		try {
		graph.reciprocityRate();
		} catch(IllegalArgumentException ex) {
			assertEquals (ex.getMessage(),graph.ERROR_NOT_EDGES_FOR_RECIPROCITY);
		}
	}
	
	/**
	 * GIVEN: a graph with at least 2 nodes and all its edges are reciprocal
	 * WHEN: calculating the reciprocityRate
	 * THEN: it returns 1
	 */
	@Test
	void reciprocalGraph() {
		double reciprocityRate=1;
		GraphEx2023<String> graph = new GraphEx2023<>(5);
		graph.addNodeEx("A");
		graph.addNodeEx("B");
		
		graph.addEdgeEx("A","B",1);
		graph.addEdgeEx("B","A",5);
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
		
		graph.addNodeEx("C");
		graph.addNodeEx("D");
		
		graph.addEdgeEx("C","D",2);
		graph.addEdgeEx("D","C",3);
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
		
		graph.addEdgeEx("C","B",6);
		graph.addEdgeEx("B","C",50);
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
		
		graph.addEdgeEx("D","B",2);
		graph.addEdgeEx("B","D",7);
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
	}
	
	/**
	 * GIVEN: a graph with at least 2 nodes and none of its edges are reciprocal
	 * WHEN: calculating the reciprocityRate
	 * THEN: it returns 0
	 */
	void noReciprocalGraph() {
		double reciprocityRate=0;
		GraphEx2023<String> graph = new GraphEx2023<>(5);
		graph.addNodeEx("A");
		graph.addNodeEx("B");
		
		graph.addEdgeEx("A","B",1);
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
		
		graph.addNodeEx("C");
		graph.addNodeEx("D");
		
		graph.addEdgeEx("C","D",2);
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
		
		graph.addEdgeEx("B","C",50);
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
		
		graph.addEdgeEx("D","B",2);
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
	}
	
	/**
	 * GIVEN: a graph with at least 2 nodes and some edges are reciprocal and some not
	 * WHEN: calculating the reciprocityRate
	 * THEN: it returns the percentage
	 */
	@Test
	void doubleReciprocalGraph() {
		GraphEx2023<String> graph = new GraphEx2023<>(5);
		graph.addNodeEx("A");
		graph.addNodeEx("B");
		
		graph.addEdgeEx("A","B",1);
		graph.addEdgeEx("B","A",5);
		double reciprocityRate=1;
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
		
		graph.addNodeEx("C");
		graph.addNodeEx("D");
		
		graph.addEdgeEx("C","D",2);
		reciprocityRate=0.5; //(1+0)/2
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
		
		graph.addEdgeEx("C","B",6);
		graph.addEdgeEx("B","C",50);
		reciprocityRate=0.666; //(1+0+1)/3
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
		
		graph.addEdgeEx("D","B",2);
		reciprocityRate=0.5; //(1+0+1+0)/4
		assertEquals(reciprocityRate, graph.reciprocityRate(), 0.01);
	}

}
