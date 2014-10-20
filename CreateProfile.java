import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * CreateProfile makes a new file for the user to save info to 
 * @author jvelez
 */
 
public class CreateProfile extends JFrame {
	private JPanel panel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel ageLabel;
	private JTextField ageField;
	private JLabel genderLabel;
	private JTextField genderField;
	private JLabel heightLabel;
	private JTextField heightField;
	private JLabel weightLabel;
	private JTextField weightField;
	private JButton create;
	private JButton exit;
	private final int WINDOW_WIDTH = 400;
	private final int WINDOW_HEIGHT = 250;
	
	/**
	 * constructor
	 */
	 
	public CreateProfile() {
		// set the title
		setTitle("New Profile");
		// set the size
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		// set the close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// build the panel and add it to the window
		buildPanel();
		add(panel);
		
		// make the window visible
		setVisible(true);
	}
	
	/**
	 * buildPanel creates and adds the elements of the 
	 * GUI to a panel
	 */
	 
	private void buildPanel() {
		// instantiate the panel and set its layout
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		
		// create the name label and text field
		nameLabel = new JLabel("Name: ");
		nameField = new JTextField(10);
		
		// create the age label and text field
		ageLabel = new JLabel("Age: ");
		ageField = new JTextField(5);
		
		// create the gender label and text field
		genderLabel = new JLabel("Gender (male/female): ");
		genderField = new JTextField(7);
		
		// create the height label and text field
		heightLabel = new JLabel("Height(in): ");
		heightField = new JTextField(5);
		
		// create the weight label and text field
		weightLabel = new JLabel("Weight(lbs): ");
		weightField = new JTextField(5);
		
		// create the create & exit buttons and 
		// give them action listeners
		create = new JButton("Create Profile");
		exit = new JButton("Exit");
		
		create.addActionListener(new CreateButtonListener());
		exit.addActionListener(new ExitButtonListener());
		
		// add the elements to the panel
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(ageLabel);
		panel.add(ageField);
		panel.add(genderLabel);
		panel.add(genderField);
		panel.add(heightLabel);
		panel.add(heightField);
		panel.add(weightLabel);
		panel.add(weightField);
		panel.add(exit);
		panel.add(create);
	}
	
	/**
	 * CreateButtonListener is a private class to handle the
	 * events after the create button has been clicked
	 */
	 
	private class CreateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// create a BodyProfile object and use its
			// methods to create a profile txt file
			BodyProfile profile = new BodyProfile();
			
			profile.setName(nameField.getText());
			profile.setAge(Integer.parseInt(ageField.getText()));
			profile.setGender(genderField.getText());
			profile.setHeight(Double.parseDouble(heightField.getText()));
			profile.setWeight(Double.parseDouble(weightField.getText()));
			
			try {
				profile.saveInfo();
				JOptionPane.showMessageDialog(null, "Profile Created");
				dispose();
				new BodyProfileMenu();
			} 
			catch(IOException ie) {
				JOptionPane.showMessageDialog(null, "Save Failed");
				System.exit(0);
			}
		}
	}
	
	/**
	 * ExitButtonListener is a private class that closes the
	 * window when the user clicks the exit button
	 */
	 
	private class ExitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
