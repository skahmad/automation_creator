package creator.pages.rest;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RequestPage extends Page {
	public RequestPage() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(xpath = "//button[@title='Add Request']")
	private WebElement addButton;
	
	@FindBy(css = "div.list-group-item div.col b")
	private List<WebElement> requestList;
	
	public RequestAddPage add() {
		click(addButton);
		return new RequestAddPage();
	}
	
	public RequestDetailsPage select(String s) {
		boolean found = false;
		for (WebElement r : requestList) {
			if (r.getText().trim().equals(s)) {
				r.click();
				found = true;
				break;
			}
		}
		if (!found) {
			throw new RuntimeException("Request not found");
		}
		return new RequestDetailsPage();
	}
}
