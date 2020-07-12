package com.globant.web.pages.packages;

import com.globant.web.pages.BasePage;
import com.globant.web.pages.flights.FlightsResultPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Room Details Page shows the information regarding with the hotel chosen and
 * allows to select a room
 */

public class RoomDetailsPage extends BasePage {

	@FindBy(id = "hotel-name")
	private WebElement hotelNameLabel;

	@FindBy(css = ".lead-price-wrapper a")
	private WebElement hotelPriceLabel;

	@FindBy(css = "#license-plate .visuallyhidden")
	private WebElement starsNumber;

	@FindBy(id = "rooms-and-rates")
	private WebElement roomsDiv;

	@FindBy(className = "room-info")
	private WebElement roomsItem;

	@FindBy(id = "availability-header")
	private WebElement chooseRoomHeader;

	@FindBy(id = "covid-alert-refundability-0")
	private WebElement nonRefundableModal;

	private final static String NON_REFUNDABLE_CONTINUE_BUTTON = ".modal-body a[data-track-page*=\"NonRefundable.Continue\"]";
	private final static String SELECT_ROOM_BUTTON = "book-button-wrapper";

	String previousHotelName;
	String previousHotelPrice;
	String previousStarsNumber;

	/**
	 * Constructor
	 *
	 * @param driver
	 * @param previousHotelName
	 * @param previousHotelPrice
	 * @param previousStarsNumber
	 */
	public RoomDetailsPage(WebDriver driver, String previousHotelName, String previousHotelPrice,
			String previousStarsNumber) {
		super(driver);
		this.previousHotelName = previousHotelName;
		this.previousHotelPrice = previousHotelPrice;
		this.previousStarsNumber = previousStarsNumber;
	}

	/**
	 * Get Hotel Name label text
	 *
	 * @return Label: String
	 */
	public String getHotelNameLabel() {
		return hotelNameLabel.getText();
	}

	/**
	 * Validate if the Hotel Name is the same than the previous page
	 *
	 * @return true: boolean
	 */

	public boolean hotelNameIsTheSame() {
		return this.previousHotelName.equals(getHotelNameLabel());
	}

	/**
	 * Get Hotel Price label text
	 *
	 * @return Label: String
	 */
	public String getHotelPriceLabel() {
		return hotelPriceLabel.getText();
	}

	/**
	 * Validate if the Hotel Price is the same than the previous page
	 *
	 * @return true: boolean
	 */

	public boolean hotelPriceIsTheSame() {
		return this.previousHotelPrice.equals(getHotelPriceLabel());
	}

	/**
	 * Get Starts Label text
	 *
	 * @return Label: String
	 */
	public String getStarsLabel() {
		return starsNumber.getText();
	}

	/**
	 * Validate if the Stars Text is the same than the previous page
	 *
	 * @return true: boolean
	 */

	public boolean starsTextIsTheSame() {
		return this.previousStarsNumber.equals(getStarsLabel());
	}

	/**
	 * Validate if Rooms Div is present
	 *
	 * @return true: boolean
	 */
	public boolean roomsDivIsPresent() {
		return roomsDiv.isDisplayed();
	}

	/**
	 * Validate if Choose Room Header is Present
	 *
	 * @return true: boolean
	 */
	public boolean chooseRoomHeaderIsPresent() {
		moveToElement(chooseRoomHeader);
		return chooseRoomHeader.isDisplayed();
	}

	/**
	 * Get Choose Room Header
	 *
	 * @return Label: String
	 */
	public String getChooseRoomHeader() {
		moveToElement(chooseRoomHeader);
		waitElementVisibility(chooseRoomHeader);
		return chooseRoomHeader.getText();
	}

	/**
	 * Select one Room clicking the Select Button
	 */

	public void selectFirstRoom() {
		log.info("The user clicks the \"Select Room\" button");
		waitElementVisibility(roomsItem);
		List<WebElement> roomsItem = this.roomsItem.findElements(By.className(SELECT_ROOM_BUTTON));
		clickElement(roomsItem.stream().findFirst().get());
	}

	/**
	 * Handle the NonRefundable pop up
	 */
	public void handleNonRefundablePopUp() {
		log.info("Non refundable pop-up shows up");
		if (nonRefundableModal.isEnabled()) {
			WebElement nonRefundableContinueButton = nonRefundableModal
					.findElement(By.cssSelector(NON_REFUNDABLE_CONTINUE_BUTTON));
			clickElement(nonRefundableContinueButton);
			log.info("The user click the \"Continue with non-refundable rate\" button");
		}
	}

	/**
	 * Continue to Flight Result Page
	 */
	public FlightsResultPage goToSelectFlightsPage() {
		handleNonRefundablePopUp();
		return new FlightsResultPage(getDriver());
	}

}
