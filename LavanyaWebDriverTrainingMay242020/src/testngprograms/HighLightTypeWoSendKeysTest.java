package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class HighLightTypeWoSendKeysTest {
	private WebDriver driver=null;
	private WebDriverWait wait=null;
  @Test
  public void highlightAndTypeWoSendKeysTest() throws InterruptedException {
	  //ope nthe url
	  driver.navigate().to("http://amazon.in");
	  wait.until(ExpectedConditions.titleContains("Amazon.in"));
	  //create an object for Actions class
	  Actions act=new Actions(driver);
	  	  
	  
	 //identify the search editbox
	  WebElement srch_editbox=driver.findElement(By.id("twotabsearchtextbox"));
	  //highlight the element
	  Utility.highLightElement1(driver, srch_editbox);
	  //type the value in search editbox
	  Utility.setAttribute(srch_editbox, "value", "headphones");
	  //Press Enter key on the element
	  srch_editbox.sendKeys(Keys.ENTER);
	 
	  //press PAGEDOWN from keywboard
	  act.sendKeys(Keys.PAGE_DOWN).perform();
	  Thread.sleep(3000);
	  act.sendKeys(Keys.PAGE_UP).perform();
	  Thread.sleep(5000);
	  //scroll for element
	  WebElement backTotop=driver.findElement(By.id("navBackToTop"));
	  Utility.scrollForElement(driver, backTotop);
	  Thread.sleep(3000);
	  //perform click action using javascript click
	  Utility.safeJavaScriptClick(driver, backTotop);
	  
	  Thread.sleep(3000);
	  
  }
  @BeforeClass
  public void beforeClass() {
	  Reporter.log("i am inside  @BeforeClass block");
	  Reporter.log("This annotated block will execute before any test in current class ");
	
	  System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chrome\\chromedriver_win32 (2)\\chromedriver.exe");
		driver= new ChromeDriver();
		Reporter.log("maximize the window"); 
		driver.manage().window().maximize();

		Reporter.log("adding implictwait");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// explicitwait - create Object for WebDriverWait
		wait = new WebDriverWait(driver, 30);

		System.out.println("Ended executing the @BeforeClass ..");
  }

  @AfterClass
  public void afterClass() {
	  Reporter.log("i am inside the @AfterClass ...");
	  System.out.println("started executing the @AfterClass ..");
	  if(driver!=null) {
		  driver.close();
	  }
	  System.out.println("Ended executing the @AfterClass ..");
  }


}
