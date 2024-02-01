import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> {
	private AVLNode<T> root;

	public AVLTree() {
		setRoot(null);
	}

	public void setRoot(AVLNode<T> root) {
		this.root = root;
	}

	public AVLNode<T> getRoot() {
		return root;
	}

	public void p_add(T element) {
		if (element == null) {
			throw new NullPointerException("The element cannot be null");
		}

		this.root = p_add(root, element);

	}

	public AVLNode<T> p_add(AVLNode<T> node, T element) {
		if (node == null) {
			node = new AVLNode<T>(element);
		} else {

			int comparison = node.getElement().compareTo(element);

			if (comparison == 0) {
				throw new IllegalArgumentException("The element already exists");
			} else if (comparison > 0) {
				node.setLeft(p_add(node.getLeft(), element));
			} else {
				node.setRight(p_add(node.getRight(), element));
			}
		}

		return updateBF(node);
	}

	public void add(T element) { // recursive method
		if (element == null) {
			throw new NullPointerException("The element cannot be null");
		}
		this.root = add(element, root); // element is OK, we may add it
	}

	private AVLNode<T> add(T element, AVLNode<T> root) {
		if (root == null)
			root = new AVLNode<T>(element);
		else {
			int comparison = root.getElement().compareTo(element);

			if (comparison == 0) {
				throw new IllegalArgumentException("The element already exists");
			} else if (comparison >= 1) { /// root > element
				root.setLeft(add(element, root.getLeft()));
			} else { // root < element
				root.setRight(add(element, root.getRight()));
			}
		}

//		root.updateHeight();
//		return root;
		return updateBF(root);
	}

	/**
	 * Preorder traversing and prints a dash ('-') to represent null pointers
	 */
	@Override
	public String toString() { // Preorder traversing
		return toString(root);
	}

	private String toString(AVLNode<T> node) {
		if (node == null)
			return "-";
		return node.toString() + toString(node.getLeft()) + toString(node.getRight());
	}

	public boolean p_search(T element) { // recursive method
		if (element == null) {
			throw new NullPointerException("The element cannot be null");
		}
		return p_search(element, root);
	}

	private boolean p_search(T element, AVLNode<T> node) {
		if (node == null)
			return false;
		int comparison = node.getElement().compareTo(element);

		if (comparison == 0) {
			return true;

		} else if (comparison >= 1) { /// root > element
			return p_search(element, node.getLeft());
		} else { // root < element
			return p_search(element, node.getRight());
		}

	}

	public boolean search(T element) {
		if (element == null) {
			throw new NullPointerException("The element cannot be null");
		}
		return search(element, root);
	}

	private boolean search(T element, AVLNode<T> root) {
		if (root == null)
			return false;

		int comparison = root.getElement().compareTo(element);

		if (comparison == 0)
			return true;
		else if (comparison >= 1) /// root > element
			return search(element, root.getLeft());
		else // root > element
			return search(element, root.getRight());

	}

//	protected T p_getMax(AVLNode<T> theRoot) {
//		if (theRoot == null)
//			throw new NullPointerException();
//
//		AVLNode<T> rightChild = theRoot.getRight();
//		if (rightChild != null) {
//			return p_getMax(rightChild);
//		}
//		return theRoot.getElement();
//
//	}

	protected T getMax(AVLNode<T> theRoot) {
		if (theRoot == null)
			throw new NullPointerException("The element cannot be null");

		if (theRoot.getRight() == null)
			return theRoot.getElement();
		return getMax(theRoot.getRight());

	}

	public void p_remove(T element) {
		if (element == null) {
			throw new NullPointerException();
		}
		this.root = p_remove(element, root);
	}

	private AVLNode<T> p_remove(T element, AVLNode<T> node) {
		if (node == null) {
			throw new IllegalArgumentException();
		}

		int comparison = node.getElement().compareTo(element);

		if (comparison == 0) {
			AVLNode<T> leftChild = node.getLeft();
			AVLNode<T> rightChild = node.getRight();

			if (leftChild == null) {
				node = rightChild;
			} else if (rightChild == null) {
				node = leftChild;
			} else {
				T maxElementLeft = getMax(leftChild);
				node.setLeft(p_remove(maxElementLeft, node.getLeft()));
				node.setElement(maxElementLeft);

			}

		} else if (comparison > 0) {
			node.setLeft(p_remove(element, node.getLeft()));
		} else {
			node.setRight(p_remove(element, node.getRight()));
		}
		if (node != null)
			node = updateBF(node);
		return node;

	}

	/**
	 * Find the element to be deleted
	 * <p>
	 * -If any of its subtrees (left/right) is null, returns the opposite subtree
	 * (to be assigned to it's father link)
	 * <p>
	 * -Else (the element has 2 children)
	 * <p>
	 * i. Replace the element with the max value obtained from its left subtree (use
	 * the getMax method for this)
	 * <p>
	 * ii. Delete the element from its left subtree (invoke to the recursive remove
	 * method again by this time provide its left subtree as the root's parameter)
	 */
	public void remove(T element) {
		if (element == null) {
			throw new NullPointerException("The element cannot be null");
		}
		remove(element, root);
	}

	private AVLNode<T> remove(T element, AVLNode<T> root) {
		if (root == null)
			throw new NullPointerException("The element cannot be null");

		int comparison = root.getElement().compareTo(element);

		if (comparison == 0) {
//			if (root.getLeft()==null && root.getRight()==null) {
//				root=null;

			if (root.getLeft() == null) {
				root = root.getRight();

			} else if (root.getRight() == null) {
				root = root.getLeft();

			} else {
//				T newRoot = getMax(root.getLeft());
//				remove(newRoot, root);
//				root.setElement(newRoot);
				root.setElement(getMax(root.getLeft()));
				root.setLeft(remove(root.getElement(), root.getLeft()));
			}

		} else if (comparison >= 1) { /// root > element
			root.setLeft(remove(element, root.getLeft()));
		} else { // root > element
			root.setRight(remove(element, root.getRight()));
		}
//		if (root != null)
//			root.updateHeight();
//		return root;
		if (root != null) {
			if (root.getElement().equals(this.getRoot().getElement())) {
				this.root = updateBF(root);
				return this.root;
			} else
				return updateBF(root);
		}
		return null;

	}

	public AVLTree<T> p_joins(AVLTree<T> tree) {
		if (tree == null) {
			throw new NullPointerException();
		}
		p_joins(tree.getRoot());
		return this;

	}

	private void p_joins(AVLNode<T> nodeToJoin) {

		if (nodeToJoin != null) {
			try {
				add(nodeToJoin.getElement());
			} catch (IllegalArgumentException e) {

			}
			p_joins(nodeToJoin.getLeft());
			p_joins(nodeToJoin.getRight());

			updateBF(nodeToJoin);

		}

	}

	/**
	 * Merges the contents of the BST/AVL tree with the elements of a second one
	 */
	public AVLTree<T> joins(AVLTree<T> tree) {
		join(tree.getRoot());
//		root.updateHeight();
//		return this;
		this.root = updateBF(root);
		return this;

	}

	private void join(AVLNode<T> root) {
		if (root != null) {
			if (!search(root.getElement()))
				add(root.getElement());

			if (root.getLeft() != null) {
				join(root.getLeft());
			}
			if (root.getRight() != null) {
				join(root.getRight());
			}

		}
	}

	/**
	 * Intersects the contents of the BST/AVL tree with the elements of a second one
	 */
	public AVLTree<T> p_intersection(AVLTree<T> tree) {
		AVLTree<T> intersected = new AVLTree<T>();
		p_intersection(intersected, tree.getRoot());
		return intersected;
	}

	private void p_intersection(AVLTree<T> intersected, AVLNode<T> node) {
		if (node != null) {
			if (search(node.getElement())) {
				intersected.add(node.getElement());
			}

			p_intersection(intersected, node.getLeft());
			p_intersection(intersected, node.getRight());
		}
	}

	/**
	 * Intersects the contents of the BST/AVL tree with the elements of a second one
	 */
	public AVLTree<T> intersection(AVLTree<T> tree) {
		AVLTree<T> intersection = new AVLTree<T>();
		intersection(tree.getRoot(), intersection);
		return intersection;
	}

	private void intersection(AVLNode<T> rootTree, AVLTree<T> intersection) {
		if (rootTree != null) {
			if (search(rootTree.getElement())) {
				intersection.add(rootTree.getElement());
			}
			intersection(rootTree.getLeft(), intersection);
			intersection(rootTree.getRight(), intersection);
		}
	}

	private AVLNode<T> p_updateBF(AVLNode<T> node) {
		node.updateHeight();
		if (node.getBF() == -2) {
			AVLNode<T> leftChild = node.getLeft();
			if (leftChild.getBF() >= 1) {
				node = p_doubleLeftRotation(node);
			} else {
				node = p_singleLeftRotation(node);
			}
		} else if (node.getBF() == 2) {
			AVLNode<T> rightChild = node.getRight();
			if (rightChild.getBF() <= -1) {
				node = p_doubleRightRotation(node);
			} else {
				node = p_singleRightRotation(node);
			}
		}
		return node;
	}

	private AVLNode<T> p_doubleLeftRotation(AVLNode<T> c) {
		AVLNode<T> a = c.getLeft();
		AVLNode<T> b = a.getRight();

		c.setLeft(b.getRight());
		a.setRight(b.getLeft());

		b.setLeft(a);
		b.setRight(c);

		a.updateHeight();
		c.updateHeight();
		b.updateHeight();
		return b;
	}

	private AVLNode<T> p_singleLeftRotation(AVLNode<T> b) {
		AVLNode<T> a = b.getLeft();
		AVLNode<T> x = a.getRight();

		a.setRight(b);
		b.setLeft(x);

		b.updateHeight();
		a.updateHeight();

		return a;
	}

	private AVLNode<T> p_doubleRightRotation(AVLNode<T> c) {
		AVLNode<T> a = c.getRight();
		AVLNode<T> b = a.getLeft();

		c.setRight(b.getLeft());
		a.setLeft(b.getRight());

		b.setRight(a);
		b.setLeft(c);

		a.updateHeight();
		c.updateHeight();
		b.updateHeight();
		return b;
	}

	private AVLNode<T> p_singleRightRotation(AVLNode<T> b) {
		AVLNode<T> a = b.getRight();
		AVLNode<T> x = a.getLeft();

		a.setLeft(b);
		b.setRight(x);

		b.updateHeight();
		a.updateHeight();

		return a;
	}

	/**
	 * Uses backtracking recursion to update the BF of each node in the search path
	 * in order to balance the tree.
	 * <p>
	 * It detects nodes unbalanced as well as to balance the tree
	 */
	private AVLNode<T> updateBF(AVLNode<T> theRoot) {
		theRoot.updateHeight();
		if (theRoot.getBF() == -2) {// Left rotation
			if (theRoot.getLeft().getBF() <= 0)
				theRoot = singleLeftRotation(theRoot);
			else
				theRoot = doubleLeftRotation(theRoot);

		} else if (theRoot.getBF() == 2) {// Right rotation
			if (theRoot.getRight().getBF() >= 0)
				theRoot = singleRightRotation(theRoot);
			else
				theRoot = doubleRightRotation(theRoot);
		}
		return theRoot;
	}

	private AVLNode<T> singleLeftRotation(AVLNode<T> b) {
		AVLNode<T> a = b.getLeft();
		b.setLeft(a.getRight());
		a.setRight(b);

		b.updateHeight();
		a.updateHeight();
		return a;
	}

	private AVLNode<T> singleRightRotation(AVLNode<T> b) {
		AVLNode<T> a = b.getRight();
		b.setRight(a.getLeft());
		a.setLeft(b);

		b.updateHeight();
		a.updateHeight();
		return a;
	}

	private AVLNode<T> doubleLeftRotation(AVLNode<T> c) {
		AVLNode<T> a = c.getLeft();
		AVLNode<T> b = a.getRight();

		a.setRight(b.getLeft());
		c.setLeft(b.getRight());

		b.setLeft(a);
		b.setRight(c);

		c.updateHeight();
		a.updateHeight();
		b.updateHeight();
		return b;
	}

	private AVLNode<T> doubleRightRotation(AVLNode<T> c) {
		AVLNode<T> a = c.getRight();
		AVLNode<T> b = a.getLeft();

		a.setLeft(b.getRight());
		c.setRight(b.getLeft());

		b.setRight(a);
		b.setLeft(c);

		c.updateHeight();
		a.updateHeight();
		b.updateHeight();
		return b;
	}

	/**
	 * Explores the tree from its root return its height by not using the property
	 * in the node
	 */
	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(AVLNode<T> root) {

		if (root == null) {
			return 0;
		}

		int heightLeft = 1;
		int heightRight = 1;

		if (root.getLeft() != null) {
			heightLeft += getHeight(root.getLeft());
		}
		if (root.getRight() != null) {
			heightRight += getHeight(root.getRight());
		}
		return Math.max(heightLeft, heightRight);
	}

	/*
	 * Returns the average factor of all the nodes in the tree
	 */
	public double balanceFactorMean() {
		double sumBF = balanceFactorMean(root);
		int numberNodes = getNumberNodes(root);
		return sumBF / numberNodes;
	}

	private int getNumberNodes(AVLNode<T> root) {
		int nodes = 0;
		if (root != null) {
			nodes = 1;
			nodes += getNumberNodes(root.getLeft());
			nodes += getNumberNodes(root.getRight());
		}
		return nodes;
	}

	private double balanceFactorMean(AVLNode<T> root) {
		double sumBF = 0;
		if (root != null) {
			sumBF = root.getBF();
			sumBF += balanceFactorMean(root.getLeft());
			sumBF += balanceFactorMean(root.getRight());

		}
		return sumBF;

	}

	/*
	 * Returns the element stored in its brother (the other child of its parent) <p>
	 * If the element is not in the tree, an exception should be raised <p> If the
	 * element has no brother, should return null
	 * 
	 */
	public T getBrother(T elem) {
		return getBrother(root, elem);
	}

	private T getBrother(AVLNode<T> root, T elem) {
		if (elem == null)
			throw new NullPointerException("The element doesn't exist");

		AVLNode<T> brother = null;
		if (root.getLeft() != null && root.getLeft().getElement().compareTo(elem) == 0) {
			brother = root.getRight();
		} else if (root.getRight() != null && root.getRight().getElement().compareTo(elem) == 0) {
			brother = root.getLeft();
		} else {
			if (root.getElement().compareTo(elem) > 0) {
				return getBrother(root.getLeft(), elem);
			} else if (root.getElement().compareTo(elem) < 0) {
				return getBrother(root.getRight(), elem);
			}
		}
		if (brother != null)
			return brother.getElement();

		return null;
	}

	/*
	 * Returns all the nodes in the specified level of the tree, if it the level
	 * one, only the root should be returned
	 */
	public List<T> getElementsInLevel(int depth) {
		List<T> elements = new ArrayList<T>();
		List<AVLNode<T>> toProcess = new ArrayList<>();
		toProcess.add(root);
		while (depth != 0) {
			depth--;
			if (depth == 0) {
				for (AVLNode<T> node : toProcess) {
					elements.add(node.getElement());
				}
			} else {
				List<AVLNode<T>> childrenToProcess = new ArrayList<>();
				for (AVLNode<T> node : toProcess) {
					childrenToProcess.add(node.getLeft());
					childrenToProcess.add(node.getRight());
				}
				toProcess = childrenToProcess;

			}
		}
		return elements;
	}

	/*
	 * Returns the ancestors of a given element. An ancestor is the father of a node
	 * or the father of an ancestor
	 */
	public List<T> getAncestors(T elem) {
		List<T> elements = new ArrayList<T>();
		AVLNode<T> node = root;

		while (node != null && node.getElement().compareTo(elem) != 0) {
			if (node.getElement().compareTo(elem) > 0) {
				elements.add(node.getElement());
				node = node.getLeft();
			} else {
				elements.add(node.getElement());
				node = node.getRight();
			}
		}
		return elements;
	}

	/*
	 * Método que devuelva una lista con todos los hijos de un determinado nodo. La
	 * lista de hijos debe estar ordenada
	 */
	public List<T> hijos(T t) {
		List<T> elements = new ArrayList<>();
		// List<AVLNode<T>> toProcess = new ArrayList<>();
		AVLNode<T> node = root;
		while (node != null && node.getElement().compareTo(t) != 0) {
			if (node.getElement().compareTo(t) > 0) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		if (node != null) {
			addChilds(node.getLeft(), elements);
			// no add the node
			addChilds(node.getRight(), elements);
		}
		return elements;
	}

	private void addChilds(AVLNode<T> node, List<T> elements) {
		if (node != null) { // Preorder notation
			addChilds(node.getLeft(), elements);
			elements.add(node.getElement());
			addChilds(node.getRight(), elements);
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
		int totalNumberOfNodes = 0;

		List<AVLNode<T>> nodesToProcess = new ArrayList<AVLNode<T>>();
		nodesToProcess.add(root);
		AVLNode<T> node = root;
		if (root == null)
			return 0;
		while (!nodesToProcess.isEmpty()) {
			node = nodesToProcess.get(0);
			totalNumberOfNodes++;

			AVLNode<T> leftChild = node.getLeft();
			AVLNode<T> rightChild = node.getRight();

			if (!root.equals(node) && !(leftChild == null && rightChild == null)) {
				intermediateNodes++;
			}

			if (leftChild != null)
				nodesToProcess.add(leftChild);
			if (rightChild != null)
				nodesToProcess.add(rightChild);
			nodesToProcess.remove(0);

		}
		percentage = (double) intermediateNodes / totalNumberOfNodes;
		return percentage;
	}
}
