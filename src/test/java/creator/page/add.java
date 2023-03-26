package creator.page;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import creator.pages.page.PageAddPage;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class add extends Creator {
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void add_page_with_name(HashMap<String, String> data) {
		Navbar navbar = new Navbar();
		Notification notification = new Notification();
		
		navbar.openApp().view(data.get("app")).openPage();
		
		PageAddPage addPage = navbar
			.openPage()
			.openAdd();
		
		this.wait(1);
		
		addPage
			.writeName(data.get("name"))
			.add();
		
		this.wait(1);
		
		String message = notification.readMessage();
		notification.close();
		System.out.println(message);
		
		assert message.equals("page added successfully"): "validation message not matched!";
	}
}
