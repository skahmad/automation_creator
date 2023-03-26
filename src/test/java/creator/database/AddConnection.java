package creator.database;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import creator.pages.database.DatabasePage;
import creator.service.AppService;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AddConnection extends Creator {
	@Test(dataProvider = "json_map_data", dataProviderClass = JsonDataProvider.class)
	public void add_database_connection(HashMap<String,String> data) {
		Navbar navbar = new Navbar();
		navbar
			.openApp()
			.view(data.get("app"))
			.openConnections();
		
		
		DatabasePage databasePage = navbar.openDatabase();
		databasePage
			.openAdd()
			.writeConnectionName(data.get("connection"))
			.selectDatabaseType(data.get("type"))
			.writeHost(data.get("host"))
			.writePort(Integer.parseInt(data.get("port")))
			.writeUser(data.get("user"))
			.writePassword(data.get("password"))
			.writeInstance(data.get("instance"))
			.add();
		
		Notification notification = new Notification();
		String expectedMessage = String.format("connection added '%s'", data.get("connection"));
		Assert.assertEquals(notification.readMessage(), expectedMessage, "Connection add message not matched!");
		
		notification.close();
		
	}
}
