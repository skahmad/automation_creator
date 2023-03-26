package creator.pages.manual;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddTestCasePage extends Page {
	public AddTestCasePage() {
		PageFactory.initElements(browser.driver(), this);
	}
	@FindBy(xpath = "//input[@id='test_case_name_field']")
	private WebElement nameField;
	
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement addButton;
	
	@FindBy(xpath = "//header/strong[text()='Add Test Case']")
	private WebElement title;
	
	@FindBy(xpath = "//header/strong[text()='Add Test Case']//../button")
	private WebElement closeButton;
	
	public AddTestCasePage writeName(String name) {
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
	
	public boolean isVisible() {
		return title.isDisplayed();
	}
	
	public void close() {
		click(closeButton);
	}
}
