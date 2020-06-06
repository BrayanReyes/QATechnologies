package org.espnSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

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

	/**
	 * Variables used for Assertions
	 * 
	 */

	public String assertHomePageTitle = "ESPN: Serving sports fans. Anytime. Anywhere.";
	public String assertLogInOption = "Log In";
	public String assertLogOutOption = "Log Out";
	public String assertEspnProfileOption = "ESPN Profile";


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
	 * @return String text title
	 */
	public String getHomePageTitle() {
		log.info("Obtener el titulo de la Home Page de ESPN");
		waitElementVisibility(homePageTitle);
		return homePageTitle.getText();
	}

	/**
	 * Go to User Menu
	 * 
	 */

	public void goToUserMenu() {
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		log.info("Entrar al menu de Usuario");
	}

	/**
	 * Go to Log In Option
	 * 
	 */

	public void goToLogIn() {
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
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
	 * Go to Log Out Option
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
		log.info("Entrar a la opción ESPN Profile y hacer switch al iframe");
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		clickElement(espnProfileOption);
		return new ESPNIFrame(switchToIFrame(espnIFrame));
	}

	/**
	 * Go to Log In Option and switch to espnIFrame
	 * 
	 * @return ESPNIFrame
	 */
	public ESPNIFrame goToSignInUpIFrame() {
		log.info("Entrar a la opción LOG IN y hacer switch al iframe");
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		clickElement(loginOption);
		return new ESPNIFrame(switchToIFrame(espnIFrame));

	}

	/**
	 * Switch to ESPN Profile iFrame
	 * 
	 * @return ESPNIFrame
	 */
	public ESPNIFrame goToESPNProfileIFrame() {
		log.info("Cambiandose al iFrame de ESPN Profile");
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		waitElementVisibility(espnProfileOption);
		clickElement(espnProfileOption);
		return new ESPNIFrame(switchToIFrame(espnIFrame));
	}

	/**
	 * Validate if an user is logged in
	 * 
	 * @return
	 */

	public boolean validateUserLoggedIn(String userName) {
		String tmpUserName = "";
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
	 * @return
	 */

	public boolean validateUserLoggedOut() {
		String tmpMessage = "";
		// need to find a better solution - it is like a sleep of 30 seconds
		waitElementInvisibility(globalUserIcon); // Log out validation is failing due to user icon is too fast
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
