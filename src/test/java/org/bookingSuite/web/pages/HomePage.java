package org.bookingSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

/**
 * This class defines the methods to interact with the Home Page
 * @author: july.moreno
 * @version: 07/06/2020
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
	 * Go to Log Out Option
	 * 
	 */

	public void goToLogOut() {
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		waitElementVisibility(logoutOption);
		clickElement(logoutOption);
		log.info("Log Info: Log Out Option clicked");
	}

	/**
	 * Go to ESPN Profile Option and switch to espnIFrame
	 * 
	 */

	public ESPNIFrame goToUpdateAccountIFrame() {
		log.info("Log Info:  ESPN Profile Option clicked - Switch to the iframe");
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
		log.info("Log Info:  Log In Option clicked - Switch to the iframe");
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		if (waitElementVisibility(loginOption))
			clickElement(loginOption);
		return new ESPNIFrame(switchToIFrame(espnIFrame));

	}

	/**
	 * Validate if an user is logged in
	 * 
	 * @return true
	 */

	public boolean validateUserLoggedIn(String userName) {
		String tmpUserName = "";
		waitElementInvisibility(globalUserIcon);
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		if (waitElementVisibility(displayLoggedUser)) {
			tmpUserName = displayLoggedUser.getText();
			waitElementVisibility(globalUserIcon);
			clickElement(globalUserIcon);
			waitElementInvisibility(displayLoggedUser);
			log.info("Log info: Validating the label Welcome " + tmpUserName);
		}else
			log.info("Log Info: Not able to check log In.");
		return (tmpUserName.equals(userName + "!"));
	}

	/**
	 * Validate if an user is logged out
	 * 
	 * @return true
	 */

	public boolean validateUserLoggedOut() {
		String tmpMessage = "";
		waitElementInvisibility(globalUserIcon);
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		if (waitElementVisibility(displayWelcomeMessage)) {
			tmpMessage = displayWelcomeMessage.getText();
			log.info("Log Info: Validating de label " + tmpMessage);
		}
		waitElementVisibility(globalUserIcon);
		clickElement(globalUserIcon);
		return (tmpMessage.equals("Welcome!"));
	}

}
