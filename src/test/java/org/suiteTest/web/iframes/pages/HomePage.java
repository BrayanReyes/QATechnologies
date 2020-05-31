package org.suiteTest.web.iframes.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.Select;

import java.security.Key;
import java.time.LocalDate;

/**
 * Class of Main Page
 * @author Pinguin
 */
public class HomePage extends BasePage{


    @FindBy(id = "tab-flight-tab-hp")
    private WebElement buttonFlights;

    @FindBy(id ="flight-type-roundtrip-label-hp-flight")
    private WebElement roundTrip;

    @FindBy(id = "flight-origin-hp-flight")
    private WebElement flyingFrom;

    @FindBy(id = "flight-destination-hp-flight")
    private WebElement flyingTo;

    @FindBy(id ="flight-departing-hp-flight")
    private WebElement departingDate;

    @FindBy(id = "flight-returning-hp-flight")
    private WebElement returningDate;

    @FindBy(id = "flight-adults-hp-flight")
    private WebElement adultsWebElement;
    private Select adults ;

    @FindBy(id = "flight-children-hp-flight")
    private WebElement childrenWebElement;
    private Select children ;

    /**
     * Contructor, a factory for producing {@link ElementLocator}s.
     * @param driver
     */
    public HomePage(WebDriver driver,String url) {
        super(driver);
        getDriver().get(url);
        adults = new Select (this.adultsWebElement);
        children = new Select (this.childrenWebElement);
    }

    public void flySection(){
        waitElementVisibility(buttonFlights);
        clickElement(buttonFlights);
    }

    // Use enum
    public void setFlyKind(){
        waitElementVisibility(roundTrip);
        clickElement(roundTrip);
    }

    public void setFlyingFrom(String flyingFrom){
        waitElementVisibility(this.flyingFrom);
        this.flyingFrom.sendKeys(flyingFrom+Keys.TAB);
    }

    public void setFlyingTo(String flyingTo){
        waitElementVisibility(this.flyingTo);
        this.flyingTo.sendKeys(flyingTo+Keys.TAB);
    }

    public void setDepartingDate(){
        this.departingDate.click();
        String tmpCSS = todayDateIncreasedBy(4);
        log.info(tmpCSS);
        WebElement startDate = getDriver().findElement(By.cssSelector(tmpCSS));
        clickElement(startDate);
    }

    public void setReturningDate(){
        this.returningDate.click();
        String tmpCSS = todayDateIncreasedBy(10);
        WebElement endDate = getDriver().findElement(By.cssSelector(tmpCSS));
        clickElement(endDate);
    }

    public void setAdults(String adults){
        try {
            this.adults.selectByValue(adults);
        }
        catch (NoSuchElementException exception){
            log.info("ERROR --> Adults value not allowed:"+adults);
        }
    }

    public void setChildren(String children){
        try {
            this.children.selectByValue(children);
        }
        catch (NoSuchElementException exception){
            log.info("ERROR --> Children value not allowed: "+children);
        }
    }

    public void setChildrenAge(String... childrenAge){
        for (int i =0; i<childrenAge.length;i++){
            String tmpCSS = "#flight-age-select-"+(i+1)+"-hp-flight";
            WebElement childAgeWE = getDriver().findElement(By.cssSelector(tmpCSS));
            Select childAgeS = new Select(childAgeWE);
            try {
                childAgeS.selectByValue(childrenAge[i]);
            }
            catch (NoSuchElementException exception){
                log.info("ERROR --> Adults value not allowed "+childrenAge[i]);
            }
        }
        sleep(10);
    }

    private String todayDateIncreasedBy(int days) {
        LocalDate increasedDate= LocalDate.now().plusDays(days).minusMonths(1);
        String tmpCSS= "button[data-year='"+increasedDate.getYear()+"']" +
                "[data-month='"+increasedDate.getMonthValue()+"']" +
                "[data-day='"+increasedDate.getDayOfMonth()+"']";
        return tmpCSS;
    }
}
