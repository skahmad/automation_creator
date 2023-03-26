package creator.pages.rest;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RequestAddPage extends Page {
	public RequestAddPage(){
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//input[@id='request_name_add_field']")
	private WebElement nameField;
	
	@FindBy(xpath = "//input[@id='request_url_add_field']")
	private WebElement urlField;
	
	@FindBy(xpath = "//select[@id='request_method_add_select']")
	private WebElement methodField;
	
	@FindBy(xpath = "//strong[text()='Add Request']")
	private WebElement title;
	
	@FindBy(xpath = "//button[@id='add_request_button_add']")
	private WebElement addButton;
	
	@FindBy(xpath = "//strong[text()='Add Request']/../button")
	private WebElement closeButton;
	
	public RequestAddPage writeName(String name) {
		actions
			.moveToElement(nameField)
			.click()
			.sendKeys(name)
			.build()
			.perform();
		return this;
	}
	
	public RequestAddPage writeUrl(String url) {
		actions
			.moveToElement(urlField)
			.click()
			.sendKeys(url)
			.build()
			.perform();
		return this;
	}
	
	public RequestAddPage selectMethod(String method) {
		Select methodSelect = new Select(methodField);
		//methodSelect.selectByVisibleText(method);
		methodSelect.selectByValue(method);
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
