package saucedemo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	// Locators
	@FindBy(id = "add-to-cart-sauce-labs-bike-light")
	@CacheLookup
	private WebElement btnAddToCart;

	@FindBy(xpath = "//a[@data-test='shopping-cart-link']")
	@CacheLookup
	private WebElement linkCart;

	// Constructor
	public ProductsPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
	}

	// Actions
	public void clickBtnAddToCart() {
		this.btnAddToCart.click();
	}
	
	public void clickLinkCart() {
		this.linkCart.click();
	}

}
