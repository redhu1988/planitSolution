package pages;

import common.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(linkText = "Contact")
	WebElement contact_link;
	public WebElement getContactLink() {
		return contact_link;
	}

	@FindBy(xpath = "//li[@id='nav-shop']/a" )
	WebElement shopLinkBtn;
	public WebElement getShopLinkBtn() {
		return shopLinkBtn;
	}
}
