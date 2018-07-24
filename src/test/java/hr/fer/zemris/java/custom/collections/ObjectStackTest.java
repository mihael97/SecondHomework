package hr.fer.zemris.java.custom.collections;

import static org.junit.Assert.*;

import org.junit.Test;

import hr.fer.zemris.java.custom.collections.demo.StackDemo;

@SuppressWarnings("javadoc")
public class ObjectStackTest {

	@Test
	public void exemple1() {
		Object[] args = { 2, 2 };
		assertEquals(Integer.valueOf(4), StackDemo.calculate(args, "+"));
	}

	@Test
	public void exemple2() {
		Object[] args = { 1, 1 };
		assertEquals(Integer.valueOf(0), StackDemo.calculate(args, "-"));
	}

	@Test
	public void example3() {
		Object[] args = { 5, 4 };
		assertEquals(Integer.valueOf(1), StackDemo.calculate(args, "%"));
	}

	@Test
	public void example4() {
		Object[] args = { 5, 4 };
		assertEquals(Integer.valueOf(20), StackDemo.calculate(args, "*"));
	}

	@Test
	public void example5() {
		Object[] args = { 10, 2 };
		assertEquals(Integer.valueOf(5), StackDemo.calculate(args, "/"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void divideByZero() {
		Object[] args = { 2, 0 };
		System.out.println(StackDemo.calculate(args, "/"));
	}
}
