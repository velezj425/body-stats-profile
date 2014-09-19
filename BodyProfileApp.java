/**
 * Body Profile 1.1
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
		System.out.print("What is your name?: ");
		String name = keyboard.nextLine();
		System.out.print("What is your age?: ");
		int age = keyboard.nextInt();
		System.out.print("What is your gender?: ");
		String gender = keyboard.next();
		System.out.print("What is your height in inches?: ");
		double height = keyboard.nextDouble();
		System.out.print("What is your weight in pounds?: ");
		double weight = keyboard.nextDouble();
		
		// Create the profile
		BodyProfile profile = new BodyProfile(height, weight);
		profile.setAge(age);
		profile.setName(name);
		profile.setGender(gender);
		
		int choice = -1;
		while(choice != 0) {
			choice = menu();
			
			switch(choice) {
				case 0:
					System.out.println("Good-Bye");
					break;
				case 1:
					displayProfile(profile);
					break;
				case 2:
					profile.setBMI();
					System.out.printf("Your BMI is %.1f\n", profile.getBMI());
					break;
				case 3:
					profile.setBodyFat();
					System.out.printf("Your body fat percentage is %.1f\n", profile.getBodyFat());
					break;
				case 4:
					profile.saveInfo();
					System.out.println("Your profile has been saved!");
					break;
				default:
					System.out.println("ERROR");
			}
		}
	}
	
	/**
	 * A menu to give user's a choice in what 
	 * they see
	 * @return choice The user's choice
	 */
	
	public static int menu() {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("\t\tMENU");
		System.out.printf("%4d - %5s\n", 0, "Exit");
		System.out.printf("%4d - %5s\n", 1, "View Full Profile");
		System.out.printf("%4d - %5s\n", 2, "Calculate BMI");
		System.out.printf("%4d - %5s\n", 3, "Calculate Body Fat Percentage");
		System.out.printf("%4d - %5s\n", 4, "Save Profile");
		
		System.out.print("Choice: ");
		int choice = keyboard.nextInt();
		
		return choice;
	}

	/**
	 * A method to display the user's profile
	 */
	
	public static void displayProfile(BodyProfile profile) {
		System.out.println("\t\t" + profile.getName() + "'s Profile");
		System.out.println("Age : " + profile.getAge());
		System.out.println("Height : " + profile.getHeight());
		System.out.println("Weight : " + profile.getWeight());
		System.out.printf("BMI : %.1f\n", profile.getBMI());
		System.out.printf("BodyFat : %.1f\n", profile.getBodyFat());
	}
}
