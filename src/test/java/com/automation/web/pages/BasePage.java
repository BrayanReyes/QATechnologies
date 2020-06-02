package com.automation.web.pages;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Parent of the other classes of pages.
 * 
 * @author july.moreno
 */
public class BasePage {

	private WebDriver driver;
	private WebDriverWait wait;
	public Logger log = Logger.getLogger(BasePage.class);

	/**
	 * Constructor.
	 * 
	 * @param driver WebDriver
	 */
	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
		this.driver = driver;
	}

	/**
	 * Get the web driver wait.
	 * 
	 * @return {@link WebDriverWait}
	 */
	protected WebDriverWait getWait() {
		return wait;
	}

	/**
	 * Get the web driver.
	 * 
	 * @return {@link WebDriver}
	 */
	protected WebDriver getDriver() {
		return driver;
	}

	/**
	 * Close the web driver.
	 */
	public void dispose() {
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Wait element to be visible.
	 * 
	 * @param one or several elements WebElement
	 */
	public void waitElementVisibility(WebElement... webElements) {
		getWait().until(ExpectedConditions.visibilityOfAllElements(webElements));
	}

	/**
	 *
	 * Wait for element to be clickable
	 * 
	 * @param webElement {@link WebElement}
	 * @return boolean
	 */
	public boolean waitElementToBeClickable(WebElement webElement) {
		try {
			getWait().until(ExpectedConditions.elementToBeClickable(webElement));
			return true;
		} catch (TimeoutException eTimeOut) {
			log.info("TimeOut exception with Web element");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Switch to iFrame inside the web page
	 * 
	 * @param iFrame WebElement
	 * @return {@link WebDriver}
	 */
	public WebDriver switchToIFrame(WebElement iFrame) {
		waitElementVisibility(iFrame);
		return getDriver().switchTo().frame(iFrame);
	}

	/**
	 * Switch to iFrame inside the web page
	 * 
	 * @param iFrame
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
	 * Scroll inside the web page until the web element and click it
	 * 
	 * @param webElement
	 */
	protected void clickElement(WebElement webElement) {
		if (waitElementToBeClickable(webElement)) {
			Actions action = new Actions(getDriver());
			action.moveToElement(webElement).click().build().perform();
		}
	}

	/**
	 * Return a Locator with a specific format increasing the current date by the days
	 * CSS -Format -> button[data-year='2020'][data-month='5'][data-day='3'] 
	 * Note: Minus one month because in web page months start at 0 and end at 11
	 * 
	 * @param days
	 * @return {@link String}
	 */
	protected String todayDateIncreasedBy(int days) {
		LocalDate increasedDate = LocalDate.now().plusDays(days).minusMonths(1);
		return "button[data-year='" + increasedDate.getYear() + "']" + "[data-month='" + increasedDate.getMonthValue() + "']"
				+ "[data-day='" + increasedDate.getDayOfMonth() + "']";
	}

}
