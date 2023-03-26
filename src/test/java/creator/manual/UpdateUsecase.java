package creator.manual;

import creator.Creator;
import creator.pages.common.Navbar;
import creator.pages.manual.UseCasePage;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class UpdateUsecase extends Creator {
	
	@Test(dataProvider = "json_map_data", dataProviderClass = JsonDataProvider.class)
	public void update_use_case(HashMap<String, String> data) {
		Navbar navbar = new Navbar();
		navbar.openApp()
			.view(data.get("app"))
			.openUseCases();
		
		UseCasePage useCasePage = navbar.openUseCase();
		
		this.wait(1);
		
		useCasePage
			.search(data.get("usecase"))
			.edit(data.get("usecase"))
			.writeName(data.get("name"))
			.add(); // todo update
		
		this.wait(1);
		
		useCasePage = navbar.openUseCase();
		
		String updateName = useCasePage
			.search(data.get("name"))
			.view(data.get("name"))
			.readUseCaseName();
		
		assert updateName.equals(data.get("name")): "use case not updated";
		
	}
}
