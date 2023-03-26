package creator.codeflow.pages;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GroupAddPage extends Page {
	public GroupAddPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(id = "group_name_field")
	private WebElement nameField;
	
	@FindBy(id = "group_description_field")
	private WebElement descriptionField;
	
	@FindBy(id = "add_collection_button")
	private WebElement saveButton;
	
	public GroupAddPage writeName(String name) {
		waitForVisible(nameField);
		write(nameField, name);
		return this;
	}
	
	public GroupAddPage writeDescription(String description) {
		waitForVisible(descriptionField);
		write(descriptionField, description);
		return this;
	}
	
	public void save() {
		waitForVisible(saveButton);
		click(saveButton);
	}
}
