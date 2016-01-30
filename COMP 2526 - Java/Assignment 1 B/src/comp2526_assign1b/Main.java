package comp2526_assign1b;

/**
 * <p>
 * The main class that runs the program. It creates a ConsoleUI and an
 * AddressBook, then calls the UI's run() method to start the program.
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
     * It creates a new AddressBook object and invokes its run method.
     * </p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        final UI          ui;
        final AddressBook book;

        ui   = new ConsoleUI();
        book = new AddressBook(ui);
        ui.run(book);
    }
}
