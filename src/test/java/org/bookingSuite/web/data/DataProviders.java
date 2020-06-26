package org.bookingSuite.web.data;

import org.bookingSuite.web.utils.Init;
import org.testng.annotations.DataProvider;

/**
 * This class defines the Data Providers that will be used for the test
 * 
 * @author: july.moreno
 * @version: 07/06/2020
 */

public class DataProviders {

	@DataProvider(name = "setUpStaySearch")
	public static Object[][] inputStaySearch() {
		return new Object[][] { { "Bogotá, Colombia", 3, 1, 1, "9" } };
	}

	@DataProvider(name = "lodgingsFilters")
	public static Object[][] inputLodgingsFilters() {
		return new Object[][] { { "5", 2 } };
	}

	@DataProvider(name = "bookerPersonalInformation")
	public static Object[][] inputBookerPersonalInformation() {
		return new Object[][] { { "Moreno", "email@email.com" } };
	}

	@DataProvider(name = "bookerLocationInformation")
	public static Object[][] inputBookerLocationInformation() {
		return new Object[][] { { "Calle falsa 123", "Bogota", "1092873", "co", "4528190" } };
	}

	@DataProvider(name = "bookerPaymentInformation")
	public static Object[][] inputBookerPaymentInformation() {
		return new Object[][] { { "Jara", "American Express", "342424242342342", "09", "2022", "3210" } };
	}

}
