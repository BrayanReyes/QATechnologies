package com.globant.web.pages.packages;

import com.globant.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Package Results Page shows the hotels result list after search a vacational
 * package, also allows to sort and filter the results in order to chose one
 * Hotel option
 */

public class PackageResultPage extends BasePage {

	@FindBy(className = "section-header-main")
	private WebElement hotelResultHeader;

	@FindBy(id = "resultsContainer")
	private WebElement hotelResultContainer;

	@FindBy(id = "searchWizard")
	private WebElement SummaryPanel;

	@FindBy(css = ".col.playback-summary-data .origin.fakeLink")
	private WebElement originLabel;

	@FindBy(css = ".col.playback-summary-data .destination.fakeLink")
	private WebElement destinationLabel;

	@FindBy(className = "mapIcon")
	private WebElement mapIcon;

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

	@FindBy(id = "pill-star3")
	private WebElement starFilterMark;

	@FindBy(css = ".hotelTitle [data-automation=hotel-name]")
	private WebElement hotelNameLabel;

	@FindBy(css = "ul.hotel-price > li.actualPrice.price.fakeLink")
	private WebElement hotelPriceLabel;

	@FindBy(css = ".flex-content.info-and-price .starRating.secondary .visuallyhidden")
	private WebElement starsNumberLabel;

	private WebElement firstHotelSelected;

	private static final String HOTEL_NAME_CSS = ".hotelTitle [data-automation=hotel-name]";
	private static final String HOTEL_PRICE_CSS = "ul.hotel-price > li.actualPrice.price.fakeLink";

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
	public boolean isMapIconPresent() {
		waitElementVisibility(mapIcon);
		return mapIcon.isDisplayed();

	}

	/**
	 * Get Travelers Label
	 *
	 * @return Travelers: String
	 */
	public String getMapIcon() {
		waitElementVisibility(mapIcon);
		return mapIcon.getText();
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
	public void sortByPrice() {
		log.info("The user clicks the \"Sort by Price\" button");
		waitElementVisibility(sortByPriceButton);
		clickElement(sortByPriceButton);
		waitAttributeToBe(updatedResults, "aria-live", "polite");
	}

	/**
	 * Validate if Hotels are sorted by price
	 * @return true: Boolean
	 */
	public boolean validateHotelsPriceOrder() {
		List<WebElement> elements = hotelResultContainer.findElements(By.cssSelector(HOTEL_PRICE_CSS));
		int previousPrice = 0, currentPrice;
		for (WebElement Option : elements) {
			currentPrice = Integer.valueOf(Option.getText().replace(",", "").substring(1));
			if (previousPrice > currentPrice)
				return false;
			previousPrice = currentPrice;
			handleAdvertisement();
		}
		return true;
	}

	/**
	 * Filter By stars
	 */
	public void filterByStars() {
		log.info("The user click the \"Stars\" filter");
		waitElementToBeClickable(filterBy3stars);
		clickElement(filterBy3stars);
		waitAttributeToBe(updatedResults, "aria-live", "assertive");
		waitAttributeToBe(updatedResults, "aria-live", "polite");
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
	 *	@return the first option in the Hotel list
	 */

	public String chooseFirstOption() {
		log.info("The user select the first hotel option");
		firstHotelSelected = resultItem.stream().findFirst().get();
		return firstHotelSelected.findElement(By.cssSelector(HOTEL_NAME_CSS)).getText();

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
	 * Click on Hotel Name Link and pass to Room Details Page
	 */
	public RoomDetailsPage clickHotelNameLink() {
		log.info("The user click the \"Hotel Name\" link");
		waitElementVisibility(firstHotelSelected);
		clickElement(firstHotelSelected);
		String previousHotelName = getHotelName();
		String previousHotelPrice = getHotelPrice();
		String previousStarsNumber = getNumberOfStars();
		changeWindowByIndex(1);
		switchToLastOpenTab(getDriver());
		return new RoomDetailsPage(getDriver(), previousHotelName, previousHotelPrice, previousStarsNumber);
	}

}
