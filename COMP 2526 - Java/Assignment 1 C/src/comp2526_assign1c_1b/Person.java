package comp2526_assign1c_1b;

/**
 * <p>
 * Defines a Person object that has two attributes, a name and a phone number.
 * The constructor for this class simply assigns the name and phone number to
 * the parameters given. It also defines two methods, one to get the phone
 * number and another to get the name of the Person object.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Person
{
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
     * The constructor for the Person class. It takes two parameters, both
     * strings, and assigns them to the Persons name and phone number.
     * </p>
     * @param nm the name of the Person object
     * @param ph the phone number of the Person object
     */
    public Person(final String nm,
                  final String ph)
    {
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
    public String getName()
    {
        return (name);
    }
    
    /**
     * <p>
     * Returns the phone number of the Person object.
     * </p>
     * @return the phone number of the Person object
     */
    public String getPhoneNumber()
    {
        return (phone);
    }
}
