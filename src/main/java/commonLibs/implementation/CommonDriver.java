package commonLibs.implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class CommonDriver {
    private WebDriver driver;
    private int pageLoadTimeout;
    private int elementDetectionTimeout;
    private String currentWorkingDirectory;

    public CommonDriver(String browserType) throws Exception {
        currentWorkingDirectory = System.getProperty("user.dir");
        if (browserType.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", currentWorkingDirectory + "/drivers/msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            throw new Exception("Invalid Browser Type " + browserType);
        }

        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();
    }

    public void navigateToUrl(String url) {
        pageLoadTimeout = 10;
        elementDetectionTimeout = 10;
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
        driver.get(url);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public void setElementDetectionTimeout(int elementDetectionTimeout) {
        this.elementDetectionTimeout = elementDetectionTimeout;
    }

    public void closeAllBrowser() {
        driver.quit();
    }

    public String getTitleOfThePage(){
        return driver.getTitle();
    }
}
