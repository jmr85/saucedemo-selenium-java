package saucedemo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage {
     // Locators
    @FindBy(id = "first-name")
	@CacheLookup
	private WebElement txtFirstName;

    @FindBy(id = "last-name")
	@CacheLookup
	private WebElement txtLastName;

    @FindBy(id = "postal-code")
	@CacheLookup
	private WebElement txtPostalCode;

    @FindBy(id = "continue")
	@CacheLookup
	private WebElement btnContinue;

	// Constructor
	public CheckoutStepOnePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Actions
    public void enterFirstName(String firstName) {
        this.txtFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        this.txtLastName.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        this.txtPostalCode.sendKeys(postalCode);
    }

	public void clickBtnContinue() {
		this.btnContinue.click();
	}
}
