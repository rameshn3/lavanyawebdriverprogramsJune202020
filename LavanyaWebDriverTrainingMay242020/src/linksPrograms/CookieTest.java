package linksPrograms;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
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

public class CookieTest {
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

		// add cookie to browser
		// create Object for Cookie class
		Cookie ck = new Cookie("testcookie", "hjdska23kj23kj45hj45k6");
		// add cookie to the browser
		driver.manage().addCookie(ck);
		// fetch all the cookies
		Set<Cookie> ckColl = driver.manage().getCookies();
		System.out.println("Number of cookies in the browser : " + ckColl.size());
		for (Cookie c : ckColl) {
			System.out.println("************************************************************");
			System.out.println("cookie name :" + c.getName() + " cookie value :" + c.getValue());
			System.out.println("cookie domain: " + c.getDomain() + " cookie path :" + c.getPath());
			System.out.println("expiry date of the cookie: " + c.getExpiry());
		}

		// delete cookie by name
		driver.manage().deleteCookieNamed("testcookie");
		// fetch all the cookies
		Set<Cookie> ckColl1 = driver.manage().getCookies();
		System.out.println("Number of cookies in the browser after delete one cookie : " + ckColl1.size());
		// delete all the cookies
		driver.manage().deleteAllCookies();

		// fetch all the cookies
		Set<Cookie> ckColl2 = driver.manage().getCookies();
		System.out.println("Number of cookies in the browser after delete all cookie : " + ckColl2.size());
		Thread.sleep(3000);
		// close the browser
		driver.close();

	}

	private static void captureScreenshot(String screenshotName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		String str = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\src\\screenshots\\" + screenshotName + str));
	}

}
