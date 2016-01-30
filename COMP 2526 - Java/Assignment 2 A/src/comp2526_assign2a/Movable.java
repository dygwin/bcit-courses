package comp2526_assign2a;

import java.awt.Point;
import java.util.ArrayList;

/**
 * <p>
 * The Interface given to Alive objects that can Move.
 * It contains a method for the Object to move, along with the other methods
 * contained in Alive.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public interface Movable extends Alive {
    /**
     * <p>
     * Moves the Movable object to one of the adjacent Cells passed into it,
     * and updates the held state of the new and old Cells. It will return the
     * coordinates of the new Cell it has moved to, or possibly null if it has
     * not moved (died).
     * </p>
     * 
     * @param cells the adjacent Cells the Movable object can move to
     * @param oldCell the Cell the Movable object was at before moving
     * @return the location of the new Cell the Movable object has moved to
     */
    public Point move(ArrayList<Cell> cells, Cell oldCell);
}
