package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public class BasicPlan extends MealPlan {
	
	/**
	 * Instance variables
	 * **/
	private Pizza pizza1;
	private Pizza pizza2;
	private Pizza pizza3;
	private Soda soda1;
	private Soda soda2;
	private Soda soda3;
	
	ArrayList<Pizza> myPizzas = new ArrayList<Pizza>();
	ArrayList<Soda> mySodas = new ArrayList<Soda>();
	
	/**
	 * Default constructor
	 * **/
	public BasicPlan() {
		super("Basic Meal Plan", 65);
		pizza1 = new Pizza();
		pizza2 = new Pizza();
		pizza3 = new Pizza();
		myPizzas.add(pizza1);
		myPizzas.add(pizza2);
		myPizzas.add(pizza3);

		soda1 = new Soda();
		soda2 = new Soda();
		soda3 = new Soda();
		mySodas.add(soda1);
		mySodas.add(soda2);
		mySodas.add(soda3);
	}
	
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
	 * Prints BasicPlan object
	 * @return BasicPlan as a formatted string
	 * **/
	public String toString() {
		return super.getPlan() + "\n" + 
				pizza1.getDescription() + "\n" + 
				pizza2.getDescription() + "\n" +
				pizza3.getDescription() + "\n" +
				soda1.getDescription() + "\n" +
				soda2.getDescription() + "\n" +
				soda3.getDescription() + "\n" +
				"Cost: $" + super.getCost() + "\n";
	}
}