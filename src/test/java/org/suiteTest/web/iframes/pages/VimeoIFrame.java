package org.suiteTest.web.iframes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void playVideo(){
        //getDriver().findElement(By.cssSelector("div.vp-controls.tiny button.play.rounded-box.state-paused")).click();
        log.info("Playing Vimeo Video");
        List<WebElement> list = getDriver().findElements(By.cssSelector("button.play.rounded-box.state-paused *"));

        list.forEach(element -> log.info( "Element  "+ element.getText() +" -- "
                + element.getTagName() + " -- Clickable: "
                +waitElementToBeClickable(element)));

        //waitElementVisibility(playButton);
        //waitElementToBeClickable(playButton);
        playButton.click();
        sleep(10);
    }

    public void pauseVideo(){
        log.info("Pausing Vimeo Video");

        //waitElementVisibility(pauseButton);
        //waitElementToBeClickable(pauseButton);
        pauseButton.click();
        sleep(10);
    }
}
