package com.globant.web.pages.packages;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(className = "book-button-wrapper")
    private WebElement selectRoomButton;

    @FindBy(id = "covid-alert-refundability-0")
    private WebElement nonRefundableModal;

    @FindBy(className = ".modal-body a[data-track-page*=\"NonRefundable.Continue\"]")
    private WebElement nonRefundableContinueButton;

    private final static String NON_REFUNDABLE_CONTINUE_BUTTON=".modal-body a[data-track-page*=\"NonRefundable.Continue\"]";

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
    public RoomDetailsPage(WebDriver driver,String previousHotelName, String previousHotelPrice, String previousStarsNumber) {
        super(driver);
        this.previousHotelName=previousHotelName;
        this.previousHotelPrice=previousHotelPrice;
        this.previousStarsNumber=previousStarsNumber;
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
     * Validate if Hotel Price label is present
     *
     * @return true: boolean
     */
    public boolean hotelPriceLabelIsPresent() {
        return hotelPriceLabel.isDisplayed();
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
     * Validate if Stars label is present
     *
     * @return true: boolean
     */
    public boolean startsLabelIsPresent() {
        return starsNumber.isDisplayed();
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
     *
     */

    public void selectFirstRoom(){
        log.info("The user clicks the \"Select Room\" button");
        waitElementVisibility(roomsItem);
        List<WebElement> roomsItem = this.roomsItem.findElements(By.className("book-button-wrapper"));
        clickElement(roomsItem.stream().findFirst().get());
    }

    /**
     * Handle the NonRefundable pop up
     */
    public void handleNonRefundablePopUp() {
        log.info("Non refundable pop-up shows up");
        if (nonRefundableModal.isDisplayed()) {
            WebElement nonRefundable = nonRefundableModal.findElement(By.cssSelector(NON_REFUNDABLE_CONTINUE_BUTTON));
            waitElementVisibility(nonRefundable);
            clickElement(nonRefundable);
            log.info("The user click the \"Continue with non-refundable rate\" button");
        }
    }


    /**
     * Continue to the next page
     */
    public SelectFlightsPage goToSelectFlightsPage() {
        handleNonRefundablePopUp();
    return new SelectFlightsPage(getDriver());
    }

}
