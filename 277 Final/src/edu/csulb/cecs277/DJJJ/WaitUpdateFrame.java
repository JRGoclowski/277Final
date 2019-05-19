package edu.csulb.cecs277.DJJJ;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WaitUpdateFrame extends JFrame {

private JPanel mWaitUpP;
	
	public WaitUpdateFrame(Reservation pReservation) {
		this.setTitle("Wait Update Frame");
		this.setSize(250,300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mWaitUpP = new JPanel();
		mWaitUpP.add(new JLabel("You have moved off the waitlist!"));
		mWaitUpP.add(new JLabel("Your room number is: " + pReservation.getmRoom().getRoomNumber()));
		mWaitUpP.add(new JLabel("Your confirmation number is: " + ReservationFrame.ConNumToString(pReservation.getmConfirmationNumber())));
		this.add(mWaitUpP);
	}
	
	public static void waitUpdater() {
		boolean free = false;
		
		for(Reservation iWait : Waitlist.getWaitListReservation()) {
			Time tStart = iWait.getmFullStartTime();
			Time tEnd = iWait.getmFullEndTime();
			int counter = 0, cMax = 0;
			
			if (iWait.getmRoom() instanceof SmallPartyRoom) {
				cMax = 10;
				for (Room iRoom : RoomList.getmSmallRooms()) {
					for (Date iDate : iRoom.getRoomDates()) {
						if (!(iDate.getmReservations().equals(null))) {
							for (Reservation iRes : iDate.getmReservations()) {
								if ((tEnd.getmHours() < iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() > iRes.getmFullEndTime().getmHours())) { continue; }
								if ((tEnd.getmHours() == iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() == iRes.getmFullEndTime().getmHours())) { 
									if ((tEnd.getmMinutes() < iRes.getmFullStartTime().getmMinutes()) || (tStart.getmMinutes() > iRes.getmFullEndTime().getmMinutes())) { continue; }
								}
								counter++;
							}
						}
					}
				}
			}
			
			else if(iWait.getmRoom() instanceof MediumPartyRoom) {
				cMax = 2;
				for (Room iRoom : RoomList.getmMediumRooms()) {
					for (Date iDate : iRoom.getRoomDates()) {
						if (!(iDate.getmReservations().equals(null))) {
							for (Reservation iRes : iDate.getmReservations()) {
								if ((tEnd.getmHours() < iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() > iRes.getmFullEndTime().getmHours())) { continue; }
								if ((tEnd.getmHours() == iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() == iRes.getmFullEndTime().getmHours())) { 
									if ((tEnd.getmMinutes() < iRes.getmFullStartTime().getmMinutes()) || (tStart.getmMinutes() > iRes.getmFullEndTime().getmMinutes())) { continue; }
								}
								counter++;
							}
						}
					}
				}
			}
			
			else if(iWait.getmRoom() instanceof KaraokeLounge) {
				cMax = 10;
				for (Room iRoom : RoomList.getmKaraokeRooms()) {
					for (Date iDate : iRoom.getRoomDates()) {
						if (!(iDate.getmReservations().equals(null))) {
							for (Reservation iRes : iDate.getmReservations()) {
								if ((tEnd.getmHours() < iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() > iRes.getmFullEndTime().getmHours())) { continue; }
								if ((tEnd.getmHours() == iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() == iRes.getmFullEndTime().getmHours())) { 
									if ((tEnd.getmMinutes() < iRes.getmFullStartTime().getmMinutes()) || (tStart.getmMinutes() > iRes.getmFullEndTime().getmMinutes())) { continue; }
								}
								counter++;
							}
						}
					}
				}
			}
			
			else if(iWait.getmRoom() instanceof BilliardsLounge) {
				cMax = 5;
				for (Room iRoom : RoomList.getmBilliardRooms()) {
					for (Date iDate : iRoom.getRoomDates()) {
						if (!(iDate.getmReservations().equals(null))) {
							for (Reservation iRes : iDate.getmReservations()) {
								if ((tEnd.getmHours() < iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() > iRes.getmFullEndTime().getmHours())) { continue; }
								if ((tEnd.getmHours() == iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() == iRes.getmFullEndTime().getmHours())) { 
									if ((tEnd.getmMinutes() < iRes.getmFullStartTime().getmMinutes()) || (tStart.getmMinutes() > iRes.getmFullEndTime().getmMinutes())) { continue; }
								}
								counter++;
							}
						}
					}
				}
			}
			
			else if(iWait.getmRoom() instanceof AquaWorld) {
				cMax = 1;
				for (Date iDate : RoomList.getmAquaWorld().getRoomDates()) {
					if (!(iDate.getmReservations().equals(null))) {
						for (Reservation iRes : iDate.getmReservations()) {
							if ((tEnd.getmHours() < iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() > iRes.getmFullEndTime().getmHours())) { continue; }
							if ((tEnd.getmHours() == iRes.getmFullStartTime().getmHours()) || (tStart.getmHours() == iRes.getmFullEndTime().getmHours())) { 
								if ((tEnd.getmMinutes() <= iRes.getmFullStartTime().getmMinutes()) || (tStart.getmMinutes() >= iRes.getmFullEndTime().getmMinutes())) { continue; }
							}
							counter++;
						}
					}
				}
			}
			
			if(counter >= cMax) { free = false; }
			free = true;
			
			if (free) {
				iWait.getmRoom().setRoomNumber(counter+1);
				iWait.setmConfirmationNumber(ReservationFrame.addConNum());
				RoomList.getmRoomList().PlaceReservation(iWait);
				
				WaitUpdateFrame wuf = new WaitUpdateFrame(iWait);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				wuf.setLocation(dim.width/2-wuf.getSize().width/2, dim.height/2-wuf.getSize().height/2);
				wuf.setVisible(true);
				return;
			}
			
		}
	}
}
