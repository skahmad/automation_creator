package creator.pages.manual;

import light.automate.core.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCaseDetails extends Page {
	public TestCaseDetails() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(css = "div#test_case_details_sidebar h5")
	private WebElement nameLabel;
	
	@FindBy(xpath = "//div[@id='test_case_details_sidebar']//span[1]")
	private WebElement uidLabel;
	
	@FindBy(css = "div#test_case_details_sidebar input[type='text']")
	private WebElement updateNameField;
	
	@FindBy(css = "div#test_case_details_sidebar header button")
	private WebElement closeButton;
	
	public TestCaseDetails updateName(String name) {
		actions
			.moveToElement(nameLabel)
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
		
		wait(1);
		
		return this;
	}
	
	public void close() {
		actions
			.moveToElement(closeButton)
			.click()
			.build()
			.perform();
	}
	
	public String uid() {
		return uidLabel.getText().trim();
	}
	
	public String readName() {
		return nameLabel.getText().trim();
	}
}
