package edu.csulb.cecs277.DJJJ;

public class Reservation {
	
	private int mConfirmationNumber;
	private int mMaintenanceTime;
	private Time mFunctionStartTime; 
	private Time mFunctionEndTime;
	private Time mFullStartTime; 
	private Time mFullEndTime;
	private Day mDay;
	private Room mRoom;
	private Guest mGuest;
	private Reservation mSetup;//TODO Figure out how to not have this be a recursive type. Too difficult to handle 
	private Reservation mGuestReservation;//Identifying if it is a cleanup/setup reservation 
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
		if (!(start.isBusinessHours() && end.isBusinessHours())) {
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
		else {
			mFunctionStartTime = start;
			mFunctionEndTime = end;
			mDay = day;
			mRoom = room;
			mGuest = guest;
			setMaintenanceTime();
			mGuestReservation = this;
			mSetup = MakeSetupReservation();
			mCleanup = MakeCleanupReservation();
			mFullStartTime = mSetup.mFunctionStartTime.Clone();
			mFullEndTime = mCleanup.mFunctionEndTime.Clone();
		}
		
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
		setup.mFunctionStartTime = mFunctionStartTime.Clone();
		setup.mFunctionEndTime = mFunctionStartTime.Clone();
		setup.mFunctionStartTime.sub(0, mMaintenanceTime);
		setup.mRoom = mRoom;
		setup.isSetup = true;
		return setup;
	}
	
	private Reservation MakeCleanupReservation () {
		Reservation Cleanup = new Reservation();
		Cleanup.mDay = mDay;
		Cleanup.mFunctionStartTime = mFunctionEndTime.Clone();
		Cleanup.mFunctionEndTime = mFunctionEndTime.Clone();
		if (!Cleanup.mFunctionEndTime.add(0, mMaintenanceTime)) {
			Cleanup.mFunctionEndTime = Time.getEndOfDay();
		}
		Cleanup.mRoom = mRoom;
		Cleanup.isCleanup = true;
		return Cleanup;
	}
	
	
	public void EditGuestStartTime(Time newTime) 
	{
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
	
	public Reservation GetResTypeAt(Time timeRes) {
		if (timeRes.isBefore(mFullStartTime) || (!timeRes.isBefore(mFullEndTime))) {
			return null;
		}
		if (timeRes.isBefore(mFunctionStartTime)) {
			return mSetup;
		}
		if (timeRes.isBefore(mFunctionEndTime)) {
			return mGuestReservation;
		}
		else {
			return mCleanup;
		}
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
	
	/**
	 * @return the mConfirmationNumber
	 */
	public int getmConfirmationNumber() {
		return mConfirmationNumber;
	}

	/**
	 * @param mConfirmationNumber the mConfirmationNumber to set
	 */
	public void setmConfirmationNumber(int mConfirmationNumber) {
		this.mConfirmationNumber = mConfirmationNumber;
	}
	
	public String toString() {
		if (isCleanup) {
			return "Cleanup Reservation";
		}
		if (isSetup) {
			return "Setup Reservation";
		}
		return "Reservation of " + this.mGuestReservation.getmGuest().getmName() + " on " + getmDay().toString();
	}
	
	
}

