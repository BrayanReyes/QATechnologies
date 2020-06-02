package com.automation.web.tests;

import com.automation.web.driver.Driver;
import com.automation.web.pages.TravelocityHomePage;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

/**
 * Parent of the other classes of test.
 * 
 * @author july.moreno
 */
public class BaseTest {

	Driver driver;
	private TravelocityHomePage homePage;
	public Logger log = Logger.getLogger(BaseTest.class);

	@BeforeTest(alwaysRun = true)
	@Parameters({ "browser", "url"})
	public void beforeTest(String browser, String url) {
		driver = new Driver(browser);
		driver.getDriver().manage().window().maximize();
		homePage = new TravelocityHomePage(driver.getDriver(), url);
		log.info("Open Browser");
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		homePage.dispose();
		log.info("Close Browser");
	}

	/**
	 * Get the home page.
	 * 
	 * @return {@link TravelocityHomePage}
	 */
	public TravelocityHomePage getHomePage() {
		return homePage;
	}

}
