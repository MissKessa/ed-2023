import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableDynamicResizingTest {

	@Test
	void test() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		a.add(4);
		assertEquals(0.2, a.getLF(),0.1);
		
		a.add(13);
		assertEquals(0.4, a.getLF(),0.1);
		
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ",a.toString());
		
		a.add(24); //DYNAMIC RESIZING!!
		assertEquals(0.27, a.getLF(),0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",a.toString());
	} 

}
