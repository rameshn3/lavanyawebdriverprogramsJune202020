package testngprograms;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class FileUploadWithAutoItTest {
	private WebDriver driver=null;
	private WebDriverWait wait=null;
	
  @Test
  public void uploadFile() throws InterruptedException, IOException {
	  //open the url
	  driver.get("http://automationpractice.com");
	  wait.until(ExpectedConditions.titleContains("My Store"));
	  
	  //click on Contact us
	  driver.findElement(By.xpath("(//a[contains(.,'Contact us')])[1]")).click();
	 
	  Thread.sleep(3000);
	   
	  //identify the browse button
	WebElement upload_btn=driver.findElement(By.xpath("//input[contains(@name,'fileUpload')]"));
	Utility.scrollForElement(driver, upload_btn);
	 Thread.sleep(3000);
	
	 Actions act=new Actions(driver);
	 act.moveToElement(upload_btn).click(upload_btn).perform();
	  Thread.sleep(3000);
	  
	  //calling autoItScript in java selenium using firefox
	  Runtime.getRuntime().exec("C:\\AutoItScript\\lavanyaautoItScript\\AutoItScriptFirefox.exe");
	  //run on chrome browser
	//Runtime.getRuntime().exec("C:\\AutoItScript\\lavanyaautoItScript\\AutoItScriptChrome.exe");
	
	  Thread.sleep(5000);
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("I am in BeforeClass block");
	  //set the chrome driver.exe path
	//set the geckodriver.exe path
	  System.setProperty("webdriver.gecko.driver", "D:\\BrowserExefiles\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		//launch the browser
		//interface refvar=new implmentingclass();
	 driver=new FirefoxDriver();
		
	 /* System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chrome\\chromedriver_win32 (2)\\chromedriver.exe");
		Reporter.log("creating chromedriver object");
	// interface refobj=new implementingclass();
	driver = new ChromeDriver();*/
	 //implicitwait
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//maximize the window
			driver.manage().window().maximize();
			//create an object for WebDriverWait class
			wait=new WebDriverWait(driver,30);
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("I am in AfterClass block");
	 //close the browser
	 driver.close();
  }

}
