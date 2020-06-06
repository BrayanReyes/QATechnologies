package org.espnSuite.web.tests;

import org.espnSuite.web.data.UserDataESPN;
import org.espnSuite.web.pages.ESPNIFrame;
import org.espnSuite.web.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class ESPNTest extends BaseTest {


//	@Test(description = "Create user account",
//			dataProviderClass = org.espnSuite.web.data.DataProviders.class,
//			dataProvider = "Users", enabled = true)
//	public void createAccount(UserDataESPN user) {
//		HomePage homePage = getHomePage();
//		// JM -> Assert: Validar el texto "Log In" en el menu de Usuario
//		ESPNIFrame espnIFrame = homePage.goToSingInUp();
//		// JM -> Assert: Validar el texto "Sign Up" en el botón dentro del iFrame
//		// JM -> Assert: Validar el texto "Create Account" en el titulo del iFrame
//		espnIFrame.singUpESPN(user);
//		// JM -> Assert: Validar el texto "ESPN Profile" en el menu de Usuario
//		// BR: Assert.assertTrue(Collections.disjoint(espnIFrame.alertMessagesRaised(),
//		// espnIFrame.getErrorSingUP()));
//		// BR: Assert.assertTrue(homePage.validateUserLogged(user.getFirstName()),"ERROR
//		// USER NOT PRESENT");
//	}
//
	@Test(description = "Create user account", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
			dataProvider = "Users", enabled = false)
	public void createAccount(UserDataESPN user) {
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToESPNIFrame();
		log.info("Validar el texto 'Sign Up'");
		espnIFrame.singUpESPN(user);
		Assert.assertTrue(Collections.disjoint(espnIFrame.alertMessagesRaised(), espnIFrame.getErrorSingUP()));
		Assert.assertTrue(homePage.validateUserLogged(user.getFirstName()),"ERROR USER NOT PRESENT");
	}

	@Test(description = "Test Log In", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
			dataProvider = "Users", enabled = true)
	public void logInTest(UserDataESPN user) {
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToESPNIFrame();
		// JM -> Assert: Validar el texto del botón "Log In" en el iFrame
		espnIFrame.loginESPN(user.getEmail(), user.getPassword());
		// JM -> Assert: Validar el título en la página de ESPN - "ESPN"
		Assert.assertTrue(homePage.validateUserLogged(user.getFirstName()), "ERROR USER NAME IS NOT PRESENT");
	}

		@Test(description = "Test Log Out", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
				dataProvider = "Users", enabled = true)
	public void zlogOutTest(UserDataESPN user) {
		HomePage homePage = getHomePage();
		homePage.logOut();
		Assert.assertTrue(homePage.validateUserLogOut(), "USER COULD NOT LOG OUT");
		//Assert.assertEquals(homePage.getTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
		//JM-> Assert: Validar el título en la página de ESPN - "ESPN"
	}


//
//	// **July paso por aqui**//
//	@AfterMethod(description = "After LogIn",alwaysRun = false)
//	public void validateHomePage(String homePagetitle) {
//		HomePage homePage = getHomePage();
//		Assert.assertEquals(homePage.getTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
//		// JM-> Assert: Validar el título en la página de ESPN - "ESPN"
//	}
//
//	//description = "Antes de Log Out"
//	@BeforeMethod(alwaysRun = false)
//	public void validateUserMenu(String espnProfileOption) {
//		HomePage homePage = getHomePage();
//		Assert.assertEquals(homePage.getESPNprofileTitle(), espnProfileOption, "TITLE IS NO AS EXPECTED");
//		// JM-> Assert: Validar el texto "ESPN Profile" en el Menu de Usuario
//	}
//
//
//	// **July paso por aqui**//
//	@AfterMethod(description = "Despues de Log Out",alwaysRun = false)
//	public void afterLogout(String loginOption) {
//		HomePage homePage = getHomePage();
//		Assert.assertEquals(homePage.getLoginTitle(), loginOption, "TITLE IS NO AS EXPECTED");
//	}
//
//	// **July paso por aqui**//
//	@BeforeMethod(description = "Antes de Delete",alwaysRun = true)
//	public void beforeDeleteAccount() {
//		log.info("Antes del Delete");
////		-> Assert:  Validar el texto "ESPN Profile" en el Menu de Usuario
////		-> Assert:  Validar el texto "Update Your Account" en el iFrame
//	}
//
//	@Test(description = "Delete account", dataProviderClass = org.espnSuite.web.data.DataProviders.class, dataProvider = "Users", enabled = false)
//	public void wdeleteAccount(String homePagetitle) {
//		HomePage homePage = getHomePage();
//		ESPNIFrame espnIFrame = homePage.goToDeleteAccount();
//		//JM -> Assert:  Validar el texto "Yes, delete this account" en el iframe "Are you sure?"
//		espnIFrame.deleteAccountSubmit();
//		//JM -> Assert:  Validar el texto "Your account has been deleted." en el titulo del iframe
//		//JM -> Assert: Validar el título en la página de ESPN - "ESPN"
//		//Assert.assertEquals(homePage.getTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
//	}
//
//	@AfterMethod(alwaysRun = true)
//	public void afterMethod() {
//		log.info("Launch HomePage");
////		getHomePage().switchToDefaultContent();
//		//JM -> Assert:  Validar el texto "Log In" en el Menu de Usuario
//	}
	
}
