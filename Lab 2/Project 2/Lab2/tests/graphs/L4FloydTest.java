package graphs;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class L4FloydTest {

	@Test
	public void TestBasic() throws Exception {
		Graph<Character> g1 = new Graph<Character>(3);

		System.out.println("TEST BASIC BEGINS ***");
		assertEquals(0, g1.getSize());

		assertTrue(g1.addNode('a'));
		assertFalse(g1.addNode('a'));

		assertEquals(1, g1.getSize());
		assertEquals(0, g1.getNode('a'));
		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		// Test nodes for nodes not found
		assertEquals(Graph.INDEX_NOT_FOUND, g1.getNode('b'));

		g1.addNode('b');
		g1.addNode('c');

		assertEquals(3, g1.getSize());
		assertEquals(0, g1.getNode('a'));
		assertEquals(1, g1.getNode('b'));
		assertEquals(2, g1.getNode('c'));

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		assertFalse(g1.existsEdge('b', 'd'));
		assertFalse(g1.existsEdge('b', 'c'));

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		g1.addEdge('b', 'c', 5.0);
		assertTrue(g1.existsEdge('b', 'c'));

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, true }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		assertFalse(g1.removeNode('e'));

		ElementNotPresentException ex = assertThrows(ElementNotPresentException.class, () -> g1.removeEdge('X', 'A'));

		assertNotEquals(null, ex.getMessage());
		assertNotEquals(0, ex.getMessage().length());
	}

	@Test
	public void testDFPrint() throws Exception {
		System.out.println("TEST DEPTH FIRST BEGINS ***");

		Graph<String> g1 = new Graph<String>(4);
		g1.addNode("A");
		g1.addNode("B");
		g1.addNode("C");
		g1.addNode("D");

		RuntimeException ex = assertThrows(FullStructureException.class, () -> g1.addNode("a"));

		assertNotEquals(null, ex.getMessage());
		assertNotEquals(0, ex.getMessage().length());

		assertEquals(g1.traverseGraphDF("A"), "A-");

		g1.addEdge("B", "A", 0.2);

		assertEquals(g1.traverseGraphDF("B"), "B-A-");

		g1.addEdge("A", "C", 0.2);

		assertEquals(g1.traverseGraphDF("B"), "B-A-C-");

		g1.addEdge("B", "C", 1);
		g1.addEdge("D", "B", 3);
		g1.addEdge("D", "C", 5);

		assertEquals(g1.traverseGraphDF("A"), "A-C-");
		assertEquals(g1.traverseGraphDF("C"), "C-");
		assertEquals(g1.traverseGraphDF("D"), "D-B-A-C-");
		assertEquals(g1.traverseGraphDF("B"), "B-A-C-");
		assertEquals(g1.traverseGraphDF("X"), null);

		ex = assertThrows(NullPointerException.class, () -> g1.traverseGraphDF(null));

		assertNotEquals(null, ex.getMessage());
		assertNotEquals(0, ex.getMessage().length());
	}

	@Test
	public void TestFloydA() throws Exception {
		Graph<String> g = new Graph<String>(6);

		System.out.println("TEST FLOYD A BEGINS ***");
		assertEquals(0, g.getSize());

		assertTrue(g.addNode("V1"));
		assertTrue(g.addNode("V2"));
		assertTrue(g.addNode("V3"));
		assertTrue(g.addNode("V4"));
		assertTrue(g.addNode("V5"));
		assertTrue(g.addNode("V6"));

		assertEquals(6, g.getSize());
		assertEquals(0, g.getNode("V1"));
		assertEquals(1, g.getNode("V2"));
		assertEquals(2, g.getNode("V3"));
		assertEquals(3, g.getNode("V4"));
		assertEquals(4, g.getNode("V5"));
		assertEquals(5, g.getNode("V6"));
		assertArrayEquals(new boolean[][] { { false, false, false, false, false, false },
				{ false, false, false, false, false, false }, { false, false, false, false, false, false },
				{ false, false, false, false, false, false }, { false, false, false, false, false, false },
				{ false, false, false, false, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, g.getWeight());

		assertTrue(g.addEdge("V1", "V2", 3.0));
		assertTrue(g.addEdge("V1", "V3", 4.0));
		assertTrue(g.addEdge("V1", "V5", 8.0));

		assertTrue(g.addEdge("V2", "V5", 5.0));

		assertTrue(g.addEdge("V3", "V5", 3.0));

		assertTrue(g.addEdge("V5", "V6", 3.0));
		assertTrue(g.addEdge("V5", "V4", 7.0));

		assertTrue(g.addEdge("V6", "V4", 2.0));

		assertArrayEquals(new boolean[][] { { false, true, true, false, true, false },
				{ false, false, false, false, true, false }, { false, false, false, false, true, false },
				{ false, false, false, false, false, false }, { false, false, false, true, false, true },
				{ false, false, false, true, false, false } }, g.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 3.0, 4.0, 0.0, 8.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 5.0, 0.0 },
				{ 0.0, 0.0, 0.0, 0.0, 3.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 7.0, 0.0, 3.0 },
				{ 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 } }, g.getWeight());

		g.floyd(g.getSize());

		assertArrayEquals(new int[][] { { -1, -1, -1, 5, 2, 4 }, { -1, -1, -1, 5, -1, 4 }, { -1, -1, -1, 5, -1, 4 },
				{ -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, 5, -1, -1 }, { -1, -1, -1, -1, -1, -1 } }, g.getP());
		assertArrayEquals(new double[][] { { 00.0, 03.0, 04.0, 12.0, 07.0, 10.0 },
				{ Graph.INFINITE, 00.0, Graph.INFINITE, 10.0, 05.0, 08.0 },
				{ Graph.INFINITE, Graph.INFINITE, 00.0, 08.0, 03.0, 06.0 },
				{ Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 00.0, Graph.INFINITE, Graph.INFINITE },
				{ Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 05.0, 00.0, 03.0 },
				{ Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 02.0, Graph.INFINITE, 00.0 } }, g.getA());

	}

	private Graph<String> createCountryGraph() {
		Graph<String> g = new Graph<String>(6);

		assertEquals(0, g.getSize());

		assertTrue(g.addNode("Spain"));
		assertTrue(g.addNode("Venezuela"));
		assertTrue(g.addNode("UK"));
		assertTrue(g.addNode("Poland"));
		assertTrue(g.addNode("Greece"));
		assertTrue(g.addNode("Japan"));

		assertEquals(6, g.getSize());
		assertEquals(0, g.getNode("Spain"));
		assertEquals(1, g.getNode("Venezuela"));
		assertEquals(2, g.getNode("UK"));
		assertEquals(3, g.getNode("Poland"));
		assertEquals(4, g.getNode("Greece"));
		assertEquals(5, g.getNode("Japan"));

		assertArrayEquals(new boolean[][] { { false, false, false, false, false, false },
				{ false, false, false, false, false, false }, { false, false, false, false, false, false },
				{ false, false, false, false, false, false }, { false, false, false, false, false, false },
				{ false, false, false, false, false, false } }, g.getEdges());

		assertArrayEquals(
				new double[][] { { 00.0, 00.0, 00.0, 00.0, 00.0, 00.0 }, { 00.0, 00.0, 00.0, 00.0, 00.0, 00.0 },
						{ 00.0, 00.0, 00.0, 00.0, 00.0, 00.0 }, { 00.0, 00.0, 00.0, 00.0, 00.0, 00.0 },
						{ 00.0, 00.0, 00.0, 00.0, 00.0, 00.0 }, { 00.0, 00.0, 00.0, 00.0, 00.0, 00.0 } },
				g.getWeight());

		// Test nodes for nodes not found
		assertEquals(Graph.INDEX_NOT_FOUND, g.getNode("Ecuador"));

		// No repeated nodes allowed

		FullStructureException ex = assertThrows(FullStructureException.class, () -> g.addNode("Venezuela"));

		assertNotEquals(null, ex.getMessage());
		assertNotEquals(0, ex.getMessage().length());
		// Testing edges

		assertFalse(g.existsEdge("Venezuela", "Ecuador"));

		assertFalse(g.existsEdge("Greece", "Spain"));

		assertTrue(g.addEdge("Spain", "Venezuela", 3.0));
		assertTrue(g.addEdge("Spain", "Greece", 2.0));
		assertTrue(g.addEdge("Venezuela", "Poland", 2.0));
		assertTrue(g.addEdge("Greece", "UK", 1.0));
		assertTrue(g.addEdge("UK", "Poland", 4.0));
		assertTrue(g.addEdge("Poland", "Spain", 1.0));
		assertTrue(g.addEdge("Poland", "Greece", 3.0));
		assertTrue(g.addEdge("Poland", "Japan", 1.0));
		assertTrue(g.addEdge("Japan", "Spain", 1.0));
		assertTrue(g.addEdge("Japan", "Poland", 2.0));

		assertArrayEquals(new boolean[][] { { false, true, false, false, true, false },
				{ false, false, false, true, false, false }, { false, false, false, true, false, false },
				{ true, false, false, false, true, true }, { false, false, true, false, false, false },
				{ true, false, false, true, false, false } }, g.getEdges());

		assertArrayEquals(
				new double[][] { { 00.0, 03.0, 00.0, 00.0, 02.0, 00.0 }, { 00.0, 00.0, 00.0, 02.0, 00.0, 00.0 },
						{ 00.0, 00.0, 00.0, 04.0, 00.0, 00.0 }, { 01.0, 00.0, 00.0, 00.0, 03.0, 01.0 },
						{ 00.0, 00.0, 01.0, 00.0, 00.0, 00.0 }, { 01.0, 00.0, 00.0, 02.0, 00.0, 00.0 } },
				g.getWeight());

		return g;
	}

	@Test
	public void TestFloydB() throws Exception {

		System.out.println("TEST FLOYD B BEGINS ***");
		Graph<String> g = createCountryGraph();

		g.floyd(g.getSize());

		assertArrayEquals(
				new double[][] { { 00.0, 03.0, 03.0, 05.0, 02.0, 06.0 }, { 03.0, 00.0, 06.0, 02.0, 05.0, 03.0 },
						{ 05.0, 08.0, 00.0, 04.0, 07.0, 05.0 }, { 01.0, 04.0, 04.0, 00.0, 03.0, 01.0 },
						{ 06.0, 09.0, 01.0, 05.0, 00.0, 06.0 }, { 01.0, 04.0, 04.0, 02.0, 03.0, 00.0 } },
				g.getA());

		assertArrayEquals(new int[][] { { -1, -1, 4, 1, -1, 3 }, { 3, -1, 4, -1, 3, 3 }, { 3, 3, -1, -1, 3, 3 },
				{ -1, 0, 4, -1, -1, -1 }, { 3, 3, -1, 2, -1, 3 }, { -1, 0, 4, -1, 0, -1 } }, g.getP());

		assertTrue(g.removeNode("Greece"));

		assertArrayEquals(new boolean[][] { { false, true, false, false, false, false },
				{ false, false, false, true, false, false }, { false, false, false, true, false, false },
				{ true, false, false, false, true, true }, { true, false, false, true, false, false },
				{ true, false, false, true, false, false } }, g.getEdges());

		assertArrayEquals(
				new double[][] { { 00.0, 03.0, 00.0, 00.0, 00.0, 00.0 }, { 00.0, 00.0, 00.0, 02.0, 00.0, 00.0 },
						{ 00.0, 00.0, 00.0, 04.0, 00.0, 00.0 }, { 01.0, 00.0, 00.0, 00.0, 01.0, 01.0 },
						{ 01.0, 00.0, 00.0, 02.0, 00.0, 00.0 }, { 01.0, 00.0, 00.0, 02.0, 00.0, 00.0 } },
				g.getWeight());

		g.floyd(g.getSize());

		assertArrayEquals(
				new double[][] { { 00.0, 03.0, Graph.INFINITE, 05.0, 06.0, 06.0 },
						{ 03.0, 00.0, Graph.INFINITE, 02.0, 03.0, 03.0 }, { 05.0, 08.0, 00.0, 04.0, 05.0, 05.0 },
						{ 01.0, 04.0, Graph.INFINITE, 00.0, 01.0, 01.0 },
						{ 01.0, 04.0, Graph.INFINITE, 02.0, 00.0, 06.0 }, { 01.0, 04.0, 04.0, 02.0, 03.0, 00.0 } },
				g.getA());

		assertArrayEquals(new int[][] { { -1, -1, -1, 1, 3, 3 }, { 3, -1, -1, -1, 3, 3 }, { 3, 3, -1, -1, 3, 3 },
				{ -1, 0, -1, -1, -1, -1 }, { -1, 0, -1, -1, -1, 3 }, { -1, 0, 4, -1, 0, -1 } }, g.getP());

		assertTrue(g.removeEdge("Poland", "Japan"));

		assertArrayEquals(new boolean[][] { { false, true, false, false, false, false },
				{ false, false, false, true, false, false }, { false, false, false, true, false, false },
				{ true, false, false, false, false, true }, { true, false, false, true, false, false },
				{ true, false, false, true, false, false } }, g.getEdges());

		assertArrayEquals(
				new double[][] { { 00.0, 03.0, 00.0, 00.0, 00.0, 00.0 }, { 00.0, 00.0, 00.0, 02.0, 00.0, 00.0 },
						{ 00.0, 00.0, 00.0, 04.0, 00.0, 00.0 }, { 01.0, 00.0, 00.0, 00.0, 00.0, 01.0 },
						{ 01.0, 00.0, 00.0, 02.0, 00.0, 00.0 }, { 01.0, 00.0, 00.0, 02.0, 00.0, 00.0 } },
				g.getWeight());

		g.floyd(g.getSize());

		assertArrayEquals(
				new double[][] { { 00.0, 03.0, Graph.INFINITE, 05.0, Graph.INFINITE, 06.0 },
						{ 03.0, 00.0, Graph.INFINITE, 02.0, Graph.INFINITE, 03.0 },
						{ 05.0, 08.0, 00.0, 04.0, Graph.INFINITE, 05.0 },
						{ 01.0, 04.0, Graph.INFINITE, 00.0, Graph.INFINITE, 01.0 },
						{ 01.0, 04.0, Graph.INFINITE, 02.0, 00.0, 06.0 }, { 01.0, 04.0, 04.0, 02.0, 03.0, 00.0 } },
				g.getA());

		assertArrayEquals(new int[][] { { -1, -1, -1, 1, -1, 3 }, { 3, -1, -1, -1, -1, 3 }, { 3, 3, -1, -1, -1, 3 },
				{ -1, 0, -1, -1, -1, -1 }, { -1, 0, -1, -1, -1, 3 }, { -1, 0, 4, -1, 0, -1 } }, g.getP());
	}

	@Test
	public void testFloydPathA() {

		System.out.println("TEST PRINT FLOYD PATH A BEGINS ***");
		Graph<String> g = createCountryGraph();

		g.floyd(g.getSize());

		assertEquals("SpainVenezuelaPolandJapan", "Spain" + g.printFloydPath("Spain", "Japan") + "Japan");
		assertEquals("SpainGreeceUK", "Spain" + g.printFloydPath("Spain", "UK") + "UK");
		assertEquals("SpainVenezuelaPoland", "Spain" + g.printFloydPath("Spain", "Poland") + "Poland");
		assertEquals("PolandSpainVenezuela", "Poland" + g.printFloydPath("Poland", "Venezuela") + "Venezuela");

		assertTrue(g.removeNode("Greece"));

		g.floyd(g.getSize());

		assertEquals("SpainVenezuelaPolandJapan", "Spain" + g.printFloydPath("Spain", "Japan") + "Japan");
		assertEquals("Spain_NO_PATH_FOUND_TO_UK", "Spain" + g.printFloydPath("Spain", "UK") + "UK");
		assertEquals("SpainVenezuelaPoland", "Spain" + g.printFloydPath("Spain", "Poland") + "Poland");
		assertEquals("PolandSpainVenezuela", "Poland" + g.printFloydPath("Poland", "Venezuela") + "Venezuela");

		assertTrue(g.removeEdge("Poland", "Japan"));

		g.floyd(g.getSize());
		assertEquals("Spain_NO_PATH_FOUND_TO_Japan", "Spain" + g.printFloydPath("Spain", "Japan") + "Japan");
		assertEquals("Spain_NO_PATH_FOUND_TO_UK", "Spain" + g.printFloydPath("Spain", "UK") + "UK");
		assertEquals("SpainVenezuelaPoland", "Spain" + g.printFloydPath("Spain", "Poland") + "Poland");
		assertEquals("PolandSpainVenezuela", "Poland" + g.printFloydPath("Poland", "Venezuela") + "Venezuela");

	}

	@Test
	public void testFloydPathB() throws Exception {
		System.out.println("TEST PRINT FLOYD PATH B BEGINS ***");

		Graph<String> g1 = new Graph<String>(4);
		g1.addNode("A");
		g1.addNode("B");
		g1.addNode("C");
		g1.addNode("D");

		RuntimeException ex = assertThrows(FullStructureException.class, () -> g1.addNode("a"));

		assertNotEquals(null, ex.getMessage());
		assertNotEquals(0, ex.getMessage().length());

		g1.addEdge("B", "A", 0.2);
		g1.addEdge("A", "C", 0.2);
		g1.addEdge("B", "C", 1);
		g1.addEdge("D", "B", 3);
		g1.addEdge("D", "C", 5);

		g1.floyd(g1.getSize());

		assertEquals("DBAC", "D" + g1.printFloydPath("D", "C") + "C");
		assertEquals("BAC", "B" + g1.printFloydPath("B", "C") + "C");
		assertEquals("AC", "A" + g1.printFloydPath("A", "C") + "C");

		ex = assertThrows(ElementNotPresentException.class, () -> g1.printFloydPath("X", "A"));

		assertNotEquals(null, ex.getMessage());
		assertNotEquals(0, ex.getMessage().length());

		ex = assertThrows(ElementNotPresentException.class, () -> g1.printFloydPath("D", "YY"));

		assertNotEquals(null, ex.getMessage());
		assertNotEquals(0, ex.getMessage().length());

		ex = assertThrows(NullPointerException.class, () -> g1.printFloydPath(null, "YY"));

		assertNotEquals(null, ex.getMessage());
		assertNotEquals(0, ex.getMessage().length());
	}

}
