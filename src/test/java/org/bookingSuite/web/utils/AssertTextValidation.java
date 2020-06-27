package org.bookingSuite.web.utils;

/**
 * Assert Text Validation defines the entries needed to perform text assertions
 * in the Booking Test class the test suite
 * 
 * @author: july.moreno
 * @version: 26/06/2020
 */

public class AssertTextValidation {

	public static String StayPageHeader = "Busca ofertas en hoteles, casas y mucho más";
	public static String CalendarLabel = "Check-in - Check-out";
	public static String GuestConfirmation = "3 adultos, 1 niño (9 años)";
	public static String LastPageProgressText = "Últimos datos";
	public static String SelectRoomsAdvice = "Primero selecciona tu alojamiento";

	/**
	 * @return the Stay Page Header
	 */
	public static String getStayPageHeader() {
		return StayPageHeader;
	}

	/**
	 * @return the Calendar Label
	 */
	public static String getCalendarLabel() {
		return CalendarLabel;
	}

	/**
	 * @return the Guest Confirmation Text
	 */
	public static String getGuestConfirmation() {
		return GuestConfirmation;
	}

	/**
	 * @return the Last Page Progress Text
	 */
	public static String getLastPageProgressText() {
		return LastPageProgressText;
	}

	/**
	 * @return the Select Rooms Advice
	 */
	public static String getSelectRoomsAdvice() {
		return SelectRoomsAdvice;
	}

}
