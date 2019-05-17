package edu.csulb.cecs277.DJJJ;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
public class Time implements Comparable {
	
	//TODO delete these
	public static final Time[] ALL_TIMES = FillTimes();
	
	private static Time[] FillTimes() {
		Time[] timeArray = new Time[60];
		ArrayList<Time> mOpenTimes = new ArrayList<Time>();
		for (int i = 9; i < 24; i++) {
			for (int j = 0; j < 4; j++) {
				Time addTime = new Time(i, j*15);
				mOpenTimes.add(addTime);
			}
		}
		for (int i = 0; i < mOpenTimes.size(); i++) {
			timeArray[i] = mOpenTimes.get(i);
		}
		return timeArray;
	}
	
	public static Time GetTimeFromString(String pTime) {
		int i = 0;
		while (!pTime.toString().equals(ALL_TIMES[i].toString()) && i < 60) {
			i++;
		}
		return ALL_TIMES[i]; 
	}
	
	NumberFormat formatter = new DecimalFormat("#00"); 
	private int mHours;
	private int mMinutes;
	private final int MAX_HOURS = 23;
	private final int MAX_MINUTES = 59;
	private final int HOUR_MINUTES = 60;
	public static final Time BEGINNING_OF_DAY = new Time(9, 0);
	public static final Time END_OF_DAY = new Time(24,0);
	
	/**
	 * Default Constructor, initializes to beginning of day
	 */
	public Time() {
		mHours = 0;
		mMinutes = 0;
	}
	
	 /**
	 * Contructor based on military time
	 * @param hours the set value for mHours
	 * @param minutes the set value for mMinutes
	 */
	public Time(int hours, int minutes) {
		if (mHours > MAX_HOURS || mMinutes > MAX_MINUTES || minutes < 0 || hours < 0) {
			mHours = 0;
			mMinutes = 0;
		}
		else {
			mHours = hours;
			mMinutes = minutes;
		}
	}
	
	public Time Clone() {
		return new Time(mHours, mMinutes);
	}
	
	/**
	 * Subtracts from the time with the given parameters
	 * @param subHours - the hours to subtract
	 * @param subMinutes - the minutes to subtract
	 * @return boolean - whether or not the subtraction was valid and performed
	 */
	public boolean sub(int subHours, int subMinutes) {
		if (subHours > MAX_HOURS) { return false; }
		if (subMinutes > MAX_MINUTES) { return false; }
		if (subHours > mHours) { return false; }
		if (subMinutes > mMinutes) {
			if (subHours > mHours -1) {
				return false;
			}
			mHours -= (subHours + 1);
			mMinutes = (HOUR_MINUTES + (mMinutes - subMinutes));
			return true;
		}
		else {
			mHours -= subHours;
			mMinutes -= subMinutes;
			return true;
		}
		
	}
	
	/**
	 * Adds from the time with the given parameters
	 * @param addHours - the hours to add
	 * @param addMinutes - the minutes to add
	 * @return boolean - whether or not the subtraction was valid and performed
	 */
	public boolean add(int addHours, int addMinutes) {
		if (addHours > MAX_HOURS) { return false; }
		if (addMinutes > MAX_MINUTES) { return false; }
		if (addHours + mHours >= MAX_HOURS) { 
			if ((addHours + mHours == MAX_HOURS) && (mMinutes + addMinutes <= MAX_MINUTES)) {
				mHours += addHours;
				mMinutes += addMinutes;
				return true;
			}
			return false; 
		}
		mHours += addHours;
		if (mMinutes + addMinutes > MAX_MINUTES) {
			mHours++;
			mMinutes = ((mMinutes + addMinutes) - HOUR_MINUTES);
			return true;
		}
		if (mMinutes + addMinutes < 0) {
			mHours--;
			mMinutes = ((mMinutes + addMinutes) + HOUR_MINUTES);
			return true;
		}
		mMinutes += addMinutes;
		return true;
	}
	
	/**
	 * Returns a 2 integer array with the time difference between two times
	 * @param timeArg - the time to compare against
	 * @return difArray - an array containing the difference in hours and minutes
	 */
	public int[] difference(Time timeArg) {
		int[] difArray = new int[2];
		difArray[0] = -(mHours - timeArg.mHours);
		difArray[1] = -(mMinutes - timeArg.mMinutes);
		return difArray;
	}
	
	/**
	 * Adds fifteen minutes to the time
	 * @return boolean - if the time was added properly
	 */
	public boolean IncFifteen() {
		return (this.add(0, 15));
	}
	
	/**
	 * Adds one hour to the time
	 * @return boolean - if the time was added properly
	 */
	public boolean IncHours() {
		return(this.add(1, 0));
	}
	
	/**
	 * Compares a time against the arguement
	 * @param pTime - the time to be compared to
	 * @return boolean - if the time is before the arguement
	 */
	public boolean isBefore(Time pTime) {
		Time localTime = pTime.Clone();
		if (this.mHours > localTime.mHours) { return false; }
		if (this.mHours == localTime.mHours && mMinutes >= localTime.mMinutes) { return false; }
		return true;
		
	}
	
	/**
	 * Returns the ALL_TIMES
	 * @return Time[] - the ALL_TIMES
	 */
	public static Time[] getAllTimes() {
		return ALL_TIMES;
	}

	/**
	 * returns the beginning of the day
	 * @return Time - the beginning of the day
	 */
	public static Time getBeginningOfDay() {
		return BEGINNING_OF_DAY.Clone();
	}

	/**
	 * returns the end of the day
	 * @return Time - the end of the day
	 */
	public static Time getEndOfDay() {
		return END_OF_DAY.Clone();
	}

	/**
	 * Compares if two times are equal
	 * @param time - the time to be compared to
	 * @return boolean - if the times are equal
	 */
	public boolean isEqualTo(Time time) {
		return ((mHours == time.mHours ) && (mMinutes == time.mMinutes));
	}
	
	/**
	 * Checks if a time is within buisness hours
	 * @return boolean - if within buisness hours
	 */
	public boolean isBusinessHours () {
		return ((!isBefore(BEGINNING_OF_DAY)) && (isBefore(END_OF_DAY)));
	}
	
	/**
	 * CompareTo implementation
	 */
	@Override
	public int compareTo(Object arg0) {
		Time comparison = (Time) arg0;
		if (isBefore(comparison)) {
			return -1;
		}
		else if (isEqualTo(comparison)) {
			return 0;
		}
		else {
			return 1;
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
	
	
	/**
	 * Converts the time to the general AM/PM time format
	 * @return String - the string form of a time.
	 */
	public String toString() {
		String minutes = formatter.format(mMinutes);
		String timeString = "";
		if (this.isEqualTo(Time.getEndOfDay())) {
			return "12:00 AM";
		}
		if (mHours >= 12) {
			if (mHours == 12) {
				timeString += mHours + ":" + minutes;
			}
			else {
				timeString += (mHours - 12) + ":" + minutes;
			}
			return (timeString + " PM");
		}
		else {
			if (mHours == 0) {
				timeString += 12 + ":" + minutes;
			}
			else {
				timeString += mHours + ":" + minutes;
			}
			return (timeString + " AM");
		}
	}

	
}
