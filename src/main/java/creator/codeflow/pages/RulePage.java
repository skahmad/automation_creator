package creator.codeflow.pages;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RulePage extends Page {
	public RulePage() {
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
	
	public RuleAddPage openAdd() {
		click(addButton);
		return new RuleAddPage();
	}
	
	public RulePage refresh() {
		click(refreshButton);
		return this;
	}
	
	public RulePage search(String name) {
		waitForVisible(searchField);
		writeAndEnter(searchField, name);
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
}
