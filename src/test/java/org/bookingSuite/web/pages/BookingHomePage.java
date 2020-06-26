package org.bookingSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * BookingTest Home Page handle the main options in BookingTest.com page (Dormir, Vuelos, Alquiler de coches, Atracciones turísticas and
 * Taxis aeropuerto) also, other features like Registra tu alojamiento, Hazte una cuenta and Iniciar sesión 
 *
 * @author: july.moreno
 * @version: 24/06/2020
 *
 */

public class BookingHomePage extends BasePage {
	
	@FindBy(css = "#cross-product-bar .xpb__link:first-child")
    private WebElement stayMenuOption;
	
    /**
     * Constructor.
     * @param driver : WebDriver
     * @param url : String - Home Page url
     */
	public BookingHomePage(WebDriver driver, String url) {
		super(driver);
		driver.get(url);
	}
	
	/**
	 * Click to {stayMenuOption} to continue the booking process
	 * 
	 * @return StaySearchPage
	 */
	
	public StaySearchPage selectStayOption() {
		log.info("User clicks Stay Option");
		waitElementVisibility(stayMenuOption);
		stayMenuOption.click();
		return new StaySearchPage(getDriver());
	}
	
}
