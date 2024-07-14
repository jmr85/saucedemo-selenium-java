package saucedemo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    // Locators
	@FindBy(id = "checkout")
	@CacheLookup
	private WebElement btnCheckout;

	// Constructor
	public CartPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
	}

	// Actions
	public void clickBtnCheckout() {
		this.btnCheckout.click();
	}
}
