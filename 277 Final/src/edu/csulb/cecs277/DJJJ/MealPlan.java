package edu.csulb.cecs277.DJJJ;

public abstract class MealPlan {
	
	/**
	 * Instance variables
	 * **/
	private String planDesc;
	private int cost;
	
	/**
	 * Default constructor
	 * **/
	public MealPlan() {
		planDesc = "N/A";
		cost = 0;
	}
	
	/**
	 * Overloaded constructor
	 * @param planDesc - description of the plan
	 * @param cost - cost of the plan
	 * **/
	public MealPlan(String planDesc, int cost) {
		this.planDesc = planDesc;
		this.cost = cost;
	}
	
	/**
	 * Gets the description of the plan
	 * @return planDesc - descriptoin of the plan
	 * **/
	public String getPlan() { return planDesc; }
	
	/**
	 * Gets the cost of the plan
	 * @return cost - cost of the plan
	 * **/
	public int getCost() { return cost; }
	
	/**
	 * Sets the description of the plan
	 * @param newPlan - new description of the plan
	 * **/
	public void setPlan(String newPlan) { planDesc = newPlan; }
	
	/**
	 * Sets the cost of the plan
	 * @param newCost - new cost of the plan
	 * **/
	public void setCost(int newCost) { cost = newCost; }
}