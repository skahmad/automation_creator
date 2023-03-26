package creator.pages.app;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddApp extends Page {
	public AddApp() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//input[@id='app_name_field']")
	private WebElement nameField;
	
	@FindBy(xpath = "//input[@id='api_url_field']")
	private WebElement urlField;
	
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement addButton;
	
	public void setAppName(String name) {}
	
	public void setVersion(String v) {}
	
	public AddApp writeName(String name) {
		actions
			.moveToElement(nameField)
			.click()
			.sendKeys(name)
			.build()
			.perform();
		
		return this;
	}
	
	public AddApp writeApiUrl(String url) {
		actions
			.moveToElement(urlField)
			.click()
			.sendKeys(url)
			.build()
			.perform();
		
		return this;
	}
	
	public void ok() {
		click(addButton);
	}
}
