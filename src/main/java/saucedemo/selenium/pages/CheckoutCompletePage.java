package saucedemo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {

    // Locators
	@FindBy(xpath = "//span[contains(text(),'Checkout: Complete!')]")
	@CacheLookup
	private WebElement msgFinal;

	@FindBy(id = "back-to-products")
	@CacheLookup
	private WebElement btnBackHome;

	// Constructor
	public CheckoutCompletePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
	}

	// Actions
	public void clickBtnBackHome() {
		this.btnBackHome.click();
	}

	public boolean isMsgFinalDisplayed() {
		return this.msgFinal.isDisplayed();
	}

	public String getMsgFinalText() {
		return this.msgFinal.getText();
	}
}
