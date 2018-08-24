package saitMLS.persistance;

/**
 * Broker.java
 * 
 * @author Macarena Hevia
 * @version 1.0
 * 
 * Class Description:  An interface specifying what functionality a broker should contain.
 */
public interface Broker {

	/**
	 * Method to release resources allocated to the broker and save all modified data. NOTE if this method is not called data may be lost
	 */
	public void closeBroker();
	
	/**
	 * Abstract generic method to call data removal method from a back end database structure of some type and brokers the information 
	 * on to the back end so the information can be deleted from the database. 
	 * The Graphical User Interface defined as the front end confirms that the information has been logically deleted.
	 * @param o an Object containing the object information to be deleted from the database
	 * @return a boolean confirming to the GUI that the data has been deleted.
	 */
	public abstract boolean remove(java.lang.Object o);
	
	/**
	 * Abstract generic method to call a data persist method from a back end database structure of some type and brokers the information 
	 * on to the back end so the information can be permanently written to the database. 
	 * The Graphical User Interface defined as the front end confirms that the information has been added correctly.
	 * @param o an Object containing the object information to be added the database.
	 * @return a boolean confirming to the GUI that the data has been added correctly
	 */
	public abstract boolean persist(java.lang.Object o);
	
	/**
	 * Abstract generic method to call search method from a back end database structure of some type and brokers the information on to the 
	 * Graphical User Interface defined as the front end.
	 * @param item a String containing the value of the item being searched for
	 * @param type a String defining the type of search to be performed
	 * @return a List of objects matching the search criteria.
	 */
	@SuppressWarnings("rawtypes")
	public java.util.List search(java.lang.String item, java.lang.String type);
}
