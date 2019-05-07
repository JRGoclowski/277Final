package edu.csulb.cecs277.DJJJ;

public class Soda {
	
	private String mySoda;
	
	String[] sodaFlavor = new String[] {"Coca-Cola", "Diet Coke", "Canada Dry", "Orange Crush", "Squirt", "Root Beer"};
	
	public Soda() {
		mySoda = "N/A";
	}
	
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
	
	public String getSoda() { return mySoda; }
}
