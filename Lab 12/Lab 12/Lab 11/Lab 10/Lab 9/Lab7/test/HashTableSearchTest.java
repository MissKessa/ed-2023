import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HashTableSearchTest {

	@Test
	void test() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ",a.toString());
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(12));
	}

}
