package creator.codeflow.collections;

import creator.Creator;
import creator.codeflow.pages.CollectionAddPage;
import creator.codeflow.pages.NavBar;
import creator.codeflow.pages.RuleCollectionPage;
import creator.commom.pages.CreatorProjectPage;
import creator.pages.NavBarFactory;
import creator.pages.common.Notification;
import light.automate.dataprovider.JsonData;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddCollectionTests extends Creator {
	
	@BeforeClass
	public void before() {
		CreatorProjectPage projectPage = new CreatorProjectPage();
		projectPage
			.openApp();
		
		projectPage.selectApp("creator");
		projectPage.open();
		projectPage.openClodFlow();
		
		
		NavBar navBar = NavBarFactory.getCodeFlowNavBar();
		navBar.gotoCollection();
	}
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_data")
	public void add_rule_collections(JsonData data) {
		/*CreatorProjectPage projectPage = new CreatorProjectPage();
		projectPage
			.openApp();
		
		projectPage.selectApp(data.get("app_name"));
		projectPage.open();
		projectPage.openClodFlow();
		
		
		NavBar navBar = NavBarFactory.getCodeFlowNavBar();
		navBar.gotoCollection();*/
		
		RuleCollectionPage collectionPage = new RuleCollectionPage();
		CollectionAddPage addPage = collectionPage.openAdd();
		
		addPage
			.writeName(data.get("name"))
			.writeDescription("*.java")
			.save();
		
		Notification notification = new Notification();
		String message = notification.readMessage();
		notification.close();
		
		Assert.assertEquals(message,"Rule Collection added successfully.");
		
		boolean present = collectionPage
			.refresh()
			.search(data.get("name"))
			.isPresent(data.get("name"));
		
		Assert.assertTrue(present, "Collection not found after created");
	}
}
