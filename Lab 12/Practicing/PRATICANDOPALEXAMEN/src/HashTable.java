import java.util.ArrayList;
import java.util.List;

public class HashTable<T> {
	protected final static int LINEAR_PROBING = 0;
	protected final static int QUADRATIC_PROBING = 1;
	protected final static int DOUBLE_HASHING = 2;
	public final static int ATTEMPTS = 20;

	private int B;
	private int redispersionType;
	private double minLF;

	private ArrayList<HashNode<T>> associativeArray;
	private double n; // number of elements

	// It's the previous prime number of B
	private int R;
	private int a = 0; // Numero de veces que se ha llamado a add, se ha añadido el elemento y ocurrió
						// al menos una colisión
	private int b = 0; // número de veces que se ha llamado a add y se ha añadido el elemento

	private List<Collision> totalCollisions = new ArrayList<>();

	public HashTable() {
		this(7, LINEAR_PROBING, 0.5);
	}

	public HashTable(int B, int redispersionType, double minLF) {
		this.B = B;
		if (redispersionType != LINEAR_PROBING && redispersionType != QUADRATIC_PROBING
				&& redispersionType != DOUBLE_HASHING)
			throw new IllegalArgumentException("The redispersion Type must be one of the ones defined");

		this.redispersionType = redispersionType;
		this.minLF = minLF;

		// Reserve memory for the ArrayList and initialise it inserting B empty
		// HashNodes
		this.associativeArray = new ArrayList<>(B);
		for (int i = 0; i < B; i++) {
			associativeArray.add(new HashNode<T>());
		}
		this.R = getPrevPrimeNumber(B);
	}

//	protected int p_f(T element, int i) {
//		if (i < 0 || element == null)
//			throw new IllegalArgumentException();
//
//		int x = element.hashCode();
//		switch (redispersionType) {
//		case LINEAR_PROBING:
//			return (x + i) % B;
//		case QUADRATIC_PROBING:
//			return (x + i * i) % B;
//		case DOUBLE_HASHING:
//			return (x + i * (R - x % R)) % B;
//		default:
//			throw new IllegalStateException();
//		}
//
//	}

	/**
	 * Hashing function
	 * 
	 * @param element to be storeed
	 * @param i       attempt number
	 * @return slot in the array where the element should be placed
	 */
	protected int f(T element, int i) {
		if (element == null) {
			throw new NullPointerException("The element must be not null");
		}

		if (i < 0) {
			throw new IllegalArgumentException("The number of attemps cannot be a negative number");
		}
		int x = Math.abs(element.hashCode());

		switch (redispersionType) {
		case LINEAR_PROBING:
			return (x + i) % B; // hasCode might return a negative number
		case QUADRATIC_PROBING:
			return (x + i * i) % B;
		case DOUBLE_HASHING:
			return (x + i * (R - x % R)) % B;
		}
		return -1;

	}

	public void print() {
		System.out.println(toString());
	}

	@Override
	public String toString() { // slot = bucket
		String result = "";
		for (int i = 0; i < associativeArray.size(); i++) {
			result += "[" + i + "] " + associativeArray.get(i).toString() + " - ";
		}
		return result;
	}

	public double getLF() {
		return n / B;
	}

	public void p_add(T element) {
		if (element == null)
			throw new NullPointerException("The element must be not null");

		int i = 0;
		while (i < ATTEMPTS) {
			int pos = f(element, i);
			HashNode<T> node = associativeArray.get(pos);

			if (!element.equals(node.getElement()))
				throw new IllegalArgumentException();
			if (!node.isValid()) {
				node.setElement(element);
				node.setStatus(HashNode.VALID);
				n++;
				break;
			}
			i++;
		}
		if (getLF() > minLF) {
			dynamicResize();
		}
	}

	public void add(T element) {
		if (element == null)
			throw new NullPointerException("The element must be not null");

		int i = 0;
		while (i < ATTEMPTS) {
			HashNode<T> node = associativeArray.get(f(element, i));

			if (element.equals(node.getElement()))
				throw new IllegalArgumentException("The element is already in the hashTable");

			else if (!node.isValid()) {
				node.setElement(element);
				node.setStatus(HashNode.VALID);
				n++;
				b++;
				if (i >= 1)
					a++;
				totalCollisions.add(new Collision(Collision.OperationType.ADD, i));

				if (getCollisionProbabilityAdd() > 0.7) {
					dynamicResize();
					a = 0;
					b = 0;
				}
				break;
			}
			i++;

		}
		if (getLF() > minLF) {
			dynamicResize();
		}

	}

	public boolean p_search(T element) {
		if (element == null)
			throw new NullPointerException("The element must be not null");

		int i = 0;
		while (i < ATTEMPTS) {
			int pos = f(element, i);
			HashNode<T> node = associativeArray.get(pos);

			if (node.isValid() && element.equals(node.getElement()))
				return true;
			else if (node.isEmpty())
				return false;
			i++;
		}
		return false;
	}

	public boolean search(T element) {
		if (element == null)
			throw new NullPointerException("The element must be not null");

		int i = 0;
		while (i < ATTEMPTS) {
			HashNode<T> node = associativeArray.get(f(element, i));
			if (element.equals(node.getElement()) && node.isValid()) {
				totalCollisions.add(new Collision(Collision.OperationType.SEARCH, i));
				return true;
			} else if (node.isEmpty())
				return false;

			i++;

		}
		return false;

	}

	public void p_remove(T element) {
		if (element == null)
			throw new NullPointerException("The element must be not null");

		int i = 0;
		while (i < ATTEMPTS) {
			HashNode<T> node = associativeArray.get(f(element, i));
			if (element.equals(node.getElement())) {
				if (node.isValid()) {
					node.setStatus(HashNode.DELETED);
					n--;
					break;
				}
			} else if (node.isEmpty())
				throw new IllegalArgumentException(); // not in table

			i++;
		}

	}

