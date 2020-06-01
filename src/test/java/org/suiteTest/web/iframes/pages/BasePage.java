package org.suiteTest.web.iframes.pages;

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

import java.time.LocalDate;

/**
 * Class with all common methods between web pages.
 * @author Pinguin
 */
public class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    protected Logger log = Logger.getLogger(BasePage.class);

    /**
     * Contructor, a factory for producing {@link ElementLocator}s.
     * @param driver Webdriver of the page
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,20);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(this.driver,20)
                ,this);
    }

    /**
     * Get the web driver
     * @return {@link WebDriver}
     */
    protected WebDriver getDriver() {
        return driver;
    }

    /**
     * Get the WebDriverWait
     * @return {@link WebDriverWait}
     */
    protected WebDriverWait getWait() {
        return wait;
    }

    /**
     * Close the driver
     */
    public void dispose(){
        if (getDriver()!=null)
            getDriver().quit();
    }

    /**
     * Wait for elements visibility
     * @param webElement to wait until it is visible
     */
    public void waitElementVisibility(WebElement... webElement){
        getWait().until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    /**
     *
     * Wait for element to be clickable
     * @param webElement Web element
     * @return boolean
     */
    public boolean waitElementToBeClickable(WebElement webElement){
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        }
        catch (TimeoutException eTimeOut){
            log.info("TimeOut exception with Web element");
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Switch to iFrame inside the web page
     * @param iFrame to switch
     * @return  {@link WebDriver}
     */
    public WebDriver switchToIFrame(WebElement iFrame){
        waitElementVisibility(iFrame);
        return getDriver().switchTo().frame(iFrame);
    }

    /**
     * Switch to iFrame inside the web page
     * @param iFrame  int switch to
     * @return {@link WebDriver}
     */
    public WebDriver switchToIFrame(int iFrame){
       return getDriver().switchTo().frame(iFrame);
    }

    /**
     * Switch from iFrame to the main page
     * @return {@link WebDriver}
     */
    public WebDriver switchToDefaultContent(){
        return getDriver().switchTo().defaultContent();
    }

    /**
     * Another implicit Wait by n secodns
     * @param seconds to wait
     */
    protected void sleep (int seconds){
        try {
            log.info("Sleeping "+seconds+" seconds.");
            Thread.sleep(seconds*1000);
        }catch (Exception e){
            log.info("Error: Could not sleep.");
        }
    }

    /**
     * Scroll inside the web page until the web element and click it
     * @param webElement to locate and click
     */
    protected void clickElement(WebElement webElement){
        if(waitElementToBeClickable(webElement)) {
            Actions action = new Actions(getDriver());
            action.moveToElement(webElement).click().build().perform();
        }
    }

    /**
     * Return a CSS with a specific format increasing the current date by the days
     * CSS -Format -> button[data-year='2020'][data-month='5'][data-day='3']
     * Note: Minus one month because in web page months start at 0 and end at 11
     * @param days to increase date
     * @return {@link String}
     */
    protected String todayDateIncreasedBy(int days) {
        LocalDate increasedDate= LocalDate.now().plusDays(days).minusMonths(1);
        return "button[data-year='"+increasedDate.getYear()+"']" +
                "[data-month='"+increasedDate.getMonthValue()+"']" +
                "[data-day='"+increasedDate.getDayOfMonth()+"']";
    }
}
