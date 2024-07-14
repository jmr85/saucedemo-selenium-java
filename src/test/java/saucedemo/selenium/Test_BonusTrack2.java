package saucedemo.selenium;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import saucedemo.selenium.pages.LoginPage;
import saucedemo.selenium.pages.ProductListPage;
import utils.EvidenceCaptureUtil;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_BonusTrack2 {
	
	String url = "https://www.saucedemo.com/";
	WebDriver driver; 
	
	String dirEvidence = "..\\saucedemo-selenium-java\\evidence\\";
	
	@BeforeSuite
	public void abrirNavegador() {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Test(description = "CP01 - Login Usuario", priority = 1)
	public void loginUsuario() {		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterCredentials("standard_user", "secret_sauce");
	}
	
	@Test(description = "CP02 - Pre Orden", priority = 2)
	public void preOrden() throws IOException {
		// Captura de Pantalla 1		
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence, "1_preOden.jpg");
		
		ProductListPage productListPage = new ProductListPage(driver);
		
		productListPage.clickBtnAddToCart();
		// Captura de pantalla 2
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence, "2_preOden.jpg");
		
		productListPage.clickLinkCart();
		// Captura de Pantalla 3
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence,"3_preOden.jpg");
		
	}
	
	@Test(description = "CP03 - Checkout", priority = 3)
	public void checkOut() throws IOException {
		WebElement btnCheckout = driver.findElement(By.id("checkout"));
		btnCheckout.click();
		
		// Captura de Pantalla 1 - checkout-step-one		
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence, "1_checkout-step-one.jpg");
		
		// completar formulario
		WebElement txtFirstName = driver.findElement(By.id("first-name"));
		txtFirstName.sendKeys("Juan Martin");
		
		WebElement txtLastName = driver.findElement(By.id("last-name"));
		txtLastName.sendKeys("Ruiz");
		
		WebElement txtPostalCode = driver.findElement(By.id("postal-code"));
		txtPostalCode.sendKeys("C1167ABC");
		
		// Captura de Pantalla 2 - form complete
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence, "2_form-complete.jpg");
		
		WebElement btnContinue = driver.findElement(By.id("continue"));
		btnContinue.click();
	
		// Captura de Pantalla 3 - checkout-step-two
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence,"3_checkout-step-two.jpg");
		
		// checkout-complete
		WebElement btnFinish = driver.findElement(By.id("finish"));
		btnFinish.click();
		
		// Captura de Pantalla 4 - checkout-complete
		EvidenceCaptureUtil.getScreenshot(driver, dirEvidence,"4_checkout-complete.jpg");
		
		WebElement msgFinal = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your order!')]"));
		
		System.out.println("Texto Mensaje FInal ---->>>>>: " + msgFinal.getText());
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(msgFinal.getText(), "xcxcxc");//Thank you for your order!
	}
	
	@AfterSuite
	public void cerrarNavegador() {
		//driver.close();
	}

}
