import javax.swing.JOptionPane;
import java.io.*;

/**
 * Body Profile 2.0
 * A program to take in user info
 * and save it to a txt profile
 * @author jvelez
 */

public class BodyProfileApp {
	public static void main(String[] args) {
		// check for the existence of a profile
		File file = new File("Profile");
		if (!file.exists()) {
			new CreateProfile();
		}
		else {
			// open the main menu
			new BodyProfileMenu();
		}
	}
}
