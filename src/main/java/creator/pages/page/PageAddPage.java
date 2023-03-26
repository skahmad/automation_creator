package creator.pages.page;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;

public class PageAddPage extends Page {
	public PageAddPage(){
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//input[@id='page_name_field']")
	private WebElement nameField;
	
	@FindBy(xpath = "//footer[@class='b-sidebar-footer']//button[text()='Add']")
	private WebElement addButton;
	
	public PageAddPage writeName(String name) {
		actions
			.moveToElement(nameField)
			.click()
			.sendKeys(name)
			.build()
			.perform();
		
		
		return this;
	}
	
	public void add() {
		click(addButton);
	}
}
