package cp213;

import java.io.PrintStream;

/**
 * Food class definition.
 *
 * @author your name, id, email here
 * @version 2025-05-04
 */
public class Food implements Comparable<Food> {

    // Constants
    public static final String ORIGINS[] = { "Canadian", "Chinese", "Indian", "Ethiopian", "Mexican", "Greek",
	    "Japanese", "Italian", "Moroccan", "Scottish", "Columbian", "English" };

    /**
     * Creates a string of food origins in the format:
     *
     * <pre>
    Origins
    0 Canadian
    1 Chinese
    ...
    11 English
     * </pre>
     *
     * @return A formatted numbered string of valid food origins.
     */
    public static String originsMenu() {

	// your code here
    	
    	String retstmt = "";
    	
    	for(int i=0; i < ORIGINS.length; i++) {
    		retstmt += i + ORIGINS[i];
    	}

	return retstmt;
    }

    // Attributes
    private String name = null;
    private int origin = 0;
    private boolean isVegetarian = false;
    private int calories = 0;

    /**
     * Food constructor.
     *
     * @param name         food name
     * @param origin       food origin code
     * @param isVegetarian whether food is vegetarian
     * @param calories     caloric content of food
     */
    public Food(final String name, final int origin, final boolean isVegetarian, final int calories) {

	// your code here
    	this.name = name;
    	this.origin = origin;
    	this.isVegetarian = isVegetarian;
    	this.calories = calories;

	return;
    }

    /**
     * Food copy constructor. All attributes are primitives or immutable.
     *
     * @param source the Food object to copy.
     */
    public Food(Food source) {

	// your code here
    	
    	this.name = source.name;
    	this.origin = source.origin;
    	this.isVegetarian = source.isVegetarian;
    	this.calories = source.calories;
    	
    }

    /*
     * (non-Javadoc) Compares this food against another food.
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    /**
     * Foods are compared by name, then by origin if the names match. Must ignore
     * case.
     */
    @Override
    public int compareTo(final Food target) {

	// your code here
    	int ret = 0;

    if(target.name != this.name) {
    	ret++;
    } else if (target.origin != this.origin) {
    	ret++;
    }
    
	return ret;
    }

    /**
     * Getter for calories attribute.
     *
     * @return calories
     */
    public int getCalories() {

	// your code here

	return calories;
    }

    /**
     * Getter for name attribute.
     *
     * @return name
     */
    public String getName() {


	return name;
    }

    /**
     * Getter for origin attribute.
     *
     * @return origin
     */
    public int getOrigin() {

	// your code here

	return origin;
    }

    /**
     * Getter for string version of origin attribute.
     *
     * @return string version of origin
     */
    public String getOriginString() {

	// your code here

	return originsMenu();
    }

    /**
     * Getter for isVegetarian attribute.
     *
     * @return isVegetarian
     */
    public boolean isVegetarian() {

	// your code here

	return isVegetarian;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    public void setOrigin(int Origins) {
    	this.origin = Origins;
    }
    public void setVegitarian(boolean veg) {
    	this.isVegetarian = veg;
    }
    public void setCalories(int calories) {
    	this.calories = calories;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object//toString() Creates a formatted string of food data.
     */
    /**
     * Returns a string version of a Food object in the form:
     *
     * <pre>
    Name:       name
    Origin:     origin string
    Vegetarian: true/false
    Calories:   calories
     * </pre>
     */
    @Override
    public String toString() {

	// your code here
    	String ret="";
    	
    	ret += "Name:		"+this.name+"\n";
    	ret += "Origin:		"+this.getOriginString()+"\n";
    	ret += "Vegetarian:	"+this.isVegetarian+"\n";
    	ret += "Calories:	"+this.calories+"\n";	

	return ret;
    }

    /**
     * Writes a single line of food data to an open PrintStream. The contents of
     * food are written as a string in the format name|origin|isVegetarian|calories
     * to ps.
     *
     * @param ps The PrintStream to write to.
     */
    public void write(final PrintStream ps) {

	// your code here
    	ps.append(this.name+"|");
    	ps.append(this.getOriginString()+"|");
    	ps.append(this.isVegetarian+"|");
    	ps.append(this.calories+"");
    	
	return;
    }

}
