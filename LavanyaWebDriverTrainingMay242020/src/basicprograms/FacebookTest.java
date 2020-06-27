package basicprograms;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookTest {

	public static void main(String[] args) {
		//set the chromdriver.exe path
		System.setProperty("webdriver.chrome.driver", "D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");
		//interface refobj=new browserdriverclass();
		WebDriver driver=new ChromeDriver();
		
		//open the url
		driver.get("https://facebook.com");
		
		//fetch the page title
		String t=driver.getTitle();
		System.out.println("current page title is-->"+t);
		
		//fetch the current page absolute url
		String absurl=driver.getCurrentUrl();
		System.out.println("current page absolute url is->"+absurl);
		
		//fetch the current page source code
		String src=driver.getPageSource();
		
		//fetch the current window id
		String pid=driver.getWindowHandle();
		System.out.println("current window id -->"+pid);
		
		//fetch all the window ids
		Set<String>handles=driver.getWindowHandles();
		System.out.println("handles collection is-->"+handles);
		
		//clsoe the browser
		driver.close();
		

	}

}
