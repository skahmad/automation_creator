package creator.pages.tasks;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddTaskSidebarPage extends Page {
	public AddTaskSidebarPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(id = "task-title-field")
	private WebElement nameField;
	
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement addButton;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelButton;
	
	public AddTaskSidebarPage writeName(String text) {
		actions
			.moveToElement(nameField)
			.click()
			.sendKeys(text)
			.build()
			.perform();
		
		return this;
	}
	
	public AddTaskSidebarPage clearName() {
		nameField.clear();
		return this;
	}
	
	public void clickOnAdd() {
		click(addButton);
	}
	
	public boolean isVisible() {
		return nameField.isDisplayed();
	}
	
	public void close() {
		click(cancelButton);
	}
}
