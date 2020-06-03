package org.espnSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(css = "a#global-user-trigger")
    private WebElement iconUser;

    @FindBy(css = "ul.account-management li:nth-child(5) a")
    private WebElement singInButton;

    @FindBy(css = "ul.account-management li:nth-child(9) a")
    private WebElement logOutButton;

    @FindBy(css = "li.display-user span")
    private WebElement welcomeName;
    //getText should be the same as your First Name

    @FindBy(css = "ul.account-management li:nth-child(5) a")
    private WebElement espnProfileButton;
    /**
     * Constructor, a factory for producing {@link ElementLocator}s.
     * @param driver Web driver of the page
     */
    public HomePage(WebDriver driver,String url) {
        super(driver);
        getDriver().get(url);
    }



}
