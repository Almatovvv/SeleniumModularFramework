package com.guru99.tests;

import com.aventstack.extentreports.Status;
import com.guru99.pages.LoginPage;
import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigUtils;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenShotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    CommonDriver commonDriver;
    String url;
    WebDriver driver;
    LoginPage loginPage;
    String currentWorkingDirectory;
    String configFileName;
    Properties configProperty;
    String reportFileName;
    ReportUtils reportUtils;
    ScreenShotUtils screenShotUtils;

    @BeforeSuite
    public void preSetup() throws IOException {
        currentWorkingDirectory = System.getProperty("user.dir");
        configFileName = currentWorkingDirectory + "/config/config.properties";
        reportFileName = currentWorkingDirectory + "/reports/TestReport.html";
        configProperty = ConfigUtils.readProperties(configFileName);
        reportUtils = new ReportUtils(reportFileName);
    }

    @BeforeClass
    public void setup() throws Exception {
        url = configProperty.getProperty("baseUrl");
        commonDriver = new CommonDriver(configProperty.getProperty("browserType"));
        driver = commonDriver.getDriver();
        loginPage = new LoginPage(driver);
        screenShotUtils = new ScreenShotUtils(driver);
        commonDriver.navigateToUrl(url);
    }

    @AfterMethod
    public void postTestAction(ITestResult result) throws Exception {
        String testCaseName = result.getName();
        long executionTime = System.currentTimeMillis();
        String screenShotFileName = currentWorkingDirectory + "/screenshots/" + testCaseName + executionTime + ".jpeg";
        if (result.getStatus() == ITestResult.FAILURE){
            reportUtils.addTestLog(Status.FAIL, "One or more steps failed");
            screenShotUtils.captureAndSaveScreenShot(screenShotFileName);
            reportUtils.attachScreenShotToReport(screenShotFileName);
        }
    }

    @AfterClass
    public void tearDown() {
        commonDriver.closeAllBrowser();
    }

    @AfterSuite
    public void postTearDown() {
        reportUtils.flushReport();
    }
}
