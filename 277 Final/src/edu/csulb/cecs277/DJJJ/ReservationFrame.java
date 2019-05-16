package edu.csulb.cecs277.DJJJ;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.csulb.cecs277.DJJJ.ReservationFrame.CancelButtonListener;
import edu.csulb.cecs277.DJJJ.ReservationFrame.DeleteButtonListener;
import edu.csulb.cecs277.DJJJ.ReservationFrame.SaveButtonListener;
import edu.csulb.cecs277.DJJJ.ReservationFrame.SaveMealButtonListener;


public class ReservationFrame extends JFrame {
	
	public static void main(String args[]) {
		ReservationFrame lRF = new ReservationFrame();
		lRF.setVisible(true);
	}

	/**
		 * @author Jonathan
		 *
		 */
	public class SaveMealButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			MealFrame lMealFrame = new MealFrame(mResFrame);
			lMealFrame.setVisible(true);
			lMealFrame.setAlwaysOnTop(true);
		}

	}

	/**
		 * @author Jonathan
		 *
		 */
	public class DeleteButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			RoomList lRoomList = RoomList.getmRoomList();
			Room lRoom = mReservation.getmRoom();
			ArrayList<Room> lRooms;
			Date dateToChange = null;
			if (lRoom instanceof SmallPartyRoom) {
				lRooms = lRoomList.getmSmallRooms();
				dateToChange = lRoomList.ReturnDateOfRes(lRooms, mReservation);
			}
			else if (lRoom instanceof MediumPartyRoom) {
				lRooms = lRoomList.getmMediumRooms();
				dateToChange = lRoomList.ReturnDateOfRes(lRooms, mReservation);
			}
			else if (lRoom instanceof KaraokeLounge) {
				lRooms = lRoomList.getmKaraokeRooms();
				dateToChange = lRoomList.ReturnDateOfRes(lRooms, mReservation);
			}
			else if (lRoom instanceof BilliardsLounge) {
				lRooms = lRoomList.getmBilliardRooms();
				dateToChange = lRoomList.ReturnDateOfRes(lRooms, mReservation);
			}
			else {
				lRooms = null;
				for (Date iDate : lRoomList.getmAquaWorld().getRoomDates()) {
					if (mReservation.getmDay().equals(iDate.getmDay())){
						dateToChange = iDate;
					}
				}
			}
			dateToChange.removeReservation(mReservation);
			lRoomList.notify();
		}
	}

	public class CancelButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	/**
		 * @author Jonathan
		 *
		 */
	public class SaveButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
	
	private ReservationFrame mResFrame;
	private JTextField mGuestNameTF, mGuestPhoneTF, mGuestAddressTF, mGuestDOBMTF, mGuestDOBDTF, mGuestDOBYTF, mGuestEmailTF;
	private JTextField mCCNameTF, mCCNumberTF, mCCSecurityTF, mCCExpirationTF;
	private JTextField mRoomDateMTF, mRoomDateDTF, mRoomDateYTF;
	
	private JCheckBox mPhoneC, mEmailC;
	
	private JComboBox<String> mRoomTypeCB, mRoomStartTimeCB, mRoomEndTimeCB, mMealPlanCB; 
	
	private JButton mSaveMealB, mSaveB, mCancelB, mDeleteB;
	
	private JPanel mFrameP, mGuestP, mCreditCardP, mRoomP, mMealPlanP, mContactP, mButtonP; 
	
	private boolean mMealEditted, isEdit;
	
	private MealPlan mMealPlan;
	
	private Reservation mReservation;
	
	
	public ReservationFrame() {
		this.setTitle("Reservation");
		this.setSize(1250,300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		InitializeGeneralComponents();
		mMealEditted = false;
		mResFrame = this;
	}
	
	public ReservationFrame(Reservation pReservation) {
		mReservation = pReservation;
		this.setTitle("Reservation");
		this.setSize(1250,300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		InitializeGeneralComponents();
		mMealEditted = false;
		isEdit = true;
		mDeleteB.setVisible(true);
		FillByReservation(mReservation);
		mResFrame = this;
	}
	
	private void InitializeGeneralComponents() {
		InitializeGuestPanel();
		InitializeCCPanel();
		InitializeRoomPanel();
		InitializeMealPanel();
		InitializeContactPanel();
		InitializeButtonPanel();
		InitializeFullPanel();
		this.add(mFrameP);
	}
	
	//private JPanel mFrameP, mGuestP, mCreditCardP, mRoomP, mMealPlanP, mContactP, mButtonP; 
	private void InitializeFullPanel() {
		mFrameP = new JPanel();
		mFrameP.add(mGuestP);
		mFrameP.add(mCreditCardP);
		mFrameP.add(mRoomP);
		mFrameP.add(mMealPlanP);
		mFrameP.add(mContactP);
		mFrameP.add(mButtonP);
	}

	private void InitializeButtonPanel() {
		mButtonP = new JPanel();
		
		mSaveB = new JButton("Save");
		mSaveB.setVisible(true);
		ActionListener saveListener = new SaveButtonListener();
		mSaveB.addActionListener(saveListener);
		
		
		mCancelB = new JButton("Cancel");
		mCancelB.setVisible(true);
		ActionListener cancelListener = new CancelButtonListener();
		mCancelB.addActionListener(cancelListener);		
		
		mDeleteB = new JButton("Delete");
		mDeleteB.setVisible(false);
		ActionListener DeleteListener = new DeleteButtonListener();
		mDeleteB.addActionListener(DeleteListener);
		
		mButtonP.add(mSaveB);
		mButtonP.add(mCancelB);
		mButtonP.add(mDeleteB);
		
	}

	private void InitializeContactPanel() {
		mContactP = new JPanel();
		
		mContactP.add(new JLabel("Contact Method:"));
		
		mPhoneC = new JCheckBox();
		mContactP.add(mPhoneC);
		mContactP.add(new JLabel("By Phone"));
		
		mEmailC = new JCheckBox();
		mContactP.add(mEmailC);
		mContactP.add(new JLabel("By Email"));
		
		
		
	}
	
	//private JButton mEditMealB,
	private void InitializeMealPanel() {
		mMealPlanP = new JPanel();
		
		mMealPlanP.add(new JLabel("Meal Plan -"));
		
		String[] plans = {"Basic", "Bronze", "Silver", "Gold", "Platinum"};
		mMealPlanCB = new JComboBox<String>(plans);
		
		mMealPlanP.add(mMealPlanCB);
		
		mSaveMealB = new JButton("Save/Edit Plan");
		mSaveMealB.setVisible(true);
		ActionListener saveMealListener = new SaveMealButtonListener();
		mSaveMealB.addActionListener(saveMealListener);
		
		mMealPlanP.add(mSaveMealB);
		
	}
	
	//private JLabel mRoomHead, mTypeInd, mDateInd, mTimeInd;
	//private JTextField mRoomDateMTF, mRoomDateDTF, mRoomDateYTF;
	//	private JComboBox<String> mRoomTypeCB, mRoomTimeCB,
	private void InitializeRoomPanel() {
		mRoomP = new JPanel();
		
		mRoomP.add(new JLabel("Room Info - "));
		
		mRoomP.add(new JLabel("Room Type:"));
		String[] roomTypes = {"Small Party Room", "Medium Party Room", "Karaoke Lounge", "Billiards Room", "Aqua World"};
		mRoomTypeCB = new JComboBox<String>(roomTypes);
		mRoomP.add(mRoomTypeCB);
		
		mRoomP.add(new JLabel("Date:"));
		mRoomDateMTF = new JTextField("M");
		mRoomDateMTF.setColumns(5);
		mRoomDateDTF = new JTextField("D");
		mRoomDateDTF.setColumns(5);
		mRoomDateYTF = new JTextField("Y");
		mRoomDateYTF.setColumns(5);
		mRoomP.add(mRoomDateDTF);
		mRoomP.add(mRoomDateMTF);
		mRoomP.add(mRoomDateYTF);
		
		
		mRoomP.add(new JLabel("Time:"));
		ArrayList<String> timeStrings = new ArrayList<String>();
		for (Time iTime: Time.ALL_TIMES) {
			timeStrings.add(iTime.toString());
		}
		String[] times = new String [timeStrings.size()];
		for (int i = 0; i < timeStrings.size(); i++) {
			times [i] = timeStrings.get(i);
		}
		mRoomStartTimeCB = new JComboBox<String>(times);
		mRoomEndTimeCB = new JComboBox<String>(times);
		mRoomP.add(new JLabel("Start:"));
		mRoomP.add(mRoomStartTimeCB);
		mRoomP.add(new JLabel("End:"));
		mRoomP.add(mRoomEndTimeCB);
		
	}

	//private JLabel mCreditCardHead, mCCNameInd, mCCNumberInd, mCCSecurityInd,  mCCExpireInd;
	//private JTextField mCCNameTF, mCCNumberTF, mCCSecurityTF, mCCExpirationTF;
	private void InitializeCCPanel() {
		mCreditCardP = new JPanel();
		
		mCreditCardP.add(new JLabel ("Credit Card -"));
		
		mCreditCardP.add(new JLabel("Name:"));
		mCCNameTF = new JTextField();
		mCCNameTF.setColumns(15);
		mCreditCardP.add(mCCNameTF);
		
		mCreditCardP.add(new JLabel("Number:"));
		mCCNumberTF = new JTextField();
		mCCNumberTF.setColumns(15);
		mCreditCardP.add(mCCNumberTF);
		
		mCreditCardP.add(new JLabel("Security Code:"));
		mCCSecurityTF = new JTextField();
		mCCSecurityTF.setColumns(15);
		mCreditCardP.add(mCCSecurityTF);
		
		mCreditCardP.add(new JLabel("Expiration:"));
		mCCExpirationTF = new JTextField();
		mCCExpirationTF.setColumns(15);
		mCreditCardP.add(mCCExpirationTF);
		
	}
	
	//private JTextField mGuestNameTF, mGuestPhoneTF, mGuestAddressTF, mGuestDOBMTF, mGuestDOBDTF, mGuestDOBYTF, mGuestEmailTF;
	private void InitializeGuestPanel() {
		mGuestP = new JPanel();		
		
		mGuestP.add(new JLabel ("Guest Info - "));
		
		mGuestP.add(new JLabel("Name:"));
		mGuestNameTF = new JTextField();
		mGuestNameTF.setColumns(15);
		mGuestP.add(mGuestNameTF);
		
		mGuestP.add(new JLabel("Phone"));
		mGuestPhoneTF = new JTextField();
		mGuestPhoneTF.setColumns(15);
		mGuestP.add(mGuestPhoneTF);
		
		mGuestP.add(new JLabel("Address:"));
		mGuestAddressTF = new JTextField();
		mGuestAddressTF.setColumns(15);
		mGuestP.add(mGuestAddressTF);
		
		mGuestP.add(new JLabel("DOB:"));
		mGuestDOBMTF = new JTextField("M");
		mGuestDOBMTF.setColumns(5);
		mGuestP.add(mGuestDOBMTF);
		mGuestDOBDTF = new JTextField("D");
		mGuestDOBDTF.setColumns(5);
		mGuestP.add(mGuestDOBDTF);
		mGuestDOBYTF = new JTextField("Y");
		mGuestDOBYTF.setColumns(5);
		mGuestP.add(mGuestDOBYTF);
		
		mGuestP.add(new JLabel("Email:"));
		mGuestEmailTF = new JTextField();
		mGuestEmailTF.setColumns(15);
		mGuestP.add(mGuestEmailTF);		
	}
	
	/*
	 *  mGuestNameTF, mGuestPhoneTF, mGuestAddressTF, mGuestDOBMTF, mGuestDOBDTF, mGuestDOBYTF, mGuestEmailTF;
	private JTextField mCCNameTF, mCCNumberTF, mCCSecurityTF, mCCExpirationTF;
	private JTextField mRoomDateMTF, mRoomDateDTF, mRoomDateYTF;
	
	private JCheckBox mPhoneC, mEmailC;
	
	private JComboBox<String> mRoomTypeCB, mRoomTimeCB, mMealPlanCB; 
	
	private JButton mSaveMealB, mSaveB, mCancelB, mDeleteB;
	
	private JPanel mFrameP, mGuestP, mCreditCardP, mRoomP, mMealPlanP, mContactP, mButtonP; 
	
	private boolean mMealEditted;
	
	private MealPlan mMealPlan;
	 */
	private void FillByReservation(Reservation pReservation) {
		Guest lGuest = pReservation.getmGuest();
		Room lRoom = pReservation.getmRoom();
		MealPlan lMeal = pReservation.getmMealPlan();
		
		mGuestNameTF.setText(lGuest.getmName());
		mGuestPhoneTF.setText(lGuest.getmPhone()); 
		mGuestAddressTF.setText(lGuest.getmAddress());
		mGuestDOBMTF.setText(Integer.toString(lGuest.getmDOB().getmMonthNumeral()));
		mGuestDOBDTF.setText(Integer.toString(lGuest.getmDOB().getmDayNumeral()));
		mGuestDOBYTF.setText(Integer.toString(lGuest.getmDOB().getmYearNumeral()));
		mGuestEmailTF.setText(lGuest.getmEmail());
		mCCNameTF.setText(lGuest.getmCard().getmName());
		mCCNumberTF.setText(lGuest.getmCard().getmNumber());
		mCCSecurityTF.setText(lGuest.getmCard().getmSecurityCode());
		mCCExpirationTF.setText(lGuest.getmCard().getmExpiration());
		mRoomDateMTF.setText(Integer.toString(pReservation.getmDay().getmMonthNumeral()));
		mRoomDateDTF.setText(Integer.toString(pReservation.getmDay().getmDayNumeral()));
		mRoomDateYTF.setText(Integer.toString(pReservation.getmDay().getmYearNumeral()));
		
		if (lGuest.isNotifyEmail()) {
			mEmailC.setSelected(true);
		}
		if (lGuest.isNotifyPhone()) {
			mPhoneC.setSelected(true);
		}
		
		//"Small Party Room", "Medium Party Room", "Karaoke Lounge", "Billiards Room", "Aqua World"
		int lRoomIndex;
		if (lRoom instanceof SmallPartyRoom) {
			lRoomIndex = 0 ;
		}
		else if (lRoom instanceof MediumPartyRoom) {
			lRoomIndex = 1;
		}
		else if (lRoom instanceof KaraokeLounge) {
			lRoomIndex = 2;
		}
		else if (lRoom instanceof BilliardsLounge) {
			lRoomIndex = 3;
		}
		else {
			lRoomIndex = 4;
		}
		
		mRoomTypeCB.setSelectedIndex(lRoomIndex);
		mRoomStartTimeCB.setSelectedItem(pReservation.getmFullStartTime().toString());
		int lMealIndex;
		if (lMeal instanceof BasicPlan) {
			lMealIndex = 0 ;
		}
		else if (lMeal instanceof BronzePlan) {
			lMealIndex = 1;
		}
		else if (lMeal instanceof SilverPlan) {
			lMealIndex = 2;
		}
		else if (lMeal instanceof GoldPlan) {
			lMealIndex = 3;
		}
		else {
			lMealIndex = 4;
		}
		mMealPlanCB.setSelectedIndex(lMealIndex);
		mMealEditted = false;
	}

	/**
	 * @return the mMealEditted
	 */
	public boolean ismMealEditted() {
		return mMealEditted;
	}

	/**
	 * @param mMealEditted the mMealEditted to set
	 */
	public void setmMealEditted(boolean mMealEditted) {
		this.mMealEditted = mMealEditted;
	}

	/**
	 * @return the mMealPlan
	 */
	public MealPlan getmMealPlan() {
		return mMealPlan;
	}

	/**
	 * @param mMealPlan the mMealPlan to set
	 */
	public void setmMealPlan(MealPlan mMealPlan) {
		this.mMealPlan = mMealPlan;
	}

	/**
	 * @return the mMealPlanCB
	 */
	public JComboBox<String> getmMealPlanCB() {
		return mMealPlanCB;
	}
	
	
	/*
	 * Contains - 
	 * Guest info: name, phone, address, DOB, email - must check DOB for adult room
	 * Credit Info - name, number, securit code, expiration
	 * Room info - number, date, time. If coming from Date time, auto fill
	 * Meal Plan Info
	 * Check box for if contacted by phone or email
	 * Save or cancel
	 * on save, if valid, show confirmation; if not, return where on waitlist it is
	 * 
	 */
	
}
