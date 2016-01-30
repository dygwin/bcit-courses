package comp2526_assign2b;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * <p>
 * Defines a Plant object, one of the possible Alive objects that can be held
 * by a Cell. It implements EdibleByHerbivore because it is exactly that. It
 * contains method to initialize itself for the Cell, and to add itself to
 * a Cell, and to give birth.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Plant extends JPanel implements Alive, EdibleByHerbivore, EdibleByOmnivore {
    /**
     * <p>
     * The maximum amount of turns the Plant can go without being eaten
     * it dies.
     * </p>
     */
    private static final int MAX_LIVES = 10;
    
    /**
     * <p>
     * The starting amount of lives for a Plant, starting at the maximum.
     * </p>
     */
    private int lives = MAX_LIVES;
    
    /**
     * <p>
     * The background color of the Plant, starting at Green
     * </p>
     */
    private Color color = Color.GREEN;
    
    /**
     * <p>
     * Initializes itself for the Cell by setting its background color to green.
     * </p>
     */
    public void init() {
        setBackground(color);
    }
    
    /**
     * <p>
     * Adds the Plant to the passed in Cell.
     * </p>
     * 
     * @param cell the JPanel for the Plant to be added to
     */
    public void setCell(Cell cell) {
        cell.add(this);
    }
    
    /**
     * <p>
     * Gives birth to a new Plant in one of the adjacent cells only if the
     * mating criteria is met. There must be at least 2 empty cell, 0 food
     * cells, and 3 mates in the adjacent cells. If it successfully mates,
     * the location of the child will be returned. Otherwise, null is
     * returned. It will also kill a plant after 10 turns.
     * </p>
     * 
     * @param adjacentCells the cells adjacent to the Plant
     * @return the location of the child
     */
    public Point giveBirth(ArrayList<Cell> adjacentCells) {
        // the possible cell for the child
        Cell candidateCell;
        // the amount of adjacent mates
        int mateCells = 0;
        // the adjacent empty cells
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        
        // find out what is contained in each of the adjacent cells
        for (int i = 0; i < adjacentCells.size(); i++) {
            if (adjacentCells.get(i).currentHeld() instanceof Plant) {
                mateCells++;
            } else if (adjacentCells.get(i).currentHeld() == null) {
                emptyCells.add(adjacentCells.get(i));
            }
        }
        
        lives--;
        color = new Color(0, (int) (color.getGreen() * .90), 0);
        setBackground(color);
        
        // if the requirements are met, then create a child in one of the empty cells
        if (emptyCells.size() >= 2 && mateCells >= 3) {
            candidateCell = emptyCells.get(RandomGenerator.nextNumber(emptyCells.size()));
            
            // update the candidateCell for the new child
            candidateCell.changeHeld(new Plant());
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
     * Replenishes health of the object.
     * </p>
     */
    public void eat() {
        // do nothing
    }
}
