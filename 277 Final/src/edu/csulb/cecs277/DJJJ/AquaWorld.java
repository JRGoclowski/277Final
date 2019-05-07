package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

//generates an AquaWorld room from the abstract class Room
public class AquaWorld extends Room implements Cloneable {

	//default constructor
	public AquaWorld() {
		setRoomCapacity(75);
		setHourlyCost(700);
		setRoomDescription("Olympic-sized pool with water slide, kiddie pool, and large jacuzzi.");
		setRoomRestrictions("To access water facilities, bathing suits must be worn at all times.");
		addRoomAmenity("access to showers/lockers");
		addRoomAmenity("life guards on duty");
		addRoomAmenity("DJ");
		addRoomAmenity("table & chair set-up");
		//setRoomMealPlan(Basic Meal Plan);      ------>Requires meal plan class to properly add a basic meal plan
	}

	//adds the provided amenity to the room's list and adjusts the flat cost appropriately
	public void upgradeTo(String upgrade) {
		boolean repeated = false;
		ArrayList<String> list = getRoomAmenities();
		for (int i=0; i<list.size(); i++) { if (upgrade.equals(list.get(i))) { repeated = true; } }
		if (!repeated) { addRoomAmenity(upgrade); }
		
		if (upgrade.toLowerCase().equals("towel rentals")) { addToRoomFlatCost(2); }
		else if (upgrade.toLowerCase().equals("party favors bag")) { addToRoomFlatCost(5); }
		else if (upgrade.toLowerCase().equals("projector")) { addToRoomHourlyCost(10); }
		else if (upgrade.toLowerCase().contains("decorations")) { addToRoomFlatCost(100); }
		else { System.out.println("Failed to add " + upgrade + " to AquaWorld room " + getRoomNumber()); }
	}

	//upgrades the room's meal plan to the given meal plan and adjusts the flat cost appropriately
	public void upgradeTo(MealPlan newMeal) {
		if (!(newMeal.getName().equals(getRoomMealPlan().getName()))) {		//----------->Requires placeholder "getName()" to be replaced by the real function to retrieve the name of a meal plan object
			addToRoomFlatCost(5 * (newMeal.getCost() - getRoomMealPlan().getCost()));	//Requires placeholder "getCost()" to be replaced by the real function to retrieve the cost of a meal plan object
			setRoomMealPlan(newMeal);
		}
	}
	
	//creates a clone of the room
	public Room clone() {
		AquaWorld aquaRoom = (AquaWorld) this.clone();
		return aquaRoom;
	}
	
}
