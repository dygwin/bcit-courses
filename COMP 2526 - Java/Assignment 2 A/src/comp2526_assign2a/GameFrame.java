package comp2526_assign2a;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * <p>
 * A JFrame that will contain all of the Cells in its associated World
 * object by populating its gridLayout with every Cell in the World, in
 * their row and column order. It also contains a MouseListener that will
 * listen for mouse clicks, in order to invoke the takeTurn() method and
 * move Cells and what they are holding in the World.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class GameFrame extends JFrame {
    /**
     * <p>
     * The World object that will contain all of the Cell objects and handle
     * the movement of the objects in the World.
     * </p>
     */
    private final World world;
    
    /**
     * <p>
     * The constructor for the GameFrame, associates a World object with the
     * GameFrame.
     * </p>
     * 
     * @param w the World object to be associated with the GameFrame
     */
    public GameFrame(final World w) {
        world = w;
    }
    
    /**
     * <p>
     * Initializes the GameFrame by giving the JFrame a title, settings its
     * layout to a GridLayout using the amount of rows and columns of the
     * World object, and then populates the GameFrame with every Cell in the
     * World object. Finally, it adds a MouseListener to listen for clicks in
     * order to invoke takeTurn().
     * </p>
     */
    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }

        addMouseListener(new TurnListener(this));
    }
    
    /**
     * <p>
     * This is invoked when the MouseListener receives a mouse click. It will
     * invoke the World's takeTurn() method, then repaint the GameFrame to
     * update the position of Cells and what they contain.
     * </p>
     */
    public void takeTurn() {
        world.takeTurn();
        repaint();
    }
}
