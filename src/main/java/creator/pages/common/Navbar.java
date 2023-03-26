package creator.pages.common;

import creator.pages.database.DatabasePage;
import creator.pages.page.PagesPage;
import creator.pages.app.Apps;
import creator.pages.manual.TestCasePage;
import creator.pages.manual.UseCasePage;
import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Navbar extends Page {
	@FindBy(xpath = "//li/a[normalize-space()='App']")
	private WebElement appLink;
	
	@FindBy(xpath = "//li/a[normalize-space()='Test Cases']")
	private WebElement testCaseLink;
	
	@FindBy(xpath = "//li/a[normalize-space()='Use Cases']")
	private WebElement useCaseLink;
	
	@FindBy(xpath = "//li/a[normalize-space()='Page']")
	private WebElement pageLink;
	
	@FindBy(id = "details-button")
	private WebElement advancedButton;
	
	@FindBy(id = "proceed-link")
	private WebElement proceedLink;
	
	@FindBy(xpath = "//li/a[normalize-space()='Database']")
	private WebElement databaseLink;
	
	
	public Navbar() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	public Apps openApp() {
		appLink.click();
		return new Apps();
	}
	
	public TestCasePage openTestCase() {
		testCaseLink.click();
		return new TestCasePage();
	}
	
	public UseCasePage openUseCase() {
		useCaseLink.click();
		return new UseCasePage();
	}
	
	public PagesPage openPage() {
		click(pageLink);
		return new PagesPage();
	}
	
	public DatabasePage openDatabase() {
		click(databaseLink);
		wait(1);
		return new DatabasePage();
	}
	
	
	
	public void httpsSkip() {
		if (advancedButton != null && advancedButton.isDisplayed()) {
			advancedButton.click();
			waitForVisible(proceedLink);
			proceedLink.click();
		}
	}
	
}
