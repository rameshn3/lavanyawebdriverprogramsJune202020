package dropdownprograms;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RediffDropdownTest {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		//set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");
		
		//interface refobj = new implementedclass()
		WebDriver driver = new ChromeDriver();
		
		//open the google.com
		driver.get("https://www.rediff.com/");
		
		//maximize the browser
		driver.manage().window().maximize();
		
		//add implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//create object for WebDriverWait class
		WebDriverWait wait =new WebDriverWait(driver,30);
		
		
		//verify the Rediff Homepage page title
		wait.until(ExpectedConditions.titleContains("Rediffmail"));
		
		//Click on Create Account link 
		driver.findElement(By.linkText("Create Account")).click();
		
		//verify the selenium - Google Search title
		wait.until(ExpectedConditions.titleContains("Rediffmail Free Unlimited Storage"));
		//wait for the Create a Rediffmail account text
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(.,'Create a Rediffmail account')]")));
		
		//Identify the country dropdown
		WebElement cntryDrp=driver.findElement(By.cssSelector("select#country"));
		
		//fetch all the dropdown options using findElements API 
		List<WebElement>opts=cntryDrp.findElements(By.tagName("option"));
		//for(datatype variablename:collectionname){l+s}
		for(WebElement o:opts) {
			System.out.println(o.getText());
			if(o.getText().equalsIgnoreCase("Uruguay")) {
				o.click();
				break;
			}
		}
		
		Thread.sleep(3000);

		//close the browser
		driver.close();
		
	}

}
