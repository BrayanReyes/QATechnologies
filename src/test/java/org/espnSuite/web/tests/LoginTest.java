package org.espnSuite.web.tests;

import org.espnSuite.web.data.UserDataESPN;
import org.espnSuite.web.pages.ESPNIFrame;
import org.espnSuite.web.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


	@BeforeMethod(description = "Before to Log In")
	@Test(description = "Create User Account", dataProviderClass = org.espnSuite.web.data.DataProviders.class, dataProvider = "Users", enabled = true)
	public void createAccount(UserDataESPN user, String homePageTitle, String userMenuTitle, String loginValidation, String signupTitle, String logoutValidation) {
		HomePage homePage = getHomePage();
		Assert.assertEquals(homePage.getHomePageTitle(), homePageTitle, "HOME PAGE TITLE IS NO AS EXPECTED");
		homePage.goToUserMenu();
		Assert.assertEquals(homePage.validateUserLoggedOut(), userMenuTitle, "WELCOME MESSAGE IS NO AS EXPECTED");
		ESPNIFrame espnIFrame = homePage.goToSignInUpIFrame();
		Assert.assertEquals(espnIFrame.getLogInButtonText(), loginValidation, "LOG IN BUTTON NOT FOUND");
		espnIFrame.goToSignUp();
		Assert.assertEquals(espnIFrame.getSignUpHeader(), signupTitle, "SIGN UP TITLE IS NO AS EXPECTED");
		espnIFrame.signUp(user);
		Assert.assertTrue(homePage.validateUserLoggedIn(user.getFirstName()),"ERROR USER NAME IS NOT PRESENT");
		homePage.goToLogOut();		
		Assert.assertEquals(homePage.validateUserLoggedOut(), userMenuTitle, "WELCOME MESSAGE IS NO AS EXPECTED");
		//Assert.assertTrue(Collections.disjoint(espnIFrame.alertMessagesRaised(), espnIFrame.getErrorSingUP()));
		
		
		
	}

	@Test(description = "Test Log In", dataProviderClass = org.espnSuite.web.data.DataProviders.class, dataProvider = "Users", enabled = false)
	public void logIn(UserDataESPN user, String userMenuTitle, String loginValidation, String homePageTitle) {
		HomePage homePage = getHomePage();
		homePage.goToUserMenu();
		Assert.assertEquals(homePage.validateUserLoggedOut(), userMenuTitle, "WELCOME MESSAGE IS NO AS EXPECTED");	
		ESPNIFrame espnIFrame = homePage.goToSignInUpIFrame();
		Assert.assertEquals(espnIFrame.getLogInButtonText(), loginValidation, "LOG IN BUTTON NOT FOUND");
//		espnIFrame.logIn();
		Assert.assertEquals(homePage.getHomePageTitle(), homePageTitle, "HOME PAGE TITLE IS NO AS EXPECTED");
	}

	@AfterMethod(description = "After LogIn",alwaysRun = false)
	public void validateHomePage(UserDataESPN user, String homePagetitle, String espnProfileLabel) {
		HomePage homePage = getHomePage();
		Assert.assertEquals(homePage.getHomePageTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
		Assert.assertTrue(homePage.validateUserLoggedIn(user.getFirstName()),"ERROR USER NAME IS NOT PRESENT");
		
	}

}
