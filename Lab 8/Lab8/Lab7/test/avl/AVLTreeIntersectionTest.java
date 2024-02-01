package avl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AVLTreeIntersectionTest {

	@Test
	void test() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');

		AVLTree<Character> b = new AVLTree<Character>();
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');

		assertEquals("d(0)--", a.intersection(b).toString());
	}

}
