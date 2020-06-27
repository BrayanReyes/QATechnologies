package org.bookingSuite.web.utils;

/**
 * Booker handles basic information related to the Booker.
 * 
 * @author: july.moreno
 * @version: 26/06/2020
 */

public class Booker {

	public String firstName;
	private String lastName;
	private String email;
	private String address;
	private String country;
	private String city;
	private String zipCode;
	private String phoneNumber;

	/**
	 * Constructor.
	 *
	 * @param bookerFirstName:   String
	 * @param bookerLastName:    String
	 * @param bookerEmail:       String
	 * @param bookerAddress:     String
	 * @param bookerCountry:     String
	 * @param bookerCity:        String
	 * @param bookerZipCode:     String
	 * @param bookerPhoneNumber: String
	 * 
	 */
	public Booker(String bookerFirstName, String bookerLastName, String bookerEmail, String bookerAddress,
			String bookerCountry, String bookerCity, String bookerZipCode, String bookerPhoneNumber) {
		this.firstName = bookerFirstName;
		this.lastName = bookerLastName;
		this.email = bookerEmail;
		this.address = bookerAddress;
		this.country = bookerCountry;
		this.city = bookerCity;
		this.zipCode = bookerZipCode;
		this.phoneNumber = bookerPhoneNumber;

	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param firstName the firstName to set
	 */
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param email the email to set
	 */
	private void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param address the address to set
	 */
	private void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param country the country to set
	 */
	private void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param city the city to set
	 */
	private void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	private void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	private void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
