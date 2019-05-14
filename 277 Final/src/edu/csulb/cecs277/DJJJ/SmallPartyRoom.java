package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

//generates an SmallPartyRoom room from the abstract class Room
public class SmallPartyRoom extends Room implements Cloneable {
	
	
	//default constructor
	public SmallPartyRoom() {
		setRoomCapacity(30);
		setHourlyCost(150);
		setRoomDescription("Room with party tables and chairs, adjacent to arcade.");
		addRoomAmenity("table & chair set-up");
		//setRoomMealPlan(Basic Meal Plan);      ------>Requires meal plan class to properly add a basic meal plan
	}

	//adds the provided amenity to the room's list and adjusts the flat cost appropriately
	public void upgradeTo(String upgrade) {
		boolean repeated = false;
		ArrayList<String> list = getRoomAmenities();
		for (int i=0; i<list.size(); i++) { if (upgrade.equals(list.get(i))) { repeated = true; } }
		if (!repeated) { addRoomAmenity(upgrade); }
		
		if (upgrade.toLowerCase().equals("party favors bag")) { addToRoomFlatCost(5); }
		else if (upgrade.toLowerCase().equals("projector")) { addToRoomHourlyCost(10); }
		else if (upgrade.toLowerCase().contains("decorations")) { addToRoomFlatCost(100); }
		else { System.out.println("Failed to add " + upgrade + " to SmallPartyRoom room " + getRoomNumber()); }
	}

	//upgrades the room's meal plan to the given meal plan and adjusts the flat cost appropriately
	public void upgradeTo(MealPlan newMeal) {
		if (!(newMeal.getName().equals(getRoomMealPlan().getName()))) {		//----------->Requires placeholder "getName()" to be replaced by the real function to retrieve the name of a meal plan object
			addToRoomFlatCost(newMeal.getCost() - getRoomMealPlan().getCost());	//Requires placeholder "getCost()" to be replaced by the real function to retrieve the cost of a meal plan object
			setRoomMealPlan(newMeal);
		}
	}
	
	//creates a clone of the room
	public Room clone() {
		SmallPartyRoom cloneRoom = (SmallPartyRoom) this.clone();
		return cloneRoom;
	}
	
}
