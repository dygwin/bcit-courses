package comp2526_assign1b;

import java.util.Scanner;

/**
 * <p>
 * Provides the menu for the user to interact with. It displays the menu, as
 * well as displays the output to the console for displaying Person objects
 * attributes. This class utilizes an AddressBook to carry out the choices
 * of the user in the menu.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class ConsoleUI implements UI {
    /**
     * <p>
     * A Scanner object that will read the users input through the System.in.
     * </p>
     */
    private final Scanner input;
    
    /**
     * <p>
     * The AddressBook object that will handle the users choices.
     * </p>
     */
    private AddressBook addressBook;
    
    /**
     * <p>
     * The constructor for the ConsoleUI.
     * </p>
     * <p>
     * Sets up an Scanner object to read the users input from System.in
     * </p>
     */
    public ConsoleUI() {
        input = new Scanner(System.in);
    }
    
    /**
     * <p>
     * Prompts the user to input a choice from 1 - 5. This method ensures
     * that the input is valid (an integer between 1 and 5). If the user
     * inputs an invalid choice, they are simply asked to choose again.
     * One the user has chosen, their choice is returned.
     * </p>
     * 
     * @return the choice of the user (1-5)
     */
    public int readChoice() {
        // the choice picked by the user, default is 4
        int choice   = 4;
        // whether the user has chosen a valid option yet, default is false
        boolean done = false;
        
        while (!done) {
            displayMsg("choice? ");
            
            // ensures that the user inputs an integer
            while (!input.hasNextInt()) {
                input.next();
                displayErrorMsg("Please enter a valid number");
            }
            
            choice = input.nextInt();
            
            //ensures that the users input is between 1 and 5
            if (choice > 0 && choice <= 5) {
                done = true;
            } else {
                displayErrorMsg("Your choice is incorrect, "
                        + "please try again");
            }
        }
        return choice;
    }
    
    /**
     * <p>
     * Prompts the user for the name and phone number of a Person object.
     * It ensures that the name and phone number are not longer than 15 and
     * 20 characters. If they are, they are automatically shortened.
     * It then creates a Person with those attributes, and returns that
     * Person object.
     * </p>
     * 
     * @return a Person object with the attributes that the user inputed
     */
    public Person readPerson() {
        // the name of the Person, as specified by the user
        String name  = "";
        // the phone number of the Person, as specified by the user
        String phone = "";
        
        name = readName();
        
        // cut the name off if it's longer than 15 characters
        if (name.length() > 15) {
            name = name.substring(0, 15);
        }
        
        displayMsg("Enter the persons phone number ");
        phone = input.next();
        
        // cut the phone number off if it's longer than 20 characters
        if (phone.length() > 20) {
            phone = phone.substring(0, 20);
        }
        
        return new Person(name, phone);
    }
    
    /**
     * <p>
     * Prompts the user to enter the name of a Person, assigns what they
     * input to the name String, then returns the name.
     * </p>
     * 
     * @return the name of the Person object, as inputed by the user
     */
    public String readName() {
        // the name of the Person, as specified by the user
        String name;
        
        displayMsg("Enter the persons name: ");
        name = input.next();
        
        return name;
    }
    
    /**
     * <p>
     * Displays the name and phone number of a single Person object. This method
     * is a support method for the displayAll() method. It is also used by the
     * AddressBook's display() method.
     * </p>
     * 
     * @param person the Person object whose name and number will be displayed.
     */
    public void display(Person person) {
        System.out.printf("%-15s", person.getName());
        System.out.printf("%-20s\n", person.getPhoneNumber());
    }
    
    /**
     * <p>
     * Displays all of the Person objects in the Person array that is passed
     * through the method. Before printing out the name and phone number of
     * every Person object in the array, it first checks to make sure that
     * the array is not empty. If its empty, it will display an error message.
     * Otherwise, it will label the Name and Phone Number columns and print
     * out the formatted name and number of every Person object by calling the
     * display() method for each Person.
     * </p>
     * 
     * @param people the Person array full of all the Person objects to display
     */
    public void displayAll(Person[] people) {
        if (people.length > 0) {
            System.out.printf("\n%-15s%-20s\n", "Name", "Phone Number");
            for (Person person : people) {
                display(person);
            }
			
            System.out.println("");
        } else {
            displayErrorMsg("\nNo contacts in the address book");
        }
    }
    
    /**
     * <p>
     * This is the main loop of the program. It will display the menu
     * to the user, and then prompt the user for their choice. Once the
     * user inputs a choice, the associated method will be carried out.
     * The choices include adding a person, deleting a person, finding
     * a person, and displaying all of the people in the database. The
     * choices are labeled 1 - 4, with an addition choice 5 to exit the
     * program.
     * </p>
     * 
     * @param book the AddressBook that will carry out the selection
     */
    public void run(AddressBook book) {
        // the users choice, default to 0
        int choice = 0;
        
        addressBook = book;
        
        do {
            displayMenu();
            
            // prompts the user to enter a choice, then assigns it to choice
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
                // should not get here
            }
        } while (choice != 5);
    }
    
    /**
     * <p>
     * Displays the menu of options that the user can choose from.
     * </p>
     */
    public void displayMenu() {
        System.out.println("\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }
    
    /**
     * <p>
     * Displays the String that is passed into it by printing the String
     * to the console. The message is intended to be an error message
     * indicating an error or failure.
     * </p>
     * 
     * @param msg the message to be displayed
     */
    public void displayErrorMsg(String msg) {
        System.out.println(msg);    
    }
    
    /**
     * <p>
     * Displays the String that is passed into by printing the String
     * to the console. The message is intended to be a confirmation
     * or prompt.
     * </p>
     * 
     * @param msg the message to be displayed
     */
    public void displayMsg(String msg) {
        System.out.println(msg);    
    }
}
