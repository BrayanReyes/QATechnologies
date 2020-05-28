package com.automation.web.tests;

import com.automation.web.driver.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Class for the example locators test.
 * @author juan.montes
 */
public class ExampleLocators {

    public Logger log = Logger.getLogger(ExampleLocators.class);
    public Driver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        driver = new Driver("chrome");
        driver.getDriver().manage().window().maximize();
        driver.getDriver().get("https://www.espn.com/?src=com&_adblock=true");
        wait = new WebDriverWait(driver.getDriver(), 120);
        //Implicit wait
        //driver.getDriver().manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        if (driver.getDriver() != null) {
            driver.getDriver().quit();
        }
    }

    @Test(description = "example locators")
    public void test() {
        //Explicit wait
        //WebDriverWait wait = new WebDriverWait(driver.getDriver(), 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("global-user-trigger")));
        log.info("Waiting for user icon");
        //User Icon
        WebElement globalUserIcon = driver.getDriver().findElement(By.id("global-user-trigger"));
        wait.until(ExpectedConditions.visibilityOf(globalUserIcon));
        log.info(globalUserIcon.getText());
        globalUserIcon.click();
        WebElement entrar = driver.getDriver().findElement(By.cssSelector("div.global-user-container ul.account-management li:nth-child(5) > a:nth-child(1)"));
        wait.until(ExpectedConditions.visibilityOf(entrar));
        log.info(entrar.getText()+" Button");
        entrar.click();

        log.info("Waiting for log in iframe");

        //iframe#disneyid-iframe
        WebElement iFrame= driver.getDriver().findElement(By.cssSelector("iframe#disneyid-iframe"));
        driver.getDriver().switchTo().frame(iFrame);

        // Select an address
        //driver.getDriver().findElement(By.cssSelector("span[data-seleniumid=\"Address0\"]")).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[ng-model='vm.username']")));

        WebElement email = driver.getDriver().findElement(By.cssSelector("input[ng-model='vm.username']"));
        log.info(email.getAttribute("placeholder"));
        //driver.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String mails="july@love.com";
        email.sendKeys(mails);

        WebElement password = driver.getDriver().findElement(By.cssSelector("input[ng-model='vm.password']"));
        log.info(password.getAttribute("placeholder"));
        password.sendKeys("12345678");

        //button[ng-click='vm.submitLogin()']
        WebElement logInButton = driver.getDriver().findElement(By.cssSelector("button[ng-click*='vm.submitLogin']"));
        log.info(logInButton.getText() + "Button");
        //logInButton.click();

        // Switch back to the default page
        driver.getDriver().switchTo().defaultContent();
/**
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.global-user-container ul.account-management li:nth-child(5) > a:nth-child(1)")));
**/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("asdf")));

        //driver.getDriver().manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
        //List<WebElement> languages = driver.getDriver().findElements(By.cssSelector(".link-box Strong"));
        //wait.until(ExpectedConditions.visibilityOfAllElements(languages));
        //languages.forEach(language -> log.info(language.getText()));
    }
}
