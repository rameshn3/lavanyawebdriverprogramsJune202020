package testngprograms;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;


public class DownloadFileUsingChrome {
	private WebDriver driver = null;
	private WebDriverWait wait = null;
	private String path="D:\\webdriverjars";
	@Test(description = "download files Using chrome browser")
	public void downloadFileUsingChromeTest() throws InterruptedException {
		Reporter.log("Started executing the downloadFileUsingChromeTest()");
		driver.get("https://www.selenium.dev/downloads/");
		Reporter.log("verify the page title");
		wait.until(ExpectedConditions.titleContains("Downloads"));
		//click on Selenium server 3.14 ..link
		driver.findElement(By.xpath("//html/body/div[1]/div[2]/p[1]/a")).click();
		
		Thread.sleep(5000);
		verifyDownloadedFile(path);
		
	}

	private void verifyDownloadedFile(String path) {
		//create object for File class
		File f = new File(path);
		//take all the files from given directory
		File[] files=f.listFiles();
		for(File f1:files) {
			if(f1.getName().equalsIgnoreCase("selenium-server-standalone-3.141.59.jar")) {
				System.out.println("downlaoded file is available in the dir:"+f1.getName());
			}else {
				System.out.println("downloaded file is not available in the directory");
			}
		}
	}
	
	

	@BeforeClass
	public void launchBrowser() {
		Reporter.log("Started executing the @BeforeClass -launchBrowser() ");
		//System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chrome\\chromedriver_win32 (2)\\chromedriver.exe");
		ChromeOptions option=Utility.downloadFileUsingChrome(path);
		driver= new ChromeDriver(option);
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
