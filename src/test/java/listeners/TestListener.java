package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.ExtentManager;
import utils.TestLogger;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        TestLogger.log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //extentTest.get().pass("Test passed");
        TestLogger.logPass("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //extentTest.get().fail(result.getThrowable());
        TestLogger.logFail("Test failed: " + result.getMethod().getMethodName());
        // Mas detalles sobre el fallo
        TestLogger.log(Status.FAIL, "Exception: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // extentTest.get().skip("Test skipped");
        TestLogger.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        TestLogger.log(Status.INFO, "Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        TestLogger.log(Status.INFO, "Test suite finished: " + context.getName());
        extent.flush();
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
}