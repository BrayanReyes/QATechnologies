package org.espnSuite.web.pages;

import org.espnSuite.web.data.UserDataESPN;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the methods to interact with the IFrame
 * @author: july.moreno
 * @version: 06/07/2020
 */

public class ESPNIFrame extends BasePage {

	@FindBy(css = "#did-ui-view h2")
	private WebElement signUpHeader;

	@FindBy(css = "a.btn.btn-secondary.ng-isolate-scope")
	private WebElement singUpButton;

	@FindBy(css = "input[ng-model='vm.model.firstName']")
	private WebElement firstNameInput;

	@FindBy(css = "input[ng-model='vm.model.lastName']")
	private WebElement lastNameInput;

	@FindBy(css = "input[ng-model='model.profile.email']")
	private WebElement emailAddressInput;

	@FindBy(css = "input[ng-model='vm.newPassword']")
	private WebElement newPasswordInput;

	@FindBy(css = "button.btn.btn-primary.ng-scope.ng-isolate-scope")
	private WebElement confirmSingUpButton;

	@FindBy(css = "input[ng-model='vm.username']")
	private WebElement usernameInput;

	@FindBy(css = "input[ng-model='vm.password']")
	private WebElement passwordInput;

	@FindBy(css = "button[ng-click*='vm.submitLogin']")
	private WebElement logInButton;

	@FindBy(css = "#did-ui-view h2")
	private WebElement espnProfileHeader;

	@FindBy(css = "#cancel-account")
	private WebElement deleteAccountLink;

	@FindBy(css = "section.workflow.workflow-deactivate button.btn.btn-primary.ng-isolate-scope")
	private WebElement deleteAccountButton;

	@FindBy(css = ".message-error.message.ng-isolate-scope.state-active")
	private List<WebElement> alertMessages;

	private List<String> signUpErrors;
//	public String assertLogInButton = "Log In";
//	public String assertEspnProfileHeader = "Update Your Account";
//	public String assertDeleteAccountHeader = "Are you sure?";
//	public String assertDeleteAccountButton = "Yes, delete this account";
//	public String assertAccountDeletedHeader = "Your account has been deleted";

	/**
	 * Constructor, a factory for producing ElementLocators.
	 * 
	 * @param driver Web driver of the page
	 */

	public ESPNIFrame(WebDriver driver) {
		super(driver);
		signUpErrors = new ArrayList<>();
		signUpErrors.add("Please enter a password.");
		signUpErrors.add("It looks like that email has already been used to create an account at Disney, ESPN, Marvel, "
				+ "or ABC. If this is your email address, just log in to your account.");
	}

	/**
	 * Get "Log In" button text from ESPN iFrame
	 * 
	 * @return String button text
	 */
	public String getLogInButtonText() {
		log.info("Capturando el texto del boton LOG IN para saber que estoy en el iFrame correcto");
		waitElementVisibility(logInButton);
		return logInButton.getText();
	}

	/**
	 * Go to Sign Up option
	 * 
	 */

	public void goToSignUp() {
		log.info("Ingresando a la opción SIGN UP");
		waitElementVisibility(singUpButton);
		clickElement(singUpButton);
	}

	/**
	 * Get Sign Up header
	 * 
	 * @return String "Create Account"
	 */
	public String getSignUpHeader() {
		log.info("Capturando el titulo del IFrame SIGN UP para saber que estoy en el iFrame correcto");
		waitElementVisibility(signUpHeader);
		return signUpHeader.getText();
	}

	/**
	 * Create a new ESPN Account
	 * 
	 */

	public void signUp(UserDataESPN user) {
		waitElementVisibility(singUpButton);
		clickElement(singUpButton);
		log.info("Creando una nueva cuenta de ESPN");
		waitElementVisibility(firstNameInput, lastNameInput, emailAddressInput, newPasswordInput, confirmSingUpButton);
		firstNameInput.sendKeys(user.getFirstName());
		lastNameInput.sendKeys(user.getLastName());
		emailAddressInput.sendKeys((user.getEmail()));
		newPasswordInput.sendKeys(user.getPassword());
		clickElement(confirmSingUpButton);
		switchToDefaultContent();
		log.info("Se creó una nueva cuenta en ESPN");
	}

	/**
	 * Sign Up error List
	 * 
	 */

	public List<String> getErrorSingUP() {
		return signUpErrors;
	}

	/**
	 * Sign Up error management. Shows Sign Up errors
	 * 
	 * @return List of errors raised when the user try to Sign Up
	 * 
	 */

	public List<String> alertMessagesRaised() {
		List<String> alertMessagesRaised = new ArrayList<>();
		if (waitElementVisibility(alertMessages)) {
			alertMessages.forEach(e -> {
				alertMessagesRaised.add(e.getText());
				log.info(e.getText());
			});
		}
		return alertMessagesRaised;
	}

	/**
	 * Log In to ESPN
	 * @param username 
	 * @param password
	 * 
	 */

	public void logIn(String username, String password) {
		waitElementVisibility(usernameInput, passwordInput);
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		clickElement(logInButton);
		switchToDefaultContent();
		log.info("Loguedo en ESPN");
	}

	/**
	 * Get IFrame Header for ESPN Profile Option 
	 * 
	 * @return String "Update Account"
	 */
	public String getEspnProfileHeader() {
		log.info("Capturando el texto del iFrame para saber que entre UPDATE ACCOUNT para desactivar la cuenta");
		waitElementVisibility(espnProfileHeader);
		return espnProfileHeader.getText();
	}

	/**
	 * Request to deactivate an ESPN Account
	 */

	public void deleteAccount() {
		moveToElement(deleteAccountLink);
		waitElementVisibility(deleteAccountLink);
		clickElement(deleteAccountLink);
		log.info("Solicito desactivar la cuenta");
	}

	/**
	 * Get Delete Account Button Text
	 * 
	 * @return String "Yes, delete this account"
	 */
	public String getDeleteAccountButtonText() {
		log.info("Capturando el texto boton de Delete para confirmar que voy a borrar la cuenta");
		waitElementVisibility(deleteAccountButton);
		return deleteAccountButton.getText();
	}

	/**
	 * Confirm ESPN deactivate Account
	 */

	public void deleteAccountSubmit() {
		waitElementVisibility(deleteAccountButton);
		clickElement(deleteAccountButton);
		switchToDefaultContent();
		log.info("Aceptar Desactivar la Cuenta");
	}

}
