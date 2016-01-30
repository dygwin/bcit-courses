package q3;

import javax.swing.JFrame;

/**
 * <p>This program creates a JFrame with the title "Stop Watch" and adds a
 * JPanel StopWatchPanel to it. It then packs the frame and sets it visible.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class StopWatch {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Stop Watch");
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new StopWatchPanel());
        frame.pack();
        frame.setVisible(true);
    }

};
