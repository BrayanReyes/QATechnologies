package org.suiteTest.web.iframes.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.Select;

import java.security.Key;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @FindBy(css = ".btn-primary.btn-action.gcw-submit[data-gcw-change-submit-text='Search']")
    private WebElement searchButton;


    @FindBy(css = ".alert.alert-error.validation-alert[aria-hidden='false'] a")
    private List<WebElement> alertMessages;

    /**
     * Contructor, a factory for producing {@link ElementLocator}s.
     * @param driver and url from
     */
    public HomePage(WebDriver driver,String url) {
        super(driver);
        getDriver().get(url);
        adults = new Select (this.adultsWebElement);
        children = new Select (this.childrenWebElement);
    }

    /**
     * Click in Fly Button
     */
    public void flySection(){
        waitElementVisibility(buttonFlights);
        clickElement(buttonFlights);
    }

    /**
     * Click on RoundTrip
     */
    public void setFlyKind(){
        waitElementVisibility(roundTrip);
        clickElement(roundTrip);
    }

    /**
     * Set the place from where you are going to depart
     * @param flyingFrom origin place
     */
    public void setFlyingFrom(String flyingFrom){
        waitElementVisibility(this.flyingFrom);
        this.flyingFrom.sendKeys(flyingFrom );
    }

    /**
     * Set the place Where do you want to fly
     * @param flyingTo destination place
     */
    public void setFlyingTo(String flyingTo){
        waitElementVisibility(this.flyingTo);
        this.flyingTo.sendKeys(flyingTo );
    }

    /**
     * Set date when trip starts four days after today
     */
    public void setDepartingDate(){
        clickElement(this.departingDate);
        String tmpCSS = todayDateIncreasedBy(4);
        WebElement startDate = getDriver().findElement(By.cssSelector(tmpCSS));
        clickElement(startDate);
    }

    /**
     * Set date when the trip ends ten days after today
     */
    public void setReturningDate(){
        clickElement(this.returningDate);
        String tmpCSS = todayDateIncreasedBy(10);
        WebElement endDate = getDriver().findElement(By.cssSelector(tmpCSS));
        clickElement(endDate);
    }

    /**
     * Set the quantity of adults in the fly
     * @param adults quantity
     */
    public void setAdults(String adults){
        try {
            this.adults.selectByValue(adults);
        }
        catch (NoSuchElementException exception){
            log.info("ERROR --> Adults value not allowed:"+adults);
        }
    }

    /**
     * Set the quantity of children in the fly
     * @param children quantity
     */
    public void setChildren(String children){
        try {
            this.children.selectByValue(children);
        }
        catch (NoSuchElementException exception){
            log.info("ERROR --> Children value not allowed: "+children);
        }
    }

    /**
     * Set the age of all childrens
     * @param childrenAge can be an array or single variable
     */
    public void setChildrenAge(String... childrenAge){
        for (int i =0; i<childrenAge.length;i++){
            // ID of CSS Selector generate by Children
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
    }

    /**
     * Click on Search Button
     */
    public void searchFly(){
        clickElement(searchButton);
    }

    public List<String> getAlertMessages(){
        List<String> alertMessagesRaised = new ArrayList<>();
        alertMessages.forEach(alertMessage -> {
            try {
                waitElementVisibility(alertMessage);
                alertMessagesRaised.add(alertMessage.getText());
            } catch (TimeoutException e) {
                log.info("Time out waiting for alert message");
            }
        }
        );
        return alertMessagesRaised;
    }

    /**
     * Check if the param is the same date selected
     * @param dateDeparture to be compared
     * @return boolean
     */
    public boolean checkDepartureDate(String dateDeparture) {
        return (dateDeparture.equals(departingDate.getAttribute("value")));
    }


    /**
     * Check if the param is the same date selected
     * @param dateReturn to be compared
     * @return boolean
     */
    public boolean checkReturnDate(String dateReturn) {
        return (dateReturn.equals(returningDate.getAttribute("value")));
    }
}
