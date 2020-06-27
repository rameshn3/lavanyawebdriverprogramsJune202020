package junitprograms;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class internalFrameDemo {
	private static WebDriver driver = null;
	private static WebDriverWait wait = null;

	@BeforeAll
	static void launchBrowser() throws Exception {
		System.out.println("started executing the launchBrowser() @BeforeAll block ...");
	    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		// set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver",
				"D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");

		// interface refobj = new implementedclass()
		driver = new ChromeDriver();
		// maximize the code
		driver.manage().window().maximize();
		// add implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("started executing the tearDownAfterClass() @BeforeAll block ...");
		if (driver != null) {
			driver.close();
		}
	}



	@Test
	void testSimpleAlert() {
		System.out.println("started executing the testSimpleAlert()..");
		driver.get("https://www.selenium.dev/");
		wait.until(ExpectedConditions.titleContains("SeleniumHQ Browser Automation"));
		Assert.assertEquals("SeleniumHQ Browser Automation", driver.getTitle());
	  //click on download tab
		driver.findElement(By.xpath("//*[text()='Downloads']")).click();
		//verify the page title
		wait.until(ExpectedConditions.titleContains("Downloads"));
		//click on java APIDocs link
		driver.findElement(By.xpath("//*[text()='Java']/following::a[4]")).click();
		//verify the Overview pg title
		wait.until(ExpectedConditions.titleIs("Overview"));
		//fetch all  frames into list
		List<WebElement>frmList = driver.findElements(By.tagName("frame"));
		System.out.println("number of frames -->"+frmList.size());
		//switch to AllClasses frame
		driver.switchTo().frame("packageFrame");
		//click on WebDriver link
		driver.findElement(By.xpath("//*[text()='WebDriver']")).click();
		//verify the Overview pg title
		wait.until(ExpectedConditions.titleIs("WebDriver"));
		//switch back to original position
		driver.switchTo().defaultContent();
		
		//switch to right side frame
		driver.switchTo().frame("classFrame");
		//verifying hte text in particular location - Interface WebDriver
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h2[@title='Interface WebDriver']"), "Interface WebDriver"));
		
		System.out.println("End of frameTest");

	}

	
	
}
