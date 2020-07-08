package com.globant.web.pages.flights;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReviewYourTripPage extends BasePage {

    @FindBy(css = "#fisHeader .section-header-main")
    private WebElement reviewYourTripHeader;

    @FindBy(css = ".tripSummaryContainer.desktopView .totalContainer")
    private WebElement priceContainer;

    @FindBy(css = ".tripSummaryContainer.desktopView .packagePriceTotal")
    private WebElement totalPriceLabel;

    @FindBy(css = "section[class$=desktopView] .priceGuarantee span")
    private WebElement priceGuaranteeLabel;

    @FindBy(css = ".flightSummaryContainer div[class*=\"flex-card\"]:nth-child(2) .flex-area-primary")
    private WebElement departureDiv;

    @FindBy(css = ".flightSummaryContainer div[class*=\"flex-card\"]:nth-child(3) .flex-area-primary")
    private WebElement returnDiv;

    @FindBy(id = "bookButton")
    private WebElement continueBookingButton;

    /**
     * Constructor
     *
     * @param driver : WebDriver
     */
    public ReviewYourTripPage(WebDriver driver) {
        super(driver);
        handleNextWindow(driver);
    }

    /**
     * Validate if Review Your Trip Header is present
     *
     * @return true: boolean
     */
    public boolean isReviewYourTripHeaderPresent() {
        waitElementVisibility(reviewYourTripHeader);
        log.info("CHECK HERE: " + reviewYourTripHeader);
        return reviewYourTripHeader.isDisplayed();
    }


    /**
     * Get Review Your Trip Header
     *
     * @return Label: String
     */
    public String getReviewYourTripHeader() {
        waitElementVisibility(reviewYourTripHeader);
        return reviewYourTripHeader.getText();
    }

    /**
     * Validate if Price Container is displayed
     *
     * @return true: boolean
     */
    public boolean isPriceContainerPresent() {
        waitElementVisibility(priceContainer);
        return priceContainer.isDisplayed();
    }

    /**
     * Validate if Total Price Label is present
     *
     * @return true: boolean
     */
    public boolean isPriceLabelPresent() {
        waitElementVisibility(totalPriceLabel);
        return totalPriceLabel.isDisplayed();
    }

    /**
     * Get Total Price Label
     *
     * @return Label: String
     */
    public String getTotalPriceLabel() {
        waitElementVisibility(totalPriceLabel);
        return totalPriceLabel.getText();
    }

    /**
     * Validate if Total Price Guarantee is present
     *
     * @return true: boolean
     */
    public boolean isPriceGuaranteeLabelPresent() {
        getWait().until(ExpectedConditions.visibilityOfAllElements(priceGuaranteeLabel));
        return priceGuaranteeLabel.isDisplayed();
    }

    /**
     * Get Total Price Guarantee Label
     *
     * @return Label: String
     */
    public String getPriceGuaranteeLabel() {
        getWait().until(ExpectedConditions.visibilityOfAllElements(priceGuaranteeLabel));
        return priceGuaranteeLabel.getText();
    }

    /**
     * Validate if Depart Div is present
     *
     * @return true: boolean
     */
    public boolean isDepartDivPresent() {
        waitElementVisibility(departureDiv);
        return departureDiv.isDisplayed();
    }

    /**
     * Validate if Return Div is present
     *
     * @return true: boolean
     */
    public boolean isReturnDivPresent() {
        waitElementVisibility(returnDiv);
        return returnDiv.isDisplayed();
    }

    /**
     * Click on Continue Booking Button
     */
    public PaymentFlightPage clickContinueBookingButton() {
        log.info("The user click the \"Continue Booking\" button");
        clickElement(continueBookingButton);
        switchToLastOpenTab(getDriver());
        return new PaymentFlightPage(getDriver());

    }

}
