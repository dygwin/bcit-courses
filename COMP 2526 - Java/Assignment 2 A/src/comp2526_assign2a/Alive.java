package comp2526_assign2a;

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
}
