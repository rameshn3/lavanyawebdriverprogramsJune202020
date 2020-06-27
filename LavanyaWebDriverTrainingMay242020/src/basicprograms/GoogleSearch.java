package basicprograms;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearch {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chrome\\chromedriver_win32 (2)\\chromedriver.exe");
		
		//interface refobj = new implementedclass()
		WebDriver driver = new ChromeDriver();
		
		//open the google.com
		driver.get("http://google.com");
		
		//maximize the browser
		driver.manage().window().maximize();
		
		//add implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//create object for WebDriverWait class
		WebDriverWait wait =new WebDriverWait(driver,30);
		
		//call all browser methods
		//fetch the current page title
		String t=driver.getTitle();
		System.out.println("current page title is-->"+t);
		
		//fetch the current page absolute url
		String absurl=driver.getCurrentUrl();
		System.out.println("current page absolute url:"+absurl);
		
		//fetch the current page html source code
		String src= driver.getPageSource();
		
		//fetch the current page window id
		String pid = driver.getWindowHandle();
		System.out.println("fetch current window id:"+pid);
		
		//verif ythe google page title
		wait.until(ExpectedConditions.titleIs("Google"));
		
		//type the selenium keyword in search editbox
		driver.findElement(By.name("q")).sendKeys("selenium");
		//submit on the search editbox
		driver.findElement(By.name("q")).submit();
		//verify the selenium - Google Search title
		wait.until(ExpectedConditions.titleContains("selenium - Google Search"));
		//wait for the search results text
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
		
		//fetch the searc hresultstext
		String txt=driver.findElement(By.cssSelector("div#result-stats")).getText();
		System.out.println("search results text is-->"+txt);
		
		//String txt = "About 3,82,00,000 results (0.72 seconds)";
		String[] str=txt.split(" ");
		//str[] = ["About","3,82,00,000","results","(0.72","seconds)"]
		//           0         1             2        3        4
		System.out.println("search results count is-->"+str[1]);
		
		//click on Selenium link
		driver.findElement(By.xpath("//*[text()='Selenium']")).click();
		//Verify selenium website homepage title - SeleniumHQ Browser Automation
		wait.until(ExpectedConditions.titleIs("SeleniumHQ Browser Automation"));
		
		//click on Downloads link
		driver.findElement(By.xpath("//*[text()='Downloads']")).click();
		//Verify Downloads page title - Downloads
		wait.until(ExpectedConditions.titleIs("Downloads"));
		
		//go back to selenium homepage 
		driver.navigate().back();
		//Verify selenium website homepage title - SeleniumHQ Browser Automation
		wait.until(ExpectedConditions.titleIs("SeleniumHQ Browser Automation"));
		Thread.sleep(2000);
		//navigate to downloads page
		driver.navigate().forward();
		//Verify Downloads page title - Downloads
		wait.until(ExpectedConditions.titleIs("Downloads"));
		
		Thread.sleep(3000);

		//close the browser
		driver.close();
		
	}

}
