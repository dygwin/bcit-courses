package comp2526_assign2a;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * <p>
 * Defines a Herbivore, one of the possible Alive objects that a Cell can hold.
 * It implements Movable because it has the ability to Move between Cells.
 * It contains a number of lives, and loses 1 each time it moves. If it moves
 * to a Cell that contains a Plant and eats the Plant, its lives will be
 * restored to the maximum amount. It contains methods to initialize itself,
 * add itself to a Cell, and to Move to different Cells.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Herbivore extends JPanel implements Movable {
    /**
     * <p>
     * The maximum amount of turns the Herbivore can go without eating before
     * it dies.
     * </p>
     */
    private static final int MAX_LIVES = 6;
    
    /**
     * <p>
     * The starting amount of lives for a Herbivore, starting at the maximum.
     * </p>
     */
    private int lives = MAX_LIVES;
    
    /**
     * <p>
     * Initializes the Herbivore by setting its background color to yellow.
     * </p>
     */
    public void init() {
        setBackground(Color.YELLOW);
    }
    
    /**
     * <p>
     * Adds the Herbivore to the Cell passed into it.
     * </p>
     * 
     * @param cell the cell the Herbivore should be added to
     */
    public void setCell(Cell cell) {
        cell.add(this);
    }
    
    /**
     * <p>
     * Moves the Herbivore to one of the adjacent Cells from the ArrayList
     * passed into it. It first searches the Cells for one containing a
     * Plant. Plant Cells are priority and the Herbivore will move to a
     * random Plant Cell if available. If there are no adjacent Plant Cells,
     * then the Herbivore will move to any random adjacent Cell, so long as
     * it does not contain another Herbivore. If the Cell it moves to contains
     * a Plant, its lives will be replenished to the maximum, otherwise, it
     * will lose 1 life. When moving Cells, changeHeld() will be called on the
     * old Cell to set its held to null. If the Herbivore has not run out of
     * lives, then it will call changeHeld() on its new Cell it have itself
     * set on that Cell, and its new Cells location will be returned as a
     * Point. If the Herbivore has died, then null will be returned.
     * </p>
     * 
     * @param cells the adjacent cells the Herbivore can move to
     * @param oldCell the cell that the Herbivore is currently in
     * 
     * @return the location of the newCell in the board in the form of a Point
     */
    public Point move(ArrayList<Cell> cells, Cell oldCell) {
        // the new Cell the Herbivore will be moved to
        Cell newCell;
        
        // picks a new unoccupied Cell to move to, Plant Cells are priority
        do {
            // the possible Plant Cells to move to
            ArrayList<Cell> plantCells = new ArrayList<Cell>();
            // the candidate Cell to move to, before checking if occupied
            Cell candidateCell;
            
            // looks for Plant Cells in the list of adjacent Cells
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).currentHeld() instanceof EdibleByHerbivore) {
                    // adds the Plant cell to the plantCells ArrayList
                    plantCells.add(cells.get(i));
                }
            }
            
            // if we found any adjacent Plant Cells, move to a random one
            if (plantCells.size() > 0) {
                candidateCell = plantCells.get(RandomGenerator.nextNumber(plantCells.size()));
            } else {
                // move to any of the adjacent Cells
                candidateCell = cells.get(RandomGenerator.nextNumber(cells.size()));
            }
            
            newCell = candidateCell;
        } while (newCell.currentHeld() instanceof Herbivore);
        
        // reset to max lives if newCell is a Plant, otherwise lose 1 life
        if (newCell.currentHeld() instanceof EdibleByHerbivore) {
            lives = MAX_LIVES;
        } else {
            lives -= 1;
        }
        
        // the Herbivores old Cell now holds nothing (null)
        oldCell.changeHeld(null);
        
        // if Herbivore hasn't died, move to new Cell and return its location
        if (lives > 0) {
            newCell.changeHeld(this);
            return new Point(newCell.getLocation());
        }
        
        // if the Herbivore has died, return null
        return null;
    }
}
