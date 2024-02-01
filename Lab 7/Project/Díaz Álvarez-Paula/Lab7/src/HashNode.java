
public class HashNode<T> {
	public final static int EMPTY = 0;
	public final static int VALID = 1;
	public final static int DELETED = 2;

	private T element;
	private int status;

	public HashNode() {
		setStatus(EMPTY);
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "(" + getStatus() + ") = " + getElement();
	}

	public boolean isValid() {
		if (getStatus() == VALID)
			return true;
		return false;
	}

	public boolean isEmpty() {
		if (getStatus() == EMPTY)
			return true;
		return false;
	}

}
