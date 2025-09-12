package cp213;

/**
 * Sample string methods.
 *
 * @author your name, ID, and email here
 * @version 2022-05-06
 */
public class StringMethods {
    // Constants
    /**
     * String of vowels.
     */
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Counts the number of vowels in a string. Does not include 'y'.
     *
     * @param string A string.
     * @return Number of vowels in string.
     */
    public static int vowelCount(final String string) {
	int count = 0;

	for(int i = 0; i < string.length(); i++) {
		for(int j = 0; j < VOWELS.length(); j++) {
			if(string.charAt(i) == VOWELS.charAt(j)) {
				count++;
			}
		}
		
	}
	

	return count;
    }

    /**
     * Counts the number of digits in a string.
     *
     * @param string A string.
     * @return Number of digits in string.
     */
    public static int digitCount(final String string) {
	int count = 0;
	String NUMBERS = "123456789";

	// your code here
	for(int i = 0; i < string.length(); i++) {
		for(int j = 0; j < NUMBERS.length(); j++) {
			if(string.charAt(i) == NUMBERS.charAt(j)) {
				count++;
			}
		}
		
	}
	return count;
    }

    /**
     * Totals the individual digits in a string.
     *
     * @param string A string.
     * @return The integer total of all individuals digits in string.
     */
    public static int totalDigits(final String string) {
	int total = 0;
	char[] temp = string.toCharArray();
	
	// your code here
	for(int i = 0; i < string.length(); i++) {
		switch((int)temp[i]) {
		case 49:
			total += 1;
			break;
		case 50:
			total += 2;
			break;
		case 51:
			total += 3;
			break;
		case 52:
			total += 4;
			break;
		case 53:
			total += 5;
			break;
		case 54:
			total += 6;
			break;
		case 55:
			total += 7;
			break;
		case 56:
			total += 8;
			break;
		case 57:
			total += 9;
			break;
		default:
			break;
			
		}
	}

	return total;
    }

    /**
     * Compares string1 and string2 and returns a comma-delimited concatenated
     * string consisting of the string that is first lexically followed by the
     * string that is second lexically. If the strings are equal, then only string1
     * is returned.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return A concatenation of string1 and string2 in order.
     */
    public static String pairs(String string1, String string2) {
	String line = null;

	// your code here
	int compare = string1.compareTo(string2);
	if(compare == 1) {
		line = string2 +","+ string1;
	} else if (compare == -1) {
		line = string1 +","+ string2;
	}
	else {
		line = string1;
	}
	
	return line;
    }

    /**
     * Finds the distance between the s1 and s2. The distance between two strings of
     * the same length is the number of positions in the strings at which their
     * characters are different. If two strings are not the same length, the
     * distance is unknown (-1). If the distance is zero, then two strings are
     * equal.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return The distance between string1 and string2.
     */
    public static int stringDistance(String string1, String string2) {
	int distance = 0;

	// your code here
	
	if(string1.length() != string2.length()) {distance = -1;}
	else {
		for (int i = 0; i < string1.length(); i++) {
			if(string1.charAt(i) != string2.charAt(i)) {
				distance += 1;
			}
		}
	}
	

	return distance;
    }
}
