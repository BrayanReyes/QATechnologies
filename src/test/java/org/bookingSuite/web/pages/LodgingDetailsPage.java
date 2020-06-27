package org.bookingSuite.web.pages;

import org.bookingSuite.web.utils.AssertTextValidation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Lodging Details Page displays information about the selected hotel and let
 * choosing the number of rooms and additional services.
 * 
 */

public class LodgingDetailsPage extends BasePage {

	@FindBy(id = "hp_hotel_name")
	private WebElement hotelNameLabel;

	@FindBy(css = "#group_recommendation .hp-group_recommendation__title")
	private WebElement nightsAndGuestsValidationLabel;

	@FindBy(css = "#hotel_main_content .bui-price-display__label")
	private WebElement nightsAndGuestsLabel;

	@FindBy(css = "#hotel_main_content .totalPrice .bui-price-display__value")
	private WebElement totalPriceLabel;

	@FindBy(id = "av-summary-checkin")
	private WebElement checkInSummaryLabel;

	@FindBy(id = "av-summary-checkout")
	private WebElement checkOutSummaryLabel;

	@FindBy(id = "av-summary-occupancy")
	private WebElement occupancySummaryLabel;

	@FindBy(css = "#hotel_main_content .submitButton a")
	private WebElement bookThisRoomButton;

	@FindBy(css = "#hprt-table > tbody > tr:nth-child(1) select.hprt-nos-select")
	private WebElement roomsSelector;

	@FindBy(className = "hprt-booking-summary-rooms-and-price")
	private WebElement roomsAndPriceSummaryLabel;

	@FindBy(css = ".hprt-reservation-total-price.bui-price-display__value")
	private WebElement priceConfirmationLabel;

	@FindBy(css = ".hprt-reservation-cta button[type=submit]")
	private WebElement iWillBookButton;

	private final String hotelName;
	private final String numberOfGuests;

	/**
	 * Constructor.
	 *
	 * @param driver:         WebDriver
	 * @param hotelName:      String
	 * @param numberOfGuests: String
	 */
	LodgingDetailsPage(WebDriver driver, String hotelName, String numberOfGuests) {
		super(driver);
		this.hotelName = hotelName;
		this.numberOfGuests = numberOfGuests;
		waitElementVisibility(hotelNameLabel);
		moveToElement(hotelNameLabel);
	}

	/**
	 * Validate if Hotel Name label is present
	 *
	 * @return true: boolean
	 */
	public boolean hotelNameLabelIsPresent() {
		return hotelNameLabel.isDisplayed();
	}

	/**
	 * Get Hotel Name label text
	 *
	 * @return Label: String
	 */
	public String getHotelNameLabel() {
		return hotelNameLabel.getText().substring(6);
	}

	/**
	 * Validate if the Hotel Name is the same than the previous page
	 *
	 * @return true: boolean
	 */

	public boolean hotelNameIsTheSame() {
        return this.hotelName.equals(getHotelNameLabel());
    }

	/**
	 * Get Nights And Guests Label text
	 *
	 * @return Label: String
	 */
	public String getGuestsTextLabel() {
		return nightsAndGuestsValidationLabel.getText().substring(17);
	}

	/**
	 * Get Nights And Guests Label text
	 *
	 * @return Label: String
	 */
	public String getNightsAndGuestsLabel() {
		return nightsAndGuestsLabel.getText().split(",")[0];
	}

	/**
	 * Validate if the number of guests is the same than the previous page
	 *
	 * @return true: boolean
	 */

	public boolean guestNumberIsTheSame() {
        return this.numberOfGuests.equals(getGuestsTextLabel());
    }

	/**
	 * Validate if Nights And Guests Label is present
	 *
	 * @return true: boolean
	 */
	public boolean nightsAndGuestsLabelIsPresent() {
		return nightsAndGuestsLabel.isDisplayed();
	}

