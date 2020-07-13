package com.globant.web.utils;

/**
 * Parameter to perform a hotel search
 * 
 * @author july.moreno
 *
 */
public class HotelsParameters {

	private final String destination;
	private final int checkInForward;
	private final int checkOutForward;
	private final String hotelName;
	private final String email;

	/**
	 * Constructor to perform a Hotel search
	 * 
	 * @param destination: String
	 * @param checkInForward: int
	 * @param checkOutForward: int
	 * @param hotelName: String
	 * @param email: String
	 */

	public HotelsParameters(String destination, int checkInForward, int checkOutForward, String hotelName,
			String email) {
		this.destination = destination;
		this.checkInForward = checkInForward;
		this.checkOutForward = checkOutForward;
		this.hotelName = hotelName;
		this.email = email;

	}

	// Get Methods
	public String getDestination() {
		return destination;
	}

	public int getCheckInForward() {
		return checkInForward;
	}

	public int getCheckOutForward() {
		return checkOutForward;
	}

	public String getHotelName() {
		return hotelName;
	}

	public String getEmail() {
		return email;
	}
}
