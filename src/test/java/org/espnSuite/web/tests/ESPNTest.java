package org.espnSuite.web.tests;

import org.espnSuite.web.data.UserDataESPN;
import org.espnSuite.web.pages.ESPNIFrame;
import org.espnSuite.web.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class ESPNTest extends BaseTest {


	@Test(description = "Create user account", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
			dataProvider = "Users", enabled = false)
	public void createAccount(UserDataESPN user) {
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToSignInUpIFrame();
		log.info("Validar el texto 'Sign Up'");
		espnIFrame.signUp(user);
		Assert.assertTrue(Collections.disjoint(espnIFrame.alertMessagesRaised(), espnIFrame.getErrorSingUP()));
		Assert.assertTrue(homePage.validateUserLoggedIn(user.getFirstName()),"ERROR USER NOT PRESENT");
	}

	@Test(description = "User Log In", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
			dataProvider = "Users")
	public void slogInTest(UserDataESPN user) {
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToSignInUpIFrame();
		// JM -> Assert: Validar el texto del botón "Log In" en el iFrame
		espnIFrame.logIn(user.getEmail(), user.getPassword());
		// JM -> Assert: Validar el título en la página de ESPN - "ESPN"
		Assert.assertTrue(homePage.validateUserLoggedIn(user.getFirstName()), "ERROR USER NAME IS NOT PRESENT");
	}

		@Test(description = "Test Log Out", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
				dataProvider = "Users")
	public void ologOutTest(UserDataESPN user) {
		HomePage homePage = getHomePage();
		homePage.goToLogOut();
		Assert.assertTrue(homePage.validateUserLoggedOut(), "USER COULD NOT LOG OUT");
		//JM-> Assert: Validar el título en la página de ESPN - "ESPN"
		//Assert.assertEquals(homePage.getTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
	}



	@Test(description = "Delete account", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
			dataProvider = "Users", enabled = false)
	public void wdeleteAccount(UserDataESPN user) {
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToUpdateAccountIFrame();
		//JM -> Assert:  Validar el texto "Update Account en el iframe"
		espnIFrame.deleteAccount();
		//JM -> Assert:  Validar el texto "Yes, delete this account" en el iframe"
		espnIFrame.deleteAccountSubmit();
		//JM -> Assert:  Validar el texto "Your account has been deleted." en el titulo del iframe
		//Assert.assertEquals(homePage.getTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
		//JM -> Assert: Validar el título en la página de ESPN - "ESPN"
		Assert.assertTrue(homePage.validateUserLoggedOut(), "USER COULD NOT LOG OUT");

	}

	
}
