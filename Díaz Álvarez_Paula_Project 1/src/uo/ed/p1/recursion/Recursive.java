package uo.ed.p1.recursion;

/**
 * This class contain a lot of static recursive methods. Some of them are also
 * implemented with loops to compare them
 * 
 * @author Paula Díaz Álvarez
 */

public class Recursive {

	public static final String ERROR_MESSAGE_NULL_MATRIX = "The matrix cannot be null";
	public static final String ERROR_MESSAGE_NEGATIVE_INDEX = "The index cannot be negative";
	public static final String ERROR_MESSAGE_NEGATIVE_NUMBER_OR_ZERO = " is a negative number or zero";
	public static final String ERROR_MESSAGE_NEGATIVE_NUMBER = " is a negative number";

	public static void main(String[] args) {
		System.out.println(factorialIter(0));

//		for (int i = 0; i < 14; i++) {
//			System.out.println(fib(i));
//		}
//
//		System.out.println(isOdd(-2));
//		for (int i = 0; i < 5; i++) {
//			System.out.println(pow2(i));
//		}
//
//		System.out.println(division(5, 2));
//
//		for (int i = 0; i < 14; i++) {
//			System.out.println(fibIter(i));
//		}
//
//		int[][] matrix = { { 1 }, { 2 } };
//		System.out.println(isSymmetric(matrix));
	}

	/**
	 * Returns if the parameter n is an odd number or not (recursive method)
	 * 
	 * @param n is the number that we want to check if it's odd or not
	 * @return true if the number is odd. False is it's even
	 */

	public static boolean isOdd(int n) {
		/*
		 * Complexity: O(n) because the method is called, approximately, n times
		 * depending on the parameter n, due to the recursion call.
		 */
		// substract by 2 until we reach 0 (false) or 1 (true)
		if (n < 0)
			n = n * (-1);
		if (n == 0)
			return false;
		if (n == 1)
			return true;

		return isOdd(n - 2);
	}

	/**
	 * Returns the factorial of the parameter n (with loops)
	 * 
	 * @param n is the number that we want the calculate the factorial for
	 * @return the factorial of the parameter n
	 * @throws an IllegalArgumentException is the parameter n is a negative number
	 */

	public static int factorialIter(int n) {
		/*
		 * Complexity: O(n) because the loop is executed, approximately, n times
		 * depending on the parameter n.
		 */

		if (n < 0)
			throw new IllegalArgumentException(n + ERROR_MESSAGE_NEGATIVE_NUMBER);

		int result = 1;

		for (int i = 2; i <= n; i++) {
			result *= i;
		}
		return result;

	}

	/**
	 * 
	 * Returns the factorial of the parameter n (recursive method)
	 * 
	 * @param n is the number that we want the calculate the factorial for
	 * @return the factorial of the parameter n
	 * @throws an IllegalArgumentException is the parameter n is a negative number
	 */
	public static int factorial(int n) {
		/*
		 * Complexity: O(n) because the method is called n times depending on the
		 * parameter n, due to the recursive call.
		 */

		if (n < 0)
			throw new IllegalArgumentException(n + ERROR_MESSAGE_NEGATIVE_NUMBER);

		if (n == 0) // base case, and stop condition
			return 1; // base case

		return n * factorial(n - 1); // general case
	}

	// /**
	// * Returns the value of 2 to the power of parameter exp (2^exp) (recursive
	// * method)
	// *
	// * @param exp is the exponent of 2
	// * @return the result of 2^exp
	// * @throws an IllegalArgumentException is the exponent is a negative number
	// */
	//
	// public static int pow2(int exp) { //O(n)
	//// 1,2,4,8...
	// if (exp < 0)
	// throw new IllegalArgumentException(exp + ERROR_MESSAGE_NEGATIVE_NUMBER);
	//
	// if (exp == 0)
	// return 1;
	//
	// return 2 * pow2(exp - 1);
	// }

	/**
	 * Returns the number in the fibbonacci sequence with that index (with loops):
	 * <p>
	 * index: 0 1 2 3 4 5 6 7 8 9 10 11 12 13
	 * <p>
	 * value: 0 1 1 2 3 5 8 13 21 34 55 89 144 233
	 * 
	 * @param index is the position of the number we want to calculate in the
	 *              fibbonacci sequence
	 * @return the number with that index in the fibbonacci sequence
	 * @throws an IllegalArgumentException is the index is a negative number
	 */

	public static int fibonacciIter(int index) {
		/*
		 * Complexity: O(index) = O(n) because the loop is executed, approximately, n
		 * times depending on the parameter index
		 */
		if (index < 0)
			throw new IllegalArgumentException(ERROR_MESSAGE_NEGATIVE_INDEX);

		int fib = 0;
		int fib1 = 0; // element n-2
		int fib2 = 1; // element n-1
		if (index == 0 || index == 1) {
			return index;
		}

		for (int i = 2; i <= index; i++) {
			fib = fib1 + fib2;
			fib1 = fib2;
			fib2 = fib;
		}
		return fib;

	}

