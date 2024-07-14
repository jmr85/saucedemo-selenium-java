package saucedemo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage {

    // Locators
	@FindBy(id = "finish")
	@CacheLookup
	private WebElement btnFinish;

	// Constructor
	public CheckoutStepTwoPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
	}

	// Actions
	public void clickBtnFinish() {
		this.btnFinish.click();
	}
}
