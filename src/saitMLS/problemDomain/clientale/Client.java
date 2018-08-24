package saitMLS.problemDomain.clientale;

import saitMLS.exceptions.clientale.*;

/**
 *	Client.java - Class describing all attributes and operations for a Client object.
 *
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public class Client implements java.io.Serializable{

	//Constants
	private static long serialVersionUID=4578131164381081053L;
	public static final int SIZE=130;
		
	//Attributes
	private boolean active;
	private java.lang.String address;
	private char clientType;
	private java.lang.String firstName;
	private long id;
	private java.lang.String lastName;
	private java.lang.String phoneNumber;
	private java.lang.String postalCode;
	/**
	 * Creates an instance of this object with default values
	 */
	public Client() {
		active = true;
	}
	/**
	 * Creates an instance of this object with a specified list of initial values.
	 * @param id id of the client
	 * @param firstName first name of the client
	 * @param lastName last name of the client
	 * @param address address of the client
	 * @param postalCode postal code of the client
	 * @param phoneNumber phone number of the client
	 * @param clientType type of client (R, C)
	 * @throws InvalidPhoneNumberException 
	 * @throws InvalidPostalCodeException
	 */
	public Client(long id, java.lang.String firstName, java.lang.String lastName, java.lang.String address, java.lang.String postalCode, java.lang.String phoneNumber, char clientType) throws InvalidPhoneNumberException, InvalidPostalCodeException{
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setPostalCode(postalCode);
		setPhoneNumber(phoneNumber);
		setClientType(clientType);
		active = true;
	}
	/**
	 * Creates an instance of this object with a specified list of initial values based on the string input..
	 * @param line string containing all parameters of a client object
	 * @throws InvalidPhoneNumberException
	 * @throws InvalidPostalCodeException
	 */
	public Client(java.lang.String line) throws InvalidPhoneNumberException, InvalidPostalCodeException{
		String elements[] = line.split(";");
		this.id=1;
		setFirstName(elements[0]);
		setLastName(elements[1]);
		setAddress(elements[2]);
		setPostalCode(elements[3]);
		setPhoneNumber(elements[4]);
		setClientType(elements[5].charAt(0));
		active = true;
	}
	/**
	 * Returns the record's current status.
	 * @return client's status
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * Sets the record's current status.
	 * @param active the current status to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * Returns the customer's current address.
	 * @return the customer's current address.
	 */
	public java.lang.String getAddress() {
		return address;
	}
	/**
	 * Sets the customer's current address
	 * @param address address to set
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	/**
	 * Returns the customer's client type
	 * @return the customer's client type
	 */
	public char getClientType() {
		return clientType;
	}
	/**
	 * sets the customer's client type
	 * @param clientType the type to set
	 */
	public void setClientType(char clientType) {
		this.clientType = clientType;
	}
	/**
	 * Returns the customer's first name
	 * @return the customer's first name
	 */
	public java.lang.String getFirstName() {
		return firstName;
	}
	/**
	 * sets the customer's first name
	 * @param firstName the first name to set
	 */
	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Returns the customer's membership id number
	 * @return the customer's membership id number
	 */
	public long getId() {
		return id;
	}
	/**
	 * sets the customer's membership id number
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Returns the customer's last name
	 * @return the customer's last name
	 */
	public java.lang.String getLastName() {
		return lastName;
	}
	/**
	 * sets the customer's last name
	 * @param lastName the last name to set
	 */
	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Returns the customer's current phone number
	 * @return the customer's current phone number
	 */
	public java.lang.String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * sets the customer's current phone number
	 * @param phoneNumber the phone number to set
	 * @throws InvalidPhoneNumberException
	 */
	public void setPhoneNumber(java.lang.String phoneNumber) throws InvalidPhoneNumberException {
		validatePhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Returns the customer's current postal code
	 * @return the customer's current postal code
	 */
	public java.lang.String getPostalCode() {
		return postalCode;
	}
	/**
	 * sets the customer's current postal code
	 * @param postalCode the postal code to set
	 * @throws InvalidPostalCodeException
	 */
	public void setPostalCode(java.lang.String postalCode) throws InvalidPostalCodeException {
		validatePostalCode(postalCode);
		this.postalCode = postalCode;
	}
	@Override
	public boolean equals(java.lang.Object o) {
		return false;
	}
	/**
	 * Returns a String representation of the Customer class.
	 */
	public java.lang.String toString(){
		return id + ", " + firstName + " " + lastName + ", " + address + ", " + postalCode + ", " + phoneNumber + ", " + clientType; 
	}
	/**
	 * Method to validate the Postal Code is in the correct 
	 * format of A9A 9A9 and if it is not then the method throws an InvalidPostalCodeException.
	 * @param pc the postal code to validate
	 * @throws InvalidPostalCodeException if the postal code format is incorrect
	 */
	private void validatePostalCode(java.lang.String pc) throws InvalidPostalCodeException{
		if(!pc.matches("([A-Z]\\d[A-Z]\\s\\d[A-Z]\\d)")) {
			throw new InvalidPostalCodeException();
		}
	}
	/**
	 * Method to validate the Phone Number is in the correct format of 111-111-1111 and if it 
	 * is not then the method throws an InvalidPhoneNumberException
	 * @param pn the phone number to validate
	 * @throws InvalidPhoneNumberException if the phone number format is incorrect
	 */
	private void validatePhoneNumber(java.lang.String pn) throws InvalidPhoneNumberException{
		if(!pn.matches("([0-9]{3}\\-[0-9]{3}\\-[0-9]{4})")) {
			throw new InvalidPhoneNumberException();
		}
	}

}
