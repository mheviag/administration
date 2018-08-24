package saitMLS.persistance.property;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import saitMLS.exceptions.property.InvalidLegalDescriptionException;
import saitMLS.exceptions.property.InvalidNumberOfBathroomsException;
import saitMLS.persistance.Broker;
import saitMLS.problemDomain.property.CommercialProperty;
import saitMLS.problemDomain.property.ResidentialProperty;
import utility.List;
import utility.SLL;

/**
 * ResidentialPropertyBroker.java - Class describing all attributes and operations for a Residential Property object. 
 * Implements Broker
 * 
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public class ResidentialPropertyBroker implements Broker {

	private static final String INPUT_FILE="res/resprop.txt";
	private long nextId;  
	private static ResidentialPropertyBroker propertyBroker;  
	private java.util.ArrayList<ResidentialProperty> propertyList;  
	private static final String SERIALIZED_FILE="res/resprop.ser";
	private List myList;
	
	/**
	 * Constructor of the ResidentialPropertyBroker class
	 * is called only when the getBroker method is called.
	 */
	private ResidentialPropertyBroker() {
		myList = new SLL();
	}
	
	/**
	 * Method controls the generation of the ResidentialPropertyBroker objects.
	 * @return an instance of propertyBroker
	 * @throws InvalidNumberOfBathroomsException 
	 */
	public static ResidentialPropertyBroker getBroker() {
		File file = new File(SERIALIZED_FILE);
		
		if(file.exists())
		{
			if(propertyBroker == null)
			{
				propertyBroker = new ResidentialPropertyBroker();
			}
		}
		else
		{
			if(propertyBroker == null)
			{
				propertyBroker = new ResidentialPropertyBroker();
				propertyBroker.loadResidentialPropertyBinaryFile();
			}
		}
		propertyBroker.loadResidentialPropertyLinkedList();
		return propertyBroker;
	}
	
	/**
	 * Method that creates a binary file if it does not exist containing the residential property database
	 * @throws InvalidNumberOfBathroomsException 
	 */
	private void loadResidentialPropertyBinaryFile() {
		ResidentialProperty rp = null;
		long id=1;
		try
		{
			ArrayList list = new ArrayList();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
			BufferedReader fin = new BufferedReader(new FileReader(INPUT_FILE));
				
			String line;
				
			while((line=fin.readLine())!=null)
			{
				String[] item = line.split(";");
				
				rp = new ResidentialProperty(id,item[0],item[1],item[2],item[3],
						Double.parseDouble(item[4]),item[5],Double.parseDouble(item[6]),
						Double.parseDouble(item[7]),Integer.parseInt(item[8]),item[9].charAt(0));
				list.add(rp);
				id++;
			}
			System.out.println("created binary file");
			fin.close();
			oos.writeObject(list);
			System.out.println("finished writing file");
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InvalidLegalDescriptionException e) {
			e.printStackTrace();
		} catch (InvalidNumberOfBathroomsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method fills a Singly Linked List as a data structure to 
	 * store residential property information in a object format.
	 */
	private void loadResidentialPropertyLinkedList() {
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIALIZED_FILE));
			
			ArrayList<ResidentialProperty> list = (ArrayList) ois.readObject();
			for(int i = 0; i < list.size(); i++)
			{
				myList.append((list.get(i)));
				//System.out.println(myList.getLast());
				
			}
			ois.close();
			loadSerializedFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method loads the serialized linked list file to propertyList attribute
	 */
	private void loadSerializedFile() {
		propertyList = new ArrayList();
		propertyList.clear();
		for(int i=0; i<myList.size();i++) {
			propertyList.add((ResidentialProperty) myList.get(i));
			//System.out.println(i);
			//System.out.println(myList.get(i));
		}
	}	
	
	/**
	 * Method saves the propertyList to a serialized linked list file.
	 */
	private void saveSerializedFile() {
		loadSerializedFile();
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
			oos.writeObject(propertyList);
			System.out.println("finished writing file");
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to search for the last highest id assigned and save it in the class level attribute nextId.
	 */
	private void  findHighestCurrentId() {
		long highestId=0;
		for(int i=0;i<myList.size();i++) {
			if(((ResidentialProperty) myList.get(i)).getId()>highestId) {
				highestId = ((ResidentialProperty) myList.get(i)).getId();
			}
		}
		nextId=highestId+1;
		//System.out.println("Highest id: " + highestId);
	}
	
	/**
	 * Method to release resources allocated to the broker and save all modified data
	 */
	@Override
	public void closeBroker() {
		saveSerializedFile();
		propertyBroker = null;
	}
	
	/**
	 * Method to remove a ResidentialProperty from the data base.
	 */
	@Override
	public boolean remove(Object o) {
		ResidentialProperty c = (ResidentialProperty) o;
		if(myList.contains(c)) {
			myList.remove(myList.indexOf(c));
			return true;
		}
		return false;
	}
	
	/**
	 * Method to add residential properties to the Linked List database.
	 */
	@Override
	public boolean persist(Object o) {
		ResidentialProperty c = (ResidentialProperty) o;
		if(c.getId()!=0) {
			System.out.println(c.getId());
			myList.set(c, (Integer.parseInt(String.valueOf(c.getId()))-1));
			return true;
		}
		else if(c.getId()==0) {
			findHighestCurrentId();
			c.setId(nextId);
			myList.append(c);
			return true;
		}
		return false;
	}

	/**
	 * Method to search the database for matching items.
	 */
	@Override
	public ArrayList search(String item, String type) {
		ArrayList<ResidentialProperty> searchList = new ArrayList();
		
		if(type.equalsIgnoreCase("id")) {
			searchList = searchId(item);
		}
		else if(type.equalsIgnoreCase("legal description")) {
			searchList = searchLegalDescription(item);
		}
		else if(type.equalsIgnoreCase("quadrant")) {
			searchList = searchQuadrant(item);
		}
		else if(type.equalsIgnoreCase("price")) {
			searchList = searchPrice(item);
		}
		
		return searchList;
	}
	
	/**
	 * Method to search for the property id and return all matching results as a list to the calling method.
	 * @param item the id to search
	 * @return a list of Residential properties matching the search
	 */
	private ArrayList<ResidentialProperty> searchId(String item) {
		ArrayList<ResidentialProperty> searchList = new ArrayList<ResidentialProperty>();
		for(int i=0;i<myList.size();i++) {
			if(((ResidentialProperty) myList.get(i)).getId()==Long.parseLong(item)) {
				searchList.add((ResidentialProperty) myList.get(i));
			}
		}
		return searchList;
	}  

	/**
	 * Method to search for the property legal description and return all matching results as a list to the calling method.
	 * @param item the legal description to search
	 * @return a list of Residential properties matching the search
	 */
	private ArrayList<ResidentialProperty> searchLegalDescription(String item) {
		ArrayList<ResidentialProperty> searchList = new ArrayList();
		for(int i=0;i<myList.size();i++) {
			if(((ResidentialProperty) myList.get(i)).getLegalDescription().equalsIgnoreCase(item)) {
				searchList.add((ResidentialProperty) myList.get(i));
			}
		}
		return searchList;
	}  
	
	/**
	 * Method to search for the property quadrant and return all matching results as a list to the calling method.
	 * @param item the quadrant to search
	 * @return a list of Residential properties matching the search
	 */
	private ArrayList<ResidentialProperty> searchQuadrant(String item) {
		ArrayList<ResidentialProperty> searchList = new ArrayList();
		for(int i=0;i<myList.size();i++) {
			if(((ResidentialProperty) myList.get(i)).getQuadrant().equalsIgnoreCase(item)) {
				searchList.add((ResidentialProperty) myList.get(i));
			}
		}
		return searchList;
	} 

	/**
	 * Method to search for the property price and return all matching results as a list to the calling method.
	 * @param item the price to search
	 * @return a list of Residential properties matching the search
	 */
	private ArrayList<ResidentialProperty> searchPrice(String item) {
		ArrayList<ResidentialProperty> searchList = new ArrayList();
		for(int i=0;i<myList.size();i++) {
			if(((ResidentialProperty) myList.get(i)).getAskingPrice()==Double.parseDouble(item)) {
				searchList.add((ResidentialProperty) myList.get(i));
			}
		}
		return searchList;
	} 
}
