package com.globant.web.pages.cruises;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CruisesResultPage extends BasePage {

    @FindBy(css = "#destination-toggle #destination-select")
    private WebElement goingToLabel;

    @FindBy(css = "#month-toggle .month-select-full")
    private WebElement DepartingBetweenLabel;

    @FindBy(id = "travelers-select")
    private WebElement TravelersLabel;

    @FindBy(css = ".cruise-page-header")
    private WebElement cruisesResultPageHeader;

    @FindBy(css = "aside[role='complementary'] input[name='length-10-14']")
    private WebElement cruiseLength_10_14_RadioButton;

    @FindBy(id = "uitk-live-announce")
    private WebElement pageUpdatedMarker;

    @FindBy(css = ".col.cruise-card-content .flex-card")
    private List<WebElement> cruisesResultsItem;

    @FindBy(className = "ship-name")
    private WebElement shipName;

    @FindBy(className = "card-price")
    private WebElement sailingPrice;

    @FindBy(className = "sailing-departing-on")
    private WebElement sailingDepartingDate;

    @FindBy(className = "sailing-returning-on")
    private WebElement sailingReturningDate;

    @FindBy(css = ".sort-options button[data-opt-group=Price]")
    private WebElement sortPriceButton;

    @FindBy(css = ".btn-container .btn.btn-secondary")
    private WebElement cruiseContinueButton;

    /**
     * Constructor
     * @param driver
     */
    public CruisesResultPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get the header for Cruises Results Page
     *
     * @return cruises results header: String
     */
    public String getCruisesResultsHeader() {
        waitElementVisibility(cruisesResultPageHeader);
            return cruisesResultPageHeader.getText().substring(10);
    }


    /**
     * Get Destination Label
     *
     * @return Destination: String
     */
    public String getDestinationLabel() {
        waitElementVisibility(goingToLabel);
        return goingToLabel.getText();
    }

    /**
     * Validate if Destination Label is displayed
     *
     * @return true: boolean
     */
    public boolean isDestinationLabelDisplayed() {
        waitElementVisibility(goingToLabel);
        return goingToLabel.isDisplayed();
    }

    /**
     * Get Dates Label
     *
     * @return Dates: String
     */
    public String getDatesLabel() {
        waitElementVisibility(DepartingBetweenLabel);
        return DepartingBetweenLabel.getText();
    }

    /**
     * Validate if Dates Label is displayed
     *
     * @return true: boolean
     */
    public boolean isDatesLabelDisplayed() {
        waitElementVisibility(DepartingBetweenLabel);
        return DepartingBetweenLabel.isDisplayed();
    }

    /**
     * Get Travelers Label
     *
     * @return Travelers: String
     */
    public String getTravelersLabel() {
        waitElementVisibility(TravelersLabel);
        return TravelersLabel.getText();
    }

    /**
     * Validate if Travelers Label is displayed
     *
     * @return true: boolean
     */
    public boolean isTravelersLabelDisplayed() {
        waitElementVisibility(TravelersLabel);
        return TravelersLabel.isDisplayed();
    }

    /**
     * Click on 10-14 Nights Cruise Length filter
     */
    public void filterByCruiseLength() {
        log.info("The user filter by cruse length");
        waitElementVisibility(cruiseLength_10_14_RadioButton);
        clickElement(cruiseLength_10_14_RadioButton);
        waitAttributeToBe(pageUpdatedMarker, "aria-live", "polite");

    }

    /**
     * Get Ship Name, Price and Dates for each item after searching
     *
     */

    public void checkResults() {

        cruisesResultsItem.forEach(item -> {

            moveToElement(item);
            String shipName = item.findElement(By.cssSelector(".ship-name")).getText();
            String sailingPrice = item.findElement(By.cssSelector(".card-price")).getText();
            String sailingDepartingDate = item.findElement(By.cssSelector(".sailing-departing-on")).getText();
            String sailingReturningDate = item.findElement(By.cssSelector(".sailing-returning-on")).getText();

            log.info("Name: " + shipName + "  Price: " + sailingPrice + "  Departing Date: " + sailingDepartingDate + "  Returning Date: " + sailingReturningDate);
        });
    }


        /**
         * Sort by Price
         */
        public void sortCruisesByPrice(){
            log.info("The user sort the cruises by price");
            waitElementVisibility(sortPriceButton);
            clickElement(sortPriceButton);
            waitAttributeToBe(pageUpdatedMarker, "aria-live", "polite");
        }

    /**
     * Select the chepest option in the list and continue to CabinDetailsPage
     * @return CabinDetailsPage
     */

        public CabinDetailsPage clickContinueButton (){
            WebElement shipChosen = cruisesResultsItem.get(0);
            moveToElement(shipChosen);
            log.info("The user selects the first option in the list.");
            String shipName = shipChosen.findElement(By.className("ship-name")).getText();
            moveToElement(shipChosen);
            WebElement chooseShipButton = shipChosen.findElement(By.cssSelector(".btn-container .btn.btn-secondary"));
            waitElementVisibility(chooseShipButton);
            clickElement(chooseShipButton);
            log.info("The user clicks the \"Continue\" Button.");
            switchToLastOpenTab(getDriver());
            return new CabinDetailsPage(getDriver());

        }

}