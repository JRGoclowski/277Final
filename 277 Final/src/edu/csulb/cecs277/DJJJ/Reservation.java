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
	private boolean isSetup = false;
	private boolean isCleanup = false;
	
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
		mGuestReservation = this;
		mSetup = MakeSetupReservation();
		mCleanup = MakeCleanupReservation();
		mFullStartTime = mSetup.mFunctionStartTime;
		mFullEndTime = mCleanup.mFunctionEndTime;
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
	
	private Reservation MakeSetupReservation () {
		Reservation setup = new Reservation();
		setup.mDay = mDay;
		setup.mFunctionStartTime = mFunctionStartTime;
		setup.mFunctionStartTime.sub(0, mMaintenanceTime);
		setup.mFunctionEndTime = mFunctionStartTime;
		setup.mRoom = mRoom;
		setup.isSetup = true;
		return setup;
	}
	
	private Reservation MakeCleanupReservation () {
		Reservation Cleanup = new Reservation();
		Cleanup.mDay = mDay;
		Cleanup.mFunctionStartTime = mFunctionStartTime;
		Cleanup.mFunctionEndTime = mFunctionStartTime;
		Cleanup.mFunctionEndTime.add(0, mMaintenanceTime);
		Cleanup.mRoom = mRoom;
		Cleanup.isCleanup = true;
		return Cleanup;
	}
	
	
	public void EditGuestStartTime(Time newTime) {
		int[] timeDiff = mFunctionStartTime.difference(newTime);
		mSetup.mFunctionStartTime.add(timeDiff[0], timeDiff[1]);
		mSetup.mFunctionEndTime.add(timeDiff[0], timeDiff[1]);
		mFunctionStartTime = mSetup.mFunctionEndTime;
		mFullStartTime = mSetup.mFunctionStartTime;
	}
	
	public void EditGuestEndTime(Time newTime) {
		int[] timeDiff = mFunctionEndTime.difference(newTime);
		mCleanup.mFunctionStartTime.add(timeDiff[0], timeDiff[1]);
		mCleanup.mFunctionEndTime.add(timeDiff[0], timeDiff[1]);
		mFunctionEndTime = mCleanup.mFunctionStartTime;
		mFullEndTime = mCleanup.mFunctionEndTime;
	}
	
	public Reservation GetResAtTime(Time timeRes) {
		if 
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

	public Time getmFunctionStartTime() {
		return mFunctionStartTime;
	}

	public Time getmFunctionEndTime() {
		return mFunctionEndTime;
	}

	public Time getmFullStartTime() {
		return mFullStartTime;
	}

	public Time getmFullEndTime() {
		return mFullEndTime;
	}

	public Reservation getmSetup() {
		return mSetup;
	}

	public Reservation getmGuestReservation() {
		return mGuestReservation;
	}

	public Reservation getmCleanup() {
		return mCleanup;
	}

	public boolean isSetup() {
		return isSetup;
	}

	public boolean isCleanup() {
		return isCleanup;
	}

	
	
}

