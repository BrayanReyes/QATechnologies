package org.espnSuite.web.tests;

import org.espnSuite.web.data.UserDataESPN;
import org.espnSuite.web.pages.HomePage;
import org.espnSuite.web.pages.ESPNIFrame;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Collections;

public class ESPNTest extends BaseTest{

    @Test(description = "Create user account", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
            dataProvider = "Users",enabled = true)
    public void createAccount(UserDataESPN user){
        HomePage homePage=getHomePage();
        ESPNIFrame espnIFrame = homePage.goToSingInUp();
        espnIFrame.singUpESPN(user);
        Assert.assertTrue(Collections.disjoint(espnIFrame.alertMessagesRaised()
                ,espnIFrame.getErrorSingUP()));
        //Assert.assertTrue(homePage.validateUserLogged(user.getFirstName()),"ERROR USER NOT PRESENT");
    }


    @Test(description = "Test Sing In", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
            dataProvider = "Users",enabled = false)
    public void logInTest(UserDataESPN user){
        HomePage homePage=getHomePage();
        ESPNIFrame espnIFrame = homePage.goToSingInUp();
        espnIFrame.singInESPN(user.getEmail(),user.getPassword());
        Assert.assertTrue(homePage.validateUserLogged(user.getFirstName()),"ERROR USER NAME IS NOT PRESENT");
    }

    @Test(description = "Test Log Out", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
            dataProvider = "Users",enabled = false)
    public void zlogOutTest(UserDataESPN user){
        HomePage homePage=getHomePage();
        homePage.logOut();
        Assert.assertTrue(homePage.validateUserLogOut(),"USER COULD NOT LOG OUT");
    }

    @Test(description = "Delete account", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
            dataProvider = "Users",enabled = false)
    public void wdeleteAccount(){
        HomePage homePage=getHomePage();
        ESPNIFrame espnIFrame =  homePage.goToDeleteAccount();
        espnIFrame.deleteAccountSubmit();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        log.info("Launch HomePage");
        getHomePage().switchToDefaultContent();
    }

}
