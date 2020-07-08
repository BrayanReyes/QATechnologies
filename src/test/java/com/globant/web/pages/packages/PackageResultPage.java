package com.globant.web.pages.packages;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PackageResultPage  extends BasePage {

    @FindBy(className = "section-header-main")
    private WebElement hotelResultHeader;

    @FindBy(id = "resultsContainer")
    private WebElement hotelResultContainer;

    @FindBy (id = "searchWizard")
    private WebElement SummaryPanel;

    @FindBy(css = ".col.playback-summary-data .origin.fakeLink")
    private WebElement originLabel;

    @FindBy(css = ".col.playback-summary-data .destination.fakeLink")
    private WebElement destinationLabel;

    @FindBy(css = ".col.playback-summary-data .travelers.fakeLink span")
    private WebElement travelersLabel;

    @FindBy(id = "sortContainer")
    private WebElement sortContainer;

    @FindBy(css = "li.option:nth-child(3) > button.btn-sort.tab")
    private WebElement sortByPriceButton;

    @FindBy(id = "uitk-live-announce")
    private WebElement updatedResults;

    @FindBy(className = "flex-link-wrap")
    private List<WebElement> resultItem;

    @FindBy(id = "star3")
    private WebElement filterBy3stars;

    @FindBy (id = "pill-star3")
    private WebElement starFilterMark;

    @FindBy(className = "hotelTitle")
    private WebElement hotelNameLink;

    @FindBy(css = ".hotelTitle [data-automation=hotel-name]")
    private WebElement hotelNameLabel;

    @FindBy(css = "ul.hotel-price > li.actualPrice.price.fakeLink")
    private WebElement hotelPriceLabel;

    @FindBy(css = ".flex-content.info-and-price .starRating.secondary .visuallyhidden")
    private WebElement starsNumberLabel;

    WebElement firstHotel;

    private static final String hotelNameCSS=".hotelTitle [data-automation=hotel-name]";
    private static final String hotelPriceCSS= "ul.hotel-price > li.actualPrice.price.fakeLink";
    private static final String starsNumberCSS= ".flex-content.info-and-price .starRating.secondary .visuallyhidden" ;

    /**
     * Constructor
     *
     * @param driver : WebDriver
     */
    public PackageResultPage(WebDriver driver) {
        super(driver);
    }


    /**
     * Validate if Hotel Results Page Header is Present
     *
     * @return true: Boolean
     */
    public boolean isHotelResultHeaderPresent() {
        waitElementVisibility(hotelResultHeader);
        return hotelResultHeader.isDisplayed();

    }

    /**
     * Get Hotel Results Page Header
     *
     * @return Header: String
     */
    public String getHotelResultHeader() {
        waitElementVisibility(hotelResultHeader);
        return hotelResultHeader.getText();
    }


    /**
     * Validate if Hotel Results Container is Present
     *
     * @return true: Boolean
     */
    public boolean isHotelResultContainerPresent() {
        waitElementVisibility(hotelResultContainer);
        return hotelResultContainer.isDisplayed();

    }

    /**
     * Validate if Origin Label is Present
     *
     * @return true: Boolean
     */
    public boolean isOriginLabelPresent() {
        waitElementVisibility(SummaryPanel);
        waitElementVisibility(originLabel);
        return originLabel.isDisplayed();
    }

    /**
     * Get Origin Label
     *
     * @return Origin: String
     */
    public String getOriginLabel() {
        waitElementVisibility(originLabel);
        return originLabel.getText();
    }

    /**
     * Validate if Destination Label is Present
     *
     * @return true: Boolean
     */
    public boolean isDestinationLabelPresent() {
        waitElementVisibility(destinationLabel);
        return destinationLabel.isDisplayed();

    }

    /**
     * Get Destination Label
     *
     * @return Destination: String
     */
    public String getDestinationLabel() {
        waitElementToBeClickable(SummaryPanel);
        waitElementVisibility(destinationLabel);
        return destinationLabel.getText();
    }

    /**
     * Validate if Travelers Label is Present
     *
     * @return true: Boolean
     */
    public boolean isTravelersLabelPresent() {
        waitElementVisibility(travelersLabel);
        return travelersLabel.isDisplayed();

    }

    /**
     * Get Travelers Label
     *
     * @return Travelers: String
     */
    public String getTravelersLabel() {
        waitElementVisibility(travelersLabel);
        return travelersLabel.getText();
    }

    /**
     * Validate if Sort Container is Present
     *
     * @return true: Boolean
     */
    public boolean isSortContainerPresent() {
        waitElementVisibility(sortContainer);
        return sortContainer.isDisplayed();

    }

    /**
     * Click on Sort By Price Button
     */
    public boolean sortByPrice() {
        log.info("The user click the \"Sort by Price\" button");
        waitElementVisibility(sortByPriceButton);
        clickElement(sortByPriceButton);
        waitAttributeToBe(updatedResults,"aria-live","polite");
        List<WebElement> elements = hotelResultContainer.findElements(By.cssSelector("ul.hotel-price > li.actualPrice.price.fakeLink"));
        int previousPrice = 0, currentPrice;
        for (WebElement Option : elements) {
            currentPrice = Integer.valueOf(Option.getText().replace(",", "").substring(1));
            if (previousPrice > currentPrice)
                return false;
            previousPrice = currentPrice;
        }
        return true;
    }

    /**
     * Filter By 3 start
     */
    public void filterByStarts(){
        log.info("The user click the \"Stars\" filter");
        waitElementToBeClickable(filterBy3stars);
        clickElement(filterBy3stars);
        waitAttributeToBe(updatedResults,"aria-live","assertive");
        waitAttributeToBe(updatedResults,"aria-live","polite");
    }

    /**
     * Validate if Filter Mark is Present
     *
     * @return true: Boolean
     */
    public boolean isFilterMarkPresent() {
        waitElementVisibility(starFilterMark);
        return starFilterMark.isDisplayed();

    }

    /**
     * Choose the first result in the list
     *
     */

    public String chooseFirstOption(){
        log.info("The user select the first hotel option");
        firstHotel = resultItem.stream().findFirst().get();
        return
                firstHotel.findElement(By.cssSelector(hotelNameCSS)).getText();

    }


    /**
     * Get the Hotel Name
     *
     * @return Hotel Name: String
     */
    public String getHotelName() {
        waitElementVisibility(hotelNameLabel);
        return hotelNameLabel.getText();
    }

    /**
     * Get the Hotel Price
     *
     * @return Price: String
     */
    public String getHotelPrice() {
        waitElementVisibility(hotelPriceLabel);
        return hotelPriceLabel.getText().trim();
    }

    /**
     * Get the Number Of Stars
     *
     * @return Price: String
     */
    public String getNumberOfStars() {
        waitElementVisibility(starsNumberLabel);
        return starsNumberLabel.getText();
    }


    /**
     * Click on Hotel Name Link
     */
    public RoomDetailsPage clickHotelNameLink() {
        log.info("The user click the \"Hotel Name\" link");
        waitElementVisibility(firstHotel);
       clickElement(firstHotel);
        String previousHotelName = getHotelName();
        String previousHotelPrice = getHotelPrice();
        String previousStarsNumber = getNumberOfStars();
        changeWindowByIndex(1);
        //switchToLastOpenTab(getDriver());
        return new RoomDetailsPage(getDriver(),previousHotelName,previousHotelPrice,previousStarsNumber);
    }



}
