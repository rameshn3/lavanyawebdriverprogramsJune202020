package linksPrograms;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrokenImagesByStatusCodeTest {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		//set the geckodriver.exe path
				System.setProperty("webdriver.gecko.driver", "D:\\webdriverjars\\executables\\geckodriver-v0.26.0-win64\\geckodriver.exe");
				
				//interface refobj = new implementedclass()
				WebDriver driver = new FirefoxDriver();
		
		// open the google.com
		driver.get("http://newtours.demoaut.com/");

		// maximize the browser
		driver.manage().window().maximize();

		// create object for WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// verify the salesforce home page title
		wait.until(ExpectedConditions.titleContains("Welcome: Mercury Tours"));
		
		//fetch all the images into collection
		List<WebElement>imagesList=driver.findElements(By.tagName("img"));
		
		for(WebElement e:imagesList
				) {
			String url=e.getAttribute("src");
			verifyLinkActive(url);
		}
		
		
		Thread.sleep(3000);
		//close the browser
		driver.close();

	}
	
	private static void verifyLinkActive(String url) throws IOException {
		//create Object for URL class
		URL ul = new URL(url);
		
		//open the connection
		HttpURLConnection hc=(HttpURLConnection) ul.openConnection();
		
		//connect to the given url
		hc.connect();
		
		//fetch the responsestatuscode
		int respCode=hc.getResponseCode();
		//fetch the response message
		String respMsg=hc.getResponseMessage();
		if(respCode==200) {
			System.out.println(url+" is active/working fine : "+respCode+" - "+respMsg);
		}else if(respCode==404) {
			System.out.println(url+" is inactive/not working : "+respCode+" - "+respMsg);
		}
		hc.disconnect();
		
	}

}
