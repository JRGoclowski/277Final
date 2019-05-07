package edu.csulb.cecs277.DJJJ;

import java.util.LinkedList;

public class Waitlist extends Observer
{
	public Waitlist(Subject subject)
	{
		this.subject = subject;
		this.subject.attach(this);
	}
	
	private LinkedList<Reservation> room = new LinkedList<Reservation>();
	
	/**
	 * @return The linked list reservation list of a particular room type 
	 */
	private LinkedList<Reservation> getRoom()
	{
		return room;
	}
	
	/**
	 * @param Adds a reservation to the room linkedlist
	 */
	private void addRerservation(Reservation d)
	{
		room.add(d);
	}

	@Override
	public void update() 
	{
		
		
	}

	
}
