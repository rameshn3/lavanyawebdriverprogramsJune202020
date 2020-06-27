package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
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

public class DragAndDropTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;
  @Test
  public void dragAndDrop() throws InterruptedException {
	  driver.get("https://jqueryui.com/droppable/");
		wait.until(ExpectedConditions.titleContains("Droppable | jQuery UI"));
		Assert.assertEquals("Droppable | jQuery UI", driver.getTitle());
	    
		//identify the internal Frame
		WebElement ifrmEle=driver.findElement(By.className("demo-frame"));
		//switch to iframe element
		driver.switchTo().frame(ifrmEle);
		
		//create Object for Actions
		Actions act = new Actions(driver);
		
		WebElement src=driver.findElement(By.id("draggable"));
		
		WebElement tgt=driver.findElement(By.id("droppable"));
		
		//act.dragAndDrop(src, tgt).perform();
		act.clickAndHold(src).perform();
		act.moveToElement(tgt).perform();
		act.release(tgt).perform();
		Thread.sleep(3000);
		
  }
  @BeforeClass
	public void launchBrowser() {
		Reporter.log("Started executing the @BeforeClass -launchBrowser() ");
		
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chrome\\chromedriver_win32 (2)\\chromedriver.exe");
			driver= new ChromeDriver();
		
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

}
