package com.crm.qa.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.Logging;

public class HomePage extends TestBase {

	@FindBy(xpath = "//span[contains(text(),'opensourcecms')]")
	WebElement userNameLabel;
	@FindBy(xpath="//input[@id='user_login']")
	WebElement username;

	@FindBy(xpath="//input[@id='user_pass']")
	WebElement password;
	@FindBy(xpath="//div[text()='Posts']")
	WebElement AddPost;
	
	@FindBy(xpath="//div[text()='Posts']/following::a[text()='Add New']")
	WebElement AddNew;
	@FindBy(xpath="//input[@id='title']")
	WebElement InputAddPost;
	@FindBy(xpath="//*[@id='post-preview']")
	WebElement PreviewBtn;
	@FindBy(xpath="//input[@id='new-tag-post_tag']")
	WebElement AddTag;
	@FindBy(xpath="//input[@id='new-tag-post_tag']//following::input[1]")
	WebElement AddBtn;
	
	@FindBy(xpath="//input[@id='post-format-link']")
	WebElement LinkRadioBtn;
	@FindBy(xpath="//input[@id='publish']")
	WebElement Publish_update;
	@FindBy(xpath="//button[@id='show-settings-link']")
	WebElement ScreenOption;
	@FindBy(xpath="//input[@id='commentsdiv-hide']")
	WebElement CommentChkBox;
	@FindBy(xpath="//input[@id='slugdiv-hide']")
	WebElement SlugChkBox;
	@FindBy(xpath="//a[contains(text(),'Log Out')]")
	WebElement LogoutBtn;
	
public static String value;
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	
	
	public boolean verifyUName(String name){
		WebElement user=driver.findElement(By.xpath("//span[contains(text(), '"+name+"')]"));
		return user.isDisplayed();
		
	}
	
	public void PostLink()
	{
		Sleeper.SleepSomeSeconds(2);
		AddPost.click();
	}
	public void AddNewLink()
	{
		Sleeper.SleepSomeSeconds(2);
		Assert.assertTrue(IsDisplayed(AddNew));
		AddNew.click();
	}
	
	public PreviewPage EnterTitle()
	{
		 value=GenerateString(10);
		InputAddPost.sendKeys(value);
		click(PreviewBtn);
		return new PreviewPage();
	}

	
	public String  GenerateString(int T) {
	  
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = T;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	return generatedString;
	}
	public void click(WebElement element)
	{
		element.click();
	}
	public String CopyText()
	{
		String tag=AddTag.getAttribute("value");
		click(AddBtn);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scroll(0, -250);");
    	js.executeScript("arguments[0].click();", PreviewBtn);
			
	return tag;
}
	public PreviewPage AddMultipleTag()
	{
		Sleeper.SleepSomeSeconds(5);
		AddTag.sendKeys(GenerateString(2).concat(","),GenerateString(2).concat(","),GenerateString(2));
		Sleeper.SleepSomeSeconds(3);
		Logging.info("Tag is being inserted in HomePage");
		return new  PreviewPage();
		
	}
public void selectRadioBtnAndClickUpdate()
{
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", LinkRadioBtn);
	JavascriptExecutor xjs = (JavascriptExecutor)driver;
	js.executeScript("scroll(0, -250);");
	JavascriptExecutor js1 = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", Publish_update);
	JavascriptExecutor js2 = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", Publish_update);
		
}
public void SelectScreenOption()
{
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", ScreenOption);
	Sleeper.SleepSomeSeconds(3);
	CheckBox_Select(CommentChkBox,SlugChkBox);
	Logging.info("Both CheckBox selected");

}
public void CheckBox_Select(WebElement chk1,WebElement chk2)
{
	chk1.click();
	chk2.click();
	Logging.info("Both CheckBox Clicked");
}

public PreviewPage ClickPreviewBtn()
{
	click(PreviewBtn);
	String Parent = driver.getWindowHandle();
	for (String handle1 : driver.getWindowHandles()) {

    	System.out.println(handle1);

    	driver.switchTo().window(handle1);

    	}
	
				driver.close();
		driver.switchTo().window(Parent);
	return new PreviewPage();
}
public void Logout()
{

	Sleeper.SleepSomeSeconds(5);
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", LogoutBtn);
	Logging.info("Successfully Logout from the System");
	
	
}
public boolean IsDisplayed(WebElement element)
{
	return element.isDisplayed();
	
}
}
