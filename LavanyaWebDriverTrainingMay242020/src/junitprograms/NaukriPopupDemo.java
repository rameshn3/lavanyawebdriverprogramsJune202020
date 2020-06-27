package junitprograms;

import java.util.List;
import java.util.Set;
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

class NaukriPopupDemo {
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
	void testNaukriPopup() {
		System.out.println("started executing the testNaukriPopup()..");
		driver.get("https://www.naukri.com/");
		wait.until(ExpectedConditions.titleContains("Naukri.com"));
		//fetch parent window id
		String pid=driver.getWindowHandle();
		//fetch all the window ids
		Set<String>handles = driver.getWindowHandles();
		
		for(String h:handles) {
			if(!pid.equals(h)) {
				driver.switchTo().window(h);
				driver.close();
			}
		}
		
		//switch back to parent window
		driver.switchTo().window(pid);
		//type the search keyword
		driver.findElement(By.id("qsb-keyword-sugg")).sendKeys("automation");
		//click on search button
		driver.findElement(By.className("btn")).click();
		

	}

	
	
}
