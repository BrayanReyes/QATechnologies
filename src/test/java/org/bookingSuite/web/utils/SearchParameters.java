package org.bookingSuite.web.utils;

/**
 * Search Stay Parameters handles basic information needed to search an stay and
 * select a lodging option
 * 
 * @author: july.moreno
 * @version: 26/06/2020
 */

public class SearchParameters {

	private String destination;
	private int daysForward;
	private int stayDuration;
	private int numberOfAdults;
	private int numberOfChildren;
	private String[] childrenAge;
	private int numberOfRooms = 1;
	private String confirmNumberOfRooms;
	private String numberOfStars;
	private int stayOptionToSelect;

	/**
	 * Constructor.
	 *
	 */
	public SearchParameters() {

	}

	/**
	 * Constructor.
	 *
	 * @param destination:               String
	 * @param daysForward:               int
	 * @param stayDuration:              int
	 * @param adultsNumber:              int
	 * @param childrenNumber:            int
	 * @param childrenAge:               String[]
	 * @param lodgingConfirmRoomsNumber: String
	 * @param stayStarsNumber:           String
	 * @param stayOptionToSelect:        int
	 * 
	 * 
	 */

	public SearchParameters(String destination, int daysForward, int stayDuration, int stayAdultsNumber,
			int stayChildrenNumber, String[] childrenAge, String lodgingConfirmRoomsNumber, String stayStarsNumber,
			int stayOptionToSelect) {

		this.destination = destination;
		this.daysForward = daysForward;
		this.stayDuration = stayDuration;
		this.numberOfAdults = stayAdultsNumber;
		this.numberOfChildren = stayChildrenNumber;
		this.childrenAge = childrenAge;
		this.confirmNumberOfRooms = lodgingConfirmRoomsNumber;
		this.numberOfStars = stayStarsNumber;
		this.stayOptionToSelect = stayOptionToSelect;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @return the numberOfAdults
	 */
	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	/**
	 * @return the numberOfChildren
	 */
	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	/**
	 * @return the numberOfRooms
	 */
	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	/**
	 * @return the childrenAge
	 */
	public String[] getChildrenAge() {
		return childrenAge;
	}

	/**
	 * @return the numberOfStars
	 */
	public String getNumberOfStars() {
		return numberOfStars;
	}

	/**
	 * @return the confirmNumberOfRooms
	 */
	public String getConfirmNumberOfRooms() {
		return confirmNumberOfRooms;
	}

	/**
	 * @return the stayStartDate
	 */
	public int getStayStartDate() {
		return daysForward;
	}

	/**
	 * @return the stayEndDate
	 */
	public int getStayEndDate() {
		return stayDuration;
	}

	/**
	 * @return the lodgingOptionToSelect
	 */
	public int getLodgingOptionToSelect() {
		return stayOptionToSelect;
	}

	/**
	 * @param destination the destination to set
	 */
	private void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @param numberOfAdults the numberOfAdults to set
	 */
	private void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	/**
	 * @param numberOfChildren the numberOfChildren to set
	 */
	private void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	/**
	 * @param numberOfRooms the numberOfRooms to set
	 */
	private void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	/**
	 * @param childrenAge the childrenAge to set
	 */
	private void setChildrenAge(String[] childrenAge) {
		this.childrenAge = childrenAge;
	}

	/**
	 * @param numberOfStars the numberOfStars to set
	 */
	private void setNumberOfStars(String numberOfStars) {
		this.numberOfStars = numberOfStars;
	}

	/**
	 * @param lodgingOptionToSelect the lodgingOptionToSelect to set
	 */
	private void setLodgingOptionToSelect(int lodgingOptionToSelect) {
		this.stayOptionToSelect = lodgingOptionToSelect;
	}

	/**
	 * @param confirmNumberOfRooms the confirmNumberOfRooms to set
	 */
	private void setConfirmNumberOfRooms(String confirmNumberOfRooms) {
		this.confirmNumberOfRooms = confirmNumberOfRooms;
	}

	/**
	 * @param stayStartDate the stayStartDate to set
	 */
	private void setStayStartDate(int stayStartDate) {
		this.daysForward = stayStartDate;
	}

	/**
	 * @param stayEndDate the stayEndDate to set
	 */
	private void setStayEndDate(int stayEndDate) {
		this.stayDuration = stayEndDate;
	}
}
