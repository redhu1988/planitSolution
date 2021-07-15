package planItTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	WebDriver driver;
	WebDriverWait wdWait;
	static final int TIMEOUT = 5;
	static final int POLLINGTIME = 100;
	
	public BaseClass(WebDriver driver) {
		this.driver = driver;
		wdWait = new WebDriverWait(driver, TIMEOUT, POLLINGTIME);
		PageFactory.initElements(driver, this);
		
	}
}
