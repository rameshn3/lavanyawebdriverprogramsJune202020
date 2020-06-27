package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class CheckboxCountTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;
	private int checkedCount;
	private int uncheckedCount;
	@BeforeMethod
	public void openUrl() {
		// open the google.com
		driver.get("http://browsershots.org/");
		wait.until(ExpectedConditions.titleContains("Browsershots"));
	}

	@Test(priority=1)
	public void checkedCheckboxCountTest() {
		// fetch all the checked checkbox count
		List<WebElement> checkedchkBox = driver
				.findElements(By.xpath("//input[@type='checkbox' and @checked='checked']"));
		checkedCount=checkedchkBox.size();
		System.out.println("checked checkbox count is-->" + checkedchkBox.size());

	}

	@Test(priority=2)
	public void unCheckedCheckboxCountTest() {
		// fetch all the checked checkbox count
		List<WebElement> uncheckedchkBox = driver
				.findElements(By.xpath("//input[@type='checkbox' and not(@checked='checked')]"));
		uncheckedCount=uncheckedchkBox.size();
		System.out.println("unchecked checkbox count is-->" + uncheckedchkBox.size());

	}

	@Test(priority=3)
	public void totalCheckboxCountTest() {
		// fetch all the checked checkbox count
		List<WebElement> totalchkBox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		int totalCount=totalchkBox.size();
		System.out.println("total checkbox count is-->" + totalchkBox.size());
		Assert.assertEquals(totalCount, (checkedCount+uncheckedCount));
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
					"D:\\BrowserExefiles\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
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
