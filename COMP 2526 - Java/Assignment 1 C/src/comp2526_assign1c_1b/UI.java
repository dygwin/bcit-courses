package comp2526_assign1c_1b;

/**
 * <p>
 * A interface for use with the two different UI types, ConsoleUI and GUI.
 * It defines the basic methods that both UI's should have, including
 * methods to read Person objects and names, methods to display information
 * about Person objects and also to display messages to the user.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public interface UI
{
    /**
     * <p>
     * Display a person objects information to the user.
     * </p>
     * 
     * @param people Person object(s) to have their information displayed
     */
    void display(Person ... people);
    
    /**
     * <p>
     * Prompt the user to enter a name, and then return that name for use
     * in other methods.
     * </p>
     * 
     * @return the name read in
     */
    String readName();
    
    /**
     * <p>
     * Reads in the name and phone number of a Person object from the user,
     * then creates a Person with those attributes and returns it.
     * </p>
     * 
     * @return the Person object thats been created
     */
    Person readPerson();
    
    /**
     * <p>
     * Assigns an AddressBook to the UI to carry out its commands.
     * </p>
     * 
     * @param book the AddressBook to be assigned to the UI
     */
    void run(AddressBook book);
    
    /**
     * <p>
     * Displays a message to the user.
     * </p>
     * 
     * @param msg the message to be displayed to the user
     */
    void displayMsg(String msg);
    
    /**
     * <p>
     * Displays an error message to the user.
     * </p>
     * 
     * @param msg the error message to be displayed to the user
     */
    void displayErrorMsg(String msg);

}
