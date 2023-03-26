package creator.app;

import creator.Creator;
import creator.pages.app.AppDetails;
import creator.pages.common.Navbar;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AddVersion extends Creator {
	Navbar navbar;
	
	@BeforeMethod
	public void init() {
		navbar = new Navbar();
	}
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void add_app_version(HashMap<String, String> data) {
		AppDetails details = navbar
			.openApp()
			.view(data.get("app"));
		
		this.wait(1);
		
		details
			.newVersion()
			.writeVersion(data.get("version"))
			.ok();
		
		this.wait(2);
		
		assert details.versions().contains(data.get("version") +" latest"): "Version not added";
	}}
