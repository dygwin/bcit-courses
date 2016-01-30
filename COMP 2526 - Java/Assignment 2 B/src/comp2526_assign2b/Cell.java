package comp2526_assign2b;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * <p>
 * Defines a Cell JPanel whose purpose is to contain other Alive Objects, or
 * possibly nothing (Empty). It contains the coordinates of itself in
 * the World, what type of Cell it is, and what it is currently holding. It
 * contains methods to set up itself and add a Alive object to itself, and
 * uses a gridLayout to manage its contents (1 or 0 Alive Objects). It also
 * contains methods to return its coordinates, get what it's currently holding,
 * change what it's holding, return its adjacent Cells in the board, and cycle
 * itself by moving what it is holding (if its of type Movable) to an adjacent
 * cell.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Cell extends JPanel {
    /**
     * <p>
     * The possible types that the Cell can be.
     * </p>
     * 
     * @author Trevor Broderick
     * @version 1.0
     */
    enum Type {Empty, Plant, Herbivore, Carnivore, Omnivore};
    
    /**
     * <p>
     * The coordinates of the Cell on the board.
     * </p>
     */
    private Point location;
    
    /**
     * <p>
     * What the Cell is currently holding. Can either be a Plant, a Herbivore,
     * or null if it isn't holding anything.
     * </p>
     */
    private Alive held;
    
    /**
     * <p>
     * The constructor for the Cell class. Sets the location of the Cell,
     * and what held by the Cell based on the Type passed into it.
     * </p>
     * 
     * @param type the Type of the cell (Empty, Plant, or Herbivore)
     * @param row the row of the Cell on the board
     * @param column the column of the Cell on the board
     */
    public Cell(Type type, int row, int column) {
        if (type == Type.Plant) {
            held = new Plant();
        } else if (type == Type.Herbivore) {
            held = new Herbivore();
        } else if (type == Type.Carnivore) {
            held = new Carnivore();
        } else if (type == Type.Omnivore) {
            held = new Omnivore();
        } else {
            held = null;
        }
        
        location = new Point(row, column);
    }
    
    /**
     * <p>
     * Sets up the cell by first removing all components from it, then calling
     * the init() and setCell() functions of what it's holding (if it's not
     * empty) to determine its background color and add the component to the
     * Cell. It then sets the border of the Cell and its layout.
     * </p>
     */
    public void init() {
        removeAll();
        
        if (held != null) {   
            held.init();
            held.setCell(this);
        }
        
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridLayout(1, 1));
    }
    
    /**
     * <p>
     * Cycles the cell if it is holding a Movable object by calling the objects
     * move() method. This will move what it's holding to one of the given 
     * adjacent Cells, and empty itself. It will then return the location of 
     * the new Cell that the object has moved to, or null if it wasn't holding
     * a Movable object in the first place.
     * </p>
     * 
     * @param adjacentCells the possible Cells the Movable object can move to
     * @return the location of the Cell the Movable object has moved to
     */
    public Point cycle(ArrayList<Cell> adjacentCells) {
        if (held instanceof Movable) {
            Point newCell = ((Movable)held).move(adjacentCells, this);
            return newCell;
        }
        return null;
    }
    
    /**
     * <p>
     * Attempt to give birth to a new Alive object of the same type as held.
     * If it is successful, it will return the new position of the child.
     * Otherwise, it will pass down a null reference.
     * </p>
     * 
     * @param adjacentCells the cells adjacent to this cell
     * @return the position of the child
     */
    public Point giveBirth(ArrayList<Cell> adjacentCells) {
        Point childCell = held.giveBirth(adjacentCells);
        
        return childCell;
    }

    /**
     * <p>
     * Check if the held object is still alive.
     * </p>
     * 
     * @return whether or not the held is alive
     */
    public boolean isAlive() {
        if (held != null && held.getLives() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * <p>
     * Gets the adjacent Cells by using the World getCellAt() method to check
     * if the Cells directly adjacent to this Cell are null. If they are not
     * null, the Cells are added to the ArrayList and returned. This will
     * return 8 Cells if the Cell is in the open, 5 Cells if it is on the side,
     * and 3 Cells if it is in a corner.
     * </p>
     * 
     * @param w the World that contains the cells
     * @return the Cells adjacent to this Cell
     */
    public ArrayList<Cell> getAdjacentCells(World w) {
        // the x coordinates to try, relative to the current x coordinate
        int[] x = {0, 1, 1, 1, 0, -1, -1, -1};
        // the y coordinates to try, relative to the current x coordinate
        int[] y = {1, 1, 0, -1, -1, -1, 0, 1};
        // the Cells adjacent to this Cell
        ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
        
        for (int i = 0; i < 8; i++) {
            // the Cell at the attempted x and y location
            Cell cell = w.getCellAt(location.x + y[i], location.y + x[i]);
            
            if (cell != null) {
                adjacentCells.add(cell);
            }
        }
        
        return adjacentCells;
    }
    
    /**
     * <p>
     * Returns the object that the Cell is currently holding.
     * </p>
     * 
     * @return the object that the Cell is currently holding
     */
    public Alive currentHeld() {
        return held;
    }
    
    /**
     * <p>
     * Changes what the Cell is currently holding, then calls the init() method
     * to reset the Cell.
     * </p>
     * 
     * @param newHeld the new object for the Cell to hold
     */
    public void changeHeld(Alive newHeld) {
        held = newHeld;
        init();
    }
    
    /**
     * <p>
     * Returns the row and column number of the Cell as a Point object.
     * </p>
     * 
     * @return the coordinates of the Cell as a Point object
     */
    public Point getLocation() {
        return location;
    }
    
    /**
     * <p>
     * Replenishes health of the held.
     * </p>
     */
    public void eat() {
        held.eat();
    }
}