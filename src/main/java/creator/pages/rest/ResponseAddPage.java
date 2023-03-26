package creator.pages.rest;

import light.automate.core.pages.Page;
import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResponseAddPage extends Page {
	public ResponseAddPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(css = "input#response_name_add_field")
	private WebElement nameField;
	
	@FindBy(css = "input#response_status_add_field")
	private WebElement statusField;
	
	@FindBy(css = "textarea#response_body_add_field")
	private WebElement bodyField;
	
	@FindBy(css = "button#add_response_add")
	private WebElement addButton;
	
	@FindBy(xpath = "//strong[text()='Add Response']")
	private WebElement title;
	
	@FindBy(xpath = "//strong[text()='Add Response']/../button")
	private WebElement closeButton;
	
	/**
	 * write name
	 * @param s name of the response
	 * @return ResponseAddPage an instance of this class
	 */
	public ResponseAddPage writeName(String s) {
		
		
		List<WebElement> tableDataValues = new ArrayList<>();
		
		Optional<WebElement> foundElement = tableDataValues
			.stream()
			.filter(e->{
				return e.getText().trim().equals(s);
			})
			.findAny();
		
		assert foundElement.isPresent(): "element not found";
		
		
		
		actions
			.moveToElement(nameField)
			.click()
			.sendKeys(s)
			.build()
			.perform();
		return this;
	}
	
	public ResponseAddPage writeStatus(String status) {
		actions
			.moveToElement(statusField)
			.click()
			.sendKeys(status)
			.build()
			.perform();
		return this;
	}
	
	public ResponseAddPage writeBody(String body) {
		actions
			.moveToElement(bodyField)
			.click()
			.sendKeys(body)
			.build()
			.perform();
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
	
	public ResponseAddPage clearName() {
		nameField.clear();
		return this;
	}
	
	public ResponseAddPage clearStatus() {
		statusField.clear();
		return this;
	}
	
	public ResponseAddPage clearBody() {
		bodyField.clear();
		return this;
	}
}
