package edu.csulb.cecs277.DJJJ;

import java.util.ArrayList;

public class dummyTester {
	
	private static ArrayList<Room> holder = new ArrayList<Room>();
	
	Room smallRoom = new SmallPartyRoom();
	
	public static void adder() {
		SmallPartyRoom smallRoom = new SmallPartyRoom();
		
		smallRoom.setRoomNumber(1);
		
		holder.add(smallRoom);
	}
	
	private dummyTester() {
		adder();
	}
	
	public static void main(String[] args) {
		dummyTester lol = new dummyTester();
	}
}
