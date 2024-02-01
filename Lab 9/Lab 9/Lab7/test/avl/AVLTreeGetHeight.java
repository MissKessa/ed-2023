package avl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AVLTreeGetHeight {

	@Test
	void test() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals(3, a.getHeight());
	}

}
