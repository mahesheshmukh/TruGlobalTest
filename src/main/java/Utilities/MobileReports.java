package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClass.MainTestClass;

public class MobileReports extends MainTestClass {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	//Start reports
	public static void startReport(String reportPath)
	{
		htmlReporter = new ExtentHtmlReporter(reportPath + "Report.html");

		//initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		//To add system or environment info by using the setSystemInfo method.
		extent.setSystemInfo("URL", "https://www.amazon.in/");
		extent.setSystemInfo("Project Name", "Amazon");
		extent.setSystemInfo("Automation", "Mobile");

		//configuration items to change the look and feel
		//add content, manage tests etc
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Regression Suite");
		htmlReporter.config().setReportName("Amazon Mobile Automation");
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

	public static void passMobTest(String object) throws Exception
	{
		test.pass(object);
	}

	//Set test case status fail and attach screenshots
	public static void failMobTest(String object) throws Exception
	{
		test.fail("Failed to " + object);
	}
}
