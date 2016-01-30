package comp2526_assign1c_1b;

/**
 * <p>
 * Uses command line arguments to determine whether the user wants to
 * create a console-based or GUI-based AddressBook and then creates the
 * AddressBook with the chosen UI and runs it.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 *
 */
public class Main {
    /**
     * <p>
     * This is the main method (entry point) that gets called by the JVM.
     * </p>
     * <p>
     * It creates a new AddressBook using the selects UI type and runs it.
     * </p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        /**
         * <p>
         * The type of UI to use for the program (command line or gui).
         * </p>
         */
        UI ui = null;
        
        /**
         * <p>
         * The address book to carry out the methods of the UI.
         * </p>
         */
        AddressBook book;
        
        if (args.length > 0) {
            if (args[0].compareToIgnoreCase("console") == 0) {
                ui = new ConsoleUI();
            } else if (args[0].compareToIgnoreCase("gui") == 0) {
                ui = new GUI();
            }
        }
        if (ui != null) {
            book = new AddressBook(ui);
            ui.run(book);
        }
    }
}
