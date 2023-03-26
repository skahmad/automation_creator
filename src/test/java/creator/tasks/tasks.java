package creator.tasks;

import creator.Creator;
import creator.pages.tasks.AddTaskSidebarPage;
import creator.pages.tasks.AllTasks;
import creator.pages.tasks.Notification;
import light.automate.dataprovider.JsonDataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class tasks extends Creator {
	
	@Test(dataProviderClass = JsonDataProvider.class, dataProvider = "json_map_data")
	public void add_task(HashMap<String, String> data) {
		AllTasks allTasks = new AllTasks();
		
		AddTaskSidebarPage add = allTasks
			.clickOnAddButton();
		
		this.wait(1);
		
		add
			.writeName(data.get("name") + System.currentTimeMillis())
			.clickOnAdd();
		
		this.wait(2);
		
		if (add.isVisible()) {
			add.clearName();
			add.close();
			this.wait(1);
		}
		
		Notification notification = new Notification();
		String msg = notification.readMessage();
		notification.close();
		this.wait(1);
		assert msg.equals(data.get("name")): "message not matched!";
	}
}
