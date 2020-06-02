package com.automation.web.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Driver class
 * 
 * @author july.moreno
 */
public class Driver {

	private WebDriver driver;
	private Logger log = Logger.getLogger(Driver.class);

	/**
	 * Constructor.
	 * 
	 * @param browser String
	 */
	/**
	 * Constructor
	 * 
	 * @param browser
	 */
	public Driver(String browser) {
		switch (browser) {
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
	 * Get the driver.
	 * 
	 * @return {@link WebDriver}
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

}