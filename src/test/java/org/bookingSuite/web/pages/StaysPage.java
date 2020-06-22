package org.bookingSuite.web.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;



/**
 * Dormir Page handles the stay information and allows to search stay option according with the parameter:
 * destination, dates of check-in and checkout, number of guests and number of rooms
 * 
*/

public class StaysPage extends BasePage{

	@FindBy(id = "ss")
	private WebElement destinationInputText;
	
	@FindBy(className = "xp__dates")
	private WebElement datesDiv;

	@FindBy(id = "xp__guests__toggle")
	private WebElement guestDiv;

	//Elements in the calendar
	@FindBy(css = ".bui-calendar__wrapper .bui-calendar__date")
	private List<WebElement> dates;

	@FindBy(className = "bui-calendar__control--next")
	private WebElement calendarControlNext;

	// ---------------------------------------
	@FindBy(className = "sb-group__field-adults")
	private WebElement adultsComponent;

	@FindBy(css = ".sb-group__field-adults .bui-stepper__display")
	private WebElement adults_quantity;
	  
	@FindBy(css = ".sb-group__field-adults .bui-stepper__add-button")
	private WebElement btn_add_adults;
	
	
//	@FindBy(className = ".sb-group__field-adults .bui-stepper__add-button")
//	private WebElement increaseAdultsButton;
	
	@FindBy(className = "sb-group-children")
	private WebElement childrenComponent;	
	
	 @FindBy(css = ".sb-group-children .bui-stepper__display")
	 private WebElement children_quantity;
	 
	 @FindBy(css = ".sb-group-children .bui-stepper__add-button")
	 private WebElement btn_add_children;
	 
	 @FindBy(css = ".sb-group-children .bui-stepper__subtract-button")
     private WebElement btn_subtract_children;

//	@FindBy(className = "")
//	private WebElement increaseChildrenButton;
	
	@FindBy(className = "sb-group__field-rooms")
	private WebElement roomsComponent;
	
	@FindBy(name = "age")
	private List<WebElement> ageSelector;	
	
	@FindBy(css = "#frm button[type=submit]")
	private WebElement buscarButton;

	
	public StaysPage(WebDriver pDriver) {
		super(pDriver);
	}
	
	/**
     * Enter destination
     * 
     * @param destination: String
     */
	public void enterDestination(String destination) {
		waitElementVisibility(destinationInputText);
		destinationInputText.sendKeys(destination);
	}

	
	/**
     * Perform the search of lodgings
     * 
     * @return Accommodation Results Page
     */
	public HotelsResultPage searchAccomodation() {
		buscarButton.click();
		return new HotelsResultPage(getDriver()); 
	}
	
}
