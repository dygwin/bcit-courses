package comp2526_assign2a;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <p>
 * The MouseListener that will be added to a GameFrame to listen for a mouse
 * click that will invoke the takeTurn() method of the GameFrame.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class TurnListener implements MouseListener {
    /**
     * <p>
     * The GameFrame to invoke the takeTurn() method of.
     * </p>
     */
    private GameFrame frame;
    
    /**
     * <p>
     * The constructor for the TurnListener, that will associate a GameFrame
     * object with it.
     * </p>
     * @param f the GameFrame object to be associated with the TurnListener
     */
    public TurnListener(GameFrame f) {
        frame = f;
    }
    
    /**
     * <p>
     * When the mouse is clicked, invoke the takeTurn() method of the GameFrame.
     * </p>
     * 
     * @param e the MouseListener event
     */
    public void mouseClicked(MouseEvent e) {
        frame.takeTurn();
    }
    
    /**
     * <p>Unused</p>
     * 
     * @param e the MouseListener event
     */
    public void mouseEntered(MouseEvent e) {}
    
    /**
     * <p>Unused</p>
     * 
     * @param e the MouseListener event
     */
    public void mouseExited(MouseEvent e) {}
    
    /**
     * <p>Unused</p>
     * 
     * @param e the MouseListener event
     */
    public void mousePressed(MouseEvent e) {}
    
    /**
     * <p>Unused</p>
     * 
     * @param e the MouseListener event
     */
    public void mouseReleased(MouseEvent e) {}

}
