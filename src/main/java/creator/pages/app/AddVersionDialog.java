package creator.pages.app;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddVersionDialog extends Page {
	public AddVersionDialog() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//div[normalize-space()='Enter version']/preceding-sibling::input")
	private WebElement versionField;
	
	@FindBy(xpath = "//button[text()='OK']")
	private WebElement okButton;
	
	public AddVersionDialog writeVersion(String version) {
		waitForVisible(versionField);
		write(versionField, version);
		return this;
	}
	
	public void ok() {
		click(okButton);
	}
}
