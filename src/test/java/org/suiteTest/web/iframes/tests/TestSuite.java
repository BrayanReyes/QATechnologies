package org.suiteTest.web.iframes.tests;

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

    private final String alertMessage = "We are only able to book between 1 and 6 travellers. Please adjust the number of travellers for your search.";
    private final String dateDepature = LocalDate.now().plusDays(4).format(
            DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    private final String dateReturn = LocalDate.now().plusDays(10).format(
            DateTimeFormatter.ofPattern("MM/dd/yyyy"));

    @Test(description = "Check Fly", dataProvider = "FlyReservation",dataProviderClass = org.suiteTest.web.iframes.data.Data.class)
    public void checkFly(String p1, String p2, String p3, String p4, String p5) {
        HomePage home = getHomePage();
        home.flySection();
        home.setFlyKind();
        home.setFlyingFrom(p1);
        home.setFlyingTo(p2);
        home.setDepartingDate();
        home.setReturningDate();
        home.setAdults(p3);
        home.setChildren(p4);
//        String[] a = {"0","10","1","2","3"};
//        home.setChildrenAge(a);
        home.setChildrenAge(p5);
        home.searchFly();
        Assert.assertEquals(home.getAlertMessage(),alertMessage,
                "Error alert is not "+alertMessage);

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
