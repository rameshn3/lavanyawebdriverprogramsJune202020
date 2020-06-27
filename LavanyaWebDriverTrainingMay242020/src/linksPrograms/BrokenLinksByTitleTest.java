package linksPrograms;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrokenLinksByTitleTest {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		// set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver",
				"D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");

		// interface refobj = new implementedclass()
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// open the google.com
		driver.get("http://newtours.demoaut.com/");

		// maximize the browser
		driver.manage().window().maximize();

		// create object for WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// verify the salesforce home page title
		wait.until(ExpectedConditions.titleContains("Welcome: Mercury Tours"));
		
		//fetch all the links into collection
		List<WebElement>linksList=driver.findElements(By.tagName("a"));
		
		//create a static String[] to store link names
		String[] linkTextArray=new String[linksList.size()];
		
		//extract the each link name from collection and store in String array
		int i=0;
		for(WebElement e:linksList) {
			linkTextArray[i]=e.getText();
			i++;
		}
		String expTitle="Not Found";
		
		//iterate each array and click on it
		for(String linkName:linkTextArray) {
			driver.findElement(By.linkText(linkName)).click();
			if(driver.getTitle().equalsIgnoreCase(expTitle)) {
				System.out.println(linkName+" is broken/not found :"+driver.getTitle());
			}else {
				System.out.println(linkName+" is working :"+driver.getTitle());
			}
			//navigate back to previous page
			driver.navigate().back();
		}
		
		Thread.sleep(3000);
		//close the browser
		driver.close();

	}

}
