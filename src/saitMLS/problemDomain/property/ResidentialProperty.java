package saitMLS.problemDomain.property;

import saitMLS.exceptions.property.InvalidLegalDescriptionException;
import saitMLS.exceptions.property.InvalidNumberOfBathroomsException;

/**
 *	ResidentialProperty.java - Abstract Class describing all attributes for a Residential Property object.
 *
 * @author Macarena Hevia
 * @version 1.0
 * 
 */
public class ResidentialProperty extends Property implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6433259502014365260L;
	private double area;
	private double bathrooms;
	private int bedrooms;
	private char garage;
	
	/**
	 * Creates an instance of this object with default values
	 */
	public ResidentialProperty() {
		
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
	 * @param area the area of the property
	 * @param bathrooms the number of bathrooms of the property
	 * @param bedrooms the number of bedrooms of the property
	 * @param garage a char which has a value of 'a', 'd' or 'n' representing attached, detached or no garage
	 * @throws InvalidLegalDescriptionException 
	 * @throws InvalidNumberOfBathroomsException 
	 */
	public ResidentialProperty(long id, String legalDescription, String address, String quadrant, 
	String zone, double askingPrice, String comments, double area, double bathrooms, int bedrooms, char garage) 
	throws InvalidLegalDescriptionException, InvalidNumberOfBathroomsException {
		
		super(id,legalDescription,address,quadrant,zone,askingPrice,comments);
		setArea(area);
		setBathrooms(bathrooms);
		setBedrooms(bedrooms);
		setGarage(garage);
	}
	
	/**
	 * Returns the propertie's area
	 * @return Returns the propertie's area
	 */
	public double getArea() {
		return area;
	}

	/**
	 * sets the propertie's area
	 * @param area the propertie's area
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/**
	 * Returns the propertie's number of bathrooms
	 * @return Returns the propertie's number of bathrooms
	 */
	public double getBathrooms() {
		return bathrooms;
	}

	/**
	 * sets the propertie's number of bathrooms
	 * @param bathrooms the number of bathrooms
	 * @throws InvalidNumberOfBathroomsException
	 */
	public void setBathrooms(double bathrooms) throws InvalidNumberOfBathroomsException {
		validateNumberOfBathrooms(bathrooms);
		this.bathrooms = bathrooms;
	}

	/**
	 * Returns the propertie's number of bedrooms
	 * @return Returns the propertie's number of bedrooms
	 */
	public int getBedrooms() {
		return bedrooms;
	}

	/**
	 * sets the propertie's number of bedrooms
	 * @param bedrooms the number of bedrooms
	 */
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	/**
	 * Returns the propertie's garage type
	 * @return Returns the propertie's garage type
	 */
	public char getGarage() {
		return garage;
	}

	/**
	 * sets the propertie's quadrant
	 * @param garage the propertie's garage type
	 */
	public void setGarage(char garage) {
		this.garage = garage;
	}
	
	/**
	 * Method to validate the number of bathrooms end in either .0 or .5 and if it 
	 * does not then the method throws an InvalidNumberOfBathroomsException
	 * @param bathrooms the number of bathrooms to validate
	 * @throws InvalidNumberOfBathroomsException
	 */
	private void validateNumberOfBathrooms(double bathrooms) throws InvalidNumberOfBathroomsException {
		if(bathrooms % 0.5 != 0) {
			throw new InvalidNumberOfBathroomsException();
		}
	}
}
