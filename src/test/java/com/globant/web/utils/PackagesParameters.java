package com.globant.web.utils;

/**
 * Parameter to perform a vacational package search
 * 
 * @author july.moreno
 *
 */

public class PackagesParameters {

	private final String flyingFrom;
	private final String flyingTo;
	private final int flightDaysForward;
	private final int flightDaysToReturn;
	private final int checkInDaysForward;
	private final int checkOutDaysToReturn;
	private final String adultsNumber = "2";

	/**
	 * Constructor for search a package
	 */
	public PackagesParameters(String flyingFrom, String flyingTo, int flightDaysForward, int flightDaysToReturn,
			int checkInDaysForward, int checkOutDaysToReturn) {
		this.flyingFrom = flyingFrom;
		this.flyingTo = flyingTo;
		this.flightDaysForward = flightDaysForward;
		this.flightDaysToReturn = flightDaysToReturn;
		this.checkInDaysForward = checkInDaysForward;
		this.checkOutDaysToReturn = checkOutDaysToReturn;
	}

	public String getFlyingFrom() {
		return flyingFrom;
	}

	public String getFlyingTo() {
		return flyingTo;
	}

	public int getFlightDaysForward() {
		return flightDaysForward;
	}

	public int getFlightDaysToReturn() {
		return flightDaysToReturn;
	}

	public int getCheckInDaysForward() {
		return checkInDaysForward;
	}

	public int getCheckOutDaysToReturn() {
		return checkOutDaysToReturn;
	}

	public String getAdultsNumber() {
		return adultsNumber;
	}

}