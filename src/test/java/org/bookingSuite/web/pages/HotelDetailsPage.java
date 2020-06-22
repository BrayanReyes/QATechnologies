package org.bookingSuite.web.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Hotel Details Page displays info about the selected hotel and let choosing the number of rooms and aditional services 
 * 
*/

public class HotelDetailsPage extends BasePage  {

	
	@FindBy(id = "hp_hotel_name")
	private WebElement hotelNameTitle;
	
	@FindBy(css = "#hotel_main_content .bui-price-display__label")
	private WebElement nightsAndGuestsLabel;
	
	@FindBy(css = "#hotel_main_content .totalPrice .bui-price-display__value")
	private WebElement totalPriceLabel;
	
	@FindBy(css = "#hotel_main_content .submitButton a")
	private WebElement bookRoomButton;

	@FindBy(css = "#hprt-table .hprt-nos-select")
	private List<WebElement> roomsSelector;

	@FindBy(className = "hprt-booking-summary-rooms-and-price")
	private WebElement roomsAndPriceSummaryLabel;
	
	@FindBy(css = ".hprt-reservation-cta button[type=submit]")
	private WebElement reservareButton;

	@FindBy(css="[class*=\"last-row\"]:first-of-type [class$=\"select\"]")
	private WebElement roomSelector;

	private String hotelName;
	private String adultsNumber;
	private String childrenNumber;
	
	public HotelDetailsPage(WebDriver driver, String hotelName, String adultsNumber, String childrenNumber) {
		super(driver);
		this.hotelName = hotelName;
		this.adultsNumber = adultsNumber;
		this.childrenNumber = childrenNumber;	
		waitElementVisibility(hotelNameTitle);
		moveToElement(hotelNameTitle);
	}

	public void bookRoom(){
		moveToElement(bookRoomButton);
		waitElementVisibility(bookRoomButton);
		clickElement(bookRoomButton);
	}

	public void setNumberOfRooms(String rooms){
		moveToElement(roomSelector);
		selectElementFromDropDownList(roomSelector,rooms);
	}

	public BookingDataPage clickFinalReserve(){
		waitElementVisibility(reservareButton);
		clickElement(reservareButton);
		return new BookingDataPage(getDriver());
	}
}
