package com.automation.web.tests;

import com.automation.web.driver.Driver;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TestPagePaul {

    private Logger log = Logger.getLogger(TestPagePaul.class);
    private Driver driver;
    private WebDriverWait wait;
    private List<String> listCss;
    private String browser = "chrome";
    private String url ="http://test-page-paul.herokuapp.com/";

    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        driver = new Driver(browser);
        driver.getDriver().manage().window().maximize();
        driver.getDriver().get(url);
        wait = new WebDriverWait(driver.getDriver(),120);
    }

    @BeforeMethod(alwaysRun = true)
    public void CSSListToCheck(){
        listCss = new ArrayList<String>();
        listCss.add("p:first-of-type"); //Also work with ".container p:first-of-type"
        listCss.add("a:first-of-type");
        listCss.add("li:nth-child(3)"); // Also work with "ul li:nth-child(3)"
        listCss.add("menu li:nth-child(1)"); // Also work with
        listCss.add("dir li:nth-child(3)");
        listCss.add("#but"); // Also work with "[name="foo"]"
        listCss.add("#f0"); //Also work with "[value="Reset 2"]"
        listCss.add("#f3"); //Also work with "input#f3"
        listCss.add("#f11 option:nth-child(3)"); //Also work with "#f11 option:last-child"
        listCss.add("tbody:nth-child(2) tr:nth-child(4) td:last-child");
        listCss.add("tbody:nth-child(1) tr:nth-child(5) td:last-child");
        listCss.add("p:nth-child(23)");
        listCss.add("h3:nth-child(26)");
    }

    @Test(description = "Test to get values from the web page")
    public void test(){
        //Waiting page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(listCss.get(0))));

        //Print all CSS required to get homework points
        listCss.forEach(tmp_css -> {
            WebElement tempWebElement = driver.getDriver().findElement(By.cssSelector(tmp_css));
            log.info("*** CSS: \"" + tmp_css + "\"" + " *** TagName:"+ tempWebElement.getTagName() + "   *** Value: "
                    + tempWebElement.getText().replace("\n", ""));
        });

//        for(String tmp_css:listCss) {
//            WebElement tempWebElement = driver.getDriver().findElement(By.cssSelector(tmp_css));
//            log.info("*** CSS: \"" + tmp_css + "\"" + " *** TagName:"+ tempWebElement.getTagName() + "   *** Value: "
//            + tempWebElement.getText().replace("\n", ""));
//        }
    }


    @AfterClass(alwaysRun = true)
    public void afterClass(){
        if (driver.getDriver()!=null)
            driver.getDriver().quit();
    }



}
