package linksPrograms;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrollTakeScreenshotTest {
	private static WebDriver driver = null;

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		// set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver",
				"D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");

		// interface refobj = new implementedclass()
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// open the google.com
		driver.get("https://www.amazon.in/");

		// maximize the browser
		driver.manage().window().maximize();

		// create object for WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// verify the salesforce home page title
		wait.until(ExpectedConditions.titleContains("Amazon.in"));

		// type the search keyword in searcheditbox
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("headphones");
		// Press the enter key on search editbox
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);

		// verify the search page title
		wait.until(ExpectedConditions.titleContains("Amazon.in : headphones"));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class,'a-spacing-top-small')]/span")));
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[contains(@class,'a-spacing-top-small')]/span")));

		// SCROLLING USING EventFiringWebDriver
		EventFiringWebDriver evnt = new EventFiringWebDriver(driver);
		// scroll down the page
		evnt.executeScript("window.scrollBy(0,2000)", "");

		captureScreenshot("UsingEventFireScrollDown");
		Thread.sleep(2000);
		// scroll UP the page
		evnt.executeScript("window.scrollBy(0,-2000)", "");

		captureScreenshot("UsingEventFireScrollUP");
		Thread.sleep(2000);
		// 2. Scroll the page Using JavascriptExecutor
		JavascriptExecutor jsx = (JavascriptExecutor) driver;

		// scroll down
		jsx.executeScript("window.scrollBy(0,4000)", "");
		captureScreenshot("UsingJavascriptExeScrollDown");
		Thread.sleep(2000);
		// scroll UP the page
		evnt.executeScript("window.scrollBy(0,-3500)", "");

		captureScreenshot("UsingJavascriptExeScrollUP");
		Thread.sleep(2000);

		//scroll for particular element
		WebElement backToTop = driver.findElement(By.id("navBackToTop"));
		jsx.executeScript("arguments[0].scrollIntoView(true);", backToTop);
		captureScreenshot("ScrollForBackToTop");
		Thread.sleep(2000);
		//click on back to top 
		jsx.executeScript("arguments[0].click();", backToTop);
		
	//scroll using Keys enum
		for(int i=1;i<=10;i++) {
			//scrolldown using Keys enum - DOWN key
			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
			captureScreenshot("ScrollDownUsingDOWNKey");
		}
		Thread.sleep(3000);
		for(int i=1;i<=10;i++) {
			//scrolldown using Keys enum - UP key
			driver.findElement(By.tagName("body")).sendKeys(Keys.UP);
			captureScreenshot("ScrollUPUsingUPKey");
		}
		
		Thread.sleep(3000);
		// close the browser
		driver.close();

	}

	private static void captureScreenshot(String screenshotName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		String str = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\src\\screenshots\\" + screenshotName+str));
	}

}
