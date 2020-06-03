package org.espnSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingInUpIFrame extends BasePage{

    @FindBy(css = "a.btn.btn-secondary.ng-isolate-scope")
    private WebElement singUpButton;

    @FindBy(css = "input[ng-model='vm.model.firstName']")
    private WebElement firstNameInput;

    @FindBy(css = "input[ng-model='vm.model.lastName']")
    private WebElement lastNameInput;

    @FindBy(css = "input[ng-model='model.profile.email']")
    private WebElement emailInput;

    @FindBy(css = "input[ng-model='vm.newPassword']")
    private WebElement newPasswordInput;

    @FindBy(css = "button.btn.btn-primary.ng-scope.ng-isolate-scope")
    private WebElement confirmSingUpButton;

    @FindBy(css = "input[ng-model='vm.username']")
    private WebElement singInUserNameInput;

    @FindBy(css = "input[ng-model='vm.password']")
    private WebElement singInPasswordInput;

    @FindBy(css = "button[ng-click*='vm.submitLogin']")
    private WebElement singInButton;

    /**
     * Constructor, a factory for producing {@link ElementLocator}s.
     * @param driver Web driver of the page
     */
    public SingInUpIFrame(WebDriver driver) {
        super(driver);
    }


    public void singInEPSN(String username, String password){
        waitElementVisibility(singInUserNameInput,singInPasswordInput);
        singInUserNameInput.sendKeys(username);
        singInPasswordInput.sendKeys(password);
        clickElement(singInButton);
    }

}
