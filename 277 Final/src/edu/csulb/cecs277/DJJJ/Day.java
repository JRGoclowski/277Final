package edu.csulb.cecs277.DJJJ;

public class Day {
	
	private int mMonthNumeral, mDayNumeral;
	private Weekday mWeekday;
	private Month mMonth;
	private String mStringForm;
	private static final int[] MONTHLY_DAY_COUNT = {31, 28, 31, 30, 31, 30,
													31, 31, 30, 31, 30, 31};
	private static final Month[] MONTH_ARRAY = Month.values();
	private static final Weekday[] DAY_ARRAY = Weekday.values();
	
	public Day(int month, int day) {
		mMonthNumeral = month;
		mDayNumeral = day;
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
		mStringForm += " of " + mMonth.toString();
	}
	
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
	 * @return the mMonthNumeral
	 */
	public int getmMonthNumeral() {
		return mMonthNumeral;
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
	
	
}
