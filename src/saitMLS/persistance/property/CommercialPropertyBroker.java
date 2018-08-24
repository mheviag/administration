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
import saitMLS.persistance.Broker;
import saitMLS.problemDomain.property.CommercialProperty;
import utility.List;
import utility.SLL;

/**
 * CommercialPropertyBroker.java - Class describing all attributes and operations for a Commercial Property object. 
 * Implements Broker
 * 
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public class CommercialPropertyBroker implements Broker {
	
	private static final String INPUT_FILE="res/comprop.txt";
	private long nextId;  
	private static CommercialPropertyBroker propertyBroker;  
	private java.util.ArrayList<CommercialProperty> propertyList;  
	private static final String SERIALIZED_FILE="res/comprop.ser";
	private List myList;
	
	/**
	 * Constructor of the CommercialPropertyBroker class
	 * is called only when the getBroker method is called.
	 */
	private CommercialPropertyBroker() {
		myList = new SLL();
	}
	
	/**
	 * Method controls the generation of the CommercialPropertyBroker objects.
	 * @return an instance of propertyBroker
	 */
	public static CommercialPropertyBroker getBroker() {
		File file = new File(SERIALIZED_FILE);
		
		if(file.exists())
		{
			if(propertyBroker == null)
			{
				propertyBroker = new CommercialPropertyBroker();
			}
		}
		else
		{
			if(propertyBroker == null)
			{
				propertyBroker = new CommercialPropertyBroker();
				propertyBroker.loadCommercialPropertyBinaryFile();
			}
		}
		propertyBroker.loadCommercialPropertyLinkedList();
		return propertyBroker;
	}
	
	/**
	 * Method that creates a binary file if it does not exist containing the commercial property database
	 */
	private void loadCommercialPropertyBinaryFile() {
		CommercialProperty cp = null;
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
				
				cp = new CommercialProperty(id,item[0],item[1],item[2],item[3],
						Double.parseDouble(item[4]),item[5],item[6],Integer.parseInt(item[7]));
				list.add(cp);
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
		}
	}
	
	/**
	 * Method fills a Singly Linked List as a data structure to 
	 * store commercial property information in a object format.
	 */
	private void loadCommercialPropertyLinkedList() {
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIALIZED_FILE));
			
			ArrayList<CommercialProperty> list = (ArrayList) ois.readObject();
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
			propertyList.add((CommercialProperty) myList.get(i));
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
			if(((CommercialProperty) myList.get(i)).getId()>highestId) {
				highestId = ((CommercialProperty) myList.get(i)).getId();
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
	 * Method to remove a CommercialProperty from the data base.
	 */
	@Override
	public boolean remove(Object o) {
		CommercialProperty c = (CommercialProperty) o;
		if(myList.contains(c)) {
			myList.remove(myList.indexOf(c));
			return true;
		}
		return false;
	}
	
	/**
	 * Method to add commercial properties to the Linked List database.
	 */
	@Override
	public boolean persist(Object o) {
		CommercialProperty c = (CommercialProperty) o;
		if(c.getId()!=0) {
			System.out.println("Id: "+(Integer.parseInt(String.valueOf(c.getId()))-1));
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
		ArrayList<CommercialProperty> searchList = new ArrayList();
		
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
	 * @return a list of Commercial properties matching the search
	 */
	private ArrayList<CommercialProperty> searchId(String item) {
		ArrayList<CommercialProperty> searchList = new ArrayList<CommercialProperty>();
		for(int i=0;i<myList.size();i++) {
			if(((CommercialProperty) myList.get(i)).getId()==Long.parseLong(item)) {
				searchList.add((CommercialProperty) myList.get(i));
			}
		}
		return searchList;
	}  

	/**
	 * Method to search for the property legal description and return all matching results as a list to the calling method.
	 * @param item the legal description to search
	 * @return a list of Commercial properties matching the search
	 */
	private ArrayList<CommercialProperty> searchLegalDescription(String item) {
		ArrayList<CommercialProperty> searchList = new ArrayList();
		for(int i=0;i<myList.size();i++) {
			if(((CommercialProperty) myList.get(i)).getLegalDescription().equalsIgnoreCase(item)) {
				searchList.add((CommercialProperty) myList.get(i));
			}
		}
		return searchList;
	}  
	
	/**
	 * Method to search for the property quadrant and return all matching results as a list to the calling method.
	 * @param item the quadrant to search
	 * @return a list of Commercial properties matching the search
	 */
	private ArrayList<CommercialProperty> searchQuadrant(String item) {
		ArrayList<CommercialProperty> searchList = new ArrayList();
		for(int i=0;i<myList.size();i++) {
			if(((CommercialProperty) myList.get(i)).getQuadrant().equalsIgnoreCase(item)) {
				searchList.add((CommercialProperty) myList.get(i));
			}
		}
		return searchList;
	} 

	/**
	 * Method to search for the property price and return all matching results as a list to the calling method.
	 * @param item the price to search
	 * @return a list of Commercial properties matching the search
	 */
	private ArrayList<CommercialProperty> searchPrice(String item) {
		ArrayList<CommercialProperty> searchList = new ArrayList();
		for(int i=0;i<myList.size();i++) {
			if(((CommercialProperty) myList.get(i)).getAskingPrice()==Double.parseDouble(item)) {
				searchList.add((CommercialProperty) myList.get(i));
			}
		}
		return searchList;
	} 
}
