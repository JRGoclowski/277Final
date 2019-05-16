package edu.csulb.cecs277.DJJJ;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
			String item = menuItem.getText();
			
			//Creates the frame and panal
			JFrame frame = new JFrame(item);
			JPanel panel = new JPanel();
			
			//sets the frame set and set the visibility to true
			frame.setSize(800, 600);
			frame.setVisible(true);
			
			//Sets it so the frame is in the middle of the screen
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
			//_______________________________________________________________________________________________________
			
			
			//Adds the text and dropdrop for month day year______________________________________________________
			JLabel monthText = new JLabel("Month: ");
			monthText.setFont(new Font("Courier", Font.BOLD,40));
			JLabel dayText = new JLabel("Day: ");
			dayText.setFont(new Font("Courier", Font.BOLD,40));
			JLabel yearText = new JLabel("Year: ");
			yearText.setFont(new Font("Courier", Font.BOLD,40));
			
			Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
			JComboBox month = new JComboBox(months);
			month.setPreferredSize(new Dimension(100,25));
			
			Integer[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
					20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
			JComboBox day = new JComboBox(days);
			day.setPreferredSize(new Dimension(100,25));
			
			
			month.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent event)
				{
					JComboBox comboBox = (JComboBox) event.getSource();
					int lInput = (int)comboBox.getSelectedItem();
					day.removeAllItems();
					
					if(lInput == 1 || lInput == 3 || lInput == 5 || lInput == 7 || lInput == 8 || lInput == 10 || lInput == 12)
					{
						for(int i = 1; 31 >= i; i ++)
						{
							day.addItem(i);
						}

					}
					else if(lInput == 2)
					{
						for(int i = 1; 28 >= i; i ++)
						{
							day.addItem(i);
						}
					}
					else
					{
						for(int i = 1; 30 >= i; i ++)
						{
							day.addItem(i);
						}
					}

				}
			});
			

			JTextField year = new JTextField("2019");
			year.setPreferredSize(new Dimension(100,25));
			
			
			panel.add(monthText);
			panel.add(month);

			panel.add(dayText);
			panel.add(day);

			panel.add(yearText);
			panel.add(year);
			//Submit is to use once the user has chosen the date to check 
			JButton submit = new JButton("Submit");
			submit.setPreferredSize(new Dimension(80, 50));
			panel.add(submit);
			
			
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
			
			
			//This JButton is used when the user wants to check in or out a specific guest______________________________
			JButton check = new JButton(item);
			check.setPreferredSize(new Dimension(120, 50));
			check.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent event)
				{
					
				}
			});
			//_________________________________________________________________________________________________________
			
			
			
			//_____________________________________________________________________________deals with getting the name of the reservation
			//making the scrollpane for the list of guest
			//TO DO create a function to look through each reservation at the specific day 
	    	//_________________________________________________________________________________________________
			
			
			//____________________________________________________________________________Checking in and out the guest
			submit.addActionListener(new ActionListener()
					{
					public void actionPerformed(ActionEvent event)
						{
							//Make the panel
							DefaultListModel demoList = new DefaultListModel();
							JList lists = new JList(demoList);
				    
				    	
							JScrollPane list = new JScrollPane(lists);
				    		list.setPreferredSize(new Dimension(100,100));
				    		
							//Get what the user inputted and search through each room with the date 
							int lMonth = (int)month.getSelectedItem();
							int lDay = (int)day.getSelectedItem();
							int lYear = Integer.parseInt(year.getText());
							//Code to look through the reservation 
							
//							//This part of the code gets the user input and finds the specific date 
							Day lIndexDay = new Day(lMonth, lDay, lYear);
							
							for (Room iRoom : RoomList.getmRoomList().getmSmallRooms()) 
							{
								System.out.println("hi");
								//Each room has a date 
								for (Date iDate : iRoom.getRoomDates()) 
								{
									if (iDate.getmDay().equals(lIndexDay))
									{
										//Get the guest name
									}
								}
							}
	
							
							
							
							
							
							
							
							
							
							
							
							//remove all
							panel.removeAll();
							
							//Adds components
							panel.add(list);
							panel.add(cancel);
							panel.add(check);
							
							//Refreshes the panel 
							panel.revalidate();
							panel.repaint();
						}
				});
			frame.add(panel);
		}
	}

	//---------------------------------------------------------------------------------------------------------------------