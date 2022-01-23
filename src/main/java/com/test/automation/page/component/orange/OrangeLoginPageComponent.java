package com.test.automation.page.component.orange;


import com.aventstack.extentreports.Status;
import com.test.automation.common.UserAction;
import com.test.automation.util.logs.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import static com.test.automation.util.extentreport.ExtentTestManager.getTest;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrangeLoginPageComponent {

    @Autowired
    private UserAction userAction;

    public void setDriver(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(id = "txtUsername")
    private WebElement usernameInputBox;

    @CacheLookup
    @FindBy(id = "txtPassword")
    private WebElement passwordInputBox;

    @CacheLookup
    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    public void login(String username, String password) {
        userAction.enter(this.usernameInputBox, username, "Orange Username");
        getTest().log(Status.INFO, "Entered Username");
        userAction.enter(this.passwordInputBox, password, "Orange Password");
        getTest().log(Status.INFO, "Entered Password");
        userAction.click(this.loginButton, "Orange Login Button");
        getTest().log(Status.INFO,"Clicked on Login button");
    }

    public void validateUrl(String url){
        Assert.assertTrue(url.contains("dashboard"));
    }

    public WebElement getUsernameInputBox() {
        return usernameInputBox;
    }

    public WebElement getPasswordInputBox() {
        return passwordInputBox;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }
}
