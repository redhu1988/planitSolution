package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	WebDriver driver;
	WebDriverWait webDriverWait;
	static final int TIMEOUT = 5;
	static final int POLLINGTIME = 100;
	
	public BaseClass(WebDriver driver) {
		this.driver = driver;
		webDriverWait = new WebDriverWait(driver, TIMEOUT, POLLINGTIME);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
		
	}
	protected void waitForElementToAppear(By locator) {
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	protected void waitForElementToDisappear(By locator) {
		webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	protected void waitForTextToDisappear(By locator, String text) {
		webDriverWait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
	}
}