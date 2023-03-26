package creator.pages.app;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Apps extends Page {
	@FindBy(xpath = "//button[@title='Add Application']")
	private WebElement addButton;
	
	@FindBy(css = "div.list-group-item b")
	List<WebElement> appsList;
	
	public Apps() {
		PageFactory.initElements(browser.driver(), this);
	}
	
	
	public List<WebElement> getAppsList() {
		return appsList;
	}
	
	public AddApp openAdd() {
		click(addButton);
		return new AddApp();
	}
	
	public AppDetails view(String app)  {
		boolean found = false;
		wait(3);
		for (WebElement webElement : this.appsList) {
			if (webElement.getText().trim().contains(app)) {
				webElement.click();
				found = true;
				break;
			}
		}
		
		if (!found)
			throw new RuntimeException("app not found '"+app+"' !");
		
		return new AppDetails();
	}
}
