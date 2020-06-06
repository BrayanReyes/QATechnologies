package org.espnSuite.web.pages;

import org.espnSuite.web.data.UserDataESPN;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class ESPNIFrame extends BasePage {

	
	@FindBy(css = "#did-ui-view h2")
	private WebElement createAccountTitle;
	
	@FindBy(css = "a.btn.btn-secondary.ng-isolate-scope")
	private WebElement singUpButton;

	@FindBy(css = "input[ng-model='vm.model.firstName']")
	private WebElement firstNameInput;

	@FindBy(css = "input[ng-model='vm.model.lastName']")
	private WebElement lastNameInput;

	@FindBy(css = "input[ng-model='model.profile.email']")
	private WebElement emailInput;

	@FindBy(css = "input[ng-model='vm.newPassword']")
	private WebElement newPasswordInput;

	@FindBy(css = "button.btn.btn-primary.ng-scope.ng-isolate-scope")
	private WebElement confirmSingUpButton;

	@FindBy(css = "input[ng-model='vm.username']")
	private WebElement loginUserNameInput;

	@FindBy(css = "input[ng-model='vm.password']")
	private WebElement loginPasswordInput;

	@FindBy(css = "button[ng-click*='vm.submitLogin']")
	private WebElement loginButton;

	@FindBy(css = "#did-ui-view h2")
	private WebElement updateAccountTitle;

	@FindBy(css = "#cancel-account")
	private WebElement deleteAccountLink;

	// @FindBy(css = "section.workflow.workflow-deactivate button.btn.btn-primary
	// ng-isolate-scope")
	@FindBy(css = "button.[did-translate='deactivate.confirmation.buttons.confirm']")
	private WebElement confirmDeleteAccountButton;

	// JM: Preguntar a Brayan si esto lo podemos hacer
	@FindBy(css = "#")
	private WebElement confirmDeleteAccountMessage;

	private List<String> errorSingUP;

	/**
	 * Constructor, a factory for producing {@link ElementLocators}.
	 * 
	 * @param driver Web driver of the page
	 */

	/**
	 * Get "Sign Up" button text from ESPN iFrame
	 * 
	 * @return String button text
	 */
	public String getSignUpButtonText() {
		log.info("Capturando el texto del boton SIGN UP para saber que estoy en el iFrame correcto");
		waitElementVisibility(singUpButton);
		return singUpButton.getText();
	}

	/**
	 * Sign Up to ESPN
	 * 
	 */

	public void singUpESPN() {
		log.info("Ingresando a la opción SIGN UP");
		waitElementVisibility(singUpButton);
		clickElement(singUpButton);
	}

	/**
	 * Get "Sign Up" button text from ESPN iFrame
	 * 
	 * @return String button text
	 */
	public String getSignUpTitle() {
		log.info("Capturando el titulo del IFrma SIGN UP para saber que estoy en el iFrame correcto");
		waitElementVisibility(createAccountTitle);
		return createAccountTitle.getText();
	}

	
	/**
	 * Create an ESPN
	 * 
	 */

	public void singUpESPN(UserDataESPN user) {
		log.info("Creando una nueva cuenta de ESPN");
		waitElementVisibility(firstNameInput, lastNameInput, emailInput, newPasswordInput, confirmSingUpButton);
		firstNameInput.sendKeys(user.getFirstName());
		lastNameInput.sendKeys(user.getLastName());
		emailInput.sendKeys((user.getEmail()));
		newPasswordInput.sendKeys(user.getPassword());
		clickElement(confirmSingUpButton);
		sleep(5);
	}

	/**
	 * Get "Log In" button text from ESPN iFrame
	 * 
	 * @return String button text
	 */
	public String getLoginbutonText() {
		log.info("Capturando el texto del boton LOG IN para saber que estoy en el iFrame correcto");
		waitElementVisibility(loginButton);
		return loginButton.getText();
	}

	/**
	 * Log In to ESPN using an username and password
	 * 
	 */

	public void loginESPN(String username, String password) {
		waitElementVisibility(loginUserNameInput, loginPasswordInput);
		loginUserNameInput.sendKeys(username);
		loginPasswordInput.sendKeys(password);
		clickElement(loginButton);
		log.info("Log In a ESPN");
	}

	/**
	 * Get ESPN Profile iFrame Title
	 * 
	 * @return String ESPN Profile iFrame Title text
	 */
	public String getESPNIFrameTitle() {
		log.info("Capturando el texto del iFrame para saber que entre al iFrame de desactivar la cuenta");
		waitElementVisibility(updateAccountTitle);
		return updateAccountTitle.getText();
	}

	/**
	 * Request to deactivate en ESPN Account
	 */

	public void deleteAccount() {
		moveToElement(deleteAccountLink);
		waitElementVisibility(deleteAccountLink);
		clickElement(deleteAccountLink);
		log.info("Solicito desactivar la cuenta");
	}

	/**
	 * Get ESPN Delete Account iFrame Title
	 * 
	 * @return String ESPN Profile iFrame Title text
	 */
	public String getDeleteAccountTitle() {
		log.info("Capturando el texto del iFrame para saber que voy a confirmar el delete de la cuenta");
		waitElementVisibility(confirmDeleteAccountButton);
		return confirmDeleteAccountButton.getText();
	}

	/**
	 * Confirm ESPN deactivate Account
	 */

	public void deleteAccountSubmit() {
		waitElementVisibility(confirmDeleteAccountButton);
		// clickElement(confirmDeleteAccountButton); //JM: Preguntar a Brayan porque
		// esto esta comentariado
		log.info("Aceptar Desactivar la Cuenta");
	}

	/**
	 * Login error management
	 */

	public ESPNIFrame(WebDriver driver) {
		super(driver);
		errorSingUP = new ArrayList<>();
		errorSingUP.add("Please enter a password.");
		errorSingUP.add("It looks like that email has already been used to create an account at Disney, ESPN, Marvel, "
				+ "or ABC. If this is your email address, just log in to your account.");
	}

	public List<String> getErrorSingUP() {
		return errorSingUP;
	}

	//JM Validar con Brayan para que sirve esto
	public List<String> alertMessagesRaised() { // Check why it is not able to find the list of web elements alerts
		List<String> alertMessagesRaised = new ArrayList<>();
		getDriver().findElements(By.cssSelector(".message-error.message.ng-isolate-scope.state-active"))// "div[ng-repeat='item
																										// in
																										// parsedItems']"))
				.forEach(e -> {
					alertMessagesRaised.add(e.getText());
					log.info(e.getText());
				});
		return alertMessagesRaised;
	}

}
