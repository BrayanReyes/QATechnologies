package org.suiteTest.web.iframes.tests;

import org.suiteTest.web.iframes.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestSuite extends BaseTest{

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
        //Assert.assert
    }


    @AfterMethod(alwaysRun = true)
    @Parameters({"url"})
    public void afterMethod(String url){
        log.info("Launch HomePage");
        getHomePage().switchToDefaultContent();
        driver.getDriver().get(url);
    }
}
