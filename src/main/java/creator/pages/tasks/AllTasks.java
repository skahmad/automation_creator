package creator.pages.tasks;

import light.automate.core.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AllTasks extends Page {
	public AllTasks() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(css = "div.list-group a b")
	private List<WebElement> tasks;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchBox;
	
	@FindBy(css = "button[title='clear search']")
	private WebElement clearSearchButton;
	
	@FindBy(css = "button[title='Filter']")
	private WebElement filterButton;
	
	@FindBy(css = "button[title='Refresh']")
	private WebElement refreshButton;
	
	@FindBy(css = "button[title='Add Task']")
	private WebElement addButton;
	
	public AllTasks writeOnSearchBox(String text) {
		actions
			.moveToElement(searchBox)
			.click()
			.sendKeys(text)
			.sendKeys(Keys.ENTER)
			.build()
			.perform();
		
		return this;
	}
	
	public AddTaskSidebarPage clickOnAddButton() {
		click(addButton);
		return new AddTaskSidebarPage();
	}
	
	public AllTasks clickOnFilterButton() {
		click(filterButton);
		return this;
	}
	
	public AllTasks clickOnRefreshButton() {
		click(refreshButton);
		return this;
	}
	
	public AllTasks clickOnClearSearchButton() {
		click(clearSearchButton);
		return this;
	}
	
	public List<WebElement> getTasks() {
		return tasks;
	}
}
