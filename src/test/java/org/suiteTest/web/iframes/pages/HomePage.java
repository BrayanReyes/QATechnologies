package org.suiteTest.web.iframes.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

/**
 * Class of Main Page
 * @author Pinguin
 */
public class HomePage extends BasePage{

    @FindBy(css = "#youtube section.p-1 .embed-responsive-item")
    private WebElement youtubeIFrame;

    @FindBy(css = "a[href='#youtube']")
    private WebElement youtubeIFrameSection;

    @FindBy(css = "#vimeo section.p-1 .embed-responsive-item")
    private WebElement vimeoIFrame;

    @FindBy(css = "a[href='#vimeo']")
    private WebElement vimeoIFrameSection;

    @FindBy(id = "gdpr_basic")
    private WebElement cookieSection;

    @FindBy(id = "gdpr_btn_full_agree")
    private WebElement cookieButton;
    /**
     * Contructor, a factory for producing {@link ElementLocator}s.
     * @param driver
     */
    public HomePage(WebDriver driver,String url) {
        super(driver);
        getDriver().get(url);
        checkCookies();
    }

    /**
     * Switch to youtube iFrame
     * @return {@link YoutubeIFrame}
     */
    public YoutubeIFrame switchToIFrameYoutube(){
        clickElement(youtubeIFrameSection);
        return new YoutubeIFrame(switchToIFrame(youtubeIFrame));
    }

    /**
     * Switch to Vimeo iFrame
     * @return {@link VimeoIFrame}
     */
    public VimeoIFrame switchToIFrameVimeo(){
        clickElement(vimeoIFrameSection);
        return new VimeoIFrame(switchToIFrame(vimeoIFrame));
    }

    /**
     * Validates if pop up of cookies is present and accept it
     */
    public void checkCookies(){
        try {
            if (cookieSection.getAttribute("aria-hidden") == null)
                clickElement(cookieButton);
            ;
            log.info("Cookies accepted");
        }
        catch (NoSuchElementException e){
            log.info("Cookies alarm not present");
        }
    }




}
