package org.suiteTest.web.iframes.tests;

import org.suiteTest.web.iframes.drivers.Driver;
import org.suiteTest.web.iframes.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.log4j.Logger;

/**
 * Class base for test
 * @author Pinguin
 */
public class BaseTest {

    protected Driver driver;
    private HomePage homePage;
    protected Logger log = Logger.getLogger(BaseTest.class);

    @BeforeTest(alwaysRun = true)
    @Parameters({"browser","url"})
    public void beforeTest(String browser,String url){
        driver = new Driver(browser);
        driver.getDriver().manage().window().maximize();
        homePage = new HomePage(driver.getDriver(),url);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        homePage.dispose();
    }

    /**
     * Get the home page
     * @return homePage
     */
    public HomePage getHomePage() {
        return homePage;
    }

}
