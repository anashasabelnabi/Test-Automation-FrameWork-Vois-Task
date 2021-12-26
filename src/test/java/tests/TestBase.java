package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import data.LoadProperties;
public class TestBase {

	public static WebDriver driver;
	public static ThreadLocal<WebDriver> threadLocalDriver= new ThreadLocal<WebDriver>();
	String targetBrowser = LoadProperties.executionType.getProperty("targetBrowserName");
	String googleUrl = LoadProperties.paths.getProperty("googleUrl");
	String CHROMEPATH = LoadProperties.paths.getProperty("chromeDriverPath");
	String FIREFOXPATH = LoadProperties.paths.getProperty("firefoxDriverPath");
	String EDGEPATH = LoadProperties.paths.getProperty("edgeDriverPath");
	String REMOTEPATH = LoadProperties.paths.getProperty("remoteUrl");

	/**
	 * Start Driver Based On Execution.Properties File (chrome or firefox or edge )
	 * then open the driver and navigate to required URL
	 * 
	 */
	@BeforeSuite
	public WebDriver initializeDriver()  {
		if (targetBrowser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+CHROMEPATH);
			driver = new ChromeDriver(); 
		}

		else if(targetBrowser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+FIREFOXPATH);
			driver = new FirefoxDriver(); 
		}

		else if (targetBrowser.equalsIgnoreCase("edge")) 
		{
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+EDGEPATH);
			driver = new EdgeDriver(); 
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
		driver.navigate().to(googleUrl);
		threadLocalDriver.set(driver);
		return getDriver() ;
	}
	public synchronized WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	/**
	 * Close The Driver With All Sessions
	 */
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}
}
