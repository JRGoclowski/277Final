package edu.csulb.cecs277.DJJJ;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class EditReservationFrame extends JFrame {
	
	/*
		 *Must first take either Guest Name or confirmation number
		 *Pull up new reservation Frame with instantiated values, plus delete button
		 * 
		 */
	
	public static void main(String[] args) {
		EditReservationFrame ERF = new EditReservationFrame();
		ERF.setVisible(true);
	}
	

	private JPanel mFrameP, mConfirmationP, mNameP, mButtonP;
	
	private JTextField mConfirmationTF, mNameTF;
	
	private JButton mSearchB, mCancelB;
	
	public EditReservationFrame() {
		this.setTitle("Edit Reservation");
		this.setSize(350,300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		AddElements();
		this.setVisible(true);
	}
	
	private void AddElements() {
		mFrameP = new JPanel();
		
		mConfirmationP = new JPanel();
		
		mConfirmationP.add(new JLabel("Confirmation Number:"));
		
		mConfirmationTF = new JTextField("Confirmation #");
		mConfirmationTF.setColumns(15);
		mConfirmationP.add(mConfirmationTF);
		
		mFrameP.add(mConfirmationP);
		
		mNameP = new JPanel();
		
		mNameP.add(new JLabel("Guest Name:"));
		
		mNameTF = new JTextField("Guest Name");
		mNameTF.setColumns(15);
		mNameP.add(mNameTF);
		
		mFrameP.add(mNameP);
		
		mButtonP = new JPanel();
		
		mSearchB = new JButton("Search");
		ActionListener SearchListener = new SearchButtonListener();
		mSearchB.addActionListener(SearchListener);
		
		mCancelB = new JButton("Cancel");
		ActionListener CancelListener = new CancelButtonListener();
		mCancelB.addActionListener(CancelListener);
		
		mButtonP.add(mSearchB);
		mButtonP.add(mCancelB);
		
		mFrameP.add(mButtonP);
		
		mFrameP.setVisible(true);
		this.add(mFrameP);
		
	}
	
	private Reservation GetByName() {
		return null;
	}
	
	private Reservation GetByConfirmatiion() {
		return null;
	}
	
	public class UnfoundErrorFrame extends JFrame {
		
		public UnfoundErrorFrame() {
			this.setTitle("Error");
			this.setSize(250,150);
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			this.add(new JLabel("No Reservation by that Index found"));
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
	
	public class SearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Reservation lFoundRes = null;
			if (!mConfirmationTF.getText().equals("Confirmation #")) {
				lFoundRes = RoomList.getmRoomList().FindReservation(Integer.parseInt(mConfirmationTF.getText()));
				if (lFoundRes != null) {
					ReservationFrame editResFrame = new ReservationFrame(lFoundRes);
					editResFrame.setVisible(true);
					return;
				}
			}
			else if (!mNameTF.getText().equals("Guest Name")) {
				lFoundRes = RoomList.getmRoomList().FindReservation(mNameTF.getText());
				if (lFoundRes != null) {
					ReservationFrame editResFrame = new ReservationFrame(lFoundRes);
					editResFrame.setVisible(true);
					return;
				}
			}			
			else {
				UnfoundErrorFrame UEF = new UnfoundErrorFrame();
				UEF.setVisible(true);
			}
		}

	}
}
