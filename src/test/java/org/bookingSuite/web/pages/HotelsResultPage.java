package org.bookingSuite.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Accommodation Results Page handles search results and filters
 * 
 * 
*/

public class HotelsResultPage extends BasePage {
	
//	@FindBy(css = "#results_prev_next p")
//	private WebElement totalItemsFound;
//
	@FindBy(id="filter_class")
	private WebElement startComponent;

	@FindBy(css = "#filter_class .filterelement")
	private List<WebElement> starsFilter;
	
	@FindBy(css = "#hotellist_inner .sr_item")
	private List<WebElement> searchResultsItem;

	@FindBy(className = "sr-hotel__name")
	private List<WebElement> hotelNameLink;
	
	@FindBy(className = "bui-review-score__badge")
	private List<WebElement> reviewScore;
	
	@FindBy(css = "div[class*=\"bui-price-display__value\"]")
	private List<WebElement> reservationPrice;
	
	@FindBy(css = ".bui-pagination.results-paging .bui-u-inline")
	private List<WebElement> resultPagingLinks;
	
	@FindBy(css = "#hotellist_inner .sr_item .sr-cta-button-row")
	private List<WebElement> chooseRoomButton;

	@FindBy(name = "group_adults")
	private WebElement quantityAdults;

	@FindBy(name = "group_children")
	private WebElement quantityChildren;

	public HotelsResultPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Set a specific number of the stars as a filter
     *
     * @param
      */
	public void setStarsFilter(String startsDataValue) {
		waitElementVisibility(startComponent);
		moveToElement(startComponent);
		int numberOfResultPages = resultPagingLinks.size();
		for(int i=0; i < starsFilter.size(); i++) {
			if(starsFilter.get(i).getAttribute("data-value").equals(startsDataValue)) {
				moveToElement(starsFilter.get(i));
				clickElement(starsFilter.get(i));
				break;
			}
		}

//		starsFilter.stream().filter(star -> star.getAttribute("data-value").equals(startsDataValue))
//				.forEach(x -> moveToElement(x));

//		starsFilter.stream().filter(star -> star.getAttribute("data-value").equals(startsDataValue))
//				.map(x -> {
//					moveToElement(x); return x;
//				});


	}

	public void checkResults(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Modify in order to return boolean value when some info is missing.
		searchResultsItem.forEach(item -> {
					moveToElement(item);
					String hotelName=item.findElement(By.cssSelector(".sr-hotel__name")).getText();
					String hotelScore=item.findElement(By.cssSelector(".bui-review-score__badge")).getText();
					String hotelPrice=item.findElement(By.cssSelector("div[class*='bui-price-display__value']")).getText();
					log.info("Name "+hotelName+"  Score "+hotelScore+"  Price "+hotelPrice);
				}
		);
	}


	public HotelDetailsPage clickOption(int option){

		WebElement hotelChosen = searchResultsItem.get(option-1);
		String hotelName=hotelChosen.findElement(By.cssSelector(".sr-hotel__name")).getText();
		moveToElement(hotelChosen);
		WebElement webElement = hotelChosen.findElement(By.className("bui-button__text"));
		waitElementVisibility(webElement);
		clickElement(webElement);
		String adulstQ = getAdultsQuantity();
		String childrenQ = getChildrenQuantity();
		switchToLastOpenTab(getDriver());
		return new HotelDetailsPage(getDriver(),hotelName,adulstQ,childrenQ);
	}

	private String getAdultsQuantity(){
		moveToElement(quantityAdults);
		waitElementVisibility(quantityAdults);
		return quantityAdults.getText();
	}


	private String getChildrenQuantity(){
		moveToElement(quantityChildren);
		waitElementVisibility(quantityChildren);
		return quantityChildren.getText();
	}
}
