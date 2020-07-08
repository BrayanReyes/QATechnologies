package com.globant.web.pages.flights;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.util.NoSuchElementException;

public class FlightsSearchPage extends BasePage {


    @FindBy(id = "flight-type-roundtrip-label-hp-flight")
    private WebElement flightsRoundTripOption;

    @FindBy(id = "flight-origin-hp-flight")
    private WebElement flightsFlyingFromInput;

    @FindBy(id = "flight-destination-hp-flight")
    private WebElement flightsFlyingToInput;

    @FindBy(id = "flight-departing-hp-flight")
    private WebElement departingFlightsDataPicker;

    @FindBy(id = "flight-returning-hp-flight")
    private WebElement returningFlightsDataPicker;

    @FindBy(id = "flight-adults-hp-flight")
    private WebElement adultsFlightsNumber;

    @FindBy(id = "flight-children-hp-flight")
    private WebElement childrenFlightsNumber;

    @FindBy(css = "#gcw-flights-form-hp-flight button[type=submit]")
    private WebElement searchFlightsButton;

    ////////

    @FindBy (className = "datepicker-close")
    private WebElement calendarLabel;

    @FindBy (css = "#package-departing-wrapper-hp-package .datepicker-dropdown")
    private  WebElement calendarDiv;

    @FindBy(css = "button[class*=\"btn-paging btn-secondary next\"]")
    private WebElement nextMonthButton;

    /**
     *Constructor
     *
     * @param driver: WebDriver
     */
    public FlightsSearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get the text for RoundYTrip Option
     *
     * @return Round Trip: String
     */
    public String getFlyingTypeText() {
        return flightsRoundTripOption.getText();
    }


    /**
     * Click on RoundTrip
     */
    public void selectFlightType() {
        waitElementVisibility(flightsRoundTripOption);
        clickElement(flightsRoundTripOption);
        log.info("The user clicks Round Trip Option");
    }

    /**
     * Set the City where you are going to depart
     *
     * @param flyingFrom
     */
    public void setFlyingFrom(String flyingFrom) {
        waitElementVisibility(flightsFlyingFromInput);
        flightsFlyingFromInput.sendKeys(flyingFrom);
        log.info("The user types the city where is flying from");
    }

    /**
     * Set the City where do will return
     *
     * @param flyingTo
     */
    public void setFlyingTo(String flyingTo) {
        waitElementVisibility(flightsFlyingToInput);
        flightsFlyingToInput.sendKeys(flyingTo);
        log.info("The user types the city where is flying to");

    }

    /**
     * Open the calendar
     *
     */

    private void openCalendar() {
        // If in case the calendar does not open automatically
        clickElement(departingFlightsDataPicker);
        if (!calendarLabel.isDisplayed()) {
            waitElementVisibility(calendarDiv);
            clickElement(calendarDiv);
        }
        moveToElement(calendarLabel);
    }

    /**
     * Find the element for the date component in the calendar
     *
     * @param cssSelector: String
     */
    private WebElement findDateElementByCSS(String cssSelector) {
        try {
            return getDriver().findElement(By.cssSelector(cssSelector));
        } catch (Exception e) {
            // log.info(e);
            waitElementToBeClickable(nextMonthButton);
            clickElement(nextMonthButton);
            return findDateElementByCSS(cssSelector);
        }
    }

    /**
     * Select a Date according with a number of days given
     *
     * @param daysFromNow: int
     */

    private void selectDate(int daysFromNow) {
        String tmpCSS = calculateDateCSS(daysFromNow);
        WebElement tmpDate = findDateElementByCSS(tmpCSS);
        clickElement(tmpDate);
    }

    /**
     * Set up the [data-date] value for the check-in, chek-out dates
     * [data-date="2020-06-26"]
     *
     * @param days: int
     *
     */

    private String calculateDateCSS(int days) {
        LocalDate increasedDate = LocalDate.now().plusDays(days).minusMonths(1);
        return "button[data-year='" + increasedDate.getYear() + "']" + "[data-month='" + increasedDate.getMonthValue() + "']"
                + "[data-day='" + increasedDate.getDayOfMonth() + "']";
    }

    /**
     * Set the Departing Date. The date should be 30 days after today
     */

    public void setDatesToDepart(int daysForward) {
        openCalendar();
        selectDate(daysForward);
        log.info("The user selects the Departing Date through the Calendar");
    }


    /**
     * Set the Returning Date. The date should be 10 days after today
     */
    public void setReturningDate(int daysToReturn) {
        setReturningDate(returningFlightsDataPicker,daysToReturn);
        log.info("The user selects the Returning Date through the Calendar");
    }

    /**
     * Set the adults quantity for the flight
     *
     * @param numberOfAdults
     */
    public void setAdultsQuantity(String numberOfAdults) {
        log.info("The user selects the Adults quantity in the dropdown list");
        selectPassengersQuantity(adultsFlightsNumber,numberOfAdults);
    }

    /**
     * Set the children quantity for the flight
     *
     * @param numberOfChildren
     */
    public void setChildrenQuantity(String numberOfChildren) {
        log.info("The user selects the Children quantity in the dropdown list");
        selectPassengersQuantity(childrenFlightsNumber,numberOfChildren);
    }


    /**
     * Set the age of every children
     *
     * @param childrenAge
     */
    public void setChildrenAge(String... childrenAge) {
        log.info("Setting Children Ages in the dropdown list");
        for (int i = 0; i < childrenAge.length; i++) {
            // ID of CSS Selector generate by Children
            String tmpLocator = "#flight-age-select-" + (i + 1) + "-hp-flight";
            WebElement childAgeWebElement = getDriver().findElement(By.cssSelector(tmpLocator));
            Select childAgeSelector = new Select(childAgeWebElement);
            try {
                childAgeSelector.selectByValue(childrenAge[i]);
            } catch (NoSuchElementException exception) {
                log.info(" ERROR --> Adults value not allowed " + childrenAge[i]);
            }
        }
    }

    /**
     * Click on Search Button
     */
    public FlightsResultPage clickSearchFlightButton() {
        log.info("The user click the \"Search\" button");
        clickElement(searchFlightsButton);
        return new FlightsResultPage(getDriver());

    }

}
