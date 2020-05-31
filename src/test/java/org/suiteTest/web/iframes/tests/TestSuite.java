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

    @Test(description = "Check Fly")
    public void checkFly() {
        HomePage home = getHomePage();
        home.flySection();
        home.setFlyKind();
        home.setFlyingFrom("LAS");
        home.setFlyingTo("LAX");
        home.setDepartingDate();
        home.setReturningDate();
        home.setAdults("4");
        home.setChildren("5");
//        String[] a = {"0","10","1","2","3"};
//        home.setChildrenAge(a);
        home.setChildrenAge("0","10","1","2","3");
        home.searchFly();
        log.info(dateDepature);
        log.info(dateReturn);
        Assert.assertEquals(home.getAlertMessage(),alertMessage,
                "Error Alert is not the same: "+ home.getAlertMessage());

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
