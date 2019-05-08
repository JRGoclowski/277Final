package edu.csulb.cecs277.DJJJ;

public class Soda extends MealPlanDecorator {
	
	/**
	 * Instance variables
	 * **/
	private String mySoda;
	String[] sodaFlavor = new String[] {"Coca-Cola", "Diet Coke", "Canada Dry", "Orange Crush", "Squirt", "Root Beer"};
	
	/**
	 * Default constructor
	 * **/
	public Soda() {
		mySoda = "N/A";
	}
	
	/**
	 * Sets the flavor of the soda
	 * @param newSoda - flavor of the soda
	 * **/
	public void setSoda(String newSoda) {
		
		switch(newSoda) {
		case "Coca-Cola": mySoda = sodaFlavor[0]; break;
		case "Diet Coke": mySoda = sodaFlavor[1]; break;
		case "Canada Dry": mySoda = sodaFlavor[2]; break;
		case "Orange Crush": mySoda = sodaFlavor[3]; break;
		case "Squirt": mySoda = sodaFlavor[4]; break;
		case "Root Beer": mySoda = sodaFlavor[5]; break;
		default: mySoda = "Water";
		}
	}
	
	/**
	 * Gets the description of the Soda
	 * @return mySoda - description of the Soda
	 * **/
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return mySoda;
	}
}