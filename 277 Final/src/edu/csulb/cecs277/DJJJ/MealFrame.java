package edu.csulb.cecs277.DJJJ;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MealFrame extends JFrame {
	
	private JLabel pizza1;
	private JComboBox<String> piz1top1;
	private JComboBox<String> piz1top2;
	private JComboBox<String> piz1top3;
	private JComboBox<String> piz1top4;

	private JLabel pizza2;
	private JComboBox<String> piz2top1;
	private JComboBox<String> piz2top2;
	private JComboBox<String> piz2top3;
	private JComboBox<String> piz2top4;
	
	private JLabel pizza3;
	private JComboBox<String> piz3top1;
	private JComboBox<String> piz3top2;
	private JComboBox<String> piz3top3;
	private JComboBox<String> piz3top4;
	
	private JLabel pizza4;
	private JComboBox<String> piz4top1;
	private JComboBox<String> piz4top2;
	private JComboBox<String> piz4top3;
	private JComboBox<String> piz4top4;
	
	private JLabel soda1;
	private JComboBox<String> sodaFlavor1;

	private JLabel soda2;
	private JComboBox<String> sodaFlavor2;

	private JLabel soda3;
	private JComboBox<String> sodaFlavor3;

	private JLabel soda4;
	private JComboBox<String> sodaFlavor4;

	private JLabel soda5;
	private JComboBox<String> sodaFlavor5;

	private JLabel wing1;
	private JComboBox<String> wingFlavor1;
	private JCheckBox bone1;

	private JLabel wing2;
	private JComboBox<String> wingFlavor2;
	private JCheckBox bone2;
	
	private JLabel ice1;
	private JComboBox<String> iceFlavor1;

	private JLabel ice2;
	private JComboBox<String> iceFlavor2;
	
	private JLabel addon;
	private JComboBox<String> addonChoice;
	
	private JButton save;
	private JButton cancel;
	
	private String planChoice;
	
	private ReservationFrame mResFrame;
	
	private String[] toppings = new String[] {"None", "Cheese", "Pepperoni", "Ham", "Jalapeno", "Sausage", "Mushroom", "Pineapple", "Bell Pepper", "Onion", "Garlic Chicken"};
	private String[] sodaFlavors = new String[] {"Coca-Cola", "Diet Coke", "Canada Dry", "Orange Crush", "Squirt", "Root Beer"};
	private String[] addons = new String[] {"Salad", "Breadsticks"};
	private String[] wingFlavors = new String[] {"Mild Spicy", "Diablo", "Lemon Pepper", "BBQ", "Sesame"};
	private String[] iceFlavors = new String[] {"Chocolate Fudge", "Vanilla Bean", "Strawberry Shortcake", "Choco-mint", "Butter Pecan"};
	
	/**
	 * Sets up the Meal Plan Frame according to what was selected in the ReservationFrame
	 * **/
	public MealFrame(ReservationFrame pResFrame) {
		mResFrame = pResFrame;
		switch(pResFrame.getmMealPlanCB().getSelectedItem().toString()) {
		case "Basic": planChoice = "Basic"; this.setTitle("Basic Meal Plan"); createBasicComponents(); break;
		case "Bronze": planChoice = "Bronze"; this.setTitle("Bronze Meal Plan"); createBronzeComponents(); break;
		case "Silver": planChoice = "Silver"; this.setTitle("Silver Meal Plan"); createSilverComponents(); break;
		case "Gold": planChoice = "Gold"; this.setTitle("Gold Meal Plan"); createGoldComponents(); break;
		case "Platinum": planChoice = "Platinum"; this.setTitle("Platinum Meal Plan"); createPlatinumComponents(); break;
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Creates the components for a Basic Meal Plan
	 * **/
	private void createBasicComponents() {
		
		pizza1 = new JLabel("Pizza 1: ");
		piz1top1 = new JComboBox<String>(toppings);
		
		pizza2 = new JLabel("Pizza 2: ");
		piz2top1 = new JComboBox<String>(toppings);
		
		pizza3 = new JLabel("Pizza 3: ");
		piz3top1 = new JComboBox<String>(toppings);
		
		soda1 = new JLabel("Soda 1: ");
		sodaFlavor1 = new JComboBox<String>(sodaFlavors);
		
		soda2 = new JLabel("Soda 2: ");
		sodaFlavor2 = new JComboBox<String>(sodaFlavors);
		
		soda3 = new JLabel("Soda 3: ");
		sodaFlavor3 = new JComboBox<String>(sodaFlavors);
		
		save = new JButton("Save");
		ActionListener saveListener = new ButtonListener();
		save.addActionListener(saveListener);
		
		cancel = new JButton("Cancel");
		ActionListener cancelListener = new ButtonListener();
		cancel.addActionListener(cancelListener);
		
		JPanel panel = new JPanel();
		this.setSize(550, 300);
		
		panel.add(pizza1);
		panel.add(piz1top1);
		
		panel.add(pizza2);
		panel.add(piz2top1);
		
		panel.add(pizza3);
		panel.add(piz3top1);
		
		panel.add(soda1);
		panel.add(sodaFlavor1);
		
		panel.add(soda2);
		panel.add(sodaFlavor2);
		
		panel.add(soda3);
		panel.add(sodaFlavor3);
		
		panel.add(save);
		panel.add(cancel);
		this.add(panel);
	}
	
	/**
	 * Creates the components for a Bronze Meal Plan
	 * **/
	private void createBronzeComponents() {
		
		pizza1 = new JLabel("Pizza 1: ");
		piz1top1 = new JComboBox<String>(toppings);
		piz1top2 = new JComboBox<String>(toppings);
		
		pizza2 = new JLabel("Pizza 2: ");
		piz2top1 = new JComboBox<String>(toppings);
		piz2top2 = new JComboBox<String>(toppings);
		
		pizza3 = new JLabel("Pizza 3: ");
		piz3top1 = new JComboBox<String>(toppings);
		piz3top2 = new JComboBox<String>(toppings);
				
		soda1 = new JLabel("Soda 1: ");
		sodaFlavor1 = new JComboBox<String>(sodaFlavors);
		
		soda2 = new JLabel("Soda 2: ");
		sodaFlavor2 = new JComboBox<String>(sodaFlavors);
		
		soda3 = new JLabel("Soda 3: ");
		sodaFlavor3 = new JComboBox<String>(sodaFlavors);
		
		soda4 = new JLabel("Soda 4: ");
		sodaFlavor4 = new JComboBox<String>(sodaFlavors);
		
		soda5 = new JLabel("Soda 5: ");
		sodaFlavor5 = new JComboBox<String>(sodaFlavors);
		
		addon = new JLabel("Addon: ");
		addonChoice = new JComboBox<String>(addons);
		
		save = new JButton("Save");
		ActionListener saveListener = new ButtonListener();
		save.addActionListener(saveListener);
		
		cancel = new JButton("Cancel");
		ActionListener cancelListener = new ButtonListener();
		cancel.addActionListener(cancelListener);
		
		JPanel panel = new JPanel();
		this.setSize(335, 300);

		panel.add(pizza1);
		panel.add(piz1top1);
		panel.add(piz1top2);
		
		panel.add(pizza2);
		panel.add(piz2top1);
		panel.add(piz2top2);
		
		panel.add(pizza3);
		panel.add(piz3top1);
		panel.add(piz3top2);
		
		panel.add(soda1);
		panel.add(sodaFlavor1);
		
		panel.add(soda2);
		panel.add(sodaFlavor2);
		
		panel.add(soda3);
		panel.add(sodaFlavor3);
		
		panel.add(soda4);
		panel.add(sodaFlavor4);
		
		panel.add(soda5);
		panel.add(sodaFlavor5);

		panel.add(addon);
		panel.add(addonChoice);
		
		panel.add(save);
		panel.add(cancel);
		this.add(panel);
	}
	
	/**
	 * Creates the components for a Silver Meal Plan
	 * **/
	private void createSilverComponents() {
		
		pizza1 = new JLabel("Pizza 1: ");
		piz1top1 = new JComboBox<String>(toppings);
		piz1top2 = new JComboBox<String>(toppings);
		piz1top3 = new JComboBox<String>(toppings);
		
		pizza2 = new JLabel("Pizza 2: ");
		piz2top1 = new JComboBox<String>(toppings);
		piz2top2 = new JComboBox<String>(toppings);
		piz2top3 = new JComboBox<String>(toppings);
		
		pizza3 = new JLabel("Pizza 3: ");
		piz3top1 = new JComboBox<String>(toppings);
		piz3top2 = new JComboBox<String>(toppings);
		piz3top3 = new JComboBox<String>(toppings);
				
		soda1 = new JLabel("Soda 1: ");
		sodaFlavor1 = new JComboBox<String>(sodaFlavors);
		
		soda2 = new JLabel("Soda 2: ");
		sodaFlavor2 = new JComboBox<String>(sodaFlavors);
		
		soda3 = new JLabel("Soda 3: ");
		sodaFlavor3 = new JComboBox<String>(sodaFlavors);
		
		soda4 = new JLabel("Soda 4: ");
		sodaFlavor4 = new JComboBox<String>(sodaFlavors);
		
		soda5 = new JLabel("Soda 5: ");
		sodaFlavor5 = new JComboBox<String>(sodaFlavors);
		
		addon = new JLabel("Salad & Breadsticks included");
		
		save = new JButton("Save");
		ActionListener saveListener = new ButtonListener();
		save.addActionListener(saveListener);
		
		cancel = new JButton("Cancel");
		ActionListener cancelListener = new ButtonListener();
		cancel.addActionListener(cancelListener);
		
		JPanel panel = new JPanel();
		this.setSize(1200, 300);

		panel.add(pizza1);
		panel.add(piz1top1);
		panel.add(piz1top2);
		panel.add(piz1top3);
		
		panel.add(pizza2);
		panel.add(piz2top1);
		panel.add(piz2top2);
		panel.add(piz2top3);
		
		panel.add(pizza3);
		panel.add(piz3top1);
		panel.add(piz3top2);
		panel.add(piz3top3);
		
		panel.add(soda1);
		panel.add(sodaFlavor1);
		
		panel.add(soda2);
		panel.add(sodaFlavor2);
		
		panel.add(soda3);
		panel.add(sodaFlavor3);
		
		panel.add(soda4);
		panel.add(sodaFlavor4);
		
		panel.add(soda5);
		panel.add(sodaFlavor5);

		panel.add(addon);
		
		panel.add(save);
		panel.add(cancel);
		this.add(panel);
	}
	
	/**
	 * Creates the components for a Gold Meal Plan
	 * **/
	private void createGoldComponents() {
		
		pizza1 = new JLabel("Pizza 1: ");
		piz1top1 = new JComboBox<String>(toppings);
		piz1top2 = new JComboBox<String>(toppings);
		piz1top3 = new JComboBox<String>(toppings);
		
		pizza2 = new JLabel("Pizza 2: ");
		piz2top1 = new JComboBox<String>(toppings);
		piz2top2 = new JComboBox<String>(toppings);
		piz2top3 = new JComboBox<String>(toppings);
		
		pizza3 = new JLabel("Pizza 3: ");
		piz3top1 = new JComboBox<String>(toppings);
		piz3top2 = new JComboBox<String>(toppings);
		piz3top3 = new JComboBox<String>(toppings);
				
		soda1 = new JLabel("Soda 1: ");
		sodaFlavor1 = new JComboBox<String>(sodaFlavors);
		
		soda2 = new JLabel("Soda 2: ");
		sodaFlavor2 = new JComboBox<String>(sodaFlavors);
		
		soda3 = new JLabel("Soda 3: ");
		sodaFlavor3 = new JComboBox<String>(sodaFlavors);
		
		soda4 = new JLabel("Soda 4: ");
		sodaFlavor4 = new JComboBox<String>(sodaFlavors);
		
		soda5 = new JLabel("Soda 5: ");
		sodaFlavor5 = new JComboBox<String>(sodaFlavors);
		
		addon = new JLabel("Salad & Breadsticks included");
		
		wing1 = new JLabel("Wing 1: ");
		wingFlavor1 = new JComboBox<String>(wingFlavors);
		bone1 = new JCheckBox("Bone-In");
		
		wing2 = new JLabel("Wing 2: ");
		wingFlavor2 = new JComboBox<String>(wingFlavors);
		bone2 = new JCheckBox("Bone-In");
		
		save = new JButton("Save");
		ActionListener saveListener = new ButtonListener();
		save.addActionListener(saveListener);
		
		cancel = new JButton("Cancel");
		ActionListener cancelListener = new ButtonListener();
		cancel.addActionListener(cancelListener);
		
		JPanel panel = new JPanel();
		this.setSize(1240, 300);

		panel.add(pizza1);
		panel.add(piz1top1);
		panel.add(piz1top2);
		panel.add(piz1top3);
		
		panel.add(pizza2);
		panel.add(piz2top1);
		panel.add(piz2top2);
		panel.add(piz2top3);
		
		panel.add(pizza3);
		panel.add(piz3top1);
		panel.add(piz3top2);
		panel.add(piz3top3);
		
		panel.add(soda1);
		panel.add(sodaFlavor1);
		
		panel.add(soda2);
		panel.add(sodaFlavor2);
		
		panel.add(soda3);
		panel.add(sodaFlavor3);
		
		panel.add(soda4);
		panel.add(sodaFlavor4);
		
		panel.add(soda5);
		panel.add(sodaFlavor5);

		panel.add(addon);
		
		panel.add(wing1);
		panel.add(wingFlavor1);
		panel.add(bone1);
		
		panel.add(wing2);
		panel.add(wingFlavor2);
		panel.add(bone2);
		
		panel.add(save);
		panel.add(cancel);
		this.add(panel);
	}
	
	/**
	 * Creates the components for a Platinum Meal Plan
	 * **/
	private void createPlatinumComponents() {
		
		pizza1 = new JLabel("Pizza 1: ");
		piz1top1 = new JComboBox<String>(toppings);
		piz1top2 = new JComboBox<String>(toppings);
		piz1top3 = new JComboBox<String>(toppings);
		piz1top4 = new JComboBox<String>(toppings);
		
		pizza2 = new JLabel("Pizza 2: ");
		piz2top1 = new JComboBox<String>(toppings);
		piz2top2 = new JComboBox<String>(toppings);
		piz2top3 = new JComboBox<String>(toppings);
		piz2top4 = new JComboBox<String>(toppings);
		
		pizza3 = new JLabel("Pizza 3: ");
		piz3top1 = new JComboBox<String>(toppings);
		piz3top2 = new JComboBox<String>(toppings);
		piz3top3 = new JComboBox<String>(toppings);
		piz3top4 = new JComboBox<String>(toppings);
		
		pizza4 = new JLabel("Pizza 4: ");
		piz4top1 = new JComboBox<String>(toppings);
		piz4top2 = new JComboBox<String>(toppings);
		piz4top3 = new JComboBox<String>(toppings);
		piz4top4 = new JComboBox<String>(toppings);
				
		soda1 = new JLabel("Soda 1: ");
		sodaFlavor1 = new JComboBox<String>(sodaFlavors);
		
		soda2 = new JLabel("Soda 2: ");
		sodaFlavor2 = new JComboBox<String>(sodaFlavors);
		
		soda3 = new JLabel("Soda 3: ");
		sodaFlavor3 = new JComboBox<String>(sodaFlavors);
		
		soda4 = new JLabel("Soda 4: ");
		sodaFlavor4 = new JComboBox<String>(sodaFlavors);
		
		soda5 = new JLabel("Soda 5: ");
		sodaFlavor5 = new JComboBox<String>(sodaFlavors);
		
		addon = new JLabel("Salad & Breadsticks included");
		
		wing1 = new JLabel("Wing 1: ");
		wingFlavor1 = new JComboBox<String>(wingFlavors);
		bone1 = new JCheckBox("Bone-In");
		
		wing2 = new JLabel("Wing 2: ");
		wingFlavor2 = new JComboBox<String>(wingFlavors);
		bone2 = new JCheckBox("Bone-In");
		
		ice1 = new JLabel("Ice Cream 1: ");
		iceFlavor1 = new JComboBox<String>(iceFlavors);
		
		ice2 = new JLabel("Ice Cream 2: ");
		iceFlavor2 = new JComboBox<String>(iceFlavors);
		
		save = new JButton("Save");
		ActionListener saveListener = new ButtonListener();
		save.addActionListener(saveListener);
		
		cancel = new JButton("Cancel");
		ActionListener cancelListener = new ButtonListener();
		cancel.addActionListener(cancelListener);
		
		JPanel panel = new JPanel();
		this.setSize(525, 400);

		panel.add(pizza1);
		panel.add(piz1top1);
		panel.add(piz1top2);
		panel.add(piz1top3);
		panel.add(piz1top4);
		
		panel.add(pizza2);
		panel.add(piz2top1);
		panel.add(piz2top2);
		panel.add(piz2top3);
		panel.add(piz2top4);
		
		panel.add(pizza3);
		panel.add(piz3top1);
		panel.add(piz3top2);
		panel.add(piz3top3);
		panel.add(piz3top4);
		
		panel.add(pizza4);
		panel.add(piz4top1);
		panel.add(piz4top2);
		panel.add(piz4top3);
		panel.add(piz4top4);
		
		panel.add(soda1);
		panel.add(sodaFlavor1);
		
		panel.add(soda2);
		panel.add(sodaFlavor2);
		
		panel.add(soda3);
		panel.add(sodaFlavor3);
		
		panel.add(soda4);
		panel.add(sodaFlavor4);
		
		panel.add(soda5);
		panel.add(sodaFlavor5);

		panel.add(addon);
		
		panel.add(wing1);
		panel.add(wingFlavor1);
		panel.add(bone1);
		
		panel.add(wing2);
		panel.add(wingFlavor2);
		panel.add(bone2);
		
		panel.add(ice1);
		panel.add(iceFlavor1);
		
		panel.add(ice2);
		panel.add(iceFlavor2);
		
		panel.add(save);
		panel.add(cancel);
		this.add(panel);
	}
	
	/**
	 * Button Listener that checks to see whether "save" or "cancel" is clicked
	 * If "save" is clicked, then the appropriate meal plan is saved to the reservation frame
	 * If "cancel" is clicked, then the frame closes and returns to the reservation frame
	 * **/
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent click) {
			Component button = (Component) click.getSource();
			JFrame frame = (JFrame) SwingUtilities.getRoot(button);
			
			if (click.getSource() == save) {
				if (planChoice.equals("Basic")) {
					BasicPlan myBasic = new BasicPlan();
					myBasic.getPizzas().get(0).addTop(piz1top1.getSelectedItem().toString());
					myBasic.getPizzas().get(1).addTop(piz2top1.getSelectedItem().toString());
					myBasic.getPizzas().get(2).addTop(piz3top1.getSelectedItem().toString());
					myBasic.getSodas().get(0).setSoda(sodaFlavor1.getSelectedItem().toString());
					myBasic.getSodas().get(1).setSoda(sodaFlavor2.getSelectedItem().toString());
					myBasic.getSodas().get(2).setSoda(sodaFlavor3.getSelectedItem().toString());
					
					mResFrame.setmMealPlan(myBasic);
					mResFrame.setmMealEditted(false);
					frame.setVisible(false);
				}
				else if (planChoice.equals("Bronze")) {
					BronzePlan myBronze = new BronzePlan();
					myBronze.getPizzas().get(0).addTop(piz1top1.getSelectedItem().toString());
					myBronze.getPizzas().get(0).addTop(piz1top2.getSelectedItem().toString());
					
					myBronze.getPizzas().get(1).addTop(piz2top1.getSelectedItem().toString());
					myBronze.getPizzas().get(1).addTop(piz2top2.getSelectedItem().toString());
					
					myBronze.getPizzas().get(2).addTop(piz3top1.getSelectedItem().toString());
					myBronze.getPizzas().get(2).addTop(piz3top2.getSelectedItem().toString());
					
					myBronze.getSodas().get(0).setSoda(sodaFlavor1.getSelectedItem().toString());
					myBronze.getSodas().get(1).setSoda(sodaFlavor2.getSelectedItem().toString());
					myBronze.getSodas().get(2).setSoda(sodaFlavor3.getSelectedItem().toString());
					myBronze.getSodas().get(3).setSoda(sodaFlavor4.getSelectedItem().toString());
					myBronze.getSodas().get(4).setSoda(sodaFlavor5.getSelectedItem().toString());
					
					myBronze.setAddon(addonChoice.getSelectedItem().toString());
					
					mResFrame.setmMealPlan(myBronze);
					mResFrame.setmMealEditted(true);
					frame.setVisible(false);
				}
				else if (planChoice.equals("Silver")) {
					SilverPlan mySilver = new SilverPlan();
					mySilver.getPizzas().get(0).addTop(piz1top1.getSelectedItem().toString());
					mySilver.getPizzas().get(0).addTop(piz1top2.getSelectedItem().toString());
					mySilver.getPizzas().get(0).addTop(piz1top3.getSelectedItem().toString());
					
					mySilver.getPizzas().get(1).addTop(piz2top1.getSelectedItem().toString());
					mySilver.getPizzas().get(1).addTop(piz2top2.getSelectedItem().toString());
					mySilver.getPizzas().get(1).addTop(piz2top3.getSelectedItem().toString());
					
					mySilver.getPizzas().get(2).addTop(piz3top1.getSelectedItem().toString());
					mySilver.getPizzas().get(2).addTop(piz3top2.getSelectedItem().toString());
					mySilver.getPizzas().get(2).addTop(piz3top3.getSelectedItem().toString());
					
					mySilver.getSodas().get(0).setSoda(sodaFlavor1.getSelectedItem().toString());
					mySilver.getSodas().get(1).setSoda(sodaFlavor2.getSelectedItem().toString());
					mySilver.getSodas().get(2).setSoda(sodaFlavor3.getSelectedItem().toString());
					mySilver.getSodas().get(3).setSoda(sodaFlavor4.getSelectedItem().toString());
					mySilver.getSodas().get(4).setSoda(sodaFlavor5.getSelectedItem().toString());
					
					mResFrame.setmMealPlan(mySilver);
					mResFrame.setmMealEditted(true);
					frame.setVisible(false);
				}
				else if (planChoice.equals("Gold")) {
					GoldPlan myGold = new GoldPlan();
					myGold.getPizzas().get(0).addTop(piz1top1.getSelectedItem().toString());
					myGold.getPizzas().get(0).addTop(piz1top2.getSelectedItem().toString());
					myGold.getPizzas().get(0).addTop(piz1top3.getSelectedItem().toString());
					
					myGold.getPizzas().get(1).addTop(piz2top1.getSelectedItem().toString());
					myGold.getPizzas().get(1).addTop(piz2top2.getSelectedItem().toString());
					myGold.getPizzas().get(1).addTop(piz2top3.getSelectedItem().toString());
					
					myGold.getPizzas().get(2).addTop(piz3top1.getSelectedItem().toString());
					myGold.getPizzas().get(2).addTop(piz3top2.getSelectedItem().toString());
					myGold.getPizzas().get(2).addTop(piz3top3.getSelectedItem().toString());
					
					myGold.getSodas().get(0).setSoda(sodaFlavor1.getSelectedItem().toString());
					myGold.getSodas().get(1).setSoda(sodaFlavor2.getSelectedItem().toString());
					myGold.getSodas().get(2).setSoda(sodaFlavor3.getSelectedItem().toString());
					myGold.getSodas().get(3).setSoda(sodaFlavor4.getSelectedItem().toString());
					myGold.getSodas().get(4).setSoda(sodaFlavor5.getSelectedItem().toString());
					
					myGold.getWings().get(0).setWings(wingFlavor1.getSelectedItem().toString(), bone1.isSelected());
					myGold.getWings().get(1).setWings(wingFlavor2.getSelectedItem().toString(), bone2.isSelected());
					
					mResFrame.setmMealPlan(myGold);
					mResFrame.setmMealEditted(true);
					frame.setVisible(false);
				}
				else if (planChoice.equals("Platinum")) {
					PlatinumPlan myPlat = new PlatinumPlan();
					myPlat.getPizzas().get(0).addTop(piz1top1.getSelectedItem().toString());
					myPlat.getPizzas().get(0).addTop(piz1top2.getSelectedItem().toString());
					myPlat.getPizzas().get(0).addTop(piz1top3.getSelectedItem().toString());
					myPlat.getPizzas().get(0).addTop(piz1top4.getSelectedItem().toString());

					myPlat.getPizzas().get(1).addTop(piz2top1.getSelectedItem().toString());
					myPlat.getPizzas().get(1).addTop(piz2top2.getSelectedItem().toString());
					myPlat.getPizzas().get(1).addTop(piz2top3.getSelectedItem().toString());
					myPlat.getPizzas().get(1).addTop(piz2top4.getSelectedItem().toString());
					
					myPlat.getPizzas().get(2).addTop(piz3top1.getSelectedItem().toString());
					myPlat.getPizzas().get(2).addTop(piz3top2.getSelectedItem().toString());
					myPlat.getPizzas().get(2).addTop(piz3top3.getSelectedItem().toString());
					myPlat.getPizzas().get(2).addTop(piz3top4.getSelectedItem().toString());
					
					myPlat.getPizzas().get(3).addTop(piz4top1.getSelectedItem().toString());
					myPlat.getPizzas().get(3).addTop(piz4top2.getSelectedItem().toString());
					myPlat.getPizzas().get(3).addTop(piz4top3.getSelectedItem().toString());
					myPlat.getPizzas().get(3).addTop(piz4top4.getSelectedItem().toString());
					
					myPlat.getSodas().get(0).setSoda(sodaFlavor1.getSelectedItem().toString());
					myPlat.getSodas().get(1).setSoda(sodaFlavor2.getSelectedItem().toString());
					myPlat.getSodas().get(2).setSoda(sodaFlavor3.getSelectedItem().toString());
					myPlat.getSodas().get(3).setSoda(sodaFlavor4.getSelectedItem().toString());
					myPlat.getSodas().get(4).setSoda(sodaFlavor5.getSelectedItem().toString());
					
					myPlat.getWings().get(0).setWings(wingFlavor1.getSelectedItem().toString(), bone1.isSelected());
					myPlat.getWings().get(1).setWings(wingFlavor2.getSelectedItem().toString(), bone2.isSelected());
					
					myPlat.getIce().get(0).setIceCream(iceFlavor1.getSelectedItem().toString());
					myPlat.getIce().get(1).setIceCream(iceFlavor2.getSelectedItem().toString());
					
					mResFrame.setmMealPlan(myPlat);
					mResFrame.setmMealEditted(true);
					frame.setVisible(false);
				}
			}
			if (click.getSource() == cancel) {
				frame.setVisible(false);
			}
		}
	}
}