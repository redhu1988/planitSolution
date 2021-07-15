package common;

import org.openqa.selenium.WebDriver;
import pages.ContactPage;
import pages.HomePage;
import pages.ShopPage;

public class ObjectRepository {
    WebDriver driver;
    HomePage homePage;
    ContactPage contactPage;
    ShopPage shopPage;

    public ObjectRepository(WebDriver driver){
        this.driver=driver;
    }
    public HomePage getHomePageInstance(){
        if(homePage==null){
            homePage=new HomePage(driver);
    }
        return homePage;
    }
    public ContactPage getContactPageInstance(){
        if(contactPage==null){
            contactPage=new ContactPage(driver);
        }
        return contactPage;
    }
    public ShopPage getShopPageInstance(){
        if(shopPage==null){
            shopPage=new ShopPage(driver);
        }
        return shopPage;
    }
}
