package creator;

import creator.pages.LoginPage;
import light.automate.core.App;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Creator extends App {
	Long timeStart = 0L;
	Long timeEnd = 0L;
	
	@Override
	@AfterMethod
	public void onTestEnd() throws Exception {
	
	}
	
	@Override
	@BeforeMethod
	public void onTestStart() throws Exception {
	}
	
	@Override
	@BeforeClass
	public void onTestClassStart() throws Exception {
		timeStart = System.currentTimeMillis();
		this
			.initialize()
			//.setRootUrl("https://localhost:2443")
			.setRootUrl(configuration.browser().getProperty("url"))
			.start();
		
		LoginPage loginPage = new LoginPage();
		loginPage.writeUsername("light@email.com");
		loginPage.writePassword("123456");
		loginPage.clickOnLogin();
		
//		Navbar navbar = new Navbar();
//		navbar.httpsSkip();
	}
	
	@Override
	@AfterClass
	public void onTestClassEnd() throws Exception {
		this.close();
		timeEnd = System.currentTimeMillis();
		System.out.println("@@@ Total Execution Time : " + (timeEnd-timeStart)/1000 + " sec");
	}
}
