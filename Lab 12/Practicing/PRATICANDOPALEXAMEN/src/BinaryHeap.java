
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {
	private ArrayList<T> heap;

	public BinaryHeap() {
		heap = new ArrayList<T>();
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

	private void p_filterUp(int pos) {
		if (pos >= heap.size() || pos < 0) {
			throw new IllegalArgumentException();
		}

		T node = heap.get(pos);

		while (pos != 0) {
			int posParent = (pos - 1) / 2;
			T parent = heap.get(posParent);
			if (parent.compareTo(node) < 0) {
				break;
			}

			Collections.swap(heap, pos, posParent);
			pos = posParent;
			node = heap.get(pos);
		}
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

	public void p_add(T element) {
		if (element == null)
			throw new NullPointerException();
		heap.add(element);
		filterUp(heap.size() - 1);
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

	private void p_filterDown(int pos) {
		if (pos < 0 || pos >= heap.size()) {
			throw new IllegalArgumentException();
		}

		// T node = heap.get(pos);
		int posLeft = 2 * pos + 1;
		int posRight = 2 * pos + 2;
		T left = null;
		T right = null;

		while (pos < heap.size()) {

			int posMinChild = -1;
			T minChild = null;
			if (posRight < heap.size()) {
				left = heap.get(posLeft);
				right = heap.get(posRight);

				int comparisonChildren = left.compareTo(right);

				if (comparisonChildren > 0) {
					posMinChild = posRight;
					minChild = right;

				} else if (comparisonChildren < 0) {
					posMinChild = posLeft;
					minChild = left;
				}
			} else if (posLeft < heap.size() && posRight >= heap.size()) {
				minChild = heap.get(posLeft);
				posMinChild = posLeft;
			} else {
				break;
			}
			if (heap.get(pos).compareTo(minChild) < 0)
				break;
			Collections.swap(heap, pos, posMinChild);

			pos = posMinChild;
			posLeft = 2 * pos + 1;
			posRight = 2 * pos + 2;
			// node = heap.get(pos);

		}
	}

	/**
	 * Applies descending filtering to the node referred by a given position in the
	 * ArrayList
	 * <p>
	 * while (pos is not a leaf)
	 * <p>
	 * 1. Select the children of pos owning the smallest value
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

	/*
	 * Returns the ancestors of the given element. An ancestor is, the father of a
	 * node or the father of an ancestor of a node
	 */
	public List<T> getAncestors(T elem) { // E[(i-1)/2]
		List<T> elements = new ArrayList<>();
		List<Integer> positions = new ArrayList<>();
		int posElem = -1;
		for (int i = 0; i < heap.size(); i++) {
			if (heap.get(i).compareTo(elem) == 0) {
				posElem = i;

			}
		}

		int posFather = (posElem - 1) / 2;
		while (posFather > 0 && posElem != 0) {
			positions.add(posFather);
			posFather = (posFather - 1) / 2;
		}
		if (posElem != 0) {
			positions.add(0);
		}

		for (int i = positions.size() - 1; i >= 0; i--) {
			elements.add(heap.get(positions.get(i)));
		}
		return elements;
	}

	/*
	 * Returns the elements in the heap following the preorder- depth first
	 * traversal
	 */
	public List<T> DFTraversal() {
		List<T> traverse = new ArrayList<T>();
		DFTraversal(0, traverse);
		return traverse;
	}

	private void DFTraversal(int pos, List<T> traverse) {
		if (pos < heap.size()) {
			DFTraversal(2 * pos + 1, traverse);
			traverse.add(heap.get(pos));
			DFTraversal(2 * pos + 2, traverse);
		}
	}

	/*
	 * Método que devuelva una lista con todos los hijos de un determinado nodo. La
	 * lista de hijos debe estar ordenada
	 */
	public List<T> hijos(T t) {
		List<T> elements = new ArrayList<>();
		T node = null;
		int pos = -1;
		for (int i = 0; i < heap.size(); i++) {
			T nodeInHeap = heap.get(i);
			if (nodeInHeap.compareTo(t) == 0) {
				node = nodeInHeap;
				pos = i;
			}
		}

		addChild(2 * pos + 1, elements);
		addChild(2 * pos + 2, elements);
		Collections.sort(elements);
		return elements;

	}

	private void addChild(int pos, List<T> elements) {
		if (pos <= heap.size() - 1) {
			elements.add(heap.get(pos));
			addChild(2 * pos + 1, elements);
			addChild(2 * pos + 2, elements);
		}

	}

	/*
	 * Método que calcule el % de nodos intermedios con respecto al número total de
	 * nodos, 0 si no hay ningún nodo en el árbol. Un nodo es intermedio si no es la
	 * raíz y tiene hijos.
	 */
	public double intermediateNodesPercentage() {
		double percentage = 0;
		int intermediateNodes = 0;
		int totalNumberOfNodes = heap.size();
		if (totalNumberOfNodes == 0) {
			return 0;
		}

		for (int i = 0; i < heap.size(); i++) { // you can do it with half heap
			int leftChildPos = 2 * i + 1;
			int rightChildPos = 2 * i + 2;
			if (i != 0 && !(leftChildPos >= heap.size() && rightChildPos >= heap.size())) {
				intermediateNodes++;
			}
			// !(heap.get(2 * i + 1) == null && heap.get(2 * i + 2) == null)
		}

		percentage = (double) intermediateNodes / totalNumberOfNodes;
		return percentage;
	}
}
