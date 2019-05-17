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
	
	public class ErrorFrame extends JFrame {
		
		public ErrorFrame() {
			this.setTitle("Error");
			this.setSize(250,300);
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			this.add(new JLabel("There were some changes that were not supported"));
		}
	}
	
	public class FinishedFrame extends JFrame {
		
		public FinishedFrame() {
			this.setTitle("Success!");
			this.setSize(250,300);
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			this.add(new JLabel("Finish this implementation"));
		}
	}

	public static void main(String args[]) {
		ReservationFrame lRF = new ReservationFrame(false);
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
			Room lRoom = mOriginalReservation.getmRoom();
			Date dateToChange = null;
			if (!(lRoom instanceof AquaWorld)) {
				dateToChange = lRoomList.ReturnDateOfRes(mOriginalReservation);
			}
			else {
				for (Date iDate : lRoomList.getmAquaWorld().getRoomDates()) {
					if (mOriginalReservation.getmDay().equals(iDate.getmDay())){
						dateToChange = iDate;
					}
				}
			}
			dateToChange.removeReservation(mOriginalReservation);
			lRoomList.notify();
		}
	}

	public class CancelButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
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
			int lDOBM = Integer.parseInt(mGuestDOBMTF.getText()), 
					lDOBD = Integer.parseInt(mGuestDOBDTF.getText()),
					lDOBY = Integer.parseInt(mGuestDOBYTF.getText());
			Day lDOBDay = new Day(lDOBM, lDOBD, lDOBY);
			int lAddM = Integer.parseInt(mRoomDateDTF.getText()), 
					lAddD = Integer.parseInt(mRoomDateMTF.getText()),
					lAddY= Integer.parseInt(mRoomDateYTF.getText());
			Day lAddDay = new Day(lAddM, lAddD, lAddY);
			Time startTime = Time.GetTimeFromString(mRoomStartTimeCB.getSelectedItem().toString());
			Time endTime = Time.GetTimeFromString(mRoomEndTimeCB.getSelectedItem().toString());
			CreditCard lAddCard = new CreditCard(mCCNameTF.getText(), mCCNumberTF.getText(), mCCSecurityTF.getText(), mCCExpirationTF.getText());
			Guest lAddGuest = new Guest(mGuestPhoneTF.getText(), mGuestEmailTF.getText(), mGuestNameTF.getText(), mGuestAddressTF.getText(), lAddCard);
			lAddGuest.setmDOB(lDOBDay);
			Reservation lAddRes = new Reservation(startTime , endTime, lAddDay, GetRoomType(mRoomTypeCB.getSelectedItem().toString()), lAddGuest);
			if (isEdit) {
				if (!AdjustChanged(mOriginalReservation)) {
					ErrorFrame EF = new ErrorFrame();
					EF.setVisible(true);
				}
			}
			if (isWaitList) {
				Waitlist.getmWaitlist().addToWaitList(lAddRes);
				int lWaitlistPos = Waitlist.getmWaitlist().getWaitListReservation().size();
				return;
			}
			if (mRoomTypeCB.getSelectedItem().toString().equals("Billiards Lounge")) {
				if (!lAddGuest.getmDOB().DOBTwentyOneBy(lAddDay)) {
					//TODO handle not making reservation
				}
			}
			RoomList.getmRoomList().PlaceReservation(lAddRes);
			//TODO IF THE RESRVATION IS FOR BILLIARDS MUST BE OLD ENOUGH
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
	
	private boolean mMealEditted, isWaitList, isEdit, mDayChanged, mTimeChanged, mRoomChanged;
	
	private MealPlan mMealPlan;
	
	private Reservation mOriginalReservation;
	
	
	public ReservationFrame(boolean pWaitList) {
		this.setTitle("Reservation");
		this.setSize(1250,300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		InitializeGeneralComponents();
		mMealEditted = false;
		isWaitList = pWaitList;
		mResFrame = this;
	}
	
	public ReservationFrame(Reservation pReservation) {
		mOriginalReservation = pReservation;
		this.setTitle("Reservation");
		this.setSize(1250,300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		InitializeGeneralComponents();
		mMealEditted = false;
		isEdit = true;
		mDeleteB.setVisible(true);
		FillByReservation(mOriginalReservation);
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
	
	private void InitializeRoomPanel() {
		mRoomP = new JPanel();
		
		mRoomP.add(new JLabel("Room Info - "));
		
		mRoomP.add(new JLabel("Room Type:"));
		String[] roomTypes = {"Small Party Room", "Medium Party Room", "Karaoke Lounge", "Billiards Lounge", "Aqua World"};
		mRoomTypeCB = new JComboBox<String>(roomTypes);
		mRoomP.add(mRoomTypeCB);
		
		mRoomP.add(new JLabel("Date:"));
		mRoomDateMTF = new JTextField("M");
		mRoomDateMTF.setColumns(5);
		mRoomDateDTF = new JTextField("D");
		mRoomDateDTF.setColumns(5);
		mRoomDateYTF = new JTextField("Y");
		mRoomDateYTF.setColumns(5);
		mRoomP.add(mRoomDateMTF);
		mRoomP.add(mRoomDateDTF);
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
	
	private boolean AdjustChanged(Reservation pReservation) {
		Guest lGuest = pReservation.getmGuest();
		Room lRoom = pReservation.getmRoom();
		MealPlan lMeal = pReservation.getmMealPlan();
		CreditCard lCC = lGuest.getmCard();
		int lDOBM = Integer.parseInt(mGuestDOBMTF.getText()), 
				lDOBD = Integer.parseInt(mGuestDOBDTF.getText()),
				lDOBY = Integer.parseInt(mGuestDOBYTF.getText());
		Day lDOBDay = new Day(lDOBM, lDOBD, lDOBY);
		RoomList lRoomList = RoomList.getmRoomList();
		//Name Changed
		if (!lGuest.getmName().equals(mGuestNameTF.getText())) {
			lGuest.setmName(mGuestNameTF.getText());
		}
		//Phone Changed
		if (!lGuest.getmPhone().equals(mGuestPhoneTF.getText())) {
			lGuest.setmPhone(mGuestPhoneTF.getText());
		}
		//Address Changed
		if (!lGuest.getmAddress().equals(mGuestAddressTF.getText())) {
			lGuest.setmAddress(mGuestAddressTF.getText());
		}
		//DOB Changed
		if (!lGuest.getmDOB().equals(lDOBDay)){
			if (ChangeDOBViable(pReservation, lDOBDay)) {
				lGuest.setmDOB(lDOBDay);
			}
			else {
				return false;
			}
		}
		//Email Changed
		if (!lGuest.getmEmail().equals(mGuestEmailTF.getText())) {
			lGuest.setmEmail(mGuestEmailTF.getText());
		}
		//CC Name Changed
		if (!lCC.getmName().equals(mCCNameTF.getText())){
			lCC.setmName(mCCNameTF.getText());
		}
		//CC Number Changed
		if (!lCC.getmNumber().equals(mCCNumberTF.getText())){
			lCC.setmNumber(mCCNumberTF.getText());
		}
		//CC Security Code Changed
		if (!lCC.getmSecurityCode().equals(mCCSecurityTF.getText())){
			lCC.setmSecurityCode(mCCSecurityTF.getText());
		}
		//CC Expiration Changed
		if (!lCC.getmExpiration().equals(mCCExpirationTF.getText())){
			lCC.setmExpiration(mCCExpirationTF.getText());
		}
		//Room Changed (make sure to handle adult)
		if (RoomChanged(pReservation)) {
			Room lNewRoom = GetViableRoomChangeRoom(pReservation, GetRoomTypeList(mRoomTypeCB.getSelectedItem().toString()));
			if (lNewRoom != null) {
				Date lNewDate = GetViableRoomChangeDate(pReservation, lNewRoom);
				RoomList.getmRoomList().ReturnDateOfRes(pReservation).removeReservation(pReservation);
				lNewDate.addReservation(pReservation);
				pReservation.setmRoom(lNewRoom);
			}
		}
		//{"Small Party Room", "Medium Party Room", "Karaoke Lounge", "Billiards Lounge", "Aqua World"};
		
		//Date Changed mRoomDateMTF, mRoomDateDTF, mRoomDateYTF;
		int lNewM = Integer.parseInt(mRoomDateDTF.getText()), 
				lNewD = Integer.parseInt(mRoomDateMTF.getText()),
				lNewY= Integer.parseInt(mRoomDateYTF.getText());
		Day lNewDay = new Day(lNewM, lNewD, lNewY);
		if (!pReservation.getmDay().equals(lNewDay)){
			if (ChangeDayIfViable(pReservation, lNewDay, lRoomList.GetRoomsSameAs(pReservation))) {
				lRoomList.ReturnDateOfRes(pReservation).removeReservation(pReservation);
			}
		}
		//Time Changed
		if (!pReservation.getmFunctionStartTime().toString().equals(mRoomStartTimeCB.getSelectedItem().toString())){
			if (!ChangeStartTime(pReservation, Time.GetTimeFromString(mRoomStartTimeCB.getSelectedItem().toString()))) {
				
			}
		}
		if (!pReservation.getmFunctionEndTime().toString().equals(mRoomEndTimeCB.getSelectedItem().toString())){
			if (!ChangeEndTime(pReservation, Time.GetTimeFromString(mRoomEndTimeCB.getSelectedItem().toString()))) {
				
			}
		}
		return true;
	}
	
	private boolean ChangeDOBViable(Reservation pReservation, Day pDay) {
		if ((!pReservation.getmGuest().getmDOB().DOBTwentyOneBy(pDay)) && mRoomTypeCB.getSelectedItem().toString() == "Billiards Lounge"){
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean RoomChanged(Reservation pReservation) {
		Room lRoom = pReservation.getmRoom();
		return ((lRoom instanceof SmallPartyRoom) && (!mRoomTypeCB.getSelectedItem().toString().equals("Small Party Room"))
				|| (lRoom instanceof MediumPartyRoom) && (!mRoomTypeCB.getSelectedItem().toString().equals("Medium Party Room"))
				|| (lRoom instanceof KaraokeLounge) && (!mRoomTypeCB.getSelectedItem().toString().equals("Karaoke Lounge"))
				|| (lRoom instanceof BilliardsLounge) && (!mRoomTypeCB.getSelectedItem().toString().equals("Billiards Lounge"))
				|| (lRoom instanceof AquaWorld) && (!mRoomTypeCB.getSelectedItem().toString().equals("Aqua World")));
	}
	
	private ArrayList<Room> GetRoomTypeList(String pRoomType){
		RoomList lRoomList = RoomList.getmRoomList();
		switch (pRoomType) {
		case "Small Party Room":
			return lRoomList.getmSmallRooms();
		case "Medium Party Room": 
			return lRoomList.getmMediumRooms();
		case "Karaoke Lounge": 
			return lRoomList.getmKaraokeRooms();
		case "Billiards Lounge": 
			return lRoomList.getmBilliardRooms();
		default:
			return null;
		}
	}
	
	private Room GetRoomType(String pRoomType){
		RoomList lRoomList = RoomList.getmRoomList();
		switch (pRoomType) {
		case "Small Party Room":
			return new SmallPartyRoom();
		case "Medium Party Room": 
			return new MediumPartyRoom();
		case "Karaoke Lounge": 
			return new KaraokeLounge();
		case "Billiards Lounge": 
			return new BilliardsLounge();
		case "Aqua World":
			return lRoomList.getmAquaWorld();
		default:
			return null;
		}
	}
	
	
	
	private Room GetViableRoomChangeRoom(Reservation pReservation, ArrayList <Room> pRooms) {
		for (Room iRoom: pRooms) {
			for (Date iDate : iRoom.getRoomDates()) {
				if (iDate.getmDay().equals(pReservation.getmDay())) {
					if (iDate.isOpen(pReservation)) {
						return iRoom;
					}
				}
			}
		}
		return null;
	}
	
	private Date GetViableRoomChangeDate(Reservation pReservation, Room pRoom) {
		for (Date iDate : pRoom.getRoomDates()) {
			if (iDate.getmDay().equals(pReservation.getmDay())) {
				if (iDate.isOpen(pReservation)) {
					return iDate;
				}
			}
		}
		return null;
	}
	
	private boolean ChangeDayIfViable(Reservation pReservation, Day pDay,  ArrayList <Room> pRooms) {
		for (Room iRoom: pRooms) {
			for (Date iDate : iRoom.getRoomDates()) {
				if (iDate.getmDay().equals(pDay)) {
					if (iDate.isOpen(pReservation)){
						iDate.addReservation(pReservation);
						pReservation.setmRoom(iRoom);
						pReservation.setmDay(pDay);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean ChangeStartTime(Reservation pReservation, Time pTime) {
		Date lDate = RoomList.getmRoomList().ReturnDateOfRes(pReservation);
		return lDate.editReservationStart(pReservation, pTime);
	}
	
	private boolean ChangeEndTime(Reservation pReservation, Time pTime) {
		Date lDate = RoomList.getmRoomList().ReturnDateOfRes(pReservation);
		return lDate.editReservationEnd(pReservation, pTime);
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
