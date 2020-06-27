package junitprograms;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class GoogleTest {
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

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("started executing the setup() @BeforeEach block ...");
		System.out.println("open the Google ...");
		driver.get("http://google.com");
		wait.until(ExpectedConditions.titleContains("Google"));
		Assert.assertEquals("Google", driver.getTitle());
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("started executing the @AftreEach annotated block"); // clear the cookies
		driver.manage().deleteAllCookies();
	}

	@Test
	void testGoogleLogo() {
		System.out.println("started executing the testGoogleLogo()..");
		WebElement gLogo = driver.findElement(By.id("hplogo"));
		// fetch the googleLogo src value
		String srcVal = gLogo.getAttribute("src");
		System.out.println("google logo src value is:" + srcVal);
		// fetch alt attribute value
		String altVal = gLogo.getAttribute("alt");
		System.out.println("glogo alt attribute value is:" + altVal);
		// color of the glogo
		String clr = gLogo.getCssValue("color");
		System.out.println("color of the google logo is:" + clr);
		String fntFamily = gLogo.getCssValue("font-family");
		System.out.println("Glogo font family is-->" + fntFamily);
		// fetch x and y cooradinates of glogo
		Point p = gLogo.getLocation();
		System.out.println("xcoordinate :" + p.getX() + " y coordinate :" + p.getY());
		// fetch the size of the gLogo
		Dimension d = gLogo.getSize();
		System.out.println("gLogo height is:" + d.getHeight() + " gologo width is: " + d.getWidth());
		System.out.println("fetch the gLogo tag:" + gLogo.getTagName());

	}

	@Test
	public void searchTest() {
		System.out.println("started executing the searchTest()..");
		// type the selenium keyword in search editbox
		driver.findElement(By.name("q")).sendKeys("selenium");
		// submit on the search editbox
		driver.findElement(By.name("q")).submit();
		// verify the selenium - Google Search title
		wait.until(ExpectedConditions.titleContains("selenium - Google Search"));
		// wait for the search results text
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));

		// fetch the searc hresultstext
		String txt = driver.findElement(By.cssSelector("div#result-stats")).getText();
		System.out.println("search results text is-->" + txt);

		// String txt = "About 3,82,00,000 results (0.72 seconds)";
		String[] str = txt.split(" ");
		// str[] = ["About","3,82,00,000","results","(0.72","seconds)"]
		// 0 1 2 3 4
		System.out.println("search results count is-->" + str[1]);
	}

	@Test
	public void failTest() {
		Assert.fail("testcase failed");
	}
	
}
