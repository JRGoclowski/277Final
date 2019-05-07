package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

//generates an KaraokeLounge room from the abstract class Room
public class KaraokeLounge extends Room implements Cloneable {

	//default constructor
	public KaraokeLounge() {
		setRoomCapacity(10);
		setHourlyCost(30);
		setRoomDescription("Enclosed lounge with karaoke machine.");
		addRoomAmenity("access to karaoke machine");
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
		else { System.out.println("Failed to add " + upgrade + " to KaraokeLounge room " + getRoomNumber()); }
	}

	//upgrades the room's meal plan to the given meal plan and adjusts the flat cost appropriately
	public void upgradeTo(MealPlan newMeal) {
		if (getRoomMealPlan()==NULL) {
			addToRoomFlatCost(newMeal.getCost());	//replace getCost method
			setRoomMealPlan(newMeal);
		}
		else if (!(newMeal.getName().equals(getRoomMealPlan().getName()))) {		//----------->Requires placeholder "getName()" to be replaced by the real function to retrieve the name of a meal plan object
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
