package edu.csulb.cecs277.DJJJ;

public class Reservation {
	
	private Time mStartTime; 
	private Time mEndTime;
	private Day mDay;
	private Room mRoom;
	private Guest mGuest;
	
	public Reservation(Time start, Time end, Day day, Room room, Guest guest) {
		
	}
	
	public Reservation (Reservation existing, boolean setup) {
		int timeToWork = (setup) ? (-1) : (1);
		if (existing.mRoom instanceof SmallPartyRoom) {
			 timeToWork *= 30;
		}
		else if (existing.mRoom instanceof KaraokeLounge || existing.mRoom instanceof BilliardsLounge) {
			timeToWork *= 15;
		}
		else if ()
		Time workStartTime;
		workStartTime = (setup) ? (mStartTime) : (mEndTime);
	}
	
}

