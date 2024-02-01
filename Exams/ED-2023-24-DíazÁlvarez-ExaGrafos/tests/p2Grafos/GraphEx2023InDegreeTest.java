package p2Grafos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GraphEx2023InDegreeTest {


	@Test
	void inDegreeTest() {
		GraphEx2023<String> graph = new GraphEx2023<>(5);
		graph.addNodeEx("A");
		assertEquals(0,graph.inDegree("A"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addNodeEx("B");
		assertEquals(0,graph.inDegree("A"));
		assertEquals(0,graph.inDegree("B"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("A","B",1);
		assertEquals(0,graph.inDegree("A"));
		assertEquals(1,graph.inDegree("B"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addNodeEx("C");
		assertEquals(0,graph.inDegree("A"));
		assertEquals(1,graph.inDegree("B"));
		assertEquals(0,graph.inDegree("C"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("B","A",3);
		assertEquals(1,graph.inDegree("A"));
		assertEquals(1,graph.inDegree("B"));
		assertEquals(0,graph.inDegree("C"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addNodeEx("D");
		assertEquals(1,graph.inDegree("A"));
		assertEquals(1,graph.inDegree("B"));
		assertEquals(0,graph.inDegree("C"));
		assertEquals(0,graph.inDegree("D"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("D","B",2);
		assertEquals(1,graph.inDegree("A"));
		assertEquals(2,graph.inDegree("B"));
		assertEquals(0,graph.inDegree("C"));
		assertEquals(0,graph.inDegree("D"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addNodeEx("E");
		assertEquals(1,graph.inDegree("A"));
		assertEquals(2,graph.inDegree("B"));
		assertEquals(0,graph.inDegree("C"));
		assertEquals(0,graph.inDegree("D"));
		assertEquals(0,graph.inDegree("E"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("E","C",6);
		assertEquals(1,graph.inDegree("A"));
		assertEquals(2,graph.inDegree("B"));
		assertEquals(1,graph.inDegree("C"));
		assertEquals(0,graph.inDegree("D"));
		assertEquals(0,graph.inDegree("E"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
		
		graph.addEdgeEx("E","D",3);
		assertEquals(1,graph.inDegree("A"));
		assertEquals(2,graph.inDegree("B"));
		assertEquals(1,graph.inDegree("C"));
		assertEquals(1,graph.inDegree("D"));
		assertEquals(0,graph.inDegree("E"));
		
		try {
			graph.inDegree("F");
		}catch(IllegalArgumentException ex) {
			assertEquals(GraphEx2023.ERROR_THE_NODE_IS_NOT_IN_THE_GRAPH, ex.getMessage());
		}
	}

}
