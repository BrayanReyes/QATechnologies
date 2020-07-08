package com.globant.web.pages.hotels;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HotelDetailsPage extends BasePage {

    @FindBy (css="h1[data-stid=\"content-hotel-title\"]")
    private WebElement hotelName;

    @FindBy (css="ul[data-stid=amenities-grid]")
    private WebElement amenitiesGrid;

    @FindBy (css="div[data-stid=mod-signup-signin-banner] .uitk-grid.uitk-cell")
    private WebElement membersDiscountBanner;

    @FindBy (css="div[data-stid=mod-signup-signin-banner] a:first-of-type")
    private WebElement signUpLink;

    /**
     *Constructor
     *
     * @param driver: WebDriver
     */
    public HotelDetailsPage(WebDriver driver) {
        super(driver);
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
     * Get the Hotel Name
     *
     * @return Hotel Name: String
     */
    public String getHotelName() {
        waitElementVisibility(hotelName);
        return hotelName.getText();
    }

    /**
     * Validate if Amenities Grid is Present
     *
     * @return true: Boolean
     */
    public boolean isAmenitiesGridPresent(){
        waitElementVisibility(amenitiesGrid);
        moveToElement(amenitiesGrid);
        return amenitiesGrid.isDisplayed();
    }

    /**
     * Validate if Members Discount Banner is Present
     *
     * @return true: Boolean
     */
    public boolean isMembersDiscountBannerPresent(){
        waitElementVisibility(membersDiscountBanner);
        moveToElement(membersDiscountBanner);
        return membersDiscountBanner.isDisplayed();
    }

    /**
     * Validate if Sign Up Link is Present
     *
     * @return true: Boolean
     */
    public boolean isSignUpLinkPresent(){
        waitElementVisibility(signUpLink);
        return signUpLink.isDisplayed();
    }


    /**
     * Get the Sign Up Link Text
     *
     * @return Sign Up Link Text: String
     */
    public String getSignUpLinkText() {
        waitElementVisibility(signUpLink);
        return signUpLink.getText();
    }

}
