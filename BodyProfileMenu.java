import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * BodyProfileMenu is the main menu interface for
 * the Body Profile App
 * @author jvelez
 */
 
public class BodyProfileMenu extends JFrame {
	private JPanel panel;
	private JLabel messageLabel;
	private JRadioButton viewProfile;
	private JRadioButton editProfile;
	private JRadioButton calcBMI;
	private JRadioButton calcBodyComp;
	private ButtonGroup bg;
	private final int WINDOW_WIDTH = 250;
	private final int WINDOW_HEIGHT = 325;
	
	/**
	 * constructor
	 */
	 
	public BodyProfileMenu() {
		// set the title
		setTitle("Body Profile App");
		// set the size
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		// set close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// build the panel and add it to the window
		buildPanel();
		add(panel);
		
		// make the window visible
		setVisible(true);
	}
	
	/**
	 * buildPanel adds the buttons and labels to the panel;
	 * also groups the buttons and assigns them action listeners
	 */
	 
	private void buildPanel() {
		// instantiate the panel and set its layout
		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		
		// create the label and add it to the panel
		messageLabel = new JLabel("Choose:");
		panel.add(messageLabel);
		
		// create the buttons and add them to the button group
		viewProfile = new JRadioButton("View my Profile");
		editProfile = new JRadioButton("Edit my Profile");
		calcBMI = new JRadioButton("Calculate BMI");
		calcBodyComp = new JRadioButton("Calculate Body Composition");
		bg = new ButtonGroup();
		bg.add(viewProfile);
		bg.add(editProfile);
		bg.add(calcBMI);
		bg.add(calcBodyComp);
		
		// give the buttons action listeners
		viewProfile.addActionListener(new RadioButtonListener());
		editProfile.addActionListener(new RadioButtonListener());
		calcBMI.addActionListener(new RadioButtonListener());
		calcBodyComp.addActionListener(new RadioButtonListener());
		
		// add the buttons to the panel
		panel.add(viewProfile);
		panel.add(editProfile);
		panel.add(calcBMI);
		panel.add(calcBodyComp);
	}
	
	/**
	 * private action listener class to handle operations
	 * after a radio button is clicked
	 */
	 
	private class RadioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// determine which radio button was selected and 
			// open the corresponding window
			if (e.getSource() == viewProfile) {
				// will display profile
				new ViewProfileWindow();
			}
			else if (e.getSource() == editProfile) {
				// will display edit profile window
				new EditProfileWindow();
			}
			else if (e.getSource() == calcBMI) {
				// display a calculated bmi based on
				// other profile info
				try {	// for whatever reason this won't work without IOException
						// handling, despite IOExceptions being handled in the 
						// CalcBMIWindow class
					new CalcBMIWindow();
				}
				catch (IOException ie) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
			else if (e.getSource() == calcBodyComp) {
				// display calculate body comp window
				try {
					new CalcBodyCompWindow();
				}
				catch (IOException ie) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		}
	}
}
