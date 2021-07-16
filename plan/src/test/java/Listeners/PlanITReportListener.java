package Listeners;

import common.ConfigurationManager;
import common.DriverAbstraction;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class PlanITReportListener implements ITestListener{

    WebDriver driver;
    ConfigurationManager configurationManager;
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        driver= DriverAbstraction.getDriverInstance();
        configurationManager=new ConfigurationManager();

        ITestListener.super.onTestFailure(result);
        try {
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(System.getProperty("user.dir")
                    +"//"+configurationManager.getScreenShotsDirectory()+"//"+File.separator +
                    result.getName()+"_"+result.getEndMillis()+".png"));
            System.out.println("*******Screenshot captured********");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
