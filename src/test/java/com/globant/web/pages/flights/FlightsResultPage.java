package com.globant.web.pages.flights;

import com.globant.web.pages.BasePage;
import com.globant.web.pages.packages.SummaryPackagePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;

/**
 * Flights Result Page shows the result after perform a flights search, also allows to sort the flights
 * and select option for Departing and Returning flight
 */

public class FlightsResultPage extends BasePage {

    @FindBy(css = ".title-departure span[data-test-id=\"title-city-text\"]")
    private WebElement flightsPageHeader;

    @FindBy(id = "sortDropdown")
    private WebElement sortDropdown;

    @FindBy (id = "flightModuleList")
    private WebElement flightsResultList;

    @FindBy(css = "div[class=\"grid-container standard-padding \"]")
    private List<WebElement> resultItemsList;

    @FindBy(css = "div[class=\"uitk-col all-col-shrink\"] button[data-test-id=select-button]")
    private List<WebElement> selectFlightButtonList;

    @FindBy(className = "duration-emphasis")
    private List<WebElement> flightDurationLabelList;

    @FindBy(className = "flight-details-link")
    private List<WebElement> flightDetailsAndFeesList;

    @FindBy(id = "bCol")
    private WebElement flightListContainer;

    @FindBy(id = "xSellHotelForcedChoice")
    private WebElement forceHotelModal;

    @FindBy(id = "forcedChoiceNoThanks")
    private WebElement noThanksLink;

    private static final String FLIGHT_DURATION_CSS = "[data-test-id=\"duration\"]";
    private static final String HOUR_SEPARATOR = "h";
    private static final String MINUTE_SEPARATOR = "m";
    private static final String DEPARTING_FARE_BUTTON_CSS = ".basic-economy-footer .t-select-btn";
    private static final String FLIGHT_MODULE_CLASS = "flight-module";
    private static final String SELECT_BUTTON_CLASS = "t-select-btn";
    private static final String FLIGHTS_RESULT_LIST_CLASS = "#flightModuleList";
    private static final String REFRESHED_FLIGHTS_LIST_CLASS = "show-change-flight-filter";
    private static final String RETURNING_FARE_BUTTON_ID="#basic-economy-tray-content-";

    /**
     * Constructor,
     *
     * @param driver : WebDriver
     */
    public FlightsResultPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get Flight Page Header
     *
     * @return Label: String
     */
    public String getFlightPageHeader() {
        return flightsPageHeader.getText();
    }

    /**
     * Validate if SFlight Page Header is present
     *
     * @return true: boolean
     */
    public boolean isFlightPageHeaderPresent() {
        return flightsPageHeader.isDisplayed();
    }

    /**
     * Validate if Sort Dropdown is present
     *
     * @return true: boolean
     */
    public boolean isSortDropdownPresent() {
        return sortDropdown.isDisplayed();
    }

    /**
     * Validate if Select Button is present in each item result
     *
     * @return true: boolean
     */
    public boolean isSelectButtonPresentInEachDivResult() {
        waitElementVisibility(resultItemsList);
        int allFlightsItems = resultItemsList.size();
        boolean isSelectButtonPresent = Objects.equals(allFlightsItems, selectFlightButtonList.size());
        return isSelectButtonPresent;
    }

    /**
     * Validate if Flight Duration Label is present in each item result
     *
     * @return true: boolean
     */
    public boolean isFlightDurationPresentInEachDivResult() {
        waitElementVisibility(resultItemsList);
        int allFlightsItems = resultItemsList.size();
        boolean isFlightDurationPresent = Objects.equals(allFlightsItems, flightDurationLabelList.size());
        return isFlightDurationPresent;
    }

    /**
     * Validate if Flight Details and Baggage fees Label is present in each item result
     *
     * @return true: boolean
     */
    public boolean isFlightDetailsAndFeesPresentInEachDivResult() {
        waitElementVisibility(resultItemsList);
        int allFlightsItems = resultItemsList.size();
        boolean isDetailsAndFeesPresent = Objects.equals(allFlightsItems, flightDetailsAndFeesList.size());
        return isDetailsAndFeesPresent;
    }

    /**
     * Select a Dropwdown Element
     *
     * @param sortDropdown: WebElement
     * @return Select
     */
    private Select selectDropdownOption(WebElement sortDropdown) {
        clickElement(sortDropdown);
        return new Select(sortDropdown);
    }

