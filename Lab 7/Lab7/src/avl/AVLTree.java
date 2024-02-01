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



	public void add(T element){ //recursive method
		if (element==null) {
			throw new NullPointerException("The element cannot be null");
		}
		this.root= add(element, root); //element is OK, we may add it
	}

	private AVLNode<T> add(T element, AVLNode<T> root) {
		if (root==null)
			root = new AVLNode<T>(element);
		else {
			int comparison= root.getElement().compareTo(element);
		
			if (comparison==0) {
				throw new IllegalArgumentException("The element already exists");
			} else if (comparison==1) { ///root < element
				root.setLeft(add(element,root.getLeft()));
			} else { //root > element
				root.setRight(add(element,root.getRight()));
			}
		}
		
		return root;
	}
	
	public String toString() { //Preorder traversing
		return toString(root);
	}
	
	private String toString(AVLNode<T> node) {
		if (node == null)
			return "-";
		return node.toString()+toString(node.getLeft())+toString(node.getRight());
	}
	
	public boolean search (T element) {
		if (element==null) {
			throw new NullPointerException("The element cannot be null");
		}
		return search(element, root);
	}

	private boolean search(T element, AVLNode<T> root) {
		if (root==null)
			return false;
		
		int comparison= root.getElement().compareTo(element);
	
		if (comparison==0) 
			return true;
		else if (comparison==1)  ///root < element
			return search(element,root.getLeft());
		else //root > element
			return search(element,root.getRight());
		
	}
	
	protected T getMax(AVLNode<T> theRoot) {
		if (theRoot == null)
			throw new NullPointerException("The element cannot be null");
		
		if(theRoot.getRight()==null)
			return theRoot.getElement();
		return getMax(theRoot.getRight());
		
	}
	
	
	
}
