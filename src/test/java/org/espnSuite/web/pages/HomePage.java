package org.espnSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

/**
 * This class defines the methods to interact with the Home Page
 * @author: july.moreno
 * @version: 06/07/2020
 */


public class HomePage extends BasePage {

	@FindBy(css = "#global-header h1 a")
	private WebElement homePageTitle;

	@FindBy(css = "a#global-user-trigger")
	private WebElement globalUserIcon;

	@FindBy(css = "ul.account-management li:nth-child(5) a")
	private WebElement loginOption;

	@FindBy(css = "ul.account-management li:nth-child(9) a")
	private WebElement logoutOption;

	@FindBy(css = "ul.account-management li:nth-child(5) a")
	private WebElement espnProfileOption;

	@FindBy(css = "#global-header .display-user")
	private WebElement displayWelcomeMessage;

	@FindBy(css = "#global-header li.display-user span")
	private WebElement displayLoggedUser;

	@FindBy(css = "#disneyid-iframe")
	private WebElement espnIFrame;

//	/**
//	 * Variables used for Assertions
//	 * 
//	 */
//
//	public String assertHomePageTitle = "ESPN";
//	public String assertLogInOption = "Log In";
//	public String assertLogOutOption = "Log Out";
//	public String assertEspnProfileOption = "ESPN Profile";


	/**
	 * Constructor, a factory for producing {@link ElementLocator}s.
	 * 
	 * @param driver Web driver of the page
	 */

	public HomePage(WebDriver driver, String url) {
		super(driver);
		getDriver().get(url);
	}

	/**
	 * Get ESPN Home Page title
	 * 
	 * @return String "ESPN"
	 */
	public String getHomePageTitle() {
		log.info("Obtener el titulo de la Home Page de ESPN para saber que voy a probar la pagina correcta");
		waitElementVisibility(homePageTitle);
		return homePageTitle.getText();
	}

	/**
	 * Go to User Menu
	 * 
	 */

	public void goToUserMenu() {
		log.info("Click Usuario");
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		log.info("Entrar al menu de Usuario");
	}

	/**
	 * Go to Log In Option
	 * 
	 */

	public void goToLogIn() {
		waitElementVisibility(loginOption);
		clickElement(loginOption);
		log.info("Entrar a la opción LOG IN");
	}

	/**
	 * Go to Log Out Option
	 * 
	 */

	public void goToLogOut() {
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		waitElementVisibility(logoutOption);
		clickElement(logoutOption);
		log.info("Entrar a la opción LOG OUT");
	}

	/**
	 * Go to ESPN Profile Option
	 * 
	 */

	public void goToEspnProfile() {
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		waitElementVisibility(espnProfileOption);
		clickElement(espnProfileOption);
		log.info("Entrar a la opción ESPN PROFILE");
	}

	/**
	 * Go to ESPN Profile Option and switch to espnIFrame
	 * 
	 */

	public ESPNIFrame goToUpdateAccountIFrame() {
		log.info("Entrar a la opción ESPN PROFILE y hacer switch al iframe");
		//waitElementInvisibility(globalUserIcon);
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		waitElementVisibility(espnProfileOption);
		clickElement(espnProfileOption);
		return new ESPNIFrame(switchToIFrame(espnIFrame));
	}

	/**
	 * Go to Log In Option and switch to espnIFrame
	 * 
	 * @return ESPNIFrame
	 */
	public ESPNIFrame goToSignInUpIFrame() {
		log.info("entra a la opcion LOG IN y hace switch al iframe");
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		waitElementVisibility(loginOption);
		clickElement(loginOption);
		return new ESPNIFrame(switchToIFrame(espnIFrame));

	}

//	/**
//	 * Switch to ESPN Profile iFrame
//	 * 
//	 * @return ESPNIFrame
//	 */
//	public ESPNIFrame goToESPNProfileIFrame() {
//		log.info("Cambiandose al iFrame de ESPN Profile");
//		waitElementVisibility(globalUserIcon);
//		clickElement(globalUserIcon);
//		waitElementVisibility(espnProfileOption);
//		clickElement(espnProfileOption);
//		return new ESPNIFrame(switchToIFrame(espnIFrame));
//	}

	/**
	 * Validate if an user is logged in
	 * 
	 * @return true if user is logged in
	 */

	public boolean validateUserLoggedIn(String userName) {
		String tmpUserName = "";
		waitElementInvisibility(globalUserIcon);
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		if (waitElementVisibility(displayLoggedUser)) {
			tmpUserName = displayLoggedUser.getText();
			log.info("Welcome " + tmpUserName);
			waitElementVisibility(globalUserIcon);
			clickElement(globalUserIcon);
			waitElementInvisibility(displayLoggedUser);
			log.info("Obtener el Usuario Logeadoe despues de loguearse");
		}
		return (tmpUserName.equals(userName + "!"));
	}

	/**
	 * Validate if an user is logged out
	 * 
	 * @return true if user is logged out
	 */

	public boolean validateUserLoggedOut() {
		String tmpMessage = "";
		waitElementInvisibility(globalUserIcon);
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		if (waitElementVisibility(displayWelcomeMessage)) {
			tmpMessage = displayWelcomeMessage.getText();
			log.info(tmpMessage);
		}
		log.info("Obtener el Welcome Message despues de desloguearse");
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		return (tmpMessage.equals("Welcome!"));
	}

}
