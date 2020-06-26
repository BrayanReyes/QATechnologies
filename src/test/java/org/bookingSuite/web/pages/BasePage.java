package org.bookingSuite.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

/**
 * This class defines common methods to interact with the web page under test
 * 
 * @author: july.moreno
 * @version: 23/06/2020
 */

public class BasePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private String parentWinHandle;
	protected Logger log = Logger.getLogger(BasePage.class);

	// Common Locators to handle with modal pages
	@FindBy(css = ".modal-mask.bp_leaving_users_light_box_mask")
	private WebElement usersModal;

	@FindBy(css = ".modal-mask-closeBtn")
	private WebElement closeModalButton;

	@FindBy(css = ".bp_rlu_checkbox__label")
	private WebElement dontShowModal;

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
	 * Select from element by {value}
	 * 
	 * @param element
	 * @param value
	 */
	public void selectElementFromDropDownList(WebElement element, String value) {
		try {

			Select dropDownList = new Select(element);
			dropDownList.selectByValue(value);
			// dropDownList.selectByVisibleText(value);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Element is not present in the list");
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
	 * 
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
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Handle the modal displayed in the booking process
	 */
	public void handleUsersModal() {
		try {
			waitElementVisibility(usersModal);
			dontShowModal.click();
			closeModalButton.click();
		} catch (Exception e) {
			log.info("The modal doesn't show up");
		}
	}

}
