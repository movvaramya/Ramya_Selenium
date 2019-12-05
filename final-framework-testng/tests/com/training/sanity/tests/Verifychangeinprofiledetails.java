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

public class Verifychangeinprofiledetails {
	
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
		screenShot = new ScreenShot(driver); 
		genericmeth = new GenericMethods(driver);
		// open the browser 
		driver.get(baseUrl);
		genericmeth.assertURL("http://realestatem1.upskills.in/");
		System.out.println("Assertion is passed and match the base URL with runtime URL");
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(10000L);
		driver.quit();
	}
	@Test
	public void validLoginTestAndUpdatedMyProfileTest() throws InterruptedException {
		loginPOM.clickLinkTextLogin();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		Thread.sleep(5000L);
		screenShot.captureScreenShot("validLoginTest");
		Thread.sleep(10000L);
		loginPOM.MouseHoverClick("howdy");
		Thread.sleep(5000L);
		genericmeth.assertText("admin","//*[@id=\"wp-admin-bar-user-info\"]/a/span","xpath", "Admin text is Not Present");
		System.out.println("Assertion pass for the text admin");			
		genericmeth.assertText("Edit My Profile","//*[@id=\"wp-admin-bar-edit-profile\"]/a","xpath", "Edit My Profile text is Present");
		System.out.println("Assertion pass for the text Edit My Profile");			
		genericmeth.assertText("Log Out","//*[@id=\"wp-admin-bar-logout\"]/a","xpath", "Log Out text is Present");
		Thread.sleep(5000L);
		screenShot.captureScreenShot("Howdy,admin");
		System.out.println("Assertion pass for the text Log Out");
		loginPOM.clickHyperLinkAdmin();
		loginPOM.sendLastName("reva");
		loginPOM.sendPhone("9876543210");
		loginPOM.clickSubmitBtn();
		Thread.sleep(5000L);
		genericmeth.assertText("Profile updated.","//*[@id=\"message\"]/p/strong","xpath", "Profile updated.text is Present");
		screenShot.captureScreenShot("UpdatedMyProfileTest");
		System.out.println("Assertion is passed and validated message as 'Profile updated.'");
		
	}


}






