import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HashTableToStringTest {

	@Test
	void toStringEmptyTable() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (0) = null - ",
				a.toString());

		HashTable<Character> b = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (0) = null - ",
				b.toString());
	}

}
