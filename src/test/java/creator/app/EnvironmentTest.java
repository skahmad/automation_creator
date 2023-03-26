package creator.app;

import creator.Creator;
import creator.pages.app.AddEnvironmentSidebar;
import creator.pages.app.AppDetails;
import creator.pages.app.DeleteEnvironmentDialog;
import creator.pages.app.SettingPage;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import light.automate.core.pages.Page;
import light.automate.dataprovider.JsonData;
import light.automate.dataprovider.JsonDataProvider;
import light.automate.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class EnvironmentTest extends Creator {
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_data")
	public void add_environment(JsonData data) {
		Navbar navbar = new Navbar();
		
		AppDetails appDetails = navbar
			.openApp()
			.view(data.get("app"));
		
		Page.wait(1);
		
		SettingPage settingPage = appDetails.openSetting();
		
		Page.wait(1);
		
		AddEnvironmentSidebar addEnv = settingPage.openAddEnvironment();
		
		addEnv
			.writeName(data.get("name"))
			.writeApiUrl(data.get("url"))
			.save();
		
		Notification notification = new Notification();
		String msg = notification.readMessage();
		Assert.assertEquals(msg, "App environment added successfully.");
	}
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_data")
	public void set_active_environment(JsonData data) {
		Navbar navbar = new Navbar();
		
		AppDetails appDetails = navbar
			.openApp()
			.view(data.get("app"));
		
		Page.wait(1);
		
		SettingPage settingPage = appDetails.openSetting();
		
		Page.wait(1);
		
		settingPage.activeEnvironment(data.get("name"));
		
		Notification notification = new Notification();
		String msg = notification.readMessage();
		Assert.assertEquals(msg, "Environment marked as default.");
	}
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_data", priority = 10)
	public void delete_environment(JsonData data) {
		Navbar navbar = new Navbar();
		
		AppDetails appDetails = navbar
			.openApp()
			.view(data.get("app"));
		
		Page.wait(1);
		
		SettingPage settingPage = appDetails.openSetting();
		
		Page.wait(1);
		
		DeleteEnvironmentDialog deleteDialog = settingPage.deleteEnvironment(data.get("name"));
		
		String message = deleteDialog.readMessage();
		Assert.assertEquals(
			message,
			String.format("Are you sure to delete '%s' environment ?", data.get("name")),
			"Delete Environment message not matched!"
			);
		
		deleteDialog.ok();
		
		Notification notification = new Notification();
		String msg = notification.readMessage();
		Assert.assertEquals(msg, "Application environment deleted successfully!");
	}
}
