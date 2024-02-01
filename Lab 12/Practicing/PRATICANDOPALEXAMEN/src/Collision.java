
public class Collision {
	public enum OperationType {
		ADD, REMOVE, SEARCH;
	}

	private OperationType operation;
	private int numberOfCollisions;

	public Collision(Collision.OperationType operation, int numberOfCollisions) {
		this.operation = operation;
		this.numberOfCollisions = numberOfCollisions;
	}

	public OperationType getOperation() {
		return operation;
	}

	public int getNumberOfCollisions() {
		return numberOfCollisions;
	}

}
