package edu.csulb.cecs277.DJJJ;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//This action listeners creates a panel according to whether the user clicked check in or out 
	class checkInorOut implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent click) 
		{
			//_______________________________________________________________________________________initial startup
			//Gets what the user clicked, either check in or check out 
			JMenuItem menuItem = (JMenuItem) click.getSource();
			String checkInOrOut = menuItem.getText();
			
			//Creates the frame and panal
			JFrame frame = new JFrame(checkInOrOut);
			JPanel panel = new JPanel();
			
			//sets the frame set and set the visibility to true
			frame.setSize(800, 600);
			frame.setVisible(true);
			
			//Sets it so the frame is in the middle of the screen
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
//_______________________________________________________________________________________________________
			//Make the panel
			DefaultListModel demoList = new DefaultListModel();
			JList lists = new JList(demoList);
    
			JScrollPane list = new JScrollPane(lists);
    		list.setPreferredSize(new Dimension(100,100));
			
//_______________________________________________________________________________________________________________________________
			//This function should edit the guestList with the reservations with the matching
//			//day that the user passed in 
//			//This array list will be used to add the reservation to when the matching date is found
			ArrayList<Reservation> guestReservation = new ArrayList<Reservation>();
			
			ArrayList<Reservation> checkInorOut = new ArrayList<Reservation>();
		
			searchReservationForGuest(guestReservation);
			
			System.out.println(guestReservation.size());
//			
//			//Now that we have the reservation we can add it to the JList of lists
//			//Get each reservation inside the guestList
			for(Reservation res : guestReservation)
			{
				//Adding to the jScrollpane each guest name inside the reservation of that specific day 
				//Selects the guest based on whether or not the check in or check out is false
				if(checkInOrOut.equals("Check-in"))
				{
					//If the reservation is check in 
					if(!res.isCheckedIn())
					{
						demoList.addElement(res.getmGuest().getmName());
						checkInorOut.add(res);
					}
				}
				else if(checkInOrOut.equals("Check-out"))
				{
					if(res.isCheckedIn() && !res.isCheckedOut())
					{
						demoList.addElement(res.getmGuest().getmName());
						checkInorOut.add(res);
					}
				}
				else
				{
					//if the guest is checked out (true) and checked in (true), then not do anything 
				}

			}

			
			
			//_________________________________________________________________________________________________________
			

			//Cancel is used once the user is done checking in or out_________________________________________________
			JButton cancel = new JButton("Cancel");
			cancel.setPreferredSize(new Dimension(80, 50));
			panel.add(cancel);
			
			cancel.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent event)
				{
					frame.setVisible(false);
				}
			});
			//___________________________________________________________________
			

			
			
			
//_____________________________________________________________________________deals with getting the name of the reservation
//making the scrollpane for the list of guest
//TO DO create a function to look through each reservation at the specific day 
//_________________________________________________________________________________________________
			
			
//_____________________________________________________________________________________________Checking in and out the guest
//_______________________________________________________________________________________________________________________
							
							 
							
							
//This JButton is used when the user wants to check in or out a specific guest_______________________________________________
//Now handle it when the user highlists a guest and clicks check in or checks out
							JButton check = new JButton(checkInOrOut);
							check.setPreferredSize(new Dimension(120, 50));
							check.addActionListener(new ActionListener()
							{
							public void actionPerformed(ActionEvent event)
								{
									if(checkInOrOut.equals("Check-in"))
									{
										
										//Now go to the guest's index in the arraylist and change check in to true
										checkInorOut.get(lists.getSelectedIndex()).setCheckedIn(true);
										
										//Furthermore, we can just remove them from the arraylist because the user 
										//will need to go back and click check in and check out again
										checkInorOut.remove(lists.getSelectedIndex());
										
										//Removes the guest's name 
										((DefaultListModel) lists.getModel()).remove(lists.getSelectedIndex());
									}
									else
									{
										//Gets the guest that is hightlighted then prints out the receipt for that particular guest 
										printReceipt(checkInorOut.get(lists.getSelectedIndex()));
										
										
										//Now go to the guest's index in the arraylist and change check out to true
										checkInorOut.get(lists.getSelectedIndex()).setCheckedOut(true);
														
										//Furthermore, we can just remove them from the arraylist because the user 
										//will need to go back and click check in and check out again
										checkInorOut.remove(lists.getSelectedIndex());
										
										//Removes the guest's name 
										((DefaultListModel) lists.getModel()).remove(lists.getSelectedIndex());
										
									}
								}
							});
							
							panel.add(list);
							panel.add(check);
							panel.add(cancel);
							frame.add(panel);
							
							frame.revalidate();
							frame.repaint();
							
		}
							
