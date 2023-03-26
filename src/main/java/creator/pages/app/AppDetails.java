package creator.pages.app;

import creator.pages.manual.TestCasePage;
import creator.pages.rest.RequestPage;
import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class AppDetails extends Page {
	public AppDetails() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	
	@FindBy(xpath = "//span[text()='Test cases']")
	private WebElement testCaseLink;
	
	@FindBy(xpath = "//span[text()='Use cases']")
	private WebElement useCaseLink;
	
	@FindBy(xpath = "//span[text()='Pages']")
	private WebElement pagesLink;
	
	
	@FindBy(xpath = "//span[text()='Requests']")
	private WebElement requestLink;
	
	@FindBy(xpath = "//span[text()='Connections']")
	private WebElement connectionLink;
	
	@FindBy(css = "div#version_list ul li button")
	private WebElement newVersionButton;
	
	@FindBy(xpath = "//div[@id='new_version']//input")
	private WebElement versionTextField;
	
	@FindBy(xpath = "//div[@id='new_version']/..//button")
	private WebElement versionSaveButton;
	
	@FindBy(css = "div#version_list ul li a>span")
	private List<WebElement> versions;
	
	@FindBy(xpath = "//div[@id='version_list']/button[2]")
	private WebElement versionDropdown;
	
	@FindBy(xpath = "//div[@title='App Setting']/button")
	private WebElement settingButton;
	
	public TestCasePage openTestCases() {
		click(testCaseLink);
		return new TestCasePage();
	}
	
	public TestCasePage openUseCases() {
		click(useCaseLink);
		return new TestCasePage();
	}
	
	public RequestPage openRequest() {
		click(requestLink);
		return new RequestPage();
	}
	
	public AddVersionDialog newVersion() {
		click(versionDropdown);
		wait(1);
		click(newVersionButton);
		return new AddVersionDialog();
	}
	
	@Deprecated
	public AppDetails writeVersion(String version) {
		actions
			.moveToElement(versionTextField)
			.click()
			.sendKeys(version)
			.build()
			.perform();
		
		return this;
	}
	
	@Deprecated
	public void save() {
		click(versionSaveButton);
	}
	
	public List<String> versions() {
		click(versionDropdown);
		wait(1);
		return this
			.versions
			.stream()
			.map(e->{
				return e.getText().trim();
			})
			.collect(Collectors.toList());
	}
	
	public SettingPage openSetting() {
		click(settingButton);
		return new SettingPage();
	}
	
	public void openPage() {
		click(pagesLink);
	}
	
	public void openConnections() {
		click(connectionLink);
	}
}
