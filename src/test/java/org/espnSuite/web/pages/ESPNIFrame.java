package org.espnSuite.web.pages;

import org.espnSuite.web.data.UserDataESPN;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.ArrayList;
import java.util.List;

public class ESPNIFrame extends BasePage{


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

    //@FindBy(css = "section.workflow.workflow-deactivate button.btn.btn-primary ng-isolate-scope")
    @FindBy(css = "button.[did-translate='deactivate.confirmation.buttons.confirm']")
    private WebElement confirmDeleteAccountButton;

    @FindBy(css = "#cancel-account")
    private WebElement deleteAccountButton;

    private List<String> errorSingUP;
    /**
     * Constructor, a factory for producing {@link ElementLocator}s.
     * @param driver Web driver of the page
     */
    public ESPNIFrame(WebDriver driver) {
        super(driver);
        errorSingUP = new ArrayList<>();
        errorSingUP.add("Please enter a password.");
        errorSingUP.add("It looks like that email has already been used to create an account at Disney, ESPN, Marvel, " +
                "or ABC. If this is your email address, just log in to your account.");
    }

    public List<String> getErrorSingUP() {
        return errorSingUP;
    }

    public void singInESPN(String username, String password){
        waitElementVisibility(singInUserNameInput,singInPasswordInput);
        singInUserNameInput.sendKeys(username);
        singInPasswordInput.sendKeys(password);
        clickElement(singInButton);
    }

    public void singUpESPN(UserDataESPN user){
        waitElementVisibility(singUpButton);
        clickElement(singUpButton);
        waitElementVisibility(firstNameInput,lastNameInput,emailInput,
                newPasswordInput,confirmSingUpButton);
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        emailInput.sendKeys((user.getEmail()));
        newPasswordInput.sendKeys(user.getPassword());
        clickElement(confirmSingUpButton);
        sleep(5);
    }

    public List<String> alertMessagesRaised(){  // Check why it is not able to find the list of web elements alerts
        List<String> alertMessagesRaised = new ArrayList<>();
         getDriver().findElements(
                By.cssSelector(".message-error.message.ng-isolate-scope.state-active"))//"div[ng-repeat='item in parsedItems']"))
                .forEach(e-> {
                    alertMessagesRaised.add(e.getText());
                    log.info(e.getText());
                });
         return alertMessagesRaised;
    }

    public void deleteAccountSubmit(){
        moveToElement(deleteAccountButton);
        waitElementVisibility(deleteAccountButton);
        clickElement(deleteAccountButton);
        waitElementVisibility(confirmDeleteAccountButton);
        //clickElement(confirmDeleteAccountButton);
        log.info("Deleting account");
    }
}

