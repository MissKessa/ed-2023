package uo.ed.p1.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RecursiveFibonacciIterTest {

	/**
	 * GIVEN: a negative index
	 * <p>
	 * WHEN: calling fibonacciIter
	 * <p>
	 * THEN: an IllegalArgumentException is thrown because the parameter cannot be
	 * negative
	 */
	@Test
	void negativeParameter() {
		int index = -1;
		try {
			Recursive.fibonacciIter(index);
		} catch (IllegalArgumentException e) {
			assertEquals(Recursive.ERROR_MESSAGE_NEGATIVE_INDEX, e.getMessage());
		}
		index = -50;
		try {
			Recursive.fibonacciIter(index);
		} catch (IllegalArgumentException e) {
			assertEquals(Recursive.ERROR_MESSAGE_NEGATIVE_INDEX, e.getMessage());
		}
	}

	/**
	 * GIVEN: a positive index
	 * <p>
	 * WHEN: calling fibonacciIter
	 * <p>
	 * THEN: returns the number with that position on the fibonacci sequence
	 */
	@Test
	void fibbonacciSequence() {
		int fibbonacciSequence[] = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233 };
		for (int i = 0; i < 14; i++) {
			assertEquals(fibbonacciSequence[i], Recursive.fibonacciIter(i));
		}
	}

}
