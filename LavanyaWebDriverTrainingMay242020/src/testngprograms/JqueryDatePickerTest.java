package testngprograms;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class JqueryDatePickerTest {
	WebDriver driver = null;
	WebDriverWait wait = null;

	@Test
	public void dateSelectionTest() throws InterruptedException {

		driver.get("http://jqueryui.com/datepicker/");
		
		WebElement frameElement = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frameElement);
		// click to open the date time picker calendar.
		By dtp = By.xpath(".//*[@id='datepicker']");
		wait.until(ExpectedConditions.presenceOfElementLocated(dtp));
		driver.findElement(dtp).click();

		// Provide the day of the month to select the date.
		HandleJQueryDateTimePicker("22");
	}

	// Function to select the day of the month in the date picker.
	public void HandleJQueryDateTimePicker(String day) throws InterruptedException {

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ui-datepicker-div")));
		WebElement table = driver.findElement(By.className("ui-datepicker-calendar"));
		System.out.println("JQuery Calendar Dates");

		List<WebElement> tableRows = table.findElements(By.xpath("//tr"));
		for (WebElement row : tableRows) {
			List<WebElement> cells = row.findElements(By.xpath("td"));

			for (WebElement cell : cells) {
				if (cell.getText().equals(day)) {
					driver.findElement(By.linkText(day)).click();
				}
			}
		}

		// Switch back to the default screen again and scroll up by using
		// the negative y-coordinates.
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("scroll(0, -250);");

		// Intentional pause for 2 seconds.
		Thread.sleep(2000);
	}

	@BeforeTest
	public void beforeTest() {

		/*
		 * //set the geckodriver.exe path System.setProperty("webdriver.gecko.driver",
		 * "D:\\BrowserExefiles\\geckodriver-v0.26.0-win64\\geckodriver.exe"); //launch
		 * the browser //interface refvar=new implmentingclass(); driver=new
		 * FirefoxDriver();
		 */
		Reporter.log("set CHROME_DRIVER_SILENT_OUTPUT_PROPERTY to avoid TImeOut log in console");
		System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chrome\\chromedriver_win32 (2)\\chromedriver.exe");
		
		Reporter.log("set the chromedriver.exe file");
		
		// interface refobj=new implementingclass();
		driver = new ChromeDriver();

		// maximize the window
		driver.manage().window().maximize();
		// add implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// create Object for WebDriverWait class
		wait = new WebDriverWait(driver, 30);

	}

	@AfterTest
	public void afterTest() {
		//driver.close();
	}

}

