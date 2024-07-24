package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import listeners.TestListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    
      /**
     * Captures a screenshot of the current web page and returns it as a Base64 encoded string.
     * 
     * @param driver The Selenium WebDriver used to capture the screenshot.
     * @return The Base64 encoded string of the screenshot.
     */
    public static String captureScreenshotAsBase64(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }

    /**
     * Captures a screenshot of the current web page as Base64,
     * and adds it to the ExtentReports report.
     * 
     * @param driver The Selenium WebDriver used to capture the screenshot.
     * @param screenshotName The base name for the screenshot.
     * @param description A description of the screenshot to be displayed in the report.
     */ 
    public static void captureAndAddToReport(WebDriver driver, String screenshotName, String description) {
        String base64Screenshot = captureScreenshotAsBase64(driver);
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String screenshotTitle = screenshotName + dateName;
        
        TestListener.getTest().log(Status.INFO, description,
            MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot, screenshotTitle).build());
        TestLogger.log(Status.INFO, "Screenshot capturado y añadido al reporte: " + description);
    }
}