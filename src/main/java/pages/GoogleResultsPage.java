package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class GoogleResultsPage extends PageBase {
	
	// Constructor
	public GoogleResultsPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}
	/////////////////////////////Element Locators///////////////////////

	private static By nextButtonLocator = By.id("pnnext");
	
	/**
	 * Return Page Number In The Pagination To Assert Which Page I Alrady in IT 
	 * 
	 *  @param driver		the current instance of Selenium webdriver
	 */
	public static String currentPageInPagination (WebDriver driver) {
		String pageNumber = driver.findElement(By.xpath("//td[@class='YyVfkd']")).getText();
		return pageNumber;
	}

	/**
	 * Return Next Button Locator 
	 */
	public static By nextButton() {
		return  nextButtonLocator;
	}
	/**
	 * Check That Results Stats Section Is Displayed Or Not Then Return True Or False
	 * @param driver         the current instance of Selenium webdriver
	 */
	public static boolean resultStats(WebDriver driver) {
		boolean isDisplayed = driver.findElement(By.id("result-stats")).isDisplayed();
		return isDisplayed;	
	}
	/**
	 * Return All The Results (ignore any maps, videos, or images) in List Of WebElement  
	 * @param driver         the current instance of Selenium webdriver
	 */
	public static int resultSectionPageTwo(WebDriver driver) {
		List<WebElement> resultSection = driver.findElements(By.xpath("//div[@class='g']"));
		int resultSectionPageTwo = resultSection.size();
		return resultSectionPageTwo ;
	}
	
	public static int resultSectionPageThree(WebDriver driver) {
		List<WebElement> resultSection = driver.findElements(By.xpath("//div[@class='g']"));
		int resultSectionPageThree = resultSection.size();
		return resultSectionPageThree ;
	}
	
//////////////////////////////////Actions////////////////////////////
	
	/**
	 * Click Next Button In The Pagination  
	 * @param driver         the current instance of Selenium webdriver
	 */
	@Step("Click Next Button")
	public void navigateThrowPagination(WebDriver driver) {
		click(driver, nextButtonLocator);
	}
	
	/**
	 * Scroll to End Of The Page To Click On Next Button 
	 * @param driver         the current instance of Selenium webdriver
	 */
	@Step("Scroll Down To Click Next Button")
	public void scrollToClickNext() {
		scrollDown();
	}

}
