package creator.codeflow.pages;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar extends Page {
	public NavBar() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//a[normalize-space(text())='Rule Collection']")
	private WebElement collectionLink;
	
	@FindBy(xpath = "//a[normalize-space(text())='Rule Group']")
	private WebElement groupLink;
	
	@FindBy(xpath = "//a[normalize-space(text())='Rule']")
	private WebElement ruleLink;
	
	public void gotoCollection() {
		waitForVisible(collectionLink);
		click(collectionLink);
	}
	
	public void gotoGroup() {
		waitForVisible(groupLink);
		click(groupLink);
	}
	
	public void gotoRule() {
		waitForVisible(ruleLink);
		click(ruleLink);
	}
}
