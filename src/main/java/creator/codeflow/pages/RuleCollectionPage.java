package creator.codeflow.pages;

import light.automate.core.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RuleCollectionPage extends Page {
	public RuleCollectionPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	
	@FindBy(xpath = "//nav//button[normalize-space(text())='Add']")
	private WebElement addButton;
	
	@FindBy(css = "div.list-group-item b")
	private List<WebElement> collectionsList;
	
	@FindBy(css = "nav.light-search-bar input[placeholder='Search']")
	private WebElement searchField;
	
	@FindBy(css = "nav.light-search-bar button[title='Clear Filter']")
	private WebElement refreshButton;
	
	
	public CollectionAddPage openAdd() {
		waitForVisible(addButton);
		click(addButton);
		return new CollectionAddPage();
	}
	
	public RuleCollectionPage refresh() {
		waitForVisible(refreshButton);
		click(refreshButton);
		return this;
	}
	
	public RuleCollectionPage search(String name) {
		waitForVisible(searchField);
		actions
			.moveToElement(searchField)
			.click()
			.sendKeys(name)
			.sendKeys(Keys.ENTER)
			.build()
			.perform();
		
		return this;
	}
	
	public boolean isPresent(String name) {
		boolean found = false;
		
		for(WebElement c: collectionsList) {
			if (c.getText().trim().equals(name)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	
	public RuleGroupPage view(String name) {
		boolean found = false;
		for(WebElement c: collectionsList) {
			if (c.getText().trim().equals(name)) {
				c.click();
				found = true;
				break;
			}
		}
		
		if (!found) {
			throw new RuntimeException("Collection not found - " + name);
		}
		
		return new RuleGroupPage();
	}
}