    /**
     * Select {Duration (Shortest)} as value for the {sortDropdown}
     *
     * @param sortParameter: String
     */
    public void sortResultsBy(String sortParameter) {
        log.info("The user sort the flight by: " + sortParameter);
        waitElementVisibility(sortDropdown);
        for (WebElement sortOption : selectDropdownOption(sortDropdown).getOptions()) {
            String optTemp = sortOption.getText();
            if (optTemp.contains(sortParameter)) {
                sortOption.click();
                break;
            }
        }
        handleAdvertisement();
        }

    /**
     * Calculate the Flight Duration in minutes
     *
     * @param flightDuration: String
     */
    private int calculateTotalMinutesFlight(String flightDuration) {
        int durationInHourToMinutes = Integer.parseInt(flightDuration.substring(0, flightDuration.indexOf(HOUR_SEPARATOR))) * 60;
        int durationInMinutes = Integer.parseInt(flightDuration.substring(flightDuration.indexOf(" "), flightDuration.indexOf(MINUTE_SEPARATOR)).trim());
        int totalFlightDurationMinutes = durationInHourToMinutes + durationInMinutes;
        return totalFlightDurationMinutes;
    }

    /**
     * Validate if Flight results are correctly sorted by duration
     *
     * return true: boolean
     */
    public boolean validateFlightsListOrder() {
        String flightDuration = "";
        int previousItemDuration = 0;
        int currentItemDuration = 0;

        for (WebElement resultItem : resultItemsList) {
            flightDuration = resultItem.findElement(By.cssSelector(FLIGHT_DURATION_CSS)).getText();
            currentItemDuration = calculateTotalMinutesFlight(flightDuration);
            if (previousItemDuration > currentItemDuration)
                return false;
            previousItemDuration = currentItemDuration;
        }
        return true;
    }

    /**
     * Select a Departure Flight according with the number option
     * @param departingOption: int
     */

    public void selectDepartingFLight(int departingOption) {
        log.info("The user selects the Departing flight number: " + departingOption);
        waitListToBeRefreshed(REFRESHED_FLIGHTS_LIST_CLASS);
        WebElement departureFlightSelected = flightsResultList.findElements(By.className(FLIGHT_MODULE_CLASS)).get(departingOption-1);
        jsScroll(getDriver(), departureFlightSelected);
        departureFlightSelected.findElement(By.className(SELECT_BUTTON_CLASS)).click();
        log.info("The user clicks \"Select\" button");
        waitElementInsideContainer(departureFlightSelected, DEPARTING_FARE_BUTTON_CSS);
        departureFlightSelected.findElement(By.cssSelector(DEPARTING_FARE_BUTTON_CSS)).click();
        log.info("The user clicks \"Select Fare\" button");
    }

    /**
     * Select a Returning Flight according with the number option
     * @param returningOption: int
     */
    public void selectReturningFLight(int returningOption) {
        log.info("The user selects the Returning flight number: " + returningOption);
        moveToElement(flightListContainer);
        jsScroll(getDriver(), flightListContainer);
        waitListToBeRefreshed(REFRESHED_FLIGHTS_LIST_CLASS);
        WebElement returnFlight = flightsResultList.findElements(By.className(FLIGHT_MODULE_CLASS)).get(returningOption-1);
        returnFlight.findElement(By.className(SELECT_BUTTON_CLASS)).click();
        log.info("The user clicks \"Select\" button");
        getWait().until(ExpectedConditions.visibilityOf(returnFlight.findElement(By.cssSelector(RETURNING_FARE_BUTTON_ID + (returningOption)))));
        getWait().until(ExpectedConditions.elementToBeClickable(returnFlight.findElement(By.cssSelector(RETURNING_FARE_BUTTON_ID + (returningOption) + " button"))));
        returnFlight.findElement(By.cssSelector(RETURNING_FARE_BUTTON_ID + (returningOption) + " button")).click();
        log.info("The user clicks \"Select Fare\" button");
    }


    /**
     * Handle Force Hotel Modal and pass to the next Page for flight booking process
     */

    public ReviewYourTripPage goToReviewYourTripPage() {
        if (forceHotelModal.isEnabled()) {
            log.info("Force Hotel Modal shows up");
            getWait().until(ExpectedConditions.elementToBeClickable(noThanksLink));
            noThanksLink.click();
            log.info("The user click the \"No Thanks\" link");
            switchToLastOpenTab(getDriver());
        }
        handleAdvertisement();
        return new ReviewYourTripPage(getDriver());
    }

    /**
     * Continue with the package booking process
     */
    public SummaryPackagePage goToSummaryPackagePage(){
        return new SummaryPackagePage(getDriver());
    }

}