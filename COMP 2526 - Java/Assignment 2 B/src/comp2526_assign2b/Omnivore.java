package comp2526_assign2b;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * <p>
 * Defines a Omnivore, one of the possible Alive objects that a Cell can hold.
 * It implements Movable because it has the ability to Move between Cells.
 * It contains a number of lives, and loses 1 each time it moves. If it moves
 * to a Cell that contains a Carnivore, Herbivore or Plant and eats them, its
 * lives will be restored to the maximum amount. It contains methods to 
 * initialize itself, add itself to a Cell, and to Move to different Cells,
 * and to give birth.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Omnivore extends JPanel implements Alive, Movable {
    /**
     * <p>
     * The maximum amount of turns the Omnivore can go without eating before
     * it dies.
     * </p>
     */
    private static final int MAX_LIVES = 2;
    
    /**
     * <p>
     * The starting amount of lives for a Omnivore, starting at the maximum.
     * </p>
     */
    private int lives = MAX_LIVES;
    
    /**
     * <p>
     * The background color of the Omnivore, starting at blue.
     * </p>
     */
    private Color color = Color.BLUE;
    
    /**
     * <p>
     * Initializes the Omnivore by setting its background color to blue.
     * </p>
     */
    public void init() {
        setBackground(color);
    }
    
    /**
     * <p>
     * Adds the Omnivore to the Cell passed into it.
     * </p>
     * 
     * @param cell the cell the Omnivore should be added to
     */
    public void setCell(Cell cell) {
        cell.add(this);
    }
    
    /**
     * <p>
     * Moves the Omnivore to one of the adjacent Cells from the ArrayList
     * passed into it. It first searches the Cells for one containing a
     * Herbivore, Carnivore, or Plant. Food Cells are priority and the Omnivore
     * will move to a random food Cell if available. If there are no 
     * adjacent food Cells, then the Omnivore will move to any random 
     * adjacent Cell, so long as it does not contain another Omnivore. 
     * If the Cell it moves to contains food, its lives will be 
     * replenished to the maximum, otherwise, it will lose 1 life.
     * When moving Cells, changeHeld() will be called on the
     * old Cell to set its held to null. If the Omnivore has not run out of
     * lives, then it will call changeHeld() on its new Cell it have itself
     * set on that Cell, and its new Cells location will be returned as a
     * Point. If the Omnivore has died, then null will be returned.
     * </p>
     * 
     * @param cells the adjacent cells the Omnivore can move to
     * @param oldCell the cell that the Omnivore is currently in
     * 
     * @return the location of the newCell in the board in the form of a Point
     */
    public Point move(ArrayList<Cell> cells, Cell oldCell) {
        // the new Cell the Omnivore will be moved to
        Cell newCell;
        
        // the possible food Cells to move to
        ArrayList<Cell> foodCells = new ArrayList<Cell>();
        // the possible empty Cells to move to
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        // the candidate Cell to move to, before checking if occupied
        Cell candidateCell;
            
        // looks for food Cells in the list of adjacent Cells
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).currentHeld() instanceof EdibleByOmnivore) {
                // adds the food cell to the foodCells ArrayList
                foodCells.add(cells.get(i));
            } else if (cells.get(i).currentHeld() == null) {
                emptyCells.add(cells.get(i));
            }
        }
            
        // if we found any adjacent Herbivore Cells, move to a random one
        if (foodCells.size() > 0) {
            candidateCell = foodCells.get(RandomGenerator.nextNumber(foodCells.size()));
        } else if (emptyCells.size() > 0) {
            // move to any of the adjacent Cells
            candidateCell = emptyCells.get(RandomGenerator.nextNumber(emptyCells.size()));
        } else {
            candidateCell = null;
        }
            
        newCell = candidateCell;
        
        if (newCell != null) {
            // reset to max lives if newCell is a Omnivore, otherwise lose 1 life
            if (newCell.currentHeld() instanceof EdibleByOmnivore) {
                lives = MAX_LIVES;
                color = Color.BLUE;
                setBackground(color);
            } else {
                lives -= 1;
                color = new Color(0, 0, (int) (color.getBlue() * .70));
                setBackground(color);
            }
            
            // the Omnivores old Cell now holds nothing (null)
            oldCell.changeHeld(null);
            
            // if Omnivores hasn't died, move to new Cell and return its location
            if (lives > 0) {
                newCell.changeHeld(this);
                
                return new Point(newCell.getLocation());
            }

            // if the Omnivore has died, return null
            return null;
        }
        
        lives -= 1;
        
        return oldCell.getLocation();
    }
    
    /**
     * <p>
     * Gives birth to a new Omnivore in one of the adjacent cells only if the
     * mating criteria is met. There must be at least 3 empty cell, 3 food
     * cells, and 1 mate in the adjacent cells. If it successfully mates,
     * the location of the child will be returned. Otherwise, null is
     * returned.
     * </p>
     * 
     * @param adjacentCells the cells adjacent to the Omnivore
     * @return the location of the child
     */
    public Point giveBirth(ArrayList<Cell> adjacentCells) {
        // the possible cell for the child
        Cell candidateCell;
        // the amount of adjacent mates
        int mateCells = 0;
        // the amount of adjacent food
        int foodCells = 0;
        // the adjacent empty cells
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        
        // find out what is contained in each of the adjacent cells
        for (int i = 0; i < adjacentCells.size(); i++) {
            if (adjacentCells.get(i).currentHeld() instanceof Omnivore) {
                mateCells++;
            } else if (adjacentCells.get(i).currentHeld() instanceof EdibleByOmnivore) {
                foodCells++;
            } else if (adjacentCells.get(i).currentHeld() == null) {
                emptyCells.add(adjacentCells.get(i));
            }
        }
        
        // if the requirements are met, then create a child in one of the empty cells
        if (emptyCells.size() >= 3 && foodCells >= 3 && mateCells > 0) {
            candidateCell = emptyCells.get(RandomGenerator.nextNumber(emptyCells.size()));
            
            // update the candidateCell for the new child
            candidateCell.changeHeld(new Omnivore());
            candidateCell.revalidate();
            candidateCell.repaint();
            
            return candidateCell.getLocation();
        } else {
            return null;
        }
    }
    
    /**
     * <p>
     * Return the number of lives of the Alive object.
     * </p>
     * 
     * @return the amount of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * <p>
     * Replenishes health.
     * </p>
     */
    public void eat() {
        lives = MAX_LIVES;
    }
}
