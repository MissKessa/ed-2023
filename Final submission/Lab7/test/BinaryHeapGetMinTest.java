import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinaryHeapGetMinTest {

	@Test
	void getMinIntegerHeap() {
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		heap.add(9);
		heap.add(8);
		heap.add(7);
		heap.add(6);
		heap.add(5);
		heap.add(1);
		heap.add(2);
		heap.add(3);
		heap.add(4);

		assertEquals(heap.toString(), "[1, 3, 2, 4, 7, 8, 5, 9, 6]");
		assertEquals(1, (int) heap.getMin());
		assertEquals(heap.toString(), "[2, 3, 5, 4, 7, 8, 6, 9]");

	}

	@Test
	void getMinCharacterHeap() {
		BinaryHeap<Character> b = new BinaryHeap<Character>();
		b.add('f');
		b.add('g');
		b.add('a');
		b.add('z');
		b.add('d');

		System.out.println(b.toString());
		assertEquals(b.toString(), "[a, d, f, z, g]");
		assertEquals('a', (char) b.getMin());
		assertEquals(b.toString(), "[d, g, f, z]");

	}

}
