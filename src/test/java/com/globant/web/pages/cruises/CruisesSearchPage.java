package com.globant.web.pages.cruises;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.NoSuchElementException;

public class CruisesSearchPage extends BasePage {

    @FindBy (className = "gcw-cruise-travelersLabel")
    private WebElement cruisesSearchPageLabel;

    @FindBy(id = "cruise-destination-hp-cruise")
    private WebElement cruiseDestinationSelector;

    @FindBy(className = "datepicker-cal")
    private WebElement calendarDiv;

    @FindBy(className = "datepicker-cal-month-header")
    private WebElement calendarHeader;

    @FindBy(id = "cruise-start-date-hp-cruise")
    private WebElement departAsEarlyDateDataPicker;

    @FindBy(id = "cruise-end-date-hp-cruise")
    private WebElement departAsLateDateDataPicker;

    @FindBy(id = "cruise-adults-hp-cruise")
    private WebElement cruiseAdultsNumber;

    @FindBy(id = "cruise-children-hp-cruise")
    private WebElement cruiseChildrenNumber;

    @FindBy(css = "#gcw-cruises-form-hp-cruise button[type=submit]")
    private WebElement searchCruisesButton;


    /**
     * Constructor
     *
     * @param driver : WebDriver
     */
    public CruisesSearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get the label for Cruises Option
     *
     * @return cruises label: String
     */
    public String getCruisesLabelText() {
        return cruisesSearchPageLabel.getText();
    }


    /**
     * Select {Europe} as value for the {cruiseDestinationInput}
     *
     * @param destination: String
     */
    public void selectCruiseDestination(String destination) {
        waitElementVisibility(cruiseDestinationSelector);
        selectElementFromDropDownList(cruiseDestinationSelector, destination);
    }

    /**
     * Set the Departing Date. The date should be 30 days after today
     */
    public void setDepartingDate(int daysFrom) {
        setDepartingDate(departAsEarlyDateDataPicker,daysFrom);
        log.info("The user selects the Departing Date through the Calendar");
    }

    /**
     * Set the Returning Date. The date should be 10 days after today
     */
    public void setReturningDate(int daysTo) {
        setReturningDate(departAsLateDateDataPicker,daysTo);
        log.info("The user selects the Returning Date through the Calendar");
    }

    /**
     * Set the adults quantity for the flight
     *
     * @param numberOfAdults
     */
    public void setAdultsQuantity(String numberOfAdults) {
        log.info("The user selects the Adults quantity in the dropdown list");
        selectPassengersQuantity(cruiseAdultsNumber,numberOfAdults);
    }

    /**
     * Set the children quantity for the flight
     *
     * @param numberOfChildren
     */
    public void setChildrenQuantity(String numberOfChildren) {
        log.info("The user selects the Children quantity in the dropdown list");
        selectPassengersQuantity(cruiseChildrenNumber,numberOfChildren);
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
    public CruisesResultPage clickSearchCruisesButton() {
        log.info("The user click the \"Search\" button");
        waitElementVisibility(searchCruisesButton);
        clickElement(searchCruisesButton);

        return new CruisesResultPage(getDriver());
    }


}
