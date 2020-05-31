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
    protected WebElement slider;
    protected int videoPlayDuration = 10;
    protected Logger log = Logger.getLogger(BasePage.class);

    /**
     * Contructor, a factory for producing {@link ElementLocator}s.
     * @param driver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,20);
        PageFactory.initElements(getDriver()
                //new AjaxElementLocatorFactory(this.driver,20)
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
     * Get the value of duration of the play
     * @return int
     */
    public int getVideoPlayDuration(){
        return this.videoPlayDuration;
    }

    /**
     * Wait for elements visibility
     * @param webElement
     */
    public void waitElementVisibility(WebElement... webElement){
        getWait().until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    /**
     *
     * Wait for element to be clickable
     * @param webElement {@link WebElement}
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
     * @param iFrame WebElement
     * @return  {@link WebDriver}
     */
    public WebDriver switchToIFrame(WebElement iFrame){
        waitElementVisibility(iFrame);
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
     * Scroll inside the web page until the web element and click it
     * @param webElement
     */
    protected void clickElement(WebElement webElement){
        if(waitElementToBeClickable(webElement)) {
            Actions action = new Actions(getDriver());
            action.moveToElement(webElement).click().build().perform();
            log.info("Moving and clicking.");
        }
    }

    /**
     * Scroll inside the web page until the web element
     * @param webElement
     */
    protected void moveToWebElement(WebElement webElement){
        Actions action = new Actions(getDriver());
        action.moveToElement(webElement).build().perform();
    }

    /**
     * Get the attribute of a web element and transform to int, only for natural numbers.
     * In other cases use String getAttribute
     * @param webElement
     * @param attribute
     * @return -1 is error.
     */
    protected int getAttributeInt(WebElement webElement, String attribute){
        try {
            return Integer.parseInt(webElement.getAttribute(attribute));
        }catch (NumberFormatException e){
            log.info("Error - converting values from String to int.");
        }catch (Exception e){
            log.info("Error - please check logs");
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Get the attribute of a web element and handle exceptions
     * @param webElement
     * @param attribute
     * @return NULL when error
     */
    protected String getAttributeString(WebElement webElement, String attribute){
        try {
            return webElement.getAttribute(attribute);
        }
        catch (NullPointerException e){
            log.info("Error - Video length is not present");
        }catch (Exception e){
            log.info("Error - please check logs");
            e.printStackTrace();
        }
        return null;
    }


    /**--------------------------Specific--------------------------------**/

    /**
     * Set the value for slider
     * @param webElement
     */
    protected void setSlider(WebElement webElement){
        this.slider=webElement;
    }

    /**
     * Check if the slider has some progress
     * @return
     */
    public boolean sliderHasProgress(){
        int videoLength = getAttributeInt(slider,"aria-valuemax");
        int videoNow = getAttributeInt(slider,"aria-valuenow");
        log.info("Video has a length of "+videoLength
                +" seconds and it reproduces until "+videoNow+" seconds.");
        return (videoNow>0);
    }
}
