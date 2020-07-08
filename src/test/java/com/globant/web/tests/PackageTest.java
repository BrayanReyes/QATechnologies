package com.globant.web.tests;

import com.globant.web.data.DataInit;
import com.globant.web.pages.HomePage;
import com.globant.web.pages.packages.*;
import com.globant.web.utils.TextValidations;
import com.globant.web.utils.PackagesParameters;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PackageTest extends BaseTest {


    @Test(description = "Booking a Flight and Partial Hotel", dataProvider = "InputPackageData",
            dataProviderClass = com.globant.web.data.DataInit.class, priority = 1, enabled = false)
    public void bookingPartialPackageTest(PackagesParameters packagesParameters) {
        HomePage home = getHomePage();

        //-----------------------------------------------------> Travelocity Home Page <-----------------------------------------------------//

        PackagesSearchPage vacationPackagePage = home.selectVacationPackagesTab();

        //-----------------------------------------------------> Vacation Package Search Page <-----------------------------------------------------//

        Assert.assertEquals(vacationPackagePage.getVacationPackageType(), TextValidations.getVacationPackageType(), "VACATION PACKAGE OPTION DOES NOT MATCH");
        vacationPackagePage.selectPackageType();
        vacationPackagePage.setFlyingFrom(packagesParameters.getFlyingFrom());
        vacationPackagePage.setFlyingTo(packagesParameters.getFlyingTo());
        vacationPackagePage.setDepartingDate(packagesParameters.getFlightDaysForward());
        vacationPackagePage.setReturningDate(packagesParameters.getFlightDaysToReturn());
        vacationPackagePage.filterPartOfStay();
        vacationPackagePage.setCheckInDate(packagesParameters.getCheckInDaysForward());
        vacationPackagePage.setCheckOutDate(packagesParameters.getCheckOutDaysToReturn());
        vacationPackagePage.clickSearchPackageButton();
        Assert.assertTrue(vacationPackagePage.isDatesErrorMessagePresent(), "ERROR MESSAGE IS NOT DISPLAYED");
        Assert.assertEquals(vacationPackagePage.getDatesErrorMessage(), TextValidations.getPackageAlertMessage(), "DATES ERROR MESSAGE IS NOT THE SAME");
        log.info("Error Message: " + vacationPackagePage.getDatesErrorMessage());
    }


    @Test(description = "Booking a Flight, Hotel and a Car", dataProvider = "InputFlightHotelCarData",
            dataProviderClass = DataInit.class, priority = 2, enabled = true)
    public void bookingFlightHotelCarTest(PackagesParameters searchFlightHotelCar) {
        HomePage home = getHomePage();

        //-----------------------------------------------------> Travelocity Home Page <-----------------------------------------------------//

        PackagesSearchPage packageSearchPage = home.selectVacationPackagesTab();

        //-----------------------------------------------------> Vacation Package Search Page <-----------------------------------------------------//

        Assert.assertEquals(packageSearchPage.getVacationPackageType(), TextValidations.getVacationPackageType(), "VACATION PACKAGE OPTION DOES NOT MATCH");
        packageSearchPage.selectPackageType();
        packageSearchPage.setFlyingFrom(searchFlightHotelCar.getFlyingFrom());
        packageSearchPage.setFlyingTo(searchFlightHotelCar.getFlyingTo());
        packageSearchPage.setDatesToDepart(searchFlightHotelCar.getFlightDaysForward());
        packageSearchPage.setReturningDate(searchFlightHotelCar.getFlightDaysToReturn());
        packageSearchPage.setAdultsQuantity(searchFlightHotelCar.getAdultsNumber());
        PackageResultPage packageResultPage = packageSearchPage.clickSearchPackageButton();

        //-----------------------------------------------------> Package Results Page <-----------------------------------------------------//

        Assert.assertTrue(packageResultPage.isHotelResultHeaderPresent(),"PAGE HEADER IS NOT PRESENT");
        Assert.assertTrue(packageResultPage.isHotelResultContainerPresent(),"RESULT CONTAINER IS NOT DISPLAYED");
        Assert.assertTrue(packageResultPage.isOriginLabelPresent(),"ORIGIN LABEL IS NOT DISPLAYED");
        Assert.assertTrue(packageResultPage.isDestinationLabelPresent(),"DESTINATION LABEL IS NOT DISPLAYED");
        Assert.assertFalse(packageResultPage.isTravelersLabelPresent(),"TRAVELERS LABEL IS DISPLAYED");
        log.info("Package Results Page Header: " + packageResultPage.getHotelResultHeader());
        log.info("Origin: " + packageResultPage.getOriginLabel());
        log.info("Destination: " + packageResultPage.getDestinationLabel());
//        log.info("Travelers: " + packageResultPage.getTravelersLabel());
        Assert.assertTrue(packageResultPage.isSortContainerPresent(),"SORT CONTAINER IS NOT DISPLAYED");
        packageResultPage.sortByPrice();
        log.info("Is sorted by price: " +  packageResultPage.sortByPrice());
        packageResultPage.filterByStarts();
        Assert.assertTrue(packageResultPage.isFilterMarkPresent(),"FILTER MARK IS NOT DISPLAYED");
        log.info("Hotel Chose: " + packageResultPage.chooseFirstOption());
        log.info("Hotel Name: " + packageResultPage.getHotelName());
        log.info("Hotel Price: " + packageResultPage.getHotelPrice());
        log.info("Number of Stars: " + packageResultPage.getNumberOfStars());
        RoomDetailsPage roomDetailsPage = packageResultPage.clickHotelNameLink();

        //-----------------------------------------------------> Room Details Page <-----------------------------------------------------//

        Assert.assertTrue(roomDetailsPage.hotelNameIsTheSame(),"HOTEL NAME IS NOT THE SAME THAN PREVIOUS PAGE");
        Assert.assertTrue(roomDetailsPage.hotelPriceIsTheSame(),"HOTEL PRICE IS NOT THE SAME THAN PREVIOUS PAGE");
        Assert.assertTrue(roomDetailsPage.starsTextIsTheSame(),"STARS LABEL IS NOT THE SAME THAN PREVIOUS PAGE");
        log.info("Hotel Name: " + roomDetailsPage.getHotelNameLabel());
        log.info("Hotel Price: " + roomDetailsPage.getHotelPriceLabel());
        log.info("Stars Label: " + roomDetailsPage.getStarsLabel());
        Assert.assertTrue(roomDetailsPage.roomsDivIsPresent(),"ROOMS DIV IS NOT DISPLAYED");
        Assert.assertEquals(roomDetailsPage.getChooseRoomHeader(), TextValidations.getChooseRoomPageHeader(), "ROOMS PAGE HEADER DOES NOT MATCH");
        log.info("Rooms Section Header: " + roomDetailsPage.getChooseRoomHeader());
        roomDetailsPage.selectFirstRoom();
        SelectFlightsPage selectFlightPage = roomDetailsPage.goToSelectFlightsPage();

        //-----------------------------------------------------> Select Flights Page <-----------------------------------------------------//

        Assert.assertTrue(selectFlightPage.flightsHeaderIsPresent(),"DEPARTURE HEADER IS NOT PRESENT");
        selectFlightPage.selectFlights(3);
        SummaryPackagePage summaryPage = selectFlightPage.goToSummaryPackagePage();

         //-----------------------------------------------------> Summary Package Page <-----------------------------------------------------//

        Assert.assertTrue(summaryPage.isSummaryPageHeaderPresent(),"SUMMARY PAGE HEADER IS NOT PRESENT");
        log.info("Summary Page Header: " + summaryPage.getSummaryPageHeaderText());
        Assert.assertTrue(summaryPage.isTransportationLinkPresent(),"ADD TRANSPORTATION LINK IS NOT PRESENT");
        summaryPage.clickAddTransportationLink();
        Assert.assertTrue(summaryPage.isTransportationModulePresent(),"TRANSPORTATION MODULE IS NOT PRESENT");
        summaryPage.clickAddCarToTripButton();
        Assert.assertTrue(summaryPage.isAddedToTripMarkPresent(),"CAR ADDED TO TRIP MARK IS NOT DISPLAYED");
        Assert.assertEquals(summaryPage.getAddedToTripMarkText(), TextValidations.getCarAddedToTripMark(),"CAR ADDED TO TRIP MARK DOES NOT MATCH");
        log.info("Car added confirmation: " + summaryPage.getAddedToTripMarkText());
        Assert.assertTrue(summaryPage.isFlightSummaryDivPresent(),"FLIGHT SUMMARY SECTION IS NOT DISPLAYED");
        Assert.assertTrue(summaryPage.isFlightPackageSummaryDivPresent(),"FLIGHT PACKAGE SUMMARY SECTION IS NOT DISPLAYED");
        Assert.assertTrue(summaryPage.isHotelSummaryDivPresent(),"HOTEL SUMMARY SECTION IS NOT DISPLAYED");
        Assert.assertTrue(summaryPage.isTransportationSummaryDivPresent(),"TRANSPORTATION SUMMARY SECTION IS NOT DISPLAYED");
        Assert.assertTrue(summaryPage.isPriceSummaryDivPresent(),"PRICE SUMMARY SECTION IS NOT DISPLAYED");
        log.info("Is Flight Summary Section displayed: " + summaryPage.isFlightSummaryDivPresent());
        log.info("Is Flight Package Summary Section displayed: " + summaryPage.isFlightPackageSummaryDivPresent());
        log.info("Is Hotel Summary Section displayed: " + summaryPage.isHotelSummaryDivPresent());
        log.info("Is Transportation Summary Section displayed: " + summaryPage.isTransportationSummaryDivPresent());
        log.info("Is Price Summary Section displayed: " + summaryPage.isPriceSummaryDivPresent());
        ReviewAndBookPage reviewPage = summaryPage.clickNextFinalDetailsButton();

        //-----------------------------------------------------> Review And Book Page <-----------------------------------------------------//

        Assert.assertEquals(reviewPage.getReviewAndBookPageHeader(), TextValidations.getReviewAndBookPageHeader(), "REVIEW AND BOOK PAGE HEADER DOES NOT MATCH");
        log.info("Review and Book Page Header: " + reviewPage.getReviewAndBookPageHeader());
        Assert.assertTrue(reviewPage.isTripPreferencesSectionPresent(),"TRIP PREFERENCES SECTION IS NOT DISPLAYED");
        Assert.assertTrue(reviewPage.isTripSummarySectionPresent(),"TRIP SUMMARY SECTION IS NOT DISPLAYED");
        Assert.assertEquals(reviewPage.getFlightSectionHeader(), TextValidations.getFlightSectionHeader(), "FLIGHT SECTION HEADER DOES NOT MATCH");
        log.info("Flight Section Header: " + reviewPage.getFlightSectionHeader());
        Assert.assertTrue(reviewPage.isFlightSectionTitlePresent(),"FLIGHT SECTION TITLE IS NOT DISPLAYED");
        log.info("Flight Section Title: " + reviewPage.getFlightSectionTitle());
        reviewPage.setFirstName(searchFlightHotelCar.getFirstName());
        reviewPage.setLastName(searchFlightHotelCar.getLastName());
        Assert.assertEquals(reviewPage.getHotelSectionHeader(), TextValidations.getHotelSectionHeader(), "HOTEL SECTION HEADER DOES NOT MATCH");
        log.info("Hotel Section Header: " + reviewPage.getHotelSectionHeader());
        Assert.assertTrue(reviewPage.isHotelSectionTitlePresent(),"HOTEL SECTION TITLE IS NOT DISPLAYED");
        log.info("Hotel Section Title: " + reviewPage.getHotelSectionTitle());
        Assert.assertTrue(reviewPage.isHotelContactNameInputPresent(),"HOTEL CONTACT INPUT IS NOT DISPLAYED");
        Assert.assertEquals(reviewPage.getTransportationSectionHeader(), TextValidations.getTransportationSectionHeader(), "TRANSPORTATION SECTION HEADER DOES NOT MATCH");
        log.info("Transportation Section Header: " + reviewPage.getTransportationSectionHeader());
        Assert.assertTrue(reviewPage.isTransportationSectionTitlePresent(),"TRANSPORTATION SECTION TITLE IS NOT DISPLAYED");
        log.info("Transportation Section Title: " + reviewPage.getTransportationSectionTitle());
        Assert.assertTrue(reviewPage.isTransportationContactInputPresent(),"TRANSPORTATION CONTACT INPUT IS NOT DISPLAYED");
        Assert.assertEquals(reviewPage.getFlightTravelersData(), TextValidations.getFlightTravelersLabel(), "FLIGHT TRAVELERS DATA DOES NOT MATCH");
        Assert.assertEquals(reviewPage.getFlightLocationData(), TextValidations.getFlightLocationLabel(), "FLIGHT LOCATION DOES NOT MATCH");
        Assert.assertEquals(reviewPage.getHotelGuestsData(), TextValidations.getHotelGuestsLabel(), "HOTEL GUESTS DOES NOT MATCH");
        Assert.assertTrue(reviewPage.isHotelLocationDataPresent(),"HOTEL LOCATION DATA IS NOT DISPLAYED");
        Assert.assertEquals(reviewPage.getTransportationGuestsData(), TextValidations.getTransportationGuestsLabel(), "TRANSPORTATION GUESTS DOES NOT MATCH");
        Assert.assertEquals(reviewPage.getTransportationLocationData(), TextValidations.getTransportationLocationLabel(), "TRANSPORTATION LOCATION DOES NOT MATCH");
        Assert.assertTrue(reviewPage.isPriceLabelPresent(),"PRICE LABEL IS NOT DISPLAYED");
        log.info("******************* FINAL ASSERTS *******************");
        log.info("Flight Travelers: " + reviewPage.getFlightTravelersData());
        log.info("Flight Location: " + reviewPage.getFlightLocationData());
        log.info("Hotel Guests: " + reviewPage.getHotelGuestsData());
        log.info("Hotel Location: " + reviewPage.getHotelLocationData());
        log.info("Transportation Guests: " + reviewPage.getTransportationGuestsData());
        log.info("Transportation Location: " + reviewPage.getTransportationLocationData());
        log.info("Price Label: " + reviewPage.getTotalPriceLabel());

    }
}