package commonLibs.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShotUtils {

    private TakesScreenshot camera;

    public ScreenShotUtils(WebDriver driver) {
        camera = (TakesScreenshot) driver;
    }

    public void captureAndSaveScreenShot(String fileName) throws Exception {
        fileName = fileName.trim();
        File imgFile, tmpFile;
        imgFile = new File(fileName);
        if (imgFile.exists()) {
            throw new Exception("File already exist");
        }
        tmpFile = camera.getScreenshotAs(OutputType.FILE);
        FileUtils.moveFile(tmpFile, imgFile);

    }
}
