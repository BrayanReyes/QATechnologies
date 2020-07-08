package com.globant.web.tests;

import com.globant.web.pages.HomePage;
import com.globant.web.pages.hotels.HotelDetailsPage;
import com.globant.web.pages.hotels.HotelsResultPage;
import com.globant.web.pages.hotels.HotelsSearchPage;
import com.globant.web.utils.TextValidations;
import com.globant.web.utils.HotelsParameters;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelsTest extends BaseTest {

    @Test(description = "Booking a Hotel", dataProvider = "InputHotelData",
            dataProviderClass = com.globant.web.data.DataInit.class,
            priority = 2,enabled = false)
    public void searchHotelByNameTest(HotelsParameters hotelsParameters) {
        HomePage home = getHomePage();

        //-----------------------------------------------------> Travelocity Home Page <-----------------------------------------------------//

        HotelsSearchPage hotelPage = home.selectHotelsTab();

        //-----------------------------------------------------> Hotels Search Page <-----------------------------------------------------//

        hotelPage.setGoingTo(hotelsParameters.getDestination());
        hotelPage.setCheckInDate(hotelsParameters.getCheckInForward());
        hotelPage.setCheckOutDate(hotelsParameters.getCheckOutForward());
        HotelsResultPage resultPage = hotelPage.clickSearchHotelsButton();

        //-----------------------------------------------------> Hotels Result Page <-----------------------------------------------------//

        resultPage.handleDiscountModal();
        Assert.assertTrue(resultPage.isPropertyNameFilterPresent(), "PROPERTY NAME FILTER IS NOT DISPLAYED");
        resultPage.clickPropertyNameFilterButton();
        resultPage.setPropertyName(hotelsParameters.getHotelName());
        Assert.assertTrue(resultPage.isHotelNameFilterMarkPresent(), "FILTER NAME MARK IS NOT DISPLAYED");
        Assert.assertTrue(resultPage.isHotelNamePresent(), "HOTEL NAME LABEL IS NOT DISPLAYED");
        Assert.assertTrue(resultPage.isHotelPricePresent(), "PRICE LABEL IS NOT DISPLAYED");
        log.info("Hotel Name: " + resultPage.getHotelName());
        log.info("Hotel Price: " + resultPage.getHotelPrice());
        HotelDetailsPage hotelDetailsPage = resultPage.clickHotelNameLink();

        //-----------------------------------------------------> Hotel Details Page <-----------------------------------------------------//

        Assert.assertTrue(hotelDetailsPage.isHotelNamePresent(), "HOTEL NAME IS NOT DISPLAYED");
        Assert.assertTrue(hotelDetailsPage.isAmenitiesGridPresent(), "AMENITIES GRID IS NOT DISPLAYED");
        Assert.assertTrue(hotelDetailsPage.isMembersDiscountBannerPresent(), "MEMBER DISCOUNT BANNER IS NOT DISPLAYED");
        Assert.assertTrue(hotelDetailsPage.isSignUpLinkPresent(), "SIGN UP LINK IS NOT DISPLAYED");
        log.info("Hotel Name: " + hotelDetailsPage.getHotelName());
        log.info("Is Amenities Grid displayed?: " + hotelDetailsPage.isAmenitiesGridPresent());
        log.info("Is Member Discount Banner displayed?: " + hotelDetailsPage.isAmenitiesGridPresent());
        log.info("Discount Banner Link: " + hotelDetailsPage.getSignUpLinkText());
    }


    @Test(description = "Get Hotel member discount",
            dataProvider = "InputHotelData",dataProviderClass = com.globant.web.data.DataInit.class,
    priority = 1, enabled = true)
    public void getHotelMemberDiscountTest(HotelsParameters hotelsParameters) {
        HomePage home = getHomePage();

        //-----------------------------------------------------> Travelocity Home Page <-----------------------------------------------------//

        HotelsSearchPage hotelPage = home.selectHotelsTab();

        //-----------------------------------------------------> Hotels Search Page <-----------------------------------------------------//

        hotelPage.setGoingTo(hotelsParameters.getDestination());
        hotelPage.setCheckInDate(hotelsParameters.getCheckInForward());
        hotelPage.setCheckOutDate(hotelsParameters.getCheckOutForward());
        Assert.assertTrue(hotelPage.isMemberDiscountBannerPresent(),"DISCOUNT BANNER IS NOT PRESENT");
        Assert.assertTrue(hotelPage.isMemberDiscountTextPresent(),"MEMBER DISCOUNT TEXT IS NOT DISPLAYED");
        hotelPage.clickMemberDiscountLink();
        Assert.assertTrue(hotelPage.isMemberDiscountSecondaryTextPresent(),"MEMBER DISCOUNT SECONDARY TEXT IS NOT DISPLAYED");
        Assert.assertEquals(hotelPage.getMemberDiscountSecondaryText(), TextValidations.getMemberDiscountSecondText(),"MEMBER DISCOUNT SECONDARY TEXT DOES NOT MATCH");
        log.info("Member Discount Text 1: " + hotelPage.getMemberDiscountText());
        log.info("Member Discount Text 2: " + hotelPage.getMemberDiscountSecondaryText());
        hotelPage.setMemberDiscountEmail(hotelsParameters.getEmail());
        hotelPage.clickSignMeUpButtonButton();
        Assert.assertTrue(hotelPage.isSignedDiscountConfirmationMessagePresent(),"SIGNED DISCOUNT TEXT IS NOT DISPLAYED");
        Assert.assertTrue(hotelPage.isSignedDiscountFirstTextPresent(),"SIGNED DISCOUNT FIRST TEXT IS NOT DISPLAYED");
        Assert.assertTrue(hotelPage.isSignedDiscountSecondTextPresent(),"SIGNED DISCOUNT SECOND TEXT IS NOT DISPLAYED");
        Assert.assertEquals(hotelPage.getSignedDiscountConfirmationMessage(), TextValidations.getSignedDiscountConfirmationMessage(),"SIGNED MEMBER DISCOUNT MESSAGE  DOES NOT MATCH");
        Assert.assertEquals(hotelPage.getSignedDiscountFirstText(), TextValidations.getSignedDiscountFirstText(),"SIGNED MEMBER DISCOUNT FIRST TEXT DOES NOT MATCH");
        Assert.assertEquals(hotelPage.getSignedDiscountSecondText(), TextValidations.getSignedDiscountSecondText(),"SIGNED MEMBER DISCOUNT SECOND TEXT DOES NOT MATCH");
        log.info("Signed Member Discount Confirmation: " + hotelPage.getSignedDiscountConfirmationMessage());
        log.info("Signed Member Discount Text 1: " + hotelPage.getSignedDiscountFirstText());
        log.info("Signed Member Discount Text 2: " + hotelPage.getSignedDiscountSecondText());

    }
}