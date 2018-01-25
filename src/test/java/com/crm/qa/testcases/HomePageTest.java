package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.Login;
import com.crm.qa.pages.PreviewPage;
import com.crm.qa.pages.Sleeper;
import com.crm.qa.util.*;

public class HomePageTest extends TestBase {
	Login login;
	HomePage homePage;
	TestUtil testUtil;
	PreviewPage PreviewPge;
	static String value;
	public HomePageTest() {
		super();
	}

	
	@Test(priority=1)
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
				login = new Login ();
		Sleeper.SleepSomeSeconds(5);
		homePage = login.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.verifyUName(prop.getProperty("username")));
		Logging.info("Login Validated");
		
		
	}
	
	@Test(priority=2)
	public void AddPost()
	{
		homePage.PostLink();
	}
	@Test(priority=3)
	public void AddNewLinkForPost()
	{
		homePage.AddNewLink();
	}
	@Test(priority=4)
	public void EnterTitleForPost()
	{
		PreviewPge = homePage.EnterTitle();
		
			}
	@Test(priority=5)
	public void VerifyPostInPreviewPage()
	{
		
		
		PreviewPge.VerifyPost(homePage.value);
	}
	@Test(priority=6)
	public void AddTag()
	
	{
		
		PreviewPge = homePage.AddMultipleTag();
			}
	@Test(priority=7)
	public void SelectRadio()
	
	{
		
		homePage.selectRadioBtnAndClickUpdate();
		
		
	}
	@Test(priority=8)
	public void SelectScreenOptionInHomePage()
	
	{
		
		homePage.SelectScreenOption();
		
		
	}
	@Test(priority=9)
	public void ClickPreviewBtn()
	
	{
		
		homePage.ClickPreviewBtn();
		
		
	}
	@Test(priority=10)
	public void LogoutFromUrl()
	
	{
		homePage.Logout();
		
		}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	
	

}
