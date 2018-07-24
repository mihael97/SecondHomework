package hr.fer.zemris.java.custom.collections;

import java.util.List;

/**
 * Class represents objects collection in {@link List} format
 * 
 * @author Mihael
 *
 */
public class LinkedListIndexedCollection extends Collection {

	/**
	 * List size
	 */
	private int size;
	/**
	 * First element
	 */
	private ListNode first;
	/**
	 * Last element
	 */
	private ListNode last;

	/**
	 * Default constructor
	 */
	public LinkedListIndexedCollection() {
	}

	/**
	 * Constructor creates new list from given collection
	 * 
	 * @param collection
	 *            - collection
	 * @throws NullPointerException
	 *             - if collection is <code>null</code>
	 */
	public LinkedListIndexedCollection(Collection collection) {
		if (collection != null) {
			addAll(collection);
		} else {
			throw new NullPointerException("Collection cannot be null!");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#add(java.lang.Object)
	 */
	@Override
	public void add(Object value) {
		if (value == null) {
			throw new NullPointerException("Object cannot be null!");
		} else if (first == null) {
			first = last = new ListNode(null, null, value);
		} else {
			ListNode node = new ListNode(last, null, value);
			last.next = node;
			last = node;
		}

		size++;
	}

	/**
	 * Method returns element on specific position
	 * 
	 * @param index
	 *            - position
	 * @return object on specific position
	 * @throws IndexOutOfBoundsException
	 *             - if index is not in range
	 */
	public Object get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Given index is " + index + ",but it must be  [0," + (size - 1) + "]");
		}
		ListNode pomListNode;

		if (index < (size / 2)) {
			pomListNode = first;

			int position = 0;
			while (pomListNode != null) {
				if (position++ == index) {
					return pomListNode.value;
				}

				pomListNode = pomListNode.next;
			}
		} else {
			pomListNode = last;

			int position = size - 1;
			while (pomListNode != null) {
				if (position-- == index) {
					return pomListNode.value;
				}

				pomListNode = pomListNode.previous;
			}
		}

		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#clear()
	 */
	@Override
	public void clear() {
		first = last = null;
		size = 0;
	}

	/**
	 * Method inserts element on given position
	 * 
	 * @param value
	 *            - object we want to add
	 * @param position
	 *            - position
	 * @throws IndexOutOfBoundsException
	 *             - is position is not in range
	 * @throws NullPointerException
	 *             - if element is <code>null</code>
	 */
	public void insert(Object value, int position) {
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("You can only insert element in range [0," + size + "]");
		} else if (value == null) {
			throw new NullPointerException("Given element is null!");
		}

		if (first == null) {
			add(value);
			size--;
		} else if (position == 0) {
			ListNode node = new ListNode(null, first, value);
			first.previous = node;
			first = node;
		} else if (position == size()) {
			ListNode node = new ListNode(last, null, value);
			last.next = node;
			last = node;
		} else {
			ListNode pomList = first;
			position--;
			while (pomList != null && position-- != 0) {
				pomList = pomList.next;
			}

			ListNode listNode = new ListNode(pomList, pomList.next, value);
			if (pomList.next != null) {
				pomList.next.previous = listNode;

			}
			pomList.next = listNode;
		}

		size++;
	}

	/**
	 * Method returns index of first element appearances
	 * 
	 * @param value
	 *            - element we are searching for
	 * @return <code>-1</code> if doesn't exist,otherwise <code>position</code>
	 */
	public int indexOf(Object value) {
		ListNode node = first;

		for (int index = 0; index < size; index++) {
			if (value.equals(node.value)) {
				return index;
			}
			node = node.next;
		}

		return -1;
	}

	/**
	 * Method removes element from given position
	 * 
	 * @param index
	 *            - position
	 * @throws IndexOutOfBoundsException
	 *             - if index is out of range
	 */
	public void remove(int index) {
		if (index < 0 || index > (size - 1)) {
			throw new IndexOutOfBoundsException(
					"Argument is " + index + " but it must be in range [0," + (size - 1) + "]");
		}

		ListNode before = null;
		ListNode node = first;

		int position = 0;
		while (node != null) {
			if (position++ == index) {
				break;
			} else {
				before = node;
				node = node.next;
			}
		}

		if (node.previous == null) {
			first = first.next;
			first.previous = null;
		} else if (node.next == null) {
			last = last.previous;
			last.next = null;
		} else {
			node.next.previous = before;
			before.next = node.next;
		}

		size--;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#forEach(hr.fer.zemris.java.custom.collections.Processor)
	 */
	@Override
	public void forEach(Processor processor) {
		for (Object object : toArray()) {
			processor.process(object);
		}
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
		ListNode node = first;

		while (node != null) {
			if (node.value.equals(value)) {
				return true;
			}

			node = node.next;
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
		ListNode node = first;

		int index = 0;
		while (node != null && index < size) {
			if (node != null) {
				forReturn[index] = node.value;
				index++;
			}

			node = node.next;
		}

		return forReturn;
	}

	/**
	 * Class implements list item with attributes:<br>
	 * <ul>
	 * <li>value</li>
	 * <li>previous node</li>
	 * <li>next node</li>
	 * </ul>
	 * 
	 * @author Mihael
	 *
	 */
	private static class ListNode {
		/**
		 * Reference to previous node
		 */
		private ListNode previous;
		/**
		 * Reference to next node
		 */
		private ListNode next;
		/**
		 * Value
		 */
		private Object value;

		/**
		 * Constructor creates new list element
		 * 
		 * @param previous
		 *            - reference to previous element
		 * @param next
		 *            - reference to next element
		 * @param value
		 *            - value
		 */
		public ListNode(ListNode previous, ListNode next, Object value) {
			this.previous = previous;
			this.next = next;
			this.value = value;
		}
	}
}
