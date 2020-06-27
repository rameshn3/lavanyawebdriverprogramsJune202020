package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;


public class MenuHandlingTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test(description = "linkedinHometest")
	public void linkedinHomeTest() throws InterruptedException {
		Reporter.log("Started executing the linkedinHomeTest");
		driver.get("https://jqueryui.com/menu/");
		Reporter.log("verify the page title");
		wait.until(ExpectedConditions.titleContains("Menu | jQuery UI"));
		
		//identify the iframe element
		
		WebElement ifrmeEl=driver.findElement(By.className("demo-frame"));
		
		//switch to this frame
		driver.switchTo().frame(ifrmeEl);
		
		Actions act = new Actions(driver);
		
		WebElement musicMenu=driver.findElement(By.id("ui-id-9"));
		
		//move the cursor to forgot password link
		act.moveToElement(musicMenu).perform();
		WebElement rocksubMenu=driver.findElement(By.id("ui-id-10"));
		
		wait.until(ExpectedConditions.visibilityOf(rocksubMenu));
		act.moveToElement(rocksubMenu).perform();
		
		WebElement classicsubMenu=driver.findElement(By.id("ui-id-12"));
		
		wait.until(ExpectedConditions.visibilityOf(classicsubMenu));
		act.moveToElement(classicsubMenu).click(classicsubMenu).build().perform();
		
		
		Thread.sleep(3000);
		
	}

	
	

	@BeforeClass
	public void launchBrowser() {
		Reporter.log("Started executing the @BeforeClass -launchBrowser() ");
		//System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		Reporter.log("set the chromedriver.exe path");
		System.setProperty("webdriver.gecko.driver",
				"D:\\webdriverjars\\executables\\geckodriver-v0.26.0-win64\\geckodriver.exe");

		// interface refobj = new implementedclass()
		driver = new FirefoxDriver();
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
