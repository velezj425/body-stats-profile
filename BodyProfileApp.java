/**
 * Body Profile 1.0
 * A program to take in user info
 * and save it to a txt profile
 * @author jvelez
 */

import java.util.Scanner;
import java.io.*;

public class BodyProfileApp {
	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		
		// Get the user's info
		System.out.print("What's your name?: ");
		String name = keyboard.nextLine();
		
		System.out.print("What's your age?: ");
		int age = keyboard.nextInt();
		
		System.out.print("What is your height in inches?: ");
		double height = keyboard.nextDouble();
		
		System.out.print("What is your weight in pounds?: ");
		double weight = keyboard.nextDouble();
		
		// Create the profile
		BodyProfile profile = new BodyProfile(height, weight);
		profile.setAge(age);
		profile.setName(name);
		
		// Display the profile
		System.out.println("\t\t" + name + "'s Profile");
		System.out.println("Age : " + profile.getAge());
		System.out.println("Height : " + profile.getHeight());
		System.out.println("Weight : " + profile.getWeight());
		System.out.printf("BMI : %.1f\n", profile.getBMI());
		
		// Decide if the profile will be saved
		System.out.print("Do you want to save your profile?: ");
		String yesOrNo = keyboard.next();
		
		if(yesOrNo.equalsIgnoreCase("yes")) {
			profile.saveInfo();
			System.out.println("Profile Saved! Good-Bye");
		}
		else {
			System.out.println("Good-Bye");
		}
	}

}
