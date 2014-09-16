/**
 * BodyProfile holds and provides
 * information about the users body 
 * and health measurements
 * @author jvelez
 */

import java.io.*;

public class BodyProfile {
	private double height;
	private double weight;
	private String name;
	private int age;
	
	/**
	 * Constructor giving a height of 0.o
	 * and a weight of 0.0
	 */
	
	public BodyProfile() {
		height = 0.0;
		weight = 0.0;
		name = "";
		age = 0;
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
	 * @return The user's BMI
	 */
	
	public double getBMI() {
		double bmi = (weight * 703) / (height * height);
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
		
		file.close();
	}
}
