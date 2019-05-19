package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public class Waitlist
{
	//implement the singleton 
	private static Waitlist mWaitlist;
	
	//SingleTon 
	private Waitlist () 
	{
		mWaitlist = this;
	}
	
	//Array list of reservations 
	static ArrayList<Reservation> mReservationList = new ArrayList<Reservation>();

	/**
	 * @return the mWaitlist arraylist 
	 */
	public static Waitlist getmWaitlist() 
	{
		return mWaitlist;
	}
	
	
	/**
	 * @return The arraylist of reservation 
	 */
	public static ArrayList<Reservation> getWaitListReservation()
	{
		return mReservationList;
	}
	
	public static void addToWaitList(Reservation pReservation) {
		mReservationList.add(pReservation);
	}
	
	/**
	 * Checks all waitlist reservations to see if any are now valid;
	 * @return Reservation - the first valid reservation
	 */
	private Reservation GetOpenReservation() {
		
		for (Reservation iRes : mReservationList) {
			Room desiredRoom = iRes.getmRoom(), defaultRoom, openRoom;
			if (desiredRoom instanceof SmallPartyRoom) {
				openRoom = RoomList.checkRooms(RoomList.getmRoomList().getmSmallRooms(), iRes);
				if (openRoom != null) {
					iRes.setmRoom(openRoom);
					return iRes;
				}
			}
			else if (desiredRoom instanceof MediumPartyRoom) {
				openRoom = RoomList.checkRooms(RoomList.getmRoomList().getmMediumRooms(), iRes);
				if (openRoom != null) {
					iRes.setmRoom(openRoom);
					return iRes;
				}
			}
			else if (desiredRoom instanceof KaraokeLounge) {
				openRoom = RoomList.checkRooms(RoomList.getmRoomList().getmKaraokeRooms(), iRes);
				if (openRoom != null) {
					iRes.setmRoom(openRoom);
					return iRes;
				}
			}
			else if (desiredRoom instanceof BilliardsLounge) {
				openRoom = RoomList.checkRooms(RoomList.getmRoomList().getmKaraokeRooms(), iRes);
				if (openRoom != null) {
					iRes.setmRoom(openRoom);
					return iRes;
				}
			}
			else if (desiredRoom instanceof AquaWorld) {
				Room lAqua = RoomList.getmRoomList().getmAquaWorld();
				for (Date iDate : lAqua.getRoomDates()) {
					if (iRes.getmDay().equals(iDate.getmDay())){
						if (iDate.isOpen(iRes)) {
							iRes.setmRoom(lAqua);
							return iRes;
						}
					}
				}
			}
		}
		return null; 
	}
	
	/**
	 * Uses helper methods to walk through waitlist and adds any valid reservations
	 * @return boolean - returns true if a valid reservation is added.
	 */
	public boolean update(){
		Reservation validRes = GetOpenReservation();
		if (validRes != null) {
			Room desiredRoom = validRes.getmRoom();  
			ArrayList<Date> dates =desiredRoom.getRoomDates();
			//IMPORTANT 
			for (Date iDate: dates) {
				if (iDate.getmDay().equals(validRes.getmDay())){
					return iDate.addReservation(validRes);
				}
			}
		}
		return false;
	}
	
	
}











/*int i, j, k;

//Checks through each reservation and see if the reservation is now valid.
for(i = 0; WaitList.size() > i; i++)
{
	//Lets first check whether the room is a specific instace of another room 
	if(WaitList.get(i).getmRoom() instanceof SmallPartyRoom)
	{
		//We're going through each room inside the arraylist 
		for(j = 0; RoomList.getmRoomList().getmSmallRooms().size() > j; j++)
		{
			//We're checking each room's arraylist of date to see if the reservation is valid 
			for(k = 0; RoomList.getmRoomList().getmSmallRooms().get(j).getRoomDates().size() > k; k++)
			{
				//Checking if the reservation is valid!
				RoomList.getmRoomList().getmSmallRooms().get(j).getRoomDates().get(k)
				.isValidReservation(WaitList.get(i));
			}
		}
	}
	else if(WaitList.get(i).getmRoom() instanceof MediumPartyRoom)
	{
		//We're going through each room inside the arraylist 
		for(j = 0; RoomList.getmRoomList().getmMediumRooms().size() > j; j++)
		{
			//We're checking each room's arraylist of date to see if the reservation is valid 
			for(k = 0; RoomList.getmRoomList().getmMediumRooms().get(j).getRoomDates().size() > k; k++)
			{
				//Checking if the reservation is valid!
				RoomList.getmRoomList().getmMediumRooms().get(j).getRoomDates().get(k)
				.isValidReservation(WaitList.get(i));
			}
		}
	}
	else if(WaitList.get(i).getmRoom() instanceof BilliardsLounge)
	{
		//We're going through each room inside the arraylist 
		for(j = 0; RoomList.getmRoomList().getmBilliardRooms().size() > j; j++)
		{
			//We're checking each room's arraylist of date to see if the reservation is valid 
			for(k = 0; RoomList.getmRoomList().getmBilliardRooms().get(j).getRoomDates().size() > k; k++)
			{
				//Checking if the reservation is valid!
				RoomList.getmRoomList().getmBilliardRooms().get(j).getRoomDates().get(k)
				.isValidReservation(WaitList.get(i));
			}
		}
	}
	else if(WaitList.get(i).getmRoom() instanceof KaraokeLounge)
	{
		//We're going through each room inside the arraylist 
		for(j = 0; RoomList.getmRoomList().getmKaraokeRooms().size() > j; j++)
		{
			//We're checking each room's arraylist of date to see if the reservation is valid 
			for(k = 0; RoomList.getmRoomList().getmKaraokeRooms().get(j).getRoomDates().size() > k; k++)
			{
				//Checking if the reservation is valid!
				RoomList.getmRoomList().getmKaraokeRooms().get(j).getRoomDates().get(k)
				.isValidReservation(WaitList.get(i));
			}
		}
	}
	else if(WaitList.get(i).getmRoom() instanceof AquaWorld)
	{
		//We're checking each room's arraylist of date to see if the reservation is valid 
		for(k = 0; RoomList.getmRoomList().getmAquaWorld().getRoomDates().size() > k; k++)
			{
				//Checking if the reservation is valid!
				RoomList.getmRoomList().getmAquaWorld().getRoomDates().get(k)
				.isValidReservation(WaitList.get(i));
			}
	}
}*/





//Has both update and notify

//public Waitlist(Subject subject)
//{
//	this.subject = subject;
//	this.subject.attach(this);
//}
//
//private LinkedList<Reservation> room = new LinkedList<Reservation>();
//
///**
// * @return The linked list reservation list of a particular room type 
// */
//private LinkedList<Reservation> getRoom()
//{
//	return room;
//}
//
///**
// * @param Adds a reservation to the room linkedlist
// */
//private void addRerservation(Reservation d)
//{
//	room.add(d);
//}
//
//@Override
//public void update() 
//{
//	
//	
//}