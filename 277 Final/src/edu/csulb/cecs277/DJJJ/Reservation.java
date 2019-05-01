package edu.csulb.cecs277.DJJJ;

public class Reservation {
	
	private int mMaintenanceTime;
	private Time mFunctionStartTime; 
	private Time mFunctionEndTime;
	private Time mFullStartTime; 
	private Time mFullEndTime;
	private Day mDay;
	private Room mRoom;
	private Guest mGuest;
	private Reservation mSetup;
	private Reservation mGuestReservation;
	private Reservation mCleanup;
	
	public Reservation() {
		mMaintenanceTime = 0;
		mFunctionStartTime = null; 
		mFunctionEndTime = null;
		mFullStartTime = null; 
		mFullEndTime = null;
		mDay = null;
		mRoom = null;
		mGuest = null;
		mSetup = null;
		mGuestReservation = null;
		mCleanup = null;
	}
	
	public Reservation(Time start, Time end, Day day, Room room, Guest guest) {
		mFunctionStartTime = start;
		mFunctionEndTime = end;
		mDay = day;
		mRoom = room;
		mGuest = guest;
		setMaintenanceTime();
		mSetup = MakeSetupReservation(this,true);
		mCleanup = MakeCleanupReservation(this, false);
		mGuestReservation = this;
	}
	
	private void setMaintenanceTime() {
		if (mRoom instanceof SmallPartyRoom) {
			mMaintenanceTime = 30;
		}
		else if (mRoom instanceof KaraokeLounge || mRoom instanceof BilliardsLounge) {
			mMaintenanceTime = 15;
		}
		else {
			mMaintenanceTime = 60;
		}
	}
	
	private Reservation MakeSetupReservation (Reservation existing) {
		Reservation setup = new Reservation();
		int timeCalc = mFunctionStartTime.getmMinutes() - mMaintenanceTime;
		if (timeCalc < 0) {
			
		}
		}
		setup.mFunctionStartTime = mFunctionStartTime;
		setup.mDay = mDay;
		
		
	}
	
	private Reservation MakeCleanupReservation (Reservation existing) {
		
	}

	public Time getmGuestStartTime() {
		return mFunctionStartTime;
	}

	public void setmGuestStartTime(Time mGuestStartTime) {
		this.mFunctionStartTime = mGuestStartTime;
	}

	public Time getmGuestEndTime() {
		return mFunctionEndTime;
	}

	public void setmGuestEndTime(Time mGuestEndTime) {
		this.mFunctionEndTime = mGuestEndTime;
	}

	public Day getmDay() {
		return mDay;
	}

	public void setmDay(Day mDay) {
		this.mDay = mDay;
	}

	public Room getmRoom() {
		return mRoom;
	}

	public void setmRoom(Room mRoom) {
		this.mRoom = mRoom;
	}

	public Guest getmGuest() {
		return mGuest;
	}

	public void setmGuest(Guest mGuest) {
		this.mGuest = mGuest;
	}

	public int getmMaintenanceTime() {
		return mMaintenanceTime;
	}

	public Time getmFullStartTime() {
		return mFullStartTime;
	}

	public Time getmFullEndTime() {
		return mFullEndTime;
	}

	public Reservation getmGuestReservation() {
		return mGuestReservation;
	}

	public Reservation getmCleanup() {
		return mCleanup;
	}
	
}

