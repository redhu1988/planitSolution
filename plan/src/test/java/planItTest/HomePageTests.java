package planItTest;

import java.io.IOException;

import common.ConfigurationManager;
import common.ObjectRepository;
import common.TestDataOperations;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.CartPage;
import pages.ContactPage;
import pages.HomePage;
import pages.ShopPage;

import static org.testng.Assert.*;

public class HomePageTests extends BaseClassTest {
    ObjectRepository objectRepository;
    HomePage homePage;
    ConfigurationManager configurationManager;
    String testFilePath;

    @BeforeTest
    private void goToPlanItSln() {
        objectRepository = new ObjectRepository(driver);
        configurationManager = new ConfigurationManager();
        driver.get(configurationManager.getApplicationUrl());
    }

    @BeforeMethod
    private void goToHomePage() {
        objectRepository.getHomePageInstance().navigateToHomePage();
    }


    @Test(priority = 1)
    private void validateContactPage() throws IOException {
        objectRepository.getHomePageInstance().navigateToContactPage();
        ContactPage contactPage = objectRepository.getContactPageInstance();
        contactPage.returnSubmitBtn().click();

        assertEquals(contactPage.returnForeNameErr().getText(), "Forename is required");
        assertTrue(contactPage.returnForeNameErr().getText().equals("Forename is required"),
                "Forename - mandatory validation message is not displayed");

        assertTrue(contactPage.returnEmailErr().getText().equals("Email is required"),
                "Email - mandatory validation message is not displayed");

        assertTrue(contactPage.returnMessageErr().getText().equals("Message is required"),
                "Message - mandatory validation message is not displayed");

        assertTrue(contactPage.returnBanner().getText().equals("We welcome your feedback - but we won't get it unless you complete the form correctly."),
                "Banner message on click of Submit is not displayed");

        contactPage.fillMandatoryFieldsValidData();
        assertFalse(contactPage.checkIfElementPresent(contactPage.returnForeNameErr()));
        assertFalse(contactPage.checkIfElementPresent(contactPage.returnEmailErr()));
        assertFalse(contactPage.checkIfElementPresent(contactPage.returnMessageErr()));
    }

    @Test(priority = 2)
    private void testContactPageSuccessfulSubmission() throws Exception {
        objectRepository.getHomePageInstance().navigateToContactPage();
        ContactPage contactPage = objectRepository.getContactPageInstance();
        contactPage.fillMandatoryFieldsValidData();
        contactPage.returnSubmitBtn().click();
        assertTrue(contactPage.returnSubmissionMsg().getText().contains("Thanks PlanIt, we appreciate your feedback."));

    }

    @Test(priority = 3)
    private void testContactPageInvalidDataErrors() throws Exception {
        objectRepository.getHomePageInstance().navigateToContactPage();
        ContactPage contactPage = objectRepository.getContactPageInstance();
        contactPage.fillMandatoryFieldsInValidData();
        assertTrue(contactPage.returnEmailErr().getText().equals("Please enter a valid email"));
        assertTrue(contactPage.returnTelephoneError().getText().equals("Please enter a valid telephone number"));
    }

    @Test(priority = 4)
    private void addItemsToShoppingCart() {
        homePage = objectRepository.getHomePageInstance();
        homePage.getShopLinkBtn().click();
        ShopPage shopPage = objectRepository.getShopPageInstance();
        shopPage.addItemsToCart();
        shopPage.goToCart();

        CartPage cartPage = objectRepository.getCartPageInstance();
        Assert.assertTrue(cartPage.validateQuantityForItemInCart("Funny Cow", "2"));
        Assert.assertTrue(cartPage.validateQuantityForItemInCart("Fluffy Bunny", "1"));
    }

    @AfterTest
    private void closeBrowser(){
        driver.close();
    }


}
