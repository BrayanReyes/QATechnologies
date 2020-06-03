package org.espnSuite.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class DeleteAccountIFrame extends BasePage{

    @FindBy(css = "a#cancel-account")
    private WebElement deleteAccountButton;

    @FindBy(css = "section.workflow.workflow-deactivate button.btn.btn-primary ng-isolate-scope")
    private WebElement confirmDeleteAccountButton;

    /**
     * Constructor, a factory for producing {@link ElementLocator}s.
     * @param driver Web driver of the page
     */
    public DeleteAccountIFrame(WebDriver driver) {
        super(driver);
    }

}
