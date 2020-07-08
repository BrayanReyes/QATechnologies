package com.globant.web.pages.flights;

import com.globant.web.pages.BasePage;
import jdk.nashorn.internal.ir.TryNode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightsResultPage extends BasePage {

    @FindBy(css = ".title-departure span[data-test-id=\"title-city-text\"]")
    private WebElement flightsPageHeader;

    @FindBy(id = "sortDropdown")
    private WebElement sortDropdown;

    @FindBy(id = "uitk-live-announce")
    private WebElement updatedResults;

  ///
    @FindBy (id = "flightModuleList")
    private WebElement resultList;

//    @FindBy (className = "flight-module")
//    private WebElement flightModule ;

//    @FindBy (className = "t-select-btn")
//    private WebElement selectButton  ;

//    @FindBy (css = ".basic-economy-footer .t-select-btn")
//    private WebElement selectFareButton  ;

    @FindBy (id = "flightSearchResultDiv")
    private WebElement changeList ;

//    #flightModuleList li[data-test-id="offer-listing"]

    @FindBy(css = "div[class=\"grid-container standard-padding \"]")
    private List<WebElement> resultItemsList;

    @FindBy(css = "div[class=\"uitk-col all-col-shrink\"] button[data-test-id=select-button]")
    private List<WebElement> selectFlightButtonList;

    @FindBy(className = "duration-emphasis")
    private List<WebElement> flightDurationLabelList;

    @FindBy(className = "flight-details-link")
    private List<WebElement> flightDetailsAndFeesList;

    @FindBy(id = "uitk-live-announce")
    private WebElement updateResultsMarker;

    @FindBy(id = "bCol")
    private WebElement flightListContainer;

    @FindBy(id = "xSellHotelForcedChoice")
    private WebElement forceHotelModal;

    @FindBy(id = "forcedChoiceNoThanks")
    private WebElement noThanksLink;

    private static final String NO_THANKS_LINK = "forcedChoiceNoThanks";
    private static final String FlightDurationCSS = "[data-test-id=\"duration\"]";
    private static final String HourSeparator = "h";
    private static final String MinuteSeparator = "m";
    private static final String ButtonDivPath = "//div[@id='basic-economy-tray-content-";
    private static final String ButtonSelect = "//button[@class='btn-secondary btn-action t-select-btn']";
    private static final String SelectFlightButtonCSS = "div[class='uitk-col all-col-shrink'] button[data-test-id='select-button']";
    private static final String FareFlightButtonCSS = "button[data-test-id='select-button-1']";

    private static final String ID_RESULT_LIST = "flightModuleList";
    private static final String CSS_SELECT_FARE_BUTTON = ".basic-economy-footer .t-select-btn";
    private static final String CLASS_FLIGHT_MODULE = "flight-module";
    private static final String CLASS_SELECT_BUTTON = "t-select-btn";
    private static final String CLASS_SHOW_CHANGE_FLIGHT = "show-change-flight-filter";

    By flightModule = By.className(CLASS_FLIGHT_MODULE);
    By selectButton = By.className(CLASS_SELECT_BUTTON);
    By changeListClass = By.className(CLASS_SHOW_CHANGE_FLIGHT);
    By selectFareButton = By.cssSelector(CSS_SELECT_FARE_BUTTON);

    /**
     * Constructor,
     *
     * @param driver : WebDriver
     */
    public FlightsResultPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get Flight Page Header
     *
     * @return Label: String
     */
    public String getFlightPageHeader() {
        return flightsPageHeader.getText();
    }

    /**
     * Validate if Sort Dropdown is present
     *
     * @return true: boolean
     */
    public boolean isSortDropdownPresent() {
        return sortDropdown.isDisplayed();
    }

    /**
     * Validate if Select Button is present in each item result
     *
     * @return true: boolean
     */
    public boolean isSelectButtonPresentInEachDivResult() {
        waitElementVisibility(resultItemsList);
        int allFlightsItems = resultItemsList.size();
        boolean isSelectButtonPresent = Objects.equals(allFlightsItems, selectFlightButtonList.size());
        return isSelectButtonPresent;
    }

    /**
     * Validate if Flight Duration Label is present in each item result
     *
     * @return true: boolean
     */
    public boolean isFlightDurationPresentInEachDivResult() {
        waitElementVisibility(resultItemsList);
        int allFlightsItems = resultItemsList.size();
        boolean isFlightDurationPresent = Objects.equals(allFlightsItems, flightDurationLabelList.size());
        return isFlightDurationPresent;
    }

    /**
     * Validate if Flight Details and Baggage fees Label is present in each item result
     *
     * @return true: boolean
     */
    public boolean isFlightDetailsAndFeesPresentInEachDivResult() {
        waitElementVisibility(resultItemsList);
        int allFlightsItems = resultItemsList.size();
        boolean isDetailsAndFeesPresent = Objects.equals(allFlightsItems, flightDetailsAndFeesList.size());
        return isDetailsAndFeesPresent;
    }

    /**
     * Select a Dropwdown Element
     *
     * @param sortDropdown: WebElement
     * @return Select
     */
    private Select selectDropdownOption(WebElement sortDropdown) {
        clickElement(sortDropdown);
        return new Select(sortDropdown);
    }


//    /**
//     * Validate if a particual option is in sort flights dropdown
//     *
//     * @return true: boolean
//     */
//    public boolean isSortOptionsInDropdown(List<String> sortingOptions) {
//        boolean isOptionInDrop = true;
//        List<WebElement> sortingOptionsList = selectDropdownOption(sortDropdown).getOptions();
//        Set<String> flightsSortOptions = new HashSet<>();
//
//        flightsSortOptions.addAll(
//                sortingOptionsList.stream()
//                        .map(option -> option.getText().split(" ")[0].toLowerCase())
//                        .collect(Collectors.toList()));
//
//        for (String option : sortingOptions)
//            if (!flightsSortOptions.contains(option.toLowerCase()))
//                isOptionInDrop = false;
//
//        log.info("valid on option" + isOptionInDrop);
//        return isOptionInDrop;
//    }


    /**
     * Select {Duration (Shortest)} as value for the {sortDropdown}
     *
     * @param sortParameter: String
     */
    public void sortResultsBy(String sortParameter) {
        log.info("The user sort the flight by: " + sortParameter);
        waitElementVisibility(sortDropdown);
        for (WebElement sortOption : selectDropdownOption(sortDropdown).getOptions()) {
            String optTemp = sortOption.getText();
            if (optTemp.contains(sortParameter)) {
                sortOption.click();
                break;
            }
        }
        waitForElementAttribute(updateResultsMarker,"aria-live","polite");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeWindowByIndex(0);
    }

    /**
     * Calculate the Flight Duration in minutes
     *
     * @param flightDuration: String
     */
    private int calculateTotalMinutesFlight(String flightDuration) {
        //log.info("Calculate total flight duration from hours to minutes");
        int durationInHourToMinutes = Integer.parseInt(flightDuration.substring(0, flightDuration.indexOf(HourSeparator))) * 60;
        int durationInMinutes = Integer.parseInt(flightDuration.substring(flightDuration.indexOf(" "), flightDuration.indexOf(MinuteSeparator)).trim());
        int totalFlightDurationMinutes = durationInHourToMinutes + durationInMinutes;
        return totalFlightDurationMinutes;
    }

    /**
     * Validate if Flight results are correctly sorted by duration
     *
     * return true: boolean
     */
    public boolean validateFlightsListOrder() {
        //selectDropdownOption(sortDropdown);
        //waitForElementAttribute(updatedResults,"aria-live","polite");
        String flightDuration = "";
        int previousItemDuration = 0;
        int currentItemDuration = 0;

        for (WebElement resultItem : resultItemsList) {
            flightDuration = resultItem.findElement(By.cssSelector(FlightDurationCSS)).getText();
            currentItemDuration = calculateTotalMinutesFlight(flightDuration);
            if (previousItemDuration > currentItemDuration)
                return false;
            previousItemDuration = currentItemDuration;
        }
        return true;
    }

    /**
     * Confirm the Flight selection accepting the Fare Button
     */

    private void confirmFlightOption(WebElement flightOptionItem, int fareFlightOption) {
        WebElement selectFlightButton = flightOptionItem.findElement(By.cssSelector(SelectFlightButtonCSS));
        clickElement(selectFlightButton);
        acceptTheFareButton(flightOptionItem, fareFlightOption);
    }

    /**
     * Select the first flight option for departure
     */
    public void selectFirstFlight() {
        log.info("The user clicks \"Select\" flight button");
        waitElementVisibility(resultItemsList);
        WebElement firstFlight = resultItemsList.stream().findFirst().get();
        moveToElement(firstFlight);
        log.info("The user click the \"Select This Fare\" button");
        confirmFlightOption(firstFlight, 1);

    }

    ////////////////////////////////////////////////////7

    public void selectFlight(int result) {
        getWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div[class=\"grid-container standard-padding \"]"), result));

        WebElement selectButton = resultItemsList.get(result).findElement(By.cssSelector("[data-test-id=\"select-button\"]"));
        clickElement(selectButton);

        List<WebElement> flightDetails = resultItemsList.get(result).findElements(By.cssSelector("[data-test-id=\"basic-economy-tray-details\"]"));
        log.info("FLIGH LIST " + flightDetails.size() );
        if(flightDetails.size() > 0){
            WebElement selectFareButton = flightDetails.get(0).findElement(By.cssSelector("[data-test-id=\"select-button\"]"));
           log.info("DIO CLICK AL FARE BUTTON");
            clickElement(selectFareButton);
        }

        handleAdvertisement();
    }
