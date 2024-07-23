package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import listeners.TestListener;

public class TestLogger {
    private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);
    
    public static void log(Status status, String message, Object... args) {
        String formattedMessage;
        if (args.length > 0) {
            formattedMessage = String.format(message.replace("{}", "%s"), args);
        } else {
            formattedMessage = message;
        }
        
        switch (status) {
            case INFO:
                logger.info(formattedMessage);
                break;
            case PASS:
                logger.info("PASS: " + formattedMessage);
                break;
            case FAIL:
                logger.error("FAIL: " + formattedMessage);
                break;
            case WARNING:
                logger.warn("WARNING: " + formattedMessage);
                break;
            default:
                logger.debug(formattedMessage);
        }
        
        ExtentTest extentTest = TestListener.getTest();
        if (extentTest != null) {
            extentTest.log(status, formattedMessage);
        }
    }
    
    // Métodos de conveniencia para los estados más comunes
    public static void logInfo(String message, Object... args) {
        log(Status.INFO, message, args);
    }
    
    public static void logPass(String message, Object... args) {
        log(Status.PASS, message, args);
    }
    
    public static void logFail(String message, Object... args) {
        log(Status.FAIL, message, args);
    }
    
    public static void logWarning(String message, Object... args) {
        log(Status.WARNING, message, args);
    }
}