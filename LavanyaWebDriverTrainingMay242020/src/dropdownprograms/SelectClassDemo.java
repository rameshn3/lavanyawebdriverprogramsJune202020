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

public class SelectClassDemo {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		//set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");
		
		//interface refobj = new implementedclass()
		WebDriver driver = new ChromeDriver();
		
		//open the google.com
		driver.get("https://in.ebay.com/");
		
		//maximize the browser
		driver.manage().window().maximize();
		
		//add implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//create object for WebDriverWait class
		WebDriverWait wait =new WebDriverWait(driver,30);
		
		
		//verify the Rediff Homepage page title
		wait.until(ExpectedConditions.titleContains("Electronics, Cars, Fashion, Collectibles & More | eBay"));
		
		//Identify the category dropdown
		WebElement catgryDrp=driver.findElement(By.cssSelector("select#gh-cat"));
		
		//create object for Select class
		Select sel =new Select(catgryDrp);
		
		//select an option by labeltext
		sel.selectByVisibleText("Consumer Electronics");
		Thread.sleep(3000);
		System.out.println("selected option using visibletext is:"+sel.getFirstSelectedOption().getText());
		//select an option by Value attribute
		sel.selectByValue("625");
		Thread.sleep(3000);
		System.out.println("selected option using value attribute is:"+sel.getFirstSelectedOption().getText());
		//select an option by index
		sel.selectByIndex(4);
		System.out.println("selected option using index is:"+sel.getFirstSelectedOption().getText());
		Thread.sleep(3000);
		
		//fetch all the dropdown options into List collection
		List<WebElement>opts=sel.getOptions();
		//select last option from dropdown
		sel.selectByIndex(opts.size()-1);
		System.out.println("selected last option using index is:"+sel.getFirstSelectedOption().getText());
		Thread.sleep(3000);
		System.out.println("***********************************************"); 
		
		for(WebElement o:opts) {
			System.out.println(o.getText());
		}
		System.out.println("***********************************************");
		
		
		//close the browser
		driver.close();
		
	}

}
