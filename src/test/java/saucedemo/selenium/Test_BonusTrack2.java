package saucedemo.selenium;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import listeners.TestListener;
import saucedemo.selenium.pages.CartPage;
import saucedemo.selenium.pages.CheckoutCompletePage;
import saucedemo.selenium.pages.CheckoutStepOnePage;
import saucedemo.selenium.pages.CheckoutStepTwoPage;
import saucedemo.selenium.pages.LoginPage;
import saucedemo.selenium.pages.ProductsPage;
import utils.EvidenceCaptureUtil;
import utils.ExtentManager;
import utils.ScreenshotUtil;
import utils.ScreenshotUtilTestNG;
import utils.TestLogger;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test_BonusTrack2 {
	private static final Logger logger = LoggerFactory.getLogger(Test_BonusTrack2.class);
	String url = "https://www.saucedemo.com/";
	WebDriver driver; 

	ExtentReports extent;
    ExtentTest test;
	
	String dirEvidence = "..\\saucedemo-selenium-java\\evidence\\";
	
	@BeforeSuite
	public void setUp() {
		logger.info("========== setUp ===========");
		logger.info("========== Starting test cases execution ===========");

		driver = new ChromeDriver();
		extent = ExtentManager.getInstance();
		driver.get(url);
		logger.info("Open Browser and Navigate to URL");

		TestLogger.logInfo("Open Browser and Navigate to URL(Extent report)");

		logger.info("Navigated to URL: {}", url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Test(description = "CP01 - User Login", priority = 1)
	public void userLogin() {	
		LoginPage loginPage = new LoginPage(driver);
		TestLogger.logInfo("Enter credentials(Extent report)");

		  // Capturar screenshot antes de hacer clic en el botón de login
        //String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "antes_login_");
        //TestListener.getTest().addScreenCaptureFromPath(screenshotPath, "Antes de hacer clic en Login");

		ScreenshotUtil.captureAndAddToReport(driver, "antes_login(2)_", "Antes de hacer clic en Login(2)");

		loginPage.enterCredentials("standard_user", "secret_sauce");
	}
	
	@Test(description = "CP02 - Pre Orden", priority = 2)
	public void preOrden() throws IOException {

		// Captura de Pantalla 1		
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence, "1_preOden.jpg");
		
		ProductsPage productListPage = new ProductsPage(driver);
		TestLogger.logInfo("click Add to cart(Extent report)");
		productListPage.clickBtnAddToCart();
		// Captura de pantalla 2
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence, "2_preOden.jpg");
		
		productListPage.clickLinkCart();
		// Captura de Pantalla 3
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence,"3_preOden.jpg");
	}
	
	@Test(description = "CP03 - Checkout", priority = 3)
	public void checkOut() throws IOException {
		CartPage cartPage = new CartPage(driver);
		TestLogger.logInfo("click Checkout(Extent report)");
		cartPage.clickBtnCheckout();
		// Captura de Pantalla 1 - checkout-step-one		
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence, "1_checkout-step-one.jpg");
		
		// completar formulario
		CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
		checkoutStepOnePage.enterFirstName("Juan Martin");
		checkoutStepOnePage.enterLastName("Ruiz");	
		checkoutStepOnePage.enterPostalCode("C1167ABC");
	
		// Captura de Pantalla 2 - form complete
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence, "2_form-complete.jpg");
		
		checkoutStepOnePage.clickBtnContinue();
		// Captura de Pantalla 3 - checkout-step-two
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence,"3_checkout-step-two.jpg");
		logger.info("click Finish");
		// checkout-complete
		CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
		checkoutStepTwoPage.clickBtnFinish();
		
		// Captura de Pantalla 4 - checkout-complete
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence,"4_checkout-complete.jpg");
		CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

		ScreenshotUtilTestNG.captureAndEmbedScreenshot(driver, "4b_checkout-complete");

		String msgFinal = checkoutCompletePage.getMsgFinalText();
		logger.info("Final checkout message: {}", msgFinal);
		//TestLogger.logInfo("Final checkout message : {} (Extent report)", msgFinal);

		TestLogger.log(Status.INFO, "Final checkout message : {}", msgFinal);
		
		/* SoftAssert soft = new SoftAssert();
		soft.assertEquals(msgFinal, "xcxcxc");//Checkout: Complete! */

		Assert.assertEquals(msgFinal, "Checkout: Complete!");
		
		// Captura de Pantalla 5 - checkout-complete
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence,"5_checkout-complete.jpg");
	}
	
	@AfterSuite
	public void tearDown() {
		//driver.close();
		extent.flush();
		logger.info("========== tearDown ===========");
	}

}
