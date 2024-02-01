import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinaryHeapAddTest {

	@Test
	void addIntegerHeap() {
		BinaryHeap<Integer> a = new BinaryHeap<Integer>();

		a.add(10);
		a.add(9);
		a.add(8);
		assertTrue(a.toString().equals("[8, 10, 9]"));

		a.add(7);
		assertTrue(a.toString().equals("[7, 8, 9, 10]"));

		a.add(6);
		assertTrue(a.toString().equals("[6, 7, 9, 10, 8]"));

		a.add(5);
		assertTrue(a.toString().equals("[5, 7, 6, 10, 8, 9]"));

		a.add(4);
		assertTrue(a.toString().equals("[4, 7, 5, 10, 8, 9, 6]"));
	}

	@Test
	void addCharacterHeap() {
		BinaryHeap<Character> a = new BinaryHeap<Character>();

		a.add('j');
		a.add('i');
		a.add('h');
		assertTrue(a.toString().equals("[h, j, i]"));

		a.add('g');
		assertTrue(a.toString().equals("[g, h, i, j]"));

		a.add('f');
		assertTrue(a.toString().equals("[f, g, i, j, h]"));

		a.add('e');
		assertTrue(a.toString().equals("[e, g, f, j, h, i]"));

		a.add('d');
		assertTrue(a.toString().equals("[d, g, e, j, h, i, f]"));
	}

}
