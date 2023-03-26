package creator.rest;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import creator.pages.rest.RequestAddPage;
import creator.pages.rest.RequestDetailsPage;
import creator.pages.rest.RequestPage;
import creator.pages.rest.ResponseAddPage;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;

public class Request extends Creator {
	Navbar navbar;
	Notification notification;
	
	@BeforeMethod
	public void goto_request_page() {
		navbar = new Navbar();
		notification = new Notification();
	}
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data", priority = 1)
	public void add_request(HashMap<String, String> data) {
		RequestPage requestPage = navbar
			.openApp()
			.view(data.get("app"))
			.openRequest();
		
		RequestAddPage add = requestPage
			.add();
		
		this.wait(1);
		
		add
			.writeName(data.get("name"))
			.writeUrl(data.get("url"))
			.selectMethod(data.get("method"))
			.add();
		
		this.wait(1);
		
		String m = notification.readMessage();
		notification.close();
		
		if (add.visible()) {
			add.close();
		}
		
		
		System.out.println(m);
		assert m.equals("Endpoint added successfully!");
	}
	
	
	@Test(dataProvider = "json_map_data", dataProviderClass = JsonDataProvider.class, priority = 2, enabled = false)
	public void add_response(HashMap<String,String> data) {
		RequestPage requestPage = navbar
			.openApp()
			.view(data.get("app"))
			.openRequest();
		
		RequestDetailsPage details = new RequestDetailsPage();
		
		if (details.visible()) {
			if (!details.readName().equals(data.get("request"))) {
				details.close();
				details = requestPage
					.select(data.get("request"));
			}
			this.wait(1);
		} else {
			details = requestPage
				.select(data.get("request"));
		}
		
		System.out.println(details.readName());
		
		
		ResponseAddPage add = details
			.changeTab("Request")
			.addResponse();
		
		this.wait(1);
		
		add
			.clearName()
			.writeName(data.get("name"))
			.clearStatus()
			.writeStatus(data.get("status"))
			.clearBody()
			.writeBody(data.get("body"))
			.add();
		
		this.wait(1);
		
		String m = notification.readMessage();
		notification.close();
		
		if (add.visible()) {
			add.close();
		}
		
		assert m.equals("Response added successfully!");
	}
	
	
	@Test(dataProvider = "json_map_data", dataProviderClass = JsonDataProvider.class, priority = 3, enabled = false)
	public void add_exists_response(HashMap<String,String> data) {
		RequestPage requestPage = navbar
			.openApp()
			.view(data.get("app"))
			.openRequest();
		
		RequestDetailsPage details = new RequestDetailsPage();
		
		if (details.visible()) {
			if (!details.readName().equals(data.get("request"))) {
				details.close();
				details = requestPage
					.select(data.get("request"));
			}
			this.wait(1);
		} else {
			details = requestPage
				.select(data.get("request"));
		}
		
		System.out.println(details.readName());
		
		
		ResponseAddPage add = details
			.changeTab("Request")
			.addResponse();
		
		this.wait(1);
		
		add
			.clearName()
			.writeName(data.get("name"))
			.clearStatus()
			.writeStatus(data.get("status"))
			.clearBody()
			.writeBody(data.get("body"))
			.add();
		
		this.wait(1);
		
		String m = notification.readMessage();
		notification.close();
		
		if (add.visible()) {
			add.close();
		}
		
		assert m.equals("Response already present!");
	}
}
