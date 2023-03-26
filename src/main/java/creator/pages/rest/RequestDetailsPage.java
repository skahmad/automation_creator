package creator.pages.rest;

import creator.pages.request.BodyListPage;
import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestDetailsPage extends Page {
	public RequestDetailsPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//li/a[text()='Request' and @role='tab']")
	private WebElement requestTab;
	
	@FindBy(xpath = "//li/a[text()='Response' and @role='tab']")
	private WebElement responseTab;
	
	@FindBy(xpath = "//li/a[text()='Raw' and @role='tab']")
	private WebElement rawTab;
	
	@FindBy(xpath = "//button[@title='Add response']")
	private WebElement addResponseButton;
	
	@FindBy(xpath = "//strong[text()='Request Details']")
	private WebElement title;
	
	@FindBy(xpath = "//strong[text()='Request Details']/../button")
	private WebElement closeButton;
	
	@FindBy(xpath = "//div[@id='details_request_sidebar']//div[@class='h5 text-bold']")
	private WebElement nameLabel;
	
	
	@FindBy(css = "div#more_options>button")
	private WebElement optionButton;
	
	@FindBy(xpath = "//a[normalize-space()='Bodies' and @role='menuitem']")
	private WebElement bodyListOption;
	
	/**
	 * Change tab
	 * @param request can be request, response and raw
	 * @return RequestDetailsPage an instance of this page
	 */
	public RequestDetailsPage changeTab(String request) {
		switch (request.trim().toLowerCase()) {
			case "request":
				click(requestTab);
				break;
				
			case "response":
				click(responseTab);
				break;
			
			case "raw":
				click(rawTab);
				break;
		}
		return this;
	}
	
	/**
	 * Click on add icon to open add response
	 * @return RequestDetailsPage an instance of this class
	 */
	public ResponseAddPage addResponse() {
		click(addResponseButton);
		return new ResponseAddPage();
	}
	
	public BodyListPage openBodyList(){
		click(optionButton);
		wait(1/2);
		click(bodyListOption);
		return new BodyListPage();
	}
	
	public boolean visible() {
		return title.isDisplayed();
	}
	
	public String readName() {
		return nameLabel.getText().trim();
	}
	
	public void close() {
		click(closeButton);
	}
}
