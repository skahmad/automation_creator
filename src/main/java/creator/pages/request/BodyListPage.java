package creator.pages.request;

import light.automate.core.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BodyListPage extends Page {
	public BodyListPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	
	@FindBy(xpath = "//button[@title='Add Body']")
	private WebElement addButton;
	
	@FindBy(xpath = "//strong[text()='Request Bodies']/preceding-sibling::button")
	private WebElement closeButton;
	
	@FindBy(css = "div#reqeust_bodies_sidebar div.item_title b")
	private List<WebElement> bodyNames;
	
	@FindBy(xpath = "//button[@title='Refresh body']")
	private WebElement refreshButton;
	
	@FindBy(xpath = "//input[@placeholder='Search body']")
	private WebElement searchBodyField;
	
	public BodyAddPage openAdd() {
		click(addButton);
		return new BodyAddPage();
	}
	
	public BodyListPage refresh(){
		click(refreshButton);
		return this;
	}
	
	public BodyListPage search(String name){
		actions
			.moveToElement(searchBodyField)
			.click()
			.sendKeys(name)
			.build()
			.perform();
		
		return this;
	}
	public BodyConfirmDialog delete(String name){
		boolean found = false;
		
		for (WebElement bodyName : bodyNames) {
			if (bodyName.getText().trim().equals(name)) {
				WebElement deleteButton = bodyName.findElement(By.xpath("ancestor::div[contains(@class,'item_title')]//button[contains(@class,'danger')]"));
				click(deleteButton);
				found = true;
				break;
			}
		}
		
		if (!found) {
			throw new RuntimeException("Body not found : " + name);
		}
		return new BodyConfirmDialog();
	}
	
	public void close() {
		click(closeButton);
	}
}
