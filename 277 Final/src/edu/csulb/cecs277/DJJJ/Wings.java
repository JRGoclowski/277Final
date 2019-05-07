package edu.csulb.cecs277.DJJJ;

public class Wings {
	
	private String myWings;
	
	String[] wingFlavor = new String[] {"Mild Spicy", "Diablo", "Lemon Pepper", "BBQ", "Sesame"};
	
	public Wings() {
		myWings = "N/A";
	}
	
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
	
	public String getWings() { return myWings; }
}
