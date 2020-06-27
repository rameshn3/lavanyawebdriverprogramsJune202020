package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;


public class OpenLinkInNewTab {
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
		
		WebElement forgotPasswordLink=driver.findElement(By.linkText("Forgot password?"));
		
		Actions act = new Actions(driver);
		
		//move the cursor to forgot password link
		act.moveToElement(forgotPasswordLink).perform();
		//open link in new tab
		//act.contextClick(forgotPasswordLink).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		
		//open link in new window
		act.contextClick(forgotPasswordLink).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		
		Thread.sleep(3000);
		
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
