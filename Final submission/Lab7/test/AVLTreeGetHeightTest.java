
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AVLTreeGetHeightTest {

	@Test
	void heightCharacterTree() {
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

	@Test
	void heightIntegerTree() {
		AVLTree<Integer> a = new AVLTree<Integer>();
		a.add(2);
		a.add(1);
		a.add(4);
		assertEquals(2, a.getHeight());
	}

}
