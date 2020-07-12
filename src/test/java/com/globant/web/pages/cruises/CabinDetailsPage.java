package com.globant.web.pages.cruises;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CabinDetailsPage extends BasePage {

    @FindBy(css = ".decision-header .decision-title.secondary-title")
    private WebElement cabinExperienceLabel;

    @FindBy(className = "title-on-ship-image")
    private WebElement shipItemHeader;

    @FindBy(className = "ship-name")
    private WebElement shipName;

    @FindBy(className = "sailing-departing-on")
    private WebElement sailingDepartingDate;

    @FindBy(className = "sailing-returning-on")
    private WebElement sailingReturningDate;

    /**
     * Constructor
     * @param driver
     */
    public CabinDetailsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Validate if Cabin Experience Label is Present
     *
     * @return true: Boolean
     */
    public boolean isCabinExperienceLabelPresent() {
        waitElementVisibility(cabinExperienceLabel);
        return cabinExperienceLabel.isDisplayed();
    }

    /**
     * Get Cabin Experience Label
     *
     * @return Label: String
     */
    public String getCabinExperienceLabel() {
        waitElementVisibility(cabinExperienceLabel);
        return cabinExperienceLabel.getText();
    }

    /**
     * Validate if Ship Name is Present
     *
     * @return true: Boolean
     */
    public boolean isShipNamePresent() {
        waitElementVisibility(shipName);
        return shipName.isDisplayed();
    }

    /**
     * Get Ship Name text
     *
     * @return Ship Name: String
     */
    public String getShipName() {
        waitElementVisibility(shipName);
        return shipName.getText();
    }

    /**
     * Validate if Destination is Present
     *
     * @return true: Boolean
     */
    public boolean isDestinationPresent(String destination) {
        waitElementVisibility(shipItemHeader);
        String cruiseInfo = shipItemHeader.getText().trim().toLowerCase();
        return cruiseInfo.contains(destination);
    }

    /**
     * Get Destination text
     *
     * @return Ship Name: String
     */
    public String getDestination() {
        waitElementVisibility(shipItemHeader);
        return shipItemHeader.getText().split(" ")[2];
    }


    /**
     * Get Number of Nights text
     *
     * @return Nights: String
     */
    public String getNumberOfNights() {
        waitElementVisibility(shipItemHeader);
        return shipItemHeader.getText().split(" ")[0];
    }

    /**
     * Validate if Sailing Departing Date is Present
     *
     * @return true: Boolean
     */
    public boolean isSailingDepartingDatePresent() {
        waitElementVisibility(sailingDepartingDate);
        return sailingDepartingDate.isDisplayed();
    }

    /**
     * Get Sailing Departing Date
     *
     * @return Sailing Departing Date: String
     */
    public String getSailingDepartingDate() {
        waitElementVisibility(sailingDepartingDate);
        return sailingDepartingDate.getText();
    }

    /**
     * Validate if Sailing Returning Date is Present
     *
     * @return true: Boolean
     */
    public boolean isSailingReturningDatePresent() {
        waitElementVisibility(sailingReturningDate);
        return sailingReturningDate.isDisplayed();
    }

    /**
     * Get Sailing Returning Date
     *
     * @return Sailing Returning Date: String
     */
    public String getSailingReturningDate() {
        waitElementVisibility(sailingReturningDate);
        return sailingReturningDate.getText();
    }

}