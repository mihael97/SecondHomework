package hr.fer.zemris.java.hw02;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

/**
 * Class implements complex number and contains auxiliary methods for working
 * with them
 * 
 * @author Mihael
 *
 */
public class ComplexNumber {
	/**
	 * Real part
	 */
	private double realPart;

	/**
	 * Imaginary part
	 */
	private double imaginaryPart;

	/**
	 * Absolute value
	 */
	private double magnitude;

	/**
	 * Angle
	 */
	private double angle;

	/**
	 * Constructor creates new complex number
	 * 
	 * @param realPart
	 *            - real part
	 * @param imaginaryPart
	 *            - imaginary part
	 */
	public ComplexNumber(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
		this.angle = Math.atan2(imaginaryPart, realPart);
		magnitude = sqrt(pow(realPart, 2) + pow(imaginaryPart, 2));
	}

	/**
	 * Method creates new complex number where imaginary part is 0
	 * 
	 * @param real
	 *            - real part
	 * @return complex number
	 */
	public static ComplexNumber fromReal(double real) {
		return new ComplexNumber(real, 0);
	}

	/**
	 * Method creates new complex number where real part is 0
	 * 
	 * @param imaginary
	 *            - imaginary part
	 * @return complex number
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		return new ComplexNumber(0, imaginary);
	}

	/**
	 * Method creates new complex number from absolute value and angle
	 * 
	 * @param magnitude
	 *            - absolute value
	 * @param angle
	 *            - angle
	 * @return complex number
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		return new ComplexNumber(magnitude * cos(angle), magnitude * sin(angle));
	}

	/**
	 * Method represents complex number parser
	 * 
	 * @param s
	 *            - complex number in String format
	 * @return complex number
	 * @throws IllegalArgumentException
	 *             - if argument is <code>null</code>
	 */
	public static ComplexNumber parse(String s) {
		try {
			if (s == null) {
				throw new IllegalArgumentException("String for parsing is null!");
			}
			if (!s.contains("i")) {
				return fromReal(Double.parseDouble(s.trim()));
			} else {
				char[] complexArray = s.toCharArray();
				String real = "";
				String imaginary = "";

				for (int i = 0; i < complexArray.length; i++) {
					char c = complexArray[i];

					if (c != ' ') {
						if (c == 'i') {
							if (imaginary.length() == 0) {
								if (real.length() <= 1) {
									real += "1";
								}
								return fromImaginary(Double.parseDouble(real));
							} else {
								if (imaginary.length() == 1) {
									imaginary += "1";
								}

								return new ComplexNumber(Double.parseDouble(real), Double.parseDouble(imaginary));
							}
						} else if (c == '+' || c == '-') {
							if (real.length() != 0) {
								imaginary += c;
							} else {
								real += c;
							}
						} else {
							if (imaginary.length() == 0) {
								real += c;
							} else {
								imaginary += c;
							}
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Niz se ne moze parsirati!");
		}

		return null;
	}

	/**
	 * Method adds complex number to this number
	 * 
	 * @param c
	 *            - complex number we add
	 * @return complex number
	 */
	public ComplexNumber add(ComplexNumber c) {
		return new ComplexNumber(this.realPart + c.getRealPart(), this.imaginaryPart + c.getImaginaryPart());
	}

	/**
	 * Method subtracts two complex numbers
	 * 
	 * @param c
	 *            - second parameter of subtraction
	 * @return complex number
	 */
	public ComplexNumber sub(ComplexNumber c) {
		return new ComplexNumber(this.realPart - c.getRealPart(), this.imaginaryPart - c.getImaginaryPart());
	}

	/**
	 * Method returns multiplies of two complex numbers
	 * 
	 * @param c
	 *            - second number in multiplication
	 * @return complex number
	 */
	public ComplexNumber mul(ComplexNumber c) {
		return new ComplexNumber(this.getRealPart() * c.getRealPart() - this.imaginaryPart * c.getImaginaryPart(),
				this.getImaginaryPart() * c.getRealPart() + this.getRealPart() * c.getImaginaryPart());
	}

	/**
	 * Method divides two complex numbers
	 * 
	 * @param c
	 *            - denominator
	 * @return complex number
	 * 
	 * @throws IllegalArgumentException
	 *             - if denominator is 0 or <code>null</code>
	 */
	public ComplexNumber div(ComplexNumber c) {
		if (c == null) {
			throw new IllegalArgumentException("Number is null!");
		}

		double denominator = c.getRealPart() * c.getRealPart() + c.getImaginaryPart() * c.getImaginaryPart();

		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator is 0!");
		}

		return new ComplexNumber(
				(this.getRealPart() * c.getRealPart() + this.imaginaryPart * c.getImaginaryPart()) / denominator,
				(this.getImaginaryPart() * c.getRealPart() - this.getRealPart() * c.getImaginaryPart()) / denominator);
	}

	/**
	 * Method returns potency of complex number
	 * 
	 * @param n
	 *            - potency we want
	 * @return complex number
	 * 
	 * @throws IllegalArgumentException
	 *             - if potency is lower than zero
	 */
	public ComplexNumber power(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Argument cannot be lower than zero. Given argument is  " + n);
		} else {
			return new ComplexNumber(pow(getMagnitude(), n) * cos(n * getAngle()),
					pow(getMagnitude(), n) * sin(n * getAngle()));
		}
	}

	/**
	 * Method returns root of complex number
	 * 
	 * @param n
	 *            - how many roots we want
	 * @return - complex number array
	 * 
	 * @throws IllegalArgumentException
	 *             - if number of roots is 0 or lower
	 */
	public ComplexNumber[] root(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Argument must be positive! Argument is " + n);
		} else {
			ComplexNumber[] array = new ComplexNumber[n];

			for (int k = 0; k < n; k++) {
				array[k] = new ComplexNumber(pow(magnitude, (double) 1 / 2) * cos((getAngle() + 2 * k * Math.PI) / n),
						pow(magnitude, (double) 1 / 2) * sin((getAngle() + 2 * k * Math.PI) / n));
			}

			return array;
		}
	}

	/**
	 * Method returns real part of complex number
	 * 
	 * @return real part
	 */
	public double getRealPart() {
		return realPart;
	}

	/**
	 * Method returns imaginary part of complex number
	 * 
	 * @return imaginary part
	 */
	public double getImaginaryPart() {
		return imaginaryPart;
	}

	/**
	 * Method returns angle between 0 and 2PI
	 * 
	 * @return angle in radians
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Method returns absolute value
	 * 
	 * @return absolute value
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * Method returns string representation of complex number
	 */
	@Override
	public String toString() {
		String imag = (imaginaryPart < 0) ? Double.valueOf(getImaginaryPart()).toString() : ("+" + getImaginaryPart());
		return getRealPart() + imag + "i";
	}

}
