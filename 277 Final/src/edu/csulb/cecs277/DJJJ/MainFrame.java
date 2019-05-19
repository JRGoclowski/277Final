package edu.csulb.cecs277.DJJJ;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/*
import backend.AquaRoom;
import backend.MediumPartyRoom;
import backend.SmallPartyRoom;
*/

public class MainFrame extends JFrame {
	JMenuBar menuBar;
	JScrollPane scrollPane;
	JPanel centerPanel;
	
	//border settings used in the method addARoomDescription()
 	Border raisedbevel = BorderFactory.createRaisedBevelBorder(); 
 	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
 	
 	// Descriptions of the default rooms and meal plans here?
 	private static String aquaDefault = "Number of Rooms: 1\n" +
 										"Description: Olympic-sized pool with water slide, kiddie pool, and large jacuzzi\n" +
 										"Capacity: 75 people\n" +
 										"Cost: $700/hr\n" +
 										"Included in cost: access to showers/lockers, life guards on duty, DJ, table & chair set-up, Basic Meal Plan\n" +
 										"Upgrades available:\n" +
 										"-Upgrade meal plan for an additional cost of $5x(new meal plan cost - basic meal plan cost)\n" +
 										"-Towel rentals ($2 each)\n" +
 										"-Party favors bag ($5 per bag)\n" +
 										"-Projector ($10/hour)\n" +
 										"-Party decorations & Set-up (+$100) - choose Hawaiian, Sea Life, Jungle, Space, or Modern Theme\n" +
 										"Restrictions: To access water facilites, bathing suits must be worn at all times";
 	
 	private static String smallDefault = "Number of Rooms: 10\n" +
 										 "Description: Room with party tables and chairs, adjacent to arcade\n" +
 										 "Capacity: 30 people\n" +
 										 "Cost: $150/hr\n" +
 										 "Included in cost: Table & chair set-up, Basic Meal Plan\n" +
 										 "Upgrades available:\n" +
 										 "-Upgrade meal plan for an additional cost of $(new meal plan cost - basic meal plan cost)\n" +
 										 "-Party favors bag ($5 per bag)\n" +
 										 "-Projector ($10/hour)\n" +
 										 "-Party decorations & Set-up (+$100) - choose Hawaiian, Sea Life, Jungle, Space, or Modern Theme";
 	
 	private static String mediumDefault = "Number of Rooms: 2\n" +
			 							  "Description: Room with party tables and chairs, quick access to arcade\n" +
			 							  "Capacity: 45 people\n" +
			 							  "Cost: $250/hr\n" +
			 							  "Included in cost: Table & chair set-up, DJ, Basic Meal Plan\n" +
			 							  "Upgrades available:\n" +
			 							  "-Upgrade meal plan for an additional cost of $3x(new meal plan cost - basic meal plan cost)\n" +
			 							  "-Party favors bag ($5 per bag)\n" +
			 							  "-Projector ($10/hour)\n" +
			 							  "-Party decorations & Set-up (+$100) - choose Hawaiian, Sea Life, Jungle, Space, or Modern Theme";

 	
 	private static String karaokeDefault = "Number of Rooms: 10\n" +
 										   "Description: Enclosed lounge with karaoke machine\n" +
 										   "Capacity: 10 people\n" +
 										   "Cost: $30/hr\n" +
 										   "Included in cost: access to karaoke machine\n" +
 										   "Upgrades available: add a meal plan";
 	
 	private static String billiardsDefault = "Number of Rooms: 5\n" +
 											 "Description: Enclosed lounge with pool table\n" +
 											 "Capacity: 10 people\n" +
 											 "Cost: $25/hr\n" +
 											 "Included in cost: access to pool tables and cues\n" +
 											 "Upgrades available: add a meal plan\n" +
 											 "Restrictions: 21+ only";
 	
 	private static String basicDefault = "Included in meal:\n" + 
 										 "-3 XL 1 Topping Gourmet Pizzas\n" + 
 										 "-3 2L Soda Bottles\n" +
 										 "Cost: $65";
 	
 	private static String bronzeDefault = "Included in meal:\n" +
 										  "-3 XL 2 Topping Gourmet Pizzas\n" + 
 										  "-5 2L Soda Bottles\n" +
 										  "-Salad or Breadsticks\n" +
 										  "Cost: $75";
 	
