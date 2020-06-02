package com.automation.web.tests;

import com.automation.web.pages.IFrameHomePage;
import com.automation.web.pages.VimeoIFrame;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Class for the alert test.
 * @author july.moreno
 */
public class TestSuiteVimeo extends BaseTest {
    
	@BeforeTest(alwaysRun = true)
	public void beforeVimeoTest() {
		IFrameHomePage homePage = getHomePage();
		homePage.vimeoListOption();
	}
		
	@Test(description = "Play Vimeo video")
	public void VimeoTest () {
		IFrameHomePage homePage = getHomePage();
		VimeoIFrame vimeoIFrame = homePage.getVimeoIframe();
		vimeoIFrame.playVimeoVideo();
		vimeoIFrame.stopVimeoVideo();
		Assert.assertTrue(vimeoIFrame.sliderProgress(),"ERROR PLAYING VIDEO");
		
	}
 
	@AfterMethod(alwaysRun = true) 
	public void AfterVimeoTest() {
		IFrameHomePage homePage = getHomePage();
		homePage.closeVimeoIframe();
	}
}
