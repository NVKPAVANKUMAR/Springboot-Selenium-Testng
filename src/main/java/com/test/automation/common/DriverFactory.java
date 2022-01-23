package com.test.automation.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverFactory {
	
	private static ConfigProperties configProps;
	
	@Autowired
    public DriverFactory(ConfigProperties configProps){
		DriverFactory.configProps = configProps;
    }
	
	public static <T> WebDriver getDriver(Class<T> driverClass) {		
		WebDriver driver = null;
		if(driverClass.equals(FirefoxDriver.class)) {
			driver = getFirefoxDriver();
		} else if(driverClass.equals(ChromeDriver.class)){
			driver = getChromeDriver();
		} else {
			driver = getEdgeDriver();
		}
		return driver;
	}
	
	private static ChromeDriver getChromeDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}
	
	private static FirefoxDriver getFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}

	private static EdgeDriver getEdgeDriver() {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();
	}
}
