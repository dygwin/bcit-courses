package comp2526_assign2a;

import java.awt.Point;
import java.util.ArrayList;

import comp2526_assign2a.Cell.Type;

/**
 * <p>
 * Defines a World object that contains all of the Cells. It has a specified
 * number of rows and columns, and a two-dimensional array as a board to
 * contain those rows and columns along with their associated Cells. It also
 * keeps track of the Cell location of every Herbivore that is added the
 * World. It has methods to initialize the World by adding all of its Cells
 * with random chances of generating Plant and Herbivore Cells, rather than
 * Empty Cells. It also contains methods to return the number of rows and
 * columns in the World, a method to return the Cell at a specific row and
 * column position in the World, and a takeTurn() method to have all the
 * Herbivores move.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class World {
    /**
     * <p>
     * The number of rows of cells for the world.
     * </p>
     */
    private int rows;
    
    /**
     * <p>
     * The number of columns of cells for the world.
     * </p>
     */
    private int columns;
    
    /**
     * <p>
     * A 2-dimensional array of rows and columns that will hold Cell objects.
     * </p>
     */
    private Cell[][] board;
    
    /**
     * <p>
     * Holds the location(row and column number) of every Herbivore on the board
     * as a Point.
     * </p>
     */
    private ArrayList<Point> herbivores = new ArrayList<Point>();
    
    /**
     * <p
     * The constructor for the World. It sets up the number of rows and columns
     * that the World has, and then creates the board with those array sizes.
     * </p>
     * 
     * @param rows the number of rows in the world
     * @param columns the number of columns in the world
     */
    public World(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new Cell[rows][columns];
    }
    
    /**
     * <p>
     * Populates the board with Cells that contain either Herbivores, Plants,
     * or nothing. It uses the RandomGenerator to generate a random number
     * to pick which type of Cell to produce. There is a 10% chance of
     * producing a Herbivore, a 30% chance of producing a Plant, and a 60%
     * chance of producing an empty Cell. After each Cell is created, its
     * init() function is called to initialize the Cell.
     * </p>
     */
    public void init() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                // the random number to decide on the type of Cell produced
                int num = RandomGenerator.nextNumber(99);
                
                if (num > 90) {
                    board[i][j] = new Cell(Type.Herbivore, i, j);
                    herbivores.add(new Point(i, j));
                } else if (num > 60) {
                    board[i][j] = new Cell(Type.Plant, i, j);
                } else {
                    board[i][j] = new Cell(Type.Empty, i, j);
                }
                
                board[i][j].init();
            }
        }
    }
    
    /**
     * <p>
     * Returns the number of rows in the world.
     * </p>
     * 
     * @return the number of rows in the world
     */
    public int getRowCount() {
        return rows;
    }
    
    /**
     * <p>
     * Returns the number of columns in the world.
     * </p>
     * 
     * @return the number of columns in the world
     */
    public int getColumnCount() {
        return columns;
    }
    
    /**
     * <p>
     * Returns the Cell at the specified row and column on the board, or null
     * if that coordinate doesn't exist on the board.
     * </p>
     * 
     * @param row the row of the Cell
     * @param column the column of the Cell
     * @return the Cell at the specified row and column, or null if nonexistent
     */
    public Cell getCellAt(int row, int column) {
        if ((row < rows && row >= 0) && (column < columns && column >= 0)) {
            return board[row][column];
        } else {
            return null;
        }
    }
    
    /**
     * <p>
     * Moves each Herbivore in the herbivores ArrayList to an adjacent cell,
     * and stores their new location into a new ArrayList as Point objects,
     * replacing the old ArrayList.
     * </p>
     */
    public void takeTurn() {
        // a temporary array to store the new locations of the Herbivores
        ArrayList<Point> temp = new ArrayList<Point>();
        
        for (Point herbivore: herbivores) {
            // the current Cell of the herbivore
            Cell cell = getCellAt(herbivore.x, herbivore.y);
            // all the possible adjacent Cells the herbivore could move to
            ArrayList<Cell> adjacentCells = cell.getAdjacentCells(this);
            // cycles the Cell and gets the new location of the herbivore
            Point newCell = cell.cycle(adjacentCells);
            
            // if the herbivore hasn't died, store its new location into temp
            if (newCell != null) {
                temp.add(newCell);
            }
        }
        
        // replace the old herbivore locations with the new ones in temp
        herbivores = temp;
    }
}
