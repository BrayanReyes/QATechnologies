package com.globant.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.globant.web.pages.HomePage;
import com.globant.web.pages.flights.FlightsResultPage;
import com.globant.web.pages.flights.FlightsSearchPage;
import com.globant.web.pages.flights.PaymentFlightPage;
import com.globant.web.pages.flights.ReviewYourTripPage;
import com.globant.web.utils.FlightsParameters;
import com.globant.web.utils.TextValidations;

/**
 * Flights Test handles the process of booking a flight till the complete credit
 * card information page.
 */

public class FlightsTest extends BaseTest {

	@Test(description = "Booking a Flight till the payment page", dataProvider = "InputFlightsData", dataProviderClass = com.globant.web.data.DataInit.class)

	public void bookingFlightTest(FlightsParameters flightsParameters) {

		HomePage home = getHomePage();

		// -----------------------------------------------------> Travelocity Home Page <-----------------------------------------------------//

		FlightsSearchPage flightsPage = home.selectFlightsTab();

		// -----------------------------------------------------> Flights Search Page <-----------------------------------------------------//

		Assert.assertEquals(flightsPage.getFlyingTypeText(), TextValidations.getFlightsPageLabel(), "FLIGHTS LABEL DOES NOT MATCH");
		flightsPage.selectFlightType();
		flightsPage.setFlyingFrom(flightsParameters.getFlyingFrom());
		flightsPage.setFlyingTo(flightsParameters.getFlyingTo());
		flightsPage.setDepartingDate(flightsParameters.getFlightDaysForward());
		flightsPage.setReturningDate(flightsParameters.getFlightDaysToReturn());
		flightsPage.setAdultsQuantity(flightsParameters.getNumberOfAdults());
		flightsPage.setChildrenQuantity(flightsParameters.getNumberOfChildren());
		flightsPage.setChildrenAge(flightsParameters.getChildrenAge());
		FlightsResultPage flightsresultPage = flightsPage.clickSearchFlightButton();

		// -----------------------------------------------------> Flights Result Page <-----------------------------------------------------//

		Assert.assertEquals(flightsresultPage.getFlightPageHeader(), TextValidations.getDepartureFlightPageHeader(), "DEPARTURE HEADER DOES NOT MATCH");
		Assert.assertTrue(flightsresultPage.isSortDropdownPresent(), "SORT DROPDOWN IS NOT DISPLAYED");
		Assert.assertTrue(flightsresultPage.isSelectButtonPresentInEachDivResult(), "SELECT BUTTON IS NOT PRESENT IN EACH ITEM");
		Assert.assertTrue(flightsresultPage.isFlightDurationPresentInEachDivResult(), "FLIGHT DURATION LABEL IS NOT PRESENT IN EACH ITEM");
		Assert.assertTrue(flightsresultPage.isFlightDetailsAndFeesPresentInEachDivResult(), "FLIGHT DETAILS AND BAGGAGE FEES LABEL IS NOT PRESENT IN EACH ITEM");
		log.info("Departure Flights Page Header: " + flightsresultPage.getFlightPageHeader());
		log.info("Is \"Select\" button present in each item?: " + flightsresultPage.isSelectButtonPresentInEachDivResult());
		log.info("Is \"Flight Duration\" label present in each item?: " + flightsresultPage.isFlightDurationPresentInEachDivResult());
		log.info("Is \"Flight Details and Baggage Fees\" label present in each item?: " + flightsresultPage.isFlightDetailsAndFeesPresentInEachDivResult());
		flightsresultPage.sortResultsBy(flightsParameters.getFlightsSortCriteria());
		Assert.assertTrue(flightsresultPage.validateFlightsListOrder(), "RESULTS ARE NOT SORTED PROPERLY BY DURATION");
		log.info("Are Flight results ordered by duration?: " + flightsresultPage.validateFlightsListOrder());
		log.info("Departure Flights Page Header: " + flightsresultPage.getFlightPageHeader());
		flightsresultPage.selectDepartingFLight(flightsParameters.getDepartureFlightOption());
		Assert.assertEquals(flightsresultPage.getFlightPageHeader(), TextValidations.getReturnFlightPageHeader(), "RETURN HEADER DOES NOT MATCH");
		log.info("Return Flights Page Header: " + flightsresultPage.getFlightPageHeader());
		flightsresultPage.selectReturningFLight(flightsParameters.getReturnFlightOption());
		ReviewYourTripPage yourTripPage = flightsresultPage.goToReviewYourTripPage();

		// -----------------------------------------------------> Review Your Trip Page <-----------------------------------------------------//

		Assert.assertEquals(yourTripPage.getReviewYourTripHeader(), TextValidations.getReviewYourPagePageHeader(), "REVIEW YOUR PAGE HEADER DOES NOT MATCH");
		Assert.assertTrue(yourTripPage.isPriceContainerPresent(), "PRICE CONTAINER IS NOT DISPLAYED");
		Assert.assertTrue(yourTripPage.isPriceLabelPresent(), "PRICE LABEL IS NOT DISPLAYED");
		Assert.assertTrue(yourTripPage.isPriceGuaranteeLabelPresent(), "PRICE GUARANTEE LABEL IS NOT DISPLAYED");
		Assert.assertTrue(yourTripPage.isDepartDivPresent(), "DEPART DIV IS NOT DISPLAYED");
		Assert.assertTrue(yourTripPage.isReturnDivPresent(), "RETURN DIV IS NOT DISPLAYED");
		log.info("Review Your Trip Page Header: " + yourTripPage.getReviewYourTripHeader());
		log.info("Total Price: " + yourTripPage.getTotalPriceLabel());
		log.info("Is Depart Div present? " + yourTripPage.isDepartDivPresent());
		log.info("Is Return Div present? " + yourTripPage.isReturnDivPresent());
		log.info("Price Guarantee Label: " + yourTripPage.getPriceGuaranteeLabel());
		log.info("Is Price Guarantee Label present?: " + yourTripPage.isPriceGuaranteeLabelPresent());
		PaymentFlightPage paymentPage = yourTripPage.clickContinueBookingButton();

		// -----------------------------------------------------> Payment Flight Page <-----------------------------------------------------//

		Assert.assertEquals(paymentPage.getPaymentFlightHeader(), TextValidations.getPaymentFlightPageHeader(), "PAYMENT FLIGHT PAGE HEADER DOES NOT MATCH");
		Assert.assertTrue(paymentPage.isPreferencesSectionPresent(), "PREFERENCES SECTION IS NOT DISPLAYED");
		paymentPage.setFirstName(flightsParameters.getBookerFirstName());
		paymentPage.setLastName(flightsParameters.getBookerLastName());
		Assert.assertEquals(paymentPage.getPreferencesSectionTitle(), TextValidations.getPreferencesSectionTitle(), "PREFERENCES SECTION TITLE DOES NOT MATCH");
		Assert.assertTrue(paymentPage.isPaymentSectionPresent(), "PAYMENT SECTION IS NOT DISPLAYED");
		Assert.assertEquals(paymentPage.getPaymentSectionTitle(), TextValidations.getPaymentSectionTitle(), "PAYMENT SECTION TITLE DOES NOT MATCH");
		Assert.assertTrue(paymentPage.isSummaryFlightSectionPresent(), "SUMMARY FLIGHT SECTION IS NOT DISPLAYED");
		Assert.assertTrue(paymentPage.isPriceGuaranteeAlertPresent(), "PRICE GUARANTEE ALERT IS NOT DISPLAYED");
		Assert.assertTrue(paymentPage.isCompleteBookingButtonPresent(), "COMPLETE BOOKING BUTTON IS NOT DISPLAYED");
		log.info("******************* FINAL ASSERTS *******************");
		log.info("Payment Flight Page Header: " + paymentPage.getPaymentFlightHeader());
		log.info("Preferences Section Title: " + paymentPage.getPreferencesSectionTitle());
		log.info("Payment Section Title: " + paymentPage.getPaymentSectionTitle());
		log.info("Is Summary Flight Section displayed?: " + paymentPage.isSummaryFlightSectionPresent());
		log.info("Is Price Guarantee Alert displayed?: " + paymentPage.isPriceGuaranteeAlertPresent());
		log.info("Is \"Continue Booking\" button displayed?: " + paymentPage.isCompleteBookingButtonPresent());
		log.info("---------- Booking a Flight till the payment page works correctly ----------");

	}
}