 	private static String silverDefault = "Included in meal:\n" +
 										  "-3 XL 3 Topping Gourmet Pizzas\n" + 
 										  "-5 2L Soda Bottles\n" +
 										  "-Salad\n" +
 										  "-Breadsticks\n" +
 										  "Cost: $90";
 	
 	private static String goldDefault = "Included in meal:\n" +
			  							"-3 XL 3 Topping Gourmet Pizzas\n" + 
			  							"-5 2L Soda Bottles\n" +
			  							"-Salad\n" +
			  							"-Breadsticks\n" +
			  							"-Choice of 2 chicken wing flavors (Mild Spicy, Diablo, Lemon Pepper, BBQ, Sesame)\n" +
			  							"-Pick bone-in or boneless\n" +
			  							"Cost: $120";
 	
 	private static String platinumDefault =  "Included in meal:\n" +
											 "-4 XL 4 Topping Gourmet Pizzas\n" + 
											 "-5 2L Soda Bottles\n" +
											 "-Salad\n" +
											 "-Breadsticks\n" +
											 "-Choice of 2 chicken wing flavors (Mild Spicy, Diablo, Lemon Pepper, BBQ, Sesame)\n" +
											 "-Pick bone-in or boneless\n" +
											 "-2 Flavors of Ice Cream (Chocolate Fudge, Vanilla Bean, Strawberry Shortcake, Choco-mint, Butter Pecan)\n" +
											 "Cost: $150";
	
