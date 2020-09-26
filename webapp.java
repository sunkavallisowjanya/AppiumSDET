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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class webapp {
  
  	  
	  AppiumDriver<MobileElement> driver;
	  String[] toDos = { "Add tasks to list", "Get number of tasks", "Clear the list" };
	 
	  @BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		  
		  DesiredCapabilities task = new DesiredCapabilities();
		  
			task.setCapability("deviceId", "G9UDU17A28000649");
			task.setCapability("platformName", "android");
			task.setCapability("automationName", "UiAutomator2");
					
			task.setCapability("appPackage", "com.android.chrome");
			task.setCapability("appActivity", "com.google.android.apps.chrome.Main");
			task.setCapability("noReset", true);
			
			
			
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			
			driver = new AppiumDriver<MobileElement>(url, task);
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get("https://www.training-support.net/selenium");
			System.out.println("Google chrome app initiated succesfully");
	}

  
  @Test
  public void todolist() {
	  
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"To-Do List\"))"));
	  
	  //driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='To-Do List']")).click();
	  driver.findElementByAccessibilityId("To-Do List Elements get added at run time").click();
	  driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]")).click();
	  
	  
	  for (String s : toDos) {
			driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys(s);
			driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
		}
	  
	  List<MobileElement> created_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
	  for (MobileElement s : created_Tasks) {
		  s.click();
		  }
	  
     driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]")).click();
	  
	  
	  List<MobileElement> clearedTasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
	  AssertJUnit.assertEquals(clearedTasks.size(), 0);
	  
	  
  }
  
  @Test
  public void loginpage() {
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"Login Form\"))"));
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Login Form']")).click();
    
     
	  //Valid Login
      String userid = "admin";
      String pwd = "password";
      
      driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).sendKeys(userid);
      driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).sendKeys(pwd);
      driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
      
      String loginMessage = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
      
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      AssertJUnit.assertEquals(loginMessage, "Welcome Back, admin");

	  //Invalid Login

	  String userid2 = "admin1";
      String pwd2 = "password1";
      WebElement id1 = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")"));
      WebElement pwd1 = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")"));
      
      id1.clear();
      pwd1.clear();
      
      id1.sendKeys(userid2);
      pwd1.sendKeys(pwd2);
      
      driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(textStartsWith(\"Log in\"))"));
      driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     
      String loggedinmsg = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      AssertJUnit.assertEquals(loggedinmsg, "Invalid Credentials");
  }
  
  @Test
  public void PopUpLoginSimpleForm() {
	  
	  //Scrolling
	  
	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"Popups\"))"));
  	  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	  driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Popups']")).click();

  		 

  		  //Valid Logins
  	  
  	      String userid = "admin";
  	      String pwd = "password";
  	      driver.findElement(MobileBy.AndroidUIAutomator("text(\"Sign In\")")).click();
  	      WebElement user = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")"));
  	      WebElement pass = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")"));

  		  user.sendKeys(userid);
  		  pass.sendKeys(pwd);

  		  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
  	      String loggedinmsg = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
  	      Assert.assertEquals(loggedinmsg, "Welcome Back, admin");

  		 //Invalid login

  		  String userid2 = "admin1";
  	      String pwd2 = "password1";
  	      driver.findElement(MobileBy.AndroidUIAutomator("text(\"Sign In\")")).click();
  	      user.clear();
  	      pass.clear();
  	      user.sendKeys(userid2);
  	      pass.sendKeys(pwd2);
  	      driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
  	      String loggedinmsg2 = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
  	      Assert.assertEquals(loggedinmsg2, "Invalid Credentials");

        Assert.assertEquals(loggedinmsg2, "Invalid Credentials");
	  	  
  }
  
  
  
  @AfterMethod
  public void afterMethod() {
	  driver.quit();
	  
  }
  

}
