package q2;

import javax.swing.JFrame;

/**
 * <p>This program creates a JFrame with the title "Trevor Broderick" and adds a
 * JPanel DrawTrianglePanel to it. It then packs the frame and sets it visible.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class DrawTriangle extends JFrame {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Trevor Broderick");
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new DrawTrianglePanel());
        frame.pack();
        frame.setVisible(true);
    }

};
