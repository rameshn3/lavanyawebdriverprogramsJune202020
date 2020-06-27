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

class NormalFrameDemo {
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
	void testInternalFrame() {
		System.out.println("started executing the testInternalFrame()..");
		driver.get("https://jqueryui.com/");
		wait.until(ExpectedConditions.titleContains("jQuery UI"));
		Assert.assertEquals("jQuery UI", driver.getTitle());
	    //click on Autocomplete link under widget section
		driver.findElement(By.xpath("//*[text()='Autocomplete']")).click();
		//verify the Autocomplete title
		wait.until(ExpectedConditions.titleContains("Autocomplete | jQuery UI"));
		//identify the internal Frame
		WebElement ifrmEle=driver.findElement(By.className("demo-frame"));
		//switch to iframe element
		driver.switchTo().frame(ifrmEle);
		
		//type the value in tags editbox
		driver.findElement(By.id("tags")).sendKeys("selenium");
		//navigate back to previous page
		driver.navigate().back();
		//verify the jQuery UI page title
		wait.until(ExpectedConditions.titleIs("jQuery UI"));
		//click on Accordion link
		driver.findElement(By.linkText("Accordion")).click();
		
		System.out.println("End of frameTest");

	}

	
	
}