	/**
	 * Validate if Total Price Label is present
	 *
	 * @return true: boolean
	 */
	public boolean totalPriceLabelIsPresent() {
		return totalPriceLabel.isDisplayed();
	}

	/**
	 * Get Total Price Label text
	 *
	 * @return Label: String
	 */
	public String getTotalPriceLabel() {
		return totalPriceLabel.getText();
	}

	/**
	 * Validate if Check In Summary is present
	 *
	 * @return true: boolean
	 */
	public boolean checkInSummaryLabelIsPresent() {
		return checkInSummaryLabel.isDisplayed();
	}

	/**
	 * Get Check In Summary text
	 *
	 * @return Label: String
	 */
	public String getCheckInSummaryLabel() {
		return checkInSummaryLabel.getText();
	}

	/**
	 * Validate if Check Out Summary is present
	 *
	 * @return true: boolean
	 */
	public boolean checkOutSummaryLabelIsPresent() {
		return checkOutSummaryLabel.isDisplayed();
	}

	/**
	 * Get Check Out Summary text
	 *
	 * @return Label: String
	 */
	public String getCheckOutSummaryLabel() {
		return checkOutSummaryLabel.getText();
	}

	/**
	 * Validate if Occupancy Summary is present
	 *
	 * @return true: boolean
	 */
	public boolean occupancySummaryLabelIsPresent() {
		return occupancySummaryLabel.isDisplayed();
	}

	/**
	 * Get Occupancy Summary text
	 *
	 * @return Label: String
	 */
	public String getOccupancySummaryLabel() {
		return occupancySummaryLabel.getText();
	}

	/**
	 * Click to {bookThisRoomButton} to continue the booking process
	 *
	 */
	public void clickBookThisRoom() {
		moveToElement(bookThisRoomButton);
		waitElementVisibility(bookThisRoomButton);
		clickElement(bookThisRoomButton);
		log.info("The user clicks \"Book This Room\" button.");
	}

	/**
	 * Click to {roomsSelector} to select the number of rooms
	 * 
	 * @param rooms: String
	 *
	 */
	public void setNumberOfRooms(String rooms) {
		moveToElement(roomsSelector);
		selectElementFromDropDownList(roomsSelector, rooms);
		log.info("Selected rooms number " + rooms);
	}

	/**
	 * Validate if Rooms and Price Summary Label is present
	 *
	 * @return true: boolean
	 */
	private boolean roomsAndPriceSummaryLabelIsPresent() {
		return roomsAndPriceSummaryLabel.isDisplayed();
	}

	/**
	 * Validate if Price Confirmation Label is present
	 *
	 * @return true: boolean
	 */
	private boolean priceConfirmationLabelIsPresent() {
		return priceConfirmationLabel.isDisplayed();
	}

	/**
	 * Validate if the number of rooms is already selected
	 *
	 * @return true: boolean
	 */

	private boolean roomsNumberIsSelected() {
        return roomsAndPriceSummaryLabelIsPresent() && priceConfirmationLabelIsPresent();
    }

	/**
	 * Click to {iWillBookButton} to continue the booking process
	 * 
	 * @param roomsNumber: String
	 * @return BookerInformationPage
	 */

	public BookerInformationPage clickFinalReserve(String roomsNumber) {

		waitAfterElementDisappear(iWillBookButton, "data-title", AssertTextValidation.getSelectRoomsAdvice());
		waitToRefresh(iWillBookButton);
		log.info("The user clicks \"I will Book\" Button.");

		if (roomsNumberIsSelected()) {
			waitElementVisibility(iWillBookButton);
			clickElement(iWillBookButton);
			return new BookerInformationPage(getDriver());

		} else {
			setNumberOfRooms(roomsNumber);
			roomsNumberIsSelected();
			waitElementVisibility(iWillBookButton);
			clickElement(iWillBookButton);
			return new BookerInformationPage(getDriver());

		}

	}
}
