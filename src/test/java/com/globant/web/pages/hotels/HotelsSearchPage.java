package com.globant.web.pages.hotels;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Hotels Search Page allows to search a Hotel according with parameters as the Destination and Dates
 * @author july.moreno
 *
 */

public class HotelsSearchPage extends BasePage {

	@FindBy(id = "hotel-destination-hp-hotel")
	private WebElement hotelsDestinationInput;

	@FindBy(id = "hotel-checkin-hp-hotel")
	private WebElement checkInDataPicker;

	@FindBy(id = "hotel-checkout-hp-hotel")
	private WebElement checkOutDataPicker;

	@FindBy(css = "#hotel-checkin-wrapper-hp-hotel .datepicker-dropdown")
	private WebElement checkInCalendarDiv;

	@FindBy(css = "#hotel-checkout-wrapper-hp-hotel .datepicker-dropdown")
	private WebElement checkOutCalendarDiv;

	@FindBy(className = "datepicker-close")
	private WebElement closeDataPickerDiv;

	@FindBy(css = "button[class*=\"btn-paging btn-secondary next\"]")
	private WebElement nextMonthButton;

	@FindBy(id = "hotel-rooms-hp-hotel")
	private WebElement hotelsRoomsNumber;

	@FindBy(id = "hotel-1-adults-hp-hotel")
	private WebElement hotelsAdultsNumber;

	@FindBy(id = "hotel-1-children-hp-hotel")
	private WebElement hotelsChildrenNumber;

	@FindBy(css = "#gcw-hotel-form-hp-hotel button[type=submit]")
	private WebElement searchHotelsButton;

	@FindBy(id = "mer-signup-wrapper")
	private WebElement memberDiscountBanner;

	@FindBy(id = "mer-signup-toggle-btn")
	private WebElement memberDiscountText;

	@FindBy(id = "mer-disclaimer")
	private WebElement memberDiscountSecondaryText;

	@FindBy(id = "mer-email")
	private WebElement memberDiscountEmail;

	@FindBy(id = "mer-signup-button")
	private WebElement signMeUpButton;

	@FindBy(id = "mer-signup-toggle-btn")
	private WebElement signedDiscountConfirmationMessage;

	@FindBy(css = "#mer-signup-success p:first-of-type")
	private WebElement signedDiscountFirstText;

	@FindBy(css = "#mer-signup-success p:last-of-type")
	private WebElement signedDiscountSecondaryText;

	/**
	 * Constructor
	 *
	 * @param driver : WebDriver
	 */
	public HotelsSearchPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Set the City where the Hotel is located
	 *
	 * @param city: String
	 */
	public void setGoingTo(String city) {
		waitElementVisibility(hotelsDestinationInput);
		hotelsDestinationInput.sendKeys(city);
		log.info("The user types the city where the hotel is located");

	}

	/**
	 * Set the Check In Date.
	 *
	 * @param daysForward: int
	 */
	public void setCheckInDate(int daysForward) {
		clickElement(checkInDataPicker);
		openCalendar(checkInDataPicker,closeDataPickerDiv, checkInCalendarDiv);
		selectDateInCalendar(daysForward,nextMonthButton);
		log.info("The user selects the Check In Date through the Calendar");
	}

	/**
	 * Set the Check Out Date.
	 *
	 * @param daysToReturn: int
	 */
	public void setCheckOutDate(int daysToReturn) {
		clickElement(checkInDataPicker);
		openCalendar(checkOutDataPicker,closeDataPickerDiv, checkOutCalendarDiv);
		selectDateInCalendar(daysToReturn,nextMonthButton);
		log.info("The user selects the Check Out Date through the Calendar");
	}

	/**
	 * Click on Search Button
	 */
	public HotelsResultPage clickSearchHotelsButton() {
		log.info("The user click the \"Search\" button");
		waitElementVisibility(searchHotelsButton);
		clickElement(searchHotelsButton);
		switchToLastOpenTab(getDriver());
		return new HotelsResultPage(getDriver());
	}

