
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class AVLTreeToStringTest {

//	@Test
//	void testHeight() {
//		AVLTree<Character> a = new AVLTree<Character>();
//		a.add('b');
//		assertEquals("b(0)--",a.toString());
//		
//		a.add('a');
//		assertEquals("b(1)a(0)---",a.toString());
//		
//		a.add('d');
//		assertEquals("b(1)a(0)--d(0)--",a.toString());
//		
//		a.add('c');
//		assertEquals("b(2)a(0)--d(1)c(0)---",a.toString());
//		
//		a.add('g');
//		assertEquals("b(2)a(0)--d(1)c(0)--g(0)--",a.toString());
//		
//		a.add('i');
//		assertEquals("b(3)a(0)--d(2)c(0)--g(1)-i(0)--",a.toString());
//		
//		a.add('h');
//		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---",a.toString());
//	}

//	@Test
//	void testBFNotRotations() {
//		AVLTree<Character> a = new AVLTree<Character>();
//		a.add('b');
//		assertEquals("b(0)--",a.toString());
//		
//		a.add('a');
//		assertEquals("b(-1)a(0)---",a.toString());
//		
//		a.add('d');
//		assertEquals("b(0)a(0)--d(0)--",a.toString());
//		
//		a.add('c');
//		assertEquals("b(1)a(0)--d(-1)c(0)---",a.toString());
//		
//		a.add('g');
//		assertEquals("b(1)a(0)--d(0)c(0)--g(0)--",a.toString());
//		
//		a.add('i');
//		assertEquals("b(2)a(0)--d(1)c(0)--g(1)-i(0)--",a.toString());
//		
//		a.add('h');
//		assertEquals("b(3)a(0)--d(2)c(0)--g(2)-i(-1)h(0)---",a.toString());
//	}

	@Test
	void toStringWithRotations() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		assertEquals("b(0)--", a.toString());

		a.add('a');
		assertEquals("b(-1)a(0)---", a.toString());

		a.add('d');
		assertEquals("b(0)a(0)--d(0)--", a.toString());

		a.add('c');
		assertEquals("b(1)a(0)--d(-1)c(0)---", a.toString());

		a.add('g');
		assertEquals("b(1)a(0)--d(0)c(0)--g(0)--", a.toString());

		a.add('i');
		assertEquals("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", a.toString());

		a.add('h');
		assertEquals("d(0)b(0)a(0)--c(0)--h(0)g(0)--i(0)--", a.toString());
	}

}
