package avl;

public class AVLNode <T>{
	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;
	
	
	
	public AVLNode(T element) {
		setElement(element);
		left=null;
		right=null;
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
		if (element==null)
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
	
	public String toString() {
		
		return getElement().toString();
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	
}
