package graphs;

public class ElementNotPresentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ElementNotPresentException(Object element) {
		super("Element " + element + " is not present in the current structure.");
	}

	public ElementNotPresentException(String message) {
		super(message);
	}

}
