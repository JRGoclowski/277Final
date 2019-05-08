package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;
import java.util.Collections;

public class Date implements Comparable {
	
	public static void main(String args[]) 
	{
		Date testDate1 = new Date(Day.TEST_DATE_FEB_13);
		Date testDate2 = new Date(Day.TEST_DATE_OCT_2);
		Guest DIO = Guest.DIO_BRANDO;
		Guest JOESEPH = Guest.JOESEPH_JOESTAR;
		Guest JOTARO = Guest.JOTARO_KUJO;
		SmallPartyRoom testRoom = new SmallPartyRoom(false);
		Reservation dioRes = new Reservation(Time.ALL_TIMES[2], Time.ALL_TIMES[6], testDate1.mDay, testRoom, DIO);
		Reservation joeRes = new Reservation(Time.ALL_TIMES[25], Time.ALL_TIMES[32], testDate1.mDay, testRoom, JOESEPH);
		Reservation jotaroRes = new Reservation(Time.ALL_TIMES[10], Time.ALL_TIMES[21], testDate1.mDay, testRoom, JOTARO);
		DebugTimeFramePrint(dioRes);
		DebugResPrint(testDate1, dioRes);
		DebugTimeFramePrint(joeRes);
		DebugResPrint(testDate1, joeRes);
		DebugTimeFramePrint(jotaroRes);
		DebugResPrint(testDate1, jotaroRes);
		DebugRelPrint(testDate1, dioRes);
		//TODO test when the time ends right as another starts
	}
	
	private static void DebugTimeFramePrint(Reservation pReservation) {
		System.out.println(pReservation.getmGuest().getmName() + 
				"\n Guest Time : " + DebugGuestTimeString(pReservation) +
				"\n Full Time : " + DebugFullTimeString(pReservation));
	}
	
	private static String DebugGuestTimeString (Reservation pReservation) {
		String guestTime = pReservation.getmFunctionStartTime().toString() + " - " + pReservation.getmFunctionEndTime().toString();
		return guestTime;
	}
	
	private static String DebugFullTimeString (Reservation pReservation) {
		String fullTime = pReservation.getmFullStartTime().toString() + " - " + pReservation.getmFullEndTime().toString();
		return fullTime;
	}
	
	private static void DebugResPrint(Date pDate, Reservation pRes){
		System.out.println(pDate.addReservation(pRes));
		System.out.println(pDate.GetAvailableBlocks().toString());
	}
	
	private static void DebugRelPrint(Date pDate, Reservation pRes){
		System.out.println(pDate.removeReservation(pRes));
		System.out.println(pDate.GetAvailableBlocks().toString());
	}
	
	private ArrayList<Reservation> mReservations = new ArrayList<Reservation>();
	private Day mDay;
	private ArrayList<Time> mOpenTimes = new ArrayList<Time>();
	
	
	public Date(Day day) {
		mDay = day;
		FillOpenTimes();
	}
	
	//TODO Check if there are issues with room availability
	public boolean isValidReservation(Reservation reservation) {
		Time testTime = Time.getBeginningOfDay();
		boolean testFinal = reservation.getmFunctionStartTime().isBefore(Time.getBeginningOfDay());
		boolean testConstructed = reservation.getmFunctionStartTime().isBefore(testTime);
		boolean valid = true, cond1 = !reservation.getmFunctionStartTime().isBefore(Time.getBeginningOfDay()) , cond2 = reservation.getmFunctionStartTime().isBefore(Time.getEndOfDay());
		valid = (reservation.getmFunctionStartTime().isBusinessHours() 
				&&  reservation.getmFunctionEndTime().isBusinessHours());
		valid = ((isOpen(reservation)) && valid);
		return valid;
	}
	
	public boolean addReservation(Reservation reservation) {
		if (!(isValidReservation(reservation))) {
			return false;
		}
		mReservations.add(reservation);
		this.ReserveTimes(reservation);
		return true;		
	}
	
	public boolean removeReservation(Reservation pReservation) {
		if (!mReservations.contains(pReservation)) {
			return false;
		}
		mReservations.remove(mReservations.indexOf(pReservation));
		this.ReleaseTimes(pReservation);
		return true;
	}
	
	public Reservation GetReservation(Guest guest) {
		for (Reservation currRes : mReservations) {
			if (currRes.getmGuest().equals(guest)){
				return currRes;
			}
		}
		return null;
	}
	
	public Reservation GetReservation(String guestName) {
		for (Reservation currRes : mReservations) {
			if (currRes.getmGuest().getmName().equals(guestName)){
				return currRes;
			}
		}
		return null;
	}
	
	public Reservation GetReservation(Time pTime) {
		for (Reservation currRes : mReservations) {
			if ((!pTime.isBefore(currRes.getmFullStartTime())) && pTime.isBefore(currRes.getmFullEndTime())){
				if (pTime.isBefore(currRes.getmFunctionStartTime())) {
					return currRes.getmSetup();
				}
				else if (pTime.isBefore(currRes.getmFunctionEndTime())) {
					return currRes;
				}
				else {
					return currRes.getmCleanup();
				}
			}
		}
		return null;
	}
	
	//tested
	private void FillOpenTimes() {
		for (int i = 9; i < 24; i++) {
			for (int j = 0; j < 4; j++) {
				Time addTime = new Time(i, j*15);
				mOpenTimes.add(addTime);
			}
		}
			
	}
	
