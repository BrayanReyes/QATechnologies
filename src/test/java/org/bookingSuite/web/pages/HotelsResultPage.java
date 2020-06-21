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
	
	@FindBy(css = "#results_prev_next p")
	private WebElement totalItemsFound;
	
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
	
	
	public HotelsResultPage(WebDriver driver) {
		super(driver);
	}
	
//	/**
//	 * Set a specific number of the stars as a filter
//     * 
//     * @param  
//      */
//	public void setStarsFilter(String startsDataValue) {
//		waitListOfElementsVisibility(searchResults);
//		int numberOfResultPages = resultPagingLinks.size();
//		for(int i=0; i < starsFilter.size(); i++) {
//			if(starsFilter.get(i).getAttribute("data-value").equals(startsDataValue)) {
//				moveToElement(starsFilter.get(i));
//				starsFilter.get(i).click();
//				break;
//			}
//		}
//		//Wait to the page to refreshed after filtering
//		resultPagingLinks =getWait().until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".bui-pagination.results-paging .bui-u-inline"), numberOfResultPages));
//	}



	
	

}
