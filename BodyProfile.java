/**
 * BodyProfile holds and provides
 * information about the users body 
 * and health measurements
 * @author jvelez
 */

import java.io.*;
import java.util.Scanner;

public class BodyProfile {
	private double height;
	private double weight;
	private String name;
	private int age;
	private String gender;
	private double bmi = 0;
	private double bodyFat = 0;
	
	/**
	 * Constructor giving a height of 0.0
	 * and a weight of 0.0
	 */
	
	public BodyProfile() {
		height = 0.0;
		weight = 0.0;
		name = "";
		age = 0;
		gender = "";
	}
	
	/**
	 * Constructor giving a height and weight
	 * specified by the user
	 */
	
	public BodyProfile(double h, double w) {
		height = h;
		weight = w;
		name = "";
		age = 0;
		gender = "";
	}
	
	/**
	 * Sets height to a user specified double
	 * @param newHeight The user's height
	 */
	
	public void setHeight(double newHeight) {
		height = newHeight;
	}
	
	/**
	 * Sets weight to a user specified double
	 * @param newWeight The user's weight
	 */
	
	public void setWeight(double newWeight) {
		weight = newWeight;
	}
	
	/**
	 * Sets the user's name
	 * @param newName The user's name
	 */
	
	public void setName(String newName) {
		name = newName;
	}
	
	/**
	 * Sets the user's age
	 * @param newAge The user's age
	 */
	
	public void setAge(int newAge) {
		age = newAge;
	}
	
	/**
	 * Sets the user's gender
	 * @param newGender The user's gender
	 */
	
	public void setGender(String newGender) {
		if(newGender.equalsIgnoreCase("male")) {
			gender = "MALE";
		}
		else if(newGender.equalsIgnoreCase("female")) {
			gender = "FEMALE";
		}
		else
			System.out.println("ERROR");
	}
	
	/**
	 * Returns the user's height
	 * @return The user's height
	 */
	
	public double getHeight() {
		return height;
	}
	
	/**
	 * Returns the user's weight
	 * @return The user's weight
	 */
	
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Returns the user's name
	 * @return The user's name
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the user's age
	 * @return The user's age
	 */
	
	public int getAge() {
		return age;
	}
	
	/**
	 * Calculates and returns the user's 
	 * Body Mass Index (BMI)
	 */
	
	public void setBMI() {
		bmi = (weight * 703) / (height * height);
	}
	
	/**
	 * Returns the user's BMI
	 * @return bmi The user's BMI
	 */
	
	public double getBMI() {
		return bmi;
	}
	
	/**
	 * Saves the user's info to a text
	 * file
	 */
	
	public void saveInfo() throws IOException {
		PrintWriter file = new PrintWriter("Profile");
		
		file.println("\t\t" + name + "'s Profile");
		file.println("Age : " + age);
		file.println("Height : " + height);
		file.println("Weight : " + weight);
		file.printf("BMI : %.1f\n", getBMI());
		file.printf("Body Fat : %.1f\n", getBodyFat());
		
		file.close();
	}

	/**
	 * Calculates the user's body fat percentage
	 */
	
	public void setBodyFat() {
		Scanner keyboard = new Scanner(System.in);
		
		if(gender.equals("MALE")) {
			// Gather measurement
			System.out.print("Enter your waist girth(inches): ");
			double girth = keyboard.nextDouble();
			
			// Make calculation
			double result1 = (weight * 1.082) + 94.42;
			double leanBodyMass = result1 - (girth * 4.15);
			bodyFat = ((weight - leanBodyMass) * 100) / weight;
		}
		else {
			// Gather measurements
			System.out.print("Enter the circumference of your wrist(inches): ");
			double wristCircum = keyboard.nextDouble();
			System.out.print("Enter the cirumference of your waist(inches): ");
			double waistCircum = keyboard.nextDouble();
			System.out.print("Enter the circumference of your hips(inches): ");
			double hipCircum = keyboard.nextDouble();
			System.out.print("Enter the circumference of your forearm(inches: ");
			double forearmCircum = keyboard.nextDouble();
			
			// make calculations
			double result1 = weight * 0.732;
			double result2 = result1 + 8.987;
			double result3 = wristCircum / 3.14;
			double result4 = waistCircum * 0.157;
			double result5 = hipCircum * 0.249;
			double result6 = forearmCircum * 0.434;
			double result7 = result2 + result3;
			double result8 = result7 - result4;
			double result9 = result8 - result5;
			double leanBodyMass = result6 + result9;
			
			bodyFat = ((weight - leanBodyMass) * 100) / weight;
			
		}
	}
	
	/**
	 * Return the user's body fat percentage
	 * @return bodyFat The user's body fat percentage
	 */
	
	public double getBodyFat() {
		return bodyFat;
	}
}
