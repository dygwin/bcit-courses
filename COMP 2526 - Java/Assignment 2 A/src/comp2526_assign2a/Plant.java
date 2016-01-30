package comp2526_assign2a;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * <p>
 * Defines a Plant object, one of the possible Alive objects that can be held
 * by a Cell. It implements EdibleByHerbivore because it is exactly that. It
 * contains method to initialize itself for the Cell, and to add itself to
 * a Cell.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Plant extends JPanel implements Alive, EdibleByHerbivore {
    /**
     * <p>
     * Initializes itself for the Cell by setting its background color to green.
     * </p>
     */
    public void init() {
        setBackground(Color.GREEN);
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
}
