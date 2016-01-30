package q2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * <p>Defines a JPanel that implements a MouseListener and a 
 * MouseMotionListener.This JPanel creates a equilateral triangle wherever 
 * the mouse is pressed, and is then resized and rotated depending on where
 * the mouse is dragged without release upon first press. A corner of the
 * triangle follows the mouse when it is dragged in order to be resized,
 * and the triangle is destroyed and recreated when the mouse is pressed
 * again.</p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class DrawTrianglePanel extends JPanel implements MouseListener,
    MouseMotionListener {
    
    /**
     * <p>The height of the JPanel.</p>
     */
    private static final int HEIGHT = 500;
    
    /**
     * <p>The width of the JPanel.</p>
     */
    private static final int WIDTH = 500;
    
    /**
     * <p>The number of points(corners) in a equilateral triangle.</p>
     */
    private static final int NUM_OF_POINTS = 3;

    /**
     * <p>The array that holds the x coordinates of the triangle polygon.</p>
     */
    private int[] polyX = new int[NUM_OF_POINTS];

    /**
     * <p>The array that holds the y coordinates of the triangle polygon.</p>
     */
    private int[] polyY = new int[NUM_OF_POINTS];
    
    /**
     * <p>The angle of each corner in an equilateral triangle in radians.
     * The equivalent to 60 degrees.</p>
     */
    private double angleEquilateral = Math.PI / NUM_OF_POINTS;
    
    /**
     * <p>The polygon to be drawn on the JPanel.</p>
     */
    private Polygon triangle;
    
    /**
     * <p>The center point of the triangle, where the mouse is pressed.</p>
     */
    private Point center;
    
    /**
     * <p>Constructor for the JPanel:
     * Sets the width and height of the JPanel, and adds the mouse and mouse
     * motion event listeners.</p>
     */
    public DrawTrianglePanel() {
        //Sets the size of the JPanel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //Adds the mouse listener to the JPanel
        addMouseListener(this);
        //Adds the mouse motion listener to the JPanel
        addMouseMotionListener(this);
    }
    
    /**
     *<p>Calculates the coordinates and draws the triangle on the screen.
     *The color is set to red and the angles and coordinates of the triangle
     *are then calculated based on where the mouse is dragging the corner
     *of the triangle. After the coordinates are calculated, the new
     *Polygon object is created using the new coordinates and assigned to the
     *triangle, and is the drawn(filled) onto the JPanel.</p>
     *
     *@param g the component to draw on
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Only draw if the center point is set (the mouse has been pressed)
        if (center != null) {
            //Sets the color to red so the triangle will be red
            g.setColor(Color.RED);
            
            //Calculates the polar radius of the triangle corner to the center
            double radius = Math.sqrt(Math.pow((polyX[0] - center.x), 2) 
                    + Math.pow((polyY[0] - center.y), 2));
            
            //Calculates the polar angle of the first triangle corner
            double angle1 = Math.atan2((polyY[0] - center.y), 
                    (polyX[0] - center.x));
            //Calculates the polar angle of the second corner
            double angle2 = angle1 + 2 * angleEquilateral;
            //Calculates the polar angle of the third corner
            double angle3 = angle2 + 2 * angleEquilateral;
            
            //Sets the x coordinate of the triangles second corner
            polyX[1] = center.x + (int) (radius * Math.cos(angle2));
            //Sets the y coordinate of the triangles second corner
            polyY[1] = center.y + (int) (radius * Math.sin(angle2));
            //Sets the x coordinate of the triangles third corner
            polyX[2] = center.x + (int) (radius * Math.cos(angle3));
            //Sets the y coordinate of the triangles third corner
            polyY[2] = center.y + (int) (radius * Math.sin(angle3));
    
            //Creates a new Polygon using the new coordinates
            triangle = new Polygon(polyX, polyY, polyX.length);
            
            //Fills the triangle Polygon onto the JPanel
            g.fillPolygon(triangle);
        }
    }
    
    /**
     * <p>Sets the center to the mouse location when it is first pressed.</p>
     * 
     * @param e the event of the mouse being pressed
     */
    public void mousePressed(MouseEvent e) {
        center = e.getPoint();
    }
    
    /**
     * <p>Updates the first corner of the triangles x and y coordinates to the
     * current position of the mouse, and repaints the JPanel in order to give
     * it a rubber banding effect.</p>
     * 
     * @param e the event of the mouse being dragged
     */
    public void mouseDragged(MouseEvent e) {
        polyX[0] = e.getPoint().x;
        polyY[0] = e.getPoint().y;
        repaint();
    }
    
    /**
     * <p>Relieves the MouseListeners required implementations.</p>
     * 
     * @param e the event of the mouse being released
     */
    public void mouseReleased(MouseEvent e) { }
    
    /**
     * <p>Relieves the MouseListeners required implementations.</p>
     * 
     * @param e the event of the mouse entering the JPanel
     */
    public void mouseEntered(MouseEvent e) { }

    /**
     * <p>Relieves the MouseListeners required implementations.</p>
     * 
     * @param e the event of the mouse exiting the JPanel
     */
    public void mouseExited(MouseEvent e) { }

    /**
     * <p>Relieves the MouseListeners required implementations.</p>
     * 
     * @param e the event of the mouse being clicked
     */
    public void mouseClicked(MouseEvent e) { }

    /**
     * <p>Relieves the MouseMotionListeners required implementations.</p>
     * 
     * @param e the event of the mouse being moved
     */
    public void mouseMoved(MouseEvent e) { }
}
