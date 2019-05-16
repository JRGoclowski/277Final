package edu.csulb.cecs277.DJJJ;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
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


public class ReservationFrame {
	

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
			// TODO Auto-generated method stub

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

	private JLabel mContactByHead, mContactPhoneInd, mContactEmailInd;
	
	private JTextField mGuestNameTF, mGuestPhoneTF, mGuestAddressTF, mGuestDOBMTF, mGuestDOBDTF, mGuestDOBYTF, mGuestEmailTF;
	private JTextField mCCNameTF, mCCNumberTF, mCCSecurityTF, mCCExpirationTF;
	private JTextField mRoomDateMTF, mRoomDateDTF, mRoomDateYTF;
	
	private JComboBox<String> mRoomTypeCB, mRoomTimeCB, mMealPlanCB; 
	
	private JButton mEditMealB, mSaveB, mCancelB, mDeleteB;
	
	private JPanel mFrameP, mGuestP, mCreditCardP, mRoomP, mMealPlanP, mContactP, mButtonP; 
	
	private boolean mMealEditted;
	
	private MealPlan mMealPlan;
	
	
	public ReservationFrame() {
		InitializeGeneralComponents();
	}
	
	public ReservationFrame(Reservation pReservation) {
		InitializeGeneralComponents();
		FillByReservation(pReservation);
	}
	
	private void InitializeGeneralComponents() {
		InitializeGuestPanel();
		InitializeCCPanel();
		InitializeRoomPanel();
		InitializeMealPanel();
		InitializeContactPanel();
		InitializeButtonPanel();
		InitializeFullPanel();
	}
	
	private void InitializeFullPanel() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	
	//private JButton mEditMealB,
	private void InitializeMealPanel() {
		mMealPlanP = new JPanel();
		
		mMealPlanP.add(new JLabel("Meal Plan -"));
		
		mMealPlanP
		
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
		String[] times = (String[])timeStrings.toArray();
		mRoomTypeCB = new JComboBox<String>(times);
		mRoomP.add(mRoomTypeCB);
		
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

	private void FillByReservation(Reservation pReservation) {
		
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
