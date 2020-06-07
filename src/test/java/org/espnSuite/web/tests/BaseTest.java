package org.espnSuite.web.tests;

import org.espnSuite.web.drivers.Driver;
import org.espnSuite.web.pages.HomePage;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.log4j.Logger;

/**
 * This class defines common methods for all test defined in ESPNTest
 * @author: july.moreno
 * @version: 06/07/2020
 */

public class BaseTest {

    protected Driver driver;
    protected HomePage homePage;
    protected Logger log = Logger.getLogger(BaseTest.class);

	/**
	 * Open the browser and validate the web page under test is displayed
	 */

    @BeforeTest(alwaysRun = true)
    @Parameters({"browser","url","homePageTitle"})
    public void beforeTest(String browser, String url, String homePageTitle){
    	driver= new Driver(browser);
        driver.getDriver().manage().deleteAllCookies();
        driver.getDriver().manage().window().maximize();
        homePage = new HomePage(driver.getDriver(),url);
        Assert.assertEquals(homePageTitle,driver.getDriver().getTitle());
        log.info("Open Browser " + driver.getDriver().getTitle());
    }

	/**
	 * Close the browser
	 */
    @AfterTest(alwaysRun = true)
    public void afterTest(){
        homePage.dispose();
        log.info("Close Browser");
    }

    /**
     * Get the home page
     * @return homePage
     */
    public HomePage getHomePage() {
        return homePage;
    }


}
