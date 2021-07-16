package pages;

import common.BaseClass;
import common.ExcelOperations;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ContactPage extends BaseClass {

    WebDriver driver;

    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//a[text() ='Submit']")
    WebElement submitBtn;

    public WebElement returnSubmitBtn() {
        return submitBtn;
    }

    @FindBy(xpath = "//*[@id='header-message']/div")
    WebElement banner;

    public WebElement returnBanner() {
        return banner;
    }

    @FindBy(id = "forename-err")
    WebElement foreNameErr;

    public WebElement returnForeNameErr() {
        return foreNameErr;
    }

    @FindBy(id = "email-err")
    WebElement emailErr;

    public WebElement returnEmailErr() {
        return emailErr;
    }

    @FindBy(id = "message-err")
    WebElement messsageError;

    public WebElement returnMessageErr() {
        return messsageError;
    }

    @FindBy(id = "forename")
    WebElement foreName_txt;

    public WebElement returnForenameTxt() {
        return foreName_txt;
    }

    @FindBy(id = "email")
    WebElement email_txt;

    public WebElement returnEmailTxt() {
        return email_txt;
    }

    @FindBy(id = "message")
    WebElement message_txt;

    public WebElement returnMessageTxt() {
        return message_txt;
    }

    @FindBy(id = "telephone")
    WebElement telephone_txt;

    public WebElement returnTelephoneTxt() {
        return telephone_txt;
    }

    @FindBy(id = "telephone-err")
    WebElement telephone_err;

    public WebElement returnTelephoneError() {
        return telephone_err;
    }

    public boolean checkIfElementPresent(WebElement element) {
        return isElementPresent(element);
    }

    public void fillMandatoryFieldsValidData() throws IOException {
        returnForenameTxt().sendKeys(ExcelOperations.getData("Contact", 1, 0));
        returnEmailTxt().sendKeys(ExcelOperations.getData("Contact", 1, 2));
        returnMessageTxt().sendKeys(ExcelOperations.getData("Contact", 1, 4));
    }

    public void fillMandatoryFieldsInValidData() throws IOException {
        returnEmailTxt().sendKeys(ExcelOperations.getData("Contact", 2, 2));
        returnTelephoneTxt().sendKeys(ExcelOperations.getData("Contact", 2, 3));
    }
}
