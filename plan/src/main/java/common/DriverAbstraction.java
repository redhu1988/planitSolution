package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverAbstraction {
   static WebDriver driver;
    public static WebDriver getDriverInstance(){
        return driver;
    }

    public static WebDriver getDriverInstance(String browserName) {

        switch (browserName) {
            case "internetExplorer":
            case "firefox":
            case "chrome":
            default:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
                driver = new ChromeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
