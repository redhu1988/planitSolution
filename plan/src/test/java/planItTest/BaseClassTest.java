package planItTest;


import common.DriverAbstraction;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClassTest {
    WebDriver driver;

    @BeforeSuite
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverAbstraction.getDriverInstance(browser);
    }

    @AfterSuite
    private void tearDown() throws InterruptedException {
        driver.quit();
    }
}
