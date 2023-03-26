package creator.pages.common;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Notification extends Page {
	public Notification() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(css = "header.toast-header strong")
	private WebElement message;
	
	@FindBy(css = "header.toast-header button")
	private WebElement closeButton;
	
	public void close() {
		click(this.closeButton);
	}
	
	public String readMessage() {
		waitForVisible(message);
		return message.getText().trim();
	}
}
