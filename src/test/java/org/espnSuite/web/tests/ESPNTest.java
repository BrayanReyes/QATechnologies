package org.espnSuite.web.tests;

import org.espnSuite.web.data.UserDataESPN;
import org.espnSuite.web.pages.ESPNIFrame;
import org.espnSuite.web.pages.HomePage;
import org.espnSuite.web.utils.Init;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

public class ESPNTest extends BaseTest {


	@Test(description = "Log In", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
			dataProvider = "activeUsers", groups = "gLogIn")
	public void logInTest(UserDataESPN user) {
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToSignInUpIFrame();
		espnIFrame.logIn(user.getEmail(), user.getPassword());
		Assert.assertEquals(homePage.getHomePageTitle(), homePage.assertHomePageTitle, "HOME PAGE TITLE IS NO AS EXPECTED");
		Assert.assertTrue(homePage.validateUserLoggedIn(user.getFirstName()), "USER NAME IS NOT PRESENT");
	}

	@AfterMethod(groups = "gLogIn")
	public void afterLogin(){
		HomePage homePage = getHomePage();
		homePage.goToLogOut();
		Assert.assertTrue(homePage.validateUserLoggedOut(), "USER COULD NOT LOG OUT");

	}



	@BeforeMethod (groups = "gLogOut")
	public void createAccount() {
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToSignInUpIFrame();
		UserDataESPN user = Init.createDataUser();
		log.info(user);
		espnIFrame.signUp(user);
		log.info("Validar errores en 'Sign Up'");
		Assert.assertTrue(Collections.disjoint(espnIFrame.alertMessagesRaised(), espnIFrame.getErrorSingUP()));
		Assert.assertTrue(homePage.validateUserLoggedIn(user.getFirstName()),"ERROR USER NOT PRESENT");
		Init.saveUserLogOut(user);
	}

	@Test(description = "Log Out", groups = "gLogOut")
	public void logOutTest() {
		HomePage homePage = getHomePage();
		homePage.goToLogOut();
		Assert.assertTrue(homePage.validateUserLoggedOut(), "USER COULD NOT LOG OUT");
		//Assert.assertEquals(homePage.getTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
	}




	@BeforeMethod(groups = "gDeleteAccount")
	public void beforeDeleteAccount(){
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToSignInUpIFrame();
		UserDataESPN user = Init.createDataUser();
		log.info(user);
		espnIFrame.signUp(user);
		log.info("Validar errores en 'Sign Up'");
		Assert.assertTrue(Collections.disjoint(espnIFrame.alertMessagesRaised(), espnIFrame.getErrorSingUP()));
		Assert.assertTrue(homePage.validateUserLoggedIn(user.getFirstName()),"ERROR USER NOT PRESENT");
		Init.saveUserDeleteAccount(user);
	}

	@Test(description = "Delete account", groups = "gDeleteAccount")
	public void deleteAccount() {
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToUpdateAccountIFrame();
		espnIFrame.deleteAccount();
		espnIFrame.deleteAccountSubmit();
		//JM -> Assert:  Validar el texto "Your account has been deleted." en el titulo del iframe
		//Assert.assertEquals(homePage.getTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
		//JM -> Assert: Validar el título en la página de ESPN - "ESPN"
		Assert.assertTrue(homePage.validateUserLoggedOut(), "USER COULD NOT LOG OUT");
	}
}
