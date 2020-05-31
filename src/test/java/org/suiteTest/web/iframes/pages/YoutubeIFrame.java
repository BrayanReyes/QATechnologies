package org.suiteTest.web.iframes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class YoutubeIFrame extends BasePage {

    @FindBy(css = "button.ytp-large-play-button.ytp-button ")
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

    /**
     * Play Youtube video by 10 seconds
     */
    public void playVideo(){
        log.info("Playing Youtube Video");
        waitElementVisibility(playButton);
        clickElement(playButton);
        sleep(10);
    }

    /**
     * Pause Youtube video
     */
    public void pauseVideo(){
        log.info("Pausing Youtube Video");
        pauseButton.click();
        sleep(2);
    }

}