	public MainFrame() {
		this.setTitle("Reservation System");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //makes window screen size
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.createMenuBar();
		centerPanel = new JPanel();
		this.createDefaultPanel();
		scrollPane = new JScrollPane(centerPanel);
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	/*
	 * initializes the default center panel and adds it to this frame
	 * */
	private void createDefaultPanel() {
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		//Title of default view
		JLabel panelTitle = new JLabel("Party World Rooms");
		panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		
		centerPanel.add(panelTitle);
		addARoomDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\smallRoom.jpg", "Small Party Room", smallDefault);
		addARoomDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\mediumRoom.jpg","Medium Party Room", mediumDefault);
		addARoomDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\aquaRoom.jpg", "Aqua Room", aquaDefault);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	private void createMealPanel() {
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		JLabel panelTitle = new JLabel("Party World Meal Plans");
		panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		
		centerPanel.add(panelTitle);
		addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\basicMeal.jpg", "Basic Meal Plan", basicDefault);
		addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\bronzeMeal.jpg", "Bronze Meal Plan", bronzeDefault);
		addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\silverMeal.jpg", "Silver Meal Plan", silverDefault);
		addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\goldMeal.jpg", "Gold Meal Plan", goldDefault);
		addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\platinumMeal.jpg", "Platinum Meal Plan", platinumDefault);
	}
	
	private void createLoungePanel() {
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		JLabel panelTitle = new JLabel("Party World Lounges");
		panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		
		centerPanel.add(panelTitle);
		addLoungeDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\karaokeLounge.jpg", "Karaoke Lounge", karaokeDefault);
		addLoungeDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\billiardsLounge.jpg", "Billiards Lounge", billiardsDefault);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	/* initializes menu bar items and adds them to this window*/
	private void createMenuBar() {
		menuBar = new JMenuBar();
		//-------
		JMenu menu;
		JMenu submenu;
		JMenuItem menuItem;
		//-------  DESCRIPTIONS MENU------------------
		menu = new JMenu("Descriptions");
		//Party Room Sub Menu
		submenu = new JMenu("Party Rooms");

		menuItem = new JMenuItem("All");
		menuItem.addActionListener(new RoomItemListener());
		submenu.add(menuItem);

		menuItem = new JMenuItem("Small Party Rooms");
		menuItem.addActionListener(new RoomItemListener());
		submenu.add(menuItem);
				
		menuItem = new JMenuItem("Medium Party Rooms");
		menuItem.addActionListener(new RoomItemListener());
		submenu.add(menuItem);
				
		menuItem = new JMenuItem("Aqua Room");
		menuItem.addActionListener(new RoomItemListener());
		submenu.add(menuItem);
		
		menu.add(submenu);				
		//Lounges Sub Menu
		submenu = new JMenu("Lounges");
				
		menuItem = new JMenuItem("All");
		submenu.add(menuItem);
		menuItem.addActionListener(new LoungeItemListener());
				
		menuItem = new JMenuItem("Karaoke Lounges");
		submenu.add(menuItem);
		menuItem.addActionListener(new LoungeItemListener());
				
		menuItem = new JMenuItem("Billiards Lounges");
		submenu.add(menuItem);
		menuItem.addActionListener(new LoungeItemListener());
				
		menu.add(submenu);
		menu.addSeparator();
		//Meal Plans Sub Menu
		submenu = new JMenu("Meal Plans");

		menuItem = new JMenuItem("All");
		submenu.add(menuItem);
		menuItem.addActionListener(new MealItemListener());
				
		menuItem = new JMenuItem("Basic");
		submenu.add(menuItem);
		menuItem.addActionListener(new MealItemListener());
				
		menuItem = new JMenuItem("Bronze");
		submenu.add(menuItem);
		menuItem.addActionListener(new MealItemListener());
				
		menuItem = new JMenuItem("Silver");
		submenu.add(menuItem);
		menuItem.addActionListener(new MealItemListener());
				
		menuItem = new JMenuItem("Gold");
		submenu.add(menuItem);
		menuItem.addActionListener(new MealItemListener());
				
		menuItem = new JMenuItem("Platinum");
		submenu.add(menuItem);
		menuItem.addActionListener(new MealItemListener());
				
		menu.add(submenu);
				
		menuBar.add(menu);
		//-------  RESERVATIONS MENU------------------
		menu = new JMenu("Reservations");
		//New reservation item
		menuItem = new JMenuItem("New Reservation...");
		menu.add(menuItem);
		menuItem.addActionListener(new ReservationItemListener());
		menu.addSeparator();
		//Edit reservation item
		menuItem = new JMenuItem("Edit Existing Reservation...");
		menu.add(menuItem);
		menuItem.addActionListener(new ReservationItemListener());
		menu.addSeparator();
		//Manage reservation sub menu 
		submenu = new JMenu("Manage Current Reservation");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("Check-in");
		submenu.add(menuItem);
		//Add a actionlistener for submenu here 
		menuItem.addActionListener(new checkInorOut());
						
		menuItem = new JMenuItem("Check-out");
		submenu.add(menuItem);
		//Add a actionlistener for submenu here 
		menuItem.addActionListener(new checkInorOut());
						
		menu.add(submenu);
				
		menuBar.add(menu);
		this.add(menuBar, BorderLayout.NORTH);
	}
	
	/* 
	 * adds room image and description to a given container
	 * @param container - the container onto which to add the new description
	 * @param imgDir - the directory of the image
	 * @param roomName - the name of the room
	 * @param roomDesc - the description of the room
	 * 
	 * */
	 private void addARoomDescription(Container container, String imgDir, String roomName, String roomDesc) {
		 JPanel p = new JPanel();
		 
		 ImageIcon icon = new ImageIcon(imgDir);
		 JLabel picture = new JLabel(roomName, icon, JLabel.CENTER);
		 picture.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		 //Set the position of the text, relative to the icon:
		 picture.setVerticalTextPosition(JLabel.TOP);
		 picture.setHorizontalTextPosition(JLabel.CENTER);
		 //Set border around 
		 picture.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		 	
		 p.add(picture);
		 	
		 JTextArea description = new JTextArea(5,10);
		 description.append(roomDesc);
		 description.setEditable(false);
		 description.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		 description.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		 p.add(description);
		 JButton bookButton = new JButton("Book Now");
		 p.add(bookButton);
		 bookButton.addActionListener(new BookItemListener(roomName));
		 container.add(p);
	}
	 
	//---------------------------------------------------------------------------------------------------------------------
	 
	private void addMealDescription(Container container, String imgDir, String mealPlan, String mealDesc) {
		JPanel p = new JPanel();
		
		ImageIcon icon = new ImageIcon(imgDir);
		JLabel picture = new JLabel(mealPlan, icon, JLabel.CENTER);
		picture.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		
		picture.setVerticalTextPosition(JLabel.TOP);
		picture.setHorizontalTextPosition(JLabel.CENTER);
		
		picture.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		
		p.add(picture);
		
		JTextArea description = new JTextArea(5,10);
		description.append(mealDesc);
		description.setEditable(false);
		description.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		description.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		p.add(description);
		container.add(p);
	}
	
	private void addLoungeDescription(Container container, String imgDir, String loungeName, String loungeDesc) {
		JPanel p = new JPanel();
		
		ImageIcon icon = new ImageIcon(imgDir);
		JLabel picture = new JLabel(loungeName, icon, JLabel.CENTER);
		picture.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		
		picture.setVerticalTextPosition(JLabel.TOP);
		picture.setHorizontalTextPosition(JLabel.CENTER);
		
		picture.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		
		p.add(picture);
		
		JTextArea description = new JTextArea(5,10);
		description.append(loungeDesc);
		description.setEditable(false);
		description.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		description.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		p.add(description);
		JButton bookButton = new JButton("Book Now");
		p.add(bookButton);
		bookButton.addActionListener(new BookItemListener(loungeName));
		container.add(p);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	class MealItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent click) {
			JMenuItem menuItem = (JMenuItem) click.getSource();
			String item = menuItem.getText();
			if (item.equals("All")) {
				centerPanel.removeAll();
				createMealPanel();
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.equals("Basic")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Basic Meal Plan");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\basicMeal.jpg", "Basic Meal Plan", basicDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.equals("Bronze")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Bronze Meal Plan");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\bronzeMeal.jpg", "Bronze Meal Plan", bronzeDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.equals("Silver")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Silver Meal Plan");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\silverMeal.jpg", "Silver Meal Plan", silverDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.equals("Gold")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Gold Meal Plan");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\goldMeal.jpg", "Gold Meal Plan", goldDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.equals("Platinum")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Platinum Meal Plan");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addMealDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\platinumMeal.jpg", "Platinum Meal Plan", platinumDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
		}
	}
	
	class RoomItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent select) {
			JMenuItem item = (JMenuItem) select.getSource();
			if (item.getText().equals("All")) {
				centerPanel.removeAll();
				createDefaultPanel();
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.getText().equals("Small Party Rooms")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Small Party Rooms");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addARoomDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\smallRoom.jpg", "Small Party Room", smallDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.getText().equals("Medium Party Rooms")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Medium Party Rooms");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addARoomDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\mediumRoom.jpg", "Medium Party Room", mediumDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.getText().equals("Aqua Room")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Aqua Room");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addARoomDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\aquaRoom.jpg", "Aqua Room", aquaDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	class LoungeItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent select) {
			JMenuItem item = (JMenuItem) select.getSource();
			if (item.getText().equals("All")) {
				centerPanel.removeAll();
				createLoungePanel();
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.getText().equals("Karaoke Lounges")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Karaoke Lounges");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addARoomDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\karaokeLounge.jpg", "Karaoke Lounge", karaokeDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
			else if (item.getText().equals("Billiards Lounges")) {
				centerPanel.removeAll();
				centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				JLabel panelTitle = new JLabel("Party World Billiards Lounges");
				panelTitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
				centerPanel.add(panelTitle);
				addARoomDescription(centerPanel, "C:\\Users\\Daniel\\Desktop\\billiardsLounge.jpg", "Billiards Lounge", billiardsDefault);
				centerPanel.revalidate();
				centerPanel.repaint();
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	class ReservationItemListener implements ActionListener {
		public void actionPerformed(ActionEvent select) {
			JMenuItem item = (JMenuItem) select.getSource();
			if (item.getText().equals("New Reservation...")) {
				DateTimeFrame dtf = new DateTimeFrame();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				dtf.setLocation(dim.width/2-dtf.getSize().width/2, dim.height/2-dtf.getSize().height/2);
				dtf.setVisible(true);
			}
			else if (item.getText().equals("Edit Existing Reservation...")) {
				EditReservationFrame eRF = new EditReservationFrame();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				eRF.setLocation(dim.width/2-eRF.getSize().width/2, dim.height/2-eRF.getSize().height/2);
				eRF.setVisible(true);
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	class BookItemListener implements ActionListener {
		private String roomName;
		
		public BookItemListener(String name) {
			roomName = name;
			if (roomName.equals("Aqua Room")) { roomName = "Aqua World"; }
		}
		
		public void actionPerformed(ActionEvent select) {
			DateTimeFrame dtf = new DateTimeFrame(roomName);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			dtf.setLocation(dim.width/2-dtf.getSize().width/2, dim.height/2-dtf.getSize().height/2);
			dtf.setVisible(true);
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------
		
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		
		
	}
}