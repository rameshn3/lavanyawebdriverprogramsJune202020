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

public class IMDBTop250Links {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		// set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver",
				"D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");

		// interface refobj = new implementedclass()
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// open the google.com
		driver.get("https://www.imdb.com/chart/top");

		// maximize the browser
		driver.manage().window().maximize();

		// create object for WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// verify the salesforce home page title
		wait.until(ExpectedConditions.titleContains("IMDb Top 250 - IMDb"));
		
		//fetch all the movie links into collection
		List<WebElement>top250MoviesList=driver.findElements(By.xpath("//table[@data-caller-name='chart-top250movie']//tbody/tr/td[2]/a"));
		
		for(int i=1;i<top250MoviesList.size();i++) {
			System.out.println("***********************************************************************************");
			//fetch movie name
			String movieName=driver.findElement(By.xpath("//table[@data-caller-name='chart-top250movie']//tbody/tr["+i+"]/td[2]/a")).getText();
			System.out.println("moview name is:"+movieName);
			//fetch the movie link url
			String url=driver.findElement(By.xpath("//table[@data-caller-name='chart-top250movie']//tbody/tr["+i+"]/td[2]/a")).getAttribute("href");
			System.out.println(movieName+ " Movie url is: "+url);
			//fetch the tooltip of each movie 
			String movieTp=driver.findElement(By.xpath("//table[@data-caller-name='chart-top250movie']//tbody/tr["+i+"]/td[2]/a")).getAttribute("title");
			System.out.println(movieName+ " Tooltip is: "+movieTp);
		
		}
		Thread.sleep(3000);
		//close the browser
		driver.close();

	}

}
