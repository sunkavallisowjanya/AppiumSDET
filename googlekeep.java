package activities;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class googlekeep {
  
	AppiumDriver<MobileElement> driver;
	  

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		  
		  DesiredCapabilities task = new DesiredCapabilities();
		  
			task.setCapability("deviceId", "G9UDU17A28000649");
			task.setCapability("platformName", "android");
			task.setCapability("automationName", "UiAutomator2");
					
			task.setCapability("appPackage", "com.google.android.keep");
			task.setCapability("appActivity", "com.google.android.keep.activities.BrowseActivity");
			task.setCapability("noReset", true);
			
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			
			driver = new AppiumDriver<MobileElement>(url, task);
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println("Google Task app initiated succesfully");
	}


  
  @Test(enabled = false)
  
  public void addnotes() {
	  
	  //Entering Note data
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/new_note_button\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/editable_title\")")).sendKeys("Test Notes - Title");
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/edit_note_text\")")).sendKeys("Test Notes - Description");
	  
	  //Going back to home page
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	 
	  //Assertion for added note
	  String addednote =driver.findElementById("index_note_title").getText();
	  AssertJUnit.assertEquals(addednote, "Test Notes - Title");
	  System.out.println("Notes added succesfully.");
	  
  }
  
  @Test
  
  public void addnoteswithremainder() {
	  
	//Entering Note data
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/new_note_button\")")).click();
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/editable_title\")")).sendKeys("Test Notes - Title");
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/edit_note_text\")")).sendKeys("Test Notes - Description");
	  
	
	  driver.findElementByAccessibilityId("Reminder").click();
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/time_spinner\")")).click();
	  
	  driver.findElement(By.id("com.google.android.keep:id/reminder_time_evening")).click();
	  driver.findElement(By.id("com.google.android.keep:id/save")).click();
	  driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	  
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/drawer_navigation_reminders\")")).click();
	  driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	  
	  String Remindertask = driver.findElement(By.id("com.google.android.keep:id/index_note_title")).getText();
	  AssertJUnit.assertEquals(Remindertask, "Test Notes - Title");
	  System.out.println("Task with Remainderadded succesfully");
 
  }
  
  
  @AfterMethod
  public void afterMethod() {
	  driver.quit();
	  
  }

}
