package com.automation.web.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.web.pages.TravelocityHomePage;

/**
 * Class for the Travel test.
 * 
 * @author juan.montes
 */
public class TestSuite extends BaseTest {


	@BeforeMethod(alwaysRun = true)
	public void beforesearchFlightTest() {
		TravelocityHomePage homePage = getHomePage();
		homePage.flightsTab();
	}

	@Test(description = "Searching Flight")
	@Parameters({"alertMessage"})
	public void searchFlight(String alertMessage) {
		TravelocityHomePage home = getHomePage();
		home.setFlightType();
		home.setFlyingFrom("LAS");
		home.setFlyingTo("LAX");
		home.setDepartingDate();
		home.setReturningDate();
		home.setAdults("4");
		home.setChildren("5");
		home.setChildrenAge("0", "10", "1", "2", "3");
		home.searchFlight();
		Assert.assertEquals(home.getAlertMessage(), alertMessage, "Error Alert is not the same: " + alertMessage);

	}

	@AfterMethod(alwaysRun = true)
	@Parameters({ "url" })
	public void afterSearchFlight(String url) {
		getHomePage().switchToDefaultContent();
		driver.getDriver().get(url);
		log.info("Reload Home Page");
	}

}
