import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

/**
 * CalcBMIWindow calculates the user's BMI, displays it, 
 * and saves it to the user's profile
 * @author jvelez
 */
 
public class CalcBMIWindow {
	private BodyProfile profile;
	private String healthStatus;
	
	/**
	 * Constructor
	 */
	 
	public CalcBMIWindow() throws IOException {
		// open the profile file and verify existence
		File file = new File("Profile");
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "No profile error");
		}
		else {
			try {
				// create a BodyProfile object to hold the info from file
				profile = new BodyProfile();
				
				profile.setName(parseFile(1));
				profile.setAge(Integer.parseInt(parseFile(2)));
				profile.setGender(parseFile(3));
				profile.setHeight(Double.parseDouble(parseFile(4)));
				profile.setWeight(Double.parseDouble(parseFile(5)));
				if (Double.parseDouble(parseFile(7)) != 0.0) {
					profile.setBodyFat(Double.parseDouble(parseFile(7)));
				}
				
				// calculate the bmi
				profile.setBMI();
				
				profile.saveInfo();
				
				// determine bmi health status
				if (profile.getBMI() > 40) 
					healthStatus = "Very severely obese";
				else if (profile.getBMI() > 35)
					healthStatus = "Severely obese";
				else if (profile.getBMI() > 30)
					healthStatus = "Moderately obese";
				else if (profile.getBMI() > 25)
					healthStatus = "Overweight";
				else if (profile.getBMI() > 18.5)
					healthStatus = "Normal";
				else if (profile.getBMI() > 16)
					healthStatus = "Underweight";
				else if (profile.getBMI() > 15)
					healthStatus = "Severely underweight";
				else 
					healthStatus = "Very severely underweight";
				
				// create a DecimalFormat object to format the bmi value
				DecimalFormat decimal = new DecimalFormat("##.0");
				
				// display the results
				JOptionPane.showMessageDialog(null, "BMI : " + 
						decimal.format(profile.getBMI()) + "\n" + healthStatus);
			}
			catch (IOException ie) {
				JOptionPane.showMessageDialog(null, "Error");
			}
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
