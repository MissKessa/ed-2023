

public class AVLNode <T>{
	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;
	
	private int height; //node's height
	
	
	
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
		//return getElement().toString()+"("+getHeight()+")";
		return getElement().toString()+"("+getBF()+")";
	}
	
	public void print() {
		System.out.println(toString());
	}



	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void updateHeight() {	
		if (getLeft()==null && getRight()==null) {
			setHeight(0);
			
		} else if (getLeft()==null) {
			setHeight(getRight().getHeight()+1);
			
		} else if (getRight()==null) {
			setHeight(getLeft().getHeight()+1);
			
		} else {
			setHeight(Math.max(getLeft().getHeight(), getRight().getHeight())+1);
		}
	}
	
	public int getBF() {
		if (getRight()==null && getLeft()==null)
			return 0;
		
		else if (getRight()==null)
			return -1 * getHeight();
		
		else if (getLeft()==null)
			return getHeight();
		
		return getRight().getHeight()-getLeft().getHeight();
	}

	
	
}
