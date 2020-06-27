package org.bookingSuite.web.pages;

import java.util.List;

import org.bookingSuite.web.utils.SearchParameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.ScrollAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Search Results Page handles lodging search results and filters
 * 
 */

public class SearchResultsPage extends BasePage {

	@FindBy(css = ".sr_header h1")
	private WebElement searchResultHeader;

	@FindBy(id = "filter_class")
	private WebElement startsComponent;

	@FindBy(css = "#filter_class .filterelement")
	private List<WebElement> starsFilter;

	@FindBy(css = "#hotellist_inner .sr_item")
	private List<WebElement> searchResultsItem;

	@FindBy(css = "#hotellist_inner > div[data-hotelid]:nth-child(1)")
	private WebElement firstLodgingItem;

	@FindBy(css = ".bui-pagination__list .sr_pagination_item:first-child")
	private WebElement firstResultPage;

	@FindBy(className = "sr-hotel__name")
	private List<WebElement> hotelNameLink;

	@FindBy(className = "bui-review-score__badge")
	private List<WebElement> reviewScore;

	@FindBy(css = "div[class*=\"bui-price-display__value\"]")
	private List<WebElement> reservationPrice;

	@FindBy(css = ".bui-pagination.results-paging .bui-u-inline")
	private List<WebElement> resultPagingLinks;

	@FindBy(css = ".bui-pagination__next-arrow a")
	private WebElement nextPageButton;

	@FindBy(css = "[class*='bui-pagination__item--active'] .bui-u-inline")
	private WebElement pageNumber;

	@FindBy(css = "#hotellist_inner .sr_item .sr-cta-button-row")
	private WebElement chooseRoomButton;

	@FindBy(name = "group_adults")
	private WebElement adultsQuantity;

	@FindBy(name = "group_children")
	private WebElement childrenQuantity;

	@FindBy(css = ".sr-group-recommendation__title_biggest")
	private WebElement adultsAndChildrenText;


	/**
	 * Constructor.
	 * 
	 * @param driver: WebDriver
	 */

	SearchResultsPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Validate if Search Result Header is present
	 *
	 * @return true
	 */
	public boolean searchResultHeaderIsPresent() {
		return searchResultHeader.isDisplayed();
	}

	/**
	 * Get Search Result Header text
	 *
	 * @return Label: String
	 */
	public String getSearchResultHeader() {
		waitElementVisibility(searchResultHeader);
		return searchResultHeader.getText();
	}

	/**
	 * Set a specific number of the stars as a filter and wait until all search
	 * results are refreshed
	 *
	 * @param startsDataValue: String
	 */
	public void setStarsFilter(String startsDataValue) {
		waitElementVisibility(startsComponent);
		moveToElement(startsComponent);
		starsFilter.stream().filter(star -> star.getAttribute("data-value").equals(startsDataValue)).forEach(x -> {
			moveToElement(x);
			clickElement(x);
		});
		
		waitForElementAttribute(firstLodgingItem, "data-class", startsDataValue);
//		waitForElementAttribute(firstLodgingItem, "data-class", searchParameters.getNumberOfStars());
		log.info("The user sets the starts filter.");
	}

	/**
	 * Validate if next page button is clickable
	 *
	 * @return true
	 */
	public boolean nextPageButtonIsClickable() {
		return waitElementToBeClickable(nextPageButton);
	}

	/**
	 * Get Hotel Name, Score and Price for each item after searching
	 *
	 */

	public void checkResults() {

		searchResultsItem.forEach(item -> {

			moveToElement(item);
			String hotelName = item.findElement(By.cssSelector(".sr-hotel__name")).getText();
			String hotelScore = item.findElement(By.cssSelector(".bui-review-score__badge")).getText();
			String hotelPrice = item.findElement(By.cssSelector("div[class*='bui-price-display__value']")).getText();

			log.info("Name: " + hotelName + "  Score: " + hotelScore + "  Price: " + hotelPrice);
		});
	}

	/**
	 * Print the total number of lodgings options found after filtering with their
	 * main information:Hotel Name, Score and Price
	 * 
	 * @return result items: int
	 */
	public int searchResultsItem(String startsDataValue) {
		int initialPageNumber = 1;
		int totalResults = searchResultsItem.size();
		boolean increasedPage = false;

		checkResults();
		while (nextPageButtonIsClickable()) {
			initialPageNumber++;
			moveToElement(nextPageButton);
			clickElement(nextPageButton);
			while (!increasedPage) {
				waitForElementAttribute(firstLodgingItem, "data-class", startsDataValue);
//				getWait().until(ExpectedConditions.attributeToBe(firstLodgingItem, "data-class", searchParameters.getNumberOfStars()));
				if (waitElementVisibility(pageNumber)
						&& pageNumber.getText().equals(String.valueOf(initialPageNumber))) {
					increasedPage = true;

					log.info("The user clicks the \"next result page\" button.");
				}
			}
			checkResults();
			increasedPage = false;
			totalResults += searchResultsItem.size();
		}
		;

		return totalResults;
	}

	/**
	 * Go back to the first results page
	 * 
	 * 
	 */
	public void moveToInitialResultsPage() {
		moveToElement(firstResultPage);
		clickElement(firstResultPage);
		log.info("The user clicks the \"first page\" button.");
	}

	/**
	 * Get the the Reservation guest from a specific item result and remove the
	 * words of this text
	 * 
	 * @return String
	 */
	public String getAdultsChildrenRecommended() {
		return adultsAndChildrenText.getText().replace("Recomendado para ", "");
	}

	/**
	 * Select one option in the result page and click to {chooseRoomButton} to continue the booking process
	 * 
	 * @return LodgingDetailsPage
	 */

	public LodgingDetailsPage clickLodgingOption(int option) {
		handleUsersModal();
		WebElement hotelChosen = searchResultsItem.get(option - 1);
		moveToElement(hotelChosen);
		log.info("The user selects the option " + option + " in the list.");
		String hotelName = hotelChosen.findElement(By.className("sr-hotel__name")).getText();
		moveToElement(hotelChosen);
		WebElement webElement = hotelChosen.findElement(By.className("bui-button__text"));
		waitElementVisibility(webElement);
		clickElement(webElement);
		log.info("The user clicks the \"Choose Room\" Button.");
		String numberOfGuests = getAdultsChildrenRecommended();
		switchToLastOpenTab(getDriver());
		return new LodgingDetailsPage(getDriver(), hotelName, numberOfGuests);
	}

}
