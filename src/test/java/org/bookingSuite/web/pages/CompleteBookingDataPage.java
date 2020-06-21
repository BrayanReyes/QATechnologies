package org.bookingSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompleteBookingDataPage extends BasePage {

	@FindBy(id = "cc1")
	private WebElement countrySelect;
	
	@FindBy(id = "address1")
	private WebElement addressInput;

	@FindBy(id = "city")
	private WebElement cityInput;
	
	@FindBy(id = "zip")
	private WebElement zipCodeInput;
	
	@FindBy(id = "phone")
	private WebElement phoneNumberInput;	
	
	@FindBy(css = "#bs3_cc_form .bp_legacy_form_box__title")
	private WebElement paymentDetailsTitle;
	
	@FindBy(id = "cc_name")
	private WebElement cardHolderNameInput;
	
	@FindBy(id = "cc_type")
	private WebElement cardTypeSelect;

	@FindBy(id = "cc_number")
	private WebElement cardNumberInput;
	
	@FindBy(id = "cc_month")
	private WebElement expirationMonthCardSelect;
	
	@FindBy(id = "ccYear")
	private WebElement expirationYearCardSelect;
	
	@FindBy(id = "cc_cvc")
	private WebElement cvcInput;
	
	
	//locators for assertion
	
	@FindBy(css = "#label_email .personal_details_reassurance_email_text")
	private WebElement email;
	
	@FindBy(id = "book_credit_card")
	private WebElement creditCardFields;
	
	@FindBy(css = "button[name*=\"review\"]")
	private WebElement validateDatabutton;
	
	@FindBy(css = "button[class*=\"book\"]")
	private WebElement completeBookButton;
	
	@FindBy(id = "#bookconditions")
	private WebElement bookConditionsLink;	
		
	@FindBy(css = "#bookOverviewTop .hotel-address-text-address")
	private WebElement hotelAddress;	
	
	
	
	
	public CompleteBookingDataPage(WebDriver driver) {
		super(driver);
	}
	
	

}
