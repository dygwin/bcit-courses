package comp2526_assign2a;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * <p>
 * Sets up the GameFrame and associates a World object with it, along with the
 * size of the World. Contains methods for calculating the size of the screen
 * and the size of the screen that the GameFrame can occupy, and a method to
 * calculate the center position of the screen. It uses these methods when
 * initializing the GameFrame to ensure it has a proper size and is located on
 * the center of the screen.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public final class Main {
    /**
     * <p>
     * The ToolKit used for calculating areas on the screen.
     * </p>
     */
    private static final Toolkit TOOLKIT;
    
    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }
    
    /**
     * <p>
     * The constructor for the Main class. Empty.
     * </p>
     */
    private Main() {
    }
    
    /**
     * <p>
     * The main access point of the JVM.
     * It creates and initializes a World and GameFrame, then sets the
     * GameFrame to visible.
     * </p>
     * 
     * @param argv command line arguments
     */
    public static void main(final String[] argv) {
        // the GameFrame the be displayed on the screen
        final GameFrame frame;
        // the World object to be associated with the GameFrame
        final World world;

        RandomGenerator.reset();
        world = new World(25, 25);
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * <p>
     * Positions the GameFrame in the center of the screen, and determines the
     * GameFrames size based on the size of the screen.
     * </p>
     * 
     * @param frame the GameFrame object displayed on the screen
     */
    private static void position(final GameFrame frame) {
        // the size of the GameFrame
        final Dimension size;
        
        size = calculateScreenArea(0.80f, 0.80f);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }
    
    /**
     * <p>
     * Finds the size of the screen and then returns the center position of the
     * screen.
     * </p>
     * 
     * @param size the size of the GameFrame
     * @return the center Point of the screen
     */
    public static Point centreOnScreen(final Dimension size) {
        // the size of the screen
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2,
                (screenSize.height - size.height) / 2));
    }
    
    /**
     * <p>
     * Calculates the area on the screen that the GameFrame can occupy,
     * based on the percentages passed into it.
     * </p>
     * 
     * @param widthPercent the width percentage of the GameFrame to occupy
     * @param heightPercent the height percentage of the GameFrame to occupy
     * @return the Dimension of the screen area that the GameFrame can occupy
     */
    public static Dimension calculateScreenArea(final float widthPercent,
            final float heightPercent) {
        // the size of the screen
        final Dimension screenSize;
        // the area of the screen the GameFrame can occupy
        final Dimension area;
        // the width of the screen that the GameFrame may occupy
        final int width;
        // the height of the screen that the GameFrame may occupy
        final int height;
        // the actual size that the GameFrame will occupy (height and width)
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > 100.0f)) {
            throw new IllegalArgumentException("widthPercent cannot be "
                    + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > 100.0f)) {
            throw new IllegalArgumentException("heightPercent cannot be "
                    + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width  = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size   = Math.min(width, height);
        area   = new Dimension(size, size);

        return (area);
    }
}
