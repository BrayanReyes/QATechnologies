package com.automation.web.tests;

import com.automation.web.driver.Driver;
import com.automation.web.pages.IFrameHomePage;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

/**
 * Parent of the other classes of test.
 * @author july.moreno
 */
public class BaseTest {
	
	
	private Driver driver;
    private IFrameHomePage homePage;
	public Logger log = Logger.getLogger(BaseTest.class);
	
	
	@BeforeTest(alwaysRun=true)
	@Parameters({"browser", "url"})
	public void beforeClass(String browser, String url) {
		driver = new Driver(browser);
		driver.getDriver().manage().window().maximize();
		homePage= new IFrameHomePage(driver.getDriver(), url);
		log.info("Browser Opened");
	}
	
	
	@AfterTest(alwaysRun=true)
	public void afterTest() {
		homePage.dispose();
		log.info("Browser closed");
	}
	
	/**
	 * Get the home page.
	 * @return {@link IFrameHomePage}
	 */
	public IFrameHomePage getHomePage() {
		return homePage;
	}
	
}