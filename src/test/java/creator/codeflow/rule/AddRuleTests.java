package creator.codeflow.rule;

import creator.Creator;
import creator.codeflow.pages.NavBar;
import creator.codeflow.pages.RuleCollectionPage;
import creator.codeflow.pages.RuleGroupPage;
import creator.codeflow.pages.RulePage;
import creator.commom.pages.CreatorProjectPage;
import creator.pages.NavBarFactory;
import creator.pages.common.Notification;
import light.automate.dataprovider.JsonData;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddRuleTests extends Creator {
	String prevAppName = "";
	String preCollectionName = "";
	String prevGroupName = "";
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_data")
	public void add_rule(JsonData data) {
		
		//if (!this.prevAppName.equals(data.get("app_name"))) {
			this.prevAppName = data.get("app_name");
			
			CreatorProjectPage projectPage = new CreatorProjectPage();
			projectPage
				.openApp();
			
			projectPage.selectApp(data.get("app_name"));
			projectPage.open();
			projectPage.openClodFlow();
			
			NavBar navBar = NavBarFactory.getCodeFlowNavBar();
			navBar.gotoCollection();
		//}
		
		//if (!preCollectionName.equals(data.get("collection_name"))) {
			this.preCollectionName = data.get("collection_name");
			
			NavBarFactory.getCodeFlowNavBar().gotoCollection();
			
			RuleCollectionPage collectionPage = new RuleCollectionPage();
			collectionPage
				.refresh()
				.view(data.get("collection_name"));
		//}
		
		//if (!prevGroupName.equals(data.get("group_name"))) {
			RuleGroupPage groupPage = new RuleGroupPage();
			groupPage
				.refresh()
				.view(data.get("group_name"));
		//}
		
		
		String name = "rule-"+System.currentTimeMillis();
		String exp = "sdfsdfsdfs";
		
		RulePage rulePage = new RulePage();
		rulePage
			.openAdd()
			.writeName(name)
			.writeExpression(exp)
			.writeDescription("create auto rule")
			.save();
		
		Notification notification = new Notification();
		String message = notification.readMessage();
		notification.close();
		
		Assert.assertEquals(message,"Rule added successfully.");
		
		boolean present = rulePage
			.refresh()
			.search(name)
			.isPresent(name);
		
		Assert.assertTrue(present, "Rule not found after created");
	}
}
