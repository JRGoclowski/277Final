package edu.csulb.cecs277.DJJJ;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Time {
	
	public static void main(String args[]) {
		Time noon = new Time(12, 0);
		Time afternoon = new Time(15, 23);
		Time morning = new Time(8, 20);
		Time dayBegin = new Time();
		Time dayEnd = new Time();
		
		Time test = new Time(9, 14);
		Time test1 = new Time(19, 0);
		Time test2 = new Time (7, 0);
		Time test3 = new Time (11, 45);
		
		noon.add(noon.difference(test)[0], noon.difference(test)[1]);
		System.out.println(noon.toString());
		
		Time after = new Time (23, 50);
		Time before = new Time(8,55);
		Time atClose = new Time(23,46);
		Time atOpen = new Time(9,0);
		Time justBeforeClose = new Time(11,48);
		
		after.isBefore(noon);
		before.isBefore(noon);
		after.isBusinessHours();
		before.isBusinessHours();
		atClose.isBusinessHours();
		atOpen.isBusinessHours();
		justBeforeClose.isBusinessHours();
		/*
		System.out.print(noon.difference(test1)[0] + ",");
		System.out.println(noon.difference(test1)[1]);
		System.out.print(noon.difference(test2)[0] + ",");
		System.out.println(noon.difference(test2)[1]);
		System.out.print(noon.difference(test3)[0] + ",");
		System.out.println(noon.difference(test3)[1]);
		System.out.print(afternoon.difference(test1)[0] + ",");
		System.out.println(afternoon.difference(test1)[1]);
		System.out.print(afternoon.difference(test2)[0] + ",");
		System.out.println(afternoon.difference(test2)[1]);
		System.out.print(afternoon.difference(test3)[0] + ",");
		System.out.println(afternoon.difference(test3)[1]);
		System.out.print(morning.difference(test1)[0] + ",");
		System.out.println(morning.difference(test1)[1]);
		System.out.print(morning.difference(test2)[0] + ",");
		System.out.println(morning.difference(test2)[1]);
		System.out.print(morning.difference(test3)[0] + ",");
		System.out.println(morning.difference(test3)[1]);
		System.out.print(dayBegin.difference(test1)[0] + ",");
		System.out.println(dayBegin.difference(test1)[1]);
		System.out.print(dayBegin.difference(test2)[0] + ",");
		System.out.println(dayBegin.difference(test2)[1]);
		System.out.print(dayBegin.difference(test3)[0] + ",");
		System.out.println(dayBegin.difference(test3)[1]);
		System.out.print(dayEnd.difference(test1)[0] + ",");
		System.out.println(dayEnd.difference(test1)[1]);
		System.out.print(dayEnd.difference(test2)[0] + ",");
		System.out.println(dayEnd.difference(test2)[1]);
		System.out.print(dayEnd.difference(test3)[0] + ",");
		System.out.println(dayEnd.difference(test3)[1]);
		*/
	}
	
	NumberFormat formatter = new DecimalFormat("#00"); 
	private int mHours;
	private int mMinutes;
	private final int MAX_HOURS = 23;
	private final int MAX_MINUTES = 59;
	private final int HOUR_MINUTES = 60;
	private static final Time BEGINNING_OF_DAY = new Time(9, 0);
	private static final Time END_OF_DAY = new Time(23,59);
	
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
	
	public boolean isBefore(Time time) {
		if (mHours > time.mHours) { return false; }
		if (mHours == time.mHours && mMinutes >= time.mMinutes) { return false; }
		return true;
		
	}
	
	private boolean isBusinessHours () {
		return ((!isBefore(BEGINNING_OF_DAY)) && (isBefore(END_OF_DAY)));
		/*int[] diffBOD = timeCheck.difference(BEGINNING_OF_DAY);
		int[] diffEOD = timeCheck.difference(END_OF_DAY);
		if (diffBOD[0] < 0 || (diffBOD[0] == 0 && diffBOD[1] < 0)) {
			return false;
		}
		if (diffEOD[0] > 0 || (diffEOD[0] == 0 && diffEOD[1] > 0)) {
			return false;
		}
		return true;*/
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
