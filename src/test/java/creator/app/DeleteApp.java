package creator.app;

import creator.Creator;
import creator.pages.app.*;
import creator.pages.app.AddApp;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import light.automate.core.pages.Page;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

public class DeleteApp extends Creator {
	@Test
	public void create_app_and_delete_app() {
		String appName = String.valueOf(System.currentTimeMillis());
		
		Navbar navbar = new Navbar();
		
		Apps apps = navbar.openApp();
		
		Page.wait(2);
		
		AddApp addApp = apps
			.openAdd();
		
		Page.wait(2);
		
		addApp
			.writeName(appName)
			.writeApiUrl("http://localhost:8080/" + System.currentTimeMillis())
			.ok();
		
		Page.wait(2);
		Notification notification = new Notification();
		Assert.assertEquals(notification.readMessage(), "App Created successfully!");
		
		AppDetails appDetails = navbar
			.openApp()
			.view(appName);
		
		Page.wait(2);
		
		SettingPage settingPage = appDetails.openSetting();
		
		Page.wait(2);
		
		Assert.assertEquals( settingPage.readName(), appName);
		
		DeleteAppSidebar deleteAppSidebar = settingPage
			.openDelete();
		
		Page.wait(2);
		
		deleteAppSidebar
			.confirm();
		
		Page.wait(2);
		Assert.assertEquals(notification.readMessage(), "Application deleted successfully!");
		
		boolean f = navbar.openApp()
			.getAppsList()
			.stream().map(e->e.getText().trim())
			.collect(Collectors.toList())
			.contains(appName);
		
		Assert.assertFalse(f);
	}
	
	@Test
	public void app_should_not_delete_after_add_use_case() {}
	
	@Test
	public void app_should_not_delete_after_add_test_case() {}
	
	@Test
	public void app_should_not_delete_after_add_request() {}
	
	@Test
	public void app_should_not_delete_after_add_restflow() {}
	
	@Test
	public void app_should_not_delete_after_add_page() {}
	
	@Test
	public void app_should_not_delete_after_add_service() {}
	
	@Test
	public void app_should_not_delete_after_add_repository() {}
	
	@Test
	public void app_should_be_deleted_after_remove_all_use_cases() {}
	
	@Test
	public void app_should_be_deleted_after_remove_all_test_case() {}
	
	@Test
	public void app_should_be_deleted_after_remove_all_restflows() {}
	
	@Test
	public void app_should_be_deleted_after_remove_all_requests() {}
	
	@Test
	public void app_should_be_deleted_after_remove_all_pages() {}
	
	@Test
	public void app_should_be_deleted_after_remove_all_services() {}
	
	/**
	 * precondition:
	 *      1. app should be present
	 *      2. there should not present any testcase, usecase, restflow, request, pages, service
	 *
	 * post-condition:
	 *      1. app should be deleted
	 *
	 * steps:
	 *      1. create app
	 *      2. add a repository
	 *      3. try to add app
	 *      4. app should not be deleted
	 *      5. delete repository
	 *      6. delete app
	 */
	@Test
	public void app_should_be_deleted_after_remove_all_repositories() {}
}
