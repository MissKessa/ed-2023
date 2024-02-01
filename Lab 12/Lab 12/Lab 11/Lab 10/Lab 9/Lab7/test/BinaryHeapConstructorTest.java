import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BinaryHeapConstructorTest {

	@Test
	void passingArray() {
		BinaryHeap<Integer> a = new BinaryHeap<>(new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
		assertEquals(a.toString(), "[1, 2, 4, 3, 6, 5, 8, 10, 7, 9]");
	}

}
