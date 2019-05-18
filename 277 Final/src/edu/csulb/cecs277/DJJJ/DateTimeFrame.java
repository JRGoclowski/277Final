package edu.csulb.cecs277.DJJJ;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DateTimeFrame extends JFrame {
	
	public static void main(String args[]) {
		DateTimeFrame dtf = new DateTimeFrame();
		dtf.setVisible(true);
	}

	public class SaveButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int sMonth, sDay, sYear;
			sMonth = Integer.parseInt(mMonthTF.getText());
			sDay = Integer.parseInt(mDayTF.getText());
			sYear= Integer.parseInt(mYearTF.getText());
			Day sDate = new Day(sMonth, sDay, sYear);
			Time startTime = Time.GetTimeFromString(mStartTimeCB.getSelectedItem().toString());
			Time endTime = Time.GetTimeFromString(mEndTimeCB.getSelectedItem().toString());
			String sRoom = mRoomTypeCB.getSelectedItem().toString();
			
			if (!waitlisted(sRoom, sDate, startTime, endTime)) {
				ReservationFrame rf = new ReservationFrame(sDate, endTime, startTime, sRoom, false);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				rf.setLocation(dim.width/2-rf.getSize().width/2, dim.height/2-rf.getSize().height/2);
				rf.setVisible(true);
				
				setVisible(false);
			}
			else {
				WaitlistMsgFrame wmf = new WaitlistMsgFrame(sDate, startTime, endTime, sRoom);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				wmf.setLocation(dim.width/2-wmf.getSize().width/2, dim.height/2-wmf.getSize().height/2);
				wmf.setVisible(true);
			}
		}

	}
	
	public class CancelButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}

	}
	
	private DateTimeFrame mDTFrame;
	private JTextField mDayTF, mMonthTF, mYearTF;
	private JComboBox<String> mStartTimeCB, mEndTimeCB, mRoomTypeCB;
	private JButton mSaveB, mCancelB;
	private JPanel mDTP, mMainP, mButtonP; 
	
	public DateTimeFrame() {
		this.setTitle("Date Time Frame");
		this.setSize(1250,300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		InitializeDateTimeP();
		InitializeButtonP();
		InitializeMainP();
		mDTFrame = this;
	}
	
	public DateTimeFrame(String roomName) {
		this.setTitle(roomName);
		this.setSize(1250,300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		InitializeDateTimeP();
		InitializeButtonP();
		InitializeMainP();
		mRoomTypeCB.setSelectedItem(roomName);
		mDTFrame = this;
	}
	
	private void InitializeMainP() {
		mMainP = new JPanel();
		mMainP.add(mDTP);
		mMainP.add(mButtonP);
		this.add(mMainP);
	}
	
	private void InitializeButtonP() {
		mButtonP = new JPanel();
		
		mSaveB = new JButton("Save");
		mSaveB.setVisible(true);
		ActionListener saveListener = new SaveButtonListener();
		mSaveB.addActionListener(saveListener);
		
		
		mCancelB = new JButton("Cancel");
		mCancelB.setVisible(true);
		ActionListener cancelListener = new CancelButtonListener();
		mCancelB.addActionListener(cancelListener);		
		
		mButtonP.add(mSaveB);
		mButtonP.add(mCancelB);
		
	}
	
	private void InitializeDateTimeP() {
		mDTP = new JPanel();
		
		mDTP.add(new JLabel("Room Type:"));
		String[] roomTypes = {"Small Party Room", "Medium Party Room", "Karaoke Lounge", "Billiards Lounge", "Aqua World"};
		mRoomTypeCB = new JComboBox<String>(roomTypes);
		mDTP.add(mRoomTypeCB);
		
		mDTP.add(new JLabel("Date:"));
		mMonthTF = new JTextField("M");
		mMonthTF.setColumns(5);
		mDayTF = new JTextField("D");
		mDayTF.setColumns(5);
		mYearTF = new JTextField("Y");
		mYearTF.setColumns(5);
		mDTP.add(mMonthTF);
		mDTP.add(mDayTF);
		mDTP.add(mYearTF);
		
		
		mDTP.add(new JLabel("Time:"));
		ArrayList<String> timeStrings = new ArrayList<String>();
		for (Time iTime: Time.ALL_TIMES) {
			timeStrings.add(iTime.toString());
		}
		String[] times = new String [timeStrings.size()];
		for (int i = 0; i < timeStrings.size(); i++) {
			times [i] = timeStrings.get(i);
		}
		mStartTimeCB = new JComboBox<String>(times);
		mEndTimeCB = new JComboBox<String>(times);
		mDTP.add(new JLabel("Start:"));
		mDTP.add(mStartTimeCB);
		mDTP.add(new JLabel("End:"));
		mDTP.add(mEndTimeCB);
		
	}
	
	private boolean waitlisted(String pRoom, Day pDate, Time pStart, Time pEnd) {
		if(pRoom.equals("Small Party Room")) {
			for (Room iRoom : RoomList.getmSmallRooms()) {
				for (Date iDate : iRoom.getRoomDates()) {
					if (pDate.equals(iDate.getmDay())){
						if (iDate.isOpen(pStart, pEnd)) {
							return true;
						}
					}
				}
			}
		}
		
		else if(pRoom.equals("Medium Party Room")) {
			for (Room iRoom : RoomList.getmMediumRooms()) {
				for (Date iDate : iRoom.getRoomDates()) {
					if (pDate.equals(iDate.getmDay())){
						if (iDate.isOpen(pStart, pEnd)) {
							return true;
						}
					}
				}
			}
		}
		
		else if(pRoom.equals("Karaoke Lounge")) {
			for (Room iRoom : RoomList.getmKaraokeRooms()) {
				for (Date iDate : iRoom.getRoomDates()) {
					if (pDate.equals(iDate.getmDay())){
						if (iDate.isOpen(pStart, pEnd)) {
							return true;
						}
					}
				}
			}
		}
		
		else if(pRoom.equals("Billiards Lounge")) {
			for (Room iRoom : RoomList.getmBilliardRooms()) {
				for (Date iDate : iRoom.getRoomDates()) {
					if (pDate.equals(iDate.getmDay())){
						if (iDate.isOpen(pStart, pEnd)) {
							return true;
						}
					}
				}
			}
		}
		
		else if(pRoom.equals("Aqua World")) {
			for (Date iDate : RoomList.getmAquaWorld().getRoomDates()) {
				if (pDate.equals(iDate.getmDay())){
					if (iDate.isOpen(pStart, pEnd)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
}