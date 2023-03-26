package creator.manual;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.common.Notification;
import creator.pages.manual.AddTestCasePage;
import creator.pages.manual.TestCasePage;
import creator.pages.manual.UseCasePage;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AddTestCase extends Creator {
	TestCasePage t;
	Navbar navbar;
	
	@BeforeMethod
	public void goto_test_case_page() {
		navbar = new Navbar();
//		navbar
//			.openApp()
//			.view("GlobalIDS");
//
		
	}
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void add_test_case(HashMap<String, String> data) {
		navbar
			.openApp()
			.view(data.get("app"))
			.openUseCases();
		
		this.wait(1);
		
		navbar
			.openUseCase();
		
		UseCasePage useCasePage = new UseCasePage();
		
		TestCasePage t = useCasePage
			.view(data.get("usecase"));
		
		this.wait(1);
		
		AddTestCasePage a = t
			.add();
		
		this.wait(1);
		
		a
			.writeName(data.get("name"))
			.add();
		
		Notification notification = new Notification();
		String m = notification.readMessage();
		notification.close();
		
		this.wait(1);
		
		if (a.isVisible()) {
			System.out.println("### close add sidebar");
			a.close();
			
			this.wait(1);
		}
		
		assert m.equals("Test case added successfully!");
	}
}
