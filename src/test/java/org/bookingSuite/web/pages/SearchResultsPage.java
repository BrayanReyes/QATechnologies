package org.bookingSuite.web.pages;

import java.util.List;

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

	@FindBy(css = ".bui-pagination__next-arrow a") // .bui-pagination__link.paging-next
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
		for (int i = 0; i < starsFilter.size(); i++) {
			if (starsFilter.get(i).getAttribute("data-value").equals(startsDataValue)) {
				moveToElement(starsFilter.get(i));
				clickElement(starsFilter.get(i));
				getWait().until(ExpectedConditions.attributeToBe(firstLodgingItem, "data-class", "5"));
				break;
			}

		}

//		starsFilter.stream().filter(star -> star.getAttribute("data-value").equals(startsDataValue))
//				.forEach(x -> moveToElement(x));

//		starsFilter.stream().filter(star -> star.getAttribute("data-value").equals(startsDataValue))
//				.map(x -> {
//					moveToElement(x); return x;
//				});

		log.info("User set the starts filter");
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

		// Modify in order to return boolean value when some info is missing.
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
	public int searchResultsItem() {
		int initialPageNumber = 1;
		int totalResults = searchResultsItem.size();
		boolean increasedPage = false;

		checkResults();
		while (nextPageButtonIsClickable()) {
			initialPageNumber++;
			moveToElement(nextPageButton);
//			log.info(searchResultsItem.size());
			clickElement(nextPageButton);
			while (!increasedPage) {
				if (waitElementVisibility(pageNumber)
						&& pageNumber.getText().equals(String.valueOf(initialPageNumber))) {
					increasedPage = true;
					
					log.info("Click to the next result page");
				}
			}
			checkResults();
			increasedPage = false;
//			getWait().until(ExpectedConditions.attributeToBe(firstLodgingItem, "data-class", "5"));
//			log.info(searchResultsItem.size());
			totalResults += searchResultsItem.size();
		};

		return totalResults;
	}

	/**
	 * Go back to the first results page
	 * 
	 * 
	 */
	public void moveToInitialResultsPage() {
		moveToElement(firstResultPage);
		waitElementToBeClickable(firstResultPage);
		clickElement(firstResultPage);
		log.info("User click first page button");
	}

//	/**
//	 * Get Adults quantity 
//     *
//     */
//	
//	private String getAdultsQuantity(){
//		moveToElement(adultsQuantity);
//		waitElementVisibility(adultsQuantity);
//		return adultsQuantity.getText();
//	}
//
//	/**
//	 * Get Children quantity 
//     *
//     */
//
//	private String getChildrenQuantity(){
//		moveToElement(childrenQuantity);
//		waitElementVisibility(childrenQuantity);
//		return childrenQuantity.getText();
//	}
//	

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
	 * Click to {chooseRoomButton} to continue the booking process
	 * 
	 * @return LodgingDetailsPage
	 */

	public LodgingDetailsPage clickLodgingOption(int option) {

		WebElement hotelChosen = searchResultsItem.get(option - 1);
		moveToElement(hotelChosen);
//		getWait().until(ExpectedConditions.attributeToBe(firstLodgingItem, "data-class", "5"));
		String hotelName = hotelChosen.findElement(By.className("sr-hotel__name")).getText();
		moveToElement(hotelChosen);
		WebElement webElement = hotelChosen.findElement(By.className("bui-button__text"));
		waitElementVisibility(webElement);
		clickElement(webElement);
//		String numberOfAdults = getAdultsQuantity();
//		String numberOfChildren = getChildrenQuantity();
		String numberOfGuests = getAdultsChildrenRecommended();
		switchToLastOpenTab(getDriver());
		return new LodgingDetailsPage(getDriver(), hotelName, numberOfGuests);
	}

}
