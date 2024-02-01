package graphs;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import graphs.ElementNotPresentException;
import graphs.FullStructureException;

public class BasicGraphTest {
	public final static int INDEX_NOT_FOUND = -1;
	public static final double DELTA = 0.0001;

	Graph<Character> g1;

	@Test
	public void testConsultAdd() {
		g1 = new Graph<Character>(3);

		assertEquals(0, g1.getSize());

		assertTrue(g1.addNode('a'));

		assertEquals(1, g1.getSize());
		assertEquals(0, g1.getNode('a'));
		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		// Test nodes for nodes not found
		assertEquals(INDEX_NOT_FOUND, g1.getNode('b'));

		assertFalse(g1.addNode('a'));

		assertTrue(g1.addNode('b'));
		assertTrue(g1.addNode('c'));

		assertEquals(3, g1.getSize());
		assertEquals(0, g1.getNode('a'));
		assertEquals(1, g1.getNode('b'));
		assertEquals(2, g1.getNode('c'));

		try {
			boolean res = g1.addNode('b');
			if (res)
				fail();
		} catch (FullStructureException ex) {
			ex.printStackTrace();
		}

		try {
			g1.addNode('X');
			fail();
		} catch (FullStructureException ex) {
			ex.printStackTrace();
			assertNotNull(ex.getMessage());
			assertNotEquals(ex.getMessage(), "");
		}

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		// Testing edges
		assertFalse(g1.existsEdge('b', 'd'));
		assertFalse(g1.existsEdge('b', 'c'));
		assertFalse(g1.existsEdge('X', 'Y'));
		assertFalse(g1.existsEdge('b', 'Y'));
		assertFalse(g1.existsEdge('X', 'c'));

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		try {
			g1.addEdge('b', 'c', -5.0);
			fail();
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			assertNotNull(ex.getMessage());
			assertNotEquals(ex.getMessage(), "");
		}
		try {
			g1.addEdge('b', 'c', 0);
			fail();
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}

		try {
			g1.addEdge('X', 'c', 2);
			fail();
		} catch (ElementNotPresentException ex) {
			ex.printStackTrace();
		}

		try {
			g1.addEdge('b', 'Y', 2);
			fail();
		} catch (ElementNotPresentException ex) {
			ex.printStackTrace();
			assertNotNull(ex.getMessage());
			assertNotEquals(ex.getMessage(), "");
		}

		assertTrue(g1.addEdge('b', 'c', 5.0));

		assertFalse(g1.addEdge('b', 'c', 3.0));

		assertTrue(g1.existsEdge('b', 'c'));

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, true }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		assertTrue(g1.addEdge('a', 'b', 2.0));
		assertTrue(g1.existsEdge('a', 'b'));

		assertArrayEquals(new boolean[][] { { false, true, false }, { false, false, true }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 2.0, 0.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		assertTrue(g1.addEdge('a', 'c', 1.0));
		assertTrue(g1.existsEdge('a', 'c'));

		assertArrayEquals(new boolean[][] { { false, true, true }, { false, false, true }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 2.0, 1.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		assertTrue(g1.addEdge('c', 'a', 3.0));
		assertTrue(g1.existsEdge('a', 'c'));

		assertTrue(g1.existsEdge('c', 'a'));

		assertFalse(g1.existsEdge('c', 'c'));

		assertArrayEquals(new boolean[][] { { false, true, true }, { false, false, true }, { true, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 2.0, 1.0 }, { 0.0, 0.0, 5.0 }, { 3.0, 0.0, 0.0 } }, g1.getWeight());
	}
	
	@Test
	public void testRemove() {
		//Previous test must be passed before starting with the remove methods
		testConsultAdd();
		
		assertFalse(g1.removeNode('X'));
		assertTrue(g1.removeNode('a'));
		assertFalse(g1.removeNode('a'));
		assertEquals(2, g1.getSize());

		assertFalse(g1.existsEdge('a', 'c'));

		assertFalse(g1.existsEdge('c', 'a'));
		assertEquals(5.0, g1.getEdge('b', 'c'), DELTA);
		assertTrue(g1.existsEdge('b', 'c'));

		assertArrayEquals(new boolean[][] { { false, false, true }, { true, false, true }, { true, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 1.0 }, { 5.0, 0.0, 5.0 }, { 3.0, 0.0, 0.0 } }, g1.getWeight());

		assertTrue(g1.removeEdge('b', 'c'));

		assertEquals(INDEX_NOT_FOUND, g1.getEdge('b', 'c'), DELTA);
		assertFalse(g1.existsEdge('b', 'c'));

		assertFalse(g1.removeEdge('b', 'c'));

		try {
			g1.removeEdge('b', 'Y');
			fail();
		} catch (ElementNotPresentException ex) {
			ex.printStackTrace();
		}

		try {
			g1.removeEdge('X', 'Y');
			fail();
		} catch (ElementNotPresentException ex) {
			ex.printStackTrace();
			assertNotNull(ex.getMessage());
			assertNotEquals(ex.getMessage(), "");
		}

		assertTrue(g1.removeNode('b'));
		assertEquals(1, g1.getSize());

		assertArrayEquals(new boolean[][] { { false, false, true }, { false, false, true }, { true, false, false } },
				g1.getEdges());

		assertTrue(g1.removeNode('c'));

		assertEquals(false, g1.removeNode('c'));
		assertEquals(0, g1.getSize());
		assertArrayEquals(new boolean[][] { { false, false, true }, { false, false, true }, { true, false, false } },
				g1.getEdges());

		assertTrue(g1.addNode('a'));

		assertTrue(g1.addNode('b'));

		assertTrue(g1.addNode('c'));
		assertEquals(3, g1.getSize());
		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
	}
}
