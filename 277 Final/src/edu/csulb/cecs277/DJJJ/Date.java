package edu.csulb.cecs277.DJJJ;

public class Date {
	
	private Reservation[] mReservations;
	private Day mDay;
	
	
	public boolean checkValidReservation() {
		
	}
	
	public boolean addReservation() {
		
	}
	
	public Reservation GetReservation(Guest guest) {
		
	}
	
	public Reservation GetReservation(String guestName) {
		
	}
	
	public String toString() {
		return (mDay.toString() + ", has " + mReservations.length + " reservations");
	}
}
