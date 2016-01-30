package q5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * <p>This class defines an EmailTest JFrame and its corresponding
 * JPanel with all of its labels, text-fields, and action listeners.</p>
 * 
 * <p>This class will create a JFrame with a red JPanel that mimics a email
 * client. The JPanel has labels and text-fields for the email address, CC,
 * Bcc, Subject, and the message box. The panel also has a send button, which
 * when hit will output all of the text-fields contents into the systems
 * output.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class EmailTest extends JFrame {
    /**
     * <p>The x and y dimension of the window.</p>
     */
    private static final int X_DIMENSION = 300;
    
    /**
     * <p>The y dimension of the window.</p>
     */
    private static final int Y_DIMENSION = 330;

    /**
     * <p>The length of the text-fields.</p>
     */
    private static final int TEXT_FIELD_LENGTH = 25;
    
    /**
     * <p>The height of the text-area.</p>
     */
    private static final int TEXT_AREA_HEIGHT = 5;

    /**
     * <p>The text-field for the "To" box.</p>
     */
    private JTextField toTextField;

    /**
     * <p>The text-field for the "CC" box.</p>
     */
    private JTextField ccTextField;

    /**
     * <p>The text-field for the "Bcc" box.</p>
     */
    private JTextField bccTextField;

    /**
     * <p>The text-field for the "Subject" box.</p>
     */
    private JTextField subjectTextField;

    /**
     * <p>The text-area for the "Message" box.</p>
     */
    private JTextArea messageTextArea;

    /**
     * <p>The default constructor which sets the title of this app, sets the
     * default close operation to exit, creates a new content pane, invokes the
     * addToPanel() method to add the components to the panel, and finally
     * packs the frame, and sets the visibility of this frame to true (show).
     * </p>
     */
    public EmailTest() {
        //Sets the windows title to "Email Test"
        super("Email Test");
        //Determines what will happen upon hitting the exit button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creates a new JPanel object
        JPanel panel = new JPanel();
        
        //Invokes the addToPanel() method on panel
        addToPanel(panel);
        
        //Adds the panel to the frame, packs it, and makes it visible
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
    /**
     * <p>Sets up all the labels and text-fields required for the email panel,
     * and adds them to the passed panel. It also sets up the send button
     * and adds an action listener to it, in addition to setting the
     * background color of the panel to red, and defining its dimensions.</p>
     * 
     * @param ePanel the panel to be set up
     */
    public void addToPanel(JPanel ePanel) {
        //Sets up a label and text-field for the "To:" box
        JLabel toLabel      = new JLabel("To: ");
        toTextField         = new JTextField(TEXT_FIELD_LENGTH);
		
        //Sets up a label and text-field for the "CC:" box
        JLabel ccLabel      = new JLabel("CC: ");
        ccTextField         = new JTextField(TEXT_FIELD_LENGTH);
		
        //Sets up a label and text-field for the "Bcc:" box
        JLabel bccLabel     = new JLabel("Bcc: ");
        bccTextField        = new JTextField(TEXT_FIELD_LENGTH);
		
        //Sets up a label and text-field for the "Subject:" box
        JLabel subjectLabel = new JLabel("Subject: ");
        subjectTextField    = new JTextField(TEXT_FIELD_LENGTH);
		
        //Sets up a label and text-field for the "Message:" box
        JLabel messageLabel = new JLabel("Message: ");
        messageTextArea     = new JTextArea(TEXT_AREA_HEIGHT, 
											TEXT_FIELD_LENGTH);
        
        //Sets up send button and attaches the ButtonListener() to it.
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ButtonListener());
		
        //Sets the dimensions of the panel, and its background color.
        ePanel.setPreferredSize(new Dimension(X_DIMENSION, Y_DIMENSION));
        ePanel.setBackground(Color.red);
        
        //Adds all of the labels and text-fields to the panel.
        ePanel.add(toLabel);
        ePanel.add(toTextField);
        ePanel.add(ccLabel);
        ePanel.add(ccTextField);
        ePanel.add(bccLabel);
        ePanel.add(bccTextField);
        ePanel.add(subjectLabel);
        ePanel.add(subjectTextField);
        ePanel.add(messageLabel);
        ePanel.add(messageTextArea);
        ePanel.add(sendButton);   
    }
    /**
     * <p>Represents an action listener for the send button.</p>
     * 
     * @author Trevor Broderick
     */
    private class ButtonListener implements ActionListener {
        /**
         * Outputs the contents from the text-fields into the standard
         * output.
         * @param event The event from the button being pressed
         */
        public void actionPerformed(ActionEvent event) {
            //Outputs all of the text-fields contents.
            System.out.println("To: " + toTextField.getText());
            System.out.println("CC: " + ccTextField.getText());
            System.out.println("Bcc: " + bccTextField.getText());
            System.out.println("Subject: " + subjectTextField.getText());
            System.out.println("Message: " + messageTextArea.getText());
        }
    }

    /**
     * <p>The main method.</p>
     * 
     * @param args unused.
     */
    public static void main(String[] args) {
        new EmailTest();
    }

};
