package com.test.automation.test.cases.ui;

import com.test.automation.common.AppConstants;
import com.test.automation.common.ConfigProperties;
import com.test.automation.common.UserAction;
import com.test.automation.page.component.orange.OrangeDashBoardPageComponent;
import com.test.automation.page.component.orange.OrangeLoginPageComponent;
import com.test.automation.test.util.AbstractBaseTest;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

import static com.test.automation.util.extentreport.ExtentTestManager.startTest;

public class OrangeHRM extends AbstractBaseTest {

    private WebDriver driver;

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Nonnull
    private OrangeLoginPageComponent orangeLoginPage;

    @Autowired
    @Nonnull
    private OrangeDashBoardPageComponent orangeDashBoardPageComponent;

    @Autowired
    @Nonnull
    private ConfigProperties configProps;

    @Autowired
    @Nonnull
    private UserAction userAction;

    private long waitTime;

    @BeforeMethod
    public void testSetup() {
        this.driver = context.getBean(WebDriver.class);
        waitTime = (long) AppConstants.TIME_OUT.getValue();
    }
    @Test(priority = 0, description = "Verify page url with correct username and password.", groups = {"regression"})
    public void verifyOrangeHRMTitle(Method method) {
        startTest(method.getName(), "Verify page url Scenario with valid username and password.");
        orangeLoginPage.setDriver(driver);
        userAction.launchUrl(driver, configProps.getProperty("orangehrm.application.url").trim());
        orangeLoginPage.login(configProps.getProperty("orangehrm.username"), configProps.getProperty("orangehrm.password"));
        orangeLoginPage.validateUrl(driver.getCurrentUrl());
    }

    @Test(priority = 1, description = "Valid Login & Logout Scenario with correct username and password.", groups = {"regression"})
    public void verifyOrangeHRMLogin_Logout(Method method) {
        startTest(method.getName(), "Valid Login Scenario with valid username and password.");
        orangeLoginPage.setDriver(driver);
        userAction.launchUrl(driver, configProps.getProperty("orangehrm.application.url").trim());
        orangeLoginPage.login(configProps.getProperty("orangehrm.username"), configProps.getProperty("orangehrm.password"));
        orangeDashBoardPageComponent.setDriver(this.driver);
        userAction.waitForElementToVisible(orangeDashBoardPageComponent.getImg_Chart(), this.waitTime, "Employee Chart");
        orangeDashBoardPageComponent.logout(this.waitTime);
    }


    @AfterMethod
    public void finishTest() {
        this.driver.quit();
    }
}
