package creator.pages.database;

import light.automate.core.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DatabasePage extends Page {
	@FindBy(css = "nav.light-search-bar input[placeholder='Search']")
	private WebElement searchBox;
	
	@FindBy(xpath = "//button[@title='Add Connection']")
	private WebElement add_button;
	
	@FindBy(css = "div.list-group div.list-group-item b")
	private List<WebElement> connectionList;
	
	public DatabasePage(){
		PageFactory.initElements(browser.driver(), this);
	}
	
	public AddDatabasePage openAdd() {
		click(add_button);
		return new AddDatabasePage();
	}
	
	public QueryPage view(String s) {
		boolean found = false;
		for (WebElement c : connectionList) {
			if(c.getText().trim().equals(s)) {
				found = true;
				actions
					.moveToElement(c)
					.click()
					.build()
					.perform();
				break;
			}
		}
		
		if (!found) {
			throw new RuntimeException("Database connection not found : " + s);
		}
		return new QueryPage();
	}
	
	public DatabasePage search(String connectionName) {
		waitForVisible(searchBox);
		searchBox.clear();
		actions
			.moveToElement(searchBox)
			.click()
			.sendKeys(connectionName)
			.sendKeys(Keys.ENTER)
			.build()
			.perform();
		wait(1);
		return this;
	}
	
	public DatabasePage testConnection(String connection) {
		for (WebElement c : connectionList) {
			if (c.getText().trim().equals(connection)) {
				WebElement testConnectionButton = c.findElement(By.xpath("./ancestor::div[contains(@class,'list-group-item')]//button[@title='Test Connection']"));
				click(testConnectionButton);
				break;
			}
		}
		
		return this;
	}
}
