package org.bookingSuite.web.pages;

import java.time.LocalDate;

import org.bookingSuite.web.utils.SearchParameters;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

/**
 * Stay Search Page handles the stay information and allows to search stay
 * options according with the parameter: destination, dates of check-in and
 * checkout, number of guests and number of rooms.
 * 
 * @author: july.moreno
 * @version: 24/06/2020
 */

public class StaySearchPage extends BasePage {

	@FindBy(className = "sb-searchbox__title-text")
	private WebElement stayPageHeader;

	@FindBy(id = "ss")
	private WebElement destinationInputText;

	@FindBy(css = "[data-i=\"0\"]")
	private WebElement destinationFirstOption;

	@FindBy(className = "sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb")
	private WebElement datesDiv;

	@FindBy(className = "bui-calendar__control--next")
	private WebElement calendarControlNext;

	@FindBy(className = "bui-calendar__display")
	private WebElement calendarLabel;

	@FindBy(id = "xp__guests__toggle")
	private WebElement guestDiv;

	@FindBy(css = ".sb-group__field-adults .bui-stepper__display")
	private WebElement adultsQuantityLabel;

	@FindBy(css = ".sb-group__field-adults .bui-stepper__add-button")
	private WebElement addAdultsButton;

	@FindBy(css = ".sb-group__field-adults .bui-stepper__subtract-button")
	private WebElement decreaseAdultsButton;

	@FindBy(css = ".sb-group-children .bui-stepper__display")
	private WebElement childrenQuantityLabel;

	@FindBy(css = ".sb-group-children .bui-stepper__add-button")
	private WebElement AddChildrenButton;

	@FindBy(css = ".sb-group__field-rooms .bui-stepper__add-button")
	private WebElement AddRoomsButton;

	@FindBy(css = ".sb-group__field-rooms .bui-stepper__display")
	private WebElement roomsQuantityLabel;

	@FindBy(css = "#frm button[type=submit]")
	private WebElement searchButton;

	/**
	 * Constructor.
	 * 
	 * @param driver: WebDriver
	 */

	StaySearchPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Get the text Stay Page Header
	 *
	 * @return Stay Page Header Text : String
	 */
	public String getStayPageHeaderText() {
		return stayPageHeader.getText();
	}

	/**
	 * Enter destination
	 * 
	 * @param destination: String
	 */
	public void enterDestination(String destination) {
		log.info("The user enters the destination.");
		waitElementVisibility(destinationInputText);
		destinationInputText.sendKeys(destination);
		waitElementVisibility(destinationFirstOption);
		clickElement(destinationFirstOption);
	}

	/**
	 * Open the calendar
	 * 
	 */

