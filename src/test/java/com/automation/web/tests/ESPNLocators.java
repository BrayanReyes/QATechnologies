package com.automation.web.tests;

import com.automation.web.driver.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Class for the example locators test.
 * @author Pinguin
 */
public class ESPNLocators {

    public Logger log = Logger.getLogger(ESPNLocators.class);
    public Driver driver;
    public WebDriverWait wait;
    private String browser = "chrome";
    private String url ="https://www.espn.com/?src=com&_adblock=true";

    @BeforeClass
    public void beforeClass() {
        driver = new Driver(browser);
        driver.getDriver().manage().window().maximize();
        driver.getDriver().get(url);
        wait = new WebDriverWait(driver.getDriver(), 120);
    }

    @Test(description = "example locators")
    public void LogIn() {

        log.info("Waiting for user icon");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a#global-user-trigger")));
        // Another way to do it is with id
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("global-user-trigger")));

        //User Icon
        WebElement globalUserIcon = driver.getDriver().findElement(By.cssSelector("a#global-user-trigger"));
        log.info("User icon "+ globalUserIcon.getText());
        globalUserIcon.click();

        WebElement logIn = driver.getDriver().findElement(By.cssSelector("ul.account-management li:nth-child(5) a"));
        log.info(logIn.getText()+" Button");
        logIn.click();

        log.info("Waiting for log in iframe");

        //driver.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //iframe#disneyid-iframe
        WebElement iFrame= driver.getDriver().findElement(By.cssSelector("iframe#disneyid-iframe"));
        driver.getDriver().switchTo().frame(iFrame);

        //Email input
        WebElement email = driver.getDriver().findElement(By.cssSelector("div.field.field-username-email input"));
        log.info(email.getAttribute("placeholder"));

        String mail="qwaszx@espn.com";
        email.sendKeys(mail);

        WebElement password = driver.getDriver().findElement(By.cssSelector("div.field.field-password input"));
        log.info(password.getAttribute("placeholder"));
        password.sendKeys("Espn2020+");

        //button[ng-click='vm.submitLogin()']
        WebElement logInButton = driver.getDriver().findElement(By.cssSelector("button.btn.btn-primary.btn-submit.ng-isolate-scope"));
        log.info(logInButton.getText() + "Button");
        logInButton.click();

        // Switch back to the default page
        driver.getDriver().switchTo().defaultContent();




        log.info("Waiting 3 seconds");
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            log.info("Error at sleeping");
        }


        //User Icon
        WebElement globalUserIcon2 = driver.getDriver().findElement(By.cssSelector("a#global-user-trigger"));
        log.info("User icon "+ globalUserIcon2.getText());
        globalUserIcon2.click();

        log.info("Check Login");
        WebElement welcomeName = driver.getDriver().findElement(By.cssSelector("li.display-user span "));
        log.info("Name "+welcomeName.getText());
        if (welcomeName.getText().equals("King!"))
            log.info("Welcome Mr King");


        //User Icon
        globalUserIcon2.click();

        WebElement logOutButton = driver.getDriver().findElement(By.cssSelector("ul.account-management li:nth-child(9) a"));
        log.info(logOutButton.getText() + "Button");
        logOutButton.click();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("asdf")));

        //Implicit wait
        //driver.getDriver().manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
        //Explicit wait
        //WebDriverWait wait = new WebDriverWait(driver.getDriver(), 120);

        try {
            Thread.sleep(2000);
        }catch (Exception e){
            log.info("Error at sleeping");
        }

    }

    @AfterClass
    public void afterClass() {
        if (driver.getDriver() != null) {
            driver.getDriver().quit();
        }
    }
}
