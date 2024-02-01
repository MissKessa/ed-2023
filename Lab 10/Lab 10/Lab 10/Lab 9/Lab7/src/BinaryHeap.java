
import java.util.ArrayList;
import java.util.Collections;

public class BinaryHeap<T extends Comparable<T>> {
	private ArrayList<T> heap;

	public BinaryHeap() {
		heap = new ArrayList<>();
	}

	public BinaryHeap(T[] elements) {
		this();
		Collections.addAll(heap, elements);

		for (int i = heap.size() - 1; i >= 0; i--) {
			int leftChildPos = 2 * i + 1;
			int rightChildPos = 2 * i + 2;
			T leftChild = null;
			T rightChild = null;

			if (leftChildPos < heap.size()) {
				leftChild = heap.get(leftChildPos);
			}
			if (rightChildPos < heap.size()) {
				rightChild = heap.get(rightChildPos);
			}
			if (leftChild == null && rightChild == null)
				continue;
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

	public void add(T element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		heap.add(heap.size(), element);
		filterUp(heap.size() - 1);
	}

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
