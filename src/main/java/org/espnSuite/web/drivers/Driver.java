package org.espnSuite.web.drivers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Class of listeners
 * @author Pinguin
 */

public class Driver {

    private WebDriver driver;
    private Logger log = Logger.getLogger(Driver.class);

    /**
     * Constructor
     * @param browser
     */
    public Driver(String browser) {
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver83.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                log.info("Error: driver is not in the list.");
                break;
        }
    }

    /**
     * Get value of driver
     * @return driver
     */
    public WebDriver getDriver() {
        return this.driver;
    }
}
