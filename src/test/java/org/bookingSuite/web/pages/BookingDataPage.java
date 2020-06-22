package org.bookingSuite.web.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * BookingTest Data Page handles basic information of the person who is booking the stay
 * 
*/

public class BookingDataPage extends BasePage {

	@FindBy(css = ".modal-mask.bp_leaving_users_light_box_mask")
	private WebElement leavingUserPopUp;
	
	@FindBy(css = ".modal-mask-closeBtn")
	private WebElement leavingUserCloseButton;
	
	@FindBy(css = ".bp_legacy_form_box__title")
	private WebElement BookindDataPageTitle;
	
	@FindBy(id = "booker_title")
	private WebElement bookerTitleSelect;	
	
	@FindBy(id = "firstname")
	private WebElement firstNameInput;	

	@FindBy(id = "lastname")
	private WebElement lastNameInput;

	@FindBy(id = "email")
	private WebElement emailInput;
	
	@FindBy(id = "email_confirm")
	private WebElement confirmEmailInput;

	@FindBy(id = "notstayer_false")
	private WebElement stayerRadioButton;

	@FindBy(id = "notstayer_true")
	private WebElement nonStayerRadioButton;
	
	@FindBy(name = "book")
	private WebElement nextLastDataButton;

	
	public BookingDataPage(WebDriver driver) {
		super(driver);
	}

	
	/**
	 * Enter last name
	 * 
	 * @param lastName: String 
	*/
	public void enterLastName(String lastName) {
		lastNameInput.sendKeys(lastName);
	}

	/**
	 * Enter email
	 * 
	 * @param email: String 
	*/
	public void enterEmail(String email) {
		emailInput.sendKeys(email);
	}
	
	/**
	 * Enter email for confirmation
	 * 
	 * @param email: String 
	*/
	public void enterEmailConfirm(String email) {
		confirmEmailInput.sendKeys(email);
	}
	
	/**
	 * Complete booker's info
	 * 
	 * @return CompletarReservaPage 
	*/
	public CompleteBookingDataPage bookHotel() {
		moveToElement(nextLastDataButton);
		nextLastDataButton.click();
		return new CompleteBookingDataPage(getDriver());
	}

}
