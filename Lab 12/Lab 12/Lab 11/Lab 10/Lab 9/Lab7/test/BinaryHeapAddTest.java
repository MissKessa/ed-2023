import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BinaryHeapAddTest {

	@Test
	void test() {
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

}
