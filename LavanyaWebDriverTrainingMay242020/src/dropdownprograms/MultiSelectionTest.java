package dropdownprograms;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultiSelectionTest {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		// set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver",
				"D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");

		// interface refobj = new implementedclass()
		WebDriver driver = new ChromeDriver();

		// open the google.com
		driver.get("https://openwritings.net/sites/default/files/selenium-test-pages/select.html");

		// maximize the browser
		driver.manage().window().maximize();

		// add implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// create object for WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// verify the Rediff Homepage page title
		wait.until(ExpectedConditions.titleContains("Test Page: Select"));

		// Identify the category dropdown
		WebElement msdrp = driver.findElement(By.cssSelector("select#multi-selections"));

		// create object for Select class
		Select sel = new Select(msdrp);

		// select an option by labeltext
		sel.selectByVisibleText("February");

		// select an option by Value attribute
		sel.selectByValue("Apr");
		// select an option by index
		sel.selectByIndex(0);
		sel.selectByVisibleText("June");
		sel.selectByValue("Aug");
		sel.selectByIndex(9);
		sel.selectByVisibleText("December");
		System.out.println("first selected option is:" + sel.getFirstSelectedOption().getText());
		// fetch all selected options
		List<WebElement> selectedOptsList = sel.getAllSelectedOptions();
		System.out.println("all selected options size is:" + selectedOptsList.size());
		for (WebElement opt : selectedOptsList) {
			System.out.println(opt.getText());
		}
		System.out.println("********************************************");
		// deselect an option by visibletext
		sel.deselectByVisibleText("February");
		// deselect an option by value attribute
		sel.deselectByValue("Apr");
		sel.deselectByIndex(0);
		// fetch all selected options
		List<WebElement> selectedOptsList1 = sel.getAllSelectedOptions();
		System.out.println("After deselect remaining selected options size is:" + selectedOptsList1.size());
		for (WebElement opt : selectedOptsList1) {
			System.out.println(opt.getText());
		}
		System.out.println("********************************************");
		// deselct all now
		sel.deselectAll();
		// fetch all selected options
		List<WebElement> selectedOptsList2 = sel.getAllSelectedOptions();
		System.out.println("After deselectall selected options size is:" + selectedOptsList2.size());

		// fetch all the dropdown options into List collection
		List<WebElement> opts = sel.getOptions();
		// select last option from dropdown
		sel.selectByIndex(opts.size() - 1);
		System.out.println("selected last option using index is:" + sel.getFirstSelectedOption().getText());
		Thread.sleep(3000);
		System.out.println("***********************************************");

		for (WebElement o : opts) {
			System.out.println(o.getText());
		}
		System.out.println("***********************************************");

		// close the browser
		driver.close();

	}

}
