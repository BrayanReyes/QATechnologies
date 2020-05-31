package org.suiteTest.web.iframes.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class VimeoIFrame extends BasePage{


    @FindBy(css ="#player")
    private WebElement playButton;

    @FindBy(className = "focus-target")
    private WebElement sliderVimeo;

    /**
     * Constructor, a factory for producing {@link ElementLocator}s.
     * @param driver
     */
    public VimeoIFrame(WebDriver driver) {
        super(driver);
        setSlider(sliderVimeo);
    }

    /**
     * Play Vimeo video by 10 seconds
     */
    public void playVideo(){
        log.info("Playing Vimeo Video");
        clickElement(playButton);
        sleep(getVideoPlayDuration());
    }

    /**
     * Pause Vimeo video
     */
    public void pauseVideo(){
        log.info("Pausing Vimeo Video");
        playButton.click();
    }
}
