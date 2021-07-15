package planItTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ContactPage;
import pages.HomePage;

public class HomePageTests extends BaseClassTest{

	@BeforeTest
	private void goToPlanItSln() {
		driver.get("http://jupiter.cloud.planittesting.com");
	}
	
	@Test
	private void navigateToContactPage() throws IOException {
		HomePage homePage = new HomePage(driver);
		ContactPage conctactPage = new ContactPage(driver);
		homePage.getContactLink().click();
		
		conctactPage.returnSubmitBtn().click();
		
		assertEquals(conctactPage.returnForeNameErr().getText(), "Forename is required");
		assertTrue(conctactPage.returnForeNameErr().getText().equals("Forename is required") ,
				"Forename - mandatory validation message is not displayed");
		
		assertTrue(conctactPage.returnEmailErr().getText().equals("Email is required") ,
				"Email - mandatory validation message is not displayed");
		
		assertTrue(conctactPage.returnMessageErr().getText().equals("Message is required") ,
				"Message - mandatory validation message is not displayed");
		
		assertTrue(conctactPage.returnBanner().getText().equals("We welcome your feedback - but we won't get it unless you complete the form correctly.") ,
				"Banner message on click of Submit is not displayed");
		
		
		conctactPage.returnForenameTxt().sendKeys(getData("Contact",1,0));
		conctactPage.returnEmailTxt().sendKeys(getData("Contact",1,2));
		conctactPage.returnMessageTxt().sendKeys(getData("Contact",1,4));
		
		assertTrue(!conctactPage.returnBanner().getText().equals("We welcome your feedback - but we won't get it unless you complete the form correctly.") ,
				"Banner message on click of Submit is not displayed");
		assertTrue(conctactPage.returnBanner().getText().equals("We welcome your feedback - tell it how it is."));
	}

	
	@AfterSuite
	private void tearDown() throws InterruptedException {
		driver.close();
	}
	
	
	
	
}
