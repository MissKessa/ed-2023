import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableRemoveTest {

	@Test
	void linearProbing() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		
		a.remove(24);
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(24));
		assertEquals("[0] (2) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ",a.toString());
		
		a.add(15);
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ",a.toString());
	}
	
	@Test
	void quadraticProbing() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		
		a.remove(24);
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(24));
		
		assertEquals("[0] (2) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ",a.toString());
		
		a.add(15);
		assertEquals("[0] (1) = 15 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ",a.toString());
	}


}
