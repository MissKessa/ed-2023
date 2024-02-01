package graphs;

public class FullStructureException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FullStructureException(Object element) {
		super("Element " + element.toString() + " could not be inserted since the data structure is full.");
	}

	public FullStructureException(Object element, Object dataStructure) {
		super("Element " + element.toString() + " could not be inserted. Data structure full:\n "
				+ dataStructure.toString());
	}

	public FullStructureException(String message) {
		super(message);
	}
}
