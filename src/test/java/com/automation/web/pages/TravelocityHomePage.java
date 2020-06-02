package com.automation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Class for interact with Travelocity Web Page.
 * 
 * @author july.moreno
 */

public class TravelocityHomePage extends BasePage {

	@FindBy(id = "tab-flight-tab-hp")
	private WebElement flightsTab;

	@FindBy(id = "flight-type-roundtrip-label-hp-flight")
	private WebElement roundTripOption;

	@FindBy(id = "flight-origin-hp-flight")
	private WebElement flyingFrom;

	@FindBy(id = "flight-destination-hp-flight")
	private WebElement flyingTo;

	@FindBy(id = "flight-departing-hp-flight")
	private WebElement departingDate;

	@FindBy(id = "flight-returning-hp-flight")
	private WebElement returningDate;

	@FindBy(id = "flight-adults-hp-flight")
	private WebElement adultsDropdown;
	private Select adults;

	@FindBy(id = "flight-children-hp-flight")
	private WebElement childrenDropdown;
	private Select children;

	@FindBy(css = ".btn-primary.btn-action.gcw-submit[data-gcw-change-submit-text='Search']")
	private WebElement searchButton;

	@FindBy(css = ".alert.alert-error.validation-alert[aria-hidden='false'] a")
	private WebElement alertMessage;

	/**
	 * Constructor.
	 * 
	 * @param driver WebDriver
	 * @param url    String
	 */
	public TravelocityHomePage(WebDriver driver, String url) {
		super(driver);
		driver.get(url);
		adults = new Select(adultsDropdown);
		children = new Select(childrenDropdown);
	}

	/**
	 * Click in Flights Button
	 */
	public void flightsTab() {
		waitElementVisibility(flightsTab);
		clickElement(flightsTab);
		log.info("Click on Flights Tab");
	}

	/**
	 * Click on RoundTrip
	 */
	public void setFlightType() {
		waitElementVisibility(roundTripOption);
		clickElement(roundTripOption);
		log.info("Select Roundtrip Option");
	}

	/**
	 * Set the City where you are going to depart
	 * 
	 * @param flyingFrom
	 */
	public void setFlyingFrom(String flyingFrom) {
		waitElementVisibility(this.flyingFrom);
		this.flyingFrom.sendKeys(flyingFrom);
		log.info("City Flying From typed");
	}

	/**
	 * Set the City where do will return
	 * 
	 * @param flyingTo
	 */
	public void setFlyingTo(String flyingTo) {
		waitElementVisibility(this.flyingTo);
		this.flyingTo.sendKeys(flyingTo);
		log.info("City Flying To typed");
		
	}

	/**
	 * Set the Departing Date. The date should be four days after today
	 */
	public void setDepartingDate() {
		clickElement(departingDate);
		String tmpLocator = todayDateIncreasedBy(4);
		WebElement startDate = getDriver().findElement(By.cssSelector(tmpLocator));
		clickElement(startDate);
		log.info("Selecting Departing Date through the Calendar");
	}

	/**
	 * Set the Returning Date. The date should be ten days after today
	 */
	public void setReturningDate() {
		clickElement(returningDate);
		String tmpLocator = todayDateIncreasedBy(10);
		WebElement endDate = getDriver().findElement(By.cssSelector(tmpLocator));
		clickElement(endDate);
		log.info("Selecting Returning Date through the Calendar");
	}

	/**
	 * Set the adults quantity in the flight
	 * 
	 * @param adults
	 */
	public void setAdults(String adults) {
		log.info("Selecting Adults quantity in the dropdown list");
		try {
			this.adults.selectByValue(adults);
		} catch (NoSuchElementException exception) {
			log.info(" ERROR --> Adults value not allowed: " + adults);
		}
	}

	/**
	 * Set the children quantity in the flight
	 * 
	 * @param children
	 */
	public void setChildren(String children) {
		log.info("Selecting Children quantity in the dropdown list");
		try {
			this.children.selectByValue(children);
		} catch (NoSuchElementException exception) {
			log.info(" ERROR --> Children value not allowed: " + children);
		}
	}

	/**
	 * Set the age of every children
	 * 
	 * @param childrenAge
	 */
	public void setChildrenAge(String... childrenAge) {
		log.info("Setting Children Ages in the dropdown list");
		for (int i = 0; i < childrenAge.length; i++) {
			// ID of CSS Selector generate by Children
			String tmpLocator = "#flight-age-select-" + (i + 1) + "-hp-flight";
			WebElement childAgeWebElement = getDriver().findElement(By.cssSelector(tmpLocator));
			Select childAgeSelector = new Select(childAgeWebElement);
			try {
				childAgeSelector.selectByValue(childrenAge[i]);
			} catch (NoSuchElementException exception) {
				log.info(" ERROR --> Adults value not allowed " + childrenAge[i]);
			}
		}
	}

	/**
	 * Click on Search Button
	 */
	public void searchFlight() {
		clickElement(searchButton);
		log.info("Searching Flights");
	}

	/**
	 * Get message alert after searching
	 */
	public String getAlertMessage() {
		waitElementVisibility(alertMessage);
		return alertMessage.getText();
	}

}
