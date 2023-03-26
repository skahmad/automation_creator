package creator.pages.app;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteAppSidebar extends Page {
	public DeleteAppSidebar() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//div[@id='delete_app_sidebar']//button[normalize-space()='Delete']")
	private WebElement confirmButton;
	
	public void confirm() {
		click(confirmButton);
	}
}
