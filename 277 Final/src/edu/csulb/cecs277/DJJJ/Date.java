package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;
import java.util.Collections;

public class Date  {
	
	private ArrayList<Reservation> mReservations = new ArrayList<Reservation>();
	private Day mDay;
	private ArrayList<Time> mOpenTimes = new ArrayList<Time>();
	
	/**
	 * Constructor for Date, takes in a day parameter
	 * @param day - the day that the date is on
	 */
	public Date(Day day) {
		mDay = day;
		FillOpenTimes();
	}
	
	/**
	 * Checks if a reservation is a valid reservation
	 * @param reservation - the reservation to be checked
	 * @return boolean - whether the reservation is valid
	 */
	public boolean isValidReservation(Reservation reservation) {
		boolean valid = true;
		valid = (reservation.getmFunctionStartTime().isBusinessHours() 
				&&  reservation.getmFunctionEndTime().isBusinessHours());
		valid = ((isOpen(reservation)) && valid);
		return valid;
	}
	
	/**
	 * Allows for a reservations' start time to be editted
	 * @param pReservation - the reservation to be editted
	 * @param pStart - the new time that the guest would like to start at
	 * @return boolean - if the time was adjusted
	 */
	public boolean editReservationStart(Reservation pReservation, Time pStart) {
		int[] diffTime = pReservation.getmFunctionStartTime().difference(pStart);
		Time newStart = pReservation.getmFullStartTime().Clone();
		newStart.add(diffTime[0], diffTime[1]);
		if (isOpen (newStart, pReservation.getmFullStartTime())) {
			this.ReserveTimes(newStart, pReservation.getmFullStartTime());
			pReservation.EditGuestStartTime(pStart);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Allows for a reservations' end time to be editted
	 * @param pReservation - the reservation to be editted
	 * @param pStart - the new time that the guest would like to end at
	 * @return boolean - if the time was adjusted
	 */
	public boolean editReservationEnd(Reservation pReservation, Time pEnd) {
		int[] diffTime = pReservation.getmFunctionEndTime().difference(pEnd);
		Time newEnd = pReservation.getmFullEndTime().Clone();
		newEnd.add(diffTime[0], diffTime[1]);
		if (isOpen (pReservation.getmFullEndTime(), newEnd)) {
			this.ReserveTimes(pReservation.getmFullEndTime(), newEnd);
			pReservation.EditGuestEndTime(pEnd);
			return true;
		}
		return false;
	}
	
	/**
	 * If a reservation is valid, adds it to the list of reservations for the date
	 * @param reservation - the reservation to be added	 
	 * @return boolean - whether the reservation was added.
	 */
	public boolean addReservation(Reservation reservation) {
		if (!(isValidReservation(reservation))) {
			return false;
		}
		mReservations.add(reservation);
		this.ReserveTimes(reservation);
		return true;		
	}
	
	/**
	 * Removes a reservation from the day
	 * @param pReservation - the reservation to be removed
	 * @return boolean - if the reservation was found and removed
	 */
	public boolean removeReservation(Reservation pReservation) {
		if (!mReservations.contains(pReservation)) {
			return false;
		}
		mReservations.remove(mReservations.indexOf(pReservation));
		this.ReleaseTimes(pReservation);
		return true;
	}
	
	/**
	 * returns a reservation searching by guest
	 * @param guest - the guest whos reservation is needed
	 * @return Reservation - the reservation of the guest
	 */
	public Reservation GetReservation(Guest guest) {
		for (Reservation currRes : mReservations) {
			if (currRes.getmGuest().equals(guest)){
				return currRes;
			}
		}
		return null;
	}
	
	/**
	 * returns a reservation searching by guest
	 * @param guestName - the name of the guest whose reservation is needed
	 * @return Reservation - the reservation of the guest
	 */
	public Reservation GetReservation(String guestName) {
		for (Reservation currRes : mReservations) {
			if (currRes.getmGuest().getmName().equals(guestName)){
				return currRes;
			}
		}
		return null;
	}
	
	/**
	 * returns a reservation searching by time
	 * @param pTime - the Time that the reservation is 
	 * @return Reservation - the reservation at that time.
	 */
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
	
	/**
	 * Fills the mOpenTimes array list with all time frames of the day
	 */
	private void FillOpenTimes() {
		for (int i = 9; i < 24; i++) {
			for (int j = 0; j < 4; j++) {
				Time addTime = new Time(i, j*15);
				mOpenTimes.add(addTime);
			}
		}
			
	}
	
	/**
	 * Removes times from mOpenTimes based on when a reservation is
	 * @param pReservation - the reservation for which time must be reserved
	 * @return boolean - whether the times were reserved properly
	 */
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
		while (!mOpenTimes.get(r).isEqualTo(end)) {
			mOpenTimes.remove(r);
			
		}
		return true;
	}
	
	/**
	 * Removes times from mOpenTimes based on times passed
	 * @param start - the start time to start removing from
	 * @param end - the end time to stop removing 
	 */
	private void ReserveTimes(Time start, Time end) {
		int r = 0; 
		while (!(mOpenTimes.get(r).isEqualTo(start))) {
			r++;
		}
		while (mOpenTimes.get(r).isBefore(end)) {
			mOpenTimes.remove(r);
			
		}
	}
	
	/**
	 * Adds times back to the mOpenTimes based on a reservation removed
	 * @param pReservation - the reservation that is removed
	 * @return boolean - whether the times were added back
	 */
	private boolean ReleaseTimes(Reservation pReservation) {
		Time start, end, timeWalker;
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
		timeWalker = start.Clone();
		Time lastAddition = end.Clone();
		while (!timeWalker.isEqualTo(lastAddition)) {
			mOpenTimes.add(timeWalker.Clone());
			timeWalker.add(0, 15);
		}
		Collections.sort(mOpenTimes);
		return true;
	}
	
	/**
	 * Adds times to mOpenTimes based on times passed
	 * @param start - the start time to start adding
	 * @param end - the end time to stop adding 
	 */
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
	
	/**
	 * checks if times are open for use on the date
	 * @param pStart - the start time to begin checking
	 * @param pEnd - the end time to stop checking
	 * @return boolean - whether the time is open
	 */
	public boolean isOpen(Time pStart, Time pEnd) {
		Time start = pStart.Clone(), end = pEnd.Clone();
		int r = 0;
		boolean open = true;
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
	
	/**
	 * checks if times are open for a reservation
	 * @param pReservation - the reservation to check if available
	 * @return boolean - whether the time is open
	 */
	public boolean isOpen(Reservation pReservation) {
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
	
	/**
	 * Returns an arraylist of strings for available, bookable times
	 * @return ArrayList<String> - the collection of timeframe strings
	 */
	public ArrayList<String> GetAvailableBlocks() {
		Time startTime, endTime, timeWalker = Time.getBeginningOfDay();
		ArrayList <String> timeStrings = new ArrayList<String>();
		int i = 0;
		while (timeWalker.isBusinessHours()){
			while (!timeWalker.isEqualTo(mOpenTimes.get(i)) && timeWalker.isBusinessHours()) {
				timeWalker.IncFifteen();
			}
			startTime = timeWalker.Clone();
			while (
					timeWalker.isEqualTo(mOpenTimes.get(i)) 
					&& timeWalker.isBusinessHours() 
					&& i < mOpenTimes.size()) {
				timeWalker.IncFifteen();
				i++;				
				if (i == mOpenTimes.size()) {
					if(!timeWalker.IncFifteen()) {
						endTime = Time.getEndOfDay();
					}
					else {
						endTime = timeWalker.Clone();
					}
					int[] timeDiff= startTime.difference(endTime);
					if ((timeDiff[0] < 1  && timeDiff[1] < 45)
							|| (timeDiff[0] == 1 && timeDiff[1] < 0)) {
						return timeStrings;
					}
					timeStrings.add((startTime + " - " + endTime));
					return timeStrings;
				}
			}
			endTime = timeWalker.Clone();
			int[] timeDiff= startTime.difference(endTime);
			if (!((timeDiff[0] < 1  && timeDiff[1] < 45)
					|| (timeDiff[0] == 1 && timeDiff[1] < 0))) {
				timeStrings.add((startTime + " - " + endTime));
			}
		}
		return timeStrings;		
	}
	
	/**
	 * returns the mReservations
	 * @return ArrayList<Reservation> - the mReservations
	 */
	public ArrayList<Reservation> getmReservations() {
		return mReservations;
	}
	
	/**
	 * returns the mDay
	 * @return Day - the mDay
	 */
	public Day getmDay() {
		return mDay;
	}
	
	/**
	 * Returns the mOpenTimes
	 * @return ArrayList<Time> - the mOpenTimes
	 */
	public ArrayList<Time> getmOpenTimes() {
		return mOpenTimes;
	}

	/**
	 * Returns a string format of the date
	 */
	public String toString() {
		return (mDay.getmStringForm() + ", has " + mReservations.size() + " reservations");
	}

}
