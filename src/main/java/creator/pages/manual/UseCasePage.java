package creator.pages.manual;

import light.automate.core.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UseCasePage extends Page {
	public UseCasePage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//div[contains(@class,'list-group-item')]//b")
	private List<WebElement> usecaseList;
	
	@FindBy(xpath = "//button[@title='Add Use case']")
	private WebElement addButton;
	
	@FindBy(xpath = "//h4[normalize-space()='Use Cases']/../following-sibling::div//input")
	private WebElement searchField;
	
	
	// todo need to change update use case
	public AddUseCasePage edit(String useCase) {
		boolean found  = false;
		for (WebElement webElement : usecaseList) {
			if (webElement.getText().trim().equals(useCase)) {
				webElement.findElement(By.xpath("../..//button[contains(@class,'btn-light')]")).click();
				found = true;
				break;
			}
		}
		
		if (!found) {
			throw new RuntimeException("Use case not found!");
		}
		// todo need to change updateUseCase
		return new AddUseCasePage();
	}
	
	public TestCasePage view(String useCase) {
		boolean found  = false;
		for (WebElement webElement : usecaseList) {
			if (webElement.getText().trim().equals(useCase)) {
				webElement.click();
				found = true;
				break;
			}
		}
		
		if (!found) {
			throw new RuntimeException("Use case not found!");
		}
		return new TestCasePage();
	}
	
	public AddUseCasePage add() {
		click(addButton);
		return new AddUseCasePage();
	}
	
	public UseCasePage search(String name) {
		actions
			.moveToElement(searchField)
			.click()
			.sendKeys(name)
			.sendKeys(Keys.ENTER)
			.build()
			.perform();
		
		wait(1);
		
		return this;
	}
}
