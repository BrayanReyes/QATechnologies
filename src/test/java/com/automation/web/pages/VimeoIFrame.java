package com.automation.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * Class for interact with the home page.
 * 
 * @author july.moreno
 */

public class VimeoIFrame extends BasePage {

	/**
	 * Constructor.
	 * 
	 * @param driver WebDriver
	 */

	public VimeoIFrame(WebDriver driver) {
		super(driver);
		setSlider(sliderVimeo);
	}

	/**
	 * Web Elements
	 * 
	 */

	@FindBy(css = "#player:nth-child(1) div.vp-controls > button div.play-icon")
	private WebElement vimeoPlayButton;

	@FindBy(css = "#player:nth-child(1) div.vp-controls > button div.pause-icon")
	private WebElement vimeoStopButton;

	@FindBy(className = "focus-target")
	private WebElement sliderVimeo;

	/**
	 * Play Vimeo video.
	 */

	public void playVimeoVideo() {
		clickElement(vimeoPlayButton);
		getWait().until(ExpectedConditions.attributeToBe(sliderVimeo, "aria-valuenow", "10"));
		log.info("Playing Vimeo video");
	}

	/**
	 * Stop Vimeo Video
	 */

	public void stopVimeoVideo() {
		vimeoStopButton.click();
		log.info("Vimeo video stopped");
	}

}