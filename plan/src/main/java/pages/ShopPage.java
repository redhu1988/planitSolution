package pages;

import common.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShopPage extends BaseClass {

    WebDriver driver;

    public ShopPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//h4[contains(.,'Funny Cow')]/following-sibling::p/a")
    WebElement funnyCowItem;

    public WebElement getFunnyCowLink() {
        return waitForElementToAppear(funnyCowItem);
    }

    @FindBy(xpath = "//h4[contains(.,'Fluffy Bunny')]/following-sibling::p/a")
    WebElement fluffyBunnyItem;

    public WebElement getFluffyBunnyLink() {
        return waitForElementToAppear(fluffyBunnyItem);
    }

    @FindBy(xpath = "//li[@id='nav-cart']/a")
    WebElement cartMenu;

    public WebElement getCartMenuBtn() {
        return cartMenu;
    }

    @FindBy(css = "div.products>ul>li")
    List<WebElement> itemList;


    public void addItemsToCart() {
        doubleClickElement(getFunnyCowLink());
        clickElement(getFluffyBunnyLink());
    }

    public void goToCart() {
        clickElementAndWaitForPageLoad(getCartMenuBtn());
    }

}
