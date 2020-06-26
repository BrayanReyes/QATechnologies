package org.bookingSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Booker Information Page handles basic information of the person who is booking the stay
 * 
*/

public class BookerInformationPage extends BasePage {

	@FindBy(className = "bp_legacy_form_box__content")
	private WebElement userInfoBox;

	@FindBy(className = "bp_legacy_form_box__title")
	private WebElement userInfoBoxTitle;

	@FindBy(id = "booker_title")
	private WebElement bookerTitleSelect;	
	
	@FindBy(id = "firstname")
	private WebElement firstNameInput;	

	@FindBy(id = "lastname")
	private WebElement lastNameInput;

	@FindBy(id = "email")
	private WebElement emailInput;
	
	@FindBy(id = "email_confirm")
	private WebElement confirmationEmailInput;

	@FindBy(id = "notstayer_false")
	private WebElement stayerRadioButton;

	@FindBy(id = "notstayer_true")
	private WebElement nonStayerRadioButton;
	
	@FindBy(name = "book")
	private WebElement lastDataButton;

	/**
	 * Constructor.
	 *
	 * @param driver: WebDriver
	 */

	BookerInformationPage(WebDriver driver) {
		super(driver);
		handleUsersModal();
	}

	/**
	 * Validate if User Information Box is present
	 *
	 * @return true
	 */
	public boolean userInfoBoxIsPresent() {
		return userInfoBox.isDisplayed();
	}


	/**
	 * Get title from User Information Box
	 *
	 * @return Title: String
	 */
	public String getUserInfoBoxTitle() {
		return userInfoBoxTitle.getText();
	}

	/**
	 * Enter {lastName} to the {lastNameInput} text field
	 * 
	 * @param lastName: String 
	*/
	private void enterLastName(String lastName) {
		lastNameInput.sendKeys(lastName);
	}

	/**
	 * Enter {email} to the {emailInput} text field
	 *
	 * @param email: String 
	*/
	private void enterEmail(String email) {
		emailInput.sendKeys(email);
	}
	
	/**
	 * Enter {confirmationEmail} to the {confirmationEmailInput} text field
	 * 
	 * @param confirmationEmail: String
	*/
	private void enterEmailConfirm(String confirmationEmail) {
		confirmationEmailInput.sendKeys(confirmationEmail);
	}

	/**
	 * Enter Booker Personal Information
	 *
	 * @param lastName: String
	 * @param email: String
	 *
	 */

	public void fillBookerPersonalInformation(String lastName, String email, String confirmationEmail){
		log.info("User fills his/her personal information");
		enterLastName(lastName);
		enterEmail(email);
		enterEmailConfirm(confirmationEmail);
	}

	/**
	 * Click to {nextLastDataButton} to continue the booking process
	 * 
	 * @return PaymentInformationPage
	*/
	public PaymentInformationPage goToPaymentInformationPage() {
		moveToElement(lastDataButton);
		lastDataButton.click();
		return new PaymentInformationPage(getDriver());
	}

}