	private void openCalendar() {
		// If in case the calendar does not open automatically
		if (!calendarLabel.isDisplayed()) {
			waitElementVisibility(datesDiv);
			clickElement(datesDiv);
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
		WebElement tmpDate = findDateElementByCSS(tmpCSS);
		clickElement(tmpDate);
	}

	/**
	 * Set the dates for Check In and Ceck Out before to search
	 * 
	 * @param daysForward:  int
	 * @param stayDuration: int
	 * 
	 */

	public void setDatesToSearch(int daysForward, int stayDuration) {
		openCalendar();
		// Start Date
		selectDate(daysForward);
		// End Date
		selectDate(stayDuration);
		log.info("The user sets the dates for Check In and Check Out.");
	}

	/**
	 * Find the element for the date component in the calendar
	 * 
	 * @param cssSelector: String
	 */
	private WebElement findDateElementByCSS(String cssSelector) {
		try {
			return getDriver().findElement(By.cssSelector(cssSelector));
		} catch (Exception e) {
			// log.info(e);
			waitElementToBeClickable(calendarControlNext);
			clickElement(calendarControlNext);
			return findDateElementByCSS(cssSelector);
		}
	}

	/**
	 * Set up the [data-date] value for the check-in, chek-out dates
	 * [data-date="2020-06-26"]
	 * 
	 * @param days: int
	 * 
	 */

	private String calculateDateCSS(int days) {
		LocalDate increasedDate = LocalDate.now().plusDays(days);
		return "[data-date='" + increasedDate + "']";
	}

	/**
	 * Get the text from Calendar Label
	 *
	 * @return Calendar Label: String
	 */
	public String getCalendarLabelText() {
		return calendarLabel.getText();
	}

	/**
	 * Set the quantity of adults
	 * 
	 * @param adultsQuantity: int
	 */

	public void setAdultsQuantity(int adultsQuantity) {

		if (adultsQuantity < 0 || adultsQuantity > 30) {
			log.info("Error in Adults quantity");
		} else {
			switch (adultsQuantity) {
			case 1:
				waitElementVisibility(decreaseAdultsButton);
				clickElement(decreaseAdultsButton);
				break;
			case 2:
				log.info("Defaults Adults Quantity");
				break;
			default:
				for (int i = 2; i < adultsQuantity; i++) {
					waitElementVisibility(addAdultsButton);
					clickElement(addAdultsButton);
				}
				break;
			}
		}
	}

	/**
	 * Get the text from Adults Quantity Label
	 *
	 * @return Adults Number Label: String
	 */
	public String getAdultsNumberLabelText() {
		return adultsQuantityLabel.getText();
	}

	/**
	 * Set the quantity of children
	 * 
	 * @param childrenQuantity: int
	 */
	public void setChildrenQuantity(int childrenQuantity) {

		if (childrenQuantity < 0 || childrenQuantity > 10) {
			log.info("Error in Adults quantity");
		} else {
			switch (childrenQuantity) {
			case 0:
				log.info("Defaults Children Quantity");
				break;
			default:
				for (int i = 0; i < childrenQuantity; i++) {
					waitElementVisibility(AddChildrenButton);
					clickElement(AddChildrenButton);
				}
				break;
			}
		}
	}

	/**
	 * Get the text from Children Quantity Label
	 *
	 * @return Children Number Label: String
	 */
	public String getChildrenNumberLabelText() {
		return childrenQuantityLabel.getText();
	}

	/**
	 * Set the quantity of rooms
	 * 
	 * @param roomsQuantity: int
	 */
	public void setRoomQuantity(int roomsQuantity) {

		if (roomsQuantity < 0 || roomsQuantity > 30) {
			log.info("Error in rooms quantity");
		} else {
			switch (roomsQuantity) {
			case 1:
				log.info("Defaults Rooms Quantity");
				break;
			default:
				for (int i = 1; i < roomsQuantity; i++) {
					waitElementVisibility(AddRoomsButton);
					clickElement(AddRoomsButton);
				}
				break;
			}
		}
	}

	/**
	 * Get the text from Rooms Quantity Label
	 *
	 * @return Rooms Number Label: String
	 */
	public String getRoomsQuantityLabelText() {
		return roomsQuantityLabel.getText();
	}

	/**
	 * Set the age of all children
	 * 
	 * @param childrenAge:String[]
	 */
	public void setChildrenAge(String... childrenAge) {
		for (int i = 0; i < childrenAge.length; i++) {
			// ID of CSS Selector generate by Children
			String tmpCSS = "[data-group-child-age=\"" + i + "\"]";
			WebElement childAgeWE = getDriver().findElement(By.cssSelector(tmpCSS));
			selectElementFromDropDownList(childAgeWE, childrenAge[i]);
		}
	}

	/**
	 * Set the guest information: Number of Adults, Children, Rooms and the age for
	 * each children
	 * 
	 * @param adultsNumber:   int
	 * @param childrenNumber: int
	 * @param childrenAges:   String
	 * 
	 */

	public void setGuestInformation(int adultsNumber, int childrenNumber, String... childrenAges) {
		log.info("The user sets the guest information.");
		waitElementVisibility(guestDiv);
		clickElement(guestDiv);
		setAdultsQuantity(adultsNumber);
		setChildrenQuantity(childrenNumber);
		setChildrenAge(childrenAges);
	}

	/**
	 * Click to {searchButton} to continue the booking process
	 * 
	 * @return LodgingDetailsPage
	 */
	public SearchResultsPage clickSearchButton() {
		log.info("The user clicks the \"Search\" button.");
		waitElementVisibility(searchButton);
		clickElement(searchButton);
		return new SearchResultsPage(getDriver());
	}
}
