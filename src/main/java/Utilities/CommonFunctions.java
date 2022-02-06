package Utilities;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import BaseClass.MainTestClass;

public class CommonFunctions extends MainTestClass{

	//Take a screenshots if test fails
	public static String getScreenshot(WebDriver driver, String path) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = path+"_Fail.png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination; 
	}

	//Create directory files for reports
	public static void CreateDirectory(String DirectoryName)
	{
		//project directory
		String  dir = DirectoryName;
		
		//create a directory in the path
		File file = new File(dir);

		if (!file.exists()) {
			file.mkdir();
		} else {
			System.out.println("Directory is already exist!");
		}
	}
}
