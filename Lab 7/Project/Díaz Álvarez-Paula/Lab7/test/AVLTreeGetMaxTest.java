
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AVLTreeGetMaxTest {

	@Test
	void maxCharacterTree() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals('i', (char) a.getMax(a.getRoot()));
	}

	@Test
	void maxIntegerTree() {
		AVLTree<Integer> a = new AVLTree<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		assertEquals(7, a.getMax(a.getRoot()));
	}

}
