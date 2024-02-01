import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableFTest {

	@Test
	void integerTest() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(2, a.f(7, 0));
		assertEquals(3, a.f(7, 1));
		assertEquals(4, a.f(7, 2));
		assertEquals(0, a.f(7, 3));
		
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 0.5);
		assertEquals(2, b.f(7, 0));
		assertEquals(3, b.f(7, 1));
		assertEquals(1, b.f(7, 2));
		assertEquals(1, b.f(7, 3));
	}
	
	@Test
	void characterTest() {
		HashTable<Character> a = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(0, a.f('A', 0));
		assertEquals(1, a.f('A', 1));
		assertEquals(2, a.f('A', 2));
		assertEquals(3, a.f('A', 3));
		
		HashTable<Character> b = new HashTable<Character>(5, HashTable.QUADRATIC_PROBING, 0.5);
		assertEquals(0, b.f('A', 0));
		assertEquals(1, b.f('A', 1));
		assertEquals(4, b.f('A', 2));
		assertEquals(4, b.f('A', 3));
	}


}
