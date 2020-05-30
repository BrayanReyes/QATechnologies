package org.suiteTest.web.iframes.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class YoutubeIFrame extends BasePage {

    @FindBy(css = "button.ytp-large-play-button.ytp-button")
    private WebElement playButton;

    @FindBy(css = "button.ytp-play-button.ytp-button")
    private WebElement pauseButton;
    /**
     * Contructor, a factory for producing {@link ElementLocator}s.
     * @param driver
     */
    public YoutubeIFrame(WebDriver driver) {
        super(driver);
    }

    public void playVideo(){
        log.info("Playing Youtube Video");
        //waitElementVisibility(playButton);
        //waitElementToBeClickable(playButton);
        playButton.click();
        sleep(10);
    }

    public void pauseVideo(){
        log.info("Pausing Youtube Video");
        //waitElementVisibility(pauseButton);
        //waitElementToBeClickable(pauseButton);
        pauseButton.click();
        sleep(10);
    }

}
