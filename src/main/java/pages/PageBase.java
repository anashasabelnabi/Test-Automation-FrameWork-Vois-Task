package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class PageBase {

	protected WebDriver driver ;
	public static  JavascriptExecutor jse ; 

	// Constructor
	public PageBase(WebDriver driver) {
		this.driver = driver ;
	}

	/**
	 * Checks if there is any text in an element, clears it, then types the required
	 * string into the target element.
	 *
	 * @param driver         the current instance of Selenium webdriver
	 * @param elementLocator the locator of the webElement under test (By xpath, id,
	 *                       selector, name ...etc)
	 * @param value           the target text that needs to be typed into the target
	 *                       webElement
	 */

	protected static void type(WebDriver driver,By elementLocator ,String value) {
		driver.findElement(elementLocator).clear();
		driver.findElement(elementLocator).sendKeys(value);
	}
	/**
	 * Enter Keys Like (Enter,tap,space,..etc)
	 *
	 * @param driver         the current instance of Selenium webdriver
	 * @param elementLocator the locator of the webElement under test (By xpath, id,
	 *                       selector, name ...etc)
	 * @param type           the type of the key that should be performed 
	 */

	protected static void enterKeys(WebDriver driver,By elementLocator ,Keys type) {
		driver.findElement(elementLocator).sendKeys(type);
	}
	/**
	 * Clicks on a certain element using Selenium WebDriver, or JavaScript
	 *
	 * @param driver         the current instance of Selenium webdriver
	 * @param elementLocator the locator of the webElement under test (By xpath, id,
	 *                       selector, name ...etc)
	 */
	protected static void click(WebDriver driver,By elementLocator) {
		driver.findElement(elementLocator).click();
	}
	/**
	 * scroll to the end of the page using instance of JavaScriptExecutor
	 **/
	protected static void scrollDown() {
		jse.executeScript("scrollBy(0,2500)");
	}
}
