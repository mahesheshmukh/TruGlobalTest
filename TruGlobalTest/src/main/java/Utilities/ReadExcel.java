package Utilities;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	//Read data from particular cell
	public String getDataFromCell(String xlFilePath,String sheetName,int row,int coloumn)
	{
		String a = null;
		try
		{
		FileInputStream fis = new FileInputStream(xlFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		XSSFCell cellval = sheet.getRow(row).getCell(coloumn);
		a =  cellval.toString();
		}catch (Exception e) {
		}
		return a;
	}
}
