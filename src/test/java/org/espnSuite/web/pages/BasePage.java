package org.espnSuite.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

	private WebDriver driver;
	private WebDriverWait wait;
	protected Logger log = Logger.getLogger(BasePage.class);

	/**
	 * Constructor, a factory for producing {@link ElementLocator}s.
	 * 
	 * @param driver Web driver of the page
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 10), this);
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
			log.info("TimeOut exception with Web element");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
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
			log.info("TimeOut exception with Web element");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 *
	 * Wait for element to be clickable
	 * 
	 * @param webElement Web element
	 * @return boolean
	 */
	public boolean waitElementToBeClickable(WebElement webElement) {
		try {
			getWait().until(ExpectedConditions.elementToBeClickable(webElement));
			return true;
		} catch (TimeoutException eTimeOut) {
			log.info("TimeOut exception to Click Web element");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
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
	 * Scroll inside the web page until the web element and click it
	 * 
	 * @param webElement to locate and click
	 */
	protected void clickElement(WebElement webElement) {
		if (waitElementToBeClickable(webElement)) {
			Actions action = new Actions(getDriver());
			action.moveToElement(webElement).click().build().perform();
		}
	}

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
			log.info("TimeOut exception invisibility with Web element");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
