package p2Grafos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GraphEx2023IsBalancedTest {

	@Test
	void isBalancedTest() {
		GraphEx2023<String> graph = new GraphEx2023<>(5);
		graph.addNodeEx("A");
		
		assertEquals(true,graph.isBalanced());
		
		
		graph.addNodeEx("B");
		assertEquals(true,graph.isBalanced());
		
		graph.addEdgeEx("A","B",1);
		assertEquals(false,graph.isBalanced());
		
		graph.addNodeEx("C");
		assertEquals(false,graph.isBalanced());
		
		graph.addEdgeEx("B","A",3);
		assertEquals(true,graph.isBalanced());
		
		graph.addNodeEx("D");
		assertEquals(true,graph.isBalanced());
		
		graph.addEdgeEx("D","B",2);
		assertEquals(false,graph.isBalanced());
		
		graph.addNodeEx("E");
		assertEquals(false,graph.isBalanced());
		
		graph.addEdgeEx("E","C",6);
		assertEquals(false,graph.isBalanced());
		
		graph.addEdgeEx("E","D",3);
		assertEquals(false,graph.isBalanced());
		
		graph.addEdgeEx("B","E",100);
		assertEquals(false,graph.isBalanced());
		
		graph.addEdgeEx("C","E",0.3);
		assertEquals(true,graph.isBalanced());
	}

}
