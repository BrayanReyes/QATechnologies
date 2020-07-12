package com.globant.web.pages.flights;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Payment Page shows the details about the flight selected and allows to enter
 * the payment data.
 * 
 * @author july.moreno
 *
 */
public class PaymentFlightPage extends BasePage {

	@FindBy(css = "#page-header .flights.generic-header")
	private WebElement paymentFlightHeader;

	@FindBy(id = "preferences")
	private WebElement preferencesSection;

	@FindBy(css = "#preferences .faceoff-module-title")
	private WebElement preferencesSectionTitle;

	@FindBy(id = "payments")
	private WebElement paymentSection;

	@FindBy(css = "#payments h2")
	private WebElement paymentSectionTitle;

	@FindBy(id = "summary-container")
	private WebElement summaryFlightSection;

	@FindBy(id = "best-price-guarantee")
	private WebElement priceGuaranteeAlert;

	@FindBy(id = "complete-booking")
	private WebElement completeBookingButton;

	@FindBy(id = "firstname[0]")
	private WebElement firstNameInput;

	@FindBy(id = "lastname[0]")
	private WebElement lastNameInput;

	@FindBy(id = "uitk-live-announce")
	private WebElement pageUpdatedMarker;

	/**
	 * Constructor
	 *
	 * @param driver : WebDriver
	 */
	public PaymentFlightPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Get Payment Flight Header
	 *
	 * @return Label: String
	 */
	public String getPaymentFlightHeader() {
		waitAttributeToBe(pageUpdatedMarker, "aria-live", "assertive");
		waitElementVisibility(paymentFlightHeader);
		return paymentFlightHeader.getText();
	}

	/**
	 * Validate if Preferences Section is present
	 *
	 * @return true: boolean
	 */
	public boolean isPreferencesSectionPresent() {
		waitElementVisibility(preferencesSection);
		return preferencesSection.isDisplayed();
	}

	/**
	 * Get Preferences Section Title
	 *
	 * @return Label: String
	 */
	public String getPreferencesSectionTitle() {
		waitElementVisibility(preferencesSectionTitle);
		return preferencesSectionTitle.getText();
	}

	/**
	 * Set the First Name for flight section
	 *
	 * @param firstName: String
	 */
	public void setFirstName(String firstName) {
		waitElementVisibility(firstNameInput);
		firstNameInput.sendKeys(firstName);
		log.info("The user types the first name");

	}

	/**
	 * Set the Last Name for flight section
	 *
	 * @param lastName: String
	 */
	public void setLastName(String lastName) {
		waitElementVisibility(lastNameInput);
		lastNameInput.sendKeys(lastName);
		log.info("The user types the last name");

	}

	/**
	 * Validate if Payment Section is present
	 *
	 * @return true: boolean
	 */
	public boolean isPaymentSectionPresent() {
		waitElementVisibility(paymentSection);
		return paymentSection.isDisplayed();
	}

	/**
	 * Get Payment Section Title
	 *
	 * @return Label: String
	 */
	public String getPaymentSectionTitle() {
		waitElementVisibility(paymentSectionTitle);
		return paymentSectionTitle.getText();
	}

	/**
	 * Validate if Summary Flight Section is present
	 *
	 * @return true: boolean
	 */
	public boolean isSummaryFlightSectionPresent() {
		waitElementVisibility(summaryFlightSection);
		return summaryFlightSection.isDisplayed();
	}

	/**
	 * Validate if Price Guarantee Alert is present
	 *
	 * @return true: boolean
	 */
	public boolean isPriceGuaranteeAlertPresent() {
		waitElementVisibility(priceGuaranteeAlert);
		return priceGuaranteeAlert.isDisplayed();
	}

	/**
	 * Validate if Complete Booking Button is present
	 *
	 * @return true: boolean
	 */
	public boolean isCompleteBookingButtonPresent() {
		waitElementVisibility(completeBookingButton);
		return completeBookingButton.isDisplayed();
	}

}