package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public class RoomList 
{
	//implement the singleton 
	private static RoomList mRoomList;
	
	private static int mConfirmationCount = 1000;
	
	//ArrayList of rooms for waitlist, when we make the rooms, we add it to this array list of rooms 
	private ArrayList<Room> mSmallRooms = new ArrayList<Room>();
	private ArrayList<Room> mMediumRooms = new ArrayList<Room>();
	private ArrayList<Room> mKaraokeRooms = new ArrayList<Room>();
	private ArrayList<Room> mBilliardRooms = new ArrayList<Room>();
	private AquaWorld mAquaWorld = new AquaWorld();
	
//	Each room has a waitlist?
	private Waitlist mWaitlist = Waitlist.getmWaitlist();
//	private Waitlist mMediumList = new Waitlist();
//	private Waitlist mKaraokeList = new Waitlist();
//	private Waitlist mBilliardsList = new Waitlist();
//	private Waitlist mAquaWorldList = new Waitlist();
	
	//SingleTon 
	private RoomList () 
	{
		mRoomList = this;
	}
	
	/**
	 * Takes these parameters and attempts to create a 
	 * @param pGuestStart
	 * @param pGuestEnd
	 * @param pGuest
	 * @param desiredRoom
	 * @param pDay
	 * @return
	 */
	
	public Reservation TakeReservation(Time pGuestStart, Time pGuestEnd, Guest pGuest, String desiredRoom, Day pDay) {
		Reservation possibleRes;
		switch (desiredRoom) {
			case "Small":
				possibleRes = new Reservation(pGuestStart, pGuestEnd, pDay, new SmallPartyRoom(), pGuest);
				break;
			case "Medium":
				possibleRes = new Reservation(pGuestStart, pGuestEnd, pDay, new MediumPartyRoom(), pGuest);
				break;
			case "Karaoke":
				possibleRes = new Reservation(pGuestStart, pGuestEnd, pDay, new KaraokeLounge(),pGuest);
				break;
			case "Billiards":
				possibleRes = new Reservation(pGuestStart, pGuestEnd, pDay, new BilliardsLounge(), pGuest);
				break;
			case "AquaWorld":
				possibleRes = new Reservation(pGuestStart, pGuestEnd, pDay, new AquaWorld(), pGuest);
				break;
			default: 
				possibleRes = new Reservation(pGuestStart, pGuestEnd, pDay, new SmallPartyRoom(), pGuest);
				break;
		}
		return possibleRes;
	}

	public boolean PlaceReservation(Reservation pReservation) {
		Room desiredRoom = pReservation.getmRoom(), openRoom;
		if (desiredRoom instanceof SmallPartyRoom) {
			openRoom = checkRooms(RoomList.getmRoomList().getmSmallRooms(), pReservation);
			if (openRoom != null) {
				pReservation.setmRoom(openRoom);
				addValidRes(pReservation);
				return true;
			}
		}
		else if (desiredRoom instanceof MediumPartyRoom) {
			openRoom = checkRooms(RoomList.getmRoomList().getmMediumRooms(), pReservation);
			if (openRoom != null) {
				pReservation.setmRoom(openRoom);
				addValidRes(pReservation);
				return true;
			}
		}
		else if (desiredRoom instanceof KaraokeLounge) {
			openRoom = checkRooms(RoomList.getmRoomList().getmKaraokeRooms(), pReservation);
			if (openRoom != null) {
				pReservation.setmRoom(openRoom);
				addValidRes(pReservation);
				return true;
			}
		}
		else if (desiredRoom instanceof BilliardsLounge) {
			openRoom = checkRooms(RoomList.getmRoomList().getmKaraokeRooms(), pReservation);
			if (openRoom != null) {
				pReservation.setmRoom(openRoom);
				addValidRes(pReservation);
				return true;
			}
		}
		else if (desiredRoom instanceof AquaWorld) {
			Room lAqua = RoomList.getmRoomList().getmAquaWorld();
			for (Date iDate : lAqua.getRoomDates()) {
				if (pReservation.getmDay().equals(iDate.getmDay())){
					if (iDate.isOpen(pReservation)) {
						pReservation.setmRoom(RoomList.getmRoomList().getmAquaWorld());
						addValidRes(pReservation);
						return true;
					}
				}
			}
		}
		Waitlist.getmWaitlist().addToWaitList(pReservation);
		return false; 
	}

		/**
		 * Goes through each room and returns any that are open for the res.
		 * @param pRooms - The list of rooms possible
		 * @param pReservation - The reservation desired
		 * @param pDefault - a
		 * @return
		 */
	public static Room checkRooms(ArrayList<Room> pRooms, Reservation pReservation) {
		for (Room iRoom : pRooms) {
			for (Date iDate : iRoom.getRoomDates()) {
				if (pReservation.getmDay().equals(iDate.getmDay())){
					if (iDate.isOpen(pReservation)) {
						return iRoom;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Adds a reservation that has a valid room
	 * @param pReservation - the room to be added.
	 */
	private static void addValidRes(Reservation pReservation) {
		for (Date iDate : pReservation.getmRoom().getRoomDates()) {
			if (pReservation.getmDay().equals(iDate.getmDay())){
				pReservation.setmConfirmationNumber(mConfirmationCount++);
				iDate.addReservation(pReservation);
			}
		}
		
	}
	
	/**
	 * @return the mRoomList
	 */
	public static RoomList getmRoomList() 
	{
		return mRoomList;
	}


	/**
	 * @return the mSmallRooms
	 */
	public ArrayList<Room> getmSmallRooms() 
	{
		return mSmallRooms;
	}


	/**
	 * @return the mMediumRooms
	 */
	public ArrayList<Room> getmMediumRooms() 
	{
		return mMediumRooms;
	}


	/**
	 * @return the mKaraokeRooms
	 */
	public ArrayList<Room> getmKaraokeRooms() 
	{
		return mKaraokeRooms;
	}


	/**
	 * @return the mBilliardRooms
	 */
	public ArrayList<Room> getmBilliardRooms() 
	{
		return mBilliardRooms;
	}


	/**
	 * @return the mAquaWorld
	 */
	public AquaWorld getmAquaWorld() 
	{
		return mAquaWorld;
	}

	//When there is a change in the reservation, the gui is clicked, when the day is changed, notifys get called. 
	//If the code if the change is viable, change is ran 
	//We do not care if the room has a number or not, it is ran regardless. 
	
	//We have to pass in a reservation to compare? 
	public void notifyWaitlist()
	{
		int sizeWaitList = mWaitlist.getWaitListReservation().size();
		//If there is any change in the reservation and we run notify, if there are no reservations to begin with, exit. 
		if(sizeWaitList == 0)
		{
			//Just do nothing here
		}
		else 
		{
			mWaitlist.notify();
		}
		
	}
	
}













////add resevervations in of date and where to put 
////Receive from 
//private ArrayList<Date> schelude = new ArrayList<Date>();
//
///**
// * @return the schelude
// */
//public ArrayList<Date> getSchelude() 
//{
//	return schelude;
//}
//
///**
// * @param schelude the schelude to set
// */
//public void setSchelude(ArrayList<Date> schelude) 
//{
//	this.schelude = schelude;
//}
//
























///**
// * @return the mSmallRooms
// */
//public ArrayList<SmallPartyRoom> getmSmallRooms() {
//	return mSmallRooms;
//}
///**
// * @param mSmallRooms the mSmallRooms to set
// */
//public void setmSmallRooms(ArrayList<SmallPartyRoom> mSmallRooms) {
//	this.mSmallRooms = mSmallRooms;
//} Johnathan, Daniel, Joe
///**
// * @return the mMediumRooms
// */
//public ArrayList<MediumPartyRoom> getmMediumRooms() {
//	return mMediumRooms;
//}
///**
// * @param mMediumRooms the mMediumRooms to set
// */
//public void setmMediumRooms(ArrayList<MediumPartyRoom> mMediumRooms) {
//	this.mMediumRooms = mMediumRooms;
//}
///**
// * @return the mKaraokeRooms
// */
//public ArrayList<KaraokeLounge> getmKaraokeRooms() {
//	return mKaraokeRooms;
//}
///**
// * @param mKaraokeRooms the mKaraokeRooms to set
// */
//public void setmKaraokeRooms(ArrayList<KaraokeLounge> mKaraokeRooms) {
//	this.mKaraokeRooms = mKaraokeRooms;
//}
///**
// * @return the mBilliardRooms
// */
//public ArrayList<BilliardsLounge> getmBilliardRooms() {
//	return mBilliardRooms;
//}
///**
// * @param mBilliardRooms the mBilliardRooms to set
// */
//public void setmBilliardRooms(ArrayList<BilliardsLounge> mBilliardRooms) {
//	this.mBilliardRooms = mBilliardRooms;
//}
///**
// * @return the mAquaWorld
// */
//public AquaWorld getmAquaWorld() {
//	return mAquaWorld;
//}
///**
// * @param mAquaWorld the mAquaWorld to set
// */
//public void setmAquaWorld(AquaWorld mAquaWorld) {
//	this.mAquaWorld = mAquaWorld;
//}
//
////Making the waitlist for rooms

///**
//* @return the mSmallList
//*/
//public Waitlist getmSmallList() 
//{
//	return mSmallList;
//}
//
//
///**
//* @return the mMediumList
//*/
//public Waitlist getmMediumList() 
//{
//	return mMediumList;
//}
//
//
///**
//* @return the mKaraokeList
//*/
//public Waitlist getmKaraokeList() 
//{
//	return mKaraokeList;
//}
//
//
///**
//* @return the mBilliardsList
//*/
//public Waitlist getmBilliardsList() 
//{
//	return mBilliardsList;
//}
//
//
///**
//* @return the mAquaWorldList
//*/
//public Waitlist getmAquaWorldList() 
//{
//	return mAquaWorldList;
//}