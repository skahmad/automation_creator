package creator.pages.page;

import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PagesPage extends Page {
	public PagesPage(){
		PageFactory.initElements(browser.driver(), this);
	}
	
	@FindBy(css = "button[title='Add Page']")
	private WebElement addButton;
	
	public PageAddPage openAdd() {
		click(addButton);
		
		return new PageAddPage();
	}
}
