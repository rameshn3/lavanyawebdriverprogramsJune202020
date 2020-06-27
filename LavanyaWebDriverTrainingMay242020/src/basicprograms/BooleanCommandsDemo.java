package basicprograms;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BooleanCommandsDemo {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		//set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");
		
		//interface refobj = new implementedclass()
		WebDriver driver = new ChromeDriver();
		
		//open the google.com
		driver.get("https://www.salesforce.com/");
		
		//maximize the browser
		driver.manage().window().maximize();
		
		//add implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//create object for WebDriverWait class
		WebDriverWait wait =new WebDriverWait(driver,30);
		
			
		//verify the salesforce home page title
		wait.until(ExpectedConditions.titleContains("Salesforce IN"));
		
		//fetch the first news text
		WebElement loginBtn = driver.findElement(By.xpath("//*[@role='button' and @aria-label='Login']"));
		
		if(loginBtn.isDisplayed()&&loginBtn.isEnabled()) {
			wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
			loginBtn.click();
		}
		//verify the salesforce login page title
		wait.until(ExpectedConditions.titleIs("Login | Salesforce"));
		//identif ythe remember me checkbox
		WebElement remembCheckbox = driver.findElement(By.id("rememberUn"));
		//check the checkbox
		if(remembCheckbox.isSelected()) {
			System.out.println("checkbox is already checked");
		}else {
			remembCheckbox.click();
		}
			
		Thread.sleep(3000);
		//uncheck the checkbox
		if(remembCheckbox.isSelected()) {
			remembCheckbox.click();
		}else {
			System.out.println("remembCheckbox is already unchecked");
		}
		
		Thread.sleep(3000);
		//close the browser
		driver.close();
		
	}

}
