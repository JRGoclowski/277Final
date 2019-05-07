package project5_FinalProject;

import java.util.ArrayList;

public class PlatinumPlan extends MealPlan {
	
	private Pizza pizza1;
	private Pizza pizza2;
	private Pizza pizza3;
	private Pizza pizza4;
	private Soda soda1;
	private Soda soda2;
	private Soda soda3;
	private Soda soda4;
	private Soda soda5;
	private Wings wings1;
	private Wings wings2;
	private IceCream ice1;
	private IceCream ice2;
		
	ArrayList<Pizza> myPizzas = new ArrayList<Pizza>();
	ArrayList<Soda> mySodas = new ArrayList<Soda>();
	ArrayList<Wings> myWings = new ArrayList<Wings>();
	ArrayList<IceCream> myIce = new ArrayList<IceCream>();
	
	public PlatinumPlan() {
		super("Platinum Meal Plan", 150);
		pizza1 = new Pizza();
		pizza2 = new Pizza();
		pizza3 = new Pizza();
		pizza4 = new Pizza();
		myPizzas.add(pizza1);
		myPizzas.add(pizza2);
		myPizzas.add(pizza3);
		myPizzas.add(pizza4);

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
		
		wings1 = new Wings();
		wings2 = new Wings();
		myWings.add(wings1);
		myWings.add(wings2);
		
		ice1 = new IceCream();
		ice2 = new IceCream();
		myIce.add(ice1);
		myIce.add(ice2);
	}
	
	public ArrayList<Pizza> getPizzas() { return myPizzas; }
	
	public ArrayList<Soda> getSodas() { return mySodas; }
	
	public ArrayList<Wings> getWings() { return myWings; }
	
	public ArrayList<IceCream> getIce() { return myIce; }
	
	public String toString() {
		
		return super.getPlan() + "\n" + 
				pizza1.getDescription() + "\n" + 
				pizza2.getDescription() + "\n" +
				pizza3.getDescription() + "\n" +
				pizza4.getDescription() + "\n" +
				soda1.getSoda() + "\n" +
				soda2.getSoda() + "\n" +
				soda3.getSoda() + "\n" +
				soda4.getSoda() + "\n" +
				soda5.getSoda() + "\n" +
				"Salad\nBreadsticks\n" +
				wings1.getWings() + "\n" +
				wings2.getWings() + "\n" +
				ice1.getIceCream() + "\n" +
				ice2.getIceCream() + "\n" +
				"Cost: $" + super.getCost() + "\n";
	}
}
