
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AVLTreeJoinsTest {

//	@Test
//	void test() {
//		AVLTree<Character> a = new AVLTree<Character>();
//		a.add('b');
//		a.add('a');
//		a.add('d');
//
//		AVLTree<Character> b = new AVLTree<Character>();
//		b.add('c');
//		b.add('g');
//		b.add('i');
//		b.add('d');
//
//		assertEquals("b(3)a(0)--d(2)c(0)--g(1)-i(0)--", a.joins(b).toString());
//	}
//	
	@Test
	void joinsCharacterTree() {
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

		assertEquals("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", a.joins(b).toString());
		assertEquals("c(0)b(-1)a(0)---g(0)d(0)--i(0)--", b.joins(a).toString());
	}

	@Test
	void joinsIntegerTree() {
		AVLTree<Integer> a = new AVLTree<Integer>();
		a.add(1);
		a.add(2);
		a.add(4);
		assertEquals("2(0)1(0)--4(0)--", a.toString());

		AVLTree<Integer> b = new AVLTree<Integer>();
		b.add(3);
		b.add(7);
		b.add(9);
		b.add(4);

		assertEquals("7(-1)3(1)-4(0)--9(0)--", b.toString());

		assertEquals("4(0)2(0)1(0)--3(0)--7(1)-9(0)--", a.joins(b).toString());
		assertEquals("3(0)2(-1)1(0)---7(0)4(0)--9(0)--", b.joins(a).toString());
	}

}
