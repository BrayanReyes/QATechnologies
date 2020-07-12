package com.globant.web.pages.packages;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Package Search Page handles the process of searching a Vacation Package,
 * according with the the type of package, origin, destination, dates and number
 * of travelers
 */

public class PackagesSearchPage extends BasePage {

	@FindBy(css = "#gcw-packages-form-hp-package .sub-nav-select div:first-child span")
	private WebElement packageFlightHotelOption;

	@FindBy(id = "package-origin-hp-package")
	private WebElement packageFlyingFromInput;

	@FindBy(id = "package-destination-hp-package")
	private WebElement packageFlyingToInput;

	@FindBy(id = "package-departing-hp-package")
	private WebElement departingPackageDataPicker;

	@FindBy(css = "#package-departing-wrapper-hp-package .datepicker-dropdown")
	private WebElement calendarDiv;

	@FindBy(className = "datepicker-close")
	private WebElement calendarLabel;

	@FindBy(css = "button[class*=\"btn-paging btn-secondary next\"]")
	private WebElement nextMonthButton;

	@FindBy(id = "package-returning-hp-package")
	private WebElement returningPackageDataPicker;

	@FindBy(id = "package-1-adults-hp-package")
	private WebElement adultsPackageNumber;

	@FindBy(id = "partialHotelBooking-hp-package")
	private WebElement partialHotelBookingCheckBox;

	@FindBy(id = "package-checkin-hp-package")
	private WebElement checkInPackageDataPicker;

	@FindBy(id = "package-checkout-hp-package")
	private WebElement checkOutPackageDataPicker;

	@FindBy(id = "search-button-hp-package")
	private WebElement searchPackageButton;

	@FindBy(className = "error-link")
	private WebElement packageAlertMessage;

	/**
	 * Constructor
	 *
	 * @param driver : WebDriver
	 */
	public PackagesSearchPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Get the text for Flight + Hotel Option
	 *
	 * @return Round Trip: String
	 */
	public String getVacationPackageType() {
		return packageFlightHotelOption.getText();
	}

	/**
	 * Click on Flight + Hotel Option
	 */
	public void selectPackageType() {
		waitElementVisibility(packageFlightHotelOption);
		clickElement(packageFlightHotelOption);
		log.info("The user clicks \"Flight + Hotel\" Option");
	}

	/**
	 * Set the City where you are flying from
	 *
	 * @param flyingFrom
	 */
	public void setFlyingFrom(String flyingFrom) {
		waitElementVisibility(packageFlyingFromInput);
		packageFlyingFromInput.sendKeys(flyingFrom);
		log.info("The user types the city where is flying from");
	}

	/**
	 * Set the City where do will return
	 *
	 * @param flyingTo
	 */
	public void setFlyingTo(String flyingTo) {
		waitElementVisibility(packageFlyingToInput);
		packageFlyingToInput.sendKeys(flyingTo);
		log.info("The user types the city where is flying to");

	}

	/**
	 * Open the calendar
	 *
	 */

	private void openCalendar() {
		clickElement(departingPackageDataPicker);
		if (!calendarLabel.isDisplayed()) {
			waitElementVisibility(calendarDiv);
			clickElement(calendarDiv);
		}
		moveToElement(calendarLabel);
	}

	/**
	 * Select a Date according with a number of days given
	 *
	 * @param daysFromNow: int
	 */

	private void selectDate(int daysFromNow) {
		String tmpCSS = calculateDateCSS(daysFromNow);
		WebElement tmpDate = findDateElementByCSS(nextMonthButton, tmpCSS);
		clickElement(tmpDate);
	}

	/**
	 * Set the Departing Date.
	 */
	public void setDepartingDate(int daysForward) {
		openCalendar();
		selectDate(daysForward);
		log.info("The user selects the Departing Date through the Calendar");
	}

	/**
	 * Set the Returning Date.
	 */
	public void setReturningDate(int daysToReturn) {
		setReturningDate(returningPackageDataPicker, daysToReturn);
		log.info("The user selects the Returning Date through the Calendar");
	}

	/**
	 * Set the adults quantity for the flight
	 *
	 * @param numberOfAdults
	 */
	public void setAdultsQuantity(String numberOfAdults) {
		log.info("The user selects the Adults quantity : " + numberOfAdults);
		selectPassengersQuantity(adultsPackageNumber, numberOfAdults);
	}

	/**
	 * Click on "I only need a hotel for part of my stay" filter
	 */
	public void filterPartOfStay() {
		log.info("User clicks \"I only need a hotel for part of my stay\" check button");
		waitElementVisibility(partialHotelBookingCheckBox);
		clickElement(partialHotelBookingCheckBox);
	}

	/**
	 * Set the Check In Date
	 */
	public void setCheckInDate(int daysForward) {
		setDepartingDate(checkInPackageDataPicker, daysForward);
		log.info("The user selects the Check In Date through the Calendar");
	}

	/**
	 * Set the Check Out Date.
	 */
	public void setCheckOutDate(int daysToReturn) {
		setReturningDate(checkOutPackageDataPicker, daysToReturn);
		log.info("The user selects the Check Out Date through the Calendar");
	}

	/**
	 * Click on Search Button
	 */
	public PackageResultPage clickSearchPackageButton() {
		log.info("The user click the \"Search\" button");
		clickElement(searchPackageButton);
		return new PackageResultPage(getDriver());

	}

	/**
	 * Validate if the Error Message is Present
	 *
	 * @return true: Boolean
	 */
	public boolean isDatesErrorMessagePresent() {
		waitElementVisibility(packageAlertMessage);
		return packageAlertMessage.isDisplayed();
	}

	/**
	 * Get Alert Message
	 *
	 * @return Dates Error Message: String
	 */
	public String getDatesErrorMessage() {
		waitElementVisibility(packageAlertMessage);
		return packageAlertMessage.getText();
	}

}
