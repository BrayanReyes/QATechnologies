package com.automation.web.tests;

import com.automation.web.pages.IFrameHomePage;
import com.automation.web.pages.YoutubeIFrame;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Class for the alert test.
 * 
 * @author july.moreno
 */
public class TestSuiteYoutube extends BaseTest {

	@BeforeTest(alwaysRun = true)
	public void beforeYoutubeTest() {
		IFrameHomePage homePage = getHomePage();
		homePage.youtubeListOption();
	}

	@Test(description = "Play Youtube video")
	public void YoutubeTest() {
		IFrameHomePage homePage = getHomePage();
		YoutubeIFrame youtubeIFrame = homePage.getYoutubeIframe();
		youtubeIFrame.playYoutubeVideo();
		youtubeIFrame.stopYoutubeVideo();
		Assert.assertTrue(youtubeIFrame.sliderProgress(),"ERROR PLAYING VIDEO");
		
	}

	@AfterMethod(alwaysRun = true)
	public void afterYoutubeTest() {
		IFrameHomePage homePage = getHomePage();
		homePage.closeYoutubeIframe();
	}
}
