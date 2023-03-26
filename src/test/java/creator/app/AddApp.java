package creator.app;

import creator.Creator;
import creator.pages.app.Apps;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import light.automate.dataprovider.JsonDataProvider;
import light.automate.listener.TestListener;
import org.testng.annotations.*;

import java.util.HashMap;

@Listeners(TestListener.class)
public class AddApp extends Creator {
	private String testCaseId="20ccb25f-58af-4d7b-93c0-3fb86cea87dd";
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void add_app(HashMap<String,String> data) {
		Notification notification = new Notification();
		
		Navbar navbar = new Navbar();
		navbar
			.openApp();
		
		Apps app = new Apps();
		
		this.wait(1);
		
		creator.pages.app.AddApp add = app
			.openAdd();
		
		this.wait(1);
		
		add
			.writeName(data.get("name"))
			.writeApiUrl(data.get("url"))
			.ok();
		
		this.wait(2);
		
		assert notification.readMessage().equalsIgnoreCase("App Created successfully!");
	}
}
