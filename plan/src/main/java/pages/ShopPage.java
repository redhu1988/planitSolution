package pages;

import common.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopPage extends BaseClass {

WebDriver driver;

	public ShopPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath = "//li[@id='product-6']/div/p/a")
	WebElement funnyCowItem;
	public WebElement getFunnyCowLink() {
		return funnyCowItem;
	}

	@FindBy(xpath = "//li[@id='product-4']/div/p/a")
	WebElement fluffyBunnyItem;
	public WebElement getFluffyBunnyLink() {
		return fluffyBunnyItem;
	}

	@FindBy(xpath = "//li[@id='nav-cart']/a")
	WebElement cartMenu;
	public WebElement getCartMenuBtn() {
		return cartMenu;
	}

}
