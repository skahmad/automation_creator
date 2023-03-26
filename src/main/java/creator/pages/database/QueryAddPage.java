package creator.pages.database;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QueryAddPage extends Page {
	@FindBy(id = "rule_name_field")
	private WebElement titleField;
	
	@FindBy(id = "rule_query_field")
	private WebElement queryField;
	
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement addButton;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelButton;
	
	
	public QueryAddPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	public QueryAddPage writeTitle(String title) {
		waitForVisible(titleField);
		actions
			.moveToElement(titleField)
			.click()
			.sendKeys(title)
			.build()
			.perform();
		
		return this;
	}
	
	public QueryAddPage writeQuery(String query) {
		waitForVisible(queryField);
		actions
			.moveToElement(queryField)
			.click()
			.sendKeys(query)
			.build()
			.perform();
		
		return this;
	}
	
	public void clickOnAdd() {
		waitForVisible(addButton);
		actions
			.moveToElement(addButton)
			.click()
			.build()
			.perform();
	}
	
}
