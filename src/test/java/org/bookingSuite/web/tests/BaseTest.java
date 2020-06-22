package org.bookingSuite.web.tests;

import org.bookingSuite.web.pages.BookingHomePage;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.log4j.Logger;
import org.bookingSuite.web.drivers.Driver;
import org.bookingSuite.web.pages.HomePage;

/**
 * This class defines common methods for all test defined in ESPNTest
 * @author: july.moreno
 * @version: 07/06/2020
 */

public class BaseTest {

    protected Driver driver;
    protected BookingHomePage homePage;
    protected Logger log = Logger.getLogger(BaseTest.class);

	/**
	 * Open the browser and validate the web page under test is displayed
	 */

    @BeforeTest(alwaysRun = true)
    @Parameters({"browser","url"})
    public void beforeTest(String browser, String url){
    	driver= new Driver(browser);
        driver.getDriver().manage().deleteAllCookies();
        driver.getDriver().manage().window().maximize();
        homePage = new BookingHomePage(driver.getDriver(),url);
        log.info("Log Info: Opening Browser " + driver.getDriver().getTitle());
    }

	/**
	 * Close the browser
	 */
    @AfterTest(alwaysRun = true)
    public void afterTest(){
        homePage.dispose();
        log.info("Log Info: Closing Browser");
    }

    /**
     * Get the home page
     * @return homePage
     */
    public BookingHomePage getHomePage() {
        return homePage;
    }


}
