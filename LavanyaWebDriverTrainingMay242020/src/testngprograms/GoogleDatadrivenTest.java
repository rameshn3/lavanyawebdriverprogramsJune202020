package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class GoogleDatadrivenTest {
	private WebDriver driver=null;
	private WebDriverWait wait=null;
		
  @Test(dataProvider = "searchData")
  public void searchTest(String s) {
	Reporter.log("Started executing the searchTest(String s): "+s);
	Reporter.log("type the " +s+ " keyword in search editbox");
			driver.findElement(By.name("q")).sendKeys(s);
			Reporter.log("submit on the search editbox");
			driver.findElement(By.name("q")).submit();
			Reporter.log("verify the selenium - Google Search title");
			wait.until(ExpectedConditions.titleContains(s+" - Google Search"));
			Reporter.log("wait for the search results text");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));

			Reporter.log("fetch the search resultstext");
			String txt = driver.findElement(By.cssSelector("div#result-stats")).getText();
			Reporter.log("search results text for "+ s + " is--> " + txt);

			//Reporter.log("String txt = About 3,82,00,000 results (0.72 seconds)");
			String[] str = txt.split(" ");
			// str[] = ["About","3,82,00,000","results","(0.72","seconds)"]
			// 0 1 2 3 4
			Reporter.log("search results count for "+s+"  is-->" + str[1]);
	  
	  
	  
  }
  @BeforeMethod
  public void beforeMethod() {
	 Reporter.log(" i am inside @BeforeMethod block");
	 Reporter.log("open the url");
	 driver.get("http://google.com");
	 Reporter.log("wait for the home page title");
	 wait.until(ExpectedConditions.titleContains("Google"));
	 Assert.assertEquals(driver.getTitle(), "Google");
  }

  @AfterMethod
  public void afterMethod() {
	Reporter.log("I am inside @AfterMethod ...");
	Reporter.log("clear the cookies");
	driver.manage().deleteAllCookies();
  }


  @DataProvider
  public Object[][] searchData() {
   Object[][] data = new Object[3][1];
     //1st row
   data[0][0]="selenium";
   //2nd row
   data[1][0]="UFT";
   //3rd row
   data[2][0] ="cucumber";
   
     return data;
    
  }
  @BeforeClass
  public void launchBrowser() {
	Reporter.log("Started executing the @BeforeClass -launchBrowser() ");
	 System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
	 Reporter.log("set the chromedriver.exe path");
		System.setProperty("webdriver.chrome.driver",
				"D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");

		// interface refobj = new implementedclass()
		driver = new ChromeDriver();
		Reporter.log("maximize the code");
		driver.manage().window().maximize();
		Reporter.log("add implicit wait");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Reporter.log("create object for WebDriverWait class");
		wait = new WebDriverWait(driver, 30);
  }

  @AfterClass
  public void afterClass() {
	  Reporter.log("started executing the afterClass() of @AfterClass block ...");
		if (driver != null) {
			driver.close();
		}
	  
  }

  @BeforeTest
  public void beforeTest() {
	  Reporter.log("i am inside @BeforeTest block");
	  Reporter.log("This annotated block will execute before any test tag related classes");
  }

  @AfterTest
  public void afterTest() {
	  Reporter.log("i am inside @AfterTest block");
	  Reporter.log("This annotated block will execute after test tag related classes have been ran");
  }

  @BeforeSuite
  public void beforeSuite() {
	  Reporter.log("i am inside @BeforeSuite block");
	  Reporter.log("This annotated block will execute before any test in the suite have ran");
  }

  @AfterSuite
  public void afterSuite() {
	  Reporter.log("i am inside @AfterTest block");
	  Reporter.log("This annotated block will execute after all tests have completed");
  }

}
