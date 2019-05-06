package project5_FinalProject;

import java.util.ArrayList;

public class SilverPlan extends MealPlan {
	
	private Pizza pizza1;
	private Pizza pizza2;
	private Pizza pizza3;
	private Soda soda1;
	private Soda soda2;
	private Soda soda3;
	private Soda soda4;
	private Soda soda5;
		
	ArrayList<Pizza> myPizzas = new ArrayList<Pizza>();
	ArrayList<Soda> mySodas = new ArrayList<Soda>();
	
	public SilverPlan() {
		super("Silver Meal Plan", 90);
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
	}
	
	public ArrayList<Pizza> getPizzas() { return myPizzas; }
	
	public ArrayList<Soda> getSodas() { return mySodas; }
	
	public String toString() {
		
		return super.getPlan() + "\n" + 
				pizza1.getDescription() + "\n" + 
				pizza2.getDescription() + "\n" +
				pizza3.getDescription() + "\n" +
				soda1.getSoda() + "\n" +
				soda2.getSoda() + "\n" +
				soda3.getSoda() + "\n" +
				soda4.getSoda() + "\n" +
				soda5.getSoda() + "\n" +
				"Salad\nBreadsticks\n" +
				"Cost: $" + super.getCost() + "\n";
	}
}
