package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public class RoomList 
{
	//implement the singleton 
	private static RoomList mRoomList = new RoomList();
	
	private static int mConfirmationCount = 1000;
	
	//ArrayList of rooms for waitlist, when we make the rooms, we add it to this array list of rooms 
	private static ArrayList<Room> mSmallRooms;
	private static ArrayList<Room> mMediumRooms;
	private static ArrayList<Room> mKaraokeRooms;
	private static ArrayList<Room> mBilliardRooms;
	private static AquaWorld mAquaWorld;
	
	//Array list of guest 
	private ArrayList<Guest> mGuestList = new ArrayList<Guest>();
	
	//Each room has a waitlist?
	private Waitlist mWaitlist = Waitlist.getmWaitlist();
	
	//SingleTon 
	private RoomList() 
	{
		mSmallRooms = new ArrayList<Room>();
		mMediumRooms = new ArrayList<Room>();
		mKaraokeRooms = new ArrayList<Room>();
		mBilliardRooms = new ArrayList<Room>();
		mAquaWorld = new AquaWorld();
		populateRoomList();
	}
	
	private void populateRoomList() {
		SmallPartyRoom mySmall = new SmallPartyRoom();
		MediumPartyRoom myMedium = new MediumPartyRoom();
		KaraokeLounge myKaraoke = new KaraokeLounge();
		BilliardsLounge myBilliards = new BilliardsLounge();
		
		for (int i = 0; i < 10; i++) {
			SmallPartyRoom newSmall = new SmallPartyRoom();
			newSmall.setRoomNumber(i+1);
			mSmallRooms.add(newSmall);
		}
		
		for (int i = 0; i < 2; i++) {
			MediumPartyRoom newMedium = new MediumPartyRoom();
			newMedium.setRoomNumber(i+1);
			mMediumRooms.add(newMedium);
		}
		
		for (int i = 0; i < 10; i++) {
			KaraokeLounge newKaraoke = new KaraokeLounge();
			newKaraoke.setRoomNumber(i+1);
			mKaraokeRooms.add(newKaraoke);
		}
		
		for (int i = 0; i < 5; i++) {
			BilliardsLounge newBilliards = new BilliardsLounge();
			newBilliards.setRoomNumber(i+1);
			mBilliardRooms.add(newBilliards);
		}
		
		mAquaWorld.setRoomNumber(1);
	}
	
	//Create a base room (new SmallRoom() or whatever)
	//For (i = 1; i < Number of that room type + 1 [you need the plus one, so it goes up to that number]; i++)
	//Room newRoom = BaseRoom.clone()
	//newRoom.setNumer(i);
	
	
	/**
	 * Takes these parameters and attempts to create a 
	 * @param pGuestStart
	 * @param pGuestEnd
	 * @param pGuest
	 * @param desiredRoom
	 * @param pDay
	 * @return
	 */
	
	//Makes a reservation and returns a reservation.
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

	//Places the reservation but first make sure that it's valid. If not, this put it inside waitlist 
	public boolean PlaceReservation(Reservation pReservation) {
		Room desiredRoom = pReservation.getmRoom(), openRoom;
		if (desiredRoom instanceof AquaWorld) {
			Room lAqua = RoomList.getmRoomList().getmAquaWorld();
			for (Date iDate : lAqua.getRoomDates()) {
				if (pReservation.getmDay().getmStringForm().equals(iDate.getmDay().getmStringForm())){
					if (iDate.isOpen(pReservation)) {
						pReservation.setmRoom(RoomList.getmRoomList().getmAquaWorld());
						addValidRes(pReservation);
						return true;
					}
				}
			}
		}
		openRoom = checkRooms(GetRoomsSameAs(pReservation), pReservation);
		if (openRoom != null) {
			pReservation.setmRoom(openRoom);
			addValidRes(pReservation);
			return true;
		}		
		return false; 
	}
	
	
	public static void removeReservation(Reservation pReservation) {
		for (Date iDate : pReservation.getmRoom().getRoomDates()) {
			if (pReservation.getmDay().getmStringForm().equals(iDate.getmDay().getmStringForm())){
				iDate.removeReservation(pReservation);
			}
		}
	}
	
	
	//Checks if the room is open 
	public static Room checkRooms(ArrayList<Room> pRooms, Day pDay, Time pStart, Time pEnd) {
		for (Room iRoom : pRooms) {
			//Each room has a date 
			for (Date iDate : iRoom.getRoomDates()) {
				if (pDay.equals(iDate.getmDay())){
					if (iDate.isOpen(pStart, pEnd)) {
						return iRoom;
					}
				}
			}
		}
		return null;
	}
	
	public static Room checkRooms(ArrayList<Room> pRooms, Reservation pReservation) {
		for (Room iRoom : pRooms) {
			for (Date iDate : iRoom.getRoomDates()) {
				if (pReservation.getmDay().getmStringForm().equals(iDate.getmDay().getmStringForm())){
					if (iDate.isOpen(pReservation)) {
						return iRoom;
					}
				}
			}
		}
		return null;
	}
	
	public static ArrayList<Room> GetRoomsSameAs(Reservation pReservation){
		Room lRoom = pReservation.getmRoom();
		if (lRoom instanceof SmallPartyRoom) {
			return mSmallRooms;
		}
		else if (lRoom instanceof MediumPartyRoom) {
			return mMediumRooms;
		}
		else if (lRoom instanceof KaraokeLounge) {
			return mKaraokeRooms;
		}
		else if (lRoom instanceof BilliardsLounge) {
			return mBilliardRooms;
		}
		else {
			return null;
		}
	}
	
	public Date ReturnDateOfRes(Reservation pReservation) {
		Room lRoom = pReservation.getmRoom();
		if (lRoom instanceof SmallPartyRoom) {
			return ReturnDateOfRes(mSmallRooms, pReservation);
		}
		else if (lRoom instanceof MediumPartyRoom) {
			return ReturnDateOfRes(mMediumRooms, pReservation);
		}
		else if (lRoom instanceof KaraokeLounge) {
			return ReturnDateOfRes(mKaraokeRooms, pReservation);
		}
		else if (lRoom instanceof BilliardsLounge) {
			return ReturnDateOfRes(mBilliardRooms, pReservation);
		}
		else {
			for (Date iDate : mAquaWorld.getRoomDates()) {
				if (pReservation.getmDay().equals(iDate.getmDay())) {
					if (iDate.getmReservations().contains(pReservation)) {
							return iDate;
					}
				}
			}
		}
		return null;
	}

	
	private Date ReturnDateOfRes(ArrayList<Room> pRooms, Reservation pReservation) {
		for (Room iRoom : pRooms) {
			//Each room has a date 
			for (Date iDate : iRoom.getRoomDates()) {
				if (pReservation.getmDay().equals(iDate.getmDay())){
					if (iDate.getmReservations().contains(pReservation)) {
						return iDate;
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
			if (pReservation.getmDay().getmStringForm().equals(iDate.getmDay().getmStringForm())){
				pReservation.setmConfirmationNumber(mConfirmationCount++);
				iDate.addReservation(pReservation);
			}
		}
		
	}
	
	public Reservation FindReservation(int pConfirmation) {
		
		for (Room iRoom : mSmallRooms) {
			for (Date iDate: iRoom.getRoomDates()) {
				for (Reservation iRes : iDate.getmReservations()) {
					if (iRes.getmConfirmationNumber()  == pConfirmation) {
						return iRes;
					}
				}
			}
		}
		for (Room iRoom : mMediumRooms) {
			for (Date iDate: iRoom.getRoomDates()) {
				for (Reservation iRes : iDate.getmReservations()) {
					if (iRes.getmConfirmationNumber()  == pConfirmation) {
						return iRes;
					}
				}
			}
		}
		for (Room iRoom : mKaraokeRooms) {
			for (Date iDate: iRoom.getRoomDates()) {
				for (Reservation iRes : iDate.getmReservations()) {
					if (iRes.getmConfirmationNumber()  == pConfirmation) {
						return iRes;
					}
				}
			}
		}
		for (Room iRoom : mBilliardRooms) {
			for (Date iDate: iRoom.getRoomDates()) {
				for (Reservation iRes : iDate.getmReservations()) {
					if (iRes.getmConfirmationNumber()  == pConfirmation) {
						return iRes;
					}
				}
			}
		}
		for (Date iDate: mAquaWorld.getRoomDates()) {
			for (Reservation iRes : iDate.getmReservations()) {
				if (iRes.getmConfirmationNumber()  == pConfirmation) {
					return iRes;
				}
			}
		}
		
		return null;
	}
	
	public Reservation FindReservation(String pName) {
		for (Room iRoom : mSmallRooms) {
			for (Date iDate: iRoom.getRoomDates()) {
				for (Reservation iRes : iDate.getmReservations()) {
					if (iRes.getmGuest().getmName().equals(pName)) {
						return iRes;
					}
				}
			}
		}
		for (Room iRoom : mMediumRooms) {
			for (Date iDate: iRoom.getRoomDates()) {
				for (Reservation iRes : iDate.getmReservations()) {
					if (iRes.getmGuest().getmName().equals(pName)) {
						return iRes;
					}
				}
			}
		}
		for (Room iRoom : mKaraokeRooms) {
			for (Date iDate: iRoom.getRoomDates()) {
				for (Reservation iRes : iDate.getmReservations()) {
					if (iRes.getmGuest().getmName().equals(pName)) {
						return iRes;
					}
				}
			}
		}
		for (Room iRoom : mBilliardRooms) {
			for (Date iDate: iRoom.getRoomDates()) {
				for (Reservation iRes : iDate.getmReservations()) {
					if (iRes.getmGuest().getmName().equals(pName)) {
						return iRes;
					}
				}
			}
		}
		for (Date iDate: mAquaWorld.getRoomDates()) {
			for (Reservation iRes : iDate.getmReservations()) {
				if (iRes.getmGuest().getmName().equals(pName)) {
					return iRes;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * @return the mRoomList
	 */
	public static RoomList getmRoomList() 
	{
		
		if (mRoomList == null) {
			mRoomList = new RoomList();
		}
		return mRoomList;
	}


	/**
	 * @return the mSmallRooms
	 */
	public static ArrayList<Room> getmSmallRooms() 
	{
		return mSmallRooms;
	}


	/**
	 * @return the mMediumRooms
	 */
	public static ArrayList<Room> getmMediumRooms() 
	{
		return mMediumRooms;
	}


	/**
	 * @return the mKaraokeRooms
	 */
	public static ArrayList<Room> getmKaraokeRooms() 
	{
		return mKaraokeRooms;
	}


	/**
	 * @return the mBilliardRooms
	 */
	public static ArrayList<Room> getmBilliardRooms() 
	{
		return mBilliardRooms;
	}


	/**
	 * @return the mAquaWorld
	 */
	public static AquaWorld getmAquaWorld() 
	{
		return mAquaWorld;
	}

	//When there is a change in the reservation, the gui is clicked, when the day is changed, notifys get called. 
	//If the code if the change is viable, change is ran 
	//We do not care if the room has a number or not, it is ran regardless. 
	
	//We have to pass in a reservation to compare? 
	public void notifyWaitlist()
	{
		mWaitlist.notify();
	}
	
}






//MANAGE CURRENT RESERVATION 

//Check in and out frames 
//The code is going to look like, build the frame. 
//Write three frames

//What day they want to look at. Go through every single day and get every date 
//Each date go through every single reservation

//NEXT FRAME CHECK IN 
//Every single reservation guest that has checked in set to false, get that guest. 
//Display all the guest that are false
//This frame should be able to click a guest and be able to check in 
//When check in is pressed, when the reservation check in and false and the guest is removed from the frame. 

//THIRD FRAME CHECK OUT 
//Every single reservation guest for that date and checked in true
//Shows all the guest and makes be able to check out 
//Pay the rest of it 









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