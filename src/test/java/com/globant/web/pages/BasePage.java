package com.globant.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//import org.openqa.selenium.support.FindBy;

/**
 * This class defines common methods to interact with the web page under test.
 *
 * @author: july.moreno
 * @version: 26/06/2020
 */

public class BasePage {

	private final WebDriver driver;
	private final WebDriverWait wait;
	private final String parentWinHandle;
	protected Logger log = Logger.getLogger(BasePage.class);

	/**
	 * Constructor, a factory for producing ElementLocators
	 *
	 * @param driver: WebDriver
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 10), this);
		parentWinHandle = this.driver.getWindowHandle();
	}

	/**
	 * Get the value of the parent window
	 *
	 * @return parentWinHandle: String
	 */
	public String getParentWinHandle() {
		return parentWinHandle;
	}

	/**
	 * Switch to the last open tab
	 *
	 * @param driver: WebDriver
	 */
	public void switchToLastOpenTab(WebDriver driver) {
		Set<String> allWindowsHandles = driver.getWindowHandles();
		for (String handle : allWindowsHandles) {
			if (!getParentWinHandle().equals(handle))
				driver.switchTo().window(handle);
		}
	}

	/**
	 * Switch to default Window
	 *
	 * @param index: int
	 */
	public void changeWindowByIndex(int index) {
		ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(index));
	}

	/**
	 * Select from element by {value}
	 *
	 * @param element: WebElement
	 * @param value: String
	 */
	public void selectElementFromDropDownList(WebElement element, String value) {
		try {

			Select dropDownList = new Select(element);
			dropDownList.selectByValue(value);
			// dropDownList.selectByVisibleText(value);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Element is not present in the list.");
		}

	}

	/**
	 * Get the web driver
	 *
	 * @return {@link WebDriver}
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Get the WebDriverWait
	 *
	 * @return {@link WebDriverWait}
	 */
	protected WebDriverWait getWait() {
		return wait;
	}

	/**
	 * Close the driver
	 */
	public void dispose() {
		if (getDriver() != null)
			getDriver().quit();
	}

	/**
	 * Wait for elements visibility
	 *
	 * @param webElement to wait until it is visible
	 * @return boolean
	 */
	public boolean waitElementVisibility(WebElement... webElement) {
		try {
			getWait().until(ExpectedConditions.visibilityOfAllElements(webElement));
			return true;
		} catch (TimeoutException eTimeOut) {
			// log.info("TimeOut exception with Web element");
			return false;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Wait for elements visibility
	 *
	 * @param webElements list to wait until it is visible
	 * @return boolean
	 */
	public boolean waitElementVisibility(List<WebElement> webElements) {
		try {
			getWait().until(ExpectedConditions.visibilityOfAllElements(webElements));
			return true;
		} catch (TimeoutException eTimeOut) {
			return false;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Wait for element to be clickable
	 *
	 * @param webElement: WebElement
	 * @return boolean
	 */
	public boolean waitElementToBeClickable(WebElement webElement) {
		try {
			getWait().until(ExpectedConditions.elementToBeClickable(webElement));
			return true;
		} catch (TimeoutException eTimeOut) {
			// log.info("TimeOut exception to Click Web element.");
			return false;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Wait for an element attribute
	 *
	 * @param webElement: WebElement
	 * @param attribute:  String
	 * @param value:      String
	 * @return boolean
	 */
	public boolean waitForElementAttribute(WebElement webElement, String attribute, String value) {
		try {
			getWait().until(ExpectedConditions.attributeToBe(webElement, attribute, value));
			return true;
		} catch (TimeoutException eTimeOut) {
			log.info("TimeOut exception to get Web element attribute.");
			return false;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Wait After an Element disappear
	 *
	 * @param webElements: WebElement
	 * @param attribute:   String
	 * @param value:       String
	 * @return boolean
	 */
	public boolean waitAfterElementDisappear(WebElement webElements, String attribute, String value) {
		try {
			getWait().until(ExpectedConditions.not(ExpectedConditions.attributeToBe(webElements, attribute, value)));
			return true;
		} catch (TimeoutException eTimeOut) {
			return false;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Wait After an Element disappear
	 *
	 * @param webElement: WebElement
	 * @return boolean
	 */
	public boolean waitToRefresh(WebElement webElement) {
		try {
			getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(webElement)));
			return true;
		} catch (TimeoutException eTimeOut) {
			return false;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Switch to iFrame inside the web page
	 *
	 * @param iFrame to switch
	 * @return {@link WebDriver}
	 */
	public WebDriver switchToIFrame(WebElement iFrame) {
		waitElementVisibility(iFrame);
		return getDriver().switchTo().frame(iFrame);
	}

	/**
	 * Switch to iFrame inside the web page
	 *
	 * @param iFrame int switch to
	 * @return {@link WebDriver}
	 */
	public WebDriver switchToIFrame(int iFrame) {
		return getDriver().switchTo().frame(iFrame);
	}

	/**
	 * Switch from iFrame to the main page
	 *
	 * @return {@link WebDriver}
	 */
	public WebDriver switchToDefaultContent() {
		return getDriver().switchTo().defaultContent();
	}

	/**
	 * Wait for the web element to be clickable and Scroll until find it to click it
	 *
	 * @param webElement to locate and click
	 */
	protected void clickElement(WebElement webElement) {
		if (waitElementToBeClickable(webElement)) {
			Actions action = new Actions(getDriver());
			action.moveToElement(webElement).click().build().perform();
		}
	}

	/**
	 * Scroll inside the web page until the web element
	 *
	 * @param webElement to locate and click
	 */
	protected void moveToElement(WebElement webElement) {
		Actions action = new Actions(getDriver());
		action.moveToElement(webElement).perform();
	}

	/**
	 * Wait for elements visibility
	 *
	 * @param webElement to wait until it is visible
	 * @return boolean
	 */
	public boolean waitElementInvisibility(WebElement... webElement) {
		try {
			getWait().until(ExpectedConditions.invisibilityOfAllElements(webElement));
			return true;
		} catch (TimeoutException eTimeOut) {
//			log.info("TimeOut exception invisibility with Web element");
			return false;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}


	/**
	 * Set the adults quantity for a search
	 *
	 * @param numberOfPassengers: String
	 */
	public void selectPassengersQuantity(WebElement webElement, String numberOfPassengers) {
		waitElementVisibility(webElement);
		selectElementFromDropDownList(webElement, numberOfPassengers);
	}

	/**
	 * Wait to an attribute
	 *
	 * @param webElement: WebElement
	 * @param attribute:  String
	 * @param value:      String
	 */
	public void waitAttributeToBe(WebElement webElement, String attribute, String value) {
		getWait().until(ExpectedConditions.attributeToBe(webElement, attribute, value));
	}

	/**
	 * Handle advertisement windows
	 */
	public void handleAdvertisement() {
		String mainWindowHandle = driver.getWindowHandle();

		Set<String> allHandles = driver.getWindowHandles();

		for (String curHandle : allHandles) {
			if (!curHandle.equals(mainWindowHandle)) {
				try {
					driver.switchTo().window(curHandle);
					driver.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		driver.switchTo().window(mainWindowHandle);
	}

	/**
	 * Scroll to the element
	 * 
	 * @param webDriver: WebDriver
	 * @param element: WebElement
	 */
	public static void jsScroll(WebDriver webDriver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	/**
	 * Custom Wait for the Fare Button in the Departure Flight
	 *
	 * @param containerElement:     list to wait until it is visible
	 * @param elementToBeClickable: String
	 *
	 */
	public void waitElementInsideContainer(WebElement containerElement, String elementToBeClickable) {
		getWait().until(
				ExpectedConditions.visibilityOf(containerElement.findElement(By.cssSelector(elementToBeClickable))));

	}

	/**
	 * Custom Wait for the result after selecting the departing flight
	 *
	 * @param cssUpdateMarker: CSS String
	 *
	 */
	public void waitListToBeRefreshed(String cssUpdateMarker) {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className(cssUpdateMarker)));
	}

	/**
	 * Return a Locator with a specific format increasing the current date by the
	 * days CSS -Format -> button[data-year='2020'][data-month='5'][data-day='3']
	 * Note: Minus one month because in web page months start at 0 and end at 11
	 *
	 * @param days: int
	 * @return {@link String}
	 */
	protected String calculateDateCSS(int days) {
		LocalDate increasedDate = LocalDate.now().plusDays(days).minusMonths(1);
		return "button[data-year='" + increasedDate.getYear() + "']" + "[data-month='" + increasedDate.getMonthValue()
				+ "']" + "[data-day='" + increasedDate.getDayOfMonth() + "']";
	}

	/**
	 * Find the element for the date component in the calendar
	 *
	 * @param cssSelector: String
	 */
	public WebElement findDateElementByCSS(WebElement webElement, String cssSelector) {
		try {
			return getDriver().findElement(By.cssSelector(cssSelector));
		} catch (Exception e) {
			// log.info(e);
			waitElementToBeClickable(webElement);
			clickElement(webElement);
			return findDateElementByCSS(webElement, cssSelector);
		}
	}

	/**
	 * Select a Date in the Calendar according with a number of days given
	 *
	 * @param daysFromNow: int
	 */

	public void selectDateInCalendar(int daysFromNow, WebElement nextMonthButton) {
		String tmpCSS = calculateDateCSS(daysFromNow);
		WebElement dayToTravel = findDateElementByCSS(nextMonthButton, tmpCSS);
		clickElement(dayToTravel);
	}

	/**
	 * Open the Calendar
	 *
	 */

	public void openCalendar(WebElement dataPicker, WebElement elementInsideDataPicker, WebElement calendarDiv) {
		clickElement(dataPicker);
		if (!elementInsideDataPicker.isDisplayed()) {
			waitElementVisibility(calendarDiv);
			clickElement(calendarDiv);
		}
		moveToElement(elementInsideDataPicker);
	}

}