	/**
	 * Validate if Member Discount Banner is Present
	 *
	 * @return true: Boolean
	 */
	public boolean isMemberDiscountBannerPresent() {
		moveToElement(memberDiscountBanner);
		waitElementVisibility(memberDiscountBanner);
		return memberDiscountBanner.isDisplayed();
	}

	/**
	 * Validate if Member Discount Text is Present
	 *
	 * @return true: Boolean
	 */
	public boolean isMemberDiscountTextPresent() {
		waitElementVisibility(memberDiscountText);
		return memberDiscountText.isDisplayed();
	}

	/**
	 * Get the Sign Up Link Text
	 *
	 * @return Sign Up Link Text: String
	 */
	public String getMemberDiscountText() {
		waitElementVisibility(memberDiscountText);
		return memberDiscountText.getText();
	}

	/**
	 * Click on Member Discount link
	 */

	public void clickMemberDiscountLink() {
		log.info("The user clicks \"Get member discount link\"");
		waitElementVisibility(memberDiscountText);
		clickElement(memberDiscountText);
	}

	/**
	 * Validate if Member Discount Secondary Text is Present
	 *
	 * @return true: Boolean
	 */
	public boolean isMemberDiscountSecondaryTextPresent() {
		moveToElement(memberDiscountSecondaryText);
		waitElementVisibility(memberDiscountSecondaryText);
		return memberDiscountSecondaryText.isDisplayed();
	}

	/**
	 * Get Member Discount Secondary Text
	 *
	 * @return Member Discount Secondary Text: String
	 */
	public String getMemberDiscountSecondaryText() {
		waitElementVisibility(memberDiscountSecondaryText);
		moveToElement(memberDiscountSecondaryText);
		return memberDiscountSecondaryText.getText();
	}

	/**
	 * Set the Email for members discount
	 *
	 * @param email: String
	 */
	public void setMemberDiscountEmail(String email) {
		log.info("The user types the email to subscribe for discounts");
		waitElementVisibility(memberDiscountEmail);
		memberDiscountEmail.sendKeys(email);
	}

	/**
	 * Click on Sign Me Up Button
	 */
	public void clickSignMeUpButtonButton() {
		log.info("The user click the \"Sign Me Up\" button");
		clickElement(signMeUpButton);
	}

	/**
	 * Validate if Signed Discount Confirmation Message is Present
	 *
	 * @return true: Boolean
	 */
	public boolean isSignedDiscountConfirmationMessagePresent() {
		waitElementVisibility(signedDiscountConfirmationMessage);
		return signedDiscountConfirmationMessage.isDisplayed();
	}

	/**
	 * Get Signed Discount Confirmation Message
	 *
	 * @return Member Discount Secondary Text: String
	 */
	public String getSignedDiscountConfirmationMessage() {
		waitElementVisibility(signedDiscountConfirmationMessage);
		return signedDiscountConfirmationMessage.getText();
	}

	/**
	 * Validate if Signed Discount First Text is Present
	 *
	 * @return true: Boolean
	 */
	public boolean isSignedDiscountFirstTextPresent() {
		waitElementVisibility(signedDiscountFirstText);
		return signedDiscountFirstText.isDisplayed();
	}

	/**
	 * Get Signed Discount First Text
	 *
	 * @return Member Discount Secondary Text: String
	 */
	public String getSignedDiscountFirstText() {
		waitElementVisibility(signedDiscountFirstText);
		return signedDiscountFirstText.getText();
	}

	/**
	 * Validate if Signed Discount Second Text is Present
	 *
	 * @return true: Boolean
	 */
	public boolean isSignedDiscountSecondTextPresent() {
		waitElementVisibility(signedDiscountSecondaryText);
		return signedDiscountSecondaryText.isDisplayed();
	}

	/**
	 * Get Signed Discount First Text
	 *
	 * @return Member Discount Second Text: String
	 */
	public String getSignedDiscountSecondText() {
		waitElementVisibility(signedDiscountSecondaryText);
		return signedDiscountSecondaryText.getText();
	}

}
