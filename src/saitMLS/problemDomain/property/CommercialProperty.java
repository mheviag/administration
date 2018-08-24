package saitMLS.problemDomain.property;

import saitMLS.exceptions.property.InvalidLegalDescriptionException;

/**
 *	CommercialProperty.java - Abstract Class describing all attributes for a Commercial Property object.
 *
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public class CommercialProperty extends Property implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 114655995578013413L;
	private int noFloors;
	private String type;
	
	/**
	 * Creates an instance of this object with default values
	 */
	public CommercialProperty() {
		
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
	 * @param type type of property
	 * @param noFloors number of floors of the property
	 * @throws InvalidLegalDescriptionException
	 */
	public CommercialProperty(long id, String legalDescription, String address, String quadrant, String zone, 
			double askingPrice, String comments, String type, int noFloors) throws InvalidLegalDescriptionException {
		super(id,legalDescription,address,quadrant,zone,askingPrice,comments);
		setType(type);
		setNoFloors(noFloors);
	}

	/**
	 * Returns the propertie's number of floors
	 * @return Returns the propertie's number of floors
	 */
	public int getNoFloors() {
		return noFloors;
	}

	/**
	 * sets the propertie's number of floors
	 * @param noFloors the propertie's number of floors
	 */
	public void setNoFloors(int noFloors) {
		this.noFloors = noFloors;
	}

	/**
	 * Returns the propertie's type
	 * @return Returns the propertie's type
	 */
	public String getType() {
		return type;
	}
 
	/**
	 * sets the propertie's type
	 * @param type the propertie's type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	/**
	 * Returns a String with the values of the Commercial Property object
	 */
	public String toString() {
		return super.getId() + " " + super.getLegalDescription() + " " + super.getAddress() + " " + super.getQuadrant() + " " + super.getZone() + " " + super.getAskingPrice() + " " + super.getComments() + " " + type + " " + noFloors;
		
	}
	
	
	
}
