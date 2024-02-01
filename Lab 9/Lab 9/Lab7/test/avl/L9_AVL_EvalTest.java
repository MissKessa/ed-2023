package avl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class L9_AVL_EvalTest {

	@Test
	public void test_AVL_5() {

		AVLTree<Integer> ti = new AVLTree<Integer>();

		ti.add(10);
		ti.add(6);
		ti.add(15);
		ti.add(3);
		ti.add(9);
		ti.add(14);
		ti.add(20);

		assertEquals("10(0)6(0)3(0)--9(0)--15(0)14(0)--20(0)--", ti.toString());
		assertEquals(3, ti.getHeight());

		ti.add(2);
		ti.add(4);
		ti.add(7);
		ti.add(12);

		assertEquals("10(0)6(0)3(0)2(0)--4(0)--9(-1)7(0)---15(-1)14(-1)12(0)---20(0)--", ti.toString());
		assertEquals(4, ti.getHeight());

		ti.add(1);

		assertEquals("10(-1)6(-1)3(-1)2(-1)1(0)---4(0)--9(-1)7(0)---15(-1)14(-1)12(0)---20(0)--", ti.toString());
		assertEquals(5, ti.getHeight());

		// 20, 4, 10, 9, 6, 3.

		ti.remove(20);

		assertEquals("6(0)3(-1)2(-1)1(0)---4(0)--10(0)9(-1)7(0)---14(0)12(0)--15(0)--", ti.toString());
		assertEquals(4, ti.getHeight());

		ti.remove(4);

		assertEquals("6(1)2(0)1(0)--3(0)--10(0)9(-1)7(0)---14(0)12(0)--15(0)--", ti.toString());
		assertEquals(4, ti.getHeight());

		ti.remove(10);

		assertEquals("6(1)2(0)1(0)--3(0)--9(1)7(0)--14(0)12(0)--15(0)--", ti.toString());
		assertEquals(4, ti.getHeight());

		ti.remove(9);

		assertEquals("6(1)2(0)1(0)--3(0)--14(-1)7(1)-12(0)--15(0)--", ti.toString());
		assertEquals(4, ti.getHeight());

		ti.remove(6);

		assertEquals("3(1)2(-1)1(0)---14(-1)7(1)-12(0)--15(0)--", ti.toString());
		assertEquals(4, ti.getHeight());

		ti.remove(3);
		assertEquals("7(0)2(-1)1(0)---14(0)12(0)--15(0)--", ti.toString());
		assertEquals(3, ti.getHeight());
	}
}