/////////////////////////////////////////////////////////////

    public ReviewYourTripPage selectReturningFlight(int result){
        selectFlight(result);

        // Check if add hotel pop-up shows up
        if(forceHotelModal.isDisplayed()){
            WebElement noThanksLink = forceHotelModal.findElement(By.id("forcedChoiceNoThanks"));
            clickElement(noThanksLink);
        }

        return new ReviewYourTripPage (getDriver());
    }
    ////////////////////////////////////////////////////////////

    /**
     * Accept the Fare Button
     */
    private void acceptTheFareButton(WebElement element, int option) {
        waitElementVisibility(element);
        WebElement selectFareButton = element.findElement(
                By.xpath(ButtonDivPath + option + "']" + ButtonSelect));
        clickElement(selectFareButton);
    }

    /**
     * Select the Return Flight
     */
    public void selectReturnFlight(int returnFlightOption) {
        //waitToRefresh(resultItemsList);
        getWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[data-test-id=\"offer-listing\"]"), returnFlightOption));
       getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class=\"grid-container standard-padding \"]")));
        // waitElementVisibility(resultItemsList);

        log.info("1: " + resultItemsList.size());
        log.info("2 " + selectFlightButtonList.size() );


        WebElement flightOptionChosen = resultItemsList.get(returnFlightOption-1);
        moveToElement(flightOptionChosen);
        log.info("The user chooses the returning flight. Option: " + returnFlightOption);
        WebElement chooseFlightButton = flightOptionChosen.findElement(By.cssSelector(SelectFlightButtonCSS));
        waitElementVisibility(chooseFlightButton);
        clickElement(chooseFlightButton);
        log.info("The user clicks the \"Select\" Button.");
        WebElement fareFlightButton = flightOptionChosen.findElement(By.cssSelector(FareFlightButtonCSS));
        waitElementVisibility(fareFlightButton);
        clickElement(fareFlightButton);
        log.info("The user clicks the \"Select this fare\" Button.");
    }


