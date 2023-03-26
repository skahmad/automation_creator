package creator.pages.database;

import light.automate.core.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class QueryPage extends Page {
	@FindBy(css = "nav.light-search-bar input[placeholder='Search']")
	private WebElement searchBox;
	
	
	@FindBy(xpath = "//button[@title='Add Query']")
	private WebElement addButton;
	
	@FindBy(css = "div.list-group b")
	private List<WebElement> queryList;
	
	public QueryPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	public QueryAddPage openAddQueryPage() {
		actions
			.moveToElement(addButton)
			.click()
			.build()
			.perform();

		return new QueryAddPage();
	}
	
	public QueryPage search(String title) {
		waitForVisible(searchBox);
		actions
			.moveToElement(searchBox)
			.click()
			.sendKeys(title)
			.sendKeys(Keys.ENTER)
			.build()
			.perform();
		
		wait(1);
		return this;
	}
	
	public boolean isPresent(String title) {
		boolean found = false;
		for (WebElement q : queryList) {
			if (q.getText().trim().equals(title)) {
				found = true;
				break;
			}
		}
		return found;
	}
}
