package hr.fer.zemris.java.hw02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import hr.fer.zemris.java.hw02.ComplexNumber;
import static hr.fer.zemris.java.hw02.ComplexNumber.*;

@SuppressWarnings("javadoc")
public class ComplexNumberTest {
	private static double TOLERANCE = 1E-6;

	@Test
	public void realNumberTest() {
		ComplexNumber number = fromReal(-1);
		assertEquals(-1, number.getRealPart(), TOLERANCE);
		assertEquals(0, number.getImaginaryPart(), TOLERANCE);
	}

	@Test
	public void imaginaryNumberTest() {
		ComplexNumber number = fromImaginary(-1);
		assertEquals(-1, number.getImaginaryPart(), TOLERANCE);
		assertEquals(0, number.getRealPart(), TOLERANCE);
	}

	@Test
	public void complexNumberTest() {
		ComplexNumber number = new ComplexNumber(4, -5);// 4-5i
		assertEquals(4, number.getRealPart(), TOLERANCE);
		assertEquals(-5, number.getImaginaryPart(), TOLERANCE);
	}

	@Test
	public void fromMagnitudeAndAngleTest() {
		ComplexNumber number = fromMagnitudeAndAngle(1, Math.PI);
		assertEquals(-1, number.getRealPart(), TOLERANCE);
		assertEquals(0, number.getImaginaryPart(), TOLERANCE);
	}

	@Test
	public void angleTest() {
		ComplexNumber result = new ComplexNumber(30, 225);
		assertEquals(1.438244794, result.getAngle(), TOLERANCE);
	}

	@Test
	public void parserTest() {
		ComplexNumber num1 = parse("1");
		assertEquals(1, num1.getRealPart(), TOLERANCE);
		assertEquals(0, num1.getImaginaryPart(), TOLERANCE);

		ComplexNumber num2 = parse("-1");
		assertEquals(-1, num2.getRealPart(), TOLERANCE);
		assertEquals(0, num2.getImaginaryPart(), TOLERANCE);

		ComplexNumber num3 = parse("i");
		assertEquals(0, num3.getRealPart(), TOLERANCE);
		assertEquals(1, num3.getImaginaryPart(), TOLERANCE);

		ComplexNumber num4 = parse("-i");
		assertEquals(0, num4.getRealPart(), TOLERANCE);
		assertEquals(-1, num4.getImaginaryPart(), TOLERANCE);

		ComplexNumber num5 = parse("1+i");
		assertEquals(1, num5.getRealPart(), TOLERANCE);
		assertEquals(1, num5.getImaginaryPart(), TOLERANCE);

		ComplexNumber num6 = parse("-1+i");
		assertEquals(-1, num6.getRealPart(), TOLERANCE);
		assertEquals(1, num6.getImaginaryPart(), TOLERANCE);

		ComplexNumber num7 = parse("1-i");
		assertEquals(1, num7.getRealPart(), TOLERANCE);
		assertEquals(-1, num7.getImaginaryPart(), TOLERANCE);

		ComplexNumber num8 = parse("-1-i");
		assertEquals(-1, num8.getRealPart(), TOLERANCE);
		assertEquals(-1, num8.getImaginaryPart(), TOLERANCE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void parseNull() {
		@SuppressWarnings("unused")
		ComplexNumber result = ComplexNumber.parse(null);
	}

	@Test
	public void parseWrongString() {
		assertNull(ComplexNumber.parse("Neki cudan niz!"));
	}

	@Test
	public void addTest() {
		ComplexNumber add1 = new ComplexNumber(1, 2);// 1+2i
		ComplexNumber add2 = new ComplexNumber(2, 7);// 2+7i

		ComplexNumber result = add1.add(add2);
		assertEquals(3, result.getRealPart(), TOLERANCE);
		assertEquals(9, result.getImaginaryPart(), TOLERANCE);
	}

	@Test
	public void subTest() {
		ComplexNumber sub1 = new ComplexNumber(1, 2);// 1+2i
		ComplexNumber sub2 = new ComplexNumber(2, 7);// 2+7i

		ComplexNumber result = sub1.sub(sub2);
		assertEquals(-1, result.getRealPart(), TOLERANCE);
		assertEquals(-5, result.getImaginaryPart(), TOLERANCE);
	}

	@Test
	public void mulTest() {
		ComplexNumber mul1 = new ComplexNumber(1, 2);// 1+2i
		ComplexNumber mul2 = new ComplexNumber(2, 7);// 2+7i

		ComplexNumber result = mul1.mul(mul2);
		assertEquals(-12, result.getRealPart(), TOLERANCE);
		assertEquals(11, result.getImaginaryPart(), TOLERANCE);
	}

	@Test
	public void divTest() {
		ComplexNumber div1 = new ComplexNumber(1, 2);// 1+2i
		ComplexNumber div2 = new ComplexNumber(2, 7);// 2+7i

		ComplexNumber result = div1.div(div2);
		assertEquals(0.3018867925, result.getRealPart(), TOLERANCE);
		assertEquals(-0.05660377358, result.getImaginaryPart(), TOLERANCE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void divZeroTest() {
		ComplexNumber div1 = new ComplexNumber(1, 2);// 1+2i
		ComplexNumber div2 = new ComplexNumber(0, 0);// 0+0i

		div1.div(div2);
	}

	@Test
	public void powTest() {
		ComplexNumber pow1 = new ComplexNumber(1, 2);// 1+2i

		ComplexNumber result = pow1.power(2);
		assertEquals(-3, result.getRealPart(), TOLERANCE);
		assertEquals(4, result.getImaginaryPart(), TOLERANCE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void powExcptionTest() {
		@SuppressWarnings("unused")
		ComplexNumber result = new ComplexNumber(2, 2).power(-1);
	}

	@Test
	public void rootTest() {
		ComplexNumber div1 = new ComplexNumber(4, 4);// 4+4i

		ComplexNumber result = div1.root(2)[0];
		assertEquals(2.197368227, result.getRealPart(), TOLERANCE);
		assertEquals(0.9101797211, result.getImaginaryPart(), TOLERANCE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void rootExceptionTest() {
		@SuppressWarnings("unused")
		ComplexNumber result = new ComplexNumber(2, 2).root(-1)[1];
	}
}
