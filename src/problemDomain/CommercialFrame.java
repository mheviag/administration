package problemDomain;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import saitMLS.exceptions.property.InvalidLegalDescriptionException;
import saitMLS.persistance.property.CommercialPropertyBroker;
import saitMLS.problemDomain.property.CommercialProperty;

/**
 *	CommercialFrame.java - 
 *  Creates a GUI application for adding, updating, removing and saving commercial properties
 *
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public class CommercialFrame {

	public CommercialPropertyBroker cb;
	private ActionListener actionListener;
	
	private JList <String>lstSearchResult;
	private DefaultListModel<String> listModel;
	private JButton btnSearchClient;
	private ArrayList<CommercialProperty> searchResult;
	private JButton btnClearSearchClient;
	private JButton btnSaveClient;
	private JButton btnDeleteClient;
	private JButton btnClearClient;

	private JTextField textSearch;
	private JLabel lblId;
	private JTextField txtDescription;
	private JTextField txtAddress;
	String[] choicesQuadrant = {"Select Quadrant","NW", "NE", "SW", "SE"};
	private JComboBox<String> cbxQuadrant;
	String[] choicesZone = {"Select Zone","R1", "R2", "R3", "R4", "I1", "I2", "I3", "I4"};
	private JComboBox<String> cbxZone;
	private JTextField txtPrice;
	private JTextField txtFloors;
	String[] choicesType = {"Select Type","M","O"};
	private JComboBox<String> cbxType;
	private JTextField txtComments;
	private JRadioButton btn1;
	private JRadioButton btn2;
	private JRadioButton btn3;
	private JRadioButton btn4;
	
	/**
	 * Constructs a new CommercialFrame object
	 * Initiates the action listener and the CommercialPropertyBroker
	 */
	public CommercialFrame() {
		actionListener = new MyActionListener();
		cb = CommercialPropertyBroker.getBroker();
	}
	/**
	 * Creates and returns a JPanel object representing the main panel for the Commercial GUI front end
	 * @return main panel of Commercial GUI containing all other panels
	 */
	public JPanel getCommercialPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorthCenterPanel(), BorderLayout.NORTH);
		panel.add(getCenterCenterPanel());
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the center of the center panel for the Commercial GUI
	 * @return center of center panel containing a grid of two columns
	 */
	private JPanel getCenterCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(getLeftCenterPanel());
		panel.add(getRightCenterPanel());
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the right of the center panel for the Commercial GUI
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
	 * Creates and returns a JPanel representing the south of the right or "info" panel for the Commercial GUI
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
	 * Creates and returns a JPanel representing the center of the right or "info" panel for the Commercial GUI
	 * @return center panel containing 9 different fields or text and combo boxes
	 */
	private Component getCenterInfoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		JLabel label = new JLabel("Commercial Property Id:");
		lblId = new JLabel("");
		JLabel label2 = new JLabel("Property Legal Description:");
		txtDescription = new JTextField(10);
		JLabel label3 = new JLabel("Property Address:");
		txtAddress = new JTextField(10);
		JLabel label4 = new JLabel("City Quadrant:");
		cbxQuadrant = new JComboBox<String>(choicesQuadrant);
		JLabel label5 = new JLabel("Zoning of Property:");
		cbxZone = new JComboBox<String>(choicesZone);
		JLabel label6 = new JLabel("Property Asking Price:");
		txtPrice = new JTextField(10);
		JLabel label7 = new JLabel("Property Type:");
		cbxType = new JComboBox<String>(choicesType);
		JLabel label8 = new JLabel("No. of Floors:");
		txtFloors = new JTextField(10);
		JLabel label9 = new JLabel("Comments about Property:");
		txtComments = new JTextField(10);
	    
		label.setForeground(Color.BLACK);
		label.setFont(new Font("TimesRoman",Font.PLAIN,16));
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("TimesRoman",Font.PLAIN,16));		
		label2.setForeground(Color.BLACK);
		label2.setFont(new Font("TimesRoman",Font.PLAIN,16));
		txtDescription.setForeground(Color.BLACK);
		txtDescription.setFont(new Font("TimesRoman",Font.PLAIN,16));
		label3.setForeground(Color.BLACK);
		label3.setFont(new Font("TimesRoman",Font.PLAIN,16));
		txtAddress.setForeground(Color.BLACK);
		txtAddress.setFont(new Font("TimesRoman",Font.PLAIN,16));
		label4.setForeground(Color.BLACK);
		label4.setFont(new Font("TimesRoman",Font.PLAIN,16));
		cbxQuadrant.setForeground(Color.BLACK);
		cbxQuadrant.setFont(new Font("TimesRoman",Font.PLAIN,16));
		label5.setForeground(Color.BLACK);
		label5.setFont(new Font("TimesRoman",Font.PLAIN,16));
		cbxZone.setForeground(Color.BLACK);
		cbxZone.setFont(new Font("TimesRoman",Font.PLAIN,16));
		label6.setForeground(Color.BLACK);
		label6.setFont(new Font("TimesRoman",Font.PLAIN,16));
		txtPrice.setForeground(Color.BLACK);
		txtPrice.setFont(new Font("TimesRoman",Font.PLAIN,16));
		label7.setForeground(Color.BLACK);
		label7.setFont(new Font("TimesRoman",Font.PLAIN,16));
		cbxType.setForeground(Color.BLACK);
		cbxType.setFont(new Font("TimesRoman",Font.PLAIN,16));
		label8.setForeground(Color.BLACK);
		label8.setFont(new Font("TimesRoman",Font.PLAIN,16));
		txtFloors.setForeground(Color.BLACK);
		txtFloors.setFont(new Font("TimesRoman",Font.PLAIN,16));
		label9.setForeground(Color.BLACK);
		label9.setFont(new Font("TimesRoman",Font.PLAIN,16));
		txtComments.setForeground(Color.BLACK);
		txtComments.setFont(new Font("TimesRoman",Font.PLAIN,16));
		
	    GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(16,0,0,0);
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
		gbc.gridy++;
		panel.add(label8, gbc);
		gbc.gridy++;
		panel.add(label9, gbc);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx++;
		gbc.gridy = 0;
		panel.add(lblId, gbc);
		gbc.gridy++;
		panel.add(txtDescription, gbc);
		gbc.gridy++;
		panel.add(txtAddress, gbc);
		gbc.gridy++;
		panel.add(cbxQuadrant, gbc);
		gbc.gridy++;
		panel.add(cbxZone, gbc);
		gbc.gridy++;
		panel.add(txtPrice, gbc);
		gbc.gridy++;
		panel.add(cbxType, gbc);
		gbc.gridy++;
		panel.add(txtFloors, gbc);
		gbc.gridy++;
		panel.add(txtComments, gbc);
	    
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the north of the right or "info" panel for the Commercial GUI
	 * @return north panel containing the title of the info section
	 */
	private Component getNorthInfoPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Commercial Property Information");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("TimesRoman",Font.BOLD,16));
		panel.add(label);
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the center of the left panel for the Commercial GUI
	 * @return center panel containing a grid of two rows, one for search and one to show the list
	 */
	private JPanel getLeftCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(getTopLeftCenterPanel());
		panel.add(getBottomLeftCenterPanel());
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the second row of the left panel for the Commercial GUI
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
	 * Creates and returns a JPanel representing the first row of the left panel for the Commercial GUI
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
	 * Creates and returns a JPanel representing the south of the first row from the left panel for the Commercial GUI
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
	 * Creates and returns a JPanel representing the center of the first row from the left panel for the Commercial GUI
	 * @return panel containing radio buttons to decide which parameter to search
	 */
	private Component getCenterSearchPanel() {
		JPanel panel = new JPanel(new GridLayout(6,1));
		JLabel label = new JLabel("Select type of search to be performed");
		JLabel label2 = new JLabel("Enter the search parameter below");
		ButtonGroup group = new ButtonGroup();
		btn1 = new JRadioButton("Commercial Property Id");
		btn1.setSelected(true);
		btn2 = new JRadioButton("Commercial Property Legal Description");
		btn3 = new JRadioButton("Quadrant of City");
		btn4 = new JRadioButton("Commercial Property Price");
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		group.add(btn4);
		
		label.setForeground(Color.BLACK);
		label.setFont(new Font("TimesRoman",Font.PLAIN,16));
		panel.add(label);

		btn1.setForeground(Color.BLACK);
		btn1.setFont(new Font("TimesRoman",Font.PLAIN,16));
		btn2.setForeground(Color.BLACK);
		btn2.setFont(new Font("TimesRoman",Font.PLAIN,16));
		btn3.setForeground(Color.BLACK);
		btn3.setFont(new Font("TimesRoman",Font.PLAIN,16));
		btn4.setForeground(Color.BLACK);
		btn4.setFont(new Font("TimesRoman",Font.PLAIN,16));
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);
		
		label2.setForeground(Color.BLACK);
		label2.setFont(new Font("TimesRoman",Font.PLAIN,16));
		panel.add(label2);
		
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the north of the first row from the left panel for the Commercial GUI
	 * @return panel containing the title of the search section
	 */
	private Component getNorthSearchPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Search Commercial Property");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("TimesRoman",Font.BOLD,16));
		panel.add(label);
		return panel;
	}
	/**
	 * Creates and returns a JPanel representing the north center panel for the Commercial GUI
	 * @return panel containing the main title of the commercial GUI
	 */
	private JPanel getNorthCenterPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Commercial Property Management Screen");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("TimesRoman",Font.BOLD,28));
		panel.add(label);
		panel.setBackground(Color.WHITE);
		return panel;
	}
	/**
	 * Clears all text boxes and combo boxes from the Commercial GUI
	 */
	private void clearForm() {
		listModel.clear();
		lstSearchResult.setSelectedIndex(-1);
		lblId.setText("");
		txtDescription.setText("");
		txtAddress.setText("");
		cbxQuadrant.setSelectedIndex(0);
		cbxZone.setSelectedIndex(0);
		txtPrice.setText("");
		txtFloors.setText("");
		cbxType.setSelectedIndex(0);
		txtComments.setText("");
	}
	/**
	 * Clears all text boxes and combo boxes from the info section of the Commercial GUI
	 */
	private void clearFields() {
		lblId.setText("");
		txtDescription.setText("");
		txtAddress.setText("");
		cbxQuadrant.setSelectedIndex(0);
		cbxZone.setSelectedIndex(0);
		txtPrice.setText("");
		txtFloors.setText("");
		cbxType.setSelectedIndex(0);
		txtComments.setText("");
	}
	/**
	 * Represents a class implementing ActionListener to handle all action events for the ResidentialFrame class
	 * 
	 * @author Macarena Hevia
	 * @version 1.0
	 */
	private class MyActionListener implements ActionListener{

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSearchClient) {
				clearForm();
				
				if(btn1.isSelected()) {
					searchResult = (ArrayList<CommercialProperty>) cb.search(textSearch.getText(), "id");
				}
				else if(btn2.isSelected()) {
					searchResult = (ArrayList<CommercialProperty>) cb.search(textSearch.getText(), "legal description");
				}
				else if(btn3.isSelected()) {
					searchResult = (ArrayList<CommercialProperty>) cb.search(textSearch.getText(), "quadrant");
				}
				else if(btn4.isSelected()) {
					searchResult = (ArrayList<CommercialProperty>) cb.search(textSearch.getText(), "price");
				}
				
				
				for(CommercialProperty c : searchResult) {
					listModel.addElement(c.getId() + " " + c.getLegalDescription() + " " + c.getQuadrant() + 
							" " + c.getAskingPrice());
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
					JOptionPane.showMessageDialog(null, "Commercial Property deleted successfully");
					clearForm();
					textSearch.setText("");
				}
			}
			else if(e.getSource() == btnSaveClient) {
				CommercialProperty c = new CommercialProperty();
				
				if(lblId.getText().toString().equals("")) {
					c.setId(0);
				}
				else {
					c.setId(Long.parseLong(lblId.getText().toString()));
				}
				
				c.setAddress(txtAddress.getText().toString());
				c.setQuadrant(cbxQuadrant.getSelectedItem().toString());
				c.setZone(cbxZone.getSelectedItem().toString());
				c.setAskingPrice(Double.parseDouble(txtPrice.getText().toString()));
				c.setNoFloors(Integer.parseInt(txtFloors.getText().toString()));
				c.setType(cbxType.getSelectedItem().toString());
				c.setComments(txtComments.getText().toString());
				try {
					c.setLegalDescription(txtDescription.getText().toString().toUpperCase());
					cb.persist(c);
					JOptionPane.showMessageDialog(null, "Commercial Property saved successfully");
					clearForm();
					textSearch.setText("");
				} catch (InvalidLegalDescriptionException e1) {
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Legal Description format error please follow the correct format (0000A0000-00)");
				}
					
				
			}
			else if(e.getSource() == btnClearClient) {
				clearFields();
			}
		}
		
	}
	/**
	 * Represents a class implementing ListSelectionListener to handle all list selection events for the ResidentialFrame class
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
				txtDescription.setText(searchResult.get(index).getLegalDescription());
				txtAddress.setText(searchResult.get(index).getAddress());
				cbxQuadrant.setSelectedItem(searchResult.get(index).getQuadrant().toUpperCase());
				cbxZone.setSelectedItem(searchResult.get(index).getZone().toUpperCase());
				txtPrice.setText(Double.toString(searchResult.get(index).getAskingPrice()));
				txtFloors.setText(Integer.toString(searchResult.get(index).getNoFloors()));
				cbxType.setSelectedItem(searchResult.get(index).getType());
				txtComments.setText(searchResult.get(index).getComments());
			}
			
		}
		
	}
}
