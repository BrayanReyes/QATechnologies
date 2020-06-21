package org.bookingSuite.web.pages;

import org.apache.commons.logging.Log;
import org.bookingSuite.web.data.UserDataESPN;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the methods to interact with the IFrame
 * @author: july.moreno
 * @version: 07/06/2020
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

	@FindBy(css = "button[ng-click*='vm.close']")
	private WebElement deleteAccountNotificationButton;

	private List<String> signUpErrors;

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
	 * Create a new ESPN Account
	 * 
	 */

	public void signUp(UserDataESPN user) {
		waitElementVisibility(singUpButton);
		clickElement(singUpButton);
		log.info("Log Info: Creating an ESPN Account");
		waitElementVisibility(firstNameInput, lastNameInput, emailAddressInput, newPasswordInput, confirmSingUpButton);
		firstNameInput.sendKeys(user.getFirstName());
		lastNameInput.sendKeys(user.getLastName());
		emailAddressInput.sendKeys((user.getEmail()));
		newPasswordInput.sendKeys(user.getPassword());
		clickElement(confirmSingUpButton);
		switchToDefaultContent();
		log.info("Log Info: ESPN Account created");
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
		waitElementVisibility(usernameInput, passwordInput,logInButton);
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		clickElement(logInButton);
		log.info("Log Info: User Request to Log In");
	}

	/**
	 * Request to deactivate an ESPN Account
	 */

	public void deleteAccount() {
		moveToElement(deleteAccountLink);
		clickElement(deleteAccountLink);
		log.info("Log Info: User request to delete the Account");
	}


	/**
	 * Confirm ESPN deactivate Account
	 */
	public void deleteAccountSubmit() {
		waitElementVisibility(deleteAccountButton);
		clickElement(deleteAccountButton);
		waitElementVisibility(deleteAccountNotificationButton);
		clickElement(deleteAccountNotificationButton);
		waitElementInvisibility(deleteAccountNotificationButton);
		switchToDefaultContent();
		log.info("Log Info: User accept to delete the account");
	}

	/**
	 * Checks if title Account Deactivated appears after log in
	 * @return yes
	 */
	public boolean checkAccountDeactivated(){
		log.info("Log Info: Checking if the account was deactivated");
		waitElementVisibility(espnProfileHeader);
		return (espnProfileHeader.getText().toString().equals("Account Deactivated"));
	}
}
