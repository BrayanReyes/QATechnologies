package org.suiteTest.web.iframes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import javax.lang.model.element.Element;
import java.util.List;

public class VimeoIFrame extends BasePage{


    @FindBy(css = ".play-icon .fill")
    private WebElement playButton;

    @FindBy(css = ".pause-icon > svg")
    private WebElement pauseButton;
    /**
     * Contructor, a factory for producing {@link ElementLocator}s.
     * @param driver
     */
    public VimeoIFrame(WebDriver driver) {
        super(driver);
    }

    /**
     * Play Vimeo video by 10 seconds
     */
    public void playVideo(){
        log.info("Playing Vimeo Video");
        moveToWebElement(playButton);
        waitElementVisibility(playButton);
        waitElementToBeClickable(playButton);
        playButton.click();
        sleep(10);
    }

    /**
     * Pause Vimeo video
     */
    public void pauseVideo(){
        log.info("Pausing Vimeo Video");
        pauseButton.click();
        sleep(2);
    }
}
