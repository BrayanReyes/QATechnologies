package com.globant.web.pages.cruises;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Cruises Result Page shows the results after search for a cruise, also allows to
 * see the details of the ship and sort the findings
 * @author july.moreno
 *
 */
public class CruisesResultPage extends BasePage {

    @FindBy(css = "#destination-toggle #destination-select")
    private WebElement goingToLabel;

    @FindBy(css = "#month-toggle .month-select-full")
    private WebElement DepartingBetweenLabel;

    @FindBy(id = "travelers-select")
    private WebElement TravelersLabel;

    @FindBy(css = ".cruise-page-header > span:first-of-type")
    private WebElement cruisesResultPageHeader;

    @FindBy(css = ".filter-options-container input[name='length-10-14']")
    private WebElement cruiseLength_10_14;

    @FindBy(id = "uitk-live-announce")
    private WebElement pageUpdatedMarker;

    @FindBy(id = "travel-advisory-wrap")
    private WebElement modalIndicator;

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

    @FindBy(id = "active-fcc-info-modal")  
    private WebElement futureCruiseModal;

    @FindBy(className = "modal-loader")
    private WebElement modalLoader;

    @FindBy(id = "fcc-info-modal")
    private WebElement closeModalButton;

    @FindBy(css = ".desktop-filter-container:first-of-type .filter-options-container > div")
    private WebElement cruiseLengthContainer;

    @FindBy(css = ".desktop-filter-container .options-group-container input[name^=\"length\"]")
    private List<WebElement> cruiseLengthRadioButtonList;

    @FindBy(css = ".desktop-filter-container .options-group-container input[name^=\"length-10-14\"]")
    private WebElement cruiseLength_10_14_RadioButton;

    @FindBy(css = ".desktop-filter-container .options-group-container label[id^=\"length\"]")
    private WebElement nightsLabel;

    @FindBy(css = ".desktop-filter-container:first-of-type .options-group-container label[id^=\"length\"]")
    private List<WebElement> optionsLabelList;

    @FindBy(css = ".desktop-filter-container:first-of-type .options-group-container label[id^=\"length-10-14\"]")
    private WebElement option_10_14_Label;

    /**
     * Constructor
     *
     * @param driver: WebDriver
     */
    public CruisesResultPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Handle Future Cruise Credit Modal
     */

    public void HandleCruiseCreditModal() {
        if (futureCruiseModal.isEnabled() || futureCruiseModal.isDisplayed()) {
            log.info("Future Cruise Modal shows up");
            waitAttributeToBe(modalIndicator,"aria-hidden","true");
            WebElement closeModalIcon = futureCruiseModal.findElement(By.id("modalCloseButton"));

            handleAdvertisement();
            clickElement(closeModalIcon);
            log.info("User close \"Future Cruise Credit\" modal");

        }
    }

    /**
     * Get the header for Cruises Results Page
     *
     * @return cruises results header: String
     */
    public String getCruisesResultsHeader() {
        waitElementVisibility(cruisesResultPageHeader);
        return cruisesResultPageHeader.getText();
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
     * Get the header for Cruises Results Page
     *
     * @return cruises results header: String
     */
    public String getNightsLabel() {
        waitElementVisibility(nightsLabel);
        return nightsLabel.getText();
    }

    /**
     * Validate the length of nights cruise filter
     */
    public int validateOptionsToFilter() {
        waitElementVisibility(cruiseLengthRadioButtonList);
        return cruiseLengthRadioButtonList.size();
    }

    /**
     * Click on 10-14 Nights Cruise Length filter
     */
    public void filterByCruiseLength() {
        switch (validateOptionsToFilter()) {
            case 0:
                log.info("There is not options to filter by night length");
                break;
            case 1:
                WebElement sortOption = cruiseLengthRadioButtonList.stream().findFirst().get();
                clickElement(sortOption);
                log.info("Filter by 10 - 14 nights is not available. Selecting the first option.");
                log.info("Nights filter: " + getNightsLabel());
                break;
            default:
                waitElementVisibility(cruiseLength_10_14_RadioButton);
                clickElement(cruiseLength_10_14_RadioButton);
                log.info("The user filter by 10 - 14 cruise length");
         }
        waitAttributeToBe(pageUpdatedMarker, "aria-live", "polite");
    }

    /**
     * Get Ship Name, Price and Dates for each item after searching
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
    public void sortCruisesByPrice() {
        log.info("The user sort the cruises by price");
        waitElementVisibility(sortPriceButton);
        clickElement(sortPriceButton);
        waitAttributeToBe(pageUpdatedMarker, "aria-live", "polite");
    }

    /**
     * Select the chepest option in the list and continue to CabinDetailsPage
     *
     * @return CabinDetailsPage
     */

    public CabinDetailsPage clickContinueButton() {
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