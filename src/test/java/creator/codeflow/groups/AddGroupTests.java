package creator.codeflow.groups;

import creator.Creator;
import creator.codeflow.pages.NavBar;
import creator.codeflow.pages.RuleCollectionPage;
import creator.codeflow.pages.RuleGroupPage;
import creator.commom.pages.CreatorProjectPage;
import creator.pages.NavBarFactory;
import creator.pages.common.Notification;
import light.automate.dataprovider.JsonData;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddGroupTests extends Creator {
	String prevAppName = "";
	String preCollectionName = "";
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_data")
	public void add_rule_groups(JsonData data) {
		if (!this.prevAppName.equals(data.get("app_name"))) {
			this.prevAppName = data.get("app_name");
			
			CreatorProjectPage projectPage = new CreatorProjectPage();
			projectPage
				.openApp();
			
			projectPage.selectApp(data.get("app_name"));
			projectPage.open();
			projectPage.openClodFlow();
			
			NavBar navBar = NavBarFactory.getCodeFlowNavBar();
			navBar.gotoCollection();
			
		}
		
		if (!preCollectionName.equals(data.get("collection_name"))) {
			this.preCollectionName = data.get("collection_name");
			
			NavBarFactory.getCodeFlowNavBar().gotoCollection();
			
			RuleCollectionPage collectionPage = new RuleCollectionPage();
			collectionPage
				.refresh()
				.view(data.get("collection_name"));
		}
		
		RuleGroupPage ruleGroupPage = new RuleGroupPage();
		ruleGroupPage
			.openAdd()
			.writeName(data.get("name"))
			.writeDescription("rule group")
			.save();
		
		Notification notification = new Notification();
		String message = notification.readMessage();
		notification.close();
		
		Assert.assertEquals(message,"Rule Group dded successfully.");
		
		boolean present = ruleGroupPage
			.refresh()
			.search(data.get("name"))
			.isPresent(data.get("name"));
		
		Assert.assertTrue(present, "Group not found after created");
	}
}
