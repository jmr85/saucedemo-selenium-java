package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

// embed the screenshots within the HTML of the TestNG report
public class ScreenshotUtil {
    private static final String SCREENSHOTS_DIR = "test-output/screenshots/";

    public static void captureAndEmbedScreenshot(WebDriver driver, String screenshotName) {
        if(driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                // Asegurarse de que el directorio existe
                new File(SCREENSHOTS_DIR).mkdirs();
                
                // Guardar el archivo
                File destFile = new File(SCREENSHOTS_DIR + screenshotName + ".png");
                FileUtils.copyFile(screenshotFile, destFile);
                
                // Crear una ruta relativa para el informe HTML
                String relativePathToScreenshot = "screenshots/" + screenshotName + ".png";
                
                // Crear etiquetas HTML para la imagen y el enlace
                String htmlImageTag = "<img src='" + relativePathToScreenshot + "' width='300px' />";
                String htmlLinkTag = "<a href='" + relativePathToScreenshot + "' target='_blank'>See Screenshot</a>";
                
                // Agregar la imagen y el enlace al reporte
                Reporter.log(htmlImageTag + "<br>" + htmlLinkTag);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
