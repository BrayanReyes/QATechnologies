package com.globant.web.tests;

import com.globant.web.pages.HomePage;
import com.globant.web.pages.hotels.HotelsSearchPage;
import com.globant.web.utils.TextValidations;
import com.globant.web.utils.HotelsParameters;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Hotels Test handles the test cases related with the process of booking a
 * Hotel
 */
public class HotelsMemberDiscountTest extends BaseTest {

	@Test(description = "Get Hotel member discount banner", dataProvider = "InputHotelData", dataProviderClass = com.globant.web.data.DataInit.class)

	public void getHotelMemberDiscountTest(HotelsParameters hotelsParameters) {
		HomePage home = getHomePage();

		// -----------------------------------------------------> Travelocity Home Page <-----------------------------------------------------//

		HotelsSearchPage hotelPage = home.selectHotelsTab();

		// -----------------------------------------------------> Hotels Search Page <-----------------------------------------------------//

		hotelPage.setGoingTo(hotelsParameters.getDestination());
		hotelPage.setCheckInDate(hotelsParameters.getCheckInForward());
		hotelPage.setCheckOutDate(hotelsParameters.getCheckOutForward());
		Assert.assertTrue(hotelPage.isMemberDiscountBannerPresent(), "DISCOUNT BANNER IS NOT PRESENT");
		Assert.assertTrue(hotelPage.isMemberDiscountTextPresent(), "MEMBER DISCOUNT TEXT IS NOT DISPLAYED");
		hotelPage.clickMemberDiscountLink();
		Assert.assertTrue(hotelPage.isMemberDiscountSecondaryTextPresent(), "MEMBER DISCOUNT SECONDARY TEXT IS NOT DISPLAYED");
		Assert.assertEquals(hotelPage.getMemberDiscountSecondaryText(), TextValidations.getMemberDiscountSecondText(), "MEMBER DISCOUNT SECONDARY TEXT DOES NOT MATCH");
		log.info("Member Discount Text 1: " + hotelPage.getMemberDiscountText());
		log.info("Member Discount Text 2: " + hotelPage.getMemberDiscountSecondaryText());
		hotelPage.setMemberDiscountEmail(hotelsParameters.getEmail());
		hotelPage.clickSignMeUpButtonButton();
		Assert.assertTrue(hotelPage.isSignedDiscountConfirmationMessagePresent(), "SIGNED DISCOUNT TEXT IS NOT DISPLAYED");
		Assert.assertTrue(hotelPage.isSignedDiscountFirstTextPresent(), "SIGNED DISCOUNT FIRST TEXT IS NOT DISPLAYED");
		Assert.assertTrue(hotelPage.isSignedDiscountSecondTextPresent(), "SIGNED DISCOUNT SECOND TEXT IS NOT DISPLAYED");
		Assert.assertEquals(hotelPage.getSignedDiscountConfirmationMessage(), TextValidations.getSignedDiscountConfirmationMessage(), "SIGNED MEMBER DISCOUNT MESSAGE  DOES NOT MATCH");
		Assert.assertEquals(hotelPage.getSignedDiscountFirstText(), TextValidations.getSignedDiscountFirstText(), "SIGNED MEMBER DISCOUNT FIRST TEXT DOES NOT MATCH");
		Assert.assertEquals(hotelPage.getSignedDiscountSecondText(), TextValidations.getSignedDiscountSecondText(), "SIGNED MEMBER DISCOUNT SECOND TEXT DOES NOT MATCH");
		log.info("Signed Member Discount Confirmation: " + hotelPage.getSignedDiscountConfirmationMessage());
		log.info("Signed Member Discount Text 1: " + hotelPage.getSignedDiscountFirstText());
		log.info("Signed Member Discount Text 2: " + hotelPage.getSignedDiscountSecondText());
		log.info("---------- Get Hotel member discount banner works properly ----------");

	}
}