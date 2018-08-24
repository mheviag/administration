package problemDomain;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import saitMLS.exceptions.clientale.InvalidPhoneNumberException;
import saitMLS.exceptions.clientale.InvalidPostalCodeException;
import saitMLS.persistance.clientale.ClientBroker;
import saitMLS.problemDomain.clientale.Client;

/**
 *	ClientFrame.java - 
 *  Creates a GUI application for adding, updating, removing and saving client properties
 *
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public class ClientFrame {
	
	public ClientBroker cb;
	private ActionListener actionListener;
	
	private JList <String>lstSearchResult;
	private DefaultListModel<String> listModel;
	private JButton btnSearchClient;
	private ArrayList<Client> searchResult;
	private JButton btnClearSearchClient;
	private JButton btnSaveClient;
	private JButton btnDeleteClient;
	private JButton btnClearClient;

	private JTextField textSearch;
	private JLabel lblId;
	private JTextField txtFirst;
	private JTextField txtLast;
	private JTextField txtAddress;
	private JTextField txtPostal;
	private JTextField txtPhone;
	private JRadioButton btn1;
	private JRadioButton btn2;
	private JRadioButton btn3;
	String[] choices = {"Select Type","C","R"};
	private JComboBox<String> cbx;
	/**
	 * Constructs a new ClientFrame object
	 * Initiates the action listener and the ClientBroker
	 */
	public ClientFrame() {
		actionListener = new MyActionListener();
		cb = ClientBroker.getBroker();
	}
	/**
	 * Creates and returns a JPanel object representing the main panel for the Client GUI front end
	 * @return main panel of Client GUI containing all other panels
	 */
	public JPanel getClientPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorthCenterPanel(), BorderLayout.NORTH);
		panel.add(getCenterCenterPanel(), BorderLayout.CENTER);
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the center of the center panel for the Client GUI
	 * @return center of center panel containing a grid of two columns
	 */
	private JPanel getCenterCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(getLeftCenterPanel());
		panel.add(getRightCenterPanel());
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the right of the center panel for the Client GUI
	 * @return right of center panel containing three panels (north, center and south)
	 */
	private JPanel getRightCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorthInfoPanel(), BorderLayout.NORTH);
		panel.add(getCenterInfoPanel(), BorderLayout.CENTER);
		panel.add(getSouthInfoPanel(), BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 10));
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the south of the right or "info" panel for the Client GUI
	 * @return south panel containing three buttons, Save, Delete and Clear
	 */
	private Component getSouthInfoPanel() {
		JPanel panel = new JPanel();
		btnSaveClient = new JButton("Save");
		btnDeleteClient = new JButton("Delete");
		btnClearClient = new JButton("Clear");
		btnSaveClient.addActionListener(actionListener);
		btnDeleteClient.addActionListener(actionListener);
		btnClearClient.addActionListener(actionListener);
		panel.add(btnSaveClient);
		panel.add(btnDeleteClient);
		panel.add(btnClearClient);
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the center of the right or "info" panel for the Client GUI
	 * @return center panel containing 7 different fields or text and combo boxes
	 */
	private Component getCenterInfoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		JLabel label = new JLabel("Client Id:");
		lblId = new JLabel("");
		JLabel label2 = new JLabel("First Name:");
		txtFirst = new JTextField(10);
		JLabel label3 = new JLabel("Last Name:");
		txtLast = new JTextField(10);
		JLabel label4 = new JLabel("Address:");
		txtAddress = new JTextField(10);
		JLabel label5 = new JLabel("Postal Code:");
		txtPostal = new JTextField(10);
		JLabel label6 = new JLabel("Phone Number:");
		txtPhone = new JTextField(10);
		JLabel label7 = new JLabel("Client Type:");
	    cbx = new JComboBox<String>(choices);
	    //cb.setVisible(true);
	    
		label.setForeground(Color.BLACK);
		label.setFont(new Font("TimesRoman",Font.PLAIN,16));

		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("TimesRoman",Font.PLAIN,16));		
		
		label2.setForeground(Color.BLACK);
		label2.setFont(new Font("TimesRoman",Font.PLAIN,16));
		
		txtFirst.setForeground(Color.BLACK);
		txtFirst.setFont(new Font("TimesRoman",Font.PLAIN,16));
		
		label3.setForeground(Color.BLACK);
		label3.setFont(new Font("TimesRoman",Font.PLAIN,16));

		txtLast.setForeground(Color.BLACK);
		txtLast.setFont(new Font("TimesRoman",Font.PLAIN,16));
		
		label4.setForeground(Color.BLACK);
		label4.setFont(new Font("TimesRoman",Font.PLAIN,16));
	
		txtAddress.setForeground(Color.BLACK);
		txtAddress.setFont(new Font("TimesRoman",Font.PLAIN,16));
		
		label5.setForeground(Color.BLACK);
		label5.setFont(new Font("TimesRoman",Font.PLAIN,16));
	
		txtPostal.setForeground(Color.BLACK);
		txtPostal.setFont(new Font("TimesRoman",Font.PLAIN,16));
		
		label6.setForeground(Color.BLACK);
		label6.setFont(new Font("TimesRoman",Font.PLAIN,16));
	
		txtPhone.setForeground(Color.BLACK);
		txtPhone.setFont(new Font("TimesRoman",Font.PLAIN,16));
		
		label7.setForeground(Color.BLACK);
		label7.setFont(new Font("TimesRoman",Font.PLAIN,16));
		
		cbx.setForeground(Color.BLACK);
		cbx.setFont(new Font("TimesRoman",Font.PLAIN,16));

		
	    GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(30,0,0,0);
		panel.add(label, gbc);
		gbc.gridy++;
		panel.add(label2, gbc);
		gbc.gridy++;
		panel.add(label3, gbc);
		gbc.gridy++;
		panel.add(label4, gbc);
		gbc.gridy++;
		panel.add(label5, gbc);
		gbc.gridy++;
		panel.add(label6, gbc);
		gbc.gridy++;
		panel.add(label7, gbc);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx++;
		gbc.gridy = 0;
		panel.add(lblId, gbc);
		gbc.gridy++;
		panel.add(txtFirst, gbc);
		gbc.gridy++;
		panel.add(txtLast, gbc);
		gbc.gridy++;
		panel.add(txtAddress, gbc);
		gbc.gridy++;
		panel.add(txtPostal, gbc);
		gbc.gridy++;
		panel.add(txtPhone, gbc);
		gbc.gridy++;
		panel.add(cbx, gbc);
	    
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the north of the right or "info" panel for the Client GUI
	 * @return north panel containing the title of the info section
	 */
	private Component getNorthInfoPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Client Information");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("TimesRoman",Font.BOLD,16));
		panel.add(label);
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the center of the left panel for the Client GUI
	 * @return center panel containing a grid of two rows, one for search and one to show the list
	 */
	private JPanel getLeftCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(getTopLeftCenterPanel());
		panel.add(getBottomLeftCenterPanel());
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the second row of the left panel for the Client GUI
	 * @return panel containing a scrollPane for the list
	 */
	private JPanel getBottomLeftCenterPanel() {
		JPanel panel = new JPanel();
		listModel = new DefaultListModel<String>();
		lstSearchResult = new JList<String>(listModel);
		lstSearchResult.addListSelectionListener(new MyListSelectionListener());
		JScrollPane scrollPane = new JScrollPane(lstSearchResult);
		lstSearchResult.setFixedCellWidth(350);
		lstSearchResult.setVisibleRowCount(16);
		panel.add(scrollPane);
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the first row of the left panel for the Client GUI
	 * @return panel containing three panels (north, center and south)
	 */
	private JPanel getTopLeftCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorthSearchPanel(), BorderLayout.NORTH);
		panel.add(getCenterSearchPanel(), BorderLayout.CENTER);
		panel.add(getSouthSearchPanel(), BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 10));
		
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the south of the first row from the left panel for the Client GUI
	 * @return panel containing two buttons, and one text box to search
	 */
	private Component getSouthSearchPanel() {
		JPanel panel = new JPanel();
		textSearch = new JTextField(10);
		btnSearchClient = new JButton("Search");
		btnClearSearchClient = new JButton("Clear Search");
		btnSearchClient.addActionListener(actionListener);
		btnClearSearchClient.addActionListener(actionListener);
		panel.add(textSearch);
		panel.add(btnSearchClient);
		panel.add(btnClearSearchClient);
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the center of the first row from the left panel for the Client GUI
	 * @return panel containing radio buttons to decide which parameter to search
	 */
	private Component getCenterSearchPanel() {
		JPanel panel = new JPanel(new GridLayout(5,1));
		JLabel label = new JLabel("Select type of search to be performed");
		JLabel label2 = new JLabel("Enter the search parameter below");
		ButtonGroup group = new ButtonGroup();
		btn1 = new JRadioButton("Client Id");
		btn1.setSelected(true);
		btn2 = new JRadioButton("Last Name");
		btn3 = new JRadioButton("Client Type");
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		
		label.setForeground(Color.BLACK);
		label.setFont(new Font("TimesRoman",Font.PLAIN,16));
		panel.add(label);

		btn1.setForeground(Color.BLACK);
		btn1.setFont(new Font("TimesRoman",Font.PLAIN,16));
		btn2.setForeground(Color.BLACK);
		btn2.setFont(new Font("TimesRoman",Font.PLAIN,16));
		btn3.setForeground(Color.BLACK);
		btn3.setFont(new Font("TimesRoman",Font.PLAIN,16));
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		
		label2.setForeground(Color.BLACK);
		label2.setFont(new Font("TimesRoman",Font.PLAIN,16));
		panel.add(label2);
		
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the north of the first row from the left panel for the Client GUI
	 * @return panel containing the title of the search section
	 */
	private Component getNorthSearchPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Search Clients");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("TimesRoman",Font.BOLD,16));
		panel.add(label);
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the north center panel for the Client GUI
	 * @return panel containing the main title of the client GUI
	 */
	private JPanel getNorthCenterPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Client Management Screen");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("TimesRoman",Font.BOLD,28));
		panel.add(label);
		panel.setBackground(Color.WHITE);
		return panel;
	}
	/**
	 * Clears all text boxes and combo boxes from the Client GUI
	 */
	private void clearForm() {
		listModel.clear();
		lblId.setText("");
		txtFirst.setText("");
		txtLast.setText("");
		txtAddress.setText("");
		txtPostal.setText("");
		txtPhone.setText("");
		cbx.setSelectedIndex(0);
	}
	/**
	 * Clears all text boxes and combo boxes from the info section of the Client GUI
	 */
	private void clearFields() {
		lblId.setText("");
		txtFirst.setText("");
		txtLast.setText("");
		txtAddress.setText("");
		txtPostal.setText("");
		txtPhone.setText("");
		cbx.setSelectedIndex(0);
	}
	/**
	 * Represents a class implementing ActionListener to handle all action events for the ClientFrame class
	 * 
	 * @author Macarena Hevia
	 * @version 1.0
	 */	
	private class MyActionListener implements ActionListener{

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == btnSearchClient) {
					clearForm();
					
					if(btn1.isSelected()) {
						searchResult = (ArrayList<Client>) cb.search(textSearch.getText(), "id");
					}
					else if(btn2.isSelected()) {
						searchResult = (ArrayList<Client>) cb.search(textSearch.getText(), "name");
					}
					else if(btn3.isSelected()) {
						searchResult = (ArrayList<Client>) cb.search(textSearch.getText(), "type");
					}
					
					
					for(Client c : searchResult) {
						listModel.addElement(c.getId() + " " + c.getFirstName() + " " + c.getLastName() + 
								" " + c.getClientType());
					}
					
				}
				else if(e.getSource() == btnClearSearchClient) {
					clearForm();
					textSearch.setText("");
				}
				else if(e.getSource() == btnDeleteClient) {
					int index = lstSearchResult.getSelectedIndex();
					if(index >= 0) {
						cb.remove(searchResult.get(index));
						JOptionPane.showMessageDialog(null, "Client deleted successfully");
						clearForm();
						textSearch.setText("");
					}
				}
				else if(e.getSource() == btnSaveClient) {
					Client c = new Client();
					c.setActive(true);
					if(lblId.getText().toString().equals("")) {
						c.setId(0);
					}
					else {
						c.setId(Long.parseLong(lblId.getText().toString()));
					}
					
					c.setFirstName(txtFirst.getText().toString());
					c.setLastName(txtLast.getText().toString());
					c.setAddress(txtAddress.getText().toString());
					try {
						c.setPostalCode(txtPostal.getText().toString().toUpperCase());
						c.setPhoneNumber(txtPhone.getText().toString());
						c.setClientType(cbx.getSelectedItem().toString().charAt(0));
						if(cb.persist(c))
						JOptionPane.showMessageDialog(null, "Client saved successfully");
						clearForm();
						textSearch.setText("");
					} catch (InvalidPostalCodeException e1) {
						//e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Postal Code format error please follow the correct format (A9A 9A9)");
					}
					catch (InvalidPhoneNumberException e1) {
						//e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Phone format error please follow the correct format (222-222-2222)");
					}
					
				}
				
				else if(e.getSource() == btnClearClient) {
					clearFields();
				}
		}
	}
	/**
	 * Represents a class implementing ListSelectionListener to handle all list selection events for the ClientFrame class
	 * 
	 * @author Macarena Hevia
	 * @version 1.0
	 */
	private class MyListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int index = lstSearchResult.getSelectedIndex();
			if(index >= 0) {
				lblId.setText(Long.toString(searchResult.get(index).getId()));
				txtFirst.setText(searchResult.get(index).getFirstName());
				txtLast.setText(searchResult.get(index).getLastName());
				txtAddress.setText(searchResult.get(index).getAddress());
				txtPostal.setText(searchResult.get(index).getPostalCode());
				txtPhone.setText(searchResult.get(index).getPhoneNumber());
				cbx.setSelectedItem(Character.toString(searchResult.get(index).getClientType()));
			}
			
		}
		
	}
}
