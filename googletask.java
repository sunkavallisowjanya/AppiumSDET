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
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class googletask {
	
  AppiumDriver<MobileElement> driver;
  String[] tasks = {"Complete Activity with Google Tasks", "Complete Activity with Google Keep", "Complete the second Activity Google Keep"};

@BeforeMethod
public void beforeMethod() throws MalformedURLException {
	  
	  DesiredCapabilities task = new DesiredCapabilities();
	  
		task.setCapability("deviceId", "G9UDU17A28000649");
		task.setCapability("platformName", "android");
		task.setCapability("automationName", "UiAutomator2");
				
		task.setCapability("appPackage", "com.google.android.apps.tasks");
		task.setCapability("appActivity", "com.google.android.apps.tasks.ui.TaskListsActivity");
		task.setCapability("noReset", true);
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		driver = new AppiumDriver<MobileElement>(url, task);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Google Task app initiated succesfully");
}



@Test
public void taskadd() {
	
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	MobileElement newtask = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/tasks_fab\")"));
		
		//Adding Tasks
	
		for (String s : tasks) {
			
			newtask.click();
			driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/add_task_title\")")).sendKeys(s);
			driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/add_task_done\")")).click();
			
		}
		
		
		//Finding elements added for assertion
		List<MobileElement> addedTasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/task_name\")"));
		int count = (addedTasks.size());
		
		AssertJUnit.assertEquals(count, 3);
	  
}


  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
