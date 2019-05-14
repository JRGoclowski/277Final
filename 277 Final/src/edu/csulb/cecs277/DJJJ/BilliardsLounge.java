package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

//generates an BilliardsLounge room from the abstract class Room
public class BilliardsLounge extends Room implements Cloneable {

	//default constructor
	public BilliardsLounge() {
		super();
		setRoomCapacity(10);
		setHourlyCost(25);
		setRoomDescription("Enclosed lounge with pool table.");
		setRoomRestrictions("21+ only");
		addRoomAmenity("access to pool table and cues");
	}

	//unavailable for this room type
	public void upgradeTo(String upgrade) {}

	//upgrades the room's meal plan to the given meal plan and adjusts the flat cost appropriately
	public void upgradeTo(MealPlan newMeal) {
		if (getRoomMealPlan() == null) {
			addToRoomFlatCost(newMeal.getCost());	//replace getCost method
			setRoomMealPlan(newMeal);
		}
		else if (!(newMeal.getPlan().equals(getRoomMealPlan().getPlan()))) {		//----------->Requires placeholder "getName()" to be replaced by the real function to retrieve the name of a meal plan object
			addToRoomFlatCost(5 * (newMeal.getCost() - getRoomMealPlan().getCost()));	//Requires placeholder "getCost()" to be replaced by the real function to retrieve the cost of a meal plan object
			setRoomMealPlan(newMeal);
		}
	}
	
	//creates a clone of the room
	public Room clone() {
		KaraokeLounge cloneRoom = (KaraokeLounge) this.clone();
		return cloneRoom;
	}
	
}
