package edu.csulb.cecs277.DJJJ;

public class Time {
	
	private int mHours;
	private int mMinutes;
	private final int MAX_HOURS = 23;
	private final int MAX_MINUTES = 23;
	
	/**
	 * Contructor based on military time
	 * @param hours the set value for mHours
	 * @param minutes the set value for mMinutes
	 */
	public Time() {
		mHours = 0;
		mMinutes = 0;
	}
	
	public Time(int hours, int minutes) {
		if (mHours > MAX_HOURS || mMinutes > MAX_MINUTES) {
			mHours = 0;
			mMinutes = 0;
		}
		else {
			mHours = hours;
			mMinutes = minutes;
		}
	}

	/**
	 * @return the mHours
	 */
	public int getmHours() {
		return mHours;
	}

	/**
	 * @param mHours the mHours to set
	 */
	public void setmHours(int mHours) {
		this.mHours = mHours;
	}

	/**
	 * @return the mMinutes
	 */
	public int getmMinutes() {
		return mMinutes;
	}

	/**
	 * @param mMinutes the mMinutes to set
	 */
	public void setmMinutes(int mMinutes) {
		this.mMinutes = mMinutes;
	}

	public String toString() {
		String timeString = "";
		if (mHours >= 12) {
			if (mHours == 12) {
				timeString += mHours + ":" + mMinutes;
			}
			else {
				timeString += (mHours - 12) + ":" + mMinutes;
			}
			return (timeString + " PM");
		}
		else {
			if (mHours == 0) {
				timeString += 12 + ":" + mMinutes;
			}
			else {
				timeString += mHours + ":" + mMinutes;
			}
			return (timeString + " AM");
		}
	}
}
