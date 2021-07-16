package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseClass {
    WebDriver driver;
    WebDriverWait webDriverWait;
    static final int TIMEOUT = 20;
    static final int POLLINGTIME = 100;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, TIMEOUT, POLLINGTIME);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);

    }

    protected WebElement waitForElementToAppear(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    protected void waitForElementToDisappear(WebElement element) {
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    protected void doubleClickElement(WebElement element)
    {
       WebElement elemnt= waitForElementToAppear(element);
      Actions actions =new Actions(driver);
      actions.moveToElement(elemnt).doubleClick(elemnt).perform();
    }

    protected void clickElement(WebElement element) {
        waitForElementToAppear(element).click();
    }

    protected void clickElementAndWaitForPageLoad(WebElement element) {
        element.click();
        waitForJSandJQueryToLoad();
    }

    public boolean waitForJSandJQueryToLoad() {

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return webDriverWait.until(jQueryLoad) && webDriverWait.until(jsLoad);
    }

}
