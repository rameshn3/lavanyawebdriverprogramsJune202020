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

public class GetElementMethodsDemo {

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
		
			
		//verify the rediff mail home page title
		wait.until(ExpectedConditions.titleContains("Rediffmail"));
		
		//fetch the first news text
		String txt = driver.findElement(By.xpath("//*[contains(@id,'topdiv')]/h2[2]/a")).getText();
		
		System.out.println("first news text is-->"+txt);
		//submit on the search editbox
		WebElement signinLink = driver.findElement(By.linkText("Sign in"));
		
		//fetch url of hte link
		String url= signinLink.getAttribute("href");
		System.out.println("sign in link url is ->"+url);
		
		//fetc the tooltip of the url
		String tp = signinLink.getAttribute("title");
		System.out.println("tooltip of hte url is -->"+tp); 
		
		//fetch the text-decoration css property value
		String ud=signinLink.getCssValue("text-decoration");
		System.out.println("underline value for sign in link is-->"+ud);
		
		//fetch the color of the signin link
		String clr = signinLink.getCssValue("color");
		System.out.println("color of the link is:"+clr);
		
		//fetch the font family
		String fntFamily = signinLink.getCssValue("font-family");
		System.out.println("font family of the sign in link -->"+fntFamily);
		
		WebElement searchEditbox = driver.findElement(By.className("homesrchbox"));
		
		//fetch the x and y coordinates of searc heditbox
		Point p = searchEditbox.getLocation();
		
		System.out.println("x coordinate is : "+p.getX()+" y coordinate :"+p.getY());
		
		//fetch the size of the searchEditbox
		Dimension d = searchEditbox.getSize();
		System.out.println("height of the searchEditbox :"+d.getHeight()+" width of the searchEditbox: "+d.getWidth());
		
		//fetch the tag of the searchEditbox
		String tg = searchEditbox.getTagName();
		System.out.println("tag name of the searchEditbox : "+tg);
		Thread.sleep(3000);

		//close the browser
		driver.close();
		
	}

}
