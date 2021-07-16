package pages;

import common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CartPage extends BaseClass {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//form[@name='form']/table")
    WebElement cartTable;

    public WebElement getCartTable() {
        return waitForElementToAppear(cartTable);
    }

    public Boolean validateQuantityForItemInCart(String itemName, String quantity) {
        List<WebElement> cartItems = getCartTable().findElements(By.xpath("//tbody/tr"));
        for (int row = 1; row <= cartItems.stream().count(); row++) {
           String nameOfItem= cartTable.findElement(By.xpath("//tbody/tr["+row+"]/td[1]")).getText();
            if (nameOfItem.contains(itemName)) {
                return cartTable.findElement(By.xpath("//tbody/tr["+row+"]/td[3]/input")).getAttribute("value").contains(quantity);
            }
        }
        return false;
    }


}
