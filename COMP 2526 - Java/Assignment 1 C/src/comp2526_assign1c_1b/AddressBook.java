package comp2526_assign1c_1b;

/**
 * <p>
 * The AddressBook class handles querying the database to store, retrieve, and
 * remove People objects from the database. It has methods to add, delete, and
 * find People objects in the database. It also has many support methods for
 * these main functions, as well as methods to display Person object's data.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class AddressBook {
    /**
     * <p>
     * A Database reference to a later assigned Database object that will
     * handle the storing of all the Person objects in the database.
     * </p>
     */
    private final Database database;
    
    /**
     * <p>
     * A UI reference that will reference the ConsoleUI object that is
     * passed into the AddressBook's constructor. It will the user
     * interactions and the displays.
     * </p>
     */
    private final UI ui;
    
    /**
     * <p>
     * The constructor for the AddressBook
     * </p>
     * <p>
     * Assigns the UI object passed through to the ui reference, and creates
     * a new Database object and assigns it to the database reference.
     * </p>
     * 
     * @param u a UI for the AddressBook (a ConsoleUI)
     */
    public AddressBook(final UI u) {
        ui       = u;
        database = new Database();
    }

    /**
     * <p>
     * Reads in the name of a Person object by invoking ui's readPerson()
     * method. That name is then checked in the database to ensure that
     * a Person object with that name does not already exist. If the name
     * doesn't exist, the database's add() method is called to create a new
     * Person object with that name in the database, and it will then print
     * out a message indicating success. If the name already existed in the
     * database, than an error message is printed into the console.
     * </p>
     */
    public void addPerson() {
        // the Person object to be added to the database
        final Person person;

        person = ui.readPerson();
        if (person != null)
            database.add(person);
    }
    
    /**
     * <p>
     * Invokes the ui's readName() method to get the name of a Person object
     * from the user. That name is then stored in the name String, and is
     * attempted to be removed by invoking the remove() method. If the Person
     * was successfully removed, a success message is printed to the console.
     * Otherwise, an error message is printed.
     * </p>
     */
    public void deletePerson() {
        // the name of a Person object, as specified by the user
        final String name;

        name = ui.readName();

        if (!remove(name))
            ui.displayErrorMsg("Could not delete " + name);
        else
            ui.displayMsg(name + " was deleted successfully");
    }

    /**
     * <p>
     * Searches for a Person object by first invoking the ui's readName()
     * method to get the name of a Person from the user. Then it will call the
     * search() method with the name passed in it and will store the result in
     * the person variable. Lastly, it checks if the returned Person object is
     * null. If it is null, it will invoke ui's displayErrorMsg() method to
     * announce the failure. If it isn't null, then it will invoke the
     * display() method to print the Person objects attributes to the console.
     * </p>
     */
    public void findPerson() {
        // a String containing the name of the Person to find
        final String name;
        // a Person object with the matching name attribute
        final Person person;

        name   = ui.readName();
        person = search(name);

        if (person != null) {
            display(person);
        } else {
            ui.displayErrorMsg("No such person");
        }
    }

    /**
     * <p>
     * A support method used by the deletePerson() method.
     * </p>
     * <p>
     * Invokes the database's removeByName() method with the name as
     * its argument. It will return whether or not the name was successfully
     * removed from the database or not.
     * </p>
     * 
     * @param name the name of the Person object to remove
     * @return whether the Person object was successfully removed
     */
    private boolean remove(final String name) {
        return (database.removeByName(name) != null);
    }

    /**
     * <p>
     * A support method used by the findPerson() and addPerson() methods.
     * </p>
     * <p> 
     * Invokes the database's findByName() method to search for the name
     * that was passed into the method. If a Person object containing that
     * name was found, it will return that Person object. If a Person object
     * with that name is not found, it will return a null Person object.
     * </p>
     * 
     * @param name the name of the Person object to look for
     * @return the Person object containing the name that was passed in
     */
    private Person search(final String name) {
        return (database.findByName(name));
    }

    /**
     * <p>
     * A support method used by the ui's run() method.
     * </p>
     * <p>
     * Calls the database's getAll() method to create a People array of every
     * Person object in the database, and then invokes ui's displayAll() method
     * with the people array as its parameter to display all of the people
     * in the user interface.
     * </p>
     */
    public void displayAll() {
        ui.display(database.getAll());
    }

    /**
     * <p>
     * A support method used by the findPerson() method.
     * </p>
     * <p>
     * Displays the attributes (name and phone number) of a single Person
     * object. It invokes the ui's display method with the Person passed in
     * it to display the Person in the user interface.
     * </p>
     * 
     * @param person a Person object to have its name and number displayed
     */
    private void display(final Person person) {
        ui.display(person);
    }
}
