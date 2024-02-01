package p2Grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class L6B_Exercises_sampleTest {

	@Test
	public void Test() {
		 Graph<String> g = new Graph<String>(6);
		    
		 System.out.println ("TEST BEGINS  ***");
		 assertEquals(0, g.getSize());
		    
		 try
		 {
			 g.addNode("V1");
			 g.addNode("V2");
			 g.addNode("V3");
			 g.addNode("V4");
			 g.addNode("V5");
			 g.addNode("V6");
		 }
		 catch (Exception e)
		 {
			 System.out.println (e.getMessage());
		 }
		    
		 assertEquals(6,  g.getSize());
		 assertEquals(0,  g.getNode("V1"));
		 assertEquals(1,  g.getNode("V2"));
		 assertEquals(2,  g.getNode("V3"));
		 assertEquals(3,  g.getNode("V4"));
		 assertEquals(4,  g.getNode("V5"));
		 assertEquals(5,  g.getNode("V6"));
		 assertArrayEquals (new boolean[][]{
			 {false, false, false, false, false, false}, 
			 {false, false, false, false, false, false}, 
			 {false, false, false, false, false, false}, 
			 {false, false, false, false, false, false}, 
			 {false, false, false, false, false, false}, 
			 {false, false, false, false, false, false}}, g.getEdges());
		 assertArrayEquals (new double[][]{
			 {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, 
			 {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}}, g.getWeight());
		 g.print();
		 
		
		 try
		 {
			 g.addEdge ("V1", "V2", 3.0);
			 g.addEdge ("V1", "V3", 4.0);
			 g.addEdge ("V1", "V5", 8.0);
			 
			 g.addEdge ("V2", "V5", 5.0);
						 
			 g.addEdge ("V3", "V5", 3.0);
			 
			 g.addEdge ("V5", "V6", 3.0);
			 g.addEdge ("V5", "V4", 7.0);
			 
			 g.addEdge ("V6", "V4", 2.0);
		 }
		 catch (Exception e)
		 {
			 System.out.println (e.getMessage());
		 }
		
		 assertArrayEquals (new boolean[][]
				 {	{false, true, true, false, true, false}, 
			 		{false, false, false, false, true, false}, 
			 		{false, false, false, false, true, false}, 
			 		{false, false, false, false, false, false}, 
			 		{false, false, false, true, false, true}, 
			 		{false, false, false, true, false, false}}, g.getEdges());
		 
		 assertArrayEquals (new double[][]{
			 	{0.0, 3.0, 4.0, 0.0, 8.0, 0.0}, 
			 	{0.0, 0.0, 0.0, 0.0, 5.0, 0.0}, 
			 	{0.0, 0.0, 0.0, 0.0, 3.0, 0.0}, 
			 	{0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, 
			 	{0.0, 0.0, 0.0, 7.0, 0.0, 3.0}, 
			 	{0.0, 0.0, 0.0, 2.0, 0.0, 0.0}}, g.getWeight());
		
	
		 
		 assertEquals("V1-V2-V3-V5-V4-V6-", g.BFPrint("V1"));
		 assertEquals("V2-V5-V4-V6-"      , g.BFPrint("V2"));
		 assertEquals("V3-V5-V4-V6-"	  , g.BFPrint("V3"));
		 assertEquals("V4-"				  , g.BFPrint("V4"));
		 assertEquals("V5-V4-V6-"		  , g.BFPrint("V5"));
		 assertEquals("V6-V4-"		      , g.BFPrint("V6"));
		 		 
		 assertEquals("V4", g.getCenter());

		 assertEquals (1, g.shortestPathLength("V1", "V2"));
		 assertEquals (1, g.shortestPathLength("V1", "V3"));
		 assertEquals (2, g.shortestPathLength("V1", "V4"));
		 assertEquals (1, g.shortestPathLength("V1", "V5"));
		 assertEquals (2, g.shortestPathLength("V1", "V6"));
		
		
		 
		 try
		 {

			 g.removeNode ("V4");
		 }
		 catch (Exception e)
		 {
			 System.out.println (e.getMessage());
		 }
		 
		 
		 assertEquals("V1-V2-V3-V5-V6-", g.BFPrint("V1"));
		 assertEquals("V2-V5-V6-"      , g.BFPrint("V2"));
		 assertEquals("V3-V5-V6-"	  , g.BFPrint("V3"));

		 assertEquals("V5-V6-"		  , g.BFPrint("V5"));
		 assertEquals("V6-"		      , g.BFPrint("V6"));
		 		 
		 assertEquals("V6", g.getCenter());

		 assertEquals (1, g.shortestPathLength("V1", "V2"));
		 assertEquals (1, g.shortestPathLength("V1", "V3"));
		 assertEquals (1, g.shortestPathLength("V1", "V5"));
		 assertEquals (2, g.shortestPathLength("V1", "V6"));
		 
		 
		
		

	}

}
