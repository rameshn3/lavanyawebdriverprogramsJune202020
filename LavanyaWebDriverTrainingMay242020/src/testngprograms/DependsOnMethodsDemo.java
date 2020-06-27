package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class DependsOnMethodsDemo {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test(description = "linkedinHometest")
	public void linkedinHomeTest() throws InterruptedException {
		Reporter.log("Started executing the linkedinHomeTest");
		driver.get("https://www.linkedin.com/");
		Reporter.log("verify the page title");
		wait.until(ExpectedConditions.titleContains("LinkedIn: Log In or Sign Up"));
		
		Reporter.log("check whether sing link is clickable aor not ");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign in")));
		Reporter.log("click on sign in link ");
		driver.findElement(By.className("nav__button-secondary")).click();
		wait.until(ExpectedConditions.titleContains("LinkedIn Login, Sign in | LinkedIn"));
		Assert.fail("HomeTest failed");
	}
	@Parameters({"email","pwd"})
	@Test(description = "loginTest",dependsOnMethods= {"linkedinHomeTest"},alwaysRun=true)
	public void doLoginTest(String email,String pwd) throws InterruptedException {

		Reporter.log("Started executing the doLoginTest");
		Reporter.log("type the email in email editbox:"+email);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(email);
		Reporter.log("type the password in password editbox:"+pwd);
		driver.findElement(By.name("session_password")).clear();
		driver.findElement(By.name("session_password")).sendKeys(pwd);
		Reporter.log("click on sign in button");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(2000);
	}

	@Test(description = "dologout from application",dependsOnMethods= {"doLoginTest"})
	public void doLogoutTest() throws InterruptedException {

		Reporter.log("Started executing the doLogoutTest");
		Reporter.log("verify the profile railcard");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'profile-rail-card')]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'profile-rail-card')]")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'nav-item__profile-member-photo nav-item__icon')]")));
		
		driver.findElement(By.xpath("//*[contains(@class,'nav-item__profile-member-photo nav-item__icon')]")).click();
		
		
		Reporter.log("wait for the visiblity of signout link");
		WebElement signoutLink=driver.findElement(By.xpath("//*[@data-control-name='nav.settings_signout']"));
		wait.until(ExpectedConditions.visibilityOf(signoutLink));
		signoutLink.click();
			

	}

	
	

	@BeforeClass
	public void launchBrowser() {
		Reporter.log("Started executing the @BeforeClass -launchBrowser() ");
		//System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		Reporter.log("set the chromedriver.exe path");
		System.setProperty("webdriver.gecko.driver",
				"D:\\webdriverjars\\executables\\geckodriver-v0.26.0-win64\\geckodriver.exe");

		// interface refobj = new implementedclass()
		driver = new FirefoxDriver();
		Reporter.log("maximize the code");
		driver.manage().window().maximize();
		Reporter.log("add implicit wait");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Reporter.log("create object for WebDriverWait class");
		wait = new WebDriverWait(driver, 30);
	}

	@AfterClass
	public void afterClass() {
		Reporter.log("started executing the afterClass() of @AfterClass block ...");
		if (driver != null) {
			driver.close();
		}

	}

}
