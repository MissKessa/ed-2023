package avl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AVLTreeRemoveTest {

	@Test
	void noChildren() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		
		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
		//No children
		a.remove('a');
		assertEquals("b(4)-d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
		
		a.remove('c');
		assertEquals("b(4)-d(3)-g(2)-i(1)h(0)---", a.toString());
		
		a.remove('h');
		assertEquals("b(3)-d(2)-g(1)-i(0)--", a.toString());
		
	}

	
	@Test
	void hasOneChild() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		
		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
		
		//Left child
		a.remove('i');
		assertEquals("b(3)a(0)--d(2)c(0)--g(1)-h(0)--", a.toString());
		
		//Right child
		a.remove('g');		
		assertEquals("b(2)a(0)--d(1)c(0)--h(0)--", a.toString());
		
	}
	
	@Test
	void hasTwoChildren() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		
		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
		
		a.remove('b');
		assertEquals("a(4)-d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
		
		a.remove('d');
		assertEquals("a(4)-c(3)-g(2)-i(1)h(0)---", a.toString());
		
	}
}
