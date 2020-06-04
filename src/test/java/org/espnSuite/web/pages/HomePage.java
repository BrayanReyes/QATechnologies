package org.espnSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class HomePage extends BasePage{

    @FindBy(css = "a#global-user-trigger")
    private WebElement iconUser;

    @FindBy(css = "ul.account-management li:nth-child(5) a")
    private WebElement singInButton; //loginOption

    @FindBy(css = "ul.account-management li:nth-child(9) a")
    private WebElement logOutButton;  //logoutOption

    @FindBy(css = "ul.account-management li:nth-child(5) a")
    private WebElement espnProfileButton; //espnProfileOption
    
    @FindBy(css = "#global-header li.display-user span")
    private WebElement welcomeName;

    @FindBy(css = "#global-header .display-user")
    private WebElement welcomeMessage;

    @FindBy(css = "#disneyid-iframe")
    private WebElement singInUpIFrame;

    //**July estuvo aqui**//
    @FindBy(css = "head.at-element-marker:nth-child(1) title") // #global-header h1 a
    private WebElement homePageTitle;
    
    /**
     * Constructor, a factory for producing {@link ElementLocator}s.
     * @param driver Web driver of the page
     */
    public HomePage(WebDriver driver,String url) {
        super(driver);
        getDriver().get(url);
    }
    
    //**July estuvo aqui**//

	/**
	 * Get ESPN Page title
	 * 
	 * @return String text title
	 */
	public String getTitle() {
		log.info("Get ESPN Home Page title");
		waitElementVisibility(homePageTitle);
		return homePageTitle.getText();
	}
	
	/**
	 * Get ESPN Profile Option title
	 * 
	 * @return String text title
	 */
	public String getESPNprofileTitle() {
		log.info("Get ESPN Profile Option title");
		waitElementVisibility(espnProfileButton);
		return espnProfileButton.getText();
	}
	
	/**
	 * Get Log In Option title
	 * 
	 * @return String text title
	 */
	public String getLoginTitle() {
		log.info("Get Log In Option title");
		waitElementVisibility(singInButton);
		return singInButton.getText();
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
