package creator.codeflow.pages;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RuleAddPage extends Page {
	public RuleAddPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(id = "rule_name_field")
	private WebElement nameField;
	
	@FindBy(id = "rule_expression_field")
	private WebElement expressionField;
	
	@FindBy(id = "rule_description_field")
	private WebElement descriptionField;
	
	@FindBy(id = "add_collection_button")
	private WebElement saveButton;
	
	public RuleAddPage writeName(String name) {
		waitForVisible(nameField);
		write(nameField, name);
		return this;
	}
	
	public RuleAddPage writeExpression(String exp) {
		write(expressionField, exp);
		return this;
	}
	
	public RuleAddPage writeDescription(String description) {
		write(descriptionField, description);
		return this;
	}
	
	public void save() {
		click(saveButton);
	}
}
