package org.espnSuite.web.tests;

import org.espnSuite.web.pages.HomePage;
import org.espnSuite.web.pages.SingInUpIFrame;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ESPNTest extends BaseTest{


    @Test(description = "Test Sing In")
    public void logInTest(){
        HomePage homePage=getHomePage();
        SingInUpIFrame singInUpIFrame = homePage.goToSingIn();
        singInUpIFrame.singInEPSN("qwaszx@espn.com","Espn2020+");
        homePage.switchToDefaultContent();
        Assert.assertTrue(homePage.validateUserLogged("King"),"ERROR USER NAME IS NOT PRESENT");
    }

    @Test(description = "Test Log Out")
    public void logOutTest(){
        HomePage homePage=getHomePage();
        homePage.logOut();
        Assert.assertTrue(homePage.validateUserLoggOut(),"USER COULD NOT LOG OUT");
    }

    @AfterMethod(alwaysRun = true)
    @Parameters({"url"})
    public void afterMethod(String url){
        log.info("Launch HomePage");
        //getHomePage().switchToDefaultContent();
        //driver.getDriver().get(url);

    }

}