	//MUST ONLY BE CALLED AFTER VERIFIED WITH VALID REGISTRATION
	private boolean ReserveTimes(Reservation pReservation) {
		Time start, end;
		int r = 0;
		if (pReservation.getmFullStartTime().isBefore(Time.getBeginningOfDay())) {
			if (!pReservation.getmFunctionStartTime().isBefore(Time.getBeginningOfDay())) {
				start = pReservation.getmFunctionStartTime();
			}
			else {
				return false;
			}
		}
		else {
			start = pReservation.getmFullStartTime();
		}
		if (!pReservation.getmFullEndTime().isBefore(Time.getEndOfDay())) {
			if (pReservation.getmFunctionEndTime().isBefore(Time.getEndOfDay())) {
				end = pReservation.getmFunctionEndTime();
			}
			else {
				return false;
			}
		}
		else {
			end = pReservation.getmFullEndTime();
		}
		while (!(mOpenTimes.get(r).isEqualTo(start))) {
			r++;
		}
		while (mOpenTimes.get(r).isBefore(end)) {
			mOpenTimes.remove(r);
			
		}
		return true;
	}
	
	private void ReserveTimes(Time start, Time end) {
		int r = 0; 
		while (!(mOpenTimes.get(r).isEqualTo(start))) {
			r++;
		}
		while (mOpenTimes.get(r).isBefore(end)) {
			mOpenTimes.remove(r);
			
		}
	}
	
	private boolean ReleaseTimes(Reservation pReservation) {
		Time start, end;
		int r = 0;
		if (pReservation.getmFullStartTime().isBefore(Time.getBeginningOfDay())) {
			if (!pReservation.getmFunctionStartTime().isBefore(Time.getBeginningOfDay())) {
				start = pReservation.getmFunctionStartTime();
			}
			else {
				return false;
			}
		}
		else {
			start = pReservation.getmFullStartTime();
		}
		if (!pReservation.getmFullEndTime().isBefore(Time.getEndOfDay())) {
			if (pReservation.getmFunctionEndTime().isBefore(Time.getEndOfDay())) {
				end = pReservation.getmFunctionEndTime();
			}
			else {
				return false;
			}
		}
		else {
			end = pReservation.getmFullEndTime();
		}
		while ((mOpenTimes.get(r).isBefore(start))) {
			r++;
		}
		Time timeWalker = mOpenTimes.get(--r);
		Time lastAddition = end;
		lastAddition.sub(0, 15);
		while (!timeWalker.isEqualTo(lastAddition)) {
			mOpenTimes.add(timeWalker.Clone());
			if (!timeWalker.isEqualTo(new Time())) {
				timeWalker.add(0, 15);
			}
		}
		Collections.sort(mOpenTimes);
		return true;
	}
	
	
	private void ReleaseTimes(Time start, Time end) {
		int r = 0;
		while ((mOpenTimes.get(r).isBefore(start))) {
			r++;
		}
		Time timeWalker = mOpenTimes.get(--r);
		Time lastAddition = end;
		lastAddition.sub(0, 15);
		while (!timeWalker.isEqualTo(lastAddition)) {
			mOpenTimes.add(timeWalker.Clone());
			if (!timeWalker.isEqualTo(new Time())) {
				timeWalker.add(0, 15);
			}
		}
		Collections.sort(mOpenTimes);
	}
	
	//tested
	private boolean isOpen(Reservation pReservation) {
		Time start, end;
		int r = 0;
		boolean open = true;
		if (pReservation.getmFullStartTime().isBefore(Time.getBeginningOfDay())) {
			if (!pReservation.getmFunctionStartTime().isBefore(Time.getBeginningOfDay())) {
				start = pReservation.getmFunctionStartTime().Clone();
			}
			else {
				return false;
			}
		}
		else {
			start = pReservation.getmFullStartTime().Clone();
		}
		if (!pReservation.getmFullEndTime().isBefore(Time.getEndOfDay())) {
			if (pReservation.getmFunctionEndTime().isBefore(Time.getEndOfDay())) {
				end = pReservation.getmFunctionEndTime().Clone();
			}
			else {
				return false;
			}
		}
		else {
			end = pReservation.getmFullEndTime().Clone();
		}
		while (!(mOpenTimes.get(r).isEqualTo(start))) {
			r++;
			if (r == mOpenTimes.size()) {
				return false;
			}
		}
		Time timeWalker = mOpenTimes.get(r).Clone();
		Time lastTime = end.Clone();
		lastTime.sub(0, 15);
		while (!timeWalker.isEqualTo(lastTime) && open) {
			if (!timeWalker.isEqualTo(mOpenTimes.get(r))) {
				open = false;
			}
			timeWalker.add(0, 15);
			r++;
		}
		return open;
	}
	
	public ArrayList<String> GetAvailableBlocks() {
		String startTime, endTime;
		Time timeWalker = Time.getBeginningOfDay();
		ArrayList <String> timeStrings = new ArrayList<String>();
		int i = 0;
		while (timeWalker.isBusinessHours()){
			while (!timeWalker.isEqualTo(mOpenTimes.get(i)) && timeWalker.isBusinessHours()) {
				timeWalker.IncFifteen();
			}
			startTime = timeWalker.toString();
			while (
					timeWalker.isEqualTo(mOpenTimes.get(i)) 
					&& timeWalker.isBusinessHours() 
					&& i < mOpenTimes.size()) {
				timeWalker.IncFifteen();
				i++;				
				if (i == mOpenTimes.size()) {
					timeWalker.IncFifteen();
					if (timeWalker.toString().equals("11:45 PM")) {
						endTime = "12:00 AM";
					}
					else {
						endTime = timeWalker.toString();
					}
					
					timeStrings.add((startTime + " - " + endTime));
					return timeStrings;
				}
			}
			endTime = timeWalker.toString();
			timeStrings.add((startTime + " - " + endTime));
		}
		return timeStrings;		
	}
	
	public String toString() {
		return (mDay.toString() + ", has " + mReservations.size() + " reservations");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
