package planItTest;

import java.io.IOException;

import common.ConfigurationManager;
import common.ObjectRepository;
import common.TestDataOperations;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.ContactPage;
import common.ExcelOperations;
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
        testFilePath = System.getProperty("user.dir") + "\\" + configurationManager.getTestDataFile();
        TestDataOperations.readTestData(testFilePath);
        driver.get(configurationManager.getApplicationUrl());
    }

    @Test
    private void navigateToContactPage() {
        homePage = objectRepository.getHomePageInstance();
        homePage.getContactLink().click();
    }


    @Test(dependsOnMethods = {"navigateToContactPage"}, priority = 1)
    private void validateContactPage() throws IOException {
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

    @Test(dependsOnMethods = {"navigateToContactPage"}, priority = 2)
    private void testContactPageSuccessfulSubmission() throws Exception {
        ContactPage contactPage = objectRepository.getContactPageInstance();
        contactPage.fillMandatoryFieldsValidData();
        contactPage.returnSubmitBtn().click();
        assertTrue(!contactPage.returnBanner().getText().equals("We welcome your feedback - but we won't get it unless you complete the form correctly."),
                "Banner message on click of Submit is not displayed");
        assertTrue(contactPage.returnBanner().getText().equals("We welcome your feedback - tell it how it is."));
    }

    @Test(dependsOnMethods = {"navigateToContactPage"}, priority = 3)
    private void testContactPageInvalidDataErrors() throws Exception {
        ContactPage contactPage = objectRepository.getContactPageInstance();
        contactPage.fillMandatoryFieldsInValidData();
        assertTrue(contactPage.returnEmailErr().getText().equals("Please enter a valid email"));
        assertTrue(contactPage.returnTelephoneError().getText().equals("Please enter a valid telephone number"));
    }

    @Test(priority = 4)
    private void addItemsToShoppingCart() {
        homePage = objectRepository.getHomePageInstance();
        ShopPage shopPage = objectRepository.getShopPageInstance();
        homePage.getShopLinkBtn().click();

        Actions actions = new Actions(driver);
        actions.doubleClick(shopPage.getFunnyCowLink()).perform();
        shopPage.getFluffyBunnyLink().click();
        shopPage.getCartMenuBtn().click();

        CartPage cartPage = objectRepository.getCartPageInstance();
        Assert.assertTrue(cartPage.validateQuantityForItemInCart("Funny Cow", "2"));
        Assert.assertTrue(cartPage.validateQuantityForItemInCart("Fluffy Bunny", "1"));
    }

    @AfterSuite
    private void tearDown() throws InterruptedException {
        driver.close();
        driver.quit();
    }


}
