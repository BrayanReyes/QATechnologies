package com.globant.web.utils;

/**
 * Parameter to perform a flight search
 * 
 * @author july.moreno
 *
 */

public class FlightsParameters {

	private final String flyingFrom;
	private final String flyingTo;
	private String numberOfAdults = "2";
	private String numberOfChildren = "0";
	private final String[] childrenAge;
	private final int flightDaysForward;
	private final int flightDaysToReturn;
	private int checkInDaysForward;
	private int checkOutDaysToReturn;
	private final String flightsSortCriteria;
	private final int departureFlightOption;
	private final int returnFlightOption;
	private final String bookerFirstName;
	private final String bookerLastName;

	/**
	 * Constructor for perform a Flight Search
	 */
	public FlightsParameters(String flyingFrom, String flyingTo, String numberOfAdults, String numberOfChildren,
			String[] childrenAge, int flightDaysForward, int flightDaysToReturn, String flightsSortCriteria,
			int departureFlightOption, int returnFlightOption, String bookerFirstName, String bookerLastName) {
		this.flyingFrom = flyingFrom;
		this.flyingTo = flyingTo;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
		this.childrenAge = childrenAge;
		this.flightDaysForward = flightDaysForward;
		this.flightDaysToReturn = flightDaysToReturn;
		this.flightsSortCriteria = flightsSortCriteria;
		this.departureFlightOption = departureFlightOption;
		this.returnFlightOption = returnFlightOption;
		this.bookerFirstName = bookerFirstName;
		this.bookerLastName = bookerLastName;
	}


	// Get Methods
	public String getFlyingFrom() {
		return flyingFrom;
	}

	public String getFlyingTo() {
		return flyingTo;
	}

	public String getNumberOfAdults() {
		return numberOfAdults;
	}

	public String getNumberOfChildren() {
		return numberOfChildren;
	}

	public String[] getChildrenAge() {
		return childrenAge;
	}

	public int getFlightDaysForward() {
		return flightDaysForward;
	}

	public int getFlightDaysToReturn() {
		return flightDaysToReturn;
	}

	public int getDepartureFlightOption() {
		return departureFlightOption;
	}

	public int getReturnFlightOption() {
		return returnFlightOption;
	}

	public String getFlightsSortCriteria() {
		return flightsSortCriteria;
	}

	public int getCheckInDaysForward() {
		return checkInDaysForward;
	}

	public int getCheckOutDaysToReturn() {
		return checkOutDaysToReturn;
	}

	public String getBookerFirstName() {
		return bookerFirstName;
	}

	public String getBookerLastName() {
		return bookerLastName;
	}
}
