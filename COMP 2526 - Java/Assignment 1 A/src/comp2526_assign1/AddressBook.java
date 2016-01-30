package comp2526_assign1;

import java.util.Scanner;

/**
 * <p>An AddressBook that takes input from the user via a Scanner object
 * and saves Person objects to an array, storing their name and phone
 * numbers. The array acts as a database that the user can then add new
 * Person objects to, remove Person objects from it, issue searches for
 * a specific Person object, or display all of the Person objects currently
 * in the array. The AddressBook has a run method that allows the user to
 * choose from those four methods (add, delete, search, and display), as
 * well as an option to exit the program.</p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class AddressBook {
    /**
     * <p>An array of Person objects to hold the people the user adds.</p>
     */
    private Person[] database;
    /**
     * <p>The Scanner object used to get input from the user.</p>
     */
    private Scanner  input;
    
    /**
     * <p>The constructor for the AddressBook class.</p>
     * <p>Sets the initial size of the database array to 0, and creates
     * a Scanner object to read input from the user through the System.in.</p>
     */
    public AddressBook() {
        database = new Person[0];
        input    = new Scanner(System.in);
    }
    
    /**
     * <p>A support method for the addPerson() method.</p>
     * <p>It first creates a temporary Person array that is one size
     * larger than the current database. It then copies in all of the
     * current Person objects from the database into the temp array,
     * then adds the Person object passed into it to the last position
     * in the temp array. Finally, it redirects database to reference
     * the temp array, essentially increasing its size and adding the
     * new Person object to it.</p>
     * 
     * @param person a Person object to be added to the database
     */
    public void add(final Person person) {
        //create a temporary array to increase the size
        Person[] temp = new Person[database.length + 1];
        
        //copy in the old names
        if (database.length != 0) {
            for (int i = 0; i < database.length; i++) {
                temp[i] = database[i];
            }
        }
        
        //add in the new name
        temp[temp.length - 1] = person;
        
        //assign database to the new array
        database = temp;
    }
    
    /**
     * <p>A support method for the findPerson() and remove() methods.</p>
     * <p>Goes through every Person object in the database array
     * and does a case insensitive check if any of their name attributes 
     * match that of the name passed into the method. If it finds a match,
     * it will return the Person objects index in the array. If it doesn't
     * find a match,it will return -1.</p>
     * 
     * @param name the name of the Person to search for
     * @return the index of the person if found, otherwise return -1
     */
    public int search(final String name) {
        for (int i = 0; i < database.length; i++) {
            //case insensitive searching
            if (database[i].getName().toUpperCase().equals(name.toUpperCase())) {
                return i; 
            }
        }
        return -1;
    }
    
    /**
     * <p>Outputs every Person objects name and phone number that is
     * currently in the database, formatted to a certain field width,
     * and preceded by a Name and Phone Number label. If there are no
     * Person objects currently in the database, a relevant message
     * is written to the console.</p>
     */
    public void displayAll() {
        if (database.length > 0) {
            System.out.printf("\n%-15s%-20s", "Name", "Phone Number");
			
            for (Person person: database) {
                System.out.printf("\n%-15s", person.getName());
                System.out.printf("%-20s", person.getNumber());
            }
			
            System.out.println("");
        } else {
            System.out.println("\nNo contacts in the address book");
        }
    }
    
    /**
     * <p>A support method for the deletePerson() method.</p>
     * <p>Retrieves the index of a person by invoking the search() method,
     * then removes the Person object at that index in the database array,
     * and reduces the size of the database by 1.</p>
     * 
     * @param name the name of the Person object to remove
     * @return whether the Person object was successfully removed or not
     */
    public boolean remove(final String name) {
        //the position of the Person, as returned by the search() method
        int pos = search(name);
		
        if (pos >= 0) {
            Person[] temp = new Person[database.length - 1];
			
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos+1, temp, pos, database.length-pos-1);
			
            database = temp;
			
            return true;
        }
        return false;
    }
    
    /**
     * <p>Displays the options for the user to choose from to the console.</p>
     * <p>Options include Add, Delete, Search, Display All, and Exit.</p>
     */
    public void displayMenu() {
        System.out.println("\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }
    
    /**
     * <p>Reads the users choice of option for the menu display,
     * and ensures that the user has chosen a valid option, or exits
     * the program if the user has chosen to.</p>
     * 
     * @return the choice of the user
     */
    public int getChoice() {
        //the choice picked by the user, default is 4
        int choice = 4;
        //whether the user has chosen a valid option yet, default to false
        boolean done = false;
		
        while (!done) {
            System.out.print("choice? ");
			
            try {
                //ensures that the user inputs an integer
                while (!input.hasNextInt()) {
                    input.next();
                    System.out.println("Please enter a valid number");
                }
				
                choice = input.nextInt();
            } catch (Exception e) {
                //do nothing
            }
			
            if (choice > 0 && choice <= 5) {
                done = true;
            }  else {
                System.out.println("Your choice is incorrect, please try again");
            }
        }
        return choice;
    }
    
    /**
     * <p>Reads in the name and phone number of a person, and invokes the
     * add() method to add the Person object to the database. It also ensures
     * the name and phone number wont be longer than its maximum character count
     * (15 and 20 spaces).</p>
     */
    public void addPerson() {
        //the name of the Person, as specified by the user
        String name = "";
        //the phone number of the Person, as specified by the user
        String phone = "";
		
        try {
            System.out.print("\nEnter the persons name ");
            name = input.next();
			
            //cut the name off if it's longer than 15 characters
            if (name.length() > 15) {
                name = name.substring(0, 15);
            }
			
            System.out.print("Enter the persons phone number ");
            phone = input.next();
			
            //cut the phone number off if it's longer than 20 characters
            if (phone.length() > 20) {
                phone = phone.substring(0, 20);
            }
        } catch (Exception e) {
            //do nothing
        }
		
        add(new Person(name, phone));
        System.out.println("\n" + name + " was added succesfully");
    }
    
    /**
     * <p>Reads a name from the user, and invokes the remove() method with
     * the name as its argument to remove the Person object from the database
     * that matches that name. It then prints out whether the Person object
     * was deleted successfully or not.</p>
     */
    public void deletePerson() {
        //the name of the Person, as specified by the user
        String name = "";
		
        try {
            System.out.print("\nEnter the persons name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
            //do nothing
        }
		
        if (!remove(name)) {
            System.out.println("Could not delete " + name);
        } else {
            System.out.println(name + " was deleted successfully");
        }
    }
    
    /**
     * <p>Reads in a name from the user and invokes the search() method
     * to search the database with the Person object that matches the name.
     * If -1 is returned from search(), then "No such person" is printed to
     * the console. If a number other than -1 is returned, than the name and
     * phone number of the Person at that position in the database is printed
     * to the console.</p>
     */
    public void findPerson() {
        //the name of the Person, as specified by the user
        String name = "";
		
        try {
            System.out.print("\nEnter the persons name ");
            name = input.next();
        } catch (Exception e) {
            //do nothing
        }
		
        //the position of the Person, as returned by the search() method
        int pos = search(name);
		
        if (pos >= 0) {
            System.out.printf("\n%-15s%-20s", "Name", "Phone Number");
            System.out.printf("\n%-15s", database[pos].getName());
            System.out.printf("%-20s\n", database[pos].getNumber());
        } else {
            System.out.println("\nNo such person");
        }
    }
    
    /**
     * <p>Displays the options of the AddressBook by calling the displayMenu()
     * method, then gets the users choice by calling the getChoice() method,
     * and finally invokes the method that corresponds to the users choice.
     * This is continually done after every choice is completed, until finally
     * the user chooses to exit the program by entering choice 5.</p>
     */
    public void run() {
        //the users choice, default to 0
        int choice = 0;
		
        do {
            displayMenu();
            choice = getChoice();
            switch(choice) {
            case 1:
                addPerson();
                break;
            case 2:
                deletePerson();
                break;
            case 3:
                findPerson();
                break;
            case 4:
                displayAll();
                break;
            default:
                //should not get here
            }
        } while (choice != 5);
    }
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     * <p>It creates a new AddressBook object and invokes its run method.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new AddressBook().run();
    }
}
