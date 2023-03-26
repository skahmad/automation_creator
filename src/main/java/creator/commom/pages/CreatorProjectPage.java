package creator.commom.pages;

import creator.codeflow.pages.NavBar;
import creator.pages.NavBarFactory;
import creator.pages.app.Apps;
import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatorProjectPage extends Page {
	public CreatorProjectPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(css = "nav.creator-navbar a[href='#/creator/projects']")
	private WebElement creatorLogo;
	
	@FindBy(css = "nav.creator-navbar a[href='#/creator/app']")
	private WebElement appLink;
	
	@FindBy(xpath = "//h4/b[text()='Code Flows']")
	private WebElement codeFlowCard;
	
	
	public void open() {
		waitForVisible(creatorLogo);
		click(creatorLogo);
	}
	
	public void openApp() {
		waitForVisible(appLink);
		click(appLink);
	}
	
	public void selectApp(String name) {
		Apps apps = new Apps();
		apps.view(name);
	}
	
	public NavBar openClodFlow() {
		waitForVisible(codeFlowCard);
		click(codeFlowCard);
		return NavBarFactory.getCodeFlowNavBar();
	}
}
