package hr.fer.zemris.java.custom.collections;

/**
 * Class represents exception which is thrown when stack is empty
 * 
 * @author Mihael
 *
 */
@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException {

	/**
	 * Default constructor
	 */
	public EmptyStackException() {
		super("Stack is empty!");
	}
}