	/**
	 * Remove method using the Lazy Deletion
	 */
	public void remove(T element) {
		if (element == null)
			throw new NullPointerException("The element must be not null");

		int i = 0;
		while (i < ATTEMPTS) {
			HashNode<T> node = associativeArray.get(f(element, i));

			if (element.equals(node.getElement()) && node.isValid()) {
				totalCollisions.add(new Collision(Collision.OperationType.SEARCH, i));
				node.setStatus(HashNode.DELETED);
				n--;
				break;
			} else if (node.isEmpty())
				throw new IllegalArgumentException("The element isn't in the hashTable");

			i++;
		}

	}

	/**
	 * Verifies whether a given number is a prime number
	 */
	private boolean isPrime(int number) { // O(n) worst case - is prime, O(1) best case (It's divisible by 2)
		if (number == 0)
			return false;

		if (number < 0)
			number *= -1;

		for (int i = 2; i < number / 2; i++) { // better with number/2 or square root of number as limit
			if (number % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * Returns the prime number predecessor of a given int number
	 */
	public int getPrevPrimeNumber(int number) { // O(nlog(n))
		boolean[] isNotPrime = new boolean[number]; // prime = false, not prime = true

		for (int i = 0; i < isNotPrime.length; i++) {
			int value = i;
			if (i == 0 || i == 1) {
				isNotPrime[i] = true;
				continue;
			}
			if (!isNotPrime[i]) {
				for (int j = i * i; j < isNotPrime.length; j += value) {
					isNotPrime[j] = true;
				}
			}
		}

		for (int i = isNotPrime.length - 1; i >= 0; i--) {
			if (!isNotPrime[i]) {
				return i;
			}
		}
		throw new RuntimeException("There is no more prime numbers");
	}

	/**
	 * Returns the prime number successor of a given int number
	 */
	public int getNextPrimeNumber(int number) { // O(nlog(n))
		boolean[] isNotPrime = new boolean[number * 2 + 1]; // prime = false, not prime = true

		for (int i = 0; i < isNotPrime.length; i++) {
			int value = i;

			if (i == 0 || i == 1) {
				isNotPrime[i] = true;
				continue;
			}
			if (!isNotPrime[i]) {
				for (int j = i * i; j < isNotPrime.length; j += value) {
					isNotPrime[j] = true;
				}
			}
		}

		for (int i = number + 1; i < isNotPrime.length; i++) {
			if (!isNotPrime[i]) {
				return i;
			}
		}
		throw new RuntimeException("There is no more prime numbers");
	}

	/**
	 * @return a new array after the dynamic resizing
	 */
	public ArrayList<HashNode<T>> getAssociativeArray() {
		return associativeArray;
	}

	private void p_dynamicResize() {
		p_dynamicResize(getNextPrimeNumber(B * 2));
	}

	private void p_dynamicResize(int newB) {
		HashTable<T> resized = new HashTable<>(newB, redispersionType, minLF);

		for (HashNode<T> node : associativeArray)
			if (node.getElement() != null && node.isValid())
				resized.add(node.getElement());

		associativeArray = resized.associativeArray;
		B = newB;
		R = getPrevPrimeNumber(newB);
	}

	/**
	 * Resizes the table dynamically to the given size
	 * 
	 * @param newSize
	 */
	private void dynamicResize(int newSize) {
		HashTable<T> resized = new HashTable<>(newSize, redispersionType, minLF);
		for (HashNode<T> node : this.associativeArray) {
			if (node.getElement() != null && node.isValid())
				resized.add(node.getElement());
		}

		this.associativeArray = resized.getAssociativeArray();
		this.B = newSize;
		this.R = getPrevPrimeNumber(B);

	}

	/**
	 * Resizes the table dynamically to the next prime to the B*2
	 */
	private void dynamicResize() {
		dynamicResize(getNextPrimeNumber(B * 2));
	}

	public int getNumberCollisions() {
		return totalCollisions.get(totalCollisions.size() - 1).getNumberOfCollisions();
	}

	public double collisionsPerOperations() {
		double rate = 0;
		double numberOfCollisions = 0;
		for (Collision coll : totalCollisions) {
			numberOfCollisions += coll.getNumberOfCollisions();
		}
		rate = numberOfCollisions / totalCollisions.size();
		return rate;

	}

	public void reverseResizing(double maxLF) {
		if (getLF() < maxLF) {
			reverseResize();
		}
	}

	private void reverseResize() {
		int primeBefore = getPrevPrimeNumber((int) Math.ceil((double) B / 2));
		HashTable<T> reduced = new HashTable<>(primeBefore, redispersionType, minLF);

		for (HashNode<T> node : associativeArray) {
			if (node.getElement() != null)
				reduced.add(node.getElement());
		}

		associativeArray = reduced.getAssociativeArray();
		B = primeBefore;
		R = getPrevPrimeNumber(primeBefore);

	}

	/*
	 * Hacer un método que te devuelva la probabilidad de que ocurra una colisión.
	 * La fórmula que mandan usar es: (numero de veces que se ha llamado a add, se
	 * ha añadido el elemento y ocurrió al menos una colisión)/(número de veces que
	 * se ha llamado a add y se ha añadido el elemento) Después de llamar a add y
	 * haber añadido el elemento, si la probabilidad > 0.7 hacer redispersión y la
	 * probalididad se resetea a 0
	 */
	public double getCollisionProbabilityAdd() {
		if (b == 0)
			return 0;
		return (double) a / b;
	}

}
