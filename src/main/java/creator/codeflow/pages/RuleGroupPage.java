package creator.codeflow.pages;

import light.automate.core.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RuleGroupPage extends Page {
	public RuleGroupPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	
	@FindBy(xpath = "//nav//button[normalize-space(text())='Add']")
	private WebElement addButton;
	
	@FindBy(css = "div.list-group-item b")
	private List<WebElement> ruleList;
	
	@FindBy(css = "nav.light-search-bar input[placeholder='Search']")
	private WebElement searchField;
	
	@FindBy(css = "nav.light-search-bar button[title='Clear Filter']")
	private WebElement refreshButton;
	
	
	public RuleGroupPage refresh() {
		waitForVisible(refreshButton);
		click(refreshButton);
		return this;
	}
	
	public RuleGroupPage search(String name) {
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
		for (WebElement r: ruleList) {
			if (r.getText().trim().equals(name)) {
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	public GroupAddPage openAdd() {
		waitForVisible(addButton);
		click(addButton);
		return new GroupAddPage();
	}
	
	
	public RulePage view(String name) {
		boolean found = false;
		for(WebElement g: ruleList) {
			if (g.getText().trim().equals(name)) {
				click(g);
				found = true;
				break;
			}
		}
		
		if (!found) {
			throw new RuntimeException("Rule Group not found");
		}
		
		return new RulePage();
	}
}
