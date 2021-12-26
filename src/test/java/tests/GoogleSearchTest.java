package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import data.JsonDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listener.AllureListener;
import pages.GoogleResultsPage;
import pages.GoogleSearchPage;

@Listeners({AllureListener.class})
@Epic("Google Search")
@Feature("Verify Count Of Search Results In The Pages")
public class GoogleSearchTest extends TestBase {
	GoogleSearchPage googleSearchPageObject ;
	GoogleResultsPage googleResultsPageObject ;
	int numberOfResultsInPageTwo ;
	int numberOfResultsInPageThree ;
	
	/**
	 * Type in Google Search Field The Required Query Coming From JsonReader
	 * Wait  to Search Results Page To Load 
	 * Assert The Search Result Page Is Loaded Successfully
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test(description = "User Can Search Using Google Search Field")
	@Severity(SeverityLevel.BLOCKER)
	@Description( "Test Description: Enter Search Query To Get Search Resul")
	@Story("User Can Search Google ")
	public void searchGoogle() throws FileNotFoundException, IOException, ParseException {
		//Take Instance Of WebDriverWait
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//Initialize Object From GoogleSearchPage 
		googleSearchPageObject = new GoogleSearchPage(driver);
		
		//Initialize Object From JsonDataReader To Use Data
		JsonDataReader jsonDataReaderObject = new JsonDataReader();
		jsonDataReaderObject.jsonReader();
		
		//Type into Search Field Using Query From Json File
		googleSearchPageObject.typeInSerachInput(driver,jsonDataReaderObject.searchQuery);
		
		//Wait Until Search Result Page To Load
		wait.until(ExpectedConditions.titleContains(jsonDataReaderObject.searchQuery));
		
		//Assert That Page Loaded Successfully And result Stats Displayed
		assertTrue(GoogleResultsPage.resultStats(driver));
	}
	
	/**
	 * In The Google Search Result Page Scroll Down To Click In Next Button
	 * Wait Until Next Button To Be Visible and Click on it
	 * In Second Page Count Number Of Search Result and store it in numberOfResultsInPageTwo 
	 * Then Scroll Down and wait Until Next Button To Be Visible and Click on it
	 * In The Third Page Count Number Of Search Result and store it in numberOfResultsInPageThree
	 * Assert That number of results in page two is equal to number of results in page three 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test(dependsOnMethods = "searchGoogle" ,description =  "User Can Scroll Down And Navigate Throw Pagination ")
	@Severity(SeverityLevel.NORMAL)
	@Description( "Verify That The Number Of Results in The Second Page Equal Number Of Result in The Third Page")
	@Story("Count The Number Of Results")
	public void countNumberOfResults() throws InterruptedException, FileNotFoundException, IOException, ParseException{
		//Take Instance Of WebDriverWait
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//Initialize Object From JsonDataReader To Use Data
		JsonDataReader jsonDataReaderObject = new JsonDataReader();
		jsonDataReaderObject.jsonReader();
		
		//Initialize Object From GooglResultsPage 
		googleResultsPageObject = new GoogleResultsPage(driver);
		//Wait Until Next Button Displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleResultsPage.nextButton()));
		//Scroll Down
		googleResultsPageObject.scrollToClickNext();
		//Click in Next Button
		googleResultsPageObject.navigateThrowPagination(driver);
		//Validate That Current Page is Page Number Two
		assertEquals(GoogleResultsPage.currentPageInPagination(driver),jsonDataReaderObject.pageNumberTwo);
		wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleResultsPage.nextButton()));
		//Get Number Of Search Results In Page Two
		numberOfResultsInPageTwo = GoogleResultsPage.resultSectionPageTwo(driver);
		//Print Number Of Search Results in page two
		System.out.println(numberOfResultsInPageTwo);
		//Scroll Down
		googleResultsPageObject.scrollToClickNext();
		//Wait Until Next Button Displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleResultsPage.nextButton()));
		//Click in Next Button
		googleResultsPageObject.navigateThrowPagination(driver);
		//Validate That Current Page is Page Number Three
		assertEquals(GoogleResultsPage.currentPageInPagination(driver), jsonDataReaderObject.pageNumberThree);
		wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleResultsPage.nextButton()));
		//Get Number Of Search Results In Page Three
		numberOfResultsInPageThree = GoogleResultsPage.resultSectionPageThree(driver);
		//Print Number Of Search Results in page two
		System.out.println(numberOfResultsInPageThree);
		//Validate That Number Of Results in Page Two Equal Number Of Results In Page Three
		assertEquals(numberOfResultsInPageTwo, numberOfResultsInPageThree);
	}
}
