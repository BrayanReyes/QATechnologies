package org.bookingSuite.web.utils;

/**
 * CreditCard handles basic information related to the booker's credit card.
 * 
 * @author: july.moreno
 * @version: 26/06/2020
 */

public class CreditCard {

	private String cardHolderLastName;
	private String cardType;
	private String cardNumber;
	private String expirationMonth;
	private String expirationYear;
	private String cvcCcode;

	/**
	 * Constructor.
	 *
	 * @param creditCardHolderLastName:  String
	 * @param creditCardType:            String
	 * @param creditCardNumber:          String
	 * @param creditCardExpirationMonth: String
	 * @param creditCardExpirationYear:  String
	 * @param creditCardCvcCode:         String
	 * 
	 */
	public CreditCard(String creditCardHolderLastName, String creditCardType, String creditCardNumber,
			String creditCardExpirationMonth, String creditCardExpirationYear, String creditCardCvcCode) {
		this.cardHolderLastName = creditCardHolderLastName;
		this.cardType = creditCardType;
		this.cardNumber = creditCardNumber;
		this.expirationMonth = creditCardExpirationMonth;
		this.expirationYear = creditCardExpirationYear;
		this.cvcCcode = creditCardCvcCode;
	}

	/**
	 * @return the cardHolderLastName
	 */
	public String getCardHolderLastName() {
		return cardHolderLastName;
	}

	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @return the expirationMonth
	 */
	public String getExpirationMonth() {
		return expirationMonth;
	}

	/**
	 * @return the expirationYear
	 */
	public String getExpirationYear() {
		return expirationYear;
	}

	/**
	 * @return the cvcCcode
	 */
	public String getCvcCcode() {
		return cvcCcode;
	}

	/**
	 * @param cardHolderLastName the cardHolderLastName to set
	 */
	private void setCardHolderLastName(String cardHolderLastName) {
		this.cardHolderLastName = cardHolderLastName;
	}

	/**
	 * @param cardType the cardType to set
	 */
	private void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	private void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @param expirationMonth the expirationMonth to set
	 */
	private void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	/**
	 * @ppublicaram expirationYear the expirationYear to set
	 */
	private void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}

	/**
	 * @param cvcCcode the cvcCcode to set
	 */
	private void setCvcCcode(String cvcCcode) {
		this.cvcCcode = cvcCcode;
	}

}
