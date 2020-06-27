package testngprograms;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ZKDemoTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test(description = "handing the tweet popup test",priority=4)
	public void tweetPopupTest() throws InterruptedException {
		Reporter.log("Started executing the tweetPopupTest");
		Reporter.log("click on Date and Time Picker link in left panel");
		driver.findElement(By.linkText("Date and Time Picker")).click();
		Reporter.log("verify the page title");
		wait.until(ExpectedConditions.titleContains("ZK Live Demo - Date and Time Picker"));
		Reporter.log("identify the iframe");
		WebElement ifrmEle = driver.findElement(By.id("twitter-widget-0"));
		Reporter.log("Switch to the iframe element");
		driver.switchTo().frame(ifrmEle);
		Reporter.log("click on Tweet button");
		driver.findElement(By.xpath("//span[@class='label'][contains(.,'Tweet')]")).click();
		Reporter.log("fetch all window ids");
		Set<String> handles = driver.getWindowHandles();
		// iterate the collection using iterator()
		Iterator<String> it = handles.iterator();
		String pid = it.next();
		Reporter.log("parent window id-->" + pid);
		String chid = it.next();
		Reporter.log("child window id -->" + chid);
		Reporter.log("switch to child window");
		driver.switchTo().window(chid);
		Reporter.log("type the email in tweet popup email editbox");
		driver.findElement(By.name("session[username_or_email]")).sendKeys("rameshn3@gmail.com");
		Reporter.log("close the child window");
		driver.close();
		Reporter.log("switch back to parent window");
		driver.switchTo().window(pid);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		Reporter.log("clear the content in date editbox");
		WebElement dateEditbox = driver.findElement(By.className("z-datebox-input"));
		dateEditbox.clear();
		Reporter.log("type the value in dateeditbox");
		dateEditbox.sendKeys("12/06/2020");

	}

	//@Test(description = "fetching all the button names",priority=3,enabled=false)
	@Test(description = "fetching all the button names",priority=1)
	public void buttonTest() {

		Reporter.log("Started executing the buttonTest");
		Reporter.log("click on Button link in left panel");
		driver.findElement(By.linkText("Button")).click();
		Reporter.log("verify the page title");
		wait.until(ExpectedConditions.titleContains("ZK Live Demo - Button"));

		Reporter.log("fetch all the buttons into collection");
		List<WebElement> buttonList = driver.findElements(By.className("z-button"));

		Reporter.log("Number of buttons:" + buttonList.size());
		for (WebElement e : buttonList) {
			Reporter.log("button Name:" + e.getText());
		}

	}

	//@Test(description = "checking all the checkboxes",priority=2,invocationCount=3)
	@Test(description = "checking all the checkboxes",priority=2)
	public void checkboxTest() throws InterruptedException {

		Reporter.log("Started executing the checkboxTest");
		Reporter.log("click on Checkbox link in left panel");
		driver.findElement(By.linkText("Checkbox")).click();
		Reporter.log("verify the page title");
		wait.until(ExpectedConditions.titleContains("ZK Live Demo - Checkbox"));

		Reporter.log("fetch all thecheckboxes into collection");
		List<WebElement> checkboxList = driver.findElements(By.xpath("//*[@type='checkbox']"));

		Reporter.log("Number of checkboxes:" + checkboxList.size());
		for (int i = 0; i < checkboxList.size(); i++) {

			if (!(checkboxList.get(i).isSelected())) {

				checkboxList.get(i).click();
				Thread.sleep(2000);
			}
		}

		String selectedCheckboxes = driver
				.findElement(By.xpath("//*[contains(text(),'You have selected ')]/following::span")).getText();
		Reporter.log("checked checkboxes are: " + selectedCheckboxes);

	}

	//@Test(description = "select few radio buttons",priority=4,timeOut=3000)
	@Test(description = "select few radio buttons",priority=3)
	public void radioButtonTest() throws InterruptedException {

		Reporter.log("Started executing the radioButtonTest");
		Reporter.log("click on Radio Button link in left panel");
		driver.findElement(By.linkText("Radio Button")).click();
		Reporter.log("verify the page title");
		wait.until(ExpectedConditions.titleContains("ZK Live Demo - Radio Button"));
		Reporter.log("Select the Performance radio button");
		driver.findElement(By.xpath("//label[text()='Performance']/preceding::input[1]")).click();
		Reporter.log("Select the Forum radio button");
		driver.findElement(By.xpath("//label[text()='Forum']/preceding::input[1]")).click();
		Reporter.log("Select the Developer Guide radio button");
		driver.findElement(By.xpath("//label[text()='Developer Guide']/preceding::input[1]")).click();
		Thread.sleep(2000);
		Reporter.log("fetch the Feature value");
		String featureVal = driver.findElement(By.xpath("//*[contains(text(),'Feature')]/following::span")).getText();
		Reporter.log("Feature value is::" + featureVal);
		Reporter.log("fetch the Web Site value");
		String WebSiteVal = driver.findElement(By.xpath("//*[contains(text(),'Web Site')]/following::span")).getText();
		Reporter.log("Web Site value is::" + WebSiteVal);
		Reporter.log("fetch the Documentation value");
		String DocumentationVal = driver.findElement(By.xpath("//*[contains(text(),'Documentation')]/following::span"))
				.getText();
		Reporter.log("Feature value is::" + DocumentationVal);
	}

	@BeforeMethod
	public void beforeMethod() {
		Reporter.log(" i am inside @BeforeMethod block");
		Reporter.log("open the url");
		driver.get("https://www.zkoss.org/zkdemo/input/");
		Reporter.log("wait for the home page title");
		wait.until(ExpectedConditions.titleContains("ZK Live Demo - Input"));
		Assert.assertEquals(driver.getTitle(), "ZK Live Demo - Input");
	}

	@AfterMethod
	public void afterMethod() {
		Reporter.log("I am inside @AfterMethod ...");
		Reporter.log("clear the cookies");
		driver.manage().deleteAllCookies();
	}
	
	@Parameters("browser")
	@BeforeClass
	public void launchBrowser(String browser) {
		Reporter.log("Started executing the @BeforeClass -launchBrowser() ");
		if(browser.equalsIgnoreCase("firefox")) {
		//System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		Reporter.log("set the geckodriver.exe path");
		System.setProperty("webdriver.gecko.driver","D:\\webdriverjars\\executables\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		//WebDriverManager.firefoxdriver().setup();
		// interface refobj = new implementedclass()
		driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chrome\\chromedriver_win32 (2)\\chromedriver.exe");
			driver= new ChromeDriver();
		}else if(browser.equalsIgnoreCase("ie")) {
			//set the chromedriver.exe path
			System.setProperty("webdriver.ie.driver", "D:\\webdriverjars\\executables\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
			
			//interface refobj = new implementedclass()
		 driver = new InternetExplorerDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			//interface refobj = new implementedclass()
			driver = new EdgeDriver();
		}
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
