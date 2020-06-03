package org.espnSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class HomePage extends BasePage{

    @FindBy(css = "a#global-user-trigger")
    private WebElement iconUser;

    @FindBy(css = "ul.account-management li:nth-child(5) a")
    private WebElement singInButton;

    @FindBy(css = "ul.account-management li:nth-child(9) a")
    private WebElement logOutButton;

    @FindBy(css = "#global-header li.display-user span")
    private WebElement welcomeName;

    @FindBy(css = "#global-header .display-user")
    private WebElement welcomeMessage;

    @FindBy(css = "ul.account-management li:nth-child(5) a")
    private WebElement espnProfileButton;

    @FindBy(css = "#disneyid-iframe")
    private WebElement singInUpIFrame;

    /**
     * Constructor, a factory for producing {@link ElementLocator}s.
     * @param driver Web driver of the page
     */
    public HomePage(WebDriver driver,String url) {
        super(driver);
        getDriver().get(url);
    }

    public ESPNIFrame goToSingInUp(){
        waitElementVisibility(iconUser);
        clickElement(iconUser);
        clickElement(singInButton);
        return new ESPNIFrame(switchToIFrame(singInUpIFrame));
    }


    public boolean validateUserLogged(String userName) {
        waitElementVisibility(iconUser);
        clickElement(iconUser);
        waitElementVisibility(welcomeName);
        log.info("Validar 1");
        log.info(welcomeName.getText());
        return (welcomeName.getText().equals(userName+"!"));
    }

    public void logOut() {
        waitElementVisibility(iconUser);
        clickElement(iconUser);
        clickElement(logOutButton);
    }

    public boolean validateUserLogOut() {  // Log out validation is failing due to user icon is too fast
        waitElementVisibility(iconUser);
        clickElement(iconUser);
        waitElementVisibility(logOutButton);
        log.info(welcomeMessage.getText());
        return (welcomeMessage.getText().equals("Welcome!"));
    }

    public ESPNIFrame goToDeleteAccount() {
        waitElementVisibility(iconUser);
        clickElement(iconUser);
        waitElementVisibility(espnProfileButton);
        clickElement(espnProfileButton);
        return new ESPNIFrame(switchToIFrame(singInUpIFrame));
    }
}
