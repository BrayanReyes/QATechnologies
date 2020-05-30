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
     * @param driver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,30);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(this.driver,30)
                ,this);

        sleep(5);
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
     * @param webElement
     */
    public void waitElementVisibility(WebElement... webElement){
        getWait().until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    /**
     * Wait for element to be clickable
     * @param  {@link WebElement}
     */
    public boolean waitElementToBeClickable(WebElement webElement){
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        }
        catch (TimeoutException eTimeOut){
            log.info("TimeOut exception");
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Switch to iFrame inside the web page
     * @param iFrame WebElement
     * @return  {@link WebDriver}
     */
    public WebDriver switchToIFrame(WebElement iFrame){
        waitElementVisibility(iFrame);
        //return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
        return getDriver().switchTo().frame(iFrame);
    }

    /**
     * Switch to iFrame inside the web page
     * @param iFrame
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
     * @param seconds
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
     * Scroll inside the web page until the web element
     * @param webElement
     */
    protected void moveToWebElement(WebElement webElement){
        Actions action = new Actions(getDriver());
        action.clickAndHold(webElement).moveToElement(webElement)
                .release(webElement).build().perform();
    }
}
