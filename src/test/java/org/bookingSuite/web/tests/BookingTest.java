package org.bookingSuite.web.tests;

import org.bookingSuite.web.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest extends BaseTest {

	//Assertions variables
	public final static String StayPageHeader = "Busca ofertas en hoteles, casas y mucho más";
	public final static String CalendarLabel = "Check-in - Check-out";
	public final static String GuestConfirmation= "3 adultos, 1 niño (9 años)";
	public final static String LastPageProgressText="Últimos datos";
	
	public final static String Destination = "Bogotá, Colombia";
	public final static int NumberOfAdults = 3;
	public final static int NumberOfChildren = 1;
	public final static int NumberOfRooms = 1;
	public final static String[] ChildrenAge = { "9" };
	
	public final static String NumberOfStars = "5";
	public final static int LodgingOptionToSelect = 2;
	
	public final static String BookerLastName= "Moreno";
	public final static String BookerEmail="email@email.com";
	
	public final static String BookerAddress = "Calle falsa 123";
	public final static String BookerCity= "Bogota";
	public final static String BookerZipCode="1092873";
	public final static String BookerCountry = "co";
	public final static String BookerPhoneNumber="3134204440";
	
	public final static String CardHolderLastName = "Jara";
	public final static String CardType="American Express";
	public final static String CardNumber= "342424242342342";
	public final static String ExpirationMonth = "09";
	public final static String ExpirationYear = "2022";
	public final static String CVCcode = "3210";
	

	@Test(description = "Test")
	public void bookingTest() {
		BookingHomePage home = getHomePage();
		StaySearchPage staysPage = home.selectStayOption();
		Assert.assertEquals(staysPage.getStayPageHeaderText(), StayPageHeader, "STAY HEADER DOES NOT MATCH");
		staysPage.enterDestination(Destination);
		staysPage.setDatesSearch();
		Assert.assertNotEquals(staysPage.getCalendarLabelText(), CalendarLabel, "CALENDAR LABEL FILLED SUCESSFULLY");
		staysPage.setGuestInformation(NumberOfAdults, NumberOfChildren, ChildrenAge);
		Assert.assertEquals(Integer.parseInt(staysPage.getAdultsNumberLabelText()), NumberOfAdults, "ADULTS QUANTITY DOES NOT MATCH");
		Assert.assertEquals(Integer.parseInt(staysPage.getChildrenNumberLabelText()), NumberOfChildren, "CHILDREN QUANTITY DOES NOT MATCH");
		Assert.assertEquals(Integer.parseInt(staysPage.getRoomsQuantityLabelText()), NumberOfRooms, "ROOMS QUANTITY DOES NOT MATCH");
		SearchResultsPage searchResultsPage = staysPage.clickSearchButton();
		Assert.assertTrue(searchResultsPage.searchResultHeaderIsPresent(), "RESULTS HEADER NOT FOUND");
		log.info(searchResultsPage.getSearchResultHeader());
		searchResultsPage.setStarsFilter(NumberOfStars);
		log.info(searchResultsPage.getSearchResultHeader());
		log.info("Lodging options found: " + searchResultsPage.searchResultsItem()); //Revisar con Pingüin
		searchResultsPage.moveToInitialResultsPage();
		LodgingDetailsPage lodginDetailsPage = searchResultsPage.clickLodgingOption(LodgingOptionToSelect);
		Assert.assertTrue(lodginDetailsPage.hotelNameLabelIsPresent(), "HOTEL NAME LABEL NOT FOUND");
		log.info("Hotel Name: "+lodginDetailsPage.getHotelNameLabel());
		Assert.assertTrue(lodginDetailsPage.hotelNameIsTheSame(), "HOTEL NAME IS NOT THE SAME THAN PREVIOUS PAGE");
		
		log.info("Number of guest: " +lodginDetailsPage.getGuestsTextLabel());
		Assert.assertTrue(lodginDetailsPage.guestNumberIsTheSame(), "GUESTS NUMBER IS NOT THE SAME THAN PREVIOUS PAGE");
		
		Assert.assertTrue(lodginDetailsPage.nightsAndGuestsLabelIsPresent(), "NIGHTS AND GUESTS LABEL NOT FOUND");
		log.info("Nights and guests: "+lodginDetailsPage.getNightsAndGuestsLabel());
		Assert.assertTrue(lodginDetailsPage.totalPriceLabelIsPresent(), "TOTAL PRICE LABEL NOT FOUND");
		log.info("Total price: "+lodginDetailsPage.getTotalPriceLabel());
		Assert.assertTrue(lodginDetailsPage.checkInSummaryLabelIsPresent(), "CHECK IN DATES NOT FOUND");
		log.info("Check In summary: "+lodginDetailsPage.getCheckInSummaryLabel());
		Assert.assertTrue(lodginDetailsPage.checkOutSummaryLabelIsPresent(), "CHECK OUT DATES NOT FOUND");
		log.info("Check Out Summary: "+lodginDetailsPage.getCheckOutSummaryLabel());
		Assert.assertTrue(lodginDetailsPage.occupancySummaryLabelIsPresent(), "OCCUPANCY LABEL NOT FOUND");
		log.info("Occupancy Label: "+lodginDetailsPage.getOccupancySummaryLabel());
		lodginDetailsPage.clicBookThisRoom();
		BookerInformationPage bookerInformationPage = lodginDetailsPage.clickFinalReserve();

		Assert.assertNotNull(bookerInformationPage.userInfoBoxIsPresent(), "USER INFORMATION DIV NOT FOUND");
		log.info("Booker Information Title: " + bookerInformationPage.getUserInfoBoxTitle());
		bookerInformationPage.fillBookerPersonalInformation(BookerLastName, BookerEmail, BookerEmail);
		PaymentInformationPage paymentInformationPage = bookerInformationPage.goToPaymentInformationPage();
		
		Assert.assertNotNull(paymentInformationPage.bookerDetailsBoxIsPresent(), "BOOKER DETAILS DIV NOT FOUND");
		log.info("Booker Details Title: " + paymentInformationPage.getBookerDetailsTitle());
		paymentInformationPage.fillBookerDetailsInformation(BookerAddress,BookerCity,BookerZipCode,BookerCountry,BookerPhoneNumber);
		Assert.assertNotNull(paymentInformationPage.paymentDetailsBoxIsPresent(), "PAYMENT DETAILS DIV NOT FOUND");
		log.info("Payment Details Tittle: " + paymentInformationPage.getPaymentDetailsTitle());
		paymentInformationPage.fillBookerPaymentInformation(CardHolderLastName,CardType,CardNumber,ExpirationMonth, ExpirationYear,	CVCcode);
		Assert.assertEquals(paymentInformationPage.getProgressPageText(), LastPageProgressText, "PROGRESS PAGE TEXT NOT FOUND");
		log.info("ASSERT 1 - Progress Page Title: " + paymentInformationPage.getProgressPageText());
		Assert.assertEquals(paymentInformationPage.getGuestsGroupSideBarText(), GuestConfirmation,"GROUP DESCRIPTION NOT FOUND");
		log.info("ASSERT 2 - My Group label: " + paymentInformationPage.getGuestsGroupSideBarText());
		Assert.assertEquals(paymentInformationPage.getBookerReassuranceEmail(), BookerEmail,"BOOKER EMAILNOT FOUND");
		log.info("ASSERT 3 - Booker Reassurance Email: " + paymentInformationPage.getBookerReassuranceEmail());
		Assert.assertTrue(paymentInformationPage.PaymentAreaIsPresent(), "PAYMENT AREA IS AVAILABLE");
		log.info("ASSERT 4 - Is \"Payment Area\" visible?: " + paymentInformationPage.PaymentAreaIsPresent());
		Assert.assertTrue(paymentInformationPage.checkYourDataButtonIsOperable(),"VALIDATE DATA BUTTON IS NOT READY TO USE");
		log.info("ASSERT 5 - Is \"Check Your Data Button\" ready?: " + paymentInformationPage.checkYourDataButtonIsOperable());
		Assert.assertTrue(paymentInformationPage.completeBookingButtonIsOperable(),	"COMPLETE BOOK BUTTON IS NOT READY TO USE");
		log.info("ASSERT 6 - Is \"Complete Booking Button\" ready?: " + paymentInformationPage.completeBookingButtonIsOperable());

	}
}
