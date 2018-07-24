package hr.fer.zemris.java.hw02.demo;

import hr.fer.zemris.java.hw02.ComplexNumber;

/**
 * Complex checks functionality of complex numbers implementation
 * 
 * @author Mihael
 *
 */
public class ComplexDemo {

	/**
	 * Main method
	 * 
	 * @param args
	 *            - not in use
	 */
	public static void main(String[] args) {
		ComplexNumber c1 = new ComplexNumber(2, 3);
		ComplexNumber c2 = ComplexNumber.parse("2.5-3i");		
		ComplexNumber c3 = c1.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57)).div(c2).power(3).root(2)[1];
		System.out.println(c3);
		
		ComplexNumber num=new ComplexNumber(2, 1);
		System.out.println("Tu string: "+num.toString());
		ComplexNumber res=ComplexNumber.parse(num.toString());
		System.out.println("Realni: "+res.getRealPart()+",imaginarni: "+res.getImaginaryPart());
		System.out.println("Angle: "+res.getAngle());
		
		System.out.println("Drugi primjer,100+100i="+ComplexNumber.parse("100+100i"));
	
		ComplexNumber c4=new ComplexNumber(100,-100);
		System.out.println("To string: "+c4.toString());
		System.out.println("Parse: "+ComplexNumber.parse(c4.toString()));
		
		System.out.println(new ComplexNumber(2, 1).getAngle());
		System.out.println(new ComplexNumber(2, -1).getAngle());
		System.out.println(new ComplexNumber(-2, 1).getAngle());
		System.out.println(new ComplexNumber(-2, -1).getAngle());
	}

}
