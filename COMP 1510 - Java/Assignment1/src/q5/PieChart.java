package q5;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <p>Creates a JFrame with a corresponding JPanel, then sets the background of
 * the panel to a green background, and draws an oval on the panel. A series
 * of eight arcs of varying colors with an arc length of 45 degrees is then 
 * drawn on the oval, representing eight separate slices of a pie.</p>
 * @author Trevor Broderick
 * @version 1.0
 */
public class PieChart extends JFrame {
    /**
     * <p>The x position of the text.</p>
     */
    private static final int X_POSITION = 20;

    /**
     * <p>The y position of the text.</p>
     */
    private static final int Y_POSITION = 20;

    /**
     * <p>The default constructor which sets the title of this app, sets the
     * default close operation to exit, creates a new content pane and finally
     * sets size and sets the visibility of this frame to true (show).</p>
     */
    public PieChart() {
        super("Cut the pie!");
        final int width  = 350;
        final int height = 220;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new PieChartPanel());
        setSize(width, height);
        setVisible(true);
    }

    /**
     * <p>A panel that acts as the Frame's content pane.</p>
     */
    class PieChartPanel extends JPanel {
        /**
         * <p>Called by the environment when the frame needs to be updated.</p>
         * <p>Sets the background of the panel to green, then draws an oval with
         * eight equal slices on it, each of varying colors, at 45 degree
         * increments in order to create a full 360 degree circle.</p>
         * @param g the graphics context with which we paint into.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            //The x and y position where the oval and every arc will begin.
            final int xStart = 50;
            final int yStart = 50;
            
            //The width and height of the oval and all arcs.
            final int lineWidth  = 80;
            final int lineHeight = 80;
            
            //The arc length every arc will follow.
            final int arcLength = 45;
            
            //Starting angle for each arc, incremented after every addition.
            int angle = 0;
            
            //Defines the color for the background.
            final Color bgColor = new Color(60, 225, 140);
            
            //Defines the color for the oval.
            final Color ovalColor = new Color(140, 50, 200);
            
            setBackground(bgColor);
            g.setColor(ovalColor);
            
            g.drawString("Something interesting ...", X_POSITION, Y_POSITION);
            
            g.drawOval(xStart, yStart, lineWidth, lineHeight);
            
            g.setColor(Color.pink);
            g.fillArc(xStart, yStart, lineWidth, lineHeight, angle, arcLength);
            angle += arcLength;
            
            g.setColor(Color.red);
            g.fillArc(xStart, yStart, lineWidth, lineHeight, angle, arcLength);
            angle += arcLength;
            
            g.setColor(Color.yellow);
            g.fillArc(xStart, yStart, lineWidth, lineHeight, angle, arcLength);
            angle += arcLength;
            
            g.setColor(Color.orange);
            g.fillArc(xStart, yStart, lineWidth, lineHeight, angle, arcLength);
            angle += arcLength;
            
            g.setColor(Color.magenta);
            g.fillArc(xStart, yStart, lineWidth, lineHeight, angle, arcLength);
            angle += arcLength;
            
            g.setColor(Color.gray);
            g.fillArc(xStart, yStart, lineWidth, lineHeight, angle, arcLength);
            angle += arcLength;
            
            g.setColor(Color.blue);
            g.fillArc(xStart, yStart, lineWidth, lineHeight, angle, arcLength);
            angle += arcLength;
            
            g.setColor(Color.green);
            g.fillArc(xStart, yStart, lineWidth, lineHeight, angle, arcLength);
            angle += arcLength;
        }
    }
    /**
     * <p>The main method.</p>
     * 
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new PieChart();
    }

};
