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
	 * getGender returns the user's gender
	 * @return The user's gender
	 */
	 
	public String getGender() {
		return gender;
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
	 * sets the bmi to a double value
	 * @param newBMI The value to set
	 */
	 
	public void setBMI(double newBMI) {
		bmi = newBMI;
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
		
		file.println("Name : " + name);
		file.println("Age : " + age);
		file.println("Gender : " + gender);
		file.println("Height(in) : " + height);
		file.println("Weight(lbs) : " + weight);
		file.printf("BMI : %.1f\n", getBMI());
		file.printf("Body Fat Percentage: %.1f\n", getBodyFat());
		
		file.close();
	}
	
	/**
	 * sets the body fat field to a specified double
	 * @param newBodyFat The value to set
	 */
	 
	public void setBodyFat(double newBodyFat) {
		bodyFat = newBodyFat;
	}

	/**
	 * Calculates the user's body fat percentage (male)
	 */
	
	public void setBodyFatMale(double girth) {	
			// Make calculation
			double result1 = (weight * 1.082) + 94.42;
			double leanBodyMass = result1 - (girth * 4.15);
			bodyFat = ((weight - leanBodyMass) * 100) / weight;		
	}
	
	/**
	 * Calculates the user's body fat percentage (female)
	 */
	 
	public void setBodyFatFemale(double wristCircum, double waistCircum, 
			double hipCircum, double forearmCircum) {
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
	
	/**
	 * Return the user's body fat percentage
	 * @return bodyFat The user's body fat percentage
	 */
	
	public double getBodyFat() {
		return bodyFat;
	}
}
