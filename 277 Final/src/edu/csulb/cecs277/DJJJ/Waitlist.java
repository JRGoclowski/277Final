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
	
	//We only have one array list of reservation. When a person wants a room, we check through this reservation .
	//Each reservation has a guest, start time, Room, 
	//When a person creates a reservation, we give it a reservation 
	//This is used if there is no room availiable for the guest, we have an arraylist of reservation that is put onto "hold"
	//Since we have the list of wait list reservation, what we can do is that everytime there is a change in a reservation
	//that is not a waitlist, we can run notify from roomlist!
	ArrayList<Reservation> WaitList = new ArrayList<Reservation>();

	/**
	 * @return the mWaitlist arraylist 
	 */
	public static Waitlist getmWaitlist() 
	{
		return mWaitlist;
	}
	
	
	/**
	 * @return The arraylist of reservation if there is empty
	 */
	public ArrayList<Reservation> getWaitListReservation()
	{
		return WaitList;
	}
	
	
	
	//This is used whenever, walks through every reservation in the waitlist when a time is free 
	public void update()
	{
		//This variable is used to keep the position of the arraylist in case a timeslot works for the reservations inside
		//waitlist 
		int i;
		
		
		//This variable will be used if the reservation is found on not
		boolean checker = true;
		//Checks through each reservation and see if the reservation is now valid? 
		//Do we make an entirely new function inside reservation? 
		for(i = 0; WaitList.size() > i; i++)
		{
			//Now we get each individual waitlist and see if the time and day now works. 
			if(true)
			{
				//The time does not work and we set the checker to false
				checker = false;
				break;
			}
		}
		
		
		//The time was changed or edit now matches one of the reservations in the waitlist. Now we can remove it from the waitlist
		if(checker)
		{
			
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