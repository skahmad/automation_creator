package creator.manual;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.manual.TestCaseDetails;
import creator.pages.manual.TestCasePage;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class UpdateTestCase extends Creator {
	Navbar navbar;
	
	@BeforeMethod
	public void init() {
		navbar = new Navbar();
	}
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void update_test_case(HashMap<String, String> data) {
		TestCasePage testCasePage = navbar
			.openApp()
			.view(data.get("app"))
			.openTestCases();
		
		this.wait(1);
		
		TestCaseDetails details = testCasePage
			.search(data.get("testcase"))
			.view(data.get("testcase"));
		
		this.wait(1);
		
		String uid = details.uid();
		
		details
			.updateName(data.get("name"))
			.close();
		
		this.wait(1);
		
		testCasePage = navbar
			.openApp()
			.view(data.get("app"))
			.openTestCases();
		
		this.wait(1);
		
		testCasePage
			.search(data.get("name"))
			.view(data.get("name"));
		
		this.wait(1);
		
		String name = details
			.readName();
		
		String uidUpdated = details.uid();
		
		
		assert name.equals(data.get("name")) : "name not updated!";
		assert uid.equals(uidUpdated) : "UID should not update!";
	}
}
