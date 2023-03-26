package creator.pages.manual;

import light.automate.core.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TestCasePage extends Page {
	public TestCasePage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//button[@title='Add Test case']")
	private WebElement addButton;
	
	@FindBy(xpath = "//h4[text()=' Test cases ']/../following-sibling::div//input")
	private WebElement search_field;
	
	@FindBy(xpath = "//div[contains(@class,'list-label')]/span")
	private List<WebElement> testCaseList;
	
	@FindBy(css = "div.list-container>p")
	private WebElement useCaseNameLabel;
	
	@FindBy(xpath = "//input[@placeholder='Enter usecase name to update']")
	private WebElement updateNameField;
	
	public AddTestCasePage add() {
		click(addButton);
		return new AddTestCasePage();
	}
	
	public TestCasePage search(String testcase) {
		actions
			.moveToElement(search_field)
			.click()
			.sendKeys(testcase)
			.build()
			.perform();
		
		return this;
	}
	
	public TestCaseDetails view(String testcase) {
		for (WebElement t : testCaseList) {
			if (t.getText().trim().equals(testcase)) {
				click(t);
				break;
			}
		}
		
		return new TestCaseDetails();
	}
	
	public List<String> testCases() {
		return testCaseList
			.stream()
			.map(t->t.getText().trim())
			.collect(Collectors.toList());
	}
	
	@Deprecated
	public TestCasePage updateUseCase(String name) {
		actions
			.moveToElement(useCaseNameLabel)
			.doubleClick()
			.build()
			.perform();
		
		wait(1);
		
		updateNameField.clear();
		
		actions
			.moveToElement(updateNameField)
			.click()
			.sendKeys(name)
			.sendKeys(Keys.ENTER)
			.build()
			.perform();
		
		return this;
	}
	
	public String readUseCaseName() {
		return useCaseNameLabel.getText().trim();
	}
}
