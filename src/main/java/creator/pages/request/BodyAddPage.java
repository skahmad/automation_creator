package creator.pages.request;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BodyAddPage extends Page {
	public BodyAddPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	
	@FindBy(css = "div#add_request_body_sidebar input#body_name_field")
	private WebElement nameField;
	
	@FindBy(css = "div#add_request_body_sidebar textarea")
	private WebElement bodyField;
	
	
	@FindBy(css = "div#add_request_body_sidebar button#add_body_button_add")
	private WebElement addButton;
	

	public void add() {
		click(addButton);
	}
	
	public BodyAddPage writeName(String name){
		actions
			.moveToElement(nameField)
			.click()
			.sendKeys(name)
			.build()
			.perform();
		return this;
	}
	public BodyAddPage writeBody(String body){
		actions
			.moveToElement(bodyField)
			.click()
			.sendKeys(body)
			.build()
			.perform();
		
		return this;
	}
	public BodyConfirmDialog delete(String name){
		return new BodyConfirmDialog();
	}
}
