package edu.csulb.cecs277.DJJJ;

public abstract class MealPlan {
	
	private String planDesc;
	private int cost;
	
	public MealPlan() {
		planDesc = "N/A";
		cost = 0;
	}
	
	public MealPlan(String planDesc, int cost) {
		this.planDesc = planDesc;
		this.cost = cost;
	}
	
	public String getPlan() { return planDesc; }
	
	public int getCost() { return cost; }
	
	public void setPlan(String newPlan) { planDesc = newPlan; }
	
	public void setCost(int newCost) { cost = newCost; }
}
