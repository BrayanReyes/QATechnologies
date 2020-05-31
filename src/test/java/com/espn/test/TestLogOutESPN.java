package com.espn.test;

import org.apache.log4j.Logger;
import org.testng.annotations.*;

public class TestLogOutESPN {

    private Logger log = Logger.getLogger(TestLoginESPN.class);

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        log.info("@BeforeSuite -> Web page available");
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest(){
        log.info("@BeforeTest -> User register in ESPN webpage");
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        log.info("@BeforeClass -> Valid user and password ");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(){
        log.info("@BeforeMethod -> Open web browser");
        log.info("@BeforeMethod -> Go to user Web page. ");
    }

    @Test
    public void LogOutTest() {
        log.info("@Test -> Click LogOut button");
        log.info("@Test -> Check user is not logged");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        log.info("@AfterMethod -> Close browser.");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass (){
        log.info("@AfterClass -> Power off");
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        log.info("@AfterTest -> End Test xml");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
        log.info("@AfterSuite -> End the suite");
    }
}
