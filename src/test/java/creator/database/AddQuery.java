package creator.database;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import creator.pages.database.QueryPage;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AddQuery extends Creator {
	@Test(dataProvider = "json_map_data", dataProviderClass = JsonDataProvider.class)
	public void add_query(HashMap<String,String> data) {
		Navbar navbar = new Navbar();
		navbar
			.openApp()
			.view(data.get("app"))
			.openConnections();
		
		QueryPage queryPage = navbar
			.openDatabase()
			.view(data.get("connection"));
		
		queryPage
			.openAddQueryPage()
			.writeTitle(data.get("title"))
			.writeQuery(data.get("query"))
			.clickOnAdd();
		
		Notification notification = new Notification();
		String s = notification.readMessage();
		notification.close();
		
		Assert.assertEquals( "Query added successfully!", s, "Query add message not matched!");
		
		boolean found = queryPage
			.search(data.get("title"))
			.isPresent(data.get("title"));
		
		Assert.assertTrue(found, "Query should be present after added : " + data.get("title"));
	}
}
