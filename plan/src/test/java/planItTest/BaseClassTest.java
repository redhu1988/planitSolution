package planItTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

public class BaseClassTest {
	WebDriver driver;
	
	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ani_b\\Documents\\GitHub\\planitSolution\\plan\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public String getData(String sheetName, int rowNum, int colNum) throws IOException {
		String inputFilePath = "C:\\Users\\ani_b\\Documents\\GitHub\\planitSolution\\plan\\PlanItSolution.xlsx";
		FileInputStream fs_in = new FileInputStream(inputFilePath);
		XSSFWorkbook workBook = new XSSFWorkbook(fs_in);
		XSSFSheet sheet = workBook.getSheet(sheetName);
		XSSFRow row = sheet.getRow(rowNum);
		
		return row.getCell(colNum).getStringCellValue();
	}
	
	
	
}
