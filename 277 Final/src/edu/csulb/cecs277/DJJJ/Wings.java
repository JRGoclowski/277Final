package edu.csulb.cecs277.DJJJ;

public class Wings extends MealPlanDecorator {
	
	/**
	 * Instance variables
	 * **/
	private String myWings;
	String[] wingFlavor = new String[] {"Mild Spicy", "Diablo", "Lemon Pepper", "BBQ", "Sesame"};
	
	/**
	 * Default constructor
	 * **/
	public Wings() {
		myWings = "N/A";
	}
	
	/**
	 * Sets the flavor of the wings and whether the wings are bone-in or boneless
	 * @param newWing - flavor of the wing
	 * @param boneIn - boolean determining bone-in or boneless
	 * **/
	public void setWings(String newWing, boolean boneIn) {
		
		if (boneIn) {
			myWings = "Bone-In ";
		} else {
			myWings = "Boneless ";
		}
		
		switch(newWing) {
		case "Mild Spicy": myWings = myWings + wingFlavor[0] + " Wings"; break;
		case "Diablo": myWings = myWings + wingFlavor[1] + " Wings"; break;
		case "Lemon Pepper": myWings = myWings + wingFlavor[2] + " Wings"; break;
		case "BBQ": myWings = myWings + wingFlavor[3] + " Wings"; break;
		case "Sesame": myWings = myWings + wingFlavor[4] + " Wings"; break;
		default: myWings = "Classic"; break;
		}
	}
	
	/**
	 * Gets the description of the Wings
	 * @return myWings - description of the Wings
	 * **/
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return myWings;
	}
}