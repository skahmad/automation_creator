package creator.codeflow.pages;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CollectionAddPage extends Page {
	public CollectionAddPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	
	@FindBy(id = "collection_name_field")
	private WebElement nameField;
	
	@FindBy(id = "collection_description_field")
	private WebElement descriptionField;
	
	@FindBy(id = "add_collection_button")
	private WebElement saveButton;
	
	public CollectionAddPage writeName(String name) {
		waitForVisible(nameField);
		actions
			.moveToElement(nameField)
			.click()
			.sendKeys(name)
			.build()
			.perform();
		
		return this;
	}
	public CollectionAddPage writeDescription(String description) {
		waitForVisible(descriptionField);
		actions
			.moveToElement(descriptionField)
			.click()
			.sendKeys(description)
			.build()
			.perform();
		return this;
	}
	
	public void save() {
		waitForVisible(saveButton);
		click(saveButton);
	}
}
