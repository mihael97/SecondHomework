package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.EmptyStackException;
import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Class checks implementation and functionality of stack<br>
 * Main program argument is given by program arguments and represents expression
 * we want to calculate
 * 
 * @author Mihael
 *
 */
public class StackDemo {

	/**
	 * Main program
	 * 
	 * @param args
	 *            - arguments,program must have only one argument
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Duljina mora biti jedan!");
		}

		String[] arguments = args[0].split("\\s+");
		ObjectStack stack = new ObjectStack();

		for (String pomString : arguments) {
			if (pomString.equals("/") || pomString.equals("%") || pomString.equals("+") || pomString.equals("-")
					|| pomString.equals("*")) {
				Object[] numbers = new Object[2];

				try {
					numbers[1] = stack.pop();
					numbers[0] = stack.pop();

					stack.push(calculate(numbers, pomString));
				} catch (EmptyStackException e) {
					System.err.println("Stog je prazan,nedovoljno argumenata!");
				} catch (IllegalArgumentException exp) {
					System.err.println("Probalo se dijeliti s nulom!");
				}
			} else {
				stack.push(pomString);
			}
		}

		if (stack.size() == 1) {
			System.out.println("Rezultat je " + stack.pop());
		}
	}

	/**
	 * Method calculates value of expression
	 * 
	 * @param numbers
	 *            - operation attributes
	 * @param operator
	 *            - operator
	 * @return value of expression,always integer
	 * 
	 * @throws IllegalArgumentException
	 *             - ako je djeljenje s nulom
	 */
	public static Integer calculate(Object[] numbers, String operator) {
		if (operator.equals("+")) {
			return Integer.parseInt(numbers[0].toString()) + Integer.parseInt(numbers[1].toString());
		} else if (operator.equals("-")) {
			return Integer.parseInt(numbers[0].toString()) - Integer.parseInt(numbers[1].toString());
		} else if (operator.equals("*")) {
			return Integer.parseInt(numbers[0].toString()) * Integer.parseInt(numbers[1].toString());
		} else if (operator.equals("%")) {
			return Integer.parseInt(numbers[0].toString()) % Integer.parseInt(numbers[1].toString());
		} else {
			if ((Integer.parseInt(numbers[1].toString())) != 0) {
				return Integer.parseInt(numbers[0].toString()) / Integer.parseInt(numbers[1].toString());
			} else {
				throw new IllegalArgumentException();
			}
		}
	}

}
