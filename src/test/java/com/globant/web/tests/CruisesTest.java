package com.globant.web.tests;

import com.globant.web.pages.HomePage;
import com.globant.web.pages.cruises.CabinDetailsPage;
import com.globant.web.pages.cruises.CruisesResultPage;
import com.globant.web.pages.cruises.CruisesSearchPage;
import com.globant.web.utils.TextValidations;
import com.globant.web.utils.CruisesParameters;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CruisesTest extends BaseTest {

    @Test(description = "Booking a Cruise",dataProvider="InputCruisesData",
            dataProviderClass = com.globant.web.data.DataInit.class)
    public void bookingCruiseTest(CruisesParameters cruisesParameters) {
        HomePage home = getHomePage();

        //-----------------------------------------------------> Travelocity Home Page <-----------------------------------------------------//

        CruisesSearchPage cruisesPage = home.selectCruisesTab();

        //-----------------------------------------------------> Cruises Search Page <-----------------------------------------------------//

        Assert.assertEquals(cruisesPage.getCruisesLabelText(), TextValidations.getCruisesPageLabel(), "CRUISES LABEL DOES NOT MATCH");
        cruisesPage.selectCruiseDestination(cruisesParameters.getCruiseDestination());
        cruisesPage.setDepartingDate(cruisesParameters.getDepartAsEarly());
        cruisesPage.setReturningDate(cruisesParameters.getDepartAsLate());
        CruisesResultPage resultPage = cruisesPage.clickSearchCruisesButton();

        //-----------------------------------------------------> Cruises Result Page <-----------------------------------------------------//

        resultPage.HandleCruiseCreditModal();
        Assert.assertTrue(resultPage.isDestinationLabelDisplayed(), "DESTINATION LABEL IS NOT DISPLAYED");
        Assert.assertTrue(resultPage.isTravelersLabelDisplayed(), "TRAVELERS LABEL IS NOT DISPLAYED");
        Assert.assertTrue(resultPage.isDatesLabelDisplayed(), "DATES LABEL IS NOT DISPLAYED");
        log.info("Cruises Results: " + resultPage.getCruisesResultsHeader());
        log.info("Destination Label: " + resultPage.getDestinationLabel());
        log.info("Travelers Label: " + resultPage.getTravelersLabel());
        log.info("Cruise Dates: " + resultPage.getDatesLabel());
        resultPage.filterByCruiseLength();
        log.info("Cruises Results: " + resultPage.getCruisesResultsHeader());
        resultPage.checkResults();
        log.info("--> Cruises discount is not displayed. Due that, I decided to sort by price <--");
        resultPage.sortCruisesByPrice();
        CabinDetailsPage cabinPage = resultPage.clickContinueButton();

        //-----------------------------------------------------> Cabin Details Page <-----------------------------------------------------//

        Assert.assertTrue(cabinPage.isCabinExperienceLabelPresent(), "CABIN EXPERIENCE LABEL IS NOT DISPLAYED");
        Assert.assertEquals(cabinPage.getCabinExperienceLabel(), TextValidations.getCabinExperienceLabel(), "CABIN LABEL DOES NOT MATCH");
        log.info("Cabin Details Page Header: " + cabinPage.getCabinExperienceLabel());
        Assert.assertTrue(cabinPage.isDestinationPresent(cruisesParameters.getCruiseDestination()), "DESTINATION IS NOT DISPLAYED");
        Assert.assertTrue(cabinPage.isShipNamePresent(), "SHIP NAME IS NOT DISPLAYED");
        Assert.assertTrue(cabinPage.isSailingDepartingDatePresent(), "DEPARTING DATE IS NOT DISPLAYED");
        Assert.assertTrue(cabinPage.isSailingReturningDatePresent(), "RETURNING DATE IS NOT DISPLAYED");
        log.info("******************* FINAL ASSERTS *******************");
        log.info("Destination: " + cabinPage.getDestination());
        log.info("Ship Name: " + cabinPage.getShipName());
        log.info("Nights Number: " + cabinPage.getNumberOfNights());
        log.info("Departing Sailing Date: " + cabinPage.getSailingDepartingDate());
        log.info("Returning Sailing Date: " + cabinPage.getSailingReturningDate());

    }
}
