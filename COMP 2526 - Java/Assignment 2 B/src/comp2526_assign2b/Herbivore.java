package comp2526_assign2b;

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
 * add itself to a Cell, and to Move to different Cells, and to give birth.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Herbivore extends JPanel implements Alive, Movable, EdibleByCarnivore, EdibleByOmnivore {
    /**
     * <p>
     * The maximum amount of turns the Herbivore can go without eating before
     * it dies.
     * </p>
     */
    private static final int MAX_LIVES = 10;
    
    /**
     * <p>
     * The starting amount of lives for a Herbivore, starting at the maximum.
     * </p>
     */
    private int lives = MAX_LIVES;
    
    /**
     * <p>
     * The background color of the Herbivore, starting at yellow
     * </p>
     */
    private Color color = Color.YELLOW;
    
    /**
     * <p>
     * Initializes the Herbivore by setting its background color to yellow.
     * </p>
     */
    public void init() {
        setBackground(color);
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
        
        // the possible Plant Cells to move to
        ArrayList<Cell> plantCells = new ArrayList<Cell>();
        // the possible Empty Cells to move to
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        // the candidate Cell to move to, before checking if occupied
        Cell candidateCell;
        
        // looks for Plant Cells in the list of adjacent Cells
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).currentHeld() instanceof EdibleByHerbivore) {
                // adds the Plant cell to the plantCells ArrayList
                plantCells.add(cells.get(i));
            } else if (cells.get(i).currentHeld() == null) {
                emptyCells.add(cells.get(i));
            }
        }
            
        // if we found any adjacent Plant Cells, move to a random one
        if (plantCells.size() > 0) {
            candidateCell = plantCells.get(RandomGenerator.nextNumber(plantCells.size()));
        } else if (emptyCells.size() > 0){
            // move to any of the adjacent Cells
            candidateCell = emptyCells.get(RandomGenerator.nextNumber(emptyCells.size()));
        } else {
            candidateCell = null;
        }
            
        newCell = candidateCell;
        
        if (newCell != null) {
            // reset to max lives if newCell is a Plant, otherwise lose 1 life
            if (newCell.currentHeld() instanceof EdibleByHerbivore) {
                lives = MAX_LIVES;
                color = Color.YELLOW;
                setBackground(color);
            } else {
                lives -= 1;
                color = new Color((int) (color.getRed() * .90), (int) (color.getGreen() * .90), 0);
                setBackground(color);
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
        
        lives -= 1;
        
        return oldCell.getLocation();
    }
    
    /**
     * <p>
     * Gives birth to a new Herbivore in one of the adjacent cells only if the
     * mating criteria is met. There must be at least 1 empty cell, 2 food
     * cells, and 1 mate in the adjacent cells. If it successfully mates,
     * the location of the child will be returned. Otherwise, null is
     * returned.
     * </p>
     * 
     * @param adjacentCells the cells adjacent to the Herbivore
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
            if (adjacentCells.get(i).currentHeld() instanceof Herbivore) {
                mateCells++;
            } else if (adjacentCells.get(i).currentHeld() instanceof EdibleByHerbivore) {
                foodCells++;
            } else if (adjacentCells.get(i).currentHeld() == null) {
                emptyCells.add(adjacentCells.get(i));
            }
        }
        
        // if the requirements are met, then create a child in one of the empty cells
        if (emptyCells.size() > 0 && foodCells >= 2 && mateCells > 0) {
            candidateCell = emptyCells.get(RandomGenerator.nextNumber(emptyCells.size()));
            
            // update the candidateCell for the new child
            candidateCell.changeHeld(new Herbivore());
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
