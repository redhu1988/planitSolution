package planItTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import common.ObjectRepository;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ContactPage;
import common.ExcelOperations;
import pages.HomePage;
import pages.ShopPage;

public class HomePageTests extends BaseClassTest{
ObjectRepository objectRepository;
HomePage homePage;
	@BeforeTest
	private void goToPlanItSln() {
		driver.get("http://jupiter.cloud.planittesting.com");
		objectRepository = new ObjectRepository(driver);
	}

	@Test
	private void navigateToContactPage(){
		homePage = objectRepository.getHomePageInstance();
		homePage.getContactLink().click();
	}


	@Test(dependsOnMethods = {"navigateToContactPage"},priority = 1)
	private void validateMandatoryFieldsContactPage() throws IOException {
		ContactPage contactPage = objectRepository.getContactPageInstance();
		contactPage.returnSubmitBtn().click();
		
		assertEquals(contactPage.returnForeNameErr().getText(), "Forename is required");
		assertTrue(contactPage.returnForeNameErr().getText().equals("Forename is required") ,
				"Forename - mandatory validation message is not displayed");
		
		assertTrue(contactPage.returnEmailErr().getText().equals("Email is required") ,
				"Email - mandatory validation message is not displayed");
		
		assertTrue(contactPage.returnMessageErr().getText().equals("Message is required") ,
				"Message - mandatory validation message is not displayed");
		
		assertTrue(contactPage.returnBanner().getText().equals("We welcome your feedback - but we won't get it unless you complete the form correctly.") ,
				"Banner message on click of Submit is not displayed");
	}

	@Test(dependsOnMethods = {"navigateToContactPage"},priority = 2)
	private void fillMandatoryDetailsContactPage() throws IOException {
		ContactPage contactPage = objectRepository.getContactPageInstance();
		contactPage.returnForenameTxt().sendKeys(ExcelOperations.getData("Contact",1,0));
		contactPage.returnEmailTxt().sendKeys(ExcelOperations.getData("Contact",1,2));
		contactPage.returnMessageTxt().sendKeys(ExcelOperations.getData("Contact",1,4));

		assertTrue(!contactPage.returnBanner().getText().equals("We welcome your feedback - but we won't get it unless you complete the form correctly.") ,
				"Banner message on click of Submit is not displayed");
		assertTrue(contactPage.returnBanner().getText().equals("We welcome your feedback - tell it how it is."));
	}

	@Test(priority = 4)
	private void addItemsToShoppingCart(){
		homePage = objectRepository.getHomePageInstance();
		ShopPage shopPage =objectRepository.getShopPageInstance();
		homePage.getShopLinkBtn().click();

	}
	@AfterSuite
	private void tearDown() throws InterruptedException {
		driver.close();
	}
	
	
	
	
}
