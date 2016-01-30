package comp2526_assign1b;

/**
 * <p>
 * An interface containing all of the methods for a UI.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public interface UI {
    /**
     * <p>
     * Displays the name and number of a single Person object.
     * </p>
     * 
     * @param person the Person object to display the data of
     */
    void display(Person person);
    
    /**
     * <p>
     * Displays the name and phone number of every Person object in the
     * people array. This should be every Person in the database.
     * </p>
     * 
     * @param people the array of People objects to display the data of
     */
    void displayAll(Person[] people);
    
    /**
     * <p>
     * Prompt the user for a name, then return the name they input.
     * </p>
     * 
     * @return the name read in by the user
     */
    String readName();
    
    /**
     * <p>
     * Prompt the user for a name and phone number to use in a Person object.
     * Then create a Person with those attributes, and return it.
     * </p>
     * 
     * @return a Person object with the data specified by the user
     */
    Person readPerson();
    
    /**
     * <p>
     * Displays the menu, prompts the user to input their choice, then
     * uses an AddressBook object to carry out the actions of the users
     * choice
     * </p>
     * 
     * @param book the AddressBook to carry out the users choice
     */
    void run(AddressBook book);
    
    /**
     * <p>
     * Displays a message by printing it to the console.
     * The message should be a success or prompt.
     * </p>
     * 
     * @param msg the message to be displayed in the console
     */
    void displayMsg(String msg);
    
    /**
     * <p>
     * Displays an error message by printing it to the console.
     * The message should be an error message indicating failure.
     * </p>
     * 
     * @param msg the message to be displayed in the console
     */
    void displayErrorMsg(String msg);
}
