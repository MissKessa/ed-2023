package avl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AVLTreeToStringTest {

	@Test
	void test() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		assertEquals("b--",a.toString());
		
		a.add('a');
		assertEquals("ba---",a.toString());
		
		a.add('d');
		assertEquals("ba--d--",a.toString());
		
		a.add('c');
		assertEquals("ba--dc---",a.toString());
		
		a.add('g');
		assertEquals("ba--dc--g--",a.toString());
		
		a.add('i');
		assertEquals("ba--dc--g-i--",a.toString());
		
		a.add('h');
		assertEquals("ba--dc--g-ih---",a.toString());
	}

}
