package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public class Date {
	
	public static void main(String args[]) {
		Date test = new Date(new Day(2, 13));
		System.out.println();
	}
	
	private Reservation[] mReservations;
	private Day mDay;
	private ArrayList<Time> openTimes = new ArrayList<Time>();
	
	public Date(Day day) {
		mDay = day;
		FillOpenTimes();
	}
	
	public boolean checkValidReservation() {
		
	}
	
	public boolean addReservation() {
		
	}
	
	public Reservation GetReservation(Guest guest) {
		
	}
	
	public Reservation GetReservation(String guestName) {
		
	}
	
	private void FillOpenTimes() {
		for (int i = 9; i < 24; i++) {
			for (int j = 0; j < 4; j++) {
				Time addTime = new Time(i, j*15);
				openTimes.add(addTime);
			}
		}
			
	}
	
	private boolean isContinuous(Time start, Time end) {
		
	}
	
	public String toString() {
		return (mDay.toString() + ", has " + mReservations.length + " reservations");
	}
}
