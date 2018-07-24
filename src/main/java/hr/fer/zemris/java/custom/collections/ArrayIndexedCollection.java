package hr.fer.zemris.java.custom.collections;

import java.util.Arrays;

/**
 * Class represents collection with adjustable number of elements
 * 
 * @author Mihael
 *
 */
public class ArrayIndexedCollection extends Collection {
	/**
	 * Number of stored elements
	 */
	private int size;

	/**
	 * Collection capacity
	 */
	private int capacity;

	/**
	 * Default collection capacity
	 */
	private final static int DEFAULT_CAPACITY = 16;

	/**
	 * Stored objects
	 */
	private Object[] elements;

	/**
	 * Default constructor
	 */
	public ArrayIndexedCollection() {
		this(null, DEFAULT_CAPACITY);
	}

	/**
	 * Constructor creates collection with starting capacity
	 * 
	 * @param initialCapacity
	 *            - staring capacity
	 * 
	 * @throws IllegalArgumentException
	 *             - if capacity is lower than 1
	 */
	public ArrayIndexedCollection(int initialCapacity) {
		this(null, initialCapacity);
	}

	/**
	 * Constructor accepts collection which size is less than 16 and creates new
	 * collection with size 16 and filled with elements from argument collection
	 * 
	 * @param collection
	 *            - collection
	 * @throws NullPointerException
	 *             - collection cannot be <code>null</code>
	 */
	public ArrayIndexedCollection(Collection collection) {
		this(collection, DEFAULT_CAPACITY);
	}

	/**
	 * Constructore creates new collection with given size <br>
	 * If collection is not <code>null</code>,all elements from collection will be
	 * copied to this collection
	 * 
	 * @param collection
	 *            - collection
	 * @param initialCapacity
	 *            - starting capacity
	 * @throws IllegalArgumentException
	 *             - if initial capacity is lower than 1
	 */
	public ArrayIndexedCollection(Collection collection, int initialCapacity) {
		if (initialCapacity < 1) {
			throw new IllegalArgumentException("Collection size must be greather or equal to 1!");
		}

		elements = new Object[initialCapacity];

		if (collection != null) {
			addAll(collection);
		}
	}

	// other methods

	/**
	 * non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#add(java.lang.Object)
	 */
	@Override
	public void add(Object value) {
		if (value == null) {
			throw new NullPointerException("Zadana vrijednost ne smije biti null!");
		}

		if (size + 1 > capacity) {
			doubleCapacity();
		}

		elements[size++] = value;
	}

	/**
	 * Method relocates array to double capacity with same elements
	 */
	private void doubleCapacity() {
		capacity *= 2;
		elements = Arrays.copyOf(elements, capacity);
	}

	/**
	 * Returns element at argument position <br>
	 * Index must be in range between <code>0</code> and
	 * <code>collection size - 1</code>
	 * 
	 * @param index
	 *            - position
	 * 
	 * @throws IndexOutOfBoundsException
	 *             - if index is not inside range
	 * 
	 * @return element at specific position
	 */
	public Object get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException(
					"Index " + index + " is not in range. Must be between [0," + (size - 1) + "].");
		} else {
			return elements[index];
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#clear()
	 */
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}

		size = 0;
	}

	/**
	 * Method inserts element at specific position in array
	 * 
	 * @param value
	 *            - element
	 * @param position
	 *            - position
	 * 
	 * @throws IndexOutOfBoundsException
	 *             - if position is not in range
	 * @throws NullPointerException
	 *             - if object is <code>null</code>
	 */
	public void insert(Object value, int position) {
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException(
					"Given position is  " + position + " but must be between [0," + size + "].");
		} else if (value == null) {
			throw new NullPointerException("Element cannot be null!");
		} else {
			Object[] pomArray = copyArray(size);

			if (size + 1 > capacity) {
				capacity *= 2;
				elements = new Object[capacity];
			}

			int elementsIndex = 0;
			int arrayItem = 0;
			size++; // because we are adding new element

			while (elementsIndex < size) {
				if (elementsIndex == position) {
					elements[elementsIndex] = value;
				} else {
					elements[elementsIndex] = pomArray[arrayItem++];
				}
				elementsIndex++;
			}

		}
	}

	/**
	 * Method returns position of first element appearance
	 * 
	 * @param value
	 *            - element we are looking for
	 * @return <code>-1</code> if element doesn't exist,otherwise element position
	 */
	public int indexOf(Object value) {
		if (value != null) {
			for (int index = 0; index < size; index++) {
				if (elements[index].equals(value)) {
					return index;
				}
			}
		}

		return -1;
	}

	/**
	 * Method removes element from given position
	 * 
	 * @param index
	 *            - position form where we want to remove element
	 * 
	 * @throws IndexOutOfBoundsException
	 *             - if index is out of range
	 * 
	 */
	public void remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException(
					"Given argument is " + index + " but it must be between [0," + (size - 1) + "].");
		}
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}

		elements[size - 1] = null;
		size--;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object value) {
		if (value == null) { // collection doesn't contains null elements
			return false;
		}

		for (Object object : elements) {
			if (value.equals(object)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		Object[] forReturn = new Object[size];

		int index = 0;
		for (Object object : elements) {
			if (object != null) {
				forReturn[index] = object;
				index++;
			} else {
				break;
			}
		}

		return forReturn;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object value) {
		if (indexOf(value) != -1) {
			remove(indexOf(value));

			return true;
		} else {
			return false;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#forEach(hr.fer.zemris.java.custom.collections.Processor)
	 */
	@Override
	public void forEach(Processor processor) {
		for (Object object : elements) {
			if (object != null) {
				processor.process(object);

			}
		}
	}

	/**
	 * Method copies elements from begin to position given by argument
	 * 
	 * @param length
	 *            - length
	 * @return array of {@link Object}s
	 */
	private Object[] copyArray(int length) {
		Object[] array = new Object[length];

		for (int i = 0; i < length; i++) {
			array[i] = elements[i];
		}

		return array;
	}
}
