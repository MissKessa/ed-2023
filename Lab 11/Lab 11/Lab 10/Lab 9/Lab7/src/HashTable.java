import java.util.ArrayList;

public class HashTable<T> {
	protected final static int LINEAR_PROBING = 0;
	protected final static int QUADRATIC_PROBING = 1;
	public final static int ATTEMPTS = 20;

	private int B;
	private int redispersionType;
	private double minLF;
	private ArrayList<HashNode<T>> associativeArray;
	private double n; // number of elements

	public HashTable() {
		this(7, LINEAR_PROBING, 0.5);
	}

	public HashTable(int b, int redispersionType, double minLF) {
		B = b;
		if (redispersionType != LINEAR_PROBING && redispersionType != QUADRATIC_PROBING)
			throw new IllegalArgumentException("The redispersion Type must be one of the ones defined");

		this.redispersionType = redispersionType;
		this.minLF = minLF;

		// Reserve memory for the ArrayList and initialise it inserting B empty
		// HashNodes
		this.associativeArray = new ArrayList<>(B);
		for (int i = 0; i < B; i++) {
			associativeArray.add(new HashNode<T>());
		}
	}

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

		switch (redispersionType) {
		case LINEAR_PROBING:
			return (Math.abs(element.hashCode()) + i) % B; // hasCode might return a negative number
		case QUADRATIC_PROBING:
			return (Math.abs(element.hashCode()) + i * i) % B;
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
				break;
			}
			i++;

		}

	}

	public boolean search(T element) {
		if (element == null)
			throw new NullPointerException("The element must be not null");

		int i = 0;
		while (i < ATTEMPTS) {
			HashNode<T> node = associativeArray.get(f(element, i));
			if (element.equals(node.getElement()) && node.isValid())
				return true;

			else if (node.isEmpty())
				return false;
			i++;

		}
		return false;

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
	private boolean isPrime(int number) { // O(n) worst case, O(1) best case (It's divisible by 2)
		if (number == 0)
			return false;

		if (number < 0)
			number *= -1;

		for (int i = 2; i < number; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * Returns the prime number predecessor of a given int number
	 */
	private int getPrevPrimeNumber(int number) { // O(nlog(n))
		boolean[] isNotPrime = new boolean[number - 1]; // prime = false, not prime = true

		for (int i = 0; i < isNotPrime.length; i++) {
			int value = i;
			if (i == 0 || i == 1) {
				isNotPrime[i] = true;
				continue;
			}
			if (!isNotPrime[i]) {
				for (int j = i; j < isNotPrime.length; j += value) {
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
	private int getNextPrimeNumber(int number) {
		boolean[] isNotPrime = new boolean[number * 2 - 1]; // prime = false, not prime = true

		for (int i = 0; i < isNotPrime.length; i++) {
			int value = i;
			if (i == 0 || i == 1) {
				isNotPrime[i] = true;
				continue;
			}
			if (!isNotPrime[i]) {
				for (int j = i; j < isNotPrime.length; j += value) {
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
}
