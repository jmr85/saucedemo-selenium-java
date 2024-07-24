package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import listeners.TestListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    
      /**
     * Captures a screenshot of the current web page and saves it as a file.
     * 
     * @param driver The Selenium WebDriver used to capture the screenshot.
     * @param screenshotName The base name for the screenshot file.
     * @return The file path where the screenshot was saved.
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

        /**
     * Captures a screenshot of the current web page, saves it as a file,
     * and adds it to the ExtentReports report.
     * 
     * @param driver The Selenium WebDriver used to capture the screenshot.
     * @param screenshotName The base name for the screenshot file.
     * @param description A description of the screenshot to be displayed in the report.
     */ 
    public static void captureAndAddToReport(WebDriver driver, String screenshotName, String description) {
        String screenshotPath = captureScreenshot(driver, screenshotName);
        TestListener.getTest().addScreenCaptureFromPath(screenshotPath, description);
        TestLogger.log(Status.INFO, "Screenshot capturado y añadido al reporte: " + description);
    }
}
