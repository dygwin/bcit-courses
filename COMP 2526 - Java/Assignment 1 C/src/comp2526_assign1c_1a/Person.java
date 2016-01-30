package comp2526_assign1c_1a;

/**
 * <p>
 * Defines a person that has two attributes, a name and a phone number. It also
 * defines methods to retrieve both the name, and the phone number of the
 * Person.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Person {
	/**
	 * <p>
	 * The name of the Person.
	 * </p>
	 */
	private String name;
	/**
	 * <p>
	 * The phone number of the person.
	 * </p>
	 */
	private String phoneNumber;

	/**
	 * <p>
	 * The constructor for the Person class.
	 * </p>
	 * <p>
	 * It takes two arguments, the persons name and phone number, and assigns it
	 * to the persons name and phoneNumber attributes.
	 * </p>
	 * 
	 * @param name the name of the Person
	 * @param phoneNumber the phone number of the Person
	 */
	public Person(String name, String phoneNumber) {
		this.name        = name;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * <p>
	 * Returns the name of the Person.
	 * </p>
	 * 
	 * @return the name of the Person
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * Returns the phone number of the Person.
	 * </p>
	 * 
	 * @return the phone number of the Person
	 */
	public String getNumber() {
		return phoneNumber;
	}
}
