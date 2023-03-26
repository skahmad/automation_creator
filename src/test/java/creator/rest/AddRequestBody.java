package creator.rest;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import creator.pages.request.BodyAddPage;
import creator.pages.request.BodyListPage;
import creator.pages.rest.RequestDetailsPage;
import creator.pages.rest.RequestPage;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AddRequestBody extends Creator {
	Navbar navbar;
	Notification notification;
	
	
	@BeforeClass
	public void goto_request_page() {
		navbar = new Navbar();
		notification = new Notification();
	}
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void add_request_body(HashMap<String, String> data) throws InterruptedException {
		browser.refresh();
		
		RequestPage request = navbar
			.openApp()
			.view(data.get("app"))
			.openRequest();
		
		wait(1);
		
		RequestDetailsPage detailsPage = request
			.select(data.get("request"));
		
		wait(1);
		
		BodyListPage bodyListPage = detailsPage
			.openBodyList();
		
		wait(1);
		BodyAddPage addPage = bodyListPage
			.openAdd();
		
		wait(1);
		addPage
			.writeName(data.get("body_name"))
			.writeBody(data.get("body_name"))
			.add();
		
		String message = notification.readMessage();
		notification.close();
		
		bodyListPage.close();
		
		detailsPage.close();
		
		Assert.assertEquals(message, data.get("message"), "notification message not matched!");
	}
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void add_request_body_to_post_api(HashMap<String, String> data) {
		browser.refresh();
		
		RequestPage request = navbar
			.openApp()
			.view(data.get("app"))
			.openRequest();
		
		wait(1);
		
		RequestDetailsPage detailsPage = request
			.select(data.get("request"));
		
		wait(1);
		
		BodyListPage bodyListPage = detailsPage
			.openBodyList();
		
		wait(1);
		BodyAddPage addPage = bodyListPage
			.openAdd();
		
		wait(1);
		addPage
			.writeName(data.get("body_name"))
			.writeBody(data.get("body"))
			.add();
		
		String message = notification.readMessage();
		notification.close();
		
		bodyListPage.close();
		
		detailsPage.close();
		
		Assert.assertEquals(message, data.get("message"), "notification message not matched!");
	}
}
