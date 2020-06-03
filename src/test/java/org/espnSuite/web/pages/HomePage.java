package org.espnSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.ws.WebEndpoint;

public class HomePage extends BasePage{

    @FindBy(css = "a#global-user-trigger")
    private WebElement iconUser;

    @FindBy(css = "ul.account-management li:nth-child(5) a")
    private WebElement singInButton;

    @FindBy(css = "ul.account-management li:nth-child(9) a")
    private WebElement logOutButton;

    @FindBy(css = "#global-header li.display-user span")
    private WebElement welcomeName;

    //@FindBy(css = "#global-header li.display-user")
    @FindBy(css = ".tools ul.account-management li:nth-child(1)")
    private WebElement welcomeMessage;

    @FindBy(css = "ul.account-management li:nth-child(5) a")
    private WebElement espnProfileButton;

    @FindBy(css = "#disneyid-iframe")
    private WebElement singInUpIFrame;

    @FindBy(css = "#disneyid-wrapper")
    private WebElement deleteAccountIFrame;

    /**
     * Constructor, a factory for producing {@link ElementLocator}s.
     * @param driver Web driver of the page
     */
    public HomePage(WebDriver driver,String url) {
        super(driver);
        getDriver().get(url);
    }

    public SingInUpIFrame goToSingIn(){
        clickElement(iconUser);
        clickElement(singInButton);
        return new SingInUpIFrame(switchToIFrame(singInUpIFrame));
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
        waitElementVisibility(logOutButton);
        clickElement(logOutButton);
    }

    public boolean validateUserLoggOut() {
        waitElementVisibility(iconUser);
        clickElement(iconUser);
        waitElementVisibility(welcomeMessage);
        log.info("Validar 2");
        log.info(welcomeMessage.getText());
        return (welcomeMessage.getText().equals("Welcome!"));
    }
}
