package org.bookingSuite.web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Payment Information Page handles the information related with the location of the booker
 * and the payment data
 *
 */

public class PaymentInformationPage extends BasePage {

	// Booker Details Box
	@FindBy(css = ".booker-details")
	private WebElement bookerDetailsBox;

	@FindBy(css = ".booker-details .bp_legacy_form_box__title")
	private WebElement bookerDetailsTitle;

	@FindBy(id = "address1")
	private WebElement addressInput;

	@FindBy(id = "city")
	private WebElement cityInput;

	@FindBy(id = "zip")
	private WebElement zipCodeInput;

	@FindBy(id = "cc1")
	private WebElement countrySelector;

	@FindBy(id = "phone")
	private WebElement phoneNumberInput;

	// Payment Details Box
	@FindBy(id = "bs3_cc_form")
	private WebElement paymentDetailsBox;

	@FindBy(css = "#bs3_cc_form .bp_legacy_form_box__title")
	private WebElement paymentDetailsTitle;

	@FindBy(id = "cc_name")
	private WebElement cardHolderNameInput;

	@FindBy(id = "cc_type")
	private WebElement cardTypeSelector;

	@FindBy(id = "cc_number")
	private WebElement cardNumberInput;

	@FindBy(id = "cc_month")
	private WebElement cardExpirationMonthSelector;

	@FindBy(id = "ccYear")
	private WebElement cardExpirationYearSelector;

	@FindBy(id = "cc_cvc")
	private WebElement cvcInput;

	// WebElements for assertions
	@FindBy(css = "li:last-child strong")
	private WebElement pageProgressTitle;

	@FindBy(css = "div[class$=\"summary\"] div:nth-child(3) div[class$=\"li_content\"]")
	private WebElement guestsGroupSideBar;

	@FindBy(css = "#label_email .personal_details_reassurance_email_text")
	private WebElement emailReassurance;

	@FindBy(id = "book_credit_card")
	private WebElement paymentDataDiv;

	@FindBy(css = "button[name*=\"review\"]")
	private WebElement checkYourDataButton;

	@FindBy(css = "button[class*=\"book\"]")
	private WebElement completeBookingButton;


	/**
	 * Constructor.
	 * 
	 * @param driver: WebDriver
	 */

	PaymentInformationPage(WebDriver driver) {
		super(driver);
		handleUsersModal();
	}

	/**
	 * Validate if Booker Details Box is present
	 *
	 * @return true
	 */
	public boolean bookerDetailsBoxIsPresent() {
		waitElementVisibility(bookerDetailsBox);
		return bookerDetailsBox.isDisplayed();
	}


	/**
	 * Get title from Booker Details Box
	 * 
	 * @return Title: String
	 */
	public String getBookerDetailsTitle() {
		return bookerDetailsTitle.getText();
	}


	/**
	 * Enter {address} to the {addressInput} text field
	 *
	 * @param address: String
	 */
	private void enterAddress(String address) {
		try {
			if (addressInput.isDisplayed()) {
				waitElementVisibility(addressInput);
				addressInput.sendKeys(address);
			}
		}catch (NoSuchElementException e){
			log.info("Address text field is not present.");
		}
	}

	/**
	 * Enter {city} to the {cityInput} text field
	 *
	 * @param city String
	 */
	private void enterCity(String city) {
		try {
			if (cityInput.isDisplayed()){
				waitElementVisibility(cityInput);
				cityInput.sendKeys(city);
			}
		}catch (NoSuchElementException e){
			log.info("City text field is not present.");
		}
	}

	/**
	 * Enter {zipCode} to the {zipCodeInput} text field
	 *
	 * @param zipCode String
	 */
	private void enterZipCode(String zipCode) {
		try {
		if (zipCodeInput.isDisplayed()){
			waitElementVisibility(zipCodeInput);
			zipCodeInput.sendKeys(zipCode);
		}
		}catch (NoSuchElementException e){
			log.info("Zip code text field is not present.");
		}
	}

	/**
	 * Select {country} as value for the {countrySelector}
	 * 
	 * @param country: String
	 */
	private void selectCountry(String country) {
		waitElementVisibility(countrySelector);
		selectElementFromDropDownList(countrySelector, country);
	}

	/**
	 * Enter {phoneNumber} to the {phoneNumberInput} text field
	 * 
	 * @param phoneNumber: String
	 */
	private void enterPhoneNumber(String phoneNumber) {
		waitElementVisibility(phoneNumberInput);
		phoneNumberInput.sendKeys(phoneNumber);

	}

	/**
	 * Fill Booker Details information
	 *
	 * @param address: String
	 * @param city String
	 * @param zipCode String
	 * @param country: String
	 * @param phoneNumber: String
	 */

	public void fillBookerDetailsInformation(String address, String city, String zipCode, String country, String phoneNumber){
		log.info("The user fills his/her location information.");
		enterAddress(address);
		enterCity(city);
		enterZipCode(zipCode);
		selectCountry(country);
		enterPhoneNumber(phoneNumber);
	}
/**---------------------------------------------------------------------------------------------------/*

 /**
 * Validate if Payment Details Box is present
 *
 * @return true
 */
public boolean paymentDetailsBoxIsPresent() {
	waitElementVisibility(paymentDetailsBox);
	return paymentDetailsBox.isDisplayed();
}


