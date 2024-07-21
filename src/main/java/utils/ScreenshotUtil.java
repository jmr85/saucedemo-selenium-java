package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

// embed the screenshots within the HTML of the TestNG report
public class ScreenshotUtil {
    public static void captureAndEmbedScreenshot(WebDriver driver, String screenshotName) {
        if(driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                // Guardar el archivo si es necesario
                FileUtils.copyFile(screenshotFile, new File("test-output/screenshots/" + screenshotName + ".png"));
                
                String base64Screenshot = 
                    Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(screenshotFile));
                String htmlImageTag = "<img src=\"data:image/png;base64," + base64Screenshot + "\" width=\"50%\" />";
                String htmlLinkTag = "<a href='screenshots/" + screenshotName + ".png' target='_blank'>View Screenshot</a>";
                
                // Agregar la imagen y el enlace al reporte
                Reporter.log(htmlImageTag + "<br>" + htmlLinkTag);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
