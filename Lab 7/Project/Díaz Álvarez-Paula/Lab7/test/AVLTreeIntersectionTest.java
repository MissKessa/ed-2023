
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AVLTreeIntersectionTest {

	@Test
	void intersectionCharacterTree() {
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
		assertEquals("d(0)--", b.intersection(a).toString());
	}

	@Test
	void intersectionIntegerTree() {
		AVLTree<Integer> a = new AVLTree<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);

		AVLTree<Integer> b = new AVLTree<Integer>();
		b.add(4);
		b.add(3);
		b.add(2);
		b.add(1);

		assertEquals("2(0)1(0)--3(0)--", a.intersection(b).toString());
		assertEquals("2(0)1(0)--3(0)--", b.intersection(a).toString());
	}

}
