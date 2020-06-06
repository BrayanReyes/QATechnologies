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
		Assert.assertEquals(homePage.getUserMenuTitle(), userMenuTitle, "USER MENU TITLE IS NO AS EXPECTED");
		ESPNIFrame espnIFrame = homePage.goToESPNIFrame();
		Assert.assertEquals(espnIFrame.getLoginbutonText(), loginValidation, "LOG IN BUTTON NOT FOUND");
		espnIFrame.singUpESPN();
		Assert.assertEquals(espnIFrame.getSignUpTitle(), signupTitle, "SIGN UP TITLE IS NO AS EXPECTED");
		espnIFrame.singUpESPN(user);
		Assert.assertEquals(homePage.getESPNprofileLabel(), logoutValidation, "ESPN PROFILE OPTION NOT FOUND");
		homePage.logOut();		

		//Assert.assertTrue(Collections.disjoint(espnIFrame.alertMessagesRaised(), espnIFrame.getErrorSingUP()));
		
		//Assert.assertTrue(homePage.validateUserLogged(user.getFirstName()),"ERROR USER NOT PRESENT");
		
	}

	@Test(description = "Test Log In", dataProviderClass = org.espnSuite.web.data.DataProviders.class, dataProvider = "Users", enabled = false)
	public void logIn(String username, String password,String userMenuTitle, String loginValidation, String homePageTitle) {
		HomePage homePage = getHomePage();
		homePage.goToUserMenu();
		Assert.assertEquals(homePage.getUserMenuTitle(), userMenuTitle, "USER MENU TITLE IS NO AS EXPECTED");	
		ESPNIFrame espnIFrame = homePage.goToESPNIFrame();
		Assert.assertEquals(espnIFrame.getLoginbutonText(), loginValidation, "LOG IN BUTTON NOT FOUND");
		espnIFrame.loginESPN(username, password);
		Assert.assertEquals(homePage.getHomePageTitle(), homePageTitle, "HOME PAGE TITLE IS NO AS EXPECTED");
	}

	@AfterMethod(description = "After LogIn",alwaysRun = false)
	public void validateHomePage(String homePagetitle, String espnProfileLabel) {
		HomePage homePage = getHomePage();
		Assert.assertEquals(homePage.getHomePageTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
		// JM-> Assert: Validar el título en la página de ESPN - "ESPN"
		Assert.assertEquals(homePage.getESPNprofileLabel(),espnProfileLabel, "ESPN PROFILE OPTION NOT FOUND");
//		Assert.assertTrue(homePage.validateUserLogged(user.getFirstName()), "ERROR USER NAME IS NOT PRESENT");

		
	}

}
