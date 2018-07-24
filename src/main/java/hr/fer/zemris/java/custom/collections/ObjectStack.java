package hr.fer.zemris.java.custom.collections;

/**
 * Class implements stack
 * 
 * @author Mihael
 *
 */
public class ObjectStack {
	/**
	 * Reference to collection where elements are stored with auxiliary methods
	 */
	private ArrayIndexedCollection collection;

	/**
	 * Default constructor
	 */
	public ObjectStack() {
		collection = new ArrayIndexedCollection(2);
	}

	/**
	 * Method checks if stack is empty
	 * 
	 * @return <code>true</code> if it is,otherwise <code>false</code>
	 */
	public boolean isEmpty() {
		return collection.isEmpty();
	}

	/**
	 * Method returns number of elements on stack
	 * 
	 * @return number of elements on stack
	 */
	public int size() {
		return collection.size();
	}

	/**
	 * Method adds element into collection
	 * 
	 * @param value
	 *            - value we want to store
	 */
	public void push(Object value) {
		collection.add(value);
	}

	/**
	 * Method adds last added element on stack
	 * 
	 * @return last added element
	 * 
	 * @throws EmptyStackException
	 *             - if stack is empty
	 */
	public Object peek() {
		if (collection.size() == 0) {
			throw new EmptyStackException();
		}

		return collection.get(collection.size() - 1);
	}

	/**
	 * Method removes last added element
	 * 
	 * @return last added element
	 */
	public Object pop() {
		Object forReturn = peek();
		collection.remove(collection.size() - 1);

		return forReturn;
	}

	/**
	 * Method clears stack
	 */
	public void clear() {
		collection.clear();
	}
}
