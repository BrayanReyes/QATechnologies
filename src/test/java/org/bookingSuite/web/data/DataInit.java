package org.bookingSuite.web.data;

import org.bookingSuite.web.utils.Booker;
import org.bookingSuite.web.utils.CreditCard;
import org.bookingSuite.web.utils.SearchParameters;
import org.testng.annotations.DataProvider;

/**
 * This class defines the Data Provider that will be used for the test
 * 
 * @author: july.moreno
 * @version: 26/06/2020
 */

public class DataInit {

	// Booking Exam Test requires just one child. So, just one one age is set up. In
	// case more children are needed, the age should be included in this array.
	String[] childrenAge = { "9" };

	// Parameters to search a stay:
	// destination, daysForward, stayDuration,stayAdultsNumber, stayChildrenNumber,childrenAge, lodgingConfirmRoomsNumber,
	// stayStarsNumber, stayOptionToSelect
	SearchParameters searchParameters = new SearchParameters("Bogotá, Colombia", 30, 45, 3, 1, childrenAge, "1", "5",
			2);

	// Parameters for a credit card: 
	// CardHolderLastName, creditCardType, creditCardNumber, creditCardExpirationMonth, creditCardExpirationYear, creditCardCvcCode
	CreditCard creditCard = new CreditCard("Jara", "American Express", "342424242342342", "09", "2022", "3210");

	// Parameters for a booker:
	// bookerFirstName, bookerLastName, bookerEmail, bookerAddress, bookerCountry, bookerCity, bookerZipCode, bookerPhoneNumber
	Booker booker = new Booker("July", "Moreno", "email@email.com", "Calle falsa 123", "co", "Bogota", "1101010",
			"3134204440");

	/**
	 * Data Provider for Booking Test
	 */
	@DataProvider(name = "BookingInputData")
	public Object[][] bookingInputData() {
		return new Object[][] { { searchParameters, booker, creditCard } };
	}

}
