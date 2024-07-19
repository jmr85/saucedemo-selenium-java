package saucedemo.selenium;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import saucedemo.selenium.pages.CartPage;
import saucedemo.selenium.pages.CheckoutCompletePage;
import saucedemo.selenium.pages.CheckoutStepOnePage;
import saucedemo.selenium.pages.CheckoutStepTwoPage;
import saucedemo.selenium.pages.LoginPage;
import saucedemo.selenium.pages.ProductsPage;
import utils.EvidenceCaptureUtil;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test_BonusTrack2 {
	private static final Logger logger = LoggerFactory.getLogger(Test_BonusTrack2.class);
	String url = "https://www.saucedemo.com/";
	WebDriver driver; 
	
	String dirEvidence = "..\\saucedemo-selenium-java\\evidence\\";
	
	@BeforeSuite
	public void abrirNavegador() {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		logger.info("Open Browser and Navigate to URL");
	}
	
	@Test(description = "CP01 - Login Usuario", priority = 1)
	public void loginUsuario() {		
		LoginPage loginPage = new LoginPage(driver);
		logger.info("========== Enter credentials ===========");
		loginPage.enterCredentials("standard_user", "secret_sauce");
	}
	
	@Test(description = "CP02 - Pre Orden", priority = 2)
	public void preOrden() throws IOException {
		// Captura de Pantalla 1		
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence, "1_preOden.jpg");
		
		ProductsPage productListPage = new ProductsPage(driver);
		logger.info("========== click Add to cart ===========");
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
		logger.info("========== click Checkout ===========");
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
		logger.info("========== click Finish ===========");
		// checkout-complete
		CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
		checkoutStepTwoPage.clickBtnFinish();
		
		// Captura de Pantalla 4 - checkout-complete
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence,"4_checkout-complete.jpg");
		CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

		String msgFinal = checkoutCompletePage.getMsgFinalText();
		
		System.out.println("Texto Mensaje FInal ---->>>>>: " + msgFinal);
		
		/* SoftAssert soft = new SoftAssert();
		soft.assertEquals(msgFinal, "xcxcxc");//Checkout: Complete! */

		Assert.assertEquals(msgFinal, "Checkout: Complete!");
		
		// Captura de Pantalla 5 - checkout-complete
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence,"5_checkout-complete.jpg");
	}
	
	@AfterSuite
	public void cerrarNavegador() {
		//driver.close();
	}

}
