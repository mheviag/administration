package saitMLS.problemDomain.property;

import saitMLS.exceptions.property.InvalidLegalDescriptionException;

/**
 *	Property.java - Abstract Class describing all attributes for a Property object.
 *
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public abstract class Property implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1019049235290531041L;
	private String address;
	private  double askingPrice;
	private String comments;
	private long id;
	private String legalDescription;
	private String quadrant;
	private String zone;
	
	/**
	 * Creates an instance of this object with default values
	 */
	public Property() {
		
	}
	
	/**
	 * Creates an instance of this object with a specified list of initial values.
	 * @param id id of the property
	 * @param legalDescription legal description of the property
	 * @param address address of the property
	 * @param quadrant quadrant of the property
	 * @param zone zone of the property
	 * @param askingPrice asking price of the property
	 * @param comments comments on the property
	 * @throws InvalidLegalDescriptionException
	 */
	public Property(long id, String legalDescription, String address, String quadrant, String zone, double askingPrice, String comments) throws InvalidLegalDescriptionException {
		setId(id);
		setLegalDescription(legalDescription);
		setAddress(address);
		setQuadrant(quadrant);
		setZone(zone);
		setAskingPrice(askingPrice);
		setComments(comments);
	}

	/**
	 * Returns the propertie's address
	 * @return Returns the propertie's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * sets the propertie's address
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the propertie's asking price
	 * @return Returns the propertie's asking price
	 */
	public double getAskingPrice() {
		return askingPrice;
	}

	/**
	 * sets the propertie's asking price
	 * @param askingPrice the asking price to set
	 */
	public void setAskingPrice(double askingPrice) {
		this.askingPrice = askingPrice;
	}

	/**
	 * Returns the propertie's comments
	 * @return Returns the propertie's comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * sets the propertie's comments
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Returns the propertie's id
	 * @return Returns the propertie's id
	 */
	public long getId() {
		return id;
	}

	/**
	 * sets the propertie's id
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns the propertie's legal description
	 * @return Returns the propertie's legal description
	 */
	public String getLegalDescription() {
		return legalDescription;
	}

	/**
	 * sets the propertie's legal description
	 * @param legalDescription the legal description to set
	 * @throws InvalidLegalDescriptionException
	 */
	public void setLegalDescription(String legalDescription) throws InvalidLegalDescriptionException {
		validateLegalDescription(legalDescription);
		this.legalDescription = legalDescription;
	}

	/**
	 * Returns the propertie's quadrant
	 * @return Returns the propertie's quadrant
	 */
	public String getQuadrant() {
		return quadrant;
	}

	/**
	 * sets the propertie's quadrant
	 * @param quadrant the quadrant to set
	 */
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	/**
	 * Returns the propertie's zone
	 * @return Returns the propertie's zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * sets the propertie's zone
	 * @param address the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}
	
	/**
	 * Method to validate the Legal Description is in the correct format of  [0-9999][A-Z][0-9999][-][0-99] and if it 
	 * is not then the method throws an InvalidLegalDescriptionException
	 * @param desc the legal description to validate
	 * @throws InvalidLegalDescriptionException if the legal description format is incorrect
	 */
	private void validateLegalDescription(String desc) throws InvalidLegalDescriptionException{
		if(!desc.matches("(\\d{1,4}[A-Z]{1}\\d{1,4}\\-\\d{1,2})")) {
			throw new InvalidLegalDescriptionException();
		}
	}
}
