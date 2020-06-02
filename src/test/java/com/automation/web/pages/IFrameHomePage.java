package com.automation.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IFrameHomePage extends BasePage {

	public IFrameHomePage(WebDriver driver, String url) {
		super(driver);
		getDriver().get(url);
	}

	@FindBy(css = "div.sticky #scrollspy ul li:nth-child(2)")
	private WebElement youtubeListOption;

	@FindBy(css = "#youtube section.p-1 iframe")
	private WebElement youtubeIframe;

	@FindBy(css = "div.sticky #scrollspy ul li:nth-child(3) a")
	private WebElement vimeoListOption;

	@FindBy(css = "#vimeo iframe.embed-responsive-item:first-of-type")
	private WebElement vimeoIframe;

	/**
	 * Click on Vimeo iframe in the Option List
	 */

	public void vimeoListOption() {
		vimeoListOption.click();
		log.info("Vimeo iframe List Option clicked");
	}

	/**
	 * Switch to Vimeo iFrame.
	 * 
	 * @return
	 */
	public VimeoIFrame getVimeoIframe() {
		return new VimeoIFrame(switchToIFrame(vimeoIframe));
	}

	/**
	 * Switch to the original Page.
	 */
	public void closeVimeoIframe() {
		getDriver().switchTo().defaultContent();
		log.info("Outside Vimeo iFrame");
	}

	/**
	 * Click on Youtube iframe in the Option List
	 */

	public void youtubeListOption() {
		youtubeListOption.click();
		log.info("YouTube iframe List Option clicked");
	}

	/**
	 * Switch to Youtube iFrame
	 * 
	 * @return
	 */
	public YoutubeIFrame getYoutubeIframe() {
		return new YoutubeIFrame(switchToIFrame(youtubeIframe));
	}

	/**
	 * Switch to the original Page.
	 */
	public void closeYoutubeIframe() {
		getDriver().switchTo().defaultContent();
		log.info("Outside Youtube iFrame");
	}

}
