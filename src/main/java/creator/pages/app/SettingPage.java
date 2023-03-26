package creator.pages.app;

import light.automate.core.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SettingPage extends Page {
	public SettingPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//button[@title='Delete Application']")
	private WebElement deleteButton;
	
	@FindBy(xpath = "(//nav[contains(@class,'light-search-bar')]//h4)[2]")
	private WebElement appNameLabel;
	
	@FindBy(xpath = "//b[text()='Environments']/../..//button[normalize-space()='Add']")
	private WebElement addEnvButton;
	
	@FindBy(xpath = "//div[contains(@class,'list-group-item')]//b")
	private List<WebElement> envList;
	
	@FindBy(xpath = "//li/a[text()='Set as Default']")
	private List<WebElement> setAsDefaultOptions;
	
	@FindBy(xpath = "//li/a[normalize-space()='Delete']")
	private List<WebElement> deleteOptions;
	
	public DeleteAppSidebar openDelete() {
		actions
			.moveToElement(appNameLabel)
			.build()
			.perform();
		click(deleteButton);
		return new DeleteAppSidebar();
	}
	
	public String readName() {
		// this click is optional, it is used for delete application button hover issue, not able to click on delete button
		click(appNameLabel);
		return appNameLabel.getText().trim();
	}
	
	public AddEnvironmentSidebar openAddEnvironment() {
		click(appNameLabel);
		click(addEnvButton);
		return new AddEnvironmentSidebar();
	}
	
	
	public void activeEnvironment(String name) {
		String optionXpath = "//b[text()='%s']/ancestor::div//button[@aria-haspopup='menu']";
		waitForVisible(appNameLabel);
		actions
			.moveToElement(appNameLabel)
			.build()
			.perform();
		
		// click on option button
		WebElement option = browser.driver().findElement(By.xpath( String.format(optionXpath, name) ));
		
		option.click();
		
		wait(1/2);
		
		// click on active option
		for (WebElement a : this.setAsDefaultOptions) {
			if (a.isDisplayed()) {
				a.click();
				break;
			}
		}
	}
	
	
	public DeleteEnvironmentDialog deleteEnvironment(String name) {
		for (WebElement env : this.envList) {
			if (env.getText().trim().equals(name)) {
				// click on option button
				env.findElement(By.xpath("ancestor::div[contains(@class,'list-group-item')]//button")).click();
				
				wait(1/2);
				
				// click on active option
				for (WebElement a : this.deleteOptions) {
					if (a.isDisplayed()) {
						a.click();
						break;
					}
				}
				break;
			}
		}
		
		wait(1/2);
		
		return new DeleteEnvironmentDialog();
	}
}
