package comp2526_assign2b;

import java.awt.Point;
import java.util.ArrayList;

/**
 * <p>
 * The Interface given to classes that are living (non-empty Cell types).
 * Contains methods to initialize the Object for its Cell, and to set the
 * Object to its Cell.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public interface Alive {
    
    /**
     * <p>
     * Initializes the Object for its Cell.
     * Sets the Background Color of the JPanel
     * </p>
     */
    public void init();
    
    /**
     * <p>
     * Adds the Object to the passed in Cell.
     * </p>
     * 
     * @param cell the Cell JPanel to add the Object to
     */
    public void setCell(Cell cell);

    /**
     * <p>
     * Adds another Alive object of the same type to an available adjacent cell.
     * </p>
     * 
     * @param adjacentCells the cells adjacent to the current Alive object
     * @return the Point of the childCell
     */
    public Point giveBirth(ArrayList<Cell> adjacentCells);

    /**
     * <p>
     * Return the number of lives of the Alive object.
     * </p>
     * 
     * @return the amount of lives
     */
    public int getLives();

    /**
     * <p>
     * Replenishes the health of the alive object.
     * </p>
     */
    public void eat();
}
