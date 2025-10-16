package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author your name here
 * @version 2022-01-17
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student(int ID, String surname, String forename, LocalDate birthDate) {
    	this.id = ID;
    	this.surname = surname;
    	this.forename = forename;
    	this.birthDate = birthDate;
	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
	String string = "";
	
	// your code here
	string = String.format("Name:      %s, %s%nID:        %d%nBirthdate: %s",
            surname, forename, id, birthDate);

	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {

	// your code here
	int surnameCompare = this.surname.compareTo(target.surname);
	if(surnameCompare != 0) {
		return surnameCompare;
	}
	
	int forenameCompare = this.forename.compareTo(target.forename);
	if(forenameCompare != 0) {
		return forenameCompare;
	}
	

	return Integer.compare(this.id, target.id);
    }

    // getters and setters defined here
    
    /**
     * @return birth date of this instance
     */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate birth date to set for this instance
	 */

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
    
    /**
     * @return forename of this instance
     */
	public String getForename() {
		return forename;
	}
	/**
	 * @param forename forename to set for this instance
	 */
	public void setForename(String forename) {
		this.forename = forename;
	}
    /**
     * @return ID of this instance
     */
	public int getId() {
		return id;
	}
	/**
	 * @param id ID to set for this instance
	 */
	public void setId(int id) {
		this.id = id;
	}
    /**
     * @return surname of this instance
     */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname surname to set for this instance
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
