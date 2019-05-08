package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;
import java.util.Collections;

public class Date implements Comparable {
	
	public static void main(String args[]) 
	{
		Date testDate = new Date(Day.TEST_DATE_FEB_13);
		Guest DIO = Guest.DIO_BRANDO;
		Guest JOESEPH = Guest.JOESEPH_JOESTAR;
		Guest JOTARO = Guest.JOTARO_KUJO;
		SmallPartyRoom testRoom = new SmallPartyRoom();
		Reservation dioRes = new Reservation(Time.ALL_TIMES[2], Time.ALL_TIMES[6], testDate.mDay, testRoom, DIO);
		Reservation joeRes = new Reservation(Time.ALL_TIMES[6], Time.ALL_TIMES[14], testDate.mDay, testRoom, JOESEPH);
		Reservation JotaroRes = new Reservation();
		System.out.println(testDate.mOpenTimes.toString());
		testDate.ReserveTimes(new Time (10, 0), new Time(22, 45));
		//testDate.ReserveTimes(new Time (17, 30), new Time(19, 30));
		//System.out.println(testDate.isOpen(testReservation));
		System.out.println(testDate.isValidReservation(testReservation));
		ArrayList<String> times = testDate.GetAvailableBlocks();
		System.out.println(times.toString());
		//System.out.println(test.mOpenTimes.toString());
		//TODO test when the time ends right as another starts
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
		boolean valid = true;
		valid = (reservation.getmFunctionStartTime().isBusinessHours() &&  reservation.getmFunctionEndTime().isBusinessHours());
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
		if (pReservation.getmFullStartTime().isBefore(Time.BEGINNING_OF_DAY)) {
			if (!pReservation.getmFunctionStartTime().isBefore(Time.BEGINNING_OF_DAY)) {
				start = pReservation.getmFunctionStartTime();
			}
			else {
				return false;
			}
		}
		else {
			start = pReservation.getmFullStartTime();
		}
		if (!pReservation.getmFullEndTime().isBefore(Time.END_OF_DAY)) {
			if (pReservation.getmFunctionEndTime().isBefore(Time.END_OF_DAY)) {
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
		if (pReservation.getmFullStartTime().isBefore(Time.BEGINNING_OF_DAY)) {
			if (!pReservation.getmFunctionStartTime().isBefore(Time.BEGINNING_OF_DAY)) {
				start = pReservation.getmFunctionStartTime();
			}
			else {
				return false;
			}
		}
		else {
			start = pReservation.getmFullStartTime();
		}
		if (!pReservation.getmFullEndTime().isBefore(Time.END_OF_DAY)) {
			if (pReservation.getmFunctionEndTime().isBefore(Time.END_OF_DAY)) {
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
		if (pReservation.getmFullStartTime().isBefore(Time.BEGINNING_OF_DAY)) {
			if (!pReservation.getmFunctionStartTime().isBefore(Time.BEGINNING_OF_DAY)) {
				start = pReservation.getmFunctionStartTime();
			}
			else {
				return false;
			}
		}
		else {
			start = pReservation.getmFullStartTime();
		}
		if (!pReservation.getmFullEndTime().isBefore(Time.END_OF_DAY)) {
			if (pReservation.getmFunctionEndTime().isBefore(Time.END_OF_DAY)) {
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
		Time timeWalker = Time.BEGINNING_OF_DAY;
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
					endTime = timeWalker.toString();
					timeStrings.add((startTime + " - " + endTime));
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
