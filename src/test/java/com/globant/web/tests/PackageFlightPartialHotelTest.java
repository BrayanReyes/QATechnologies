package com.globant.web.tests;

import com.globant.web.data.DataInit;
import com.globant.web.pages.HomePage;
import com.globant.web.pages.flights.FlightsResultPage;
import com.globant.web.pages.packages.*;
import com.globant.web.utils.FlightsParameters;
import com.globant.web.utils.PackagesParameters;
import com.globant.web.utils.TextValidations;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Package Test handles the process of booking a Vacation Package, according with the options:
 * {Flight + Hotel }, {Flight + Hotel + Car }, {Flight + Car } and {Hotel + Car}
 */

public class PackageFlightPartialHotelTest extends BaseTest {

    @Test(description = "Verify that the error message displayed when looking for a hotel in incorrect dates is correct.",
            dataProvider = "InputPackageData",
            dataProviderClass = DataInit.class,
            priority = 2,
            enabled = true)

    public void bookingPartialPackageTest(PackagesParameters packagesParameters) {
        HomePage home = getHomePage();

        //-----------------------------------------------------> Travelocity Home Page <-----------------------------------------------------//

        PackagesSearchPage vacationPackagePage = home.selectVacationPackagesTab();

        //-----------------------------------------------------> Vacation Package Search Page <-----------------------------------------------------//

        Assert.assertEquals(vacationPackagePage.getVacationPackageType(), TextValidations.getVacationPackageType(), "VACATION PACKAGE OPTION DOES NOT MATCH");
        vacationPackagePage.selectPackageType();
        vacationPackagePage.setFlyingFrom(packagesParameters.getFlyingFrom());
        vacationPackagePage.setFlyingTo(packagesParameters.getFlyingTo());
        vacationPackagePage.setDepartingDate(packagesParameters.getFlightDaysForward());
        vacationPackagePage.setReturningDate(packagesParameters.getFlightDaysToReturn());
        vacationPackagePage.filterPartOfStay();
        vacationPackagePage.setCheckInDate(packagesParameters.getCheckInDaysForward());
        vacationPackagePage.setCheckOutDate(packagesParameters.getCheckOutDaysToReturn());
        vacationPackagePage.clickSearchPackageButton();
        Assert.assertTrue(vacationPackagePage.isDatesErrorMessagePresent(), "ERROR MESSAGE IS NOT DISPLAYED");
        Assert.assertEquals(vacationPackagePage.getDatesErrorMessage(), TextValidations.getPackageAlertMessage(), "DATES ERROR MESSAGE IS NOT THE SAME");
        log.info("Error Message: " + vacationPackagePage.getDatesErrorMessage());
    }


}