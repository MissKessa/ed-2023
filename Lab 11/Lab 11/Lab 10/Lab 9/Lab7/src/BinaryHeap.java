
import java.util.ArrayList;
import java.util.Collections;

public class BinaryHeap<T extends Comparable<T>> {
	private ArrayList<T> heap;

	public BinaryHeap() {
		heap = new ArrayList<>();
	}

	/**
	 * Builds a binary heap from an array of elements. It loads the elements in the
	 * ArrayList and then call to the filterDown method for every item that is not a
	 * leaf
	 */
	public BinaryHeap(T[] elements) {
		this();
		Collections.addAll(heap, elements);

		for (int i = heap.size() - 1; i >= 0; i--) {
			filterDown(i);
		}

	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public void print() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return heap.toString();
	}

	/**
	 * Executes ascending filtering on the node referred by a given position in the
	 * ArrayList
	 * <p>
	 * Repeat until pos reaches the root (slot 0 in the ArrayList) or until its
	 * value is greater than that of its father
	 * <p>
	 * If the item is lower than its father (placed in slot E[(i-1)/2]) swap their
	 * positions
	 */
	private void filterUp(int pos) {
		if ((pos >= heap.size() || pos < 0)) {
			throw new IndexOutOfBoundsException("The position must be between [0, size of heap]");
		}
		if (pos != 0) {
			T root = heap.get(0);
			T element = heap.get(pos);
			int fatherPos = (pos - 1) / 2;
			T father = heap.get(fatherPos);

			while (father == null || (pos != 0 && (element.compareTo(father) <= -1))) {
//				heap.set(fatherPos, element);
//				heap.set(pos, father);
				Collections.swap(heap, pos, fatherPos);

				pos = fatherPos;
				fatherPos = (pos - 1) / 2;
				father = heap.get(fatherPos);
			}
		}
	}

	/**
	 * Place the element at the end of the heap (ArrayList) and apply the filterUp
	 * method
	 */
	public void add(T element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		heap.add(heap.size(), element);
		filterUp(heap.size() - 1);
	}

	/**
	 * Applies descending filtering to the node referred by a given position in the
	 * ArrayList
	 * <p>
	 * while (pos is not a leaf)
	 * <p>
	 * 1. Select the children of pos owning the smalles value
	 * <p>
	 * 2. If the value of the pos > value of the child {Collections.swap(heap,
	 * pos,child); pos=child;}
	 * <p>
	 * else{stop}
	 */
	private void filterDown(int pos) {
		if (pos >= heap.size() || pos < 0) {
			throw new IndexOutOfBoundsException("The position must be between [0, size of heap]");
		}
		int leftChildPos = 2 * pos + 1;
		int rightChildPos = 2 * pos + 2;
		T leftChild = null;
		T rightChild = null;

		if (leftChildPos < heap.size()) {
			leftChild = heap.get(leftChildPos);
		}

		if (rightChildPos < heap.size()) {
			rightChild = heap.get(rightChildPos);
		}
		T element = heap.get(pos);
		while (!(leftChild == null && rightChild == null)) {
			int minChildPos = 0;
			T minChild = null;

			if (leftChild == null) {
				minChild = rightChild;
				minChildPos = rightChildPos;
			} else if (rightChild == null) {
				minChild = leftChild;
				minChildPos = leftChildPos;
			} else {
				int comparison = leftChild.compareTo(rightChild);
				if (comparison >= 1) {
					minChild = rightChild;
					minChildPos = rightChildPos;
				} else {
					minChild = leftChild;
					minChildPos = leftChildPos;
				}
			}

			if (element.compareTo(minChild) >= 1) {
				Collections.swap(heap, pos, minChildPos);
				pos = minChildPos;
				leftChildPos = 2 * pos + 1;
				rightChildPos = 2 * pos + 2;
				leftChild = null;
				rightChild = null;

				if (leftChildPos < heap.size()) {
					leftChild = heap.get(leftChildPos);
				}
				if (rightChildPos < heap.size()) {
					rightChild = heap.get(rightChildPos);
				}
				element = heap.get(pos);
			} else {
				break;
			}
		}
	}

	/**
	 * Remove method. Returns the maximum priority element (first element in the
	 * ArrayList)
	 * <p>
	 * Place the last element f the ArrayList in the heap's root and filter it down
	 */
	public T getMin() { // or remove
		if (isEmpty())
			return null;
		T minimum = heap.get(0);
		Collections.swap(heap, 0, heap.size() - 1);
		heap.remove(heap.size() - 1);
		if (!isEmpty())
			filterDown(0);

		return minimum;
	}
}
