package creator.pages.app;

import light.automate.core.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteEnvironmentDialog extends Page {
	public DeleteEnvironmentDialog() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//h5[starts-with(@id,'environment-delete-confirm-dialog')]/../..")
	private WebElement dialog;
	
	@FindBy(xpath = "//button[text()='OK']")
	private WebElement okButton;
	
	public String readMessage() {
		WebElement messageLabel = this.dialog.findElement(By.xpath("//div[@class='modal-body']/h5"));
		return messageLabel.getText().trim();
	}
	
	public void ok() {
		click(okButton);
	}
}
