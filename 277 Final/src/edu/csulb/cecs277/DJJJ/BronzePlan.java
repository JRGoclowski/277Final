package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public class BronzePlan extends MealPlan {
	
	/**
	 * Instance variables
	 * **/
	private Pizza pizza1;
	private Pizza pizza2;
	private Pizza pizza3;
	private Soda soda1;
	private Soda soda2;
	private Soda soda3;
	private Soda soda4;
	private Soda soda5;
	private String addon;
		
	ArrayList<Pizza> myPizzas = new ArrayList<Pizza>();
	ArrayList<Soda> mySodas = new ArrayList<Soda>();
	
	/**
	 * Default constructor
	 * **/
	public BronzePlan() {
		super("Bronze Meal Plan", 75);
		pizza1 = new Pizza();
		pizza2 = new Pizza();
		pizza3 = new Pizza();
		myPizzas.add(pizza1);
		myPizzas.add(pizza2);
		myPizzas.add(pizza3);

		soda1 = new Soda();
		soda2 = new Soda();
		soda3 = new Soda();
		soda4 = new Soda();
		soda5 = new Soda();
		mySodas.add(soda1);
		mySodas.add(soda2);
		mySodas.add(soda3);
		mySodas.add(soda4);
		mySodas.add(soda5);
		addon = "N/A";
	}
	
	/**
	 * Sets the addon for the plan (Salad or Breadsticks)
	 * @param newAddon - addon to be set
	 * **/
	public void setAddon(String newAddon) {
		
		switch(newAddon) {
		case "Salad": addon = "Salad"; break;
		case "Breadsticks": addon = "Breadsticks"; break;
		default: addon = "N/A";
		}
	}
	
	/**
	 * Gets the addon
	 * @return addon - could either be Salad or Breadstick as a String
	 * **/
	public String getAddon() { return addon; }
	
	/**
	 * Gets the ArrayList of Pizza objects
	 * @return myPizzas - ArrayList of Pizza objects
	 * **/
	public ArrayList<Pizza> getPizzas() { return myPizzas; }
	
	/**
	 * Gets the ArrayList of Soda objects
	 * @return mySodas - ArrayList of Soda objects
	 * **/
	public ArrayList<Soda> getSodas() { return mySodas; }
	
	/**
	 * Prints BronzePlan object
	 * @return BronzePlan as a formatted string
	 * **/
	public String toString() {
		return super.getPlan() + "\n" + 
				pizza1.getDescription() + "\n" + 
				pizza2.getDescription() + "\n" +
				pizza3.getDescription() + "\n" +
				soda1.getDescription() + "\n" +
				soda2.getDescription() + "\n" +
				soda3.getDescription() + "\n" +
				soda4.getDescription() + "\n" +
				soda5.getDescription() + "\n" +
				getAddon() + "\n" +
				"Cost: $" + super.getCost() + "\n";
	}
}