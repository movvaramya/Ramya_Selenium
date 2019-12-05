package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class Verifyuserlogout {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private GenericMethods genericmeth;
	

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		genericmeth = new GenericMethods(driver);
		// open the browser 
		driver.get(baseUrl);
		genericmeth.assertURL("http://realestatem1.upskills.in/");
		System.out.println("Assertion is passed and match the base URL with runtime URL");
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void validLogOutTest() throws InterruptedException {
		loginPOM.clickLinkTextLogin();
		loginPOM.clickTagNameRegisterBtn();
		loginPOM.sendEmail("revasharma@gmail1.com");
		loginPOM.sendFirstName("soumya");
		loginPOM.sendLastName("Roy");
		loginPOM.clickRegisterBtn();
		Thread.sleep(5000L);
		genericmeth.assertText("You have successfully registered to Real Estate. We have emailed your password to the email address you entered.", "//*[@id=\"post-133\"]/div/div/div/div[1]/p", "xpath", "Message is not Present");
		System.out.println("Assertion is Passed"+"--->"+ "You have successfully registered to Real Estate. We have emailed your password to the email address you entered.");
		loginPOM.clickTagNameLoginBtn();
		loginPOM.sendUserName("revasharma@gmail.com");
		loginPOM.sendPassword("reva321");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("User registration done");
		loginPOM.MouseHoverClick("howdy");
		Thread.sleep(5000L);
		loginPOM.clickLogOutBtn();
}
}



