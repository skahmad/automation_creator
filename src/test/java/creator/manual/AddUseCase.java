package creator.manual;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import creator.pages.manual.AddUseCasePage;
import creator.pages.manual.UseCasePage;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AddUseCase extends Creator {
	Navbar navbar;
	Notification notification;
	UseCasePage useCasePage;
	
	@BeforeMethod
	public void goto_use_case_page() {
		navbar = new Navbar();
		notification = new Notification();
	}
	
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void add_use_case(HashMap<String, String> data) {
		navbar
			.openApp()
			.view(data.get("app"))
			.openUseCases();
		
		useCasePage = navbar.openUseCase();
		
		AddUseCasePage a = useCasePage
			.add();
		
		this.wait(1);
		
		a
			.writeName(data.get("name"))
		.   add();
		
		this.wait(1);
		
		String m = notification.readMessage();
		notification.close();
		
		if (a.visible()) {
			a.close();
		}
		
		assert m.equals("use case added successfully");
	}
}
