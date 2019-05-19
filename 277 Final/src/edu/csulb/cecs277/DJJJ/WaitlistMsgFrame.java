package edu.csulb.cecs277.DJJJ;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.csulb.cecs277.DJJJ.ReservationFrame.BustedFrame;

public class WaitlistMsgFrame extends JFrame {
	
	private Day date;
	private Time start, end;
	private String room;
	private JButton mAcceptB, mDeclineB;
	private JPanel mWMP, mMainP, mButtonP; 
	
	public WaitlistMsgFrame(Day sDate, Time startTime, Time endTime, String sRoom) {
		date = sDate;
		start = startTime;
		end = endTime;
		room = sRoom;
		
		this.setTitle("Waitlist Message");
		this.setSize(1250,300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		InitializeWaitMsgP();
		InitializeButtonP();
		InitializeMainP();
	}
	
	private void InitializeMainP() {
		mMainP = new JPanel();
		mMainP.add(mWMP);
		mMainP.add(mButtonP);
		this.add(mMainP);
	}
	
	private void InitializeButtonP() {
		mButtonP = new JPanel();
		
		mAcceptB = new JButton("Accept");
		mAcceptB.setVisible(true);
		ActionListener acceptListener = new AcceptButtonListener();
		mAcceptB.addActionListener(acceptListener);
		
		
		mDeclineB = new JButton("Decline");
		mDeclineB.setVisible(true);
		ActionListener declineListener = new DeclineButtonListener();
		mDeclineB.addActionListener(declineListener);		
		
		mButtonP.add(mAcceptB);
		mButtonP.add(mDeclineB);
		
	}
	
	private void InitializeWaitMsgP() {
		mWMP = new JPanel();
		mWMP.add(new JLabel("No availability on selected date/time.  Add to waitlist? "));
	}
	
	public class AcceptButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			ReservationFrame rf = new ReservationFrame(date, end, start, room, true);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			rf.setLocation(dim.width/2-rf.getSize().width/2, dim.height/2-rf.getSize().height/2);
			rf.setVisible(true);
			
			setVisible(false);
		}
	}
	
	public class DeclineButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DateTimeFrame dtf = new DateTimeFrame(room);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			dtf.setLocation(dim.width/2-dtf.getSize().width/2, dim.height/2-dtf.getSize().height/2);
			dtf.setVisible(true);
			
			setVisible(false);
		}
	}
}
