package com.automation.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	protected WebElement slider;
	public Logger log = Logger.getLogger(BasePage.class);

	/**
	 * Constructor.
	 * 
	 * @param pDriver WebDriver
	 */
	public BasePage(WebDriver pDriver) {
		PageFactory.initElements(pDriver, this);
		wait = new WebDriverWait(pDriver, 20);
		driver = pDriver;
	}

	/**
	 * Get the web driver wait.
	 * 
	 * @return {@link WebDriverWait}
	 */
	public WebDriverWait getWait() {
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
	 * @param element WebElement
	 */
	public void waitElementVisibility(WebElement element) {
		getWait().until(ExpectedConditions.visibilityOfAllElements(element));
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
			log.info("TimeOut exception with Web Element");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Scroll inside the web page until the web element and click it
	 * 
	 * @param webElement
	 */
	protected void clickElement(WebElement webElement) {
		waitElementToBeClickable(webElement);
		webElement.click();
		log.info("Switch to iFrame");
	}

	/**
	 * Set the value for slider
	 * 
	 * @param webElement
	 */
	protected void setSlider(WebElement webElement) {
		this.slider = webElement;
	}

	/**
	 * Check if the slider has some progress
	 * 
	 * @return
	 */
	public boolean sliderProgress() {
		int videoNow = Integer.parseInt(this.slider.getAttribute("aria-valuenow"));
		log.info("Video has reproduced until " + videoNow + " seconds.");
		return (videoNow > 0);
	}

}
