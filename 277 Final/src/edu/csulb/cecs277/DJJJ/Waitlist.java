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
	ArrayList<Reservation> WaitList = new ArrayList<Reservation>();

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
	public ArrayList<Reservation> getWaitListReservation()
	{
		return WaitList;
	}
	
	
	
	//This is used whenever, walks through every reservation in the waitlist when a time is free 
	public void update()
	{
		int i, j, k;
		
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
		}
		
	}

	
	public static void main(String[] args)
	{
		System.out.println("hi");
	}
}
















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