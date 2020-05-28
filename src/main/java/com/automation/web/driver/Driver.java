package com.automation.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Driver class
 * @author juan.montes
 */
public class Driver {

	private WebDriver driver;

	/**
	 * Constructor.
	 * @param browser : String
	 */
	public Driver (String browser) {
		switch (browser) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
				driver = new FirefoxDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver83.exe");
				driver = new ChromeDriver();
				break;
			default:
				break;
		}
	}

	/**
	 * Get the driver.
	 * @return {@link WebDriver}
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

}