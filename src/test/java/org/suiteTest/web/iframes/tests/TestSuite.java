package org.suiteTest.web.iframes.tests;

import org.suiteTest.web.iframes.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestSuite extends BaseTest{

    @AfterMethod(alwaysRun = true)
    @Parameters({"url"})
    public void afterMethod(String url){
        log.info("Launch HomePage");
        getHomePage().switchToDefaultContent();
        driver.getDriver().get(url);
    }

    @Test(description = "Check Fly")
    public void checkFly() {
        HomePage home = getHomePage();
        home.flySection();
        home.setFlyKind();
        home.setFlyingFrom("LAS");
        home.setFlyingTo("LAX");
        home.setAdults("3");
        home.setChildren("2");
        home.setDepartingDate();
        home.setReturningDate();
        String[] a = {"0","10"};
        home.setChildrenAge(a);
    }
}