//        if (flightOption == null) {
//            selectFirstFlight();
//            changeWindowByIndex(2);
//            log.info("Departure Flight Not Found. Selected the first one by default");
//
//        } else {
//            confirmFlightOption(flightOption, returnFlightOption);
//            changeWindowByIndex(2);
//            log.info("The user select the flight option " + returnFlightOption);
//       }
//    }

    /**
     * Get the index for the "Returning" List
     */
    private WebElement getListIndexElement(List<WebElement> list, int index) {
        try {
            log.info("PRIMERO: " +list.get(index).getSize());
            return list.get(index);
        } catch (Exception ex) {
            System.err.println("Returning option not found");
        }
        return null;
    }


    /**
     * Handle Force Hotel Modal
     */

    public void handleForceHotelModal() {
        log.info("Force Hotel Modal shows up");
        if (forceHotelModal.isDisplayed()) {
            WebElement noThanksLink = forceHotelModal.findElement(By.cssSelector(NO_THANKS_LINK));
            waitElementVisibility(noThanksLink);
            clickElement(noThanksLink);
            log.info("The user click the \"No Thanks\" link");
        }
    }


    /**
     * Pass to the next Page for flight booking process
     */

    public ReviewYourTripPage goToReviewYourTripPage() {
        if (forceHotelModal.isEnabled()) {
            getWait().until(ExpectedConditions.elementToBeClickable(noThanksLink));
            noThanksLink.click();
    }
        return new ReviewYourTripPage(getDriver());
    }


    public void selectDepartingFLightAt(int index) {
        WebElement element = resultList.findElements(flightModule).get(index);
        BasePage.jsScroll(getDriver(), element);

        element.findElement(selectButton).click();

        getWait().until(ExpectedConditions.visibilityOf(element.findElement(selectFareButton)));
        element.findElement(selectFareButton).click();
    }

    public void selectReturningFLightAt(int index) {


        moveToElement(flightListContainer);

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getWait().until(ExpectedConditions.visibilityOfElementLocated(changeListClass));

        WebElement element = resultList.findElements(flightModule).get(index);
        element.findElement(selectButton).click();

        getWait().until(ExpectedConditions.visibilityOf(element.findElement(By.id("basic-economy-tray-content-" + (index + 1)))));

        getWait().until(ExpectedConditions.elementToBeClickable(element.findElement(By.cssSelector("#basic-economy-tray-content-" + (index + 1) + " button"))));
        element.findElement(By.cssSelector("#basic-economy-tray-content-" + (index + 1) + " button")).click();
    }
}