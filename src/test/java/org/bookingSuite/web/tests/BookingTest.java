package org.bookingSuite.web.tests;

import org.bookingSuite.web.pages.*;
import org.testng.annotations.Test;

public class BookingTest extends BaseTest{


    @Test(description = "July es muy sexy!")
    public void searchTest(){
        BookingHomePage home = getHomePage();
        StaysPage staysPage = home.selectStayOption();
        staysPage.enterDestination("Bogotá, Colombia");
        staysPage.setDatesSearch();
        staysPage.setFamilyInfo(3,2,"9","2");
        HotelsResultPage hotelsResultPage= staysPage.searchAccomodation();
        hotelsResultPage.setStarsFilter("5");
        hotelsResultPage.checkResults();
        HotelDetailsPage hotelDetailsPage=hotelsResultPage.clickOption(2);
        hotelDetailsPage.bookRoom();
        hotelDetailsPage.setNumberOfRooms("1");
        BookingDataPage bookingDataPage = hotelDetailsPage.clickFinalReserve();
        bookingDataPage.enterLastName("Kings");
        bookingDataPage.enterEmail("kingsofkings@gmail.com");
        bookingDataPage.enterEmail("kingsofkings@gmail.com");
        bookingDataPage.bookHotel();
    }
}
