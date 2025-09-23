package cp213;

/**
 * @author Your name and id here
 * @version 2025-09-07
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if {@code string} is a "palindrome": a word, verse, or sentence
     * (such as "Able was I ere I saw Elba") that reads the same backward or
     * forward. Ignores case, spaces, digits, and punctuation in {@code string}.
     *
     * @param string a string
     * @return {@code true} if {@code string} is a palindrome, {@code false}
     *         otherwise
     */
    public static boolean isPalindrome(final String string) {

	// your code here
    	 if (string == null) {
    	        return false;
    	    }

    	    // Normalize: keep only letters, lowercase them
    	    String normalized = string.replaceAll("[^A-Za-z]", "").toLowerCase();

    	    int left = 0;
    	    int right = normalized.length() - 1;

    	    while (left < right) {
    	        if (normalized.charAt(left) != normalized.charAt(right)) {
    	            return false;
    	        }
    	        left++;
    	        right--;
    	    }

    	    return true;
    }

    /**
     * Determines if {@code name} is a valid Java variable name. Variables names
     * must start with a letter or an underscore, but cannot be an underscore alone.
     * The rest of the variable name may consist of letters, numbers and
     * underscores.
     *
     * @param name a string to test as a Java variable name
     * @return {@code true} if {@code name} is a valid Java variable name,
     *         {@code false} otherwise
     */
    public static boolean isValid(final String name) {

    	if (name == null || name.isEmpty()) {
            return false;
        }

        char first = name.charAt(0);
        if (!(Character.isLetter(first) || first == '_')) {
            return false;
        }

        if (name.equals("_")) {
            return false;
        }

        for (int i = 1; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!(Character.isLetterOrDigit(c) || c == '_')) {
                return false;
            }
        }

        return true;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word the string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	// your code here
    	boolean vowels = false;
    	String piglatinword = null;
    		
    	for (int i = 0; i < VOWELS.length(); i++) {
    		if(word.charAt(0) == VOWELS.charAt(i)) {
    			vowels = true;
    		}
    	
    		
    		if(vowels) {
    			piglatinword = word + "way";
    		} else {
    			String firstchar = String.valueOf(word.charAt(0));
    			String secondchar = String.valueOf(word.charAt(1));
    			String new_word = word.replaceFirst(firstchar, "").replaceFirst(secondchar, "");
    			piglatinword = secondchar.toUpperCase() + new_word + firstchar.toLowerCase() + "ay";
    		}
    }

	return piglatinword;
    }

}
