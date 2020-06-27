package org.bookingSuite.web.tests;

import javax.xml.crypto.Data;

import org.bookingSuite.web.pages.*;
import org.bookingSuite.web.utils.AssertTextValidation;
import org.bookingSuite.web.utils.Booker;
import org.bookingSuite.web.utils.CreditCard;
import org.bookingSuite.web.utils.SearchParameters;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest extends BaseTest {
	
	/**
	 * Steps to book a stay in Booking.com web page from searching a lodging to entering payment data
	 * @param searchParameters
	 * @param booker
	 * @param creditCard
	 */

	@Test(description = "Booking Test", dataProvider="BookingInputData", 
			dataProviderClass = org.bookingSuite.web.data.DataInit.class)
	public void bookingTest(SearchParameters searchParameters, Booker booker, CreditCard creditCard) {
		BookingHomePage home = getHomePage();
		
		//-----------------------------------------------------> Booking Home Page <-----------------------------------------------------//
		StaySearchPage staysPage = home.selectStayOption();
		
		//-----------------------------------------------------> Stay Search Page <-----------------------------------------------------//
		Assert.assertEquals(staysPage.getStayPageHeaderText(),AssertTextValidation.getStayPageHeader(), "STAY HEADER DOES NOT MATCH");
		staysPage.enterDestination(searchParameters.getDestination());
		staysPage.setDatesToSearch(searchParameters.getStayStartDate(),searchParameters.getStayEndDate());
		Assert.assertNotEquals(staysPage.getCalendarLabelText(), AssertTextValidation.getCalendarLabel(), "CALENDAR LABEL FILLED SUCESSFULLY");
		staysPage.setGuestInformation(searchParameters.getNumberOfAdults(), searchParameters.getNumberOfChildren(), searchParameters.getChildrenAge());
		Assert.assertEquals(Integer.parseInt(staysPage.getAdultsNumberLabelText()), searchParameters.getNumberOfAdults(), "ADULTS QUANTITY DOES NOT MATCH");
		Assert.assertEquals(Integer.parseInt(staysPage.getChildrenNumberLabelText()), searchParameters.getNumberOfChildren(), "CHILDREN QUANTITY DOES NOT MATCH");
		Assert.assertEquals(Integer.parseInt(staysPage.getRoomsQuantityLabelText()), searchParameters.getNumberOfRooms(), "ROOMS QUANTITY DOES NOT MATCH");
		SearchResultsPage searchResultsPage = staysPage.clickSearchButton();
		
		//-----------------------------------------------------> Search Result Page <-----------------------------------------------------//
		Assert.assertTrue(searchResultsPage.searchResultHeaderIsPresent(), "RESULTS HEADER NOT FOUND");
		log.info("Search results: " + searchResultsPage.getSearchResultHeader());
		searchResultsPage.setStarsFilter(searchParameters.getNumberOfStars());
		log.info("Search results: " + searchResultsPage.getSearchResultHeader());
		log.info("Lodging options found: " + searchResultsPage.searchResultsItem(searchParameters.getNumberOfStars()));
		searchResultsPage.moveToInitialResultsPage();
		LodgingDetailsPage lodginDetailsPage = searchResultsPage.clickLodgingOption(searchParameters.getLodgingOptionToSelect());

		//-----------------------------------------------------> Lodging Details Page <-----------------------------------------------------//
		Assert.assertTrue(lodginDetailsPage.hotelNameLabelIsPresent(), "HOTEL NAME LABEL NOT FOUND");
		log.info("Hotel Name: "+lodginDetailsPage.getHotelNameLabel());
		Assert.assertTrue(lodginDetailsPage.hotelNameIsTheSame(), "HOTEL NAME IS NOT THE SAME THAN PREVIOUS PAGE");
		log.info("Number of guest: " +lodginDetailsPage.getGuestsTextLabel());
		Assert.assertTrue(lodginDetailsPage.guestNumberIsTheSame(), "GUESTS NUMBER IS NOT THE SAME THAN PREVIOUS PAGE");
		Assert.assertTrue(lodginDetailsPage.nightsAndGuestsLabelIsPresent(), "NIGHTS AND GUESTS LABEL NOT FOUND");
		log.info("Number of  Nights: "+lodginDetailsPage.getNightsAndGuestsLabel());
		Assert.assertTrue(lodginDetailsPage.totalPriceLabelIsPresent(), "TOTAL PRICE LABEL NOT FOUND");
		log.info("Total price: "+lodginDetailsPage.getTotalPriceLabel());
		Assert.assertTrue(lodginDetailsPage.checkInSummaryLabelIsPresent(), "CHECK IN DATES NOT FOUND");
		log.info("Check In summary: "+lodginDetailsPage.getCheckInSummaryLabel());
		Assert.assertTrue(lodginDetailsPage.checkOutSummaryLabelIsPresent(), "CHECK OUT DATES NOT FOUND");
		log.info("Check Out Summary: "+lodginDetailsPage.getCheckOutSummaryLabel());
		Assert.assertTrue(lodginDetailsPage.occupancySummaryLabelIsPresent(), "OCCUPANCY LABEL NOT FOUND");
		log.info("Occupancy Label: "+lodginDetailsPage.getOccupancySummaryLabel());
		lodginDetailsPage.clickBookThisRoom();
		BookerInformationPage bookerInformationPage = lodginDetailsPage.clickFinalReserve(searchParameters.getConfirmNumberOfRooms());
		
		//-----------------------------------------------------> Booker Information Page <-----------------------------------------------------//
		Assert.assertNotNull(bookerInformationPage.userInfoBoxIsPresent(), "USER INFORMATION DIV NOT FOUND");
		log.info("Booker Information Title: " + bookerInformationPage.getUserInfoBoxTitle());
		bookerInformationPage.fillBookerPersonalInformation(booker.getLastName(), booker.getEmail(), booker.getEmail());
		PaymentInformationPage paymentInformationPage = bookerInformationPage.goToPaymentInformationPage();
		
		//-----------------------------------------------------> Payment Information Page <-----------------------------------------------------//
		Assert.assertNotNull(paymentInformationPage.bookerDetailsBoxIsPresent(), "BOOKER DETAILS DIV NOT FOUND");
		log.info("Booker Details Title: " + paymentInformationPage.getBookerDetailsTitle());
		paymentInformationPage.fillBookerDetailsInformation(booker.getAddress(),booker.getCity(),booker.getZipCode(),booker.getCountry(),booker.getPhoneNumber());
		Assert.assertNotNull(paymentInformationPage.paymentDetailsBoxIsPresent(), "PAYMENT DETAILS DIV NOT FOUND");
		log.info("Payment Details Title: " + paymentInformationPage.getPaymentDetailsTitle());
		paymentInformationPage.fillBookerPaymentInformation(creditCard.getCardHolderLastName(),creditCard.getCardType(),creditCard.getCardNumber(),creditCard.getExpirationMonth(), creditCard.getExpirationYear(),	creditCard.getCvcCcode());
		Assert.assertEquals(paymentInformationPage.getProgressPageText(), AssertTextValidation.getLastPageProgressText(), "PROGRESS PAGE TEXT NOT FOUND");
		Assert.assertEquals(paymentInformationPage.getGuestsGroupSideBarText(), AssertTextValidation.getGuestConfirmation(),"GROUP DESCRIPTION NOT FOUND");
		Assert.assertEquals(paymentInformationPage.getBookerReassuranceEmail(), booker.getEmail(),"BOOKER EMAILNOT FOUND");
		Assert.assertTrue(paymentInformationPage.PaymentAreaIsPresent(), "PAYMENT AREA IS AVAILABLE");
		Assert.assertTrue(paymentInformationPage.checkYourDataButtonIsOperable(),"VALIDATE DATA BUTTON IS NOT READY TO USE");
		Assert.assertTrue(paymentInformationPage.completeBookingButtonIsOperable(),	"COMPLETE BOOK BUTTON IS NOT READY TO USE");
		
		//-----------------------------------------------------> Assertions Print <-----------------------------------------------------//
		log.info("*********************  ASSERTIONS PRINT  ********************");
		log.info("ASSERT 1 - Progress Page Title: " + paymentInformationPage.getProgressPageText());
		log.info("ASSERT 2 - Group Label: " + paymentInformationPage.getGuestsGroupSideBarText());
		log.info("ASSERT 3 - Booker Reassurance Email: " + paymentInformationPage.getBookerReassuranceEmail());
		log.info("ASSERT 4 - Is \"Payment Area\" visible? : " + paymentInformationPage.PaymentAreaIsPresent());
		log.info("ASSERT 5 - Is \"Check Your Data\" button ready to use? : " + paymentInformationPage.checkYourDataButtonIsOperable());
		log.info("ASSERT 6 - Is \"Complete Booking\"  button ready to use? : " + paymentInformationPage.completeBookingButtonIsOperable());
		
	}
}
