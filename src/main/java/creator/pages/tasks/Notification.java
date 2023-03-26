package creator.pages.tasks;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Notification extends Page {
	public Notification() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(css = ".toast-header button")
	private WebElement closeButton;
	
	@FindBy(css = ".toast-body")
	private WebElement msg;
	
	
	public void close() {
		click(closeButton);
	}
	
	public String readMessage() {
		return msg.getText().trim();
	}
}
