package p2Grafos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GraphEx2023OutDegreeTest {

	@Test
	void outDegreeTest() {
		GraphEx2023<String> graph = new GraphEx2023<>(5);
		graph.addNodeEx("A");
		assertEquals(0,graph.outDegree("A"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addNodeEx("B");
		assertEquals(0,graph.outDegree("A"));
		assertEquals(0,graph.outDegree("B"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("A","B",1);
		assertEquals(1,graph.outDegree("A"));
		assertEquals(0,graph.outDegree("B"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addNodeEx("C");
		assertEquals(1,graph.outDegree("A"));
		assertEquals(0,graph.outDegree("B"));
		assertEquals(0,graph.outDegree("C"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("B","A",3);
		assertEquals(1,graph.outDegree("A"));
		assertEquals(1,graph.outDegree("B"));
		assertEquals(0,graph.outDegree("C"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addNodeEx("D");
		assertEquals(1,graph.outDegree("A"));
		assertEquals(1,graph.outDegree("B"));
		assertEquals(0,graph.outDegree("C"));
		assertEquals(0,graph.outDegree("D"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("D","B",2);
		assertEquals(1,graph.outDegree("A"));
		assertEquals(1,graph.outDegree("B"));
		assertEquals(0,graph.outDegree("C"));
		assertEquals(1,graph.outDegree("D"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addNodeEx("E");
		assertEquals(1,graph.outDegree("A"));
		assertEquals(1,graph.outDegree("B"));
		assertEquals(0,graph.outDegree("C"));
		assertEquals(1,graph.outDegree("D"));
		assertEquals(0,graph.outDegree("E"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("E","C",6);
		assertEquals(1,graph.outDegree("A"));
		assertEquals(1,graph.outDegree("B"));
		assertEquals(0,graph.outDegree("C"));
		assertEquals(1,graph.outDegree("D"));
		assertEquals(1,graph.outDegree("E"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("E","D",3);
		assertEquals(1,graph.outDegree("A"));
		assertEquals(1,graph.outDegree("B"));
		assertEquals(0,graph.outDegree("C"));
		assertEquals(1,graph.outDegree("D"));
		assertEquals(2,graph.outDegree("E"));
		
		try {
			graph.outDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
	}

}
