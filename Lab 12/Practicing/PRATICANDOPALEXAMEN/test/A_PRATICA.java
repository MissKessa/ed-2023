import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class A_PRATICA {

	@Test
	void balanceFactorMean() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		assertEquals(0, a.balanceFactorMean(), 0.1);

		a.add('a');
		assertEquals(-0.5, a.balanceFactorMean(), 0.1);

		a.add('d');
		assertEquals(0, a.balanceFactorMean(), 0.1);

		a.add('c');
		assertEquals(0, a.balanceFactorMean(), 0.1);

		a.add('g');
		assertEquals(0.2, a.balanceFactorMean(), 0.1);

		a.add('i');
		assertEquals(0.166, a.balanceFactorMean(), 0.1);

		a.add('h');

		assertEquals(0, a.balanceFactorMean());
	}

	@Test
	void getBrother() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');

		assertEquals('b', a.getBrother('h'));
		assertEquals('h', a.getBrother('b'));

		assertEquals('a', a.getBrother('c'));
		assertEquals('c', a.getBrother('a'));

		assertEquals('g', a.getBrother('i'));
		assertEquals('i', a.getBrother('g'));
	}

	@Test
	void getElementsInLevel() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');

		List<Character> elements = new ArrayList<Character>();
		elements.add('d');
		assertEquals(elements.toString(), a.getElementsInLevel(1).toString());

		elements = new ArrayList<Character>();
		elements.add('b');
		elements.add('h');
		assertEquals(elements.toString(), a.getElementsInLevel(2).toString());

		elements = new ArrayList<Character>();
		elements.add('a');
		elements.add('c');
		elements.add('g');
		elements.add('i');
		assertEquals(elements.toString(), a.getElementsInLevel(3).toString());

	}

	@Test
	void getAncestorsAVL() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');

		List<Character> elements = new ArrayList<Character>();
		assertEquals(elements.toString(), a.getAncestors('d').toString());

		elements = new ArrayList<Character>();
		elements.add('d');
		assertEquals(elements.toString(), a.getAncestors('b').toString());
		assertEquals(elements.toString(), a.getAncestors('h').toString());

		elements = new ArrayList<Character>();
		elements.add('d');
		elements.add('b');
		assertEquals(elements.toString(), a.getAncestors('a').toString());
		assertEquals(elements.toString(), a.getAncestors('c').toString());

		elements = new ArrayList<Character>();
		elements.add('d');
		elements.add('h');
		assertEquals(elements.toString(), a.getAncestors('g').toString());
		assertEquals(elements.toString(), a.getAncestors('i').toString());

	}

	@Test
	void getHijosAVL() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');

		List<Character> elements = new ArrayList<Character>();
		elements.add('a');
		elements.add('b');
		elements.add('c');
		elements.add('g');
		elements.add('h');
		elements.add('i');
		assertEquals(elements.toString(), a.hijos('d').toString());

		elements = new ArrayList<Character>();
		elements.add('a');
		elements.add('c');
		assertEquals(elements.toString(), a.hijos('b').toString());

		elements = new ArrayList<Character>();
		elements.add('g');
		elements.add('i');
		assertEquals(elements.toString(), a.hijos('h').toString());

		elements = new ArrayList<Character>();
		assertEquals(elements.toString(), a.hijos('a').toString());
		assertEquals(elements.toString(), a.hijos('c').toString());
		assertEquals(elements.toString(), a.hijos('g').toString());
		assertEquals(elements.toString(), a.hijos('i').toString());

	}

	@Test
	void intermediateNodesAVL() {
		AVLTree<Character> a = new AVLTree<Character>();
		assertEquals(0, a.intermediateNodesPercentage(), 0.1);

		a.add('b');
		assertEquals(0, a.intermediateNodesPercentage(), 0.1);

		a.add('a');
		assertEquals(0, a.intermediateNodesPercentage(), 0.1);

		a.add('d');
		assertEquals(0, a.intermediateNodesPercentage(), 0.1);

		a.add('c');
		assertEquals(0.25, a.intermediateNodesPercentage(), 0.1);

		a.add('g');
		assertEquals(0.25, a.intermediateNodesPercentage(), 0.1);

		a.add('i');
		assertEquals(0.33, a.intermediateNodesPercentage(), 0.1);

		a.add('h');
		assertEquals(0.29, a.intermediateNodesPercentage(), 0.1);

	}

	@Test
	void getAncestorsHeap() {
		BinaryHeap<Integer> a = new BinaryHeap<Integer>();
		a.add(10);
		a.add(9);
		a.add(8);
		a.add(7);
		a.add(6);
		a.add(5);
		a.add(4);
		// assertTrue(a.toString().equals("[4, 7, 5, 10, 8, 9, 6]"));

		List<Integer> elements = new ArrayList<Integer>();
		assertEquals(elements.toString(), a.getAncestors(4).toString());

		elements = new ArrayList<Integer>();
		elements.add(4);
		assertEquals(elements.toString(), a.getAncestors(7).toString());
		assertEquals(elements.toString(), a.getAncestors(5).toString());

		elements = new ArrayList<Integer>();
		elements.add(4);
		elements.add(7);
		assertEquals(elements.toString(), a.getAncestors(10).toString());
		assertEquals(elements.toString(), a.getAncestors(8).toString());

		elements = new ArrayList<Integer>();
		elements.add(4);
		elements.add(5);
		assertEquals(elements.toString(), a.getAncestors(9).toString());
		assertEquals(elements.toString(), a.getAncestors(6).toString());
	}

	@Test
	void getHijosHeap() {
		BinaryHeap<Integer> a = new BinaryHeap<Integer>();
		a.add(10);
		a.add(9);
		a.add(8);
		a.add(7);
		a.add(6);
		a.add(5);
		a.add(4);
		// assertTrue(a.toString().equals("[4, 7, 5, 10, 8, 9, 6]"));

		List<Integer> elements = new ArrayList<Integer>();
		elements.add(5);
		elements.add(6);
		elements.add(7);
		elements.add(8);
		elements.add(9);
		elements.add(10);

		assertEquals(elements.toString(), a.hijos(4).toString());

		elements = new ArrayList<Integer>();
		elements.add(8);
		elements.add(10);
		assertEquals(elements.toString(), a.hijos(7).toString());

		elements = new ArrayList<Integer>();
		elements.add(6);
		elements.add(9);
		assertEquals(elements.toString(), a.hijos(5).toString());

		elements = new ArrayList<Integer>();
		assertEquals(elements.toString(), a.hijos(10).toString());
		assertEquals(elements.toString(), a.hijos(8).toString());
		assertEquals(elements.toString(), a.hijos(9).toString());
		assertEquals(elements.toString(), a.hijos(6).toString());
	}

	@Test
	void intermediateNodesHeap() {
		BinaryHeap<Integer> a = new BinaryHeap<Integer>();
		assertEquals(0, a.intermediateNodesPercentage(), 0.1);

		a.add(10);
		assertEquals(0, a.intermediateNodesPercentage(), 0.1);

		a.add(9);
		assertEquals(0, a.intermediateNodesPercentage(), 0.1);

		a.add(8);
		assertEquals(0, a.intermediateNodesPercentage(), 0.1);

		a.add(7);
		assertEquals(0.25, a.intermediateNodesPercentage(), 0.1);

		a.add(6);
		assertEquals(0.2, a.intermediateNodesPercentage(), 0.1);

		a.add(5);
		assertEquals(0.4, a.intermediateNodesPercentage(), 0.1);

		a.add(4);
		assertEquals(0.29, a.intermediateNodesPercentage(), 0.1);

	}

	@Test
	void DFTraversalHeap() {
		BinaryHeap<Integer> a = new BinaryHeap<Integer>();
		a.add(10);
		a.add(9);
		a.add(8);
		a.add(7);
		a.add(6);
		a.add(5);
		a.add(4);
		// assertTrue(a.toString().equals("[4, 7, 5, 10, 8, 9, 6]"));

		List<Integer> elements = new ArrayList<Integer>();
		elements.add(10);
		elements.add(7);
		elements.add(8);
		elements.add(4);
		elements.add(9);
		elements.add(5);
		elements.add(6);
		assertEquals(elements.toString(), a.DFTraversal().toString());
	}

	@Test
	void collisionsPerOperations() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4); // collisions=0;
		assertEquals(0, a.getNumberCollisions());

		assertEquals(0, a.collisionsPerOperations(), 0.1);

		a.add(13); // collisions=0;
		assertEquals(0, a.getNumberCollisions());

		assertEquals(0, a.collisionsPerOperations(), 0.1);

		a.add(24); // collisions=1;
		assertEquals(1, a.getNumberCollisions());

		assertEquals(0.33, a.collisionsPerOperations(), 0.1);

		a.add(3); // collisions=3;
		assertEquals(3, a.getNumberCollisions());

		// assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 -
		// [4] (1) = 4 - ", a.toString());

		assertEquals(1, a.collisionsPerOperations(), 0.1);

		a.search(3);
		assertEquals(3, a.getNumberCollisions());
		assertEquals(1.4, a.collisionsPerOperations(), 0.1);

		a.remove(24);
		assertEquals(1, a.getNumberCollisions());
		assertEquals(1.33, a.collisionsPerOperations(), 0.1);
	}

	@Test
	void reverseResizing() {
		HashTable<Integer> a = new HashTable<Integer>(7, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		assertEquals(
				"[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (1) = 13 - ",
				a.toString());
		a.reverseResizing(0.5);

		assertEquals("[0] (0) = null - [1] (1) = 4 - [2] (1) = 13 - ", a.toString());
	}

	@Test
	void getCollisionProbabilityAdd() {
		HashTable<Integer> a = new HashTable<Integer>(7, HashTable.LINEAR_PROBING, 1.0);
		a.add(4); // 4
		assertEquals(0, a.getCollisionProbabilityAdd(), 0.1);

		a.add(11); // 4 > 5
		assertEquals(0.5, a.getCollisionProbabilityAdd(), 0.1);

		a.add(12); // 5 > 6
		assertEquals(0.67, a.getCollisionProbabilityAdd(), 0.1);
		assertEquals(
				"[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (1) = 11 - [6] (1) = 12 - ",
				a.toString());

		a.add(6); // 4,5,6 > 0
		assertEquals(0, a.getCollisionProbabilityAdd(), 0.1);
		assertEquals(
				"[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (1) = 6 - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - [11] (1) = 11 - [12] (1) = 12 - [13] (0) = null - [14] (0) = null - [15] (0) = null - [16] (0) = null - ",
				a.toString());
	}

}
