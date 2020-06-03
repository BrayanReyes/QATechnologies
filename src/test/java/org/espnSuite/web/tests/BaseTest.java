package org.espnSuite.web.tests;

import org.espnSuite.web.drivers.Driver;
import org.espnSuite.web.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.log4j.Logger;

public class BaseTest {

    protected Driver driver;
    private HomePage homePage;
    protected Logger log = Logger.getLogger(BaseTest.class);

    @BeforeTest(alwaysRun = true)
    @Parameters({"browser","url","title"})
    public void beforeTest(String browser, String url, String tittle){
        driver= new Driver(browser);
        driver.getDriver().manage().deleteAllCookies();
        driver.getDriver().manage().window().maximize();
        homePage = new HomePage(driver.getDriver(),url);
        Assert.assertEquals(tittle,driver.getDriver().getTitle());
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
