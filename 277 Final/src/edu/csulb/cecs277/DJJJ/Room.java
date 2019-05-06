package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public abstract class Room {	//may not need to extend Cloneable
								//As a matter of fact it doesn't, and actually breaks my code if you do. so plz no. -Jonathan
	
	private int mNumber;
	private int mCapacity;
	private String mDescription;
	private String mRestrictions;
	private int mHourlyCost;
	private int mFlatCost;
	private ArrayList<Date> mDates;
	private ArrayList<String> mAmenities;
	private MealPlan mMealPlan;
	
	//Default constructor
	public Room() {
		mNumber = 0;
		mCapacity = 0;
		mDescription = "";
		mRestrictions = "";
		mHourlyCost = 0;
		mFlatCost = 0;
		mDates = null;
		mMealPlan = null;
	}
	
	//------------------This method may not be necessary, revisit------------------------------------
	//Overloaded constructor for full information
	public Room(int number, int capacity, String description, int hourlyCost, ArrayList<Date> dates, MealPlan mealPlan) {
		mNumber = number;
		mCapacity = capacity;
		mDescription = description;
		mRestrictions = "";
		mHourlyCost = hourlyCost;
		mDates = dates;
		mMealPlan = mealPlan;
		mFlatCost = 0;
	}
	
	//Retrieves the room number
	public int getRoomNumber() {
		return mNumber;
	}
	
	//Sets the room number
	public void setRoomNumber(int num) {
		mNumber = num;
	}
	
	//Retrieves the room capacity
	public int getRoomCapacity() {
		return mCapacity;
	}
	
	//Sets the room capacity
	public void setRoomCapacity(int capacity) {
		mCapacity = capacity;
	}
	
	//Retrieves the room description
	public String getRoomDescription() {
		return mDescription;
	}
	
	//Sets the room description
	public void setRoomDescription(String desc) {
		mDescription = desc;
	}
	
	//Retrieves the room restrictions
	public String getRoomRestrictions() {
		return mRestrictions;
	}
	
	//Sets the room restrictions
	public void setRoomRestrictions(String rest) {
		mRestrictions = rest;
	}
	
	//Retrieves the room hourly cost
	public int getHourlyCost() {
		return mHourlyCost;
	}
	
	//Sets the room hourly cost
	public void setHourlyCost(int cost) {
		mHourlyCost = cost;
	}
	
	//Retrieves the room dates
	public ArrayList<Date> getRoomDates() {
		return mDates;
	}
	
	//sets the room dates
	public void setRoomDates(ArrayList<Date> dates) {
		mDates = dates;
	}
	
	//Retrieves the room meal plan
	public MealPlan getRoomMealPlan() {
		return mMealPlan;
	}
	
	//sets the room meal plan
	public void setRoomMealPlan(MealPlan plan) {
		mMealPlan = plan;
	}
	
	//adds the provided amenity
	public void addRoomAmenity(String amenity) {
		mAmenities.add(amenity);
	}
	
	//adds to the base cost of the room
	public void addToRoomFlatCost(int cost) {
		mFlatCost += cost;
	}
	
	//adds to the hourly cost of the room
	public void addToRoomHourlyCost(int cost) {
		mHourlyCost += cost;
	}
	
	//Retrieves the room amenities
	public ArrayList<String> getRoomAmenities() {
		return mAmenities;
	}
	
	//returns the total cost of the room given hours used
	public double getTotalCost(double hours) {
		return mHourlyCost * hours + mFlatCost;
	}
	
	//overloaded method to upgrade amenity
	public abstract void upgradeTo(String upgrade);
	
	//overloaded method to upgrade meal plan
	public abstract void upgradeTo(MealPlan newMeal);
	
	//abstract clone method for subclasses to implement
	public abstract Object clone();
	
}
