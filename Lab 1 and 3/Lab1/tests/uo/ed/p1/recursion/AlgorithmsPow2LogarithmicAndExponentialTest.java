package uo.ed.p1.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AlgorithmsPow2LogarithmicAndExponentialTest {

	/**
	 * GIVEN: a negative exponent
	 * <p>
	 * WHEN: calling pow2LogarithmicAndExponential
	 * <p>
	 * THEN: an IllegalArgumentException is thrown because the exponent cannot be
	 * negative
	 */
	@Test
	void negativeParameter() {
		int exp = -1;
		try {
			Algorithms.pow2LogarithmicAndExponential(exp);
		} catch (IllegalArgumentException e) {
			assertEquals(exp + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER, e.getMessage());
		}
		exp = -50;
		try {
			Algorithms.pow2LogarithmicAndExponential(exp);
		} catch (IllegalArgumentException e) {
			assertEquals(exp + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER, e.getMessage());
		}
	}

	/**
	 * GIVEN: a positive exponent
	 * <p>
	 * WHEN: calling pow2LogarithmicAndExponential
	 * <p>
	 * THEN: returns the 2^parameter
	 */
	@Test
	void pow2ExponentialSequence() {
		int pow2Sequence[] = { 1, 2, 4, 8, 16, 32, 64, 128 };
		for (int i = 0; i < 8; i++) {
			assertEquals(pow2Sequence[i], Algorithms.pow2LogarithmicAndExponential(i));
		}
	}

}
