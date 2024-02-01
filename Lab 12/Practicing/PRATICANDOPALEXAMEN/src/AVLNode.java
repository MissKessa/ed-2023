
public class AVLNode<T> {
	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;

	private int height; // node's height

	public AVLNode(T element) {
		setElement(element);
		left = null;
		right = null;
	}

	public AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
		this(element);
		setLeft(left);
		setRight(right);
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		if (element == null)
			throw new NullPointerException("The element cannot be null");
		this.element = element;
	}

	public AVLNode<T> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	public AVLNode<T> getRight() {
		return right;
	}

	public void setRight(AVLNode<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		// return getElement().toString()+"("+getHeight()+")";
		return getElement().toString() + "(" + getBF() + ")";
	}

	public void print() {
		System.out.println(toString());
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * // * Updates the node.height using the relative height from its children to
	 * obtain // * it //
	 */
	public void p_updateHeight() {
		if (left == null && right != null) {
			setHeight(right.getHeight() + 1);
		} else if (left != null && right == null) {
			setHeight(left.getHeight() + 1);
		} else if (left == null && right == null) {
			setHeight(1);
		} else {
			setHeight(Math.max(left.getHeight(), right.getHeight()) + 1);
		}

	}

	/**
	 * // * Returns the balance factor for each node by using the left and right
	 * subtrees // * of the node //
	 */
	public int p_getBF() { // AVL node's BF must be in {-1, 0, 1}
		if (left == null && right == null) {
			return 0;
		} else if (left != null && right == null) {
			return -1 * left.getHeight();
		} else if (left == null && right != null) {
			return right.getHeight();
		}
		return right.getHeight() - left.getHeight();
	}

	/**
	 * Updates the node.height using the relative height from its children to obtain
	 * it
	 */
	public void updateHeight() {
		if (getLeft() == null && getRight() == null) {
			setHeight(0);

		} else if (getLeft() == null) {
			setHeight(getRight().getHeight() + 1);

		} else if (getRight() == null) {
			setHeight(getLeft().getHeight() + 1);

		} else {
			setHeight(Math.max(getLeft().getHeight(), getRight().getHeight()) + 1);
		}
	}

	/**
	 * Returns the balance factor for each node by using the left and right subtrees
	 * of the node
	 */
	public int getBF() { // AVL node's BF must be in {-1, 0, 1}
		if (getRight() == null && getLeft() == null)
			return 0;

		else if (getRight() == null)
			return -1 * getHeight();

		else if (getLeft() == null)
			return getHeight();

		return getRight().getHeight() - getLeft().getHeight();
	}

}
