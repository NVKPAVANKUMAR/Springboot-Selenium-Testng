package com.test.automation.page.component.orange;

import com.aventstack.extentreports.Status;
import com.test.automation.common.UserAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.test.automation.util.extentreport.ExtentTestManager.getTest;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrangeDashBoardPageComponent {
    @Autowired
    private UserAction userAction;

    public void setDriver(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(id = "welcome")
    private WebElement link_Welcome;

    @CacheLookup
    @FindBy(xpath = "//a[.='Logout']")
    private WebElement link_Logout;

    @CacheLookup
    @FindBy(xpath = "//canvas[@class='flot-overlay']")
    private WebElement img_Chart;

    public void logout(long waitTime) {
        userAction.waitForElementToVisible(this.getImg_Chart(), waitTime, "Employee Chart");
        getTest().log(Status.INFO, "LoggedIn Successfully!");
        userAction.click(this.link_Welcome, "Welcome");
        getTest().log(Status.INFO, "Clicked on Welcome button");
        userAction.waitForElementToVisible(this.getLogoutLink(), waitTime, "Logout Link");
        userAction.click(this.link_Logout, "Logout");
        getTest().log(Status.INFO, "Clicked on Logout button");

    }

    public WebElement getWelcomeLink() {
        return link_Welcome;
    }

    public WebElement getLogoutLink() {
        return link_Logout;
    }

    public WebElement getImg_Chart() {
        return img_Chart;
    }
}
