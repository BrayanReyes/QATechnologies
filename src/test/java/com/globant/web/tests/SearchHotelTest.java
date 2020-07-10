package com.globant.web.tests;

import com.globant.web.pages.HomePage;
import com.globant.web.pages.hotels.HotelDetailsPage;
import com.globant.web.pages.hotels.HotelsResultPage;
import com.globant.web.pages.hotels.HotelsSearchPage;
import com.globant.web.utils.HotelsParameters;
import com.globant.web.utils.TextValidations;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Hotels Test handles the test cases related with the process of booking a Hotel
 */
public class SearchHotelTest extends BaseTest {

    @Test(description = "Verify that search by hotel name works properly",
            dataProvider = "InputHotelData",
            dataProviderClass = com.globant.web.data.DataInit.class)

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

        //resultPage.handleDiscountModal();
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
        log.info("Search by hotel name works properly");
    }

}