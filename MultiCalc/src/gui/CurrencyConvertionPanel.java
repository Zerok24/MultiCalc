package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.json.JSONException;

import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import com.ritaja.xchangerate.util.Currency;

import calculations.Conversion;


/**
 * Currency Calculator panel
 * @author Bunguiu Norales
 *
 */
public class CurrencyConvertionPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	 
	//iniatialized all the GUI components
    private JComboBox<String> convertFrom, convertTo;
    private JTextField txtFrom, txtTo;
    private JButton compute, clear;
    private JLabel from, to;
    private JLabel lblFrom, lblTo;
    private Conversion convertion;
    
    /**
     * default constructor
     * sets all GUI components for the panel
     */
    public CurrencyConvertionPanel() {

        //add all the GUI components
        setSize(420,300);
        setLayout(null);
        setBackground(new Color(220,183,183));
        
        JLabel name = new JLabel("Currency Calc");
		name.setFont(new Font("DejaVu Sans Mono",Font.BOLD,40));
		name.setBounds(50, 0, 300, 100);
        add(name);
        
        lblFrom = new JLabel("From:");
        lblFrom.setBounds(30,75,250,30);
        lblFrom.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
        add(lblFrom);

        convertFrom = new JComboBox<String>();
        convertFrom.setBounds(30,100,150,30);
		convertFrom.setBackground(new Color(175,175,175));
        convertFrom.setEditable(true);
        
        convertTo = new JComboBox<String>();
        convertTo.setEditable(true);
        convertTo.setBounds(200,100,150,30);
		convertTo.setBackground(new Color(175,175,175));
        dropDownFiller();
        add(convertFrom);
        
        lblTo = new JLabel("To:");
        lblTo.setBounds(200,75,250,30);
        lblTo.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
        add(lblTo);
        
        add(convertTo);

        from = new JLabel("Enter Amount to Convert:");
        from.setBounds(20,140,300,30);
        from.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
        add(from);

        txtFrom = new JTextField(15);
        txtFrom.setBounds(20,170,350,40);
        add(txtFrom);
        
        clear = new JButton("Clear");
        clear.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
        clear.setBackground(new Color(234,53,53));
        clear.setBorder(new LineBorder(new Color(193,42,42),2));
        clear.setBounds(100,330,100,40);

        add(clear);

        to = new JLabel("Total Amount Converted:");
        to.setBounds(20,210,300,30);
        to.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
        add(to);  

        txtTo = new JTextField(15);
        txtTo.setBounds(20,240,350,40);
        txtTo.setEditable(false);
        add(txtTo);

        compute = new JButton("Compute");
        compute.setBackground(new Color(88,194,99));
        compute.setBorder(new LineBorder(new Color(66,159,76),2));
        compute.setFont(new Font("DejaVu Sans Mono",Font.BOLD,15));
        compute.setBounds(210,330,100,40);
        add(compute);
       
        //register all the GUI components who will listen or invoke the methods
        compute.addActionListener(this);
        txtFrom.addActionListener(this);
        clear.addActionListener(this);
        
    }
    
    
    /**
     * fills the drop down menu with the currency options
     */
    public void dropDownFiller() {
		for (Currency currency: Currency.values()) {
			convertFrom.addItem(currency.toString());
			convertTo.addItem(currency.toString());
		}
		convertFrom.setSelectedIndex(148);
		convertTo.setSelectedIndex(48);
	}
    
    
    // sets actions for the compute and clear buttons 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if (command.equals("Compute") && !txtFrom.getText().equals("")) {
			convertion = new Conversion(BigDecimal.valueOf(Double.parseDouble(txtFrom.getText())), convertFrom.getSelectedItem().toString(),
										convertTo.getSelectedItem().toString());
			
			try {
				txtTo.setText(convertion.convert().toString());
			} catch (CurrencyNotSupportedException | JSONException | StorageException | EndpointException
					| ServiceException e1) {
				e1.printStackTrace();
			}
			
		}else if ( command.equals("Clear") ) {
			txtFrom.setText("");
			txtTo.setText("");
		}
	}
}
