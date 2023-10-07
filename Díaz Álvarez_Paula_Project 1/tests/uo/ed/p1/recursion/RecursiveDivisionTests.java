
package uo.ed.p1.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RecursiveDivisionTests {

	/**
	 * GIVEN: a negative dividend and a positive divisor
	 * <p>
	 * WHEN: calling division
	 * <p>
	 * THEN: an IllegalArgumentException is thrown because the dividend cannot be
	 * negative
	 */
	@Test
	void dividenIsANegativeNumber() {
		int dividend = -1;
		int divider = 2;
		try {
			Recursive.division(dividend, divider);
		} catch (IllegalArgumentException e) {
			assertEquals(dividend + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER, e.getMessage());
		}

		dividend = -50;
		try {
			Recursive.division(dividend, divider);
		} catch (IllegalArgumentException e) {
			assertEquals(dividend + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER, e.getMessage());
		}
	}

	/**
	 * GIVEN: a negative divisor and a positive dividend
	 * <p>
	 * WHEN: calling division
	 * <p>
	 * THEN: an IllegalArgumentException is thrown because the divisor cannot be
	 * negative
	 */
	@Test
	void dividorIsANegativeNumber() {
		int divider = -1;
		int dividend = 2;
		try {
			Recursive.division(dividend, divider);
		} catch (IllegalArgumentException e) {
			assertEquals(divider + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER_OR_ZERO, e.getMessage());
		}

		divider = -50;
		try {
			Recursive.division(dividend, divider);
		} catch (IllegalArgumentException e) {
			assertEquals(divider + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER_OR_ZERO, e.getMessage());
		}
	}

	/**
	 * GIVEN: a divisor that is zero and a positive dividend
	 * <p>
	 * WHEN: calling division
	 * <p>
	 * THEN: an IllegalArgumentException is thrown because the divisor cannot be
	 * zero
	 */
	@Test
	void dividorIsZero() {
		int divider = 0;
		int dividend = 2;
		try {
			Recursive.division(dividend, divider);
		} catch (IllegalArgumentException e) {
			assertEquals(divider + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER_OR_ZERO, e.getMessage());
		}

		dividend = 50;
		try {
			Recursive.division(dividend, divider);
		} catch (IllegalArgumentException e) {
			assertEquals(divider + Recursive.ERROR_MESSAGE_NEGATIVE_NUMBER_OR_ZERO, e.getMessage());
		}
	}

	/**
	 * GIVEN: a divisor and a dividend, where the divisor > dividend
	 * <p>
	 * WHEN: calling division
	 * <p>
	 * THEN: the result is 0
	 */
	@Test
	void divisorIsGreaterThanDividend() {
		int divider = 2;
		int dividend = 1;
		int result = 0;

		assertEquals(result, Recursive.division(dividend, divider));
		dividend = 5;
		divider = 90;
		assertEquals(result, Recursive.division(dividend, divider));
	}

	/**
	 * GIVEN: a divisor and a dividend, where the divisor = dividend
	 * <p>
	 * WHEN: calling division
	 * <p>
	 * THEN: the result is 1
	 */
	@Test
	void divisorIsEqualToDividend() {
		int divider = 5;
		int dividend = 5;
		int result = 1;

		assertEquals(result, Recursive.division(dividend, divider));
		dividend = 90;
		divider = 90;
		assertEquals(result, Recursive.division(dividend, divider));
	}

	/**
	 * GIVEN: a divisor and a dividend, where the divisor < dividend
	 * <p>
	 * WHEN: calling division
	 * <p>
	 * THEN: the result is the integer division D/d
	 */
	@Test
	void divisorIsLessThanDividend() {
		int divider = 2;
		int dividend = 4;
		int result = 2;

		assertEquals(result, Recursive.division(dividend, divider));
		dividend = 5;
		divider = 2;
		assertEquals(result, Recursive.division(dividend, divider));
		dividend = 50;
		divider = 10;
		result = 5;
		assertEquals(result, Recursive.division(dividend, divider));
	}

	/**
	 * GIVEN: a dividend that is 0 and a positive divisor
	 * <p>
	 * WHEN: calling division
	 * <p>
	 * THEN: the result is 0
	 */
	@Test
	void dividendIsZero() {
		int divider = 4;
		int dividend = 0;
		int result = 0;

		assertEquals(result, Recursive.division(dividend, divider));
		divider = 50;
		assertEquals(result, Recursive.division(dividend, divider));
	}

}
