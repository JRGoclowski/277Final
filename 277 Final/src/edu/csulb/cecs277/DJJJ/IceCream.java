package edu.csulb.cecs277.DJJJ;

public class IceCream extends MealPlanDecorator {
	
	/**
	 * Instance variables
	 * **/
	private String myIceCream;
	String[] iceCreamFlavor = new String[] {"Chocolate Fudge", "Vanilla Bean", "Strawberry Shortcake", "Choco-mint", "Butter Pecan"};
	
	/**
	 * Default constructor
	 * **/
	public IceCream() {
		myIceCream = "N/A";
	}
	
	/**
	 * Sets the Ice Cream flavor
	 * @param newIceCream - flavor of the ice cream
	 * **/
	public void setIceCream(String newIceCream) {
		
		switch(newIceCream) {
		case "Chocolate Fudge": myIceCream = iceCreamFlavor[0] + " Ice Cream"; break;
		case "Vanilla Bean": myIceCream = iceCreamFlavor[1] + " Ice Cream"; break;
		case "Strawberry Shortcake": myIceCream = iceCreamFlavor[2] + " Ice Cream"; break;
		case "Choco-mint": myIceCream = iceCreamFlavor[3] + " Ice Cream"; break;
		case "Butter Pecan": myIceCream = iceCreamFlavor[4] + " Ice Cream"; break;
		default: myIceCream = "Frozen Milk"; break;
		}
	}
	
	/**
	 * Gets the description of the ice cream
	 * @return myIceCream - description of the ice cream
	 * **/
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return myIceCream;
	}
}