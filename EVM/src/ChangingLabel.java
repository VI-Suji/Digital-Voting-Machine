import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

// ActionEvent and ActionListener
// live in the java.awt.event
// package

// Application with a changing text field
public class ChangingLabel extends JLabel implements ActionListener {
        
        // String constants which define the text that's displayed.
        private static final String TEXT1 = "Hello, World!";
        private static final String TEXT2 = "Hello again!";
        
        // Instance field to keep track of the text currently
        // displayed
        private boolean usingText1;
        
        // Constructor.  Sets alignment and initial text, and
        // sets the usingText1 field appropriately.
        public ChangingLabel() {
                this.setHorizontalAlignment(SwingConstants.CENTER);             
                this.setText(TEXT1);    
                usingText1 = true;
        }
        
        // A method to change the label text.  If the
        // current text is TEXT1, changes it to TEXT2,
        // and vice-versa.  The method is private here,
        // because it is never called directly.
        private void changeLabel() {
                if(usingText1) {
                        this.setText(TEXT2);
                        usingText1 = false;
                } else {
                        this.setText(TEXT1);
                        usingText1 = true;
                }
        }
        
        // This method implements the ActionListener interface.
        // Any time that an action is performed by a component
        // that this object is registered with, this method
        // is called.  We can analyze the event object received
        // here for more information if we want to, but it's not
        // necessary in this application.
        public void actionPerformed(ActionEvent e) {
                this.changeLabel();
        }
        
        // Main method.  This is the code that is executed when the
        // program is run
        public static void main(String[] args) {
            
            // Constructs the ChangingLabel.  All the
            // intialization is done be the default
            // constructor, defined in the ChangingLabel
            // class below.
            ChangingLabel changingLabel = new ChangingLabel();
            
            // Create the button
            JButton button = new JButton("Button");
            
            // Register the ChangingLabel as an action
            // listener to the button.  Whenever the
            // button is pressed, its ActionEvent will
            // be sent the ChangingLabel's
            // actionPerformed() method, and the code
            // there will be executed.
            button.addActionListener(changingLabel);
            
            // Create the frame
            JFrame frame = new JFrame("Hello");
            
            // Add the label and the button to the
            // frame, using layout constants.
            frame.add(changingLabel, BorderLayout.CENTER);
            frame.add(button, BorderLayout.SOUTH);
            
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.toFront();
    }
}