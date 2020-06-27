package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class FileUploadTest {
	private WebDriver driver=null;
	private WebDriverWait wait=null;
	private String fpath="D:\\JavaPrograms\\fileprograms_april2018\\fileoutput.doc";
  @Test
  public void uploadFile() throws InterruptedException {
	  //open the url
	  driver.get("https://www.monsterindia.com/");
	  wait.until(ExpectedConditions.titleContains("Job Vacancy - Latest Job Openings - Job Search Online - Monster India"));
	  
	  //click on upload button
	  driver.findElement(By.xpath("//*[contains(@class,'resume-btn btn-orange')]")).click();
	 
	  Thread.sleep(3000);
		  
	  //identify the browse button
	  WebElement upload_btn=driver.findElement(By.xpath("//form[@name='parsingResponseForm']//div[1]/button"));
	 
	  Utility.safeJavaScriptClick(driver, upload_btn);
	  driver.findElement(By.xpath("//span[@class='browse-text'][contains(.,'Choose file')]")).click();
	  Thread.sleep(5000);
	  Utility.uploadFileWithRobot(fpath);
	  
	  Thread.sleep(5000);
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'file-uploading')]")));
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'successUploadFile')]")));
	  if(driver.findElement(By.xpath("//*[contains(@class,'successUploadFile')]")).isDisplayed()) {
		  System.out.println("File uploaded successfully");  
	  }else {
		  System.out.println("File is not uploaded successfully");
	  }
	  Thread.sleep(5000);
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("I am in BeforeClass block");
	  //set the chrome driver.exe path
	  System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chrome\\chromedriver_win32 (2)\\chromedriver.exe");
		 Reporter.log("creating chromedriver object");
		// interface refobj=new implementingclass();
		driver = new ChromeDriver();
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