	/**
	 * Get title from Payment Details Box
	 *
	 * @return Title: String
	 */
	public String getPaymentDetailsTitle() {
		return paymentDetailsTitle.getText();
	}

	/**
	 * Enter {cardHolderName} to the {cardHolderNameInput} text field
	 * 
	 * @param cardHolderName: String
	 */
	private void enterCardHolderName(String cardHolderName) {
		waitElementVisibility(cardHolderNameInput);
		cardHolderNameInput.clear();
		cardHolderNameInput.sendKeys(cardHolderName);

	}

	/**
	 * Select {cardType} as value for {cardTypeSelector}
	 * 
	 * @param cardType: String
	 */
	private void selectCardType(String cardType) {
		waitElementVisibility(cardTypeSelector);
		selectElementFromDropDownList(cardTypeSelector, cardType);

	}

	/**
	 * Enter {cardNumber} in the {cardNumberInput} text field
	 * 
	 * @param cardNumber: String
	 */
	private void enterCardNumber(String cardNumber) {
		waitElementVisibility(cardNumberInput);
		cardNumberInput.sendKeys(cardNumber);

	}

	/**
	 * Select {cardExpirationMonth} as value for {cardExpirationMonthSelector}
	 * 
	 * @param cardEspirationMonth: String
	 */
	private void selectCardExpirationMonth(String cardEspirationMonth) {
		waitElementVisibility(cardExpirationMonthSelector);
		selectElementFromDropDownList(cardExpirationMonthSelector, cardEspirationMonth);

	}

	/**
	 * Select {cardExpirationYear} as value for {cardExpirationYearSelector}
	 * 
	 * @param cardExpirationYear: String
	 */
	private void selectCardExpirationYear(String cardExpirationYear) {
		waitElementVisibility(cardExpirationYearSelector);
		selectElementFromDropDownList(cardExpirationYearSelector, cardExpirationYear);
	}

	/**
	 * Enter {cvcCode} to the {cvcInput} text field
	 * 
	 * @param cvcCode: String
	 */
	private void enterCvcCode(String cvcCode) {
		waitElementVisibility(cvcInput);
		cvcInput.sendKeys(cvcCode);
	}

	/**
	 * Fill Payment Details
	 *
	 * @param cardHolderName: String
	 * @param cardType: String
	 * @param cardNumber String
	 * @param expirationMonth: String
	 * @param expirationYear: String
	 * @param cvcCode: String
	 */

	public void fillBookerPaymentInformation(String cardHolderName, String cardType, String cardNumber, String expirationMonth, String expirationYear, String cvcCode)
	
	{
		
		if(cardHolderNameInput.isEnabled()){
			log.info("The user fills the payment information.");
			enterCardHolderName(cardHolderName);
			selectCardType(cardType);
			enterCardNumber(cardNumber);
			selectCardExpirationMonth(expirationMonth);
			selectCardExpirationYear(expirationYear);
			enterCvcCode(cvcCode);

		}
		else {
			log.info("Reservation doesn't need credit card.");
		}

	}

/**---------------------------------------------------------------------------------------------------/*

	// Methods for assertions

	/**
	 * Get text from the Progress Page label
	 * 
	 * @return pageProgressTitle: String
	 */
	public String getProgressPageText() {
		return pageProgressTitle.getText();
	}

	/**
	 * Get text from the label "Your Group"
	 * 
	 * @return guestsGroupText: String
	 */
	public String getGuestsGroupSideBarText() {
		return guestsGroupSideBar.getText();
	}

	/**
	 * Get text from the E-mail label
	 * 
	 * @return String {email}
	 */
	public String getBookerReassuranceEmail() {
		return emailReassurance.getText();
	}

	/**
	 * Validate if Payment Data Area is visible
	 * 
	 * @return true
	 */
	public boolean PaymentAreaIsPresent() {
		return paymentDataDiv.isDisplayed();
	}

	/**
	 * Validate if Check Your Data Button is visible
	 * 
	 * @return true
	 */
	private boolean checkYourDataButtonIsVisible() {
		return waitElementVisibility(checkYourDataButton);
	}

	/**
	 * Validate if Check Your Data Button is clickable
	 * 
	 * @return true
	 */
	private boolean checkYourDataButtonIsClickable() {
		return waitElementToBeClickable(checkYourDataButton);
	}

	/**
	 * Validate if Check Your Data Button is operable
	 * 
	 * @return true
	 */

	public boolean checkYourDataButtonIsOperable() {
		if (checkYourDataButtonIsVisible() && checkYourDataButtonIsClickable()) {
			return true;
		}
		return false;
	}

	/**
	 * Validate if Complete Booking Button is visible
	 * 
	 * @return true
	 */
	private boolean completeBookingButtonIsVisible() {
		return waitElementVisibility(completeBookingButton);
	}

	/**
	 * Validate if Complete Booking Button is clickable
	 * 
	 * @return true
	 */
	private boolean completeBookingButtonIsClickable() {
		return waitElementToBeClickable(completeBookingButton);
	}

	/**
	 * Validate if Complete Booking Button is operable
	 * 
	 * @return true
	 */

	public boolean completeBookingButtonIsOperable() {
		if (completeBookingButtonIsVisible() && completeBookingButtonIsClickable()) {
			return true;
		}
		return false;
	}
}