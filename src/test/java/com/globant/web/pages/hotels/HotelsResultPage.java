package com.globant.web.pages.hotels;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelsResultPage extends BasePage {


    @FindBy (css=".overlay-content.overlay-modal-inherit")
    private WebElement discountModal;

    @FindBy (css=".uitk-toolbar-overlay button[type=button]")
    private WebElement closeDiscountModalButton;

    @FindBy (css=".search-results-listing .uitk-typeahead .uitk-field:last-child")
    private WebElement searchByPropertyNameButton;

    @FindBy (css="input[name=foo]")
    private WebElement propertyNameInput;

    @FindBy (css=".uitk-dialog-content-wrapper li:first-of-type")
    private WebElement hotelFirstOption;

    @FindBy (css=".all-b-padding-three button[type=button]")
    private WebElement hotelNameFilterMark;

    @FindBy (css=".uitk-card-content [data-stid=content-hotel-title]")
    private WebElement hotelName;

    @FindBy (css="span[data-stid=content-hotel-lead-price]")
    private WebElement hotelPrice;

    /**
     * Constructor
     *
     * @param driver : WebDriver
     */
    public HotelsResultPage(WebDriver driver) {
        super(driver);
        handleDiscountModal();
    }

    /**
     *
     */
    public void handleDiscountModal(){
            waitElementVisibility(discountModal);
            waitElementToBeClickable(closeDiscountModalButton);
            clickElement(closeDiscountModalButton);
    }

    /**
     * Validate if Property Name Filter is Present
     *
     * @return true: Boolean
     */
    public boolean isPropertyNameFilterPresent(){
        waitElementVisibility(searchByPropertyNameButton);
        return searchByPropertyNameButton.isDisplayed();
    }

    /**
     * Click on Property Name Filter Button
     */
    public void clickPropertyNameFilterButton() {
        waitElementToBeClickable(searchByPropertyNameButton);
        clickElement(searchByPropertyNameButton);
        log.info("The user click the \"Search by property name\" button");
    }


    /**
     * Set the Property Name
     *
     * @param propertyName
     */
    public void setPropertyName(String propertyName) {
        waitElementVisibility(propertyNameInput);
        propertyNameInput.sendKeys(propertyName);
        waitElementVisibility(hotelFirstOption);
        clickElement(hotelFirstOption);
        log.info("The user types and select the property that is looking for");

    }

    /**
     * Validate if Hotel Name Filter Mark is Present
     *
     * @return true: Boolean
     */
    public boolean isHotelNameFilterMarkPresent(){
        waitElementVisibility(hotelNameFilterMark);
        return hotelNameFilterMark.isDisplayed();
    }


    /**
     * Validate if Hotel Name is Present
     *
     * @return true: Boolean
     */
    public boolean isHotelNamePresent(){
        waitElementVisibility(hotelName);
        return hotelName.isDisplayed();
    }


    /**
     * Get the Hotel Name after filtering
     *
     * @return Hotel Name: String
     */
    public String getHotelName() {
        waitElementVisibility(hotelName);
        return hotelName.getText();
    }


    /**
     * Validate if Hotel Price is Present
     *
     * @return true: Boolean
     */
    public boolean isHotelPricePresent(){
        waitElementVisibility(hotelPrice);
        return hotelPrice.isDisplayed();
    }


    /**
     * Get the Hotel Price after filtering
     *
     * @return Price: String
     */
    public String getHotelPrice() {

        waitElementVisibility(hotelPrice);
        return hotelPrice.getText();
    }

    /**
     * Click on Search Button
     */
    public HotelDetailsPage clickHotelNameLink() {
        log.info("The user click the \"Hotel Name\" link");
        waitElementVisibility(hotelName);
        clickElement(hotelName);
        switchToLastOpenTab(getDriver());
        return new HotelDetailsPage(getDriver());
    }

}
