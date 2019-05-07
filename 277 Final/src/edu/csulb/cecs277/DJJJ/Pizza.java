package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public class Pizza {
	
	private String myPizza;
	private ArrayList<String> myTop;
	
	String[] toppings = new String[] {"Cheese", "Pepperoni", "Ham", "Jalapeno", "Sausage", "Mushroom", "Pineapple", "Bell Pepper", "Onion", "Garlic Chicken"};
	
	public Pizza() {
		myPizza = "N/A";
		myTop = new ArrayList<String>();
	}
	
	public void addTop(String newTop) {
		
		switch(newTop) {
		case "Cheese": myTop.add(toppings[0]); break;
		case "Pepperoni": myTop.add(toppings[1]); break;
		case "Ham": myTop.add(toppings[2]); break;
		case "Jalapeno": myTop.add(toppings[3]); break;
		case "Sausage": myTop.add(toppings[4]); break;
		case "Mushroom": myTop.add(toppings[5]); break;
		case "Pineapple": myTop.add(toppings[6]); break;
		case "Bell Pepper": myTop.add(toppings[7]); break;
		case "Onion": myTop.add(toppings[8]); break;
		case "Garlic Chicken": myTop.add(toppings[9]); break;
		default: myTop.add(toppings[0]); break;
		}
	}
	
	public String getDescription() {
		
		String finalTop[] = new String[myTop.size()];
		for (int i = 0; i < finalTop.length; i++) {
			finalTop[i] = myTop.get(i);
		}
		String joinTop = String.join(", ", finalTop);
		myPizza = "XL Gourmet Pizza w/ " + joinTop;
		return myPizza;
	}
}
