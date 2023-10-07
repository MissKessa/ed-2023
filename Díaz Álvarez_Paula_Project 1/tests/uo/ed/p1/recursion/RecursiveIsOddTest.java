package uo.ed.p1.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RecursiveIsOddTest {

	/**
	 * GIVEN: a negative number
	 * <p>
	 * WHEN: calling isOdd
	 * <p>
	 * THEN: if it's odd returns true. Otherwise, false
	 */
	@Test
	void negativeIsOdd() {
		boolean[] negativeSequence = { false, true, false, true, false, true };
		for (int i = 0; i < 6; i++) {
			int number = -1 * i;
			assertEquals(negativeSequence[i], Recursive.isOdd(number));
		}
	}

	/**
	 * GIVEN: a positive number
	 * <p>
	 * WHEN: calling isOdd
	 * <p>
	 * THEN: if it's odd returns true. Otherwise, false
	 */
	@Test
	void positiveIsOdd() {
		boolean[] positiveSequence = { false, true, false, true, false, true };
		for (int i = 0; i < 6; i++) {
			int number = i;
			assertEquals(positiveSequence[i], Recursive.isOdd(number));
		}
	}

}
