package com.globant.web.pages.packages;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectFlightsPage extends BasePage {

    @FindBy(id = "uitk-live-announce")
    private WebElement updatedResults;

    //aria-live
    //polite
    //Departure results now sorted by Price (Lowest)

    @FindBy(className = "title-city-text")
    private WebElement flightsHeader;

    @FindBy(css = "li[data-test-id=\"offer-listing\"]")
    private List<WebElement> resultItems;

    @FindBy(css = "button[data-test-id=\"select-button\"]")
    private WebElement selectFlightButton;

    @FindBy(className = "basic-economy-tray uitk-grid")
    private WebElement rulesAndRestrictionDiv;

    @FindBy(css = "button[data-test-id=select-button-1]")
    private WebElement selectThisFareButton;

    private static final String INTERNAL_BUTTON_DIV_PATH = "//div[@id='basic-economy-tray-content-";
    private static final String INTERNAL_BUTTON_SELECT = "//button[@class='btn-secondary btn-action t-select-btn']";

    /**
     * Constructor
     *
     * @param driver : WebDriver
     */
    public SelectFlightsPage(WebDriver driver) {
        super(driver);
    }


    /**
     * Validate if Flights Header is present
     *
     * @return true: boolean
     */
    public boolean flightsHeaderIsPresent() {
        waitElementVisibility(flightsHeader);
        return flightsHeader.isDisplayed();
    }

    /**
     * Get Flight Header text
     *
     * @return Label: String
     */
    public String getFlightsHeaderText() {
        return flightsHeader.getText();
    }

    /**
     * Confirm the Flight selection accepting the Fare Button
     */
    private void confirmFlightOption(WebElement flightFound, int flightOption) {
        WebElement selectFlightButton = flightFound.findElement(By.cssSelector("button[data-test-id=\"select-button\"]"));
        clickElement(selectFlightButton);
        acceptTheFareButton(flightFound, flightOption);
    }

    /**
     * Accept the Fare Button
     */
    private void acceptTheFareButton(WebElement element, int option) {
        waitElementVisibility(element);
        WebElement selectFareButton = element.findElement(
                By.xpath(INTERNAL_BUTTON_DIV_PATH + option + "']" + INTERNAL_BUTTON_SELECT));
        clickElement(selectFareButton);
    }

    /**
     * Select the First Flight Option
     */
    public void selectFirstFlight() {
        log.info("The user clicks \"Select\" flight button");
        waitElementVisibility(resultItems);
        WebElement firstFlight = resultItems.stream().findFirst().get();
        moveToElement(firstFlight);
        log.info("The user click the \"Select This Fare\" button");
        confirmFlightOption(firstFlight, 1);
    }


    public void selectReturnOptionFlight(int returnFlightOption) {
        log.info("The user chooses the returning flight. Option " + returnFlightOption);
        waitElementVisibility(resultItems);
        WebElement listIndexElement = getListIndexElement(resultItems, returnFlightOption - 1);
        if (listIndexElement == null) {
            selectFirstFlight();
            log.info("Departure Flight Not Found. Select the first by default");
        } else {
            confirmFlightOption(listIndexElement, returnFlightOption);
            log.info("The user click the \"Select This Fare\" button");
        }
    }

    /**
     * Get the index for the "Fare Button" List
     */
    private WebElement getListIndexElement(List<WebElement> list, int index) {
        try {
            return list.get(index);
        } catch (Exception ex) {
            System.err.println("Not Found Select Fare");
        }
        return null;
    }

    /**
     * Select Flights
     */
    public void selectFlights(int optionFlight) {
        selectFirstFlight();
        selectReturnOptionFlight(optionFlight);
    }

    /**
     * Continue with the package booking process
     */
    public SummaryPackagePage goToSummaryPackagePage(){
        return new SummaryPackagePage(getDriver());
    }

}