//_________________________________________________________________________________________________________________________________
							
							
							
							
//______________________________________________________________________________________________________________________________


		
		//This function looks through each room inside roomlist for the matching dates that the user passed in 
		private void searchReservationForGuest(ArrayList<Reservation> guestList)
		{
			for (Room iRoom : RoomList.getmRoomList().getmSmallRooms()) 
			{
				//Each room has a date 
				for (Date iDate : iRoom.getRoomDates()) 
				{
						for(Reservation res : iDate.getmReservations())
						{
							guestList.add(res);
						}
				}
			}
			for (Room iRoom : RoomList.getmRoomList().getmMediumRooms()) 
			{
				//Each room has a date 
				for (Date iDate : iRoom.getRoomDates()) 
				{
						for(Reservation res : iDate.getmReservations())
						{
							guestList.add(res);
						}
				}
			}
			for (Room iRoom : RoomList.getmRoomList().getmBilliardRooms()) 
			{
				//Each room has a date 
				for (Date iDate : iRoom.getRoomDates()) 
				{
						for(Reservation res : iDate.getmReservations())
						{
							guestList.add(res);
						}
				}
			}
			for (Room iRoom : RoomList.getmRoomList().getmKaraokeRooms()) 
			{
				//Each room has a date 
				for (Date iDate : iRoom.getRoomDates()) 
				{
						for(Reservation res : iDate.getmReservations())
						{
							guestList.add(res);
						}
				}
			}
			
			for(Date iDate : RoomList.getmRoomList().getmAquaWorld().getRoomDates())
			{
					for(Reservation res : iDate.getmReservations())
					{
						guestList.add(res);
					}
			}
		}

		
		
		public void printReceipt(Reservation res)
		{
			//TO DO
			//Open up a new frame and ask if the guest has any damages
			//After clicking submit, finally print out the receipt
			//Receipt screen has a close button 
//_________________________________________________________________________________________________________________________
			//Asks the user for any damages
			JFrame frame = new JFrame("Damages");
			JPanel panel = new JPanel();
			
			//sets the frame set and set the visibility to true
			frame.setSize(800, 600);
			frame.setVisible(true);
			
			//Sets it so the frame is in the middle of the screen
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
			
			//Get the room type_____________________________________________________________________________________________________


			
			
			//JLabel and textfields for the damage and cost 
			JLabel damageDescriptionText = new JLabel("Damage: ");
			damageDescriptionText.setFont(new Font("Courier", Font.BOLD,30));
			
			JTextField damagesDescription = new JTextField("none");
			damagesDescription.setPreferredSize(new Dimension(100,25));
			
			JLabel damageCostText = new JLabel("Damage Cost: ");
			damageCostText.setFont(new Font("Courier", Font.BOLD,30));
			
			JTextField damagesCost = new JTextField("0");
			damagesCost.setPreferredSize(new Dimension(100,25));
			double costOfDamage = Double.parseDouble(damagesCost.getText());
			
			JButton submitDamage = new JButton("Submit");
			submitDamage.setPreferredSize(new Dimension(120, 50));
			
			panel.add(damageDescriptionText);
			panel.add(damagesDescription);
			panel.add(damageCostText);
			panel.add(damagesCost);
			
			panel.add(submitDamage);
			
			frame.add(panel);
//___________________________________________________________________________________________________________________________
		
			
			
			
//______________________________________________________________________________________________________________________________
			//prints out the invoice 
			submitDamage.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent event)
				{
					double hourSpent = res.getmFunctionEndTime().getmHours() - res.getmFunctionStartTime().getmHours();
					double minuteSpent = res.getmFullEndTime().getmMinutes() - res.getmFullStartTime().getmMinutes();
					if(minuteSpent == 15)
					{
						minuteSpent = 0.25;
					}
					else if(minuteSpent == 30)
					{
						minuteSpent = 0.50;
					}
					else if(minuteSpent == 45)
					{
						minuteSpent = 0.75;
					}
					else if(minuteSpent == 60)
					{
						minuteSpent = 1;
					}
					
					double timeSpent = (res.getmRoom().getHourlyCost()  * hourSpent) + (res.getmRoom().getHourlyCost() * minuteSpent);
					//Get the description in a string and the cost in an integer 
					String damageDescription = damagesDescription.getText();
					int damageCost = Integer.parseInt(damagesCost.getText());
					
					
					
					//Remove everything from the jpanel and jframe
					panel.removeAll();
					frame.getContentPane().removeAll();
					frame.setTitle("Invoice");
					String mRoomtype;
					if (res.getmRoom() instanceof SmallPartyRoom) {
						mRoomtype = "Small party room";
					}
					else if (res.getmRoom()instanceof MediumPartyRoom) {
						mRoomtype = "Medium party room";
					}
					else if (res.getmRoom()instanceof KaraokeLounge) {
						mRoomtype = "Karaoke Lounge";
					}
					else if (res.getmRoom() instanceof BilliardsLounge) {
						mRoomtype = "Billiards Lounge";
					}
					else
					{
						mRoomtype = "Aqua World";
					}
					
					//____________________________________________________________________________________________________________________________
					
					//______________________________________________________________________________________________________________________
					
					
					//The total cost
					double totalCost = costOfDamage + res.getmRoom().getRoomMealPlan().getCost() + timeSpent;
					//Setting up the pane
					JTextArea textArea = new JTextArea(
						 "                   ----------------------Basic Information----------------------\n"
					+    "Name: " + res.getmGuest().getmName() + "\n"
					+    "Phone Number: " + res.getmGuest().getmPhone() +  "\n"
					+	 "Email: " + res.getmGuest().getmEmail() + "\n"
					+    "Address: " + res.getmGuest().getmAddress() +"\n"
					+    "Credit Card Number : " + res.getmGuest().getmCard().getmNumber() + "\n"
					+    "                  ------------------------Charges-----------------------\n" 
					//Calculate the room type cost 
					+    "Room Type: " + mRoomtype + "... $" + timeSpent + "\n"
					//Gets the room number
					+ 	 "Room Number: " + res.getmRoom().getRoomNumber() + "\n"
					//Get the meal plan
					+    "Meal Plan: " + res.getmRoom().getRoomMealPlan().getPlan() + "... $" + res.getmRoom().getRoomMealPlan().getCost() + "\n"
					//Get the damage cost added
					+    "Damage Info: " + damagesDescription.getText() + "\n"
					//Damage cost
					+	 "Damage Cost: " + costOfDamage + "\n"
					+    "\nTotal Cost: $" + totalCost
						);
					
					textArea.setFont(new Font("Serif", Font.ITALIC, 24));
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);
					
					
					JScrollPane box = new JScrollPane(textArea);
					box.setVerticalScrollBarPolicy(
			                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					box.setPreferredSize(new Dimension(700, 500));
					
					panel.add(box);
					
					
					
					
					
					
					
					
					
					
					//Add the panel to the frame and remake
					frame.add(panel);
					frame.repaint();
					frame.revalidate();
				}
			});
//_________________________________________________________________________________________________________________________
		}
		public double hoursSpent(int hours, int min) {
		       double total = (double) hours;
		       if (min==15) { total+=0.25; }
		       else if (min==30) {total+=0.5; }
		       else if (min==45) {total+=0.75; }
		       else if (min==60) {total+=1; }
		       return total;
		}
	}

	//---------------------------------------------------------------------------------------------------------------------