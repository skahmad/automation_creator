package creator.pages.manual;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddUseCasePage extends Page {
	public AddUseCasePage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	
	@FindBy(css = "input#use_case_name_field")
	private WebElement useCaseNameField;
	
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement addButton;
	
	@FindBy(xpath = "//strong[text()='Add Use Case']")
	private WebElement title;
	
	@FindBy(xpath = "//header//strong[text()='Add Use Case']/preceding-sibling::button")
	private WebElement closeButton;
	
	public AddUseCasePage writeName(String name) {
		waitForVisible(useCaseNameField);
		
		actions
			.moveToElement(useCaseNameField)
			.click()
			.sendKeys(name)
			.build()
			.perform();
		
		return this;
	}
	
	public AddUseCasePage clearName() {
		waitForVisible(useCaseNameField);
		useCaseNameField.clear();
		return this;
	}
	
	public void add() {
		click(addButton);
	}
	
	public boolean visible() {
		return title.isDisplayed();
	}
	
	public void close() {
		click(closeButton);
	}
}
