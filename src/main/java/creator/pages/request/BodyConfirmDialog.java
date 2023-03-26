package creator.pages.request;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BodyConfirmDialog extends Page {
	public BodyConfirmDialog() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(css = "div#modal-confirm-dialog header h5")
	private WebElement title;
	
	@FindBy(css = "div#modal-confirm-dialog form h5")
	private WebElement description;
	
	@FindBy(xpath = "//div[@id='modal-confirm-dialog']//button[normalize-space()='OK']")
	private WebElement okButton;
	
	@FindBy(xpath = "//div[@id='modal-confirm-dialog']//button[normalize-space()='OK']")
	private WebElement cancelButton;
	
	
	public void ok() {
		waitForVisible(title);
		click(okButton);
	}
	public void cancel() {
		click(cancelButton);
	}
	public String readTitle() {
		waitForVisible(title);
		return title.getText().trim();
	}
	public String readDescription() {
		waitForVisible(description);
		return description.getText().trim();
	}
	
}
