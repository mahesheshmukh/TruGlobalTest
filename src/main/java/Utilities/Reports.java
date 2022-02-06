package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClass.MainTestClass;

public class Reports extends MainTestClass {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static String reportPath = currentDir + "\\Reports\\" + timeStamp +"_WebReport\\"+ "Report.html";

	//Start reports
	public static void startReport()
	{
		htmlReporter = new ExtentHtmlReporter(reportPath);

		//initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		//To add system or environment info by using the setSystemInfo method.
		extent.setSystemInfo("URL", "https://www.amazon.in/");
		extent.setSystemInfo("Project Name", "Amazon");
		extent.setSystemInfo("Automation", "Web");

		//configuration items to change the look and feel
		//add content, manage tests etc
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	//Set info icon
	public static void infoTest(String caseNo, String Description) throws Exception
	{
		String testResult = "Case No : " + caseNo + " &nbsp; <br /> &nbsp; Description : " + Description.substring(0, Math.min(Description.length(), 60)) + "...";
		test.info(MarkupHelper.createLabel(testResult, ExtentColor.BLUE));
	}

	//Set test case status pass
	public static void passTest(String object) throws Exception
	{
		test.pass(object);
	}

	//Set test case status fail and attach screenshots
	public static void failTest(String object) throws Exception
	{
		String screenshotPath = getScreenshot();
		test.fail("Failed to " + object , MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
		Thread.sleep(2000);
	}
	
	public static void passMobTest(String object) throws Exception
	{
		test.pass(object);
	}

	//Set test case status fail and attach screenshots
	public static void failMobTest(String object) throws Exception
	{
		test.fail("Failed to " + object);
	}

	public static String getScreenshot() throws IOException {
		String scrBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		File file = OutputType.FILE.convertFromBase64Png(scrBase64);
		byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		return new String(encoded, StandardCharsets.US_ASCII);
	}
}
