import static org.junit.Assert.*;

import org.junit.Test;


public class L11_HashTable_EvalTest {

	
	
	@Test
	public void testPrimeNumber() throws Exception
	{
		// Example
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		
		
		assertEquals(2, a.getNextPrimeNumber(1));
		assertEquals(3, a.getNextPrimeNumber(2));
		assertEquals(5, a.getNextPrimeNumber(3));
		assertEquals(5, a.getNextPrimeNumber(4));
		assertEquals(7, a.getNextPrimeNumber(5));
		assertEquals(7, a.getNextPrimeNumber(6));
		assertEquals(11, a.getNextPrimeNumber(7));
		assertEquals(11, a.getNextPrimeNumber(8));
		assertEquals(11, a.getNextPrimeNumber(9));
		assertEquals(11, a.getNextPrimeNumber(10));
		assertEquals(13, a.getNextPrimeNumber(11));
		
		assertEquals(13, a.getPrevPrimeNumber(15));
		assertEquals(13, a.getPrevPrimeNumber(14));
		assertEquals(11, a.getPrevPrimeNumber(13));
		assertEquals(11, a.getPrevPrimeNumber(12));
		assertEquals(7, a.getPrevPrimeNumber(11));
		assertEquals(7, a.getPrevPrimeNumber(10));
		assertEquals(7, a.getPrevPrimeNumber(9));
		assertEquals(7, a.getPrevPrimeNumber(8));
		assertEquals(5, a.getPrevPrimeNumber(7));
		assertEquals(5, a.getPrevPrimeNumber(6));
		assertEquals(3, a.getPrevPrimeNumber(5));
		assertEquals(3, a.getPrevPrimeNumber(4));
		assertEquals(2, a.getPrevPrimeNumber(3));
	}
	

}
