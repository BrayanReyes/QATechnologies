package com.globant.web.tests;


import com.globant.web.pages.HomePage;
import com.globant.web.pages.flights.FlightsResultPage;
import com.globant.web.pages.flights.FlightsSearchPage;
import com.globant.web.pages.flights.PaymentFlightPage;
import com.globant.web.pages.flights.ReviewYourTripPage;
import com.globant.web.utils.TextValidations;
import com.globant.web.utils.FlightsParameters;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightsTest extends BaseTest{

    @Test(description = "Booking a Flight till the payment page",dataProvider="InputFlightsData",
            dataProviderClass = com.globant.web.data.DataInit.class)
    public void bookingFlightTest(FlightsParameters flightsParameters){
        HomePage home = getHomePage();

        //-----------------------------------------------------> Travelocity Home Page <-----------------------------------------------------//

        FlightsSearchPage flightsPage = home.selectFlightsTab();

        //-----------------------------------------------------> Flights Search Page <-----------------------------------------------------//

        Assert.assertEquals(flightsPage.getFlyingTypeText(), TextValidations.getFlightsPageLabel(),"FLIGHTS LABEL DOES NOT MATCH");
        flightsPage.selectFlightType();
        flightsPage.setFlyingFrom(flightsParameters.getFlyingFrom());
        flightsPage.setFlyingTo(flightsParameters.getFlyingTo());
        flightsPage.setDatesToDepart(flightsParameters.getDaysForward());
//        flightsPage.setDepartingDate(flightsParameters.getDaysForward());
        flightsPage.setReturningDate(flightsParameters.getDaysToReturn());
        flightsPage.setAdultsQuantity(flightsParameters.getNumberOfAdults());
        flightsPage.setChildrenQuantity(flightsParameters.getNumberOfChildren());
        flightsPage.setChildrenAge(flightsParameters.getChildrenAge());
        FlightsResultPage resultPage = flightsPage.clickSearchFlightButton();

        //-----------------------------------------------------> Flights Result Page <-----------------------------------------------------//

        Assert.assertEquals(resultPage.getFlightPageHeader(), TextValidations.getDepartureFlightPageHeader(),"DEPARTURE HEADER DOES NOT MATCH");
        log.info("Departure Flights Header: " + resultPage.getFlightPageHeader());
        Assert.assertTrue(resultPage.isSortDropdownPresent(),"SORT DROPDOWN IS NOT DISPLAYED");
        Assert.assertTrue(resultPage.isSelectButtonPresentInEachDivResult(),"SELECT BUTTON IS NOT PRESENT IN EACH ITEM");
        log.info("Is \"Select\" button present in each item?: " + resultPage.isSelectButtonPresentInEachDivResult());
        Assert.assertTrue(resultPage.isFlightDurationPresentInEachDivResult(),"FLIGHT DURATION LABEL IS NOT PRESENT IN EACH ITEM");
        log.info("Is \"Flight Duration\" label present in each item?: " + resultPage.isFlightDurationPresentInEachDivResult());
        Assert.assertTrue(resultPage.isFlightDetailsAndFeesPresentInEachDivResult(),"FLIGHT DETAILS AND BAGGAGE FEES LABEL IS NOT PRESENT IN EACH ITEM");
        log.info("Is \"Flight Details and Baggage Fees\" label present in each item?: " + resultPage.isFlightDetailsAndFeesPresentInEachDivResult());
        resultPage. sortResultsBy(flightsParameters.getDuration());
        Assert.assertTrue(resultPage.validateFlightsListOrder(),"RESULTS ARE NOT SORTED PROPERLY BY DURATION");
        log.info("Are Flight results ordered by duration?: " + resultPage.validateFlightsListOrder());

      resultPage.selectDepartingFLightAt(1);

       //resultPage.selectFlight(0);
       // resultPage.selectFirstFlight();

        Assert.assertEquals(resultPage.getFlightPageHeader(), TextValidations.getReturnFlightPageHeader(),"RETURN HEADER DOES NOT MATCH");
        log.info("Return Flights Header: " + resultPage.getFlightPageHeader());

        //ReviewYourTripPage yourTripPage = resultPage.selectReturningFlight(3);
        resultPage.selectReturningFLightAt(2);

        //resultPage.selectReturnFlight(flightsParameters.getReturnFlightOption());
       // resultPage.handleForceHotelModal();
        ReviewYourTripPage yourTripPage = resultPage.goToReviewYourTripPage();

        //-----------------------------------------------------> Review Your Trip Page <-----------------------------------------------------//

        Assert.assertEquals(yourTripPage.getReviewYourTripHeader(), TextValidations.getReviewYourPagePageHeader(),"REVIEW YOUR PAGE HEADER DOES NOT MATCH");
        log.info("Review Your Trip Header: " + yourTripPage.getReviewYourTripHeader());
        Assert.assertTrue(yourTripPage.isPriceContainerPresent(),"PRICE CONTAINER IS NOT DISPLAYED");
        Assert.assertTrue(yourTripPage.isPriceLabelPresent(),"PRICE LABEL IS NOT DISPLAYED");
        log.info("Total Price: " + yourTripPage.getTotalPriceLabel());
        Assert.assertTrue(yourTripPage.isPriceGuaranteeLabelPresent(),"PRICE GUARANTEE LABEL IS NOT DISPLAYED");
        log.info("Price Guarantee Label: " + yourTripPage.getPriceGuaranteeLabel());
        log.info("Is Price Guarantee Label present?: " + yourTripPage.isPriceGuaranteeLabelPresent());

        Assert.assertTrue(yourTripPage.isDepartDivPresent(),"DEPART DIV IS NOT DISPLAYED");
        Assert.assertTrue(yourTripPage.isReturnDivPresent(),"RETURN DIV IS NOT DISPLAYED");
        log.info("Is Depart Div present? " + yourTripPage.isDepartDivPresent());
        log.info("Is Return Div present? " + yourTripPage.isReturnDivPresent());
        PaymentFlightPage paymentPage = yourTripPage.clickContinueBookingButton();

        //-----------------------------------------------------> Review Your Trip Page <-----------------------------------------------------//

        Assert.assertEquals(paymentPage.getPaymentFlightHeader(), TextValidations.getPaymentFlightPageHeader(),"PAYMENT FLIGHT PAGE HEADER DOES NOT MATCH");
        log.info("Payment Flight Header: " + paymentPage.getPaymentFlightHeader());
        Assert.assertTrue(paymentPage.isPreferencesSectionPresent(),"PREFERENCES SECTION IS NOT DISPLAYED");
        Assert.assertEquals(paymentPage.getPreferencesSectionTitle(), TextValidations.getPreferencesSectionTitle(),"PREFERENCES SECTION TITLE DOES NOT MATCH");
        log.info("Preferences Title: " + paymentPage.getPreferencesSectionTitle());
        Assert.assertTrue(paymentPage.isPaymentSectionPresent(),"PAYMENT SECTION IS NOT DISPLAYED");
        Assert.assertEquals(paymentPage.getPaymentSectionTitle(), TextValidations.getPaymentSectionTitle(),"PAYMENT SECTION TITLE DOES NOT MATCH");
        log.info("Preferences Title: " + paymentPage.getPaymentSectionTitle());
        Assert.assertTrue(paymentPage.isSummaryFlightSectionPresent(),"SUMMARY FLIGHT SECTION IS NOT DISPLAYED");
        Assert.assertTrue(paymentPage.isPriceGuaranteeAlertPresent(),"PRICE GUARANTEE ALERT IS NOT DISPLAYED");
        Assert.assertTrue(paymentPage.isCompleteBookingButtonPresent(),"COMPLETE BOOKING BUTTON IS NOT DISPLAYED");
        log.info("Is Summary Flight Section displayed?: " + paymentPage.isSummaryFlightSectionPresent());
        log.info("Is Price Guarantee Alert displayed?: " + paymentPage.isPriceGuaranteeAlertPresent());
        log.info("Is \"Continue Booking\" button displayed?: " + paymentPage.isCompleteBookingButtonPresent());


    }
}
