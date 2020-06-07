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

/**
 * This class defines the test method under scope: Log In, Log Out and Deactivate Account
 * Sign Up is not a test method itself, but is a precondition to the other tests
 * Groups were used to structure the order in which is desired to run the test
 * @author: july.moreno
 * @version: 06/07/2020
 */


public class ESPNTest extends BaseTest {


	/**
	 * Log In to an active ESPN account using an username and password and validate the Log In
	 * was successful.
	 * The data to Log In is taken from UserDataESPN.dat
	 * Log In Test will be executed for all the account with "Active" status
	 */
	
	@Test(description = "Log In Test", dataProviderClass = org.espnSuite.web.data.DataProviders.class,
			dataProvider = "activeUsers", groups = "LogInGroup")
	public void logInTest(UserDataESPN user) {
		HomePage homePage = getHomePage();
		ESPNIFrame espnIFrame = homePage.goToSignInUpIFrame();
		espnIFrame.logIn(user.getEmail(), user.getPassword());
//		Assert.assertEquals(homePage.getHomePageTitle(), homePage.assertHomePageTitle, "HOME PAGE TITLE IS NO AS EXPECTED");
		Assert.assertTrue(homePage.validateUserLoggedIn(user.getFirstName()), "USER NAME IS NOT PRESENT");
	}

	/**
	 * Due to Log In Test could be executed for more than one account, I need to Log Out
	 * from one account to Log In to the following one 
	 */
	
	@AfterMethod(description = "Log Out after ", groups = "LogInGroup")
	public void logOutAfterLogInTest(){
		HomePage homePage = getHomePage();
		homePage.goToLogOut();
		Assert.assertTrue(homePage.validateUserLoggedOut(), "USER COULD NOT LOG OUT");

	}

	/**
	 * Create a new ESPN Account in order to run Log Out Test
	 * The new user account will be saved in UserDataESPN.dat
	 */
	
	@BeforeMethod (groups = "LogOutGroup")
	public void createAccountBeforeLogOutTest() {
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

	/**
	 * Log Out from an ESPN Account and validate the Log Out was successful
	 */
	
	@Test(description = "Log Out Test", groups = "LogOutGroup")
	public void logOutTest() {
		HomePage homePage = getHomePage();
		homePage.goToLogOut();
		Assert.assertTrue(homePage.validateUserLoggedOut(), "USER COULD NOT LOG OUT");
		//Assert.assertEquals(homePage.getTitle(), homePagetitle, "TITLE IS NO AS EXPECTED");
	}

	/**
	 * Create a new ESPN Account in order to run Log Out Test
	 * The new user account will be saved in UserDataESPN.dat
	 */

	@BeforeMethod(groups = "DeleteAccountGroup")
	public void createAccountBeforeDeleteAccountTest(){
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

	/**
	 * Deactivate an ESPN Account and validate that deactivate process was successful
	 */
	
	@Test(description = "Delete Account Test", groups = "DeleteAccountGroup")
	public void deleteAccountTest() {
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
