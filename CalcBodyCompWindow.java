import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

/**
 * CalcBodyCompWindow gives the user a set of text fields
 * to fill, and calculates the body fat percentage based on 
 * these measurements in combination with the info in their profile
 * @author jvelez
 */
 
public class CalcBodyCompWindow extends JFrame {
	private JPanel panel;
	private JLabel waistCircumLabel;
	private JTextField waistCircumField;
	private JLabel wristCircumLabel;
	private JTextField wristCircumField;
	private JLabel forearmCircumLabel;
	private JTextField forearmCircumField;
	private JLabel hipCircumLabel;
	private JTextField hipCircumField;
	private JButton calcButton;
	private JButton exitButton;
	private BodyProfile profile;
	
	/**
	 * constructor
	 */
	 
	public CalcBodyCompWindow() throws IOException {
		// open the profile file and confirm existence
		File file = new File("Profile");
		if (!file.exists()) {
			JOptionPane.showMessageDialog(null, "No file error");
		}
		else {
			setTitle("Calculate Body Composition");
				
			// create a BodyProfile object so that we can check gender
			// and pass reference to the appropriate panel
			profile = new BodyProfile();
			profile.setName(parseFile(1));
			profile.setAge(Integer.parseInt(parseFile(2)));
			profile.setGender(parseFile(3));
			profile.setHeight(Double.parseDouble(parseFile(4)));
			profile.setWeight(Double.parseDouble(parseFile(5)));
			if (Double.parseDouble(parseFile(6)) != 0.0) {
				profile.setBMI(Double.parseDouble(parseFile(6)));
			}
			
			// add the appropriate panel and set the size
			if (profile.getGender().equals("MALE")) {
				setSize(500, 150);
				buildMalePanel();
				add(panel);
			}
			else if (profile.getGender().equals("FEMALE")) {
				setSize(500, 200);
				buildFemalePanel();
				add(panel);
			}
			
			setVisible(true);
		} 
	}
	
	/**
	 * buildFemalePanel builds a panel for a 
	 * female profile
	 */
	 
	private void buildFemalePanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		
		// build the necessary items
		wristCircumLabel = new JLabel("Circumference of wrist " +
				"in inches");
		wristCircumField = new JTextField(5);
		forearmCircumLabel = new JLabel("Circumference of forearm" +
				" in inches");
		forearmCircumField = new JTextField(5);
		waistCircumLabel = new JLabel("Circumference of waist " +
				" in inches");
		waistCircumField = new JTextField(5);
		hipCircumLabel = new JLabel("Circumference of hips in inches");
		hipCircumField = new JTextField(5);
		
		calcButton = new JButton("Calculate");
		exitButton = new JButton("Exit");
		
		// give the buttons action listeners
		calcButton.addActionListener(new CalcButtonListener());
		exitButton.addActionListener(new ExitButtonListener());
		
		// add the items to the panel
		panel.add(wristCircumLabel);
		panel.add(wristCircumField);
		panel.add(forearmCircumLabel);
		panel.add(forearmCircumField);
		panel.add(waistCircumLabel);
		panel.add(waistCircumField);
		panel.add(hipCircumLabel);
		panel.add(hipCircumField);
		panel.add(exitButton);
		panel.add(calcButton);
	}
	
	/**
	 * buildMalePanel builds a panel for the male 
	 * profile
	 */
	 
	private void buildMalePanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		
		// build items to go on label
		waistCircumLabel = new JLabel("Circumference of waist " +
				"in inches");
		waistCircumField = new JTextField(5);
		calcButton = new JButton("Calculate");
		exitButton = new JButton("Exit");
		
		// give the buttons action listeners
		calcButton.addActionListener(new CalcButtonListener());
		exitButton.addActionListener(new ExitButtonListener());
		
		panel.add(waistCircumLabel);
		panel.add(waistCircumField);
		panel.add(exitButton);
		panel.add(calcButton);
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

	/**
	 * MaleCalcButtonListener handles the events of 
	 * a calcbutton being clicked on the malepanel
	 */
	 
	private class CalcButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DecimalFormat decimal = new DecimalFormat("##.0");
			// calculate the body fat %
			if (profile.getGender().equals("MALE")) {
				profile.setBodyFatMale(Double.parseDouble(waistCircumField.getText()));
			}
			else if (profile.getGender().equals("FEMALE")) {
				profile.setBodyFatFemale(Double.parseDouble(wristCircumField.getText()),
						Double.parseDouble(waistCircumField.getText()), 
						Double.parseDouble(hipCircumField.getText()), 
						Double.parseDouble(forearmCircumField.getText()));
			}			
			
			try {
				// save the new profile
				profile.saveInfo();
				
				// display the findings
				dispose();
				JOptionPane.showMessageDialog(null, "Your body fat percentage is " +
						decimal.format(profile.getBodyFat()) + "%");
			}
			catch (IOException ie) {
				JOptionPane.showMessageDialog(null, "Error");
			}
		}
	}
	
	/**
	 * closes window when the user clicks the exit button
	 */
	 
	private class ExitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
