package com.guru99.tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Parameters({"username", "password"})
    @Test
    public void verifyUserLoginWithCorrectCredentials(String username, String password) {
        reportUtils.createATestCase("Verify user login with correct credentials test");
        reportUtils.addTestLog(Status.INFO,"Verify user login with correct credentials test started!");
        loginPage.loginToApplication(username, password);
        String expectedTitle = "Guru99 Bank  Manager HomePage";
        String actualTitle = commonDriver.getTitleOfThePage();
        reportUtils.addTestLog(Status.INFO,"Comparing expected webpage title and actual");
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
