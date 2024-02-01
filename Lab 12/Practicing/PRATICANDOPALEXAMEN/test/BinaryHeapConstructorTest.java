import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinaryHeapConstructorTest {

	@Test
	void constructorByPassingArray() {
		Integer[] array = new Integer[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		BinaryHeap<Integer> a = new BinaryHeap<>(array);
		assertEquals(a.toString(), "[1, 2, 4, 3, 6, 5, 8, 10, 7, 9]");
	}

}