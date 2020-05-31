package org.suiteTest.web.iframes.tests;

import org.suiteTest.web.iframes.pages.HomePage;
import org.suiteTest.web.iframes.pages.VimeoIFrame;
import org.suiteTest.web.iframes.pages.YoutubeIFrame;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestSuite extends BaseTest{

    @AfterMethod(alwaysRun = true)
    @Parameters({"url"})
    public void afterMethod(String url){
        log.info("Launch HomePage");
        getHomePage().switchToDefaultContent();
        driver.getDriver().get(url);
    }

    @Test(description = "Youtube IFrame Test, Play and Pause", enabled=true)
    public void youtubeTest(){
        log.info("Starting Youtube Test");
        HomePage homePage = getHomePage();
        YoutubeIFrame youtubeIFrame = homePage.switchToIFrameYoutube();
        youtubeIFrame.playVideo();
        youtubeIFrame.pauseVideo();
        Assert.assertTrue(youtubeIFrame.sliderHasProgress(),"ERROR PLAYING VIDEO");
    }

    @Test(description = "Vimeo IFrame Test, Play and Pause", enabled=true)
    public void vimeoTest(){
        log.info("Starting Vimeo Test");
        HomePage homePage = getHomePage();
        VimeoIFrame vimeoIFrame = homePage.switchToIFrameVimeo();
        vimeoIFrame.playVideo();
        vimeoIFrame.pauseVideo();
        Assert.assertTrue(vimeoIFrame.sliderHasProgress(),"ERROR PLAYING VIDEO");
    }
}
