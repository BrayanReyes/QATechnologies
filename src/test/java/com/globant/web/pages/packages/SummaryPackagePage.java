package com.globant.web.pages.packages;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Summary Package Page shows the whole vacational package information and allows to enter the
 * payment information.
 */

public class SummaryPackagePage extends BasePage {

    @FindBy (id = "destination-text")
    private WebElement summaryPageHeader;

    @FindBy (id = "lx-gt-highlight")
    private WebElement addTransportationLink;

    @FindBy (css = "#dxContainer #dxGroundTransportationModule")
    private WebElement transportationModule;

    @FindBy (css = ".gt-add-btn")
    private WebElement addToTripButton;

    @FindBy (css = "#dxGroundTransportationModule .uitk-grid > div:first-of-type .added-to-trip")
    private WebElement addedToTripMark;

    // For Asserts

    @FindBy (id = "rspFlightsContainer")
    private WebElement flightSummaryDiv;

    @FindBy (css = ".flight .pkg-summary")
    private WebElement flightPackageSummaryDiv;

    @FindBy (css = ".hotel .pkg-summary")
    private WebElement hotelSummaryDiv;

    @FindBy (css = ".activities .package-summary-dx.Transp")
    private WebElement transportationSummaryDiv;

    @FindBy (id = "totalContainer")
    private WebElement priceSummaryDiv;

    @FindBy (css = "#FlightUDPBookNowButton1 button")
    private WebElement nextFinalDetailsButton;


    /**
     * Constructor
     *
     * @param driver : WebDriver
     */
    public SummaryPackagePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Validate if Summary Page Header is present
     *
     * @return true: boolean
     */
    public boolean isSummaryPageHeaderPresent() {
        return summaryPageHeader.isDisplayed();
    }

    /**
     * Get Summary Page Header text
     *
     * @return Label: String
     */
    public String getSummaryPageHeaderText() {
        return summaryPageHeader.getText().trim();
    }

    /**
     * Validate if Add Transportation Link is present
     *
     * @return true: boolean
     */
    public boolean isTransportationLinkPresent() {
        return addTransportationLink.isDisplayed();
    }

    /**
     * Click on Add Transportation Link
     */
    public void clickAddTransportationLink() {
        log.info("The user click the \"Add Transportation\" link");
        waitElementToBeClickable(addTransportationLink);
        clickElement(addTransportationLink);
    }

    /**
     * Validate if Transportation Module is present
     *
     * @return true: boolean
     */
    public boolean isTransportationModulePresent() {
        return transportationModule.isDisplayed();
    }

    /**
     * Click on Add Transportation
     */
    public void clickAddCarToTripButton() {
        if (transportationModule.isDisplayed() && transportationModule.isEnabled()) {
            List<WebElement> addCarButtonList = transportationModule.findElements(By.cssSelector(".gt-add-btn"));
            if (!addCarButtonList.isEmpty()) {
                WebElement addToTripButton = addCarButtonList.stream().findFirst().get();
                clickElement(addToTripButton);
                log.info("The user click the \"Add To Trip\" button");
            }
        }
    }

    /**
     * Validate if Added to Trip Mark is present
     *
     * @return true: boolean
     */
    public boolean isAddedToTripMarkPresent() {
        return addedToTripMark.isDisplayed();
    }


    /**
     * Get Added To Trip Mark text
     *
     * @return Label: String
     */
    public String getAddedToTripMarkText() {
        return addedToTripMark.getText().trim();
    }

    //Assertions

    /**
     * Validate if Flight Summary Div is present
     *
     * @return true: boolean
     */
    public boolean isFlightSummaryDivPresent() {
        return flightSummaryDiv.isDisplayed();
    }

    /**
     * Validate if Flight Package Summary Div is present
     *
     * @return true: boolean
     */
    public boolean isFlightPackageSummaryDivPresent() {
        return flightPackageSummaryDiv.isDisplayed();
    }

    /**
     * Validate if Hotel Summary Div is present
     *
     * @return true: boolean
     */
    public boolean isHotelSummaryDivPresent() {
        waitElementVisibility(hotelSummaryDiv);
        return hotelSummaryDiv.isDisplayed();
    }

    /**
     * Validate if Transportation Summary Div is present
     *
     * @return true: boolean
     */
    public boolean isTransportationSummaryDivPresent() {
        waitElementVisibility(transportationSummaryDiv);
        return transportationSummaryDiv.isDisplayed();
    }

    /**
     * Validate if Price Summary Div is present
     *
     * @return true: boolean
     */
    public boolean isPriceSummaryDivPresent() {
        return priceSummaryDiv.isDisplayed();
    }

    /**
     * Click on Next Final Details Button
     */
    public ReviewAndBookPage clickNextFinalDetailsButton() {
        log.info("The user click the \"Next: Final Details\" button");
        waitElementVisibility(nextFinalDetailsButton);
        clickElement(nextFinalDetailsButton);
        return new ReviewAndBookPage(getDriver());
    }

}
