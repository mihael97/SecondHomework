package hr.fer.zemris.java.custom.collections;

/**
 * Class represents collection of generic objects
 * 
 * @author Mihael
 *
 */
public class Collection {

	/**
	 * Class constructor
	 */
	protected Collection() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method checks if collection is empty
	 * 
	 * @return <code>true</code> if collection is empty,otherwise <code>false</code>
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Method returns collection size
	 * 
	 * @return collection size
	 */
	public int size() {
		return 0;
	}

	/**
	 * Method adds object into collection
	 * 
	 * @param value
	 *            - object for add
	 * 
	 * @throws NullPointerException
	 *             - if object is <code>null</code>
	 */
	public void add(Object value) {
	}

	/**
	 * Method checks if collection contains object
	 * 
	 * @param value
	 *            - object
	 * @return <code>true</code> if object is inside collection,otherwise
	 *         <code>false</code>
	 */
	public boolean contains(Object value) {
		return false;
	}

	/**
	 * Method removes element from collection
	 * 
	 * @param value
	 *            - element we want to remove
	 * @return - <true>if we removed element successfully,otherwise
	 *         <code>false</code>
	 */
	public boolean remove(Object value) {
		return false;
	}

	/**
	 * Method returns collection in array format. <b>Never returns
	 * <code>null</code></b>
	 * 
	 * @return collection in <code>array</code> format
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method calls <code>processor.process()</code> for every collection item
	 * 
	 * @param processor
	 *            - processor which process we want to use
	 */
	public void forEach(Processor processor) {

	}

	/**
	 * Method adds every element from argument collection to current collection
	 * 
	 * @param other
	 *            - collection we want to add
	 */
	public void addAll(Collection other) {
		if (other != null) {
			other.forEach(new Processor() {

				@Override
				public void process(Object value) {
					add(value);
				}

			});
		}
	}

	/**
	 * Method removes all elements from collection
	 */
	public void clear() {

	}
}
