package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public class Schedule 
{
	//ArrayList of rooms for waitlist 
	private ArrayList<smallPartyRooms> mSmallRooms = new ArrayList<smallPartyRooms>();
	private ArrayList<mediumPartyRooms> mMediumRooms = new ArrayList<mediumPartyRooms>();
	private ArrayList<karaokeRooms> mKaraokeRooms = new ArrayList<mKaraokeRooms>();
	private ArrayList<billiardRooms> mBilliardRooms = new ArrayList<billiardRooms>();
	private AquaWorld mAquaWorld = new AquaWorld();
	
	//Making the waitlist for rooms
	private WaitList mSmallList = new WaitList();
	private WaitList mMediumList = new WaitList();
	private WaitList mKaraokeList = new WaitList();
	private WaitList mBilliardsList = new WaitList();
	private WaitList mAquaWorldList = new WaitList();
	
	/**
	 * @return The array list of small party rooms
	 * Everytime you create a small party room, you add to this list.
	 */
	public ArrayList<smallPartyRooms> getSmallRoomList()
	{
		return mSmallRooms;
	}
	
	/**
	 * @return The array list of medium room list
	 * Everytime you create a medium party room, you add to this list.
	 */
	public ArrayList<mediumPartyRooms> getMediumRoomList()
	{
		return mMediumRooms;
	}
	
	
	/**
	 * @return The array list of karaoke rooms
	 * Everytime you create a karaoke party room, you add to this list.
	 */
	public ArrayList<smallKaraokeRooms> getKaraokeRoomList()
	{
		return mKaraokeRooms;
	}
	
	
	/**
	 * @return The array list of billiard
	 * Everytime you create a biliard party room, you add to this list.
	 */
	public ArrayList<smallPartyRooms> getBilliardRoomList()
	{
		return mBilliardRooms;
	}
	
	
	/**
	 * @return The aquaworld object 
	 * Everytime you create an aquaworld party room, you add to this list.
	 */
	public AquaWorld getAquaWorld()
	{
		return mAquaWorld;
	}
	
	
	
	public WaitList getWaitListSmall()
	{
		return mSmallList;
	}
	
	public WaitList getWaitListMedium()
	{
		return mMediumList;
	}
	
	public WaitList getWaitListKaraoke()
	{
		return mKaraokeList;
	}
	
	public WaitList getWaitListBilliards()
	{
		return mBilliardsList;
	}
	
	public WaitList getWaitListAqua()
	{
		return mAquaWorldList;
	}

	
}
