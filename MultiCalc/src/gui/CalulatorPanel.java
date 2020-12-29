package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import calculations.Calcs;

/*
 * Standard Calculator Panel
 */
public class CalulatorPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static JTextField calculationField = new JTextField();
	private static JTextField inputResultField = new JTextField();
	
	/*
	 * Default constructor
	 * sets attributes for the panel 
	 */
	public CalulatorPanel() {

		setLayout(new BorderLayout());
		add(panelName(),BorderLayout.NORTH);
		add(display(), BorderLayout.CENTER );
		add(button(), BorderLayout.SOUTH);
	}
	
	/**
	 *  creates a panel that contains the main panel's title 
	 * @return panel 
	 */
	public JPanel panelName() {
		JLabel label = new JLabel("Standard Calc");
		label.setFont(new Font("DejaVu Sans Mono",Font.BOLD,40));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(220,183,183));
		
		panel.add(label);
		return panel;
	}
	
	/**
	 *  creates a panel that contains all the buttons of the calculator
	 * @return panel that contain buttons
	 */
	public JPanel button() {
		
		JPanel panel = new JPanel();
		JButton[] buttons = new JButton[24];
		String [] bNames = {"|x|","CE","C","\u232B","\u221A","modInv","mod","\u00F7","1","2","3","x","4","5","6",
							"\u2013","7","8","9","+","\u2213","0", ".","="};
		panel.setLayout(new GridBagLayout());
		panel.setBackground(new Color(220,183,183));
		GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 8, 8, 5); 
        
        // column 0 
        c.gridx = 0; 
  
        // row 0 
        c.gridy = 0; 
  
        // increases components width 
        c.ipadx = 40; 
  
        // increases components height  
        c.ipady = 10; 
        
        // initializes all the buttons and sets it attributes and position in the panel
		for (int i = 0; i < buttons.length; i++) {
			if (i % 4 == 0) {
				c.gridy++;
				c.gridx = 0;
			}
			buttons[i] = new JButton(bNames[i]);
			buttons[i].setFont(new Font("DejaVu Sans Mono",Font.BOLD,20));
			buttons[i].setBackground(new Color(175,175,175));
			buttons[i].setBorder(new LineBorder(Color.black,2));
			buttons[i].setPreferredSize(new Dimension(35,35));
			buttons[i].addActionListener(this);
			panel.add(buttons[i],c);
			c.gridx++;

		}
		
		return panel;
	}
	
	/**
	 * creates a panel the contain the display for the calculator
	 * @return the panel
	 */
	public JPanel display() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		panel.setBackground(new Color(220,183,183));
		GridBagConstraints c = new GridBagConstraints();
		 // insets for all components 
        c.insets = new Insets(2, 2, 2, 2); 
  
        // column 0 
        c.gridx = 0; 
  
        // row 0 
        c.gridy = 0; 
  
        // increases components width
        c.ipadx = 350; 
  
        // increases components height 
        c.ipady = 15; 
		
		calculationField.setEditable(false);
		calculationField.setBorder(new LineBorder(new Color(151,64,64), 4));
		calculationField.setBackground(new Color(224,73,73));
		calculationField.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(calculationField,c);
		
		inputResultField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		c.gridy = 1; 
		panel.add(inputResultField,c);
		return panel;
	}
	
	//sets actions to each button
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand(); //action string
		
		if(command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4") || command.equals("5") ||
				command.equals("6") || command.equals("7")  || command.equals("8") || command.equals("9") || command.equals("0") ) {
			if (calculationField.getText().contains("=")) {
				calculationField.setText("");
				inputResultField.setText("");
				inputResultField.setText(inputResultField.getText() + command);
			}else {
				inputResultField.setText(inputResultField.getText() + command);
			}
			
		}
		
		if (command.equals("+") || command.equals("x") || command.equals("\u2013") || command.equals("\u00F7") ||
				command.equals("modInv") || command.equals("mod") ) {
			calculationField.setText(inputResultField.getText() + command);
			inputResultField.setText("");
		}
		
		if (command.equals("=") && (!inputResultField.getText().equals("") && !calculationField.getText().equals("")) ) {
			DecimalFormat format = new DecimalFormat("#.##");
			String right = inputResultField.getText();
			if (calculationField.getText().contains("+") || calculationField.getText().contains("\u2013") || 
					calculationField.getText().contains("x") || calculationField.getText().contains("\u00F7") ){
				inputResultField.setText(String.valueOf( format.format(Calcs.basicCalculations(calculationField.getText() + inputResultField.getText()))) );
			}else if (calculationField.getText().contains("modInv")) {
				inputResultField.setText(String.valueOf( Calcs.modInv(calculationField.getText() + inputResultField.getText())) );
			}else if (calculationField.getText().contains("mod")) {
				inputResultField.setText(String.valueOf(format.format(Calcs.mod(calculationField.getText() + inputResultField.getText())) ) );
			}
			
			calculationField.setText(calculationField.getText() + right + "=");
		}
		
		if (command.equals("CE")) {
			inputResultField.setText("");
			calculationField.setText("");
		}
		if (command.equals("C")) {
			inputResultField.setText("");
		}
		
		if (command.equals("\u221A")) {
			String right = inputResultField.getText();
			inputResultField.setText(String.valueOf(Calcs.squareRoot(inputResultField.getText())));
			calculationField.setText("\u221A" +right + "=");
		}
		if (command.equals("\u232B")) {
			String operation = inputResultField.getText();
			if (operation.length() >=1) {
				inputResultField.setText(operation.substring(0, operation.length()-1) );
			}
		}
		
		if (command.equals("\u2213")) {
			if (!inputResultField.getText().contains("-")) {
				inputResultField.setText("-"+ inputResultField.getText());
			}else if (inputResultField.getText().contains("-")) {
				inputResultField.setText(inputResultField.getText().substring(1,inputResultField.getText().length()));
			}
		}
		
		if (command.equals(".")) {
			inputResultField.setText(inputResultField.getText() + ".");
		}
		
		if (command.equals("|x|")) {
			
			if (inputResultField.getText().contains("-")) {
				inputResultField.setText(inputResultField.getText().substring(1,inputResultField.getText().length()));
			}
		}
		
	}
	
}
