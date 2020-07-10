package com.globant.web.pages.packages;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Review And Book Page shows the details about the vacational package chosen through the process of
 * selecting the hotel, the departing and returning flights.  Also, allows to select additional services
 * like transportation.
 */

public class ReviewAndBookPage extends BasePage {

    @FindBy(className = "flights")
    private WebElement reviewAndBookPageHeader;

    @FindBy(id = "preferences")
    private WebElement tripPreferencesSection;

    @FindBy(id = "trip-summary")
    private WebElement tripSummarySection;

    @FindBy(css = "section[class*=\"flight-preferences\"] h2")
    private WebElement flightSectionHeader;

    @FindBy(css = "section[class*=\"flight-preferences\"] .traveler-info-header h3 > span:first-of-type")
    private WebElement flightSectionTitle;

    @FindBy(id = "firstname[0]")
    private WebElement firstNameInput;

    @FindBy(id = "lastname[0]")
    private WebElement lastNameInput;

    @FindBy(css = "section[class*=\"hotel-preferences\"] h2")
    private WebElement hotelSectionHeader;

    @FindBy(css = "section[class*=\"hotel-preferences\"] .traveler-info-header h3")
    private WebElement hotelSectionTitle;

    @FindBy(id = "hotel-contact-name0")
    private WebElement hotelContactNameInput;

    @FindBy(css = "section[class*=\"lx-preferences\"] h2")
    private WebElement transportationSectionHeader;

    @FindBy(css = "section[class*=\"lx-preferences\"] .traveler-info-header h3")
    private WebElement transportationSectionTitle;

    @FindBy(id = "new_traveler_name_2")
    private WebElement transportationContactInput;

    // Validate correctness of data

    @FindBy(css = ".product-summary:nth-child(2) .product-description")
    private WebElement flightTravelersData;

    @FindBy(css = ".product-summary:nth-child(2) .location-info")
    private WebElement flightLocationData;

    @FindBy(css = ".product-summary:nth-child(3) .product-description")
    private WebElement hotelGuestsData;

    @FindBy(css = ".product-summary:nth-child(3) .location-info")
    private WebElement hotelLocationData;

    @FindBy(className = "lx-ticket-count")
    private WebElement transportationGuestsData;

    @FindBy(css = ".product-summary:nth-child(4) .location-info")
    private WebElement transportationLocationData;

    @FindBy(css = "span[data-price-update=\"finalTripTotal\"]")
    private WebElement totalPriceLabel;

    /**
     * Constructor
     *
     * @param driver : WebDriver
     */
    public ReviewAndBookPage(WebDriver driver) {
        super(driver);
    }


    /**
     * Validate if Review and Book Page Header is present
     *
     * @return true: boolean
     */
    public boolean isReviewAndBookPageHeaderPresent() {
        return reviewAndBookPageHeader.isDisplayed();
    }

    /**
     * Get Review and Book Page Header text
     *
     * @return Label: String
     */
    public String getReviewAndBookPageHeader() {
        return reviewAndBookPageHeader.getText();
    }

    /**
     * Validate if Trip Preferences Section is present
     *
     * @return true: boolean
     */
    public boolean isTripPreferencesSectionPresent() {
        return tripPreferencesSection.isDisplayed();
    }

    /**
     * Validate if Trip Summary Section is present
     *
     * @return true: boolean
     */
    public boolean isTripSummarySectionPresent() {
        return tripSummarySection.isDisplayed();
    }

    /**
     * Get Flight Section Header text
     *
     * @return Label: String
     */
    public String getFlightSectionHeader() {
        return flightSectionHeader.getText();
    }

    /**
     * Validate if Flight Section Title is present
     *
     * @return true: boolean
     */
    public boolean isFlightSectionTitlePresent() {
        return flightSectionTitle.isDisplayed();
    }

    /**
     * Get Flight Section Title
     *
     * @return Title: String
     */
    public String getFlightSectionTitle() {
        return flightSectionTitle.getText();
    }


    /**
     * Set the First Name for flight section
     *
     * @param firstName: String
     */
    public void setFirstName(String firstName) {
        waitElementVisibility(firstNameInput);
        firstNameInput.sendKeys(firstName);
        log.info("The user types the first name");

    }

    /**
     * Set the Last Name for flight section
     *
     * @param lastName: String
     */
    public void setLastName(String lastName) {
        waitElementVisibility(lastNameInput);
        lastNameInput.sendKeys(lastName);
        log.info("The user types the last name");

    }

    /**
     * Get Hotel Section Header text
     *
     * @return Header: String
     */
    public String getHotelSectionHeader() {
        return hotelSectionHeader.getText();
    }

    /**
     * Validate if Hotel Section Title is present
     *
     * @return true: boolean
     */
    public boolean isHotelSectionTitlePresent() {
        return hotelSectionTitle.isDisplayed();
    }

    /**
     * Get Hotel Section Title
     *
     * @return Title: String
     */
    public String getHotelSectionTitle() {
        return hotelSectionTitle.getText();
    }

    /**
     * Validate if Hotel Contact Input is present
     *
     * @return true: boolean
     */
    public boolean isHotelContactNameInputPresent() {
        return hotelContactNameInput.isDisplayed();
    }

    /**
     * Get Transportation Section Header text
     *
     * @return Header: String
     */
    public String getTransportationSectionHeader() {
        return transportationSectionHeader.getText();
    }

    /**
     * Validate if Transportation Section Title is present
     *
     * @return true: boolean
     */
    public boolean isTransportationSectionTitlePresent() {
        return transportationSectionTitle.isDisplayed();
    }

    /**
     * Get Transportation Section Title
     *
     * @return Title: String
     */
    public String getTransportationSectionTitle() {
        return transportationSectionTitle.getText();
    }

    /**
     * Validate if Transportation Contact Input is present
     *
     * @return true: boolean
     */
    public boolean isTransportationContactInputPresent() {
        return transportationContactInput.isDisplayed();
    }

    // Validate correctness of data

    /**
     * Get Flight Travelers Data
     *
     * @return Title: String
     */
    public String getFlightTravelersData() {
        return flightTravelersData.getText();
    }

    /**
     * Get Flight Location
     *
     * @return Title: String
     */
    public String getFlightLocationData() {
        return flightLocationData.getText();
    }

    /**
     * Get Hotel Guests Data
     *
     * @return Title: String
     */
    public String getHotelGuestsData() {
        return hotelGuestsData.getText();
    }

    /**
     * Validate if Hotel Location Data is present
     *
     * @return true: boolean
     */
    public boolean isHotelLocationDataPresent() {
        return hotelLocationData.isDisplayed();
    }

    /**
     * Get Hotel Location
     *
     * @return Title: String
     */
    public String getHotelLocationData() {
        return hotelLocationData.getText();
    }

    /**
     * Get Transportation Guests Data
     *
     * @return Title: String
     */
    public String getTransportationGuestsData() {
        return transportationGuestsData.getText();
    }

    /**
     * Get Transportation Location
     *
     * @return Title: String
     */
    public String getTransportationLocationData() {
        moveToElement(transportationLocationData);
        return transportationLocationData.getText().trim();
    }

    /**
     * Validate if Total Price Label is present
     *
     * @return true: boolean
     */
    public boolean isPriceLabelPresent() {
        return totalPriceLabel.isDisplayed();
    }

    /**
     * Get Total Price Label
     *
     * @return Price: String
     */
    public String getTotalPriceLabel() {
        return totalPriceLabel.getText();
    }

}
