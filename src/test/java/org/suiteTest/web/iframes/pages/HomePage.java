package org.suiteTest.web.iframes.pages;

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

    @FindBy(css = "#vimeo section.p-1 .embed-responsive-item")
    private WebElement vimeoIFrame;

    /**
     * Contructor, a factory for producing {@link ElementLocator}s.
     * @param driver
     */
    public HomePage(WebDriver driver,String url) {
        super(driver);
        getDriver().get(url);
    }

    public YoutubeIFrame switchToIFrameYoutube(){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(youtubeIFrame);
        actions.perform();
        return new YoutubeIFrame(switchToIFrame(youtubeIFrame));
    }

    public VimeoIFrame switchToIFrameVimeo(){
        return new VimeoIFrame(switchToIFrame(vimeoIFrame));
    }

}
