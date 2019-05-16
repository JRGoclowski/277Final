package edu.csulb.cecs277.DJJJ;

public class Day implements Comparable {
	
	
	
	private int mMonthNumeral, mDayNumeral, mYearNumeral;
	private Weekday mWeekday;
	private Month mMonth;
	private String mStringForm;
	private static final int MONTHS = 12;
	protected static final int[] MONTHLY_DAY_COUNT = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final Month[] MONTH_ARRAY = Month.values();
	private static final Weekday[] DAY_ARRAY = Weekday.values();
	
	public static final Day TEST_DATE_FEB_13 = new Day(2, 13, 2019);//TODO Remember to delete this
	public static final Day TEST_DATE_OCT_2 = new Day(10, 2, 2019);
	
	/**
	 * General constructor for a day
	 * @param month - the month
	 * @param day - the day
	 * @param year - the year
	 */
	public Day(int month, int day, int year) {
		if (month > MONTHS	|| day > MONTHLY_DAY_COUNT[month-1]) {
			mMonthNumeral = 0;
			mDayNumeral = 0;
			mWeekday = null;
			mStringForm = "Invalid Date";
		}
		mMonthNumeral = month;
		mDayNumeral = day;
		mYearNumeral = year;
		mMonth = MONTH_ARRAY[mMonthNumeral-1];
		FindWeekday();
		mStringForm = mWeekday.toString() + " the " + mDayNumeral;
		if ((int)(mDayNumeral / 10) == 1) {
			mStringForm += "th";
		}
		else {
			switch (mDayNumeral%10) {
			case 1: mStringForm += "st"; break;
			case 2: mStringForm += "nd"; break;
			case 3: mStringForm += "rd"; break;
			default: mStringForm += "th"; break;
			}
		}
		mStringForm += " of " + mMonth.toString() + ", " + mYearNumeral;
	}
	
	/**
	 * Helper method to identify the weekday of a day
	 */
	private void FindWeekday() {
		int totalDayCount = 0;
		for (int i = 0; i < mMonthNumeral; i++) {
			totalDayCount += MONTHLY_DAY_COUNT[i];
		}
		totalDayCount += mDayNumeral;
		setmWeekday(DAY_ARRAY[totalDayCount%7]);
	}
	
	static public enum Month {
		JAN,
		FEB,
		MAR,
		APR,
		MAY,
		JUN,
		JUL,
		AUG,
		SEP,
		OCT,
		NOV,
		DEC
	}
	
	static public enum Weekday {
		MON,
		TUE,
		WED,
		THU,
		FRI,
		SAT,
		SUN
	}
	
	/**
	 * Returns if the DOB this is called on is 21 by the date given
	 * @param DayToCompare - the day to compare against
	 * @return boolean - if 21 by day
	 */
	public boolean DOBTwentyOneBy(Day DayToCompare) {
		if ((DayToCompare.getmYearNumeral() - this.getmYearNumeral()) > 21) {
			return true;
		}
		else if ((DayToCompare.getmYearNumeral() - this.getmYearNumeral()) == 21) {
			if (DayToCompare.getmMonthNumeral() > this.getmMonthNumeral()) {
				return true;
			}
			else if (DayToCompare.getmMonthNumeral() == this.getmMonthNumeral()) {
				if (DayToCompare.getmDayNumeral() >= this.getmDayNumeral()) {
					return true;
				}
			}
		}
		return false;
		
	}
		
	/**
	 * @return the mMonthNumeral
	 */
	public int getmMonthNumeral() {
		return mMonthNumeral;
	}

	/**
	 * @return the mYearNumeral
	 */
	public int getmYearNumeral() {
		return mYearNumeral;
	}

	/**
	 * @param mYearNumeral the mYearNumeral to set
	 */
	public void setmYearNumeral(int mYearNumeral) {
		this.mYearNumeral = mYearNumeral;
	}

	/**
	 * @param mMonthNumeral the mMonthNumeral to set
	 */
	public void setmMonthNumeral(int mMonthNumeral) {
		this.mMonthNumeral = mMonthNumeral;
	}

	/**
	 * @return the mDayNumeral
	 */
	public int getmDayNumeral() {
		return mDayNumeral;
	}

	/**
	 * @param mWeekday the mWeekday to set
	 */
	private void setmWeekday(Weekday mWeekday) {
		this.mWeekday = mWeekday;
	}

	/**
	 * @param mMonth the mMonth to set
	 */
	private void setmMonth(Month mMonth) {
		this.mMonth = mMonth;
	}

	/**
	 * @param mStringForm the mStringForm to set
	 */
	private void setmStringForm(String mStringForm) {
		this.mStringForm = mStringForm;
	}

	/**
	 * @param mDayNumeral the mDayNumeral to set
	 */
	public void setmDayNumeral(int mDayNumeral) {
		this.mDayNumeral = mDayNumeral;
	}

	/**
	 * @return the mWeekday
	 */
	public Weekday getmWeekday() {
		return mWeekday;
	}

	/**
	 * @return the mMonth
	 */
	public Month getmMonth() {
		return mMonth;
	}

	/**
	 * @return the mStringForm
	 */
	public String getmStringForm() {
		return mStringForm;
	}

	@Override
	public int compareTo(Object arg0) {
		Day compareDay = (Day) arg0;
		if (mMonthNumeral < compareDay.mMonthNumeral) {
			return -1;
		}
		else if (mMonthNumeral == compareDay.mMonthNumeral) {
			if (mDayNumeral < compareDay.mDayNumeral) {
				return -1;
			}
			else if (mDayNumeral == compareDay.mDayNumeral) {
				return 0;
			}
		}
		return 1;
		
	}
	
	
}
