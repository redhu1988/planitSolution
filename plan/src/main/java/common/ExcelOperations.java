package common;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelOperations {

	public static String getData(String sheetName, int rowNum, int colNum) throws IOException {
		String inputFilePath = System.getProperty("user.dir")+"\\PlanItSolution.xlsx";
		FileInputStream fs_in = new FileInputStream(inputFilePath);
		XSSFWorkbook workBook = new XSSFWorkbook(fs_in);
		XSSFSheet sheet = workBook.getSheet(sheetName);
		XSSFRow row = sheet.getRow(rowNum);

		return row.getCell(colNum).getStringCellValue();
	}
}
