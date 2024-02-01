import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableFinalTest {

	@Test
	void linearProbingNoResizing() {
		HashTable<Integer> a = new HashTable<Integer>(7, HashTable.LINEAR_PROBING, 1);
		
		a.add(4);
		assertEquals(0.14, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ",a.toString());
		
		a.add(10);
		assertEquals(0.29, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ",a.toString());
		
		a.add(12);
		assertEquals(0.43, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (0) = null - ",a.toString());
		
		a.add(3);
		assertEquals(0.57, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (1) = 3 - ",a.toString());
		
		a.add(17);
		assertEquals(0.71, a.getLF(),0.2);
		assertEquals("[0] (1) = 17 - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (1) = 3 - ",a.toString());
		
		a.add(15);
		assertEquals(0.85, a.getLF(),0.2);
		assertEquals("[0] (1) = 17 - [1] (1) = 15 - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (1) = 3 - ",a.toString());
		
		a.add(14);
		assertEquals(1, a.getLF(),0.2);
		assertEquals("[0] (1) = 17 - [1] (1) = 15 - [2] (1) = 14 - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (1) = 3 - ",a.toString());
	}
	
	@Test
	void linearProbingResizing() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		
		a.add(4);
		assertEquals(0.2, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - ",a.toString());
		
		a.add(1);
		assertEquals(0.4, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - ",a.toString());
		
		a.remove(4);
		assertEquals(0.2, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (2) = 4 - ",a.toString());
		
		a.add(2);
		assertEquals(0.4, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (1) = 2 - [3] (0) = null - [4] (2) = 4 - ",a.toString());
		
		a.add(3); //resize
		assertEquals(0.27, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (0) = null - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",a.toString());
		
	}
	
	@Test
	void quadraticProbingNoResizing() {
		HashTable<Integer> a = new HashTable<Integer>(7, HashTable.QUADRATIC_PROBING, 1);
		
		a.add(4);
		assertEquals(0.14, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ",a.toString());
		
		a.add(10);
		assertEquals(0.29, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ",a.toString());
		
		a.add(12);
		assertEquals(0.43, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (0) = null - ",a.toString());
		
		a.add(3);
		assertEquals(0.57, a.getLF(),0.2);
		assertEquals("[0] (1) = 3 - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (0) = null - ",a.toString());
		
		a.add(16);
		assertEquals(0.71, a.getLF(),0.2);
		assertEquals("[0] (1) = 3 - [1] (0) = null - [2] (1) = 16 - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (0) = null - ",a.toString());
		
		a.add(15);
		assertEquals(0.85, a.getLF(),0.2);
		assertEquals("[0] (1) = 3 - [1] (1) = 15 - [2] (1) = 16 - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (0) = null - ",a.toString());
		
		a.add(13);
		assertEquals(1, a.getLF(),0.2);
		assertEquals("[0] (1) = 3 - [1] (1) = 15 - [2] (1) = 16 - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (1) = 13 - ",a.toString());
	}
	
	@Test
	void quadraticProbingResizing() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 0.5);
		
		a.add(4);
		assertEquals(0.2, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - ",a.toString());
		
		a.add(9);
		assertEquals(0.4, a.getLF(),0.2);
		assertEquals("[0] (1) = 9 - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - ",a.toString());
		
		a.remove(4);
		assertEquals(0.2, a.getLF(),0.2);
		assertEquals("[0] (1) = 9 - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (2) = 4 - ",a.toString());
		
		a.add(2);
		assertEquals(0.4, a.getLF(),0.2);
		assertEquals("[0] (1) = 9 - [1] (0) = null - [2] (1) = 2 - [3] (0) = null - [4] (2) = 4 - ",a.toString());
		
		a.add(3); //resize
		assertEquals(0.27, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (1) = 2 - [3] (1) = 3 - [4] (0) = null - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (1) = 9 - [10] (0) = null - ",a.toString());
		
	}
	
	@Test
	void doubleHashingNoResizing() {
		HashTable<Integer> a = new HashTable<Integer>(7, HashTable.DOUBLE_HASHING, 1);
		
		a.add(4);
		assertEquals(0.14, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ",a.toString());
		
		a.add(10);
		assertEquals(0.29, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - ",a.toString());
		
		a.add(12);
		assertEquals(0.43, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (0) = null - ",a.toString());
		
		a.add(3);
		assertEquals(0.57, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (1) = 3 - ",a.toString());
		
		a.add(17);
		assertEquals(0.71, a.getLF(),0.2);
		assertEquals("[0] (1) = 17 - [1] (0) = null - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (1) = 3 - ",a.toString());
		
		a.add(15);
		assertEquals(0.85, a.getLF(),0.2);
		assertEquals("[0] (1) = 17 - [1] (1) = 15 - [2] (0) = null - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (1) = 3 - ",a.toString());
		
		a.add(14);
		assertEquals(1, a.getLF(),0.2);
		assertEquals("[0] (1) = 17 - [1] (1) = 15 - [2] (1) = 14 - [3] (1) = 10 - [4] (1) = 4 - [5] (1) = 12 - [6] (1) = 3 - ",a.toString());
	}
	
	@Test
	void doubleHashingResizing() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 0.5);
		
		a.add(4);
		assertEquals(0.2, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - ",a.toString());
		
		a.add(1);
		assertEquals(0.4, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (1) = 4 - ",a.toString());
		
		a.remove(4);
		assertEquals(0.2, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (0) = null - [3] (0) = null - [4] (2) = 4 - ",a.toString());
		
		a.add(2);
		assertEquals(0.4, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (1) = 2 - [3] (0) = null - [4] (2) = 4 - ",a.toString());
		
		a.add(3); //resize
		assertEquals(0.27, a.getLF(),0.2);
		assertEquals("[0] (0) = null - [1] (1) = 1 - [2] (1) = 2 - [3] (1) = 3 - [4] (0) = null - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",a.toString());
		
	}

}
