package creator.database;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import creator.pages.database.DatabasePage;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestConnection extends Creator {
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void database_connection_test(HashMap<String, String> data) {
		Navbar navbar = new Navbar();
		navbar
			.openApp()
			.view(data.get("app"))
		.openConnections();
		
		navbar
			.openDatabase()
			.search(data.get("connection"))
			.testConnection(data.get("connection"));
		
		Notification notification = new Notification();
		String message = notification.readMessage();
		notification.close();
		Assert.assertEquals(data.get("message"), message, "Test connection message not matched!");
	}
}
