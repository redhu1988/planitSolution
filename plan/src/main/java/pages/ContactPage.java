package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
WebDriver driver;
	
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//a[text() ='Submit']" )
	WebElement submitBtn;
	
	public WebElement returnSubmitBtn() {
		return submitBtn;
	}
	
	@FindBy(xpath = "//*[@id='header-message']/div")
	WebElement banner;
	
	public WebElement returnBanner() {
		return banner;
	}
	
	@FindBy(xpath = "//*[@id='forename-err']")
	WebElement foreNameErr;
	
	public WebElement returnForeNameErr() {
		return foreNameErr;
	}
	
	@FindBy(xpath = "//*[@id='email-err']")
	WebElement emailErr;
	
	public WebElement returnEmailErr() {
		return emailErr;
	}
	
	@FindBy(xpath = "//*[@id='message-err']")
	WebElement messsageError;
	
	public WebElement returnMessageErr() {
		return messsageError;
	}
	
	@FindBy(name = "forename")
	WebElement foreName_txt;
	
	public WebElement returnForenameTxt() {
		return foreName_txt;
	}
	
	@FindBy(name = "email")
	WebElement email_txt;
	
	public WebElement returnEmailTxt() {
		return email_txt;
	}
	
	@FindBy(name = "message")
	WebElement message_txt;
	
	public WebElement returnMessageTxt() {
		return message_txt;
	}
}
