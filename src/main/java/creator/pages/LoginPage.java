package creator.pages;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {
	public LoginPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(id = "name-input")
	private WebElement usernameField;
	
	@FindBy(id = "password-input")
	private WebElement passwordField;
	
	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginButton;
	
	
	public void writeUsername(String username) {
		usernameField.sendKeys(username);
	}
	
	public void writePassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickOnLogin() {
		loginButton.click();
	}
}
