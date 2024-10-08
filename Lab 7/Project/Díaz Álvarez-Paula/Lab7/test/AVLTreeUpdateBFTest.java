
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AVLTreeUpdateBFTest {

	@Test
	void updateBFSingleRotation() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('a');
		a.add('b');
		a.add('c');
		assertEquals("b(0)a(0)--c(0)--", a.toString());
		a.add('d');
		a.add('e');
		assertEquals("b(1)a(0)--d(0)c(0)--e(0)--", a.toString());

		a.add('f');
		assertEquals("d(0)b(0)a(0)--c(0)--e(1)-f(0)--", a.toString());
	}

	@Test
	void updateBFDoubleRotation() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('e');
		a.add('g');
		a.add('b');
		a.add('d');
		a.add('c');
		assertEquals("e(-1)c(0)b(0)--d(0)--g(0)--", a.toString());
	}

	@Test
	void updateBFFullTest() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('a');
		a.add('b');
		a.add('d');
		assertEquals("b(0)a(0)--d(0)--", a.toString());

		AVLTree<Character> b = new AVLTree<Character>();
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');

		assertEquals("g(-1)c(1)-d(0)--i(0)--", b.toString());
	}

}
