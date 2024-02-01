package uo.ed.p1.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RecursiveFactorialIterTest {
	/**
	 * GIVEN: a negative parameter
	 * <p>
	 * WHEN: calling factorialIter
	 * <p>
	 * THEN: an IllegalArgumentException is thrown because the parameter cannot be
	 * negative
	 */
	@Test
	void negativeParameter() {
		int n = -1;
		try {
			Recursive.factorialIter(n);
		} catch (IllegalArgumentException e) {
			assertEquals(n + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER, e.getMessage());
		}
		n = -50;
		try {
			Recursive.factorialIter(n);
		} catch (IllegalArgumentException e) {
			assertEquals(n + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER, e.getMessage());
		}
	}

	/**
	 * GIVEN: a positive parameter
	 * <p>
	 * WHEN: calling factorialIter
	 * <p>
	 * THEN: returns the n!
	 */
	@Test
	void facSequence() {
		int facSequence[] = { 1, 1, 2, 6, 24, 120, 720 };
		for (int i = 0; i < 7; i++) {
			assertEquals(facSequence[i], Recursive.factorialIter(i));
		}
	}

}
