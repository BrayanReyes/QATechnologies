package org.bookingSuite.web.pages;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.*;
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

	@FindBy(css ="[data-i=\"0\"]")
	private WebElement autoCompleteDestinationFirstOption; //Should we use the datalabel instead
	
	@FindBy(className = "sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb")
	private WebElement datesDiv;

	@FindBy(id = "xp__guests__toggle")
	private WebElement guestDiv;

	//Elements in the calendar
	@FindBy(css = ".bui-calendar__wrapper .bui-calendar__date")
	private List<WebElement> dates;

	@FindBy(className = "bui-calendar__control--next")
	private WebElement calendarControlNext;

	@FindBy(className = "bui-calendar__display")
	private WebElement calendarLabel;

	// ---------------------------------------
	@FindBy(className = "sb-group__field-adults")
	private WebElement adultsComponent;

	@FindBy(css = ".sb-group_field-adults .bui-stepper_display")
	private WebElement adults_quantity;

	@FindBy(css = ".sb-group__field-adults .bui-stepper__add-button")
	private WebElement increaseAdultsButton;

	@FindBy(css = ".sb-group__field-adults .bui-stepper__subtract-button")
	private WebElement decreaseAdultsButton;
	
	@FindBy(className = "sb-group-children")
	private WebElement childrenComponent;	
	
	 @FindBy(css = ".sb-group-children .bui-stepper__display")
	 private WebElement children_quantity;
	 
	 @FindBy(css = ".sb-group-children .bui-stepper__add-button")
	 private WebElement btn_add_children;
	 
	 @FindBy(css = ".sb-group-children .bui-stepper__subtract-button")
     private WebElement btn_subtract_children;

	@FindBy(css = ".sb-group-children .bui-stepper__add-button")
	private WebElement increaseChildrenButton;
	
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
		waitElementVisibility(autoCompleteDestinationFirstOption);
		clickElement(autoCompleteDestinationFirstOption);
	}

	
	/**
     * Perform the search of lodgings
     * 
     * @return Accommodation Results Page
     */
	public HotelsResultPage searchAccomodation() {
		waitElementVisibility(buscarButton);
		clickElement(buscarButton);
		return new HotelsResultPage(getDriver()); 
	}

	public void setFamilyInfo(int qAdults,int qChildren, String... childrenAges){
		waitElementVisibility(guestDiv);
		clickElement(guestDiv);
		setAdultsQuantity(qAdults);
		setChildrenQuantity(qChildren);
		setChildrenAge(childrenAges);
	}

	public void setChildrenQuantity(int childrenQuantity){

		if (childrenQuantity<0 || childrenQuantity>10){
			log.info("Error in Adults quantity");
		}
		else {
			switch (childrenQuantity) {
				case 0:
					log.info("Defaults Children Quantity");
					break;
				default:
					for (int i =0; i<childrenQuantity;i++){
						waitElementVisibility(increaseChildrenButton);
						clickElement(increaseChildrenButton);
					}
					break;
			}
		}
	}

	public void setAdultsQuantity(int adultsQuantity){

		if (adultsQuantity<0 || adultsQuantity>30){
			log.info("Error in Adults quantity");
		}
		else {
			switch (adultsQuantity) {
				case 1:
					waitElementVisibility(decreaseAdultsButton);
					clickElement(decreaseAdultsButton);
					break;
				case 2:
					log.info("Defaults Adults Quantity");
					break;
				default:
					for (int i =2; i<adultsQuantity;i++){
						waitElementVisibility(increaseAdultsButton);
						clickElement(increaseAdultsButton);
					}
					break;
			}
		}
	}

	/**
	 * Set the age of all childrens
	 * @param childrenAge can be an array or single variable
	 */
	public void setChildrenAge(String... childrenAge){
		for (int i =0; i<childrenAge.length;i++){
			// ID of CSS Selector generate by Children
			String tmpCSS = "[data-group-child-age=\""+i+"\"]";
			WebElement childAgeWE = getDriver().findElement(By.cssSelector(tmpCSS));
			selectElementFromDropDownList(childAgeWE,childrenAge[i]);
//			Select childAgeS = new Select(childAgeWE);
//			try {
//				childAgeS.selectByValue(childrenAge[i]);
//			}
//			catch (NoSuchElementException exception){
//				log.info("ERROR --> Children age value not allowed "+childrenAge[i]);
//			}
		}
	}

	/**
	 *
	 */
	public void setDatesSearch(){
		openCalendar();
		//Start Date
		selectDate(30);
		//End Date
		selectDate(45);
	}

	public void openCalendar(){
		// If the calendar does not open automatically
		if(!calendarLabel.isDisplayed()) {
			waitElementToBeClickable(datesDiv);
			clickElement(datesDiv);
		}
		moveToElement(calendarLabel);
	}

	public void selectDate(int daysFromNow){
		String tmpCSS = calculateDateCSS(daysFromNow);
		WebElement tmpDate =findDateElementByCSS(tmpCSS);
		clickElement(tmpDate);
	}

	public WebElement findDateElementByCSS(String cssSelector){
		try {
			return getDriver().findElement(By.cssSelector(cssSelector));
		}catch (Exception e){
			//log.info(e);
			waitElementToBeClickable(calendarControlNext);
			clickElement(calendarControlNext);
			return findDateElementByCSS(cssSelector);
		}
	}
	/**
	 *  [data-date="2020-06-26"]
	 */
	public String calculateDateCSS(int days){
		LocalDate increasedDate= LocalDate.now().plusDays(days);
		return "[data-date='"+increasedDate+"']";
	}
}
