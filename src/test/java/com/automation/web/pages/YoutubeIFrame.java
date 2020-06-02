package com.automation.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Class for interact with the Home Page.
 * 
 * @author july.moreno
 */

public class YoutubeIFrame extends BasePage {

	/**
	 * Constructor.
	 * 
	 * @param driver WebDriver
	 */

	public YoutubeIFrame(WebDriver driver) {
		super(driver);
		setSlider(sliderYoutube);
	}

	/**
	 * Web Elements
	 */

	@FindBy(css = "button.ytp-large-play-button.ytp-button")
	private WebElement youtubePlayButton;

	@FindBy(css = "button.ytp-play-button.ytp-button")
	private WebElement youtubeStopButton;

	@FindBy(css = ".ytp-progress-bar")
    private WebElement sliderYoutube;
	
	/**
	 * Play Youtube video.
	 */

	public void playYoutubeVideo() {
		waitElementVisibility(youtubePlayButton);
		clickElement(youtubePlayButton);	
		getWait().until(ExpectedConditions.invisibilityOf(sliderYoutube));
		log.info("Playing YouTube video");

	}

	/**
	 * Stop Youtube Video
	 */

	public void stopYoutubeVideo() {
		youtubeStopButton.click();
		log.info("YouTube video stopped");
	}

}