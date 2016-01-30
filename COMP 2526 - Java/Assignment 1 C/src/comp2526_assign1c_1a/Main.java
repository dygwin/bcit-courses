package comp2526_assign1c_1a;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

/**
 * <p>
 * Creates a graphical user interface for an interactive address book program.
 * Upon starting, the run() method is invoked which displays the option menu,
 * from which the user can then select an option using their keyboard and
 * add, delete, display, and search for various Person objects that are being
 * help in the People array.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Main {
    /**
     * <p>
     * This is the main method (entry point) that gets called by the JVM.
     * </p>
     * <p>
     * Runs the run() method to start the GUI program.
     * </p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new Main().run();
    }
    
    /**
     * <p>
     * An array of Person objects to act as the database of Persons.
     * </p>
     */
    private Person[] people = new Person[0];
    
    /**
     * <p>
     * The users choice when prompted in the menu screen.
     * </p>
     */
    private int choice = 0;
    
    /**
     * <p>
     * An anonymous JFrame that will act as the GUI to display information
     * and take input. Its paint method is overwritten to display the menu.
     * </p>
     */
    JFrame frame = new JFrame() {
        /**
         * <p>
         * Clears the frame and displays the menu.
         * </p>
         * 
         * @param g the graphics object to modify and draw on
         */
        private void displayMenu(Graphics g){
            // color to clear screen with
            Color c = this.getBackground();
            // use that color
            g.setColor(c);
            // color in a rectangle the size of the window with that color
            g.fillRect(0,0,this.getWidth(), this.getHeight());
            // set color to draw with now to black
            g.setColor(Color.black);
            g.drawString("1) Add",100,100);
            g.drawString("2) Delete",100,120);
            g.drawString("3) Search",100,140);
            g.drawString("4) Display All",100,160);
            g.drawString("5) Exit",100,180);
          }
        
        /**
         * <p>
         * Invokes the displayMenu() method whenever the paint() and repaint()
         * methods are invoked.
         * </p>
         * 
         * @param g the Graphics object to paint on
         */
        public void paint(Graphics g) {
            displayMenu(g);
        }
    };

    /**
     * <p>
     * A support method for the addPerson() method.
     * </p>
     * <p>
     * It first creates a temporary Person array that is one size larger than
     * the current database. It then copies in all of the current Person objects
     * from the database into the temp array, then adds the Person object passed
     * into it to the last position in the temp array. Finally, it redirects
     * database to reference the temp array, essentially increasing its size and
     * adding the new Person object to it.
     * </p>
     * 
     * @param person a Person object to be added to the database
     */
    public void add(final Person person) {
        // create a temporary array to increase the size
        Person[] temp = new Person[people.length + 1];

        // copy in the old names
        if (people.length != 0) {
            for (int i = 0; i < people.length; i++) {
                temp[i] = people[i];
            }
        }

        // add in the new name
        temp[temp.length - 1] = person;

        // assign database to the new array
        people = temp;
    }

    /**
     * <p>
     * A support method for the findPerson() and remove() methods.
     * </p>
     * <p>
     * Goes through every person in the people array and does a case sensitive
     * check if any of their name attributes match that of the name passed into
     * the method. If it finds a match, it will return the Person objects index
     * in the array. If it doesn't find a match,it will return -1.
     * </p>
     * 
     * @param name the name of the Person to search for
     * @return the index of the person if found, otherwise return -1
     */
    public int search(final String name) {
        for (int i = 0; i < people.length; i++) {
            // case insensitive searching
            if (people[i].getName().equals(name)) {
                return i;
            }
        }
		
        return -1;
    }

    /**
     * <p>
     * Outputs every Person objects name and phone number that is currently in
     * the database. If there are no Person objects currently in the database,
     * an error message is displayed on the screen.
     * </p>
     */
    public void displayAll() {
        // the information of the person to be displayed
        String msg = "";
        
        if (people.length > 0) {
            for (Person person : people) {
                msg += person.getName() + "    " + person.getNumber() + "\n";
            }
            JOptionPane.showMessageDialog(frame, msg, "Address book entries",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            displayErrorMsg("\nNo contacts in the address book");
        }
    }

    /**
     * <p>
     * A support method for the deletePerson() method.
     * </p>
     * <p>
     * Retrieves the index of a person by invoking the search() method, then
     * removes the Person object at that index in the database array, and
     * reduces the size of the database by 1.
     * </p>
     * 
     * @param name the name of the Person object to remove
     * @return whether the Person object was successfully removed or not
     */
    public boolean remove(final String name) {
        // the position of the Person, as returned by the search() method
        int pos = search(name);

        if (pos >= 0) {
            Person[] temp = new Person[people.length - 1];
            System.arraycopy(people, 0, temp, 0, pos);
            System.arraycopy(people, pos + 1, temp, pos, people.length - pos
                    - 1);
            people = temp;
			
            return true;
        }
		
        return false;
    }

    /**
     * <p>
     * Reads in the name and phone number of a person, and invokes the add()
     * method to add the Person object to the database. It also ensures the name
     * doesn't already exist in the database. If it does already exist, an error
     * message is displayed.
     * </p>
     */
    public void addPerson() {
        // the name of the Person, as specified by the user
        String name = JOptionPane.showInputDialog("Enter the persons name");
        // the phone number of the Person, as specified by the user
        String phone = JOptionPane.showInputDialog("Enter the persons phone number");

        // check if the name is already in the database
        if (search(name) == -1) {
            Person person = new Person(name, phone);
            add(person);
            JOptionPane.showMessageDialog(frame, name + " was added successfully", "Success",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            displayErrorMsg(name + " already exists");
        }
    }

    /**
     * <p>
     * Reads a name from the user, and invokes the remove() method with the name
     * as its argument to remove the Person object from the database that
     * matches that name. It then displays whether the Person object was
     * deleted successfully or not.
     * </p>
     */
    public void deletePerson() {
        // the name of the Person, as specified by the user
        String name = JOptionPane.showInputDialog("Enter the persons name");
        
        if (!remove(name)) {
            displayErrorMsg("Could not delete " + name);
        } else {
            JOptionPane.showMessageDialog(frame, name + " was deleted successfully", "Success",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * <p>
     * Reads in a name from the user and invokes the search() method to search
     * the database with the Person object that matches the name. If -1 is
     * returned from search(), then "No such person" is displayed.
     * If a number other than -1 is returned, than the name and phone number of
     * the Person at that position in the database is displayed on the screen.
     * </p>
     */
    public void findPerson() {
        // the name of the Person, as specified by the user
        String name = JOptionPane.showInputDialog("Enter the persons name");
        // the string to hold the persons info to be displayed
        String msg = "";
        
        // the position of the Person, as returned by the search() method
        int pos = search(name);

        if (pos >= 0) {
            msg = people[pos].getName() + "     " + people[pos].getNumber();
            
            JOptionPane.showMessageDialog(frame, msg, "Address book enteries",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            displayErrorMsg("\nNo such person");
        }
    }

    /**
     * Purpose: displays a message on the title bar of the window
     * 
     * @param msg String - non-error message to display
     */
    public void displayMsg(String msg) {
        frame.setTitle(msg);
    }

    /**
     * Purpose: displays an error message in a dialog box
     * 
     * @param msg String - error msg to display
     */
    public void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * <p>
     * Sets up the frames size and visibility, and adds a key listener to
     * get the users choice on the menu.
     * </p>
     */
    public void run() {
        // the users choice, default to 0
        frame.setSize(400, 400);// fix window size
        frame.setVisible(true);// make window visible
        frame.addKeyListener(new KeyBoardInput());// listen to keyboard input
    }
    
    /**
     * <p>
     * Invokes a certain method relevant to the users choice when they select
     * a number on the menu. Valid choices are 1 - 5.
     * (addPerson, deletePerson, findPerson, displayAll, and exit)
     * </p>
     */
    private void evaluateChoice(){

        switch(choice)
        {
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
            case 5:
              System.exit(0);
              break;

            default:
                //should not get here
        }

     }
    
    /**
     * <p>
     * Gets the users input via their keyboard for use on the menu screen, to
     * determine their choice. If they choose an invalid option (not 1-5) them
     * -1 is simply returned, which does nothing. If a meaningful number is
     * selected, then that number is returned and used in the evaluateChoice()
     * method.
     * </p>
     * 
     * @author Trevor Broderick A00925723
     * @version 1.0
     */
    private class KeyBoardInput extends KeyAdapter {

        /**
         * Purpose: When a key is pressed on the keyboard this method is called
         * 
         * @param e KeyEvent - key pressed and other information
         */
        public void keyTyped(KeyEvent e) {
            // set the "choice" data member of the outer class GUI
            // to get the integer value, get the character value of the key
            // pressed, make it a string and ask the Integer class to parse it
            try {
                choice = Integer.parseInt("" + e.getKeyChar());
                // if it wasn't an integer key pressed then make an invalid
                // choice
            } catch (Exception except) {
                choice = -1;// this will result in nothing happening
            }
            evaluateChoice(); // GUI method to call the addressBook to perform
                              // task
        }
    }

}
