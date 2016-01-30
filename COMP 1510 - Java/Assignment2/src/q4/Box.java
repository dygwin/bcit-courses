package q4;

/**
 * <p>This class defines a Box object that has height, a width, and a depth.
 * It also has a state referring to whether or not the box is full. Each of
 * these attributes have methods that allow you to get and set the attributes
 * from outside of this class. A toString method is also defined, which will
 * return the height, width, depth, and full state of the box.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Box {
    /**
     * <p>Stores the height of the box.</p>
     */
    private double height;
    /**
     * <p>Stores the width of the box.</p>
     */
    private double width;
    /**
     * <p>Stores the depth of the box.</p>
     */
    private double depth;
    /**
     * <p>Stores whether the box is full or not.</p>
     */
    private boolean full;
    /**
     * <p>Constructor for the Box class. Takes the height, width, and depth as 
     * parameters. Also sets the full-state of the box to false by default.</p>
     * 
     * @param h the height of the box
     * @param w the width of the box
     * @param d the depth of the box
     */
    public Box(double h, double w, double d) {
        height = h;
        width  = w;
        depth  = d;
        full   = false;
    }
    /**
     * <p>Sets the height of the box.</p>
     * 
     * @param h the height of the box
     */
    public void setHeight(double h) {
        height = h;
    }
    /**
     * <p>Sets the width of the box.</p>
     * 
     * @param w the width of the box
     */
    public void setWidth(double w) {
        width = w;
    }
    /**
     * <p>Sets the depth of the box.</p>
     * 
     * @param d the depth of the box
     */
    public void setDepth(double d) {
        depth = d;
    }
    /**
     * <p>Sets the full-state of the box.</p>
     * 
     * @param isFull the new state of the full boolean
     */
    public void setFull(boolean isFull) {
        full = isFull;
    }
    /**
     * <p>Returns the height of the box.</p>
     * 
     * @return the height of the box
     */
    public double getHeight() {
        return height;
    }
    /**
     * <p>Returns the width of the box.</p>
     * 
     * @return the width of the box
     */
    public double getWidth() {
        return width;
    }
    /**
     * <p>Returns the depth of the box.</p>
     * 
     * @return the depth of the box
     */
    public double getDepth() {
        return depth;
    }
    /**
     * <p>Returns the whether or not the box is full.</p>
     * 
     * @return the full state of the box
     */
    public boolean getFull() {
        return full;
    }
    /**
     * <p>Returns a string detailing the width, height, and depth of the box,
     * as well as whether or not it is full.</p>
     * 
     * @return a string detailing to attributes of the box
     */
    public String toString() {
        String message = "This box has a height of " + height + ", a width of "
                + width + ", a depth of " + depth + ", and whether its full is "
                + full;
        return message;
    }
};
