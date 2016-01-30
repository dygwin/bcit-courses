package q3;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * <p>Defines a JPanel that displays a stop watch. The JPanel uses
 * a flow layout to organize the panel, and has an two ActionListeners
 * to respond to the timer and button presses. It uses a timer to update
 * and display the current time of the stop watch to the JPanel, and has
 * a stop button to pause the timer, and a reset button to reset the time
 * of the stop watch to zero.</p>
 * 
 * @author Trevor Broderick
 * 
 * @version 1.0
 *
 */
public class StopWatchPanel extends JPanel {
    
    /**
     * <p>The height of the JPanel.</p>
     */
    private static final int HEIGHT   = 100;
    
    /**
     * <p>The width of the JPanel.</p>
     */
    private static final int WIDTH    = 250;

    /** 
     * <p>Font size for the time labels.</p>
     */
    private static final int FONT_SIZE = 48;
    
    /**
     * <p>The delay of the timer.</p>
     */
    private static final int DELAY     = 10;

    /**
     * <p>The amount of minutes the stop watch recorded.
     * Set to zero by default.</p>
     */
    private int minutes;

    /**
     * <p>The amount of seconds the stop watch recorded.
     * Set to zero by default.</p>
     */
    private int seconds;

    /**
     * <p>The amount of milliseconds the stop watch recorded.
     * Set to zero by default.</p>
     */
    private int milliseconds;
    
    /**
     * <p>The timer responsible for updating the time of the JPanel.</p>
     */
    private Timer timer;

    /**
     * <p>Represents the minutes tracked by the timer.</p>
     */
    private JLabel minutesLabel;
    
    /**
     * <p>Represents the seconds tracked by the timer.</p>
     */
    private JLabel secondsLabel;
    
    /**
     * <p>Represents the milliseconds tracked by the tier.</p>
     */
    private JLabel millisecondsLabel;
    
    /**
     * <p>JButton that controls the state of the timer.</p>
     */
    private JButton stopButton;
    
    /**
     * <p>JButton to reset the time tracked back to zero.</p>
     */
    private JButton resetButton;
    
    /**
     * <p>Used to format the time to keep a zero infront.</p>
     */
    private DecimalFormat timeFmt = new DecimalFormat("00");
    
    /**
     * <p>Constructor: creates the timer and defines the size and layout
     * of the panel. Creates labels for the time and buttons to manipulate
     * the time. Changes the font of the time labels and adds all of the
     * components to the panel.</p>
     */
    public StopWatchPanel() {
        //Creates the timer and adds a TimeListener to it.
        timer = new Timer(DELAY, new TimeListener());
        
        //Sets the size of the panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        //Sets the background color to white
        setBackground(Color.white);

        //The labels for the different parts of the time
        minutesLabel      = new JLabel("00");
        secondsLabel      = new JLabel(":00");
        millisecondsLabel = new JLabel(":00");
        
        //Buttons to manipulate the time
        stopButton  = new JButton("STOP");
        resetButton = new JButton("RESET");
        
        //Creates a ButtonListener for the buttons
        ButtonListener buttonListener = new ButtonListener();
        
        //Adds the buttonListener to both the buttons
        stopButton.addActionListener(buttonListener);
        resetButton.addActionListener(buttonListener);

        //Sets the font of all the time labels
        minutesLabel.setFont(new Font("Helvetica", Font.BOLD, FONT_SIZE));
        secondsLabel.setFont(new Font("Helvetica", Font.BOLD, FONT_SIZE));
        millisecondsLabel.setFont(new Font("Helvetica", Font.BOLD, FONT_SIZE));
        
        //Adds every component to the panel
        add(minutesLabel);
        add(secondsLabel);
        add(millisecondsLabel);
        add(stopButton);
        add(resetButton);
        
        //Starts the timer
        timer.start();
    }
    
    /**
     * <p>Class that is called by the timer every 10 milliseconds to
     * update the time represented on the JPanel.</p>
     * 
     * @author Trevor Broderick
     *
     */
    private class TimeListener implements ActionListener {
        /**
         * <p>Updates the time labels.</p>
         * 
         * @param event the timer calling it
         */
        public void actionPerformed(ActionEvent event) {
            //The max amount of milliseconds before it turns into a second
            final int millisecondsCap = 100;
            //The max amount of seconds before it turns into a minute
            final int secondsCap      = 60;
            
            //Adds 1 (instead of 10 so it goes into 100) millisecond each call
            milliseconds += 1;
            
            //Turns 100 milliseconds into 1 second
            if (milliseconds == millisecondsCap) {
                milliseconds = 0;
                seconds     += 1;
            }
            //Turns 60 seconds into 1 minute
            if (seconds == secondsCap) {
                seconds  = 0;
                minutes += 1;
            }
            
            //Updates the text of the time labels
            millisecondsLabel.setText(":" + timeFmt.format(milliseconds));
            secondsLabel.setText(":" + timeFmt.format(seconds));
            minutesLabel.setText(timeFmt.format(minutes));
        }
    }
    
    /**
     * <p>Class that is used for controlling the button actions and
     * manipulating the time.</p>
     * 
     * @author Trevor Broderick
     *
     */
    private class ButtonListener implements ActionListener {
        /**
         * <p>Stops and starts the timer when the stopButton is pressed,
         * and resets the time when the resetButton is pressed.</p>
         * 
         * @param event a button being pressed
         */
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == stopButton) {
                if (timer.isRunning()) {
                    //If the timer is running, stop it and change the button
                    timer.stop();
                    stopButton.setText("START");
                } else {
                    //If the timer is stopped, start it and change the button
                    timer.start();
                    stopButton.setText("STOP");
                }
            } else {
                //If the reset button is hit, reset the time to zero
                minutes      = 0;
                seconds      = 0;
                milliseconds = 0;

                //Updates the time labels
                millisecondsLabel.setText(":" + timeFmt.format(milliseconds));
                secondsLabel.setText(":" + timeFmt.format(seconds));
                minutesLabel.setText(timeFmt.format(minutes));
            }
        }
    }
}
