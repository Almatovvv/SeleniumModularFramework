package com.guru99.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="uid")
    private WebElement userId;

    @FindBy(name="password")
    private WebElement userPassword;

    @FindBy(name="btnLogin")
    private WebElement loginButton;

    public void loginToApplication(String username, String password){
        elementControl.sendKeys(userId, username);
        elementControl.sendKeys(userPassword, password);
        elementControl.clickElement(loginButton);
    }
}
