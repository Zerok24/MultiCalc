package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * main frame for the application
 * @author Bunguiu Norales
 *
 */
public class CalcWindow extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FinalGradePanel finalGradePanel = new FinalGradePanel();
	private CalulatorPanel panel = new CalulatorPanel();
	private CurrencyConvertionPanel convertionPanel = new CurrencyConvertionPanel();
	private WordCounter counter = new WordCounter();
	
	public CalcWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,550);
		this.getContentPane().setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		this.setJMenuBar(menuBar());
		add(panel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MutiCalculator");
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * menu bar for the window
	 * @return menu bar
	 */
	public JMenuBar menuBar() {
		
		JMenu menu = new JMenu("Calcs");  
		JMenuBar mb=new JMenuBar();  
		JMenuItem i1, i2,i3,i4;  
		
		i1 = new JMenuItem("Standard Calc");
		i1.addActionListener(this);
		i2 = new JMenuItem("Grade Calc");
		i2.addActionListener(this);
		i3 = new JMenuItem("Currency Calc");
		i3.addActionListener(this);
		i4 = new JMenuItem("Word Calc");
		i4.addActionListener(this);
		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		menu.add(i4);
		mb.add(menu);
		return mb;
	}
	
	/**
	 * sets actions for the menu bar options
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Grade Calc")) {
			panel.setVisible(false);
			convertionPanel.setVisible(false);
			counter.setVisible(false);
			finalGradePanel.setVisible(true);
			add(finalGradePanel, BorderLayout.CENTER);
			setVisible(true);
			
		}else if (e.getActionCommand().equals("Standard Calc") ) {
			finalGradePanel.setVisible(false);
			convertionPanel.setVisible(false);
			counter.setVisible(false);
			panel.setVisible(true);
			add(panel, BorderLayout.CENTER);
		}else if (e.getActionCommand().equals("Currency Calc")) {
			finalGradePanel.setVisible(false);
			panel.setVisible(false);
			counter.setVisible(false);
			convertionPanel.setVisible(true);
			add(convertionPanel, BorderLayout.CENTER);
		}else if (e.getActionCommand().equals("Word Calc")) {
			finalGradePanel.setVisible(false);
			panel.setVisible(false);
			convertionPanel.setVisible(false);
			counter.setVisible(true);	
			add(counter);
		}
		
	}
}
