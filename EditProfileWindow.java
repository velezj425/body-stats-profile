import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

/**
 * EditProfileWindow allows the user to edit the information within their profile 
 * @author jvelez
 */
 
public class EditProfileWindow extends JFrame {
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
	private JButton save;
	private JButton exit;
	private final int WINDOW_WIDTH = 400;
	private final int WINDOW_HEIGHT = 250;
	
	/**
	 * constructor
	 */
	 
	public EditProfileWindow() {
		// set the title
		setTitle("Edit Profile");
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
		nameField = new JTextField(parseFile(1));
		
		// create the age label and text field
		ageLabel = new JLabel("Age: ");
		ageField = new JTextField(parseFile(2));
		
		// create the gender label and text field
		genderLabel = new JLabel("Gender (male/female): ");
		genderField = new JTextField(parseFile(3));
		
		// create the height label and text field
		heightLabel = new JLabel("Height(in): ");
		heightField = new JTextField(parseFile(4));
		
		// create the weight label and text field
		weightLabel = new JLabel("Weight(lbs): ");
		weightField = new JTextField(parseFile(5));
		
		// create the create & exit buttons and 
		// give them action listeners
		save = new JButton("Save Profile");
		exit = new JButton("Exit");
		
		save.addActionListener(new SaveButtonListener());
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
		panel.add(save);
	}
	
	/**
	 * SaveButtonListener is a private class to handle the
	 * events after the save button has been clicked
	 */
	 
	private class SaveButtonListener implements ActionListener {
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
				JOptionPane.showMessageDialog(null, "Profile Saved");
				dispose();
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
			dispose();
		}
	}
	
	/**
	 * parseFile is a method to go through the text profile
	 * and separate the data from its tag and return it as text
	 * @param lineNum The line number to parse
	 */
	 
	private String parseFile(int lineNum) {
		File file = new File("Profile");
		String returnLine = "";
		String line;
		
		// check for the existence of the file
		if (!file.exists()) 
			JOptionPane.showMessageDialog(null, "Profile Doesn't Exist");
		else {
			try {
				int i = 1;
				StringBuilder lineSB = new StringBuilder("");
				Scanner inputFile = new Scanner(file);
				
				// go throught the file until you've reached the desired line
				while (i <= lineNum) {
					if (i == lineNum) {
						line = inputFile.nextLine();
						
						// pass the line to string builder
						lineSB.append(line);
						i++;
					}
					else 
						// read the line and move on
						line = inputFile.nextLine();
						i++;
				}
				// find what the position of the ":" is and delete everything before it
				int colonIndex = lineSB.indexOf(":");
				lineSB.delete(0, (colonIndex + 2)); // use +2 to take away the space
													// after the colon as well
				returnLine = lineSB.toString();
				}
			catch (IOException ie) {
				JOptionPane.showMessageDialog(null, "Error");
			}
		}
		
		return returnLine;
	}
}
