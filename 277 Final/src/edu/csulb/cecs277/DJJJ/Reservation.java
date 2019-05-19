package edu.csulb.cecs277.DJJJ;

public class Reservation {
	
	public static final Reservation TEST_RESERVATION = new Reservation(Time.ALL_TIMES[2], Time.ALL_TIMES[6], new Day(1,1,2015), new SmallPartyRoom(), Guest.DIO_BRANDO);
	
	private int mConfirmationNumber;
	private int mMaintenanceTime;
	private Time mFunctionStartTime; 
	private Time mFunctionEndTime;
	private Time mFullStartTime;
	private Time mFullEndTime;
	private MealPlan mMealPlan;
	private Day mDay;
	private Room mRoom;
	private Guest mGuest;
	private Reservation mSetup;//TODO Figure out how to not have this be a recursive type. Too difficult to handle 
	private Reservation mGuestReservation;//Identifying if it is a cleanup/setup reservation 
	private Reservation mCleanup;
	private String mRoomTheme;
	private boolean isSetup = false;
	private boolean isCleanup = false;
	private boolean checkedIn = false;
	private boolean checkedOut = false;
	
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
		mMealPlan = new BasicPlan();
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
			mMealPlan = new BasicPlan();
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
			mMealPlan = new BasicPlan();
		}
		
	}
	
	/**
	 * Sets the maintenence time of the reservation based on the room
	 */
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
	
	/**
	 * Returns a setup reservation for the reservation
	 * @return Reservation - the setup
	 */
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
	
	/**
	 * Returns a cleanup reservation for the reservation
	 * @return Reservation - the cleanup
	 */
	private Reservation MakeCleanupReservation () {
		Reservation Cleanup = new Reservation();
		Cleanup.mDay = mDay;
		Cleanup.mFunctionStartTime = mFunctionEndTime.Clone();
		Cleanup.mFunctionEndTime = mFunctionEndTime.Clone();
		int hTime = 0, mTime;
		mTime = mMaintenanceTime;
		if (mTime==60) { hTime = 1; mTime = 0; }
		if (!Cleanup.mFunctionEndTime.add(hTime, mTime)) {
			Cleanup.mFunctionEndTime = Time.getEndOfDay();
		}
		Cleanup.mRoom = mRoom;
		Cleanup.isCleanup = true;
		return Cleanup;
	}
	
	/**
	 * Changes the start time of the guest, altering other times
	 * @param newTime - the new time the guest would like to start on
	 */
	public void EditGuestStartTime(Time newTime) 
	{
		int[] timeDiff = mFunctionStartTime.difference(newTime);
		mSetup.mFunctionStartTime.add(timeDiff[0], timeDiff[1]);
		mSetup.mFunctionEndTime.add(timeDiff[0], timeDiff[1]);
		mFunctionStartTime = mSetup.mFunctionEndTime;
		mFullStartTime = mSetup.mFunctionStartTime;
	}
	
	/**
	 * Changes the end time of the guest, altering other times
	 * @param newTime - the new time the guest would like to end on
	 */
	public void EditGuestEndTime(Time newTime) {
		int[] timeDiff = mFunctionEndTime.difference(newTime);
		mCleanup.mFunctionStartTime.add(timeDiff[0], timeDiff[1]);
		mCleanup.mFunctionEndTime.add(timeDiff[0], timeDiff[1]);
		mFunctionEndTime = mCleanup.mFunctionStartTime;
		mFullEndTime = mCleanup.mFunctionEndTime;
	}
	
	/**
	 * Gets the reservation based on a time passed to it
	 * @param timeRes - the time to be found
	 * @return Reservation - whichever reservation is at that time
	 */
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

	/**
	 * @return the mMealPlan
	 */
	public MealPlan getmMealPlan() {
		return mMealPlan;
	}

	/**
	 * @param mMealPlan the mMealPlan to set
	 */
	public void setmMealPlan(MealPlan mMealPlan) {
		this.mMealPlan = mMealPlan;
	}

	/**
	 * @return the mDay
	 */
	public Day getmDay() {
		return mDay;
	}

	/**
	 * @param mDay the mDay to set
	 */
	public void setmDay(Day mDay) {
		this.mDay = mDay;
	}

	/**
	 * @return the mRoom
	 */
	public Room getmRoom() {
		return mRoom;
	}

	/**
	 * @param mRoom the mRoom to set
	 */
	public void setmRoom(Room mRoom) {
		this.mRoom = mRoom;
	}

	/**
	 * @return the mGuest
	 */
	public Guest getmGuest() {
		return mGuest;
	}

	/**
	 * @param mGuest the mGuest to set
	 */
	public void setmGuest(Guest mGuest) {
		this.mGuest = mGuest;
	}

	/**
	 * @return the mRoomTheme
	 */
	public String getmRoomTheme() {
		return mRoomTheme;
	}

	/**
	 * @param mRoomTheme the mRoomTheme to set
	 */
	public void setmRoomTheme(String mRoomTheme) {
		this.mRoomTheme = mRoomTheme;
	}

	/**
	 * @return the checkedIn
	 */
	public boolean isCheckedIn() {
		return checkedIn;
	}

	/**
	 * @param checkedIn the checkedIn to set
	 */
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	/**
	 * @return the checkedOut
	 */
	public boolean isCheckedOut() {
		return checkedOut;
	}

	/**
	 * @param checkedOut the checkedOut to set
	 */
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	/**
	 * @return the mMaintenanceTime
	 */
	public int getmMaintenanceTime() {
		return mMaintenanceTime;
	}

	/**
	 * @return the mFunctionStartTime
	 */
	public Time getmFunctionStartTime() {
		return mFunctionStartTime;
	}

	/**
	 * @return the mFunctionEndTime
	 */
	public Time getmFunctionEndTime() {
		return mFunctionEndTime;
	}

	/**
	 * @return the mFullStartTime
	 */
	public Time getmFullStartTime() {
		return mFullStartTime;
	}

	/**
	 * @return the mFullEndTime
	 */
	public Time getmFullEndTime() {
		return mFullEndTime;
	}

	/**
	 * @return the mSetup
	 */
	public Reservation getmSetup() {
		return mSetup;
	}

	/**
	 * @return the mGuestReservation
	 */
	public Reservation getmGuestReservation() {
		return mGuestReservation;
	}

	/**
	 * @return the mCleanup
	 */
	public Reservation getmCleanup() {
		return mCleanup;
	}

	/**
	 * @return the isSetup
	 */
	public boolean isSetup() {
		return isSetup;
	}

	/**
	 * @return the isCleanup
	 */
	public boolean isCleanup() {
		return isCleanup;
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

