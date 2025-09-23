package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Your name and id here
 * @version 2025-09-07
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param string the string to test
     * @return {@code true} if {@code string} is all digits, {@code false} otherwise
     */
    public static boolean allDigits(final String string) {

	// your code here
    	int digitcount = 0;
    	
    	for (char c : string.toCharArray()) {
    		if(Character.isDigit(c)) {
    			digitcount += 1;
    		}
    	}
    	
    	boolean returnstmt = true;
    	
    	if(digitcount < string.length()) {
    		returnstmt = false;
    	}
    	
	return returnstmt;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn the serial number to test
     * @return {@code true} if the serial number is valid in form, {@code false}
     *         otherwise.
     */
    public static boolean validSn(final String sn) {

	// your code here
    	boolean valied = true;
    	
    	char[] serialnum = sn.toCharArray();
    	
    	if(serialnum[0] != 'S' && serialnum[1] != 'N') {
    		valied = false;
    	} else if (serialnum[2] != '/') {
    		valied = false;
    	} 	
    	for (int i = 3; i <= 6; i++) {
        	if (!Character.isDigit(serialnum[i])) valied = false;
        	
    	} 	
    	if(serialnum[7] != '-')valied = false;	
    	for (int i = 8; i < serialnum.length; i++) {
    		if(!Character.isDigit(serialnum[i])) valied = false;

    	}
	return valied;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * {@code goodSns}, and invalid serial numbers to {@code badSns}. Each line of
     * {@code fileIn} contains a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

	// your code here
    	
    	

	return;
    }

}
