package creator.service;

import creator.pages.app.AddApp;
import creator.pages.app.Apps;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import light.automate.core.pages.Page;

public class AppService {
	public String addApp(String appName, String url) {
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
		return notification.readMessage();
	}
}
