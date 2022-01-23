package com.test.automation.config;


import com.test.automation.common.AppConstants;
import com.test.automation.common.ConfigProperties;
import com.test.automation.common.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.test.automation"})
public class BeanConfig {

    @Autowired
    private ConfigProperties configProps;

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertySourcePlaceHolder() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @DependsOn(value = {"configProperties"})
    public WebDriver getDriver() {
        return this.getBrowserDriver();
    }

    private WebDriver getBrowserDriver() {
        WebDriver driver = null;
        try {
            if (configProps.getProperty("browser.name").trim().equalsIgnoreCase(AppConstants.CHROME.getValue().toString())) {
                driver = DriverFactory.getDriver(ChromeDriver.class);
            } else if (configProps.getProperty("browser.name").trim().equalsIgnoreCase(AppConstants.EDGE.getValue().toString())) {
                driver = DriverFactory.getDriver(EdgeDriver.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return driver;
    }
}