package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class GoogleSearchPage extends PageBase{

	// Constructor
	public GoogleSearchPage(WebDriver driver) {
		super(driver);
	}
	/////////////////////////////Element Locators///////////////////////
	private By serachInput = By.name("q");
	
	//////////////////////////////////Actions////////////////////////////
	
	/**
	 * Type in Google Search Field Then Click Enter To Perform The Search
	 *
	 * @param driver         the current instance of Selenium webdriver
	 * @param Query          the target text that needs to be typed into Search Field
	 */
	@Step("Type Search Query In Google Search Field")
	public void typeInSerachInput(WebDriver driver ,String Query) {
		type(driver,serachInput,Query);
		enterKeys(driver,serachInput, Keys.ENTER);
	}

}
