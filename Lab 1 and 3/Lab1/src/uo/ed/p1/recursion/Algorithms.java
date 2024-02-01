package uo.ed.p1.recursion;

public class Algorithms {
	public static final String ERROR_MESSAGE_NEGATIVE_NUMBER = " is a negative number";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int pow2Iter(long exp) {
		/*
		 * Complexity: O(exp) = O(n) because the loop is executes n times depending on
		 * the parameter exp.
		 */
		if (exp < 0)
			throw new IllegalArgumentException(exp + ERROR_MESSAGE_NEGATIVE_NUMBER);

		int res = 1;
		for (int i = 1; i <= exp; i++) {
			res *= 2;
			TestBench.doNothing(exp);
		}
		return res;
	}

	/**
	 * Returns the value of 2 to the power of parameter exp (2^exp) (recursive
	 * method)
	 * 
	 * @param exp is the exponent of 2
	 * @return the result of 2^exp
	 * @throws an IllegalArgumentException is the exponent is a negative number
	 */

	public static int pow2(long exp) {
		/*
		 * Complexity: O(exp) = O(n) because the method is called n times depending on
		 * the parameter exp, due to the recursion call.
		 */
		TestBench.doNothing(exp);

		if (exp < 0)
			throw new IllegalArgumentException(exp + ERROR_MESSAGE_NEGATIVE_NUMBER);

		if (exp == 0)
			return 1;

		return 2 * pow2(exp - 1);
	}

	public static int pow2Logarithmic(long exp) {
		/*
		 * Complexity: O(log(exp)) = O(log(n)) because the exponent is divided by 2 in
		 * each call, and only call once in each iteration thanks to storing the result
		 * of the call in a variable
		 */
		TestBench.doNothing(exp);

		if (exp < 0)
			throw new IllegalArgumentException(exp + ERROR_MESSAGE_NEGATIVE_NUMBER);

		if (exp == 0)
			return 1;

		int res = pow2Logarithmic(exp / 2); // one call
		if (exp % 2 != 0)
			return res * res * 2;
		return res * res;
	}

	public static int pow2Exponential(long exp) {
		/*
		 * Complexity: O(2^exp) = O(2^n) because in each iteration, the method is called
		 * twice. So, then each of these calls, calls twice again; and these do the
		 * same..
		 */
		TestBench.doNothing(exp);

		if (exp < 0)
			throw new IllegalArgumentException(exp + ERROR_MESSAGE_NEGATIVE_NUMBER);

		if (exp == 0)
			return 1;

		return pow2Exponential(exp - 1) + pow2Exponential(exp - 1);
	}

	public static int pow2LogarithmicAndExponential(long exp) { //
		/*
		 * Complexity: O(log(2^exp)) = O(log(2^n)) ~ O(n) because in each iteration, the
		 * method is called twice, but the exponent is divided by 2 in each call
		 */
		TestBench.doNothing(exp);

		if (exp < 0)
			throw new IllegalArgumentException(exp + ERROR_MESSAGE_NEGATIVE_NUMBER);

		if (exp == 0)
			return 1;

		if (exp % 2 != 0)
			return pow2LogarithmicAndExponential(exp / 2) * pow2LogarithmicAndExponential(exp / 2) * 2; // (2^(n/2))*(2^(n/2))=2^n;
																										// 2 calls
		return pow2LogarithmicAndExponential(exp / 2) * pow2LogarithmicAndExponential(exp / 2);

	}

	public static void linear(long iter) {
		/*
		 * Complexity: O(iter) = O(n) because the loop is executed n times depending on
		 * the parameter iter
		 */
		for (int i = 0; i < iter; i++) {
			TestBench.doNothing(iter);
		}
	}

	public static void quadratic(long iter) {
		/*
		 * Complexity: O(iter^2) = O(n^2) because the outside loop is executed n times
		 * depending on the parameter iter, and the inner loop is also executes n times.
		 * So it's executed n*n times.
		 */
		for (int i = 0; i < iter; i++) {
			for (int j = 0; j < iter; j++) {
				TestBench.doNothing(iter);
			}
		}
	}

	public static void cubic(long iter) {
		/*
		 * Complexity: O(iter^3) = O(n^3) because of the 3 nested loops (same
		 * explanation as the square(long iter) method. So it's executed n*n*n times.
		 */
		for (int i = 0; i < iter; i++) {
			for (int j = 0; j < iter; j++) {
				for (int k = 0; k < iter; k++) {
					TestBench.doNothing(iter);
				}
			}
		}
	}

	public static void logarithmic(long iter) {
		/*
		 * Complexity: O(log(iter)) = O(log(n)) because i increases by 2 in iteration
		 * (same as dividing iter by 2)
		 */
		for (int i = 1; i < iter; i *= 2) {
			TestBench.doNothing(iter);
		}
	}

}
