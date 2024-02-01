package uo.ed.p1.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RecursiveFibonacciTest {

	/**
	 * GIVEN: a negative index
	 * <p>
	 * WHEN: calling fibonacci
	 * <p>
	 * THEN: an IllegalArgumentException is thrown because the parameter cannot be
	 * negative
	 */
	@Test
	void negativeParameter() {
		int index = -1;
		try {
			Recursive.fibonacci(index);
		} catch (IllegalArgumentException e) {
			assertEquals(Recursive.ERROR_MESSAGE_NEGATIVE_INDEX, e.getMessage());
		}
		index = -50;
		try {
			Recursive.fibonacci(index);
		} catch (IllegalArgumentException e) {
			assertEquals(Recursive.ERROR_MESSAGE_NEGATIVE_INDEX, e.getMessage());
		}
	}

	/**
	 * GIVEN: a positive parameter
	 * <p>
	 * WHEN: calling fibonacci
	 * <p>
	 * THEN: returns the number with that position on the fibonacci sequence
	 */
	@Test
	void fibbonacciSequence() {
		int fibbonacciSequence[] = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233 };
		for (int i = 0; i < 14; i++) {
			assertEquals(fibbonacciSequence[i], Recursive.fibonacci(i));
		}
	}

}
