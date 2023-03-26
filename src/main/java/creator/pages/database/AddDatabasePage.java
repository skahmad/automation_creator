package creator.pages.database;

import creator.pages.manual.AddTestCasePage;
import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddDatabasePage extends Page {
	
	@FindBy(id = "connection_name_field")
	private WebElement connection_name_field;
	
	@FindBy(id = "db_type_options")
	private WebElement database_type_select;
	
	@FindBy(id = "host_field")
	private WebElement host_field;
	
	@FindBy(id = "port_field")
	private WebElement port_field;
	
	@FindBy(id = "user_name_field")
	private WebElement user_name_field;
	
	@FindBy(id = "password_field")
	private WebElement password_field;
	
	@FindBy(id = "instance_name_field")
	private WebElement instance_field;
	
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement add_button;
	
	public AddDatabasePage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	public AddDatabasePage writeConnectionName(String name) {
		waitForVisible(connection_name_field);
		actions
			.moveToElement(connection_name_field)
			.click()
			.sendKeys(name)
			.build()
			.perform();
		
		return this;
	}
	
	public AddDatabasePage selectDatabaseType(String type) {
		Select select = new Select(database_type_select);
		select.selectByVisibleText(type);
		return this;
	}
	
	public AddDatabasePage writeHost(String host) {
		actions
			.moveToElement(host_field)
			.click()
			.sendKeys(host)
			.build()
			.perform();
		return this;
	}
	
	public AddDatabasePage writePort(int port) {
		actions
			.moveToElement(port_field)
			.click()
			.sendKeys(String.valueOf(port))
			.build()
			.perform();
		return this;
	}
	
	public AddDatabasePage writeUser(String user) {
		actions
			.moveToElement(user_name_field)
			.click()
			.sendKeys(user)
			.build()
			.perform();
		
		return this;
	}
	
	public AddDatabasePage writePassword(String password) {
		actions
			.moveToElement(password_field)
			.click()
			.sendKeys(password)
			.build()
			.perform();
		return this;
	}
	
	public AddDatabasePage writeInstance(String instance) {
		actions
			.moveToElement(instance_field)
			.click()
			.sendKeys(instance)
			.build()
			.perform();
		
		return this;
	}
	
	public void add() {
		actions
			.moveToElement(add_button)
			.click()
			.build()
			.perform();
	}
}
