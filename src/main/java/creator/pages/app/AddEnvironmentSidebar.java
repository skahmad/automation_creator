package creator.pages.app;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEnvironmentSidebar extends Page {
	public AddEnvironmentSidebar() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(id = "env_name_field")
	private WebElement nameField;
	
	@FindBy(id = "env_url_field")
	private WebElement urlField;
	
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement addButton;
	
	@FindBy(xpath = "//strong[text()='Add Environment']/preceding-sibling::button")
	private WebElement closeButton;
	
	public AddEnvironmentSidebar writeName(String name) {
		waitForVisible(nameField);
		write(nameField, name);
		return this;
	}
	public AddEnvironmentSidebar writeApiUrl(String url) {
		write(urlField, url);
		return this;
	}
	
	public void save() {
		click(addButton);
	}
	
	public void cancel() {
		click(closeButton);
	}
}