	/**
	 * Returns the number in the fibbonacci sequence with that index (recursive
	 * method):
	 * <p>
	 * index: 0 1 2 3 4 5 6 7 8 9 10 11 12 13
	 * <p>
	 * value: 0 1 1 2 3 5 8 13 21 34 55 89 144 233
	 * 
	 * @param index is the position of the number we want to calculate in the
	 *              fibbonacci sequence
	 * @return the number with that index in the fibbonacci sequence
	 * @throws an IllegalArgumentException is the index is a negative number
	 */

	public static int fibonacci(int index) {
		/*
		 * Complexity: O(2^index) = O(2^n) because in each iteration there are 2 calls,
		 * then 4 (each call calls 2), then 8...
		 */
		if (index < 0)
			throw new IllegalArgumentException(ERROR_MESSAGE_NEGATIVE_INDEX);

		if (index == 0 || index == 1)
			return index;

		return fibonacci(index - 1) + fibonacci(index - 2);
	}

	/**
	 * 
	 * Returns if the given matrix is symmetric or not (recursive method)
	 * 
	 * @param matrix is the given matrix that we want to check if it's symmetric or
	 *               not
	 * @return true if the matrix is symmetric. Otherwise, false
	 * @throws an IllegalArgumentException if the matrix is null
	 */

	public static boolean isSymmetric(int[][] matrix) {
		/*
		 * Complexity: O(n^2) because the 2 nested loops going through the matrix. Each
		 * loop is executed n times depending on the length of the given matrix as
		 * parameter
		 */
		if (matrix == null)
			throw new IllegalArgumentException(ERROR_MESSAGE_NULL_MATRIX);

		// diagonal doesn't care
		// one dimension
		// false: not square

		if (matrix.length == 1 && matrix[0].length == 1) // one dimension matrix
			return true;

		// if (matrix.length == 1 && matrix[0].length != 1) // not row matrix
		// return false;
		// if (matrix.length != 1 && matrix[0].length == 1) // not column matrix
		// return false;

//		if ((matrix.length == 1 || matrix[0].length == 1) && (matrix.length != matrix[0].length))
//			return false;

		int[][] reducedMatrix = new int[matrix.length - 1][matrix[0].length - 1];
		int[] firstColumn = new int[matrix[0].length];
		int[] firstRow = new int[matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			if ((matrix[i].length != matrix[0].length) || matrix.length != matrix[i].length)
				return false; // It's not squared

			firstColumn[i] = matrix[i][0];
			firstRow[i] = matrix[0][i];

			if (i == 0)
				continue;

			for (int j = 1; j < matrix[i].length; j++)
				reducedMatrix[i - 1][j - 1] = matrix[i][j];
		}

		return arrayIsEqual(firstColumn, firstRow) && isSymmetric(reducedMatrix);

	}

	/**
	 * Compares the content of 2 given arrays
	 * 
	 * @param array1 is one of the arrays to compare
	 * @param array2 is the other array to compare
	 * @return true if they are equal in content
	 * @throws an IllegalArgumentException if one of the matrix is null
	 */

	private static boolean arrayIsEqual(int[] array1, int[] array2) {
		if (array1 == null)
			throw new IllegalArgumentException("The array1 cannot be null");

		if (array2 == null)
			throw new IllegalArgumentException("The array2 cannot be null");

		if (array1.length != array2.length)
			return false;

		if (array1.equals(array2))
			return true;

		for (int i = 0; i < array1.length; i++)
			if (array1[i] != array2[i])
				return false;

		return true;
	}

	/**
	 * 
	 * Returns the remainder of the integer division of dividend divide by divider
	 * (recursive method)
	 * 
	 * @param dividend is the dividend
	 * @param divider  is the divisor
	 * @return the remainder between dividend and divider
	 * @throws an IllegalArgumentException if dividend or divider are negative
	 *            number, or divider is zero
	 */

	public static int remainder(int dividend, int divider) {
		/*
		 * Complexity: O(dividend/divisor) = O(n) because the method is called n times
		 * (dividend/divisor times), that depends on both parameters, due to the
		 * recursion
		 */
		if (dividend < 0)
			throw new IllegalArgumentException(dividend + ERROR_MESSAGE_NEGATIVE_NUMBER);

		if (divider <= 0)
			throw new IllegalArgumentException(divider + ERROR_MESSAGE_NEGATIVE_NUMBER_OR_ZERO);
// 		r=D-d-d..... 
		if (dividend < divider)
			return dividend;

		return remainder(dividend - divider, divider);

	}

	/**
	 * 
	 * Returns the integer division of dividend divide by divider (recursive method)
	 * 
	 * @param dividend is the dividend
	 * @param divider  is the divisor
	 * @return the integer division between dividend and divider
	 * @throws an IllegalArgumentException if dividend or divider are negative
	 *            number, or divider is zero
	 */

	public static int division(int dividend, int divider) {
		/*
		 * Complexity: O(dividend/divisor) = O(n) because the method is called n times
		 * (dividend/divisor times), that depends on both parameters, due to the
		 * recursion
		 */
		if (dividend < 0)
			throw new IllegalArgumentException(dividend + ERROR_MESSAGE_NEGATIVE_NUMBER);
		if (divider <= 0)
			throw new IllegalArgumentException(divider + ERROR_MESSAGE_NEGATIVE_NUMBER_OR_ZERO);

		int i = 1;

		if (dividend < divider)
			return 0;

		return i + division(dividend - divider, divider);

	}

}