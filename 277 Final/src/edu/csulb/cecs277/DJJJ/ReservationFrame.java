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

public class ReservationFrame extends JFrame {
	
	private JLabel options;
	
	private JButton coffeeButton;
	private JButton teaButton;
	private JButton pastryButton;
	private JButton doneButton;
		
	private JScrollPane receiptPane;
	
	private JTextArea receiptText;
	
	private Receipt mReceipt;
	
		
	/**
	 * Constructs an initial frame for the order to run on
	 */
	public InitialFrame() {
		createComponents();
		mReceipt = new Receipt();
		this.setTitle("New Order");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Updates the frame to show changes to the order
	 */
	public void update() {
		doneButton.setVisible(true);
		if (!mReceipt.isEmpty()) {
			receiptText.setText(mReceipt.CreateFull());
			receiptText.setVisible(true);
			receiptPane.setVisible(true);
		}
		iF.repaint();
	}
	
	/**
	 * Creates the initial frame with all the desired components
	 */
	private void createComponents() {
		options = new JLabel("Select an item to purchase: ");
		receiptText = new JTextArea();
		receiptText.setEditable(false);
		receiptText.setVisible(false);
		
		receiptPane = new JScrollPane(receiptText);
		receiptPane.setPreferredSize(new Dimension(600, 350));
		receiptPane.setVisible(true);
		
		
		coffeeButton = new JButton("Coffee");
		ActionListener coffeeListener = new CoffeeButtonListener();
		coffeeButton.addActionListener(coffeeListener);
		
		teaButton = new JButton("Tea");
		ActionListener teaListener = new TeaButtonListener();
		teaButton.addActionListener(teaListener);
		
		pastryButton = new JButton("Pastry");
		ActionListener pastryListener = new PastryButtonListener();
		pastryButton.addActionListener(pastryListener);
		
		doneButton = new JButton("Done");
		ActionListener doneListener = new DoneButtonListener();
		doneButton.addActionListener(doneListener);
		doneButton.setVisible(false);
				
		JPanel initialPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		JPanel receiptPanel = new JPanel();
		JPanel donePanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		donePanel.setSize(20,40);
		
		buttonPanel.add(options, BorderLayout.LINE_START);
		buttonPanel.add(coffeeButton, BorderLayout.PAGE_START);
		buttonPanel.add(teaButton,BorderLayout.PAGE_START);
		buttonPanel.add(pastryButton);
		
		receiptPanel.add(receiptPane);
		
		donePanel.add(doneButton);
		
		initialPanel.add(buttonPanel, BorderLayout.NORTH);
		initialPanel.add(leftPanel, BorderLayout.WEST);
		initialPanel.add(rightPanel, BorderLayout.EAST);
		
		leftPanel.setPreferredSize(new Dimension(40,350));
		rightPanel.setPreferredSize(new Dimension(40,350));
		
		initialPanel.add(receiptPanel, BorderLayout.CENTER);
		initialPanel.add(donePanel, BorderLayout.PAGE_END);
		
		this.add(initialPanel);		
	}
	
	/**
	 * Creates the Coffee Button Listener
	 * @author Jonathan
	 *
	 */
	class CoffeeButtonListener implements ActionListener {
		
		/**
		 * Creates a new Coffee Order frame and makes it visible
		 */
		public void actionPerformed(ActionEvent click) {
			CoffeeOrderFrame cF = new CoffeeOrderFrame(iF, mReceipt);
			cF.setVisible(true);
		}
	}
	
	/**
	 * Creates the Tea Button Listener
	 * @author Jonathan
	 *
	 */
	class TeaButtonListener implements ActionListener {
		
		/**
		 * Creates a new Tea Order frame and makes it visible
		 */
		public void actionPerformed(ActionEvent click) {
			TeaOrderFrame tF = new TeaOrderFrame(iF, mReceipt);
			tF.setVisible(true);
		}
	}
	
	/**
	 * Creates the Pastry Button Listener
	 * @author Jonathan
	 *
	 */
	class PastryButtonListener implements ActionListener {
		
		/**
		 * Creates a new Pastry Order frame and makes it visible
		 */
		public void actionPerformed(ActionEvent click) {
			PastryOrderFrame pF = new PastryOrderFrame(iF, mReceipt);
			pF.setVisible(true);
		}
	}
	
	/**
	 * Creates the Done Button Listener
	 * @author Jonathan
	 *
	 */
	class DoneButtonListener implements ActionListener {
		
		/**
		 * If the order is not empty, Creates a finalize Order 
		 * frame and makes it visible, otherwise it closes the 
		 * program
		 */
		public void actionPerformed(ActionEvent click) {
			if (!mReceipt.isEmpty()) {
				FinalizeOrderFrame fF = new FinalizeOrderFrame(mReceipt);
				fF.setVisible(true);
			}
			else {
				System.exit(0);
			}
		}
	}
	
	/**
	 * returns the receipt of the order
	 * @return
	 */
	public Receipt getMReceipt() {
		return mReceipt;
	}
	
	/**
	 * Runs the program
	 */
	public static void main (String[] args) {
		iF = new InitialFrame();
		iF.setVisible(true);
	}
	
}

