package project5_FinalProject;

public class IceCream {
	
	private String myIceCream;
	
	String[] iceCreamFlavor = new String[] {"Chocolate Fudge", "Vanilla Bean", "Strawberry Shortcake", "Choco-mint", "Butter Pecan"};
	
	public IceCream() {
		myIceCream = "N/A";
	}
	
	public void setIceCream(String newIceCream) {
		
		switch(newIceCream) {
		case "Chocolate Fudge": myIceCream = iceCreamFlavor[0] + " Ice Cream"; break;
		case "Vanilla Bean": myIceCream = iceCreamFlavor[1] + " Ice Cream"; break;
		case "Strawberry Shortcake": myIceCream = iceCreamFlavor[2] + " Ice Cream"; break;
		case "Choco-mint": myIceCream = iceCreamFlavor[3] + " Ice Cream"; break;
		case "Butter Pecan": myIceCream = iceCreamFlavor[4] + " Ice Cream"; break;
		default: myIceCream = "Frozen Milk"; break;
		}
	}
	
	public String getIceCream() { return myIceCream; }
}
