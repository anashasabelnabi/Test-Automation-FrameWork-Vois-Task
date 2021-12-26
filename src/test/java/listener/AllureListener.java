package listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import tests.TestBase;

public class AllureListener extends TestBase implements ITestListener{

	/**
	 * Return Test Method Name
	 *
	 * @param iTestResult         the current instance of ITestResult
	 */
	private static String getTestMethodName (ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	/**
	 * Take ScreenShot and Return it AS bytes to send it as attachment in the report
	 *
	 * @param driver         the current instance of Selenium webdriver
 
	 */
	@Attachment(value="Take Screen Shot",type="image/png")
	public byte[] saveScreenShot (WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	/**
	 *  Return Logs 
	 * @param message          String that Contain The Logs
 
	 */
	@Attachment(value="Current Method Log",type="text/plain")
	public static String saveTextLogs(String message) {
		return message;
	}
	
	/**
	 * Take Screen Shot On Failure
	 * @param iTestResult         the current instance of ITestResult
 
	 */
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I'm On Test Failure "+getTestMethodName(iTestResult)+" Failed");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((TestBase) testClass).getDriver() ;
		if(driver instanceof WebDriver) {
			saveScreenShot(driver);
		}
		saveTextLogs(getTestMethodName(iTestResult)+" Failed and Screenshot taken!!");		
	}
}
