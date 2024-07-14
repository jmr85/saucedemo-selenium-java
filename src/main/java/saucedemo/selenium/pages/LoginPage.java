package saucedemo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	// Locators
	@FindBy(css="#user-name")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(xpath="//input[@id='password']")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(id="login-button")
	@CacheLookup
	WebElement btnLogin;
	
	// Constructor
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	public void enterUserName(String userName) {
	    txtUserName.sendKeys(userName);
	}

	public void enterPassword(String password) {
	    txtPassword.sendKeys(password);
	}

	public void clickLogin() {
	    btnLogin.click();
	}

	
	public void enterCredentials(String userName, String password) {
		txtUserName.sendKeys(userName);
		txtPassword.sendKeys(password);
		btnLogin.click();
	}
}
