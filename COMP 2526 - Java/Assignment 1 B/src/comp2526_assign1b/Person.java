package comp2526_assign1b;

/**
 * <p>
 * Defines a Person object, for use in a database. Person objects
 * have name and phone number attributes. There are also methods
 * to return both the name and the phone number.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Person {
    /**
     * <p>
     * The name of the Person object.
     * </p>
     */
    private final String name;
    
    /**
     * <p>
     * The phone number of the Person object.
     * </p>
     */
    private final String phone;
    
    /**
     * <p>
     * The constructor for the Person class.
     * It takes two parameters, a name and a phone number, which it then
     * assigns to the Person objects name and phone attributes.
     * </p>
     * 
     * @param nm the name of the Person object
     * @param ph the phone number of the Person object
     */
    public Person(final String nm, final String ph) {
        name  = nm;
        phone = ph;
    }
    
    /**
     * <p>
     * Returns the name of the Person object.
     * </p>
     * 
     * @return the name of the Person object
     */
    public String getName() {
        return (name);
    }
    
    /**
     * <p>
     * Returns the phone number of the Person object.
     * </p>
     * 
     * @return the phone number of the Person object
     */
    public String getPhoneNumber() {
        return (phone);
    }
}
