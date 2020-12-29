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
import javax.swing.border.LineBorder;

import calculations.Calcs;

/**
 * Word counter panel
 * @author Bunguiu Norales
 *
 */
public class WordCounter extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JLabel charCount;
	private JLabel wordCount;
	
	/**
	 * default constructor 
	 */
	public WordCounter() {
		
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
		JLabel label2 = new JLabel("   Word Calc");
		label2.setFont(new Font("DejaVu Sans Mono",Font.BOLD,40));
		
		JLabel label = new JLabel("Enter your text here:");	
		label.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
		textArea = new JTextArea(10,30);
		textArea.setEditable(true);
		
		add(label2,c);
		c.gridy = 2;
		add(label,c);
		c.gridx = 0; 
		c.gridy = 3;
		c.ipady = 200; 
		c.ipadx = 350;
		add(new JScrollPane(textArea),c);
		c.gridx = 0; 
		c.gridy = 4;
		c.ipady = 15; 
		c.ipadx = 100;
		add(buttonPanel(),c);
		c.gridy = 5;
		add(infoPanel(),c);
	}
	
	/**
	 * creates a button panel
	 * @return button panel
	 */
	public JPanel buttonPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2,100,20));
		panel.setBackground(new Color(220,183,183));
		
		JButton count = new JButton("Count");
		count.addActionListener(this);
		count.setPreferredSize(new Dimension(60,60));
		count.setPreferredSize(new Dimension(20,20));
        count.setFont(new Font("DejaVu Sans Mono",Font.BOLD,20));	
        count.setBackground(new Color(88,194,99));
        count.setBorder(new LineBorder(new Color(66,159,76),2));
        
		JButton clear = new JButton("Clear");
		clear.addActionListener(this);
		clear.setPreferredSize(new Dimension(20,20));
		clear.setFont(new Font("DejaVu Sans Mono",Font.BOLD,20));
        clear.setBackground(new Color(234,53,53));
        clear.setBorder(new LineBorder(new Color(193,42,42),2));
		
		panel.add(count);
		panel.add(clear);
		return panel;
	}
	
	/**
	 * contains labels for information of the text area
	 * @return a panel
	 */
	public JPanel infoPanel() {
		JPanel panel = new JPanel();
		
		panel.setBackground(new Color(220,183,183));
		panel.setLayout(new GridLayout(2,1,2,20));
		
		charCount = new JLabel("        Character count is:");
		charCount.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
		
		wordCount = new JLabel("        Word count is: ");
		wordCount.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
		
		panel.add(charCount);
		panel.add(wordCount);
		return panel;
	}

	/**
	 * sets actions for the buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command =e.getActionCommand();
		
		if (command.equals("Count")) {
			charCount.setText("        Character count is: " + Calcs.charNumber(textArea.getText()));
			wordCount.setText("        Word count is: " + Calcs.numberOfWords(textArea.getText()));
		}else if (command.equals("Clear")) {
			textArea.setText("");
			charCount.setText("        Character count is: ");
			wordCount.setText("        Word count is: ");
		}	
	}
}
