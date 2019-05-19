package edu.csulb.cecs277.DJJJ;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;

public class DateTimeFrame extends JFrame {
	
	public static void main(String args[]) {
		DateTimeFrame dtf = new DateTimeFrame();
		dtf.setVisible(true);
	}

	public class SaveButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int sMonth, sDay, sYear;
			sMonth = Integer.parseInt((String)mMonthS.getValue());
			sDay = Integer.parseInt((String)mDayS.getValue());
			sYear= Integer.parseInt((String)mYearS.getValue());
			Day sDate = new Day(sMonth, sDay, sYear);
			Time startTime = Time.GetTimeFromString((String)mStartTimeS.getValue());
			Time endTime = Time.GetTimeFromString((String)mEndTimeS.getValue());
			String sRoom = mRoomTypeCB.getSelectedItem().toString();
			
			if (!waitlisted(sRoom, sDate, startTime, endTime)) {
				setVisible(false);
				ReservationFrame rf = new ReservationFrame(sDate, endTime, startTime, sRoom, false);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				rf.setLocation(dim.width/2-rf.getSize().width/2, dim.height/2-rf.getSize().height/2);
				rf.setVisible(true);
			}
			else {
				setVisible(false);
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
	private JComboBox<String> mRoomTypeCB;
	private JSpinner mStartTimeS, mEndTimeS, mDayS, mMonthS, mYearS;
	private JButton mSaveB, mCancelB;
	private JPanel mDTP, mMainP, mButtonP;
	private JFormattedTextField mFTF;
	
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
		
		String[] labels = {"Month: ", "Year: ", "Another Date: "};
		mDTP.add(new JLabel("Date:"));
		String[] monthStrings = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		SpinnerListModel monthModel = new SpinnerListModel(monthStrings);
		mMonthS = addLabeledSpinner(this, labels[0], monthModel);
		mFTF = getTextField(mMonthS);
		mFTF.setColumns(4);
		String[] dayStrings = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		SpinnerListModel dayModel = new SpinnerListModel(dayStrings);
		mDayS = addLabeledSpinner(this, labels[1], dayModel);
		mFTF = getTextField(mDayS);
		mFTF.setColumns(4);
		String[] yearStrings = {"2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029"};
		SpinnerListModel yearModel = new SpinnerListModel(yearStrings);
		mYearS = addLabeledSpinner(this, labels[2], yearModel);
		mFTF = getTextField(mYearS);
		mFTF.setColumns(4);
		
		mDTP.add(mMonthS);
		mDTP.add(mDayS);
		mDTP.add(mYearS);
		
		
		mDTP.add(new JLabel("Time:"));
		ArrayList<String> timeStrings = new ArrayList<String>();
		for (Time iTime: Time.ALL_TIMES) {
			timeStrings.add(iTime.toString());
		}
		String[] times = new String [timeStrings.size()];
		for (int i = 0; i < timeStrings.size(); i++) {
			times [i] = timeStrings.get(i);
		}
		SpinnerListModel sTimesModel = new SpinnerListModel(times);
		mStartTimeS = new JSpinner(sTimesModel);
		mFTF = getTextField(mStartTimeS);
		mFTF.setColumns(5);
		SpinnerListModel eTimesModel = new SpinnerListModel(times);
		mEndTimeS = new JSpinner(eTimesModel);
		mFTF = getTextField(mEndTimeS);
		mFTF.setColumns(5);
		mDTP.add(new JLabel("Start:"));
		mDTP.add(mStartTimeS);
		mDTP.add(new JLabel("End:"));
		mDTP.add(mEndTimeS);
		
	}
	
	static protected JSpinner addLabeledSpinner(Container c, String label, SpinnerModel model) {
		JLabel l = new JLabel(label);
		c.add(l);

		JSpinner spinner = new JSpinner(model);
		l.setLabelFor(spinner);
		c.add(spinner);

		return spinner;
	}
	
	public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                               + spinner.getEditor().getClass()
                               + " isn't a descendant of DefaultEditor");
            return null;
        }
    }
	
	private boolean waitlisted(String pRoom, Day pDate, Time pStart, Time pEnd) {
		Time tStart = pStart.Clone();
		Time tEnd = pEnd.Clone();
		int counter = 0, cMax = 0;

		if(pRoom.equals("Small Party Room")) {
			cMax = 10;
			tStart.sub(0,  30);
			tEnd.add(0,  30);
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
		
		else if(pRoom.equals("Medium Party Room")) {
			cMax = 2;
			tStart.sub(1,  0);
			tEnd.add(1,  0);
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
		
		else if(pRoom.equals("Karaoke Lounge")) {
			cMax = 10;
			tStart.sub(0,  15);
			tEnd.add(0,  15);
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
		
		else if(pRoom.equals("Billiards Lounge")) {
			cMax = 5;
			tStart.sub(0, 15);
			tEnd.add(0, 15);
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
		
		else if(pRoom.equals("Aqua World")) {
			cMax = 1;
			tStart.sub(1, 0);
			tEnd.add(1, 0);
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
		
		if(counter >= cMax) { return true; }
		return false;
	}
	
}