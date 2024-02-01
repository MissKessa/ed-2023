package avl;

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
			} else { // root > element
				root.setRight(add(element, root.getRight()));
			}
		}

//		root.updateHeight();
//		return root;
		return updateBF(root);
	}

	@Override
	public String toString() { // Preorder traversing
		return toString(root);
	}

	private String toString(AVLNode<T> node) {
		if (node == null)
			return "-";
		return node.toString() + toString(node.getLeft()) + toString(node.getRight());
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

	protected T getMax(AVLNode<T> theRoot) {
		if (theRoot == null)
			throw new NullPointerException("The element cannot be null");

		if (theRoot.getRight() == null)
			return theRoot.getElement();
		return getMax(theRoot.getRight());

	}

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
}
