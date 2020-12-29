package calculations;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Utility class for calculator panel final grade calculator panel
 * @author Bunguiu Norales
 *
 */
public class Calcs {
	
	/**
	 * handles basic operations for the standard calculator
	 * @param calculation
	 * @return the answer to the calculation request
	 */
	public static double basicCalculations(String calculation) {
		
		String [] split = calculation.split("x|\u00F7|\u2013|\\+" );
		if (calculation.contains("+")) {
			return Double.parseDouble(split[0]) + Double.parseDouble(split[1]);
		}
		else if (calculation.contains("\u2013")) {
			return Double.parseDouble(split[0]) -  Double.parseDouble(split[1]);
		}else if (calculation.contains("x")) {
			return Double.parseDouble(split[0]) *  Double.parseDouble(split[1]);
		}else {
			return Double.parseDouble(split[0]) /  Double.parseDouble(split[1]);
		}
	}
	
	/**
	 * calculates square root
	 * @param calculation
	 * @return the square root of the calculaton
	 */
	public static Double squareRoot(String calculation) {
		return Math.sqrt(Double.parseDouble(calculation));

	}
	
	/**
	 * calculates modulo
	 * @param calculation
	 * @return the modulos of the calculation
	 */
	public static double mod(String calculation) {
		
		String [] split = calculation.split("mod");
		
		return Double.parseDouble(split[0]) % Double.parseDouble(split[1]) ;
	}
	
	/**
	 * calculates modular inverse of the calculation
	 * @param calculation
	 * @return the modular inverse
	 */
	public static String modInv(String calculation) {
		String [] split = calculation.split("modInv");
		BigInteger left = new BigInteger(split[0]);
		BigInteger right = new BigInteger(split[1]);
		String result;
		try {
			 result = left.modInverse(right).toString();
			
		} catch (ArithmeticException e) {
			// TODO: handle exception
			result = "INVERTIBLE MODULO";
			
		}
		return result;
	}
	
	
	/**
	 * this handles the calculation for the final grade panel
	 * @param current
	 * @param wanted
	 * @param finalWorth
	 * @return the grade needed in the final exam to get wanted grade
	 */
	public static String gradeCalc(String current, String wanted, String finalWorth) {
		DecimalFormat format = new DecimalFormat("#.##");
		double needed = (100 * Double.parseDouble(wanted) - (100-Double.parseDouble(finalWorth)) * Double.parseDouble(current)) / Double.parseDouble(finalWorth) ;
		
		String answer = "Answer: \n \tYou need a "+String.valueOf(format.format(needed)) + "% in the final exam \n\t  to get a " + wanted + "% in the class."; 
	
		return answer;
		
	}
	
	public static String numberOfWords(String content) {
		if (content.equals("")) {
			return "0";
		}
		
		String split[] = content.split("\\s+"); 

		return String.valueOf(split.length);
	}
	
	public static String charNumber(String content) {  
        return String.valueOf(content.length());
	}
}
