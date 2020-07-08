package com.globant.web.pages;

import com.globant.web.pages.cruises.CruisesSearchPage;
import com.globant.web.pages.flights.FlightsSearchPage;
import com.globant.web.pages.hotels.HotelsSearchPage;
import com.globant.web.pages.packages.PackagesSearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Home Page handle the main options in Travelocity.com page:
 * Flights, Hotels, Vacation Packages, Cars, Cruises and Things to Do
 */

public class HomePage extends BasePage {

    @FindBy(id = "tab-flight-tab-hp")
    private WebElement flightsTab;

    @FindBy(id = "tab-hotel-tab-hp")
    private WebElement hotelsTab;

    @FindBy(id = "tab-package-tab-hp")
    private WebElement vacationPackagesTab;

    @FindBy(id = "tab-car-tab-hp")
    private WebElement carsTab;

    @FindBy(id = "tab-cruise-tab-hp")
    private WebElement cruisesTab;

    @FindBy(id = "tab-activity-tab-hp")
    private WebElement thingsToDoTab;

    /**
     * Constructor.
     *
     * @param driver : WebDriver
     * @param url    : String
     */
    public HomePage(WebDriver driver, String url) {
        super(driver);
        driver.get(url);
    }

    /**
     * Click to {flightsTab} to continue the start booking process
     *
     * @return FlightSearchPage
     */

    public FlightsSearchPage selectFlightsTab() {
        log.info("The user clicks the \"Flights Tab\".");
        waitElementVisibility(flightsTab);
        flightsTab.click();
        return new FlightsSearchPage(getDriver());
    }


    /**
     * Click to {hotelsTab} to continue the start booking process
     *
     * @return HotelsSearchPage
     */

    public HotelsSearchPage selectHotelsTab() {
        log.info("The user clicks the \"Hotels Tab\".");
        waitElementVisibility(hotelsTab);
        hotelsTab.click();
        return new HotelsSearchPage(getDriver());
    }


    /**
     * Click to {vacationPackagesTab} to continue the start booking process
     *
     * @return CruisesSearchPage
     */

    public PackagesSearchPage selectVacationPackagesTab() {
        log.info("The user clicks the \"Vacation Packages Tab\".");
        waitElementVisibility(vacationPackagesTab);
        vacationPackagesTab.click();
        return new PackagesSearchPage(getDriver());
    }

    /**
     * Click to {cruisesTab} to continue the start booking process
     *
     * @return CruisesSearchPage
     */

    public CruisesSearchPage selectCruisesTab() {
        log.info("The user clicks the \"Cruises Tab\".");
        waitElementVisibility(cruisesTab);
        cruisesTab.click();
        return new CruisesSearchPage(getDriver());
    }


}
