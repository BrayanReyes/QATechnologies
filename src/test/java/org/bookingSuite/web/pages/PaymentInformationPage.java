package org.bookingSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentInformationPage extends BasePage {

	// WebElements Locators

	@FindBy(css = "[class*=\"booker-details\"] .bp_legacy_form_box__title")
	private WebElement fillBookerDataTitle;

	@FindBy(id = "cc1")
	private WebElement countrySelector;

	@FindBy(id = "phone")
	private WebElement phoneNumberInput;

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

	// WebElements Locators for assertions

	@FindBy(css = "li:last-child strong")
	private WebElement pageProgressTitle;

	@FindBy(css = "div[class$=\"summary\"] div:nth-child(3) div[class$=\"li_content\"] ")
	private WebElement guestsGroupText;

	@FindBy(css = "#label_email .personal_details_reassurance_email_text")
	private WebElement email;

	@FindBy(id = "book_credit_card")
	private WebElement paymentDataDiv;

	@FindBy(css = "button[name*=\"review\"]")
	private WebElement validateDataButton;

	@FindBy(css = "button[class*=\"book\"]")
	private WebElement completeBookButton;

	@FindBy(id = "#bookconditions")
	private WebElement bookConditionsLink;

	/**
	 * Constructor.
	 * 
	 * @param Web driver of the page: driver
	 */

	public PaymentInformationPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Get title from Fill Booker Data area
	 * 
	 * @return (Introduce tus datos): String
	 */
	public String getFillBookerDataTitle() {
		return fillBookerDataTitle.getText();
	}

	/**
	 * Select {country} as value for the {countrySelect}
	 * 
	 * @param country: String
	 */
	public void selectCountry(String country) {
		waitElementVisibility(countrySelector);
		selectElementFromDropDownList(countrySelector, country);
	}

	/**
	 * Enter {phoneNumberInput} to the phone text field
	 * 
	 * @param phoneNumber: String
	 */
	public void enterPhoneNumber(String phoneNumber) {
		waitElementVisibility(phoneNumberInput);
		phoneNumberInput.sendKeys(phoneNumber);

	}

	/**
	 * Get title {paymentDetailsTitle} before to complete payment data
	 * 
	 * @return paymentDetailsTitle: String
	 */
	public String getPaymentDetailsTitle() {
		return paymentDetailsTitle.getText();
	}

	/**
	 * Enter {cardHolderNameInput} to the card Holder text field
	 * 
	 * @param phoneNumberInput: String
	 */
	public void enterCardHolderName(String cardHolderName) {
		waitElementVisibility(cardHolderNameInput);
		cardHolderNameInput.sendKeys(cardHolderName);

	}

	/**
	 * Select {cardType} as value for {cardTypeSelector}
	 * 
	 * @param cardTypeSelector: String
	 */
	public void selectCardType(String cardType) {
		waitElementVisibility(cardTypeSelector);
		selectElementFromDropDownList(cardTypeSelector, cardType);

	}

	/**
	 * Enter {cardNumberInput} to the card number text field
	 * 
	 * @param cardNumberInput: String
	 */
	public void enterCardNumber(String cardNumber) {
		waitElementVisibility(cardNumberInput);
		cardNumberInput.sendKeys(cardNumber);

	}

	/**
	 * Select {cardExpirationMonthSelector} as value for Expiration Month DropDown
	 * 
	 * @param cardEspirationMonth: String
	 */
	public void selectCardExpirationMonth(String cardEspirationMonth) {
		waitElementVisibility(cardExpirationMonthSelector);
		selectElementFromDropDownList(cardExpirationMonthSelector, cardEspirationMonth);

	}

	/**
	 * Select {cardExpirationYearSelector} as value for Expiration Year DropDown
	 * 
	 * @param cardEspirationYear: String
	 */
	public void selectCardExpirationYear(String cardEspirationYear) {
		waitElementVisibility(cardExpirationYearSelector);
		selectElementFromDropDownList(cardExpirationYearSelector, cardEspirationYear);
	}

	/**
	 * Enter {cvcInput} to the card number CVC code text field
	 * 
	 * @param cvcInput: String
	 */
	public void enterCvcCode(String cvcCode) {
		waitElementVisibility(cvcInput);
		cvcInput.sendKeys(cvcCode);
	}

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
	public String getGuestsGroupText() {
		return guestsGroupText.getText();
	}

	/**
	 * Get text from the E-mail label
	 * 
	 * @return String {email}
	 */
	public String getBookerEmail() {
		return email.getText();
	}

	/**
	 * Validate if Payment Data Area is visible
	 * 
	 * @return true
	 */
	public boolean PaymentAreaIsPresent() {
		return waitElementVisibility(paymentDataDiv);
	}

	/**
	 * Check if Validate Data Button is visible
	 * 
	 * @return true
	 */
	public boolean validateDataButtonIsVisible() {
		return waitElementVisibility(validateDataButton);
	}

	/**
	 * Check if Validate Data Button is clickable
	 * 
	 * @return true
	 */
	public boolean validateDataButtonIsClickable() {
		return waitElementToBeClickable(validateDataButton);
	}

	/**
	 * Check if Validate Data Button is operable
	 * 
	 * @return true
	 */

	public boolean validateDataButtonIsOperable() {
		if (validateDataButtonIsVisible() && validateDataButtonIsClickable()) {
			return true;
		}
		return false;
	}

	/**
	 * Validate if Complete Book Button is visible
	 * 
	 * @return true
	 */
	public boolean completeBookButtonIsVisible() {
		return waitElementVisibility(completeBookButton);
	}

	/**
	 * Validate if Complete Book Button is clickable
	 * 
	 * @return true
	 */
	public boolean completeBookButtonIsClickable() {
		return waitElementToBeClickable(completeBookButton);
	}

	/**
	 * Validate if Complete Book Button is operable
	 * 
	 * @return true
	 */

	public boolean completeBookButtonIsOperable() {
		if (completeBookButtonIsVisible() && completeBookButtonIsClickable()) {
			return true;
		}
		return false;
	}

	/**
	 * Validate if Book Conditions Link is visible
	 * 
	 * @return true
	 */
	public boolean bookConditionsLinkIsVisible() {
		return waitElementVisibility(bookConditionsLink);

	}

	/**
	 * Validate if Book Conditions Link is clickable
	 * 
	 * @return true
	 */
	public boolean bookConditionsLinkIsClickable() {
		return waitElementToBeClickable(bookConditionsLink);
	}

	/**
	 * Validate if Book Conditions Link is operable
	 * 
	 * @return true
	 */

	public boolean bookConditionsLinkIsOperable() {
		if (bookConditionsLinkIsVisible() && bookConditionsLinkIsClickable()) {
			return true;
		}
		return false;
	}

}
