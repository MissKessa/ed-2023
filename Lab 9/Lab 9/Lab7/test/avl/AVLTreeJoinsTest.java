package avl;

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
	void fullTest() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('a');
		a.add('b');
		a.add('d');
		assertEquals("b(0)a(0)--d(0)--",a.toString());
		
		AVLTree<Character> b = new AVLTree<Character>();
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');
		
		assertEquals("g(-1)c(1)-d(0)--i(0)--",b.toString());
		assertEquals("d(0)b(0)a(0)--c(0)--g(1)-i(0)--",a.joins(b).toString());
	}

}
