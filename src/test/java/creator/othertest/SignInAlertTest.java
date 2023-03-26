package creator.othertest;

import creator.Creator;
import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

public class SignInAlertTest extends Creator {
	@Test
	public void sign_in_alert_accept_with_credential() {
		wait(10);
		String userName = "";
		String password = "";
		
		System.out.println(
			"window : " +
			browser.driver().getWindowHandles().size()
		);
		
		Alert alert = browser.driver().switchTo().alert();
		
		System.out.println("close");
		assert false;
	}
}
