package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import calculations.Calcs;

/**
 * Panel for the Final Grade Calculator
 * @author Bunguiu Norales
 *
 */
public class FinalGradePanel extends JPanel implements ActionListener{
	
	

	private static final long serialVersionUID = 1L;
	private JTextField curGradeField = new JTextField(); 	// current grade
	private JTextField wantGradeField = new JTextField();	//wanted grade 
	private JTextField finalExamField = new JTextField(); 	// final exam worth
	private JTextArea textArea = new JTextArea(2,3);
	
	/*
	 * default class constructor
	 * here all the attributes of the final grade panel are set
	 */
	public FinalGradePanel() {
		
		setLayout(new GridBagLayout());
		setBackground(new Color(220,183,183));
		
		GridBagConstraints c = new GridBagConstraints();
		
		 // insets for all components 
		c.insets = new Insets(2, 2, 2, 2); 
		 // column 0 
        c.gridx = 0; 
  
        // row 0 
        c.gridy = 0; 
  
        // increases components width
        c.ipadx = 60; 
  
        // increases components height 
        c.ipady = 15; 
        
		JLabel name = new JLabel("  Grade Calc"); 	// panel title
		name.setFont(new Font("DejaVu Sans Mono",Font.BOLD,40));
        
        JButton calculateButton = new JButton("Calculate");		// calculate button
        calculateButton.addActionListener(this);
        
        JButton clearButton = new JButton("Clear");		// clear button
        clearButton.addActionListener(this); 		
        
        calculateButton.setFont(new Font("DejaVu Sans Mono",Font.BOLD,20));	
        calculateButton.setBackground(new Color(88,194,99));
        calculateButton.setBorder(new LineBorder(new Color(66,159,76),2));

        clearButton.setFont(new Font("DejaVu Sans Mono",Font.BOLD,20));
        clearButton.setBackground(new Color(234,53,53));
        clearButton.setBorder(new LineBorder(new Color(193,42,42),2));
        clearButton.setPreferredSize(new Dimension(35,30));
       
        textArea.setEditable(false);
        
        JLabel label = new JLabel("Your current grade is (%):");	//current grade label
		JLabel label2 = new JLabel("The grade you want is (%):");	// wanted grade label
		JLabel label3 = new JLabel("Your final exam is worth (%):");	// final grade worth label
		label.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
		label2.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
		label3.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
		
		JPanel jPanel = new JPanel();		//panel to add calculate and clear button next to each other
		jPanel.setLayout(new GridLayout(0,2,60,2));
		jPanel.setBackground(new Color(220,183,183));
		jPanel.add(clearButton);
		jPanel.add(calculateButton);
		
		//The following add components to the main panel 
		// and sets attributes 
		add(name,c);
		c.gridy = 1; 
		add(label,c);
		c.ipadx = 350; 
		c.gridy = 2; 
		add(curGradeField,c);
		c.ipadx = 60; 
		c.gridy = 3;
		add(label2,c);
		c.ipadx = 350; 
		c.gridy = 4;
		add(wantGradeField,c);
		c.ipadx = 60;
		c.gridy = 5;
		add(label3,c);
		c.ipadx = 350;
		c.gridy = 6;
		add(finalExamField,c);
		c.gridy = 7;
		c.ipadx = 80; 
		add(jPanel,c);
		c.gridx = 0; 
		c.gridy = 8;
		c.ipady = 100; 
		c.ipadx = 350;
		add(new JScrollPane(textArea),c);
	}
	
	//sets actions to the buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Calculate")) {
			textArea.setText(Calcs.gradeCalc(curGradeField.getText(), wantGradeField.getText(), finalExamField.getText()));
			
		}
		
		if (e.getActionCommand().equals("Clear")) {
			textArea.setText("");
			curGradeField.setText("");
			wantGradeField.setText("");
			finalExamField.setText("");
		}
	}
}
