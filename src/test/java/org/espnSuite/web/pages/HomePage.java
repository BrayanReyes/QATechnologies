package org.espnSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class HomePage extends BasePage {

	// **July estuvo aqui**//
	@FindBy(css = "#global-header h1 a") // head.at-element-marker:nth-child(1) title
	private WebElement homePageTitle;

	@FindBy(css = "a#global-user-trigger")
	private WebElement iconUser;

	@FindBy(css = "ul.account-management li:nth-child(5) a")
	private WebElement loginOption; // loginOption //singInButton

	@FindBy(css = "ul.account-management li:nth-child(9) a")
	private WebElement logoutOption; // logoutOption //logOutButton

	@FindBy(css = "ul.account-management li:nth-child(5) a")
	private WebElement espnProfileOption; // espnProfileOption //espnProfileButton

	@FindBy(css = "#global-header .display-user")
	private WebElement welcomeMessage;

	@FindBy(css = "#global-header li.display-user span")
	private WebElement welcomeName;

	@FindBy(css = "#disneyid-iframe")
	private WebElement espnIFrame; // singInUpIFrame

	/**
	 * Constructor, a factory for producing {@link ElementLocator}s.
	 * 
	 * @param driver Web driver of the page
	 */
	public HomePage(WebDriver driver, String url) {
		super(driver);
		getDriver().get(url);
	}

	// **July estuvo aqui**//

	/**
	 * Get ESPN Page title
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
		waitElementVisibility(iconUser);
		clickElement(iconUser);
		log.info("Entrar al menu de Usuario");
	}

	/**
	 * Get Menu User Welcome label
	 * 
	 * @return String text welcome label
	 */
	public String getUserMenuTitle() {
		log.info("Obtener el titulo del Menu de Usuario antes de loguearse");
		waitElementVisibility(welcomeMessage);
		return welcomeMessage.getText();
	}

	/**
	 * Go to Log In Option
	 * 
	 */

	public void logIn() {
		waitElementVisibility(iconUser);
		clickElement(iconUser);
		clickElement(loginOption);
		log.info("Entrar a la opción LOG IN");
	}

	/**
	 * Go to Log Out Option
	 * 
	 */

	public void logOut() {
		waitElementVisibility(iconUser);
		clickElement(iconUser);
		clickElement(logoutOption);
		log.info("Entrar a la opción LOG OUT");
	}

	/**
	 * Get Menu User Welcome Name label
	 * 
	 * @return String text welcome label
	 */
	public String getLogedinUserMenuTitle() {
		log.info("Obtener el titulo del Menu de Usuario despues de loguearse");
		waitElementVisibility(welcomeName);
		return welcomeName.getText();
	}

	/**
	 * Go to ESPN Profile Option
	 * 
	 */

	public void espnProfile() {
		waitElementVisibility(iconUser);
		clickElement(iconUser);
		clickElement(espnProfileOption);
		log.info("Entrar a la opción ESPN PROFILE");
	}

	/**
	 * Get ESPN Profile Option title
	 * 
	 * @return String text title
	 */
	public String getESPNprofileLabel() {
		log.info("Obtener el texto de la opción ESPN Profile");
		waitElementVisibility(espnProfileOption);
		return espnProfileOption.getText();
	}

	/**
	 * Switch to Log In / Sign Up iFrame
	 * 
	 * @return ESPNIFrame
	 */
	public ESPNIFrame goToLoginSingupIFrame() {
		log.info("Cambiandose al iFrame de Log In / Sign Up");
		waitElementVisibility(iconUser);
		clickElement(iconUser);
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
		waitElementVisibility(iconUser);
		clickElement(iconUser);
		waitElementVisibility(espnProfileOption);
		clickElement(espnProfileOption);
		return new ESPNIFrame(switchToIFrame(espnIFrame));
	}

	// Validaciones de Pinguin que no se bien como utilizar
	public boolean validateUserLogged(String userName) {
		waitElementVisibility(iconUser);
		clickElement(iconUser);
		waitElementVisibility(welcomeName);
		log.info("Validar 1");
		log.info(welcomeName.getText());
		return (welcomeName.getText().equals(userName + "!"));
	}

	// Validaciones de Pinguin que no se bien como utilizar
	public boolean validateUserLogOut() { // Log out validation is failing due to user icon is too fast
		waitElementVisibility(iconUser);
		clickElement(iconUser);
		waitElementVisibility(logoutOption);
		log.info(welcomeMessage.getText());
		return (welcomeMessage.getText().equals("Welcome!"));
	}

}
