package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;
import java.util.Collections;

public class Date {
	
	public static void main(String args[]) {
		Date test = new Date(new Day(2, 13));
	}
	
	private Reservation[] mReservations;
	private Day mDay;
	private ArrayList<Time> openTimes = new ArrayList<Time>();
	
	public Date(Day day) {
		mDay = day;
		FillOpenTimes();
	}
	
	//TODO Check if there are issues with room availability
	public boolean isValidReservation(Reservation reservation) {
		boolean valid = true;
		Time startTime = reservation.getmFullStartTime();
		Time endTime = reservation.getmFullEndTime() ;
		Time guestStart = reservation.getmFunctionStartTime();
		Time guestEnd = reservation.getmFunctionEndTime();
		valid = (guestStart.isBusinessHours() && guestEnd.isBusinessHours());
		valid = ((isOpen(startTime, endTime)) && valid);
		return valid;
	}
	
	public boolean addReservation(Reservation reservation) {
		if (!(isValidReservation(reservation))) {
			return false;
		}
		
	}
	
	public Reservation GetReservation(Guest guest) {
		
	}
	
	public Reservation GetReservation(String guestName) {
		
	}
	
	//tested
	private void FillOpenTimes() {
		for (int i = 9; i < 24; i++) {
			for (int j = 0; j < 4; j++) {
				Time addTime = new Time(i, j*15);
				openTimes.add(addTime);
			}
		}
			
	}
	
	private void ReserveTimes(Time start, Time end) {
		int r = 0; 
		while (!(openTimes.get(r).isEqualTo(start))) {
			r++;
		}
		while (openTimes.get(r).isBefore(end)) {
			openTimes.remove(r++);
			
		}
	}
	
	private void ReleaseTimes(Time start, Time end) {
		int r = 0;
		while ((openTimes.get(r).isBefore(start))) {
			r++;
		}
		Time timeWalker = openTimes.get(r);
		while (timeWalker.isBefore(end)) {
			timeWalker.add(0, 15);
			r++;
		}
	}
	
	//tested
	private boolean isOpen(Time start, Time end) {
		int r = 0;
		boolean open = true;
		while (!(openTimes.get(r).isEqualTo(start))) {
			r++;
		}
		Time timeWalker = openTimes.get(r);
		while (timeWalker.isBefore(end) && open) {
			if (!timeWalker.isEqualTo(openTimes.get(r))) {
				open = false;
			}
			timeWalker.add(0, 15);
			r++;
		}
		return open;
	}
	
	public String toString() {
		return (mDay.toString() + ", has " + mReservations.length + " reservations");
	}
}
