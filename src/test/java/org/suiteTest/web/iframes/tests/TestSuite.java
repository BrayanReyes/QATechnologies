package org.suiteTest.web.iframes.tests;

import org.suiteTest.web.iframes.data.FlyDetail;
import org.suiteTest.web.iframes.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestSuite extends BaseTest{

    private final String alertMessage6 = "We are only able to book between 1 and 6 travellers. Please adjust the number of travellers for your search.";
    private final String alertMessageDeparture="Tell us where you're flying to.";
    private final String dateDepature = LocalDate.now().plusDays(4).format(
            DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    private final String dateReturn = LocalDate.now().plusDays(10).format(
            DateTimeFormatter.ofPattern("MM/dd/yyyy"));

    @Test(description = "Check Fly", dataProvider = "FlyReservation",dataProviderClass = org.suiteTest.web.iframes.data.Data.class)
    public void checkFly(FlyDetail flyDetail) {
        HomePage home = getHomePage();
        home.flySection();
        home.setFlyKind();
        home.setFlyingFrom(flyDetail.getOrigin());
        home.setFlyingTo(flyDetail.getDestination());
        home.setDepartingDate();
        home.setReturningDate();
        home.setAdults(flyDetail.getAdults());
        home.setChildren(flyDetail.getChilds());
        home.setChildrenAge(flyDetail.getChildsAge());
        home.searchFly();
        Assert.assertTrue(home.getAlertMessages().contains(alertMessage6),
                "Error alert is not present.");
        if (flyDetail.getDestination().equals(""))
            Assert.assertTrue(home.getAlertMessages().contains(alertMessageDeparture),
                    "Error alert is not present when destination is empty.");

        // The next assert could be not necessary due to dates are set by program
        Assert.assertTrue(home.checkDepartureDate(dateDepature),
                "Error Departure Date is wrong");
        Assert.assertTrue(home.checkReturnDate(dateReturn),
                "Error Return Date is wrong");
    }

    @AfterMethod(alwaysRun = true)
    @Parameters({"url"})
    public void afterMethod(String url){
        log.info("Launch HomePage");
        getHomePage().switchToDefaultContent();
        driver.getDriver().get(url);

    }
}
