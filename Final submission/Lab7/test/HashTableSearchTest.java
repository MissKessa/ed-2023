import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HashTableSearchTest {

	@Test
	void searchLinearTable() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(12));

	}

	@Test
	void searchQuadraticTable() {
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);
		assertEquals("[0] (1) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(false, b.search(12));
	}

	@Test
	void searchDoubleHashingTable() {
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 1.0);
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(false, b.search(12));
	}

}
