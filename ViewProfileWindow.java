import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

/**
 * This program reads from the Profile text file and
 * displays its information for the user.
 * @author Julian Velez
 */
 
public class ViewProfileWindow {
	
	/**
	 * constructor
	 */
	
	public ViewProfileWindow() {
		try {
			// Open the file
			File file = new File("Profile");
			Scanner inputFile = new Scanner(file);
		
			// create a StringBuilder object to hold the file's contents
			StringBuilder output = new StringBuilder();
		
			// read lines from the file until we reach the end
			while (inputFile.hasNext()) {
				String line = inputFile.nextLine();
			
				output.append(line + "\n");
			}
		
			inputFile.close();
			
			// display the output
			JOptionPane.showMessageDialog(null, output.toString());
		}
		catch (IOException ie) {
			JOptionPane.showMessageDialog(null, "File not found");
		}
	}
}
