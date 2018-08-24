package problemDomain;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import saitMLS.exceptions.property.InvalidNumberOfBathroomsException;

/**
 *	MyFrame.java - 
 *  Creates a GUI application to show the different frames for Client, Residential and Commercial Properties
 *
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public class MyFrame {


	private JButton btnClient;
	private JButton btnResidential;
	private JButton btnCommercial;
	private ArrayList<JPanel> panelList;
	private ActionListener actionListener;
	private JFrame frame;
	private ClientFrame c;
	private ResidentialFrame r;
	private CommercialFrame co;

	/**
	 * Constructs a new MyFrame object
	 */
	public MyFrame() {
		initialize();
	}
	/**
	 * Initializes the GUI front-end for the MyFrame object
	 * Creates and displays a JFrame object
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(250, 40, 800, 650);
		actionListener = new MyActionListener();
		//actionListener2 = new MyActionListener2();
		panelList = new ArrayList<JPanel>();
		frame.add(getNorthPanel(), BorderLayout.NORTH);
		c = new ClientFrame();
		r = new ResidentialFrame();
		co = new CommercialFrame();
		panelList.add(c.getClientPanel());
		panelList.add(r.getResidentialPanel());
		panelList.add(co.getCommercialPanel());
		
		frame.add(panelList.get(0), BorderLayout.CENTER);

		CloseFrame close = new CloseFrame();
	    frame.addWindowListener(close);
		frame.setVisible(true);
		
	}
	/**
	 * Creates and returns a JPanel representing the north panel for the Residential GUI
	 * @return north panel containing three buttons to switch between frames 
	 */
	private JPanel getNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		
		btnClient = new JButton("Client");
		btnClient.setBorder(buttonEdge);
		btnClient.addActionListener(actionListener);
		
		btnResidential = new JButton("Residential");
		btnResidential.setBorder(buttonEdge);
		btnResidential.addActionListener(actionListener);
		
		btnCommercial = new JButton("Commercial");
		btnCommercial.setBorder(buttonEdge);
		btnCommercial.addActionListener(actionListener);
		
		panel.add(btnClient);
		panel.add(btnResidential);
		panel.add(btnCommercial);
		
		return panel;
	}
	/**
	 * Represents a class implementing ActionListener to handle all action events for the MyFrame class
	 * 
	 * @author Macarena Hevia
	 * @version 1.0
	 */
	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JPanel tp;
			for(int i = 0; i < panelList.size(); i++)
			{
				tp = panelList.get(i);
				frame.remove(tp);
				tp.setVisible(false);
			}
			
			if(e.getSource() == btnClient)
			{
				tp = panelList.get(0);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			else if(e.getSource() == btnResidential)
			{
				tp = panelList.get(1);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			else if(e.getSource() == btnCommercial)
			{
				tp = panelList.get(2);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			
		}
	}
	/**
	 * Represents a class extending WindowAdapter to handle closing of the window
	 * 
	 * @author Macarena Hevia
	 * @version 1.0
	 */
	class CloseFrame extends WindowAdapter
	{
	  public void windowClosing(WindowEvent e)
	  {
		  c.cb.closeBroker();
		  r.rb.closeBroker();
		  co.cb.closeBroker();
		  System.exit(0);
	  }
	}
}