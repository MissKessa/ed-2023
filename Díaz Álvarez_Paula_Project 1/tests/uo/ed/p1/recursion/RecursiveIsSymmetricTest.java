package uo.ed.p1.recursion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RecursiveIsSymmetricTest {

	/**
	 * GIVEN: a dimensional matrix
	 * <p>
	 * WHEN: calling isSymmetric()
	 * <p>
	 * THEN: returns true
	 */
	@Test
	void oneDimension() {
		int[][] matrix = { { 1 } };
		assertTrue(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -1 } };
		assertTrue(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { 20 } };
		assertTrue(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -20 } };
		assertTrue(Recursive.isSymmetric(matrix));
	}

	/**
	 * GIVEN: a null matrix
	 * <p>
	 * WHEN: calling isSymmetric()
	 * <p>
	 * THEN: throws an IllegalArgumentException
	 */
	@Test
	void nullMatrix() {
		int[][] matrix = null;
		try {
			Recursive.isSymmetric(matrix);
		} catch (IllegalArgumentException e) {
			assertEquals(Recursive.ERROR_MESSAGE_NULL_MATRIX, e.getMessage());
		}
	}

	/**
	 * GIVEN: a row matrix
	 * <p>
	 * WHEN: calling isSymmetric()
	 * <p>
	 * THEN: returns false
	 */
	@Test
	void rowMatrix() {
		int[][] matrix = { { 1, 1 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -1, 2 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { 20, -5 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -20, 9 } };
		assertFalse(Recursive.isSymmetric(matrix));
	}

	/**
	 * GIVEN: a column matrix
	 * <p>
	 * WHEN: calling isSymmetric()
	 * <p>
	 * THEN: returns false
	 */
	@Test
	void columnMatrix() {
		int[][] matrix = { { 1 }, { 1 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -1 }, { 2 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { 20 }, { -5 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -20 }, { 9 } };
		assertFalse(Recursive.isSymmetric(matrix));
	}

	/**
	 * GIVEN: a not squared matrix
	 * <p>
	 * WHEN: calling isSymmetric()
	 * <p>
	 * THEN: returns false
	 */
	@Test
	void notSquaredMatrix() {
		int[][] matrix = { { 1, 1 }, { 1 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -1 }, { 2, -5 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { 20, 7 }, { -5, 9, 10 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -20, -7, 10 }, { -20, -7, 10 }, { 9, -5 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -20, -7, 10 }, { -20, -7, 10 } };
		assertFalse(Recursive.isSymmetric(matrix));
	}

	/**
	 * GIVEN: a not symmetric matrix
	 * <p>
	 * WHEN: calling isSymmetric()
	 * <p>
	 * THEN: returns false
	 */
	@Test
	void notSymmetricMatrix() {
		int[][] matrix = { { 1, 1 }, { 2, 1 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -1, 8 }, { 2, -5 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { 20, 7 }, { 20, 7 } };
		assertFalse(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -20, -7, 11 }, { 9, -5, 9 }, { 9, -5, 9 } };
		assertFalse(Recursive.isSymmetric(matrix));
	}

	/**
	 * GIVEN: a symmetric matrix
	 * <p>
	 * WHEN: calling isSymmetric()
	 * <p>
	 * THEN: returns true
	 */
	@Test
	void symmetricMatrix() {
		int[][] matrix = { { 1, 1 }, { 1, 1 } };
		assertTrue(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -1, 8 }, { 8, -1 } };
		assertTrue(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { 20, 7 }, { 7, -70 } };
		assertTrue(Recursive.isSymmetric(matrix));

		matrix = new int[][] { { -20, 1, 2 }, { 1, -5, 6 }, { 2, 6, 8 } };
		assertTrue(Recursive.isSymmetric(matrix));

	}

}
