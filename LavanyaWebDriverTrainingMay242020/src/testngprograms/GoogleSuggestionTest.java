package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Iterator;
import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class GoogleSuggestionTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test
	public void googleSuggestionTest() throws InterruptedException {
		// open the google.com
		driver.get("https://google.com");
		wait.until(ExpectedConditions.titleIs("Google"));
		Thread.sleep(2000);
		// type the selenium keyword in search editbox.
		driver.findElement(By.name("q")).sendKeys("selenium", Keys.BACK_SPACE, "m");

		String expKeyWord = "selenium download";
		// identify the suggestion box
		WebElement suggbox = driver.findElement(By.className("erkvQe"));
		// fetch all the suggestions into collection
		List<WebElement> suggList = suggbox.findElements(By.tagName("li"));
		// Iterate the collection using iterator()
		/*
		 * Iterator<WebElement>it=suggList.iterator(); while(it.hasNext()) {
		 * System.out.println("suggestion name is-->"+it.next().getText());
		 * if(it.next().getText().toLowerCase().trim().equals(expKeyWord)) {
		 * it.next().click(); break; } }
		 */
		for (WebElement e : suggList) {
			System.out.println(e.getText());
			if (e.getText().toLowerCase().trim().equals(expKeyWord)) {
				e.click();
				break;
			}
		}
		Thread.sleep(1000);
		// verify the search results count text is present in the webpage or not
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
		// fetch the search results count text and extract the only count from search
		// results count text.
		String txt = driver.findElement(By.xpath("//div[@id='result-stats']")).getText();
		System.out.println("search results text is-->" + txt);
		// String txt="About 3,99,00,000 results (0.59 seconds) ";
		String[] str = txt.split(" ");
		// str[]=["About","3,99,00,000","results","(0.59","seconds)"]
		System.out.println("selenium results count is-->" + str[1]);

	}

	@Parameters({ "browser" })
	@BeforeClass
	public void launchBrowser(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {
			// set the geckodriver.exe path
			System.setProperty("webdriver.gecko.driver",
					"D:\\BrowserExefiles\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			// launch the browser
			// interface refvar=new implmentingclass();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chrome\\chromedriver_win32 (2)\\chromedriver.exe");
			driver= new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			// set the MicrosoftEdgeDriver.exe path
			System.setProperty("webdriver.edge.driver", "D:\\BrowserExefiles\\MicrosoftWebDriver.exe");
			// launch the edge browser
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			// set IEDriverServer.exe file
			System.setProperty("webdriver.ie.driver",
					"D:\\BrowserExefiles\\IEDriverServer_Win32_3.14.0\\IEDriverServer.exe");
			// launch the IE browser
			driver = new InternetExplorerDriver();
		}

		// maximize the window
		driver.manage().window().maximize();
		// add implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// create Object for WebDriverWait class
		wait = new WebDriverWait(driver, 30);
	}

	@AfterClass
	public void afterClass() {
		// close the browser
		driver.close();
	}

}
