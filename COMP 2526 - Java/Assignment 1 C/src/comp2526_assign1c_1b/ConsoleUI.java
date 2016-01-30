package comp2526_assign1c_1b;

import java.util.Scanner;

/**
 * <p>
 * Creates a console-based user interface for an AddressBook, that can read
 * in information from the user to create new Person objects, add them to the
 * database, search for Person objects, and delete Person objects. It provides
 * many ways to show messages to the user and prompt them for input using the
 * console. After prompting the user for a choice from the menu, it will
 * execute the correlating method in the AddressBook associated with the UI.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class ConsoleUI implements UI {
    /**
     * <p>
     * The Scanner object used to get input from the user.
     * </p>
     */
    private final Scanner input;
    
    /**
     * <p>
     * The AddressBook object that will carry out the commands
     * issued by the UI.
     * </p>
     */
    private AddressBook addressBook;
    
    /**
     * <p>
     * Displays the heading for when we display Person objects in the console.
     * </p>
     */
    private void displayHeading() {
        String heading1 = "Name";
        String heading2 = "Phone";
        System.out.printf("%-20s%-15s\n", heading1, heading2);
    }
    
    /**
     * <p>
     * The constructor for the ConsoleUI class.
     * It sets up a Scanner object for use in the UI.
     * </p>
     */
    public ConsoleUI() {
        input = new Scanner(System.in);
    }
    
    /**
     * <p>
     * Prompts the user for their choice, and takes their input for a number
     * between 1 and 5. If they choose an invalid option, an error message is
     * printed.
     * </p>
     * 
     * @return the choice of the user
     */
    public int readChoice() {
        // users choice, defaulted to 4
        int choice = 4;
        // whether the user has finished choosing a valid option
        boolean done = false;

        while (!done) {
            System.out.print("choice? ");

            try {
                choice = input.nextInt();
            } catch (Exception e) {
            }

            if (choice > 0 && choice <= 5) {
                done = true;
            } else {
                System.out.println("\nYour choice is incorrect,"
                        + " please try again");
            }
        }

        return choice;
    }
    
    /**
     * <p>
     * Prompts the user for a name and number, then creates a Person object
     * with those attributes and returns it.
     * </p>
     * 
     * @return the newly created Person object with the chosen attributes
     */
    public Person readPerson() {
        // the Person object to be created and returned
        final Person person;
        // the name of the Person object
        final String name;
        // the phone number of the Person object
        final String phone;

        System.out.print("Enter the persons name ");
        name   = input.next();
        System.out.print("\nEnter the persons phone number ");
        phone  = input.next();
        System.out.println("");
        person = new Person(name, phone);

        return (person);
    }
    
    /**
     * <p>
     * Reads in the name of a Person object from the user, then returns
     * that name.
     * </p>
     * 
     * @return the name that was read in
     */
    public String readName() {
        final String name;

        System.out.print("Enter the persons name ");
        name = input.next();
        System.out.println("");

        return (name);
    }
    
    /**
     * <p>
     * Displays the header, then the information of Person object(s).
     * </p>
     * 
     * @param person the Person object(s) to be dislayed in the console
     */
    public void display(final Person... person) {
        displayHeading();
        for (Person p : person) {
            System.out.printf("%-20s15%s\n", p.getName(), p.getPhoneNumber());
        }
    }
    
    /**
     * <p>
     * Gets the users choice and then runs the AddressBooks method that
     * correlates to the users choice. If the user chooses 5, however, then
     * the program is exited.
     * </p>
     * 
     * @param book the AddressBook to carry out the choices
     */
    public void run(final AddressBook book) {
        // the users choice
        int choice = 0;

        addressBook = book;

        do {
            displayMenu();

            choice = readChoice();

            switch (choice) {
            case 1:
                addressBook.addPerson();
                break;
            case 2:
                addressBook.deletePerson();
                break;
            case 3:
                addressBook.findPerson();
                break;
            case 4:
                addressBook.displayAll();
                break;
            default:
                // should not get here - except for 5!
            }
        } while (choice != 5);
    }
    
    /**
     * <p>
     * Displays the option menu to the console.
     * </p>
     */
    private static void displayMenu() {
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }
    
    /**
     * <p>
     * Displays an error message to the console.
     * </p>
     * 
     * @param msg the message to be displayed to the console
     */
    public void displayErrorMsg(String msg) {
        System.out.println(msg);
    }
    
    /**
     * <p>
     * Displays a message to the console.
     * </p>
     * 
     * @param msg the error message to be displayed to the console
     */
    public void displayMsg(String msg) {
        System.out.println(msg);
    }
}
