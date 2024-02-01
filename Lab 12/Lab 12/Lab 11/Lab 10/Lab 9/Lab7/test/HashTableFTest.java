import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HashTableFTest {

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
		
		HashTable<Integer> c = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(2, c.f(7, 0));
		assertEquals(4, c.f(7, 1));
		assertEquals(1, c.f(7, 2));
		assertEquals(3, c.f(7, 3));
		assertEquals(0, c.f(7, 4));
		
		assertEquals(0, c.f(0, 0));
		assertEquals(1, c.f(2, 4));
		assertEquals(2, c.f(3,3));
		assertEquals(3, c.f(32, 1));
		assertEquals(4, c.f(1045, 2));
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
		
		HashTable<Character> c = new HashTable<Character>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(0, c.f('A', 0));
		assertEquals(1, c.f('A', 1));
		assertEquals(2, c.f('A', 2));
		assertEquals(3, c.f('A', 3));
		assertEquals(4, c.f('A', 4));
		
		assertEquals(2, c.f('a', 0));
		assertEquals(4, c.f('a', 1));
		assertEquals(1, c.f('a', 2));
		assertEquals(3, c.f('a', 3));
		assertEquals(0, c.f('a', 4));
		
	}


}
