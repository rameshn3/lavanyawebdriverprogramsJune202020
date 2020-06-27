package junitprograms;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class AlertDemo {
	private static WebDriver driver = null;
	private static WebDriverWait wait = null;

	@BeforeAll
	static void launchBrowser() throws Exception {
		System.out.println("started executing the launchBrowser() @BeforeAll block ...");
	    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		// set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver",
				"D:\\webdriverjars\\executables\\chromedriver_win32 (2)\\chromedriver.exe");

		// interface refobj = new implementedclass()
		driver = new ChromeDriver();
		// maximize the code
		driver.manage().window().maximize();
		// add implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("started executing the tearDownAfterClass() @BeforeAll block ...");
		if (driver != null) {
			driver.close();
		}
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("started executing the setup() @BeforeEach block ...");
		System.out.println("open the http://only-testing-blog.blogspot.com/2014/01/textbox.html ...");
		driver.get("http://only-testing-blog.blogspot.com/2014/01/textbox.html");
		wait.until(ExpectedConditions.titleContains("Only Testing: TextBox"));
		Assert.assertEquals("Only Testing: TextBox", driver.getTitle());
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("started executing the @AftreEach annotated block"); // clear the cookies
		driver.manage().deleteAllCookies();
	}

	@Test
	void testSimpleAlert() {
		System.out.println("started executing the testSimpleAlert()..");
	  //click on Show Me Alert button
		driver.findElement(By.xpath("//input[@value='Show Me Alert']")).click();
		//switch to simple alert dialogbox
		Alert alt=driver.switchTo().alert();
		System.out.println("simple alert textbox is:"+alt.getText());
		//click on ok button
		alt.accept();
		System.out.println("End of simple Alert");

	}

	@Test
	public void testPromptDialog() {
		System.out.println("started executing the testPromptDialog()..");
		//click on show me prompt button
		driver.findElement(By.xpath("//button[text()='Show Me Prompt']")).click();
		//switch to prompt dialog
		Alert prmpt = driver.switchTo().alert();
		System.out.println("prompt text is:" +prmpt.getText());
		//type the value in editbox
		prmpt.sendKeys("selenium");
		//click on ok button
		prmpt.accept();
		
		//click on show me prompt button
		driver.findElement(By.xpath("//button[text()='Show Me Prompt']")).click();
				//switch to prompt dialog
		Alert prmpt1 = driver.switchTo().alert();
		//click on cancel button
		prmpt1.dismiss();
		
		System.out.println("End of promptDialog test");
	}

	@Test
	public void testConfirmation() {
		System.out.println("started executing the testConfirmation()..");
		//click on Show Me Confirmation button
		driver.findElement(By.xpath("//button[text()='Show Me Confirmation']")).click();
		//switch to confirmation dialog
		Alert cnf = driver.switchTo().alert();
		System.out.println("confirmation message text is:" +cnf.getText());
		
		//click on ok button
		cnf.accept();
		
		//fetch the confirmation button message
		WebElement cnfMsg=driver.findElement(By.id("demo"));
		System.out.println("confirmaiton ok button msg:"+cnfMsg.getText());
		
		//click on Show Me Confirmation button
		driver.findElement(By.xpath("//button[text()='Show Me Confirmation']")).click();
				//switch to Confirmation dialog
		Alert cnf1 = driver.switchTo().alert();
		//click on cancel button
		cnf1.dismiss();
		System.out.println("confirmaiton cancel button msg:"+cnfMsg.getText());
		System.out.println("End of promptDialog test");
	}
	
}
