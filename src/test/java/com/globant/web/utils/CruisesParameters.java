package com.globant.web.utils;

/**
 * Parameter to perform a cruise search
 * 
 * @author july.moreno
 *
 */
public class CruisesParameters {

	private String cruiseDestination;
	private int departAsEarly;
	private int departAsLate;

	/**
	 * Constructor to perform a cruise search
	 * 
	 * @param destination
	 * @param departAsEarly
	 * @param departAsLate
	 */
	public CruisesParameters(String destination, int departAsEarly, int departAsLate) {
		this.cruiseDestination = destination;
		this.departAsEarly = departAsEarly;
		this.departAsLate = departAsLate;
	}

	// Get Methods
	public String getCruiseDestination() {
		return cruiseDestination;
	}

	public int getDepartAsEarly() {
		return departAsEarly;
	}

	public int getDepartAsLate() {
		return departAsLate;
	}
}
