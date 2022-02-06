package BaseClass;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Configuration;

import com.browserstack.local.Local;

import Utilities.CommonFunctions;
import Utilities.Reports;

public class MainTestClass {

	public static final String USERNAME = "mtestd_EwUhQM";
	public static final String AUTOMATE_KEY = "yqmTsTkWhxzRrfjxDP9q";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	public static RemoteWebDriver driver;
	public static String currentDir = System.getProperty("user.dir");
	public static String timeStamp = new SimpleDateFormat("yyMdHms").format(new Date());
	private Local l;

	@BeforeSuite(alwaysRun = true)
	public void openBrowser() throws InterruptedException{
		try
		{
			//Run locally	
			/*System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
			driver = new ChromeDriver();
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			System.out.println(caps.getVersion());
			Thread.sleep(1000);
			driver.manage().window().maximize();
			driver.get("https://www.amazon.in/");*/

			CommonFunctions.CreateDirectory(currentDir + "\\" + "Reports");
			CommonFunctions.CreateDirectory(currentDir + "\\Reports\\" + timeStamp +"_WebReport");
			Reports.startReport();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod(alwaysRun = true)
	@org.testng.annotations.Parameters(value = { "config", "environment" })
	public void openBrowserstack(String config_file, String environment) throws InterruptedException{
		try
		{
			JSONParser parser = new JSONParser();
			JSONObject config = (JSONObject) parser.parse(new FileReader(currentDir + "/src/test/resources/" + config_file));
			JSONObject envs = (JSONObject) config.get("environments");

			DesiredCapabilities capabilities = new DesiredCapabilities();

			Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
			Iterator it = envCapabilities.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
			}

			Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
			it = commonCapabilities.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				if (capabilities.getCapability(pair.getKey().toString()) == null) {
					capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
				}
			}

			String username = System.getenv("BROWSERSTACK_USERNAME");
			if (username == null) {
				username = (String) config.get("user");
			}

			String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
			if (accessKey == null) {
				accessKey = (String) config.get("key");
			}

			if (capabilities.getCapability("browserstack.local") != null
					&& capabilities.getCapability("browserstack.local") == "true") {
				l = new Local();
				Map<String, String> options = new HashMap<String, String>();
				options.put("key", accessKey);
				l.start(options);
			}
			capabilities.setCapability("os", "Windows");
			capabilities.setCapability("os_version", "11");
			capabilities.setCapability("browser_version", "latest"); 
			capabilities.setPlatform(Platform.ANY); 

			driver = new RemoteWebDriver(new URL(URL), capabilities);
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			System.out.println(caps.getVersion());
			driver.manage().window().maximize();
			driver.get("https://www.amazon.in/");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		if (l != null) {
			l.stop();
		}
	}
	
	@AfterSuite(alwaysRun = true)
	public void closeWebdriver() throws Exception
	{
		driver.quit();
		Reports.extent.flush();
	}

	//Selenium methods
	protected WebElement findWebElement(String locator) {
		WebElement webElement = null;
		if (!isBlankOrNull(locator)) {
			try {
				if (locator.startsWith("@id")) {
					webElement = driver.findElement(By.id(removeStart(locator, "@id=")));
				} else if (locator.startsWith("@name")) {
					webElement = driver.findElement(By.name(removeStart(locator, "@name=")));
				} else if (locator.startsWith("@linkText")) {
					webElement = driver.findElement(By.linkText(removeStart(locator, "@linkText=")));
				} else if (locator.startsWith("@partialLinkText")) {
					webElement = driver.findElement(By.partialLinkText(removeStart(locator, "@partialLinkText=")));
				} else if (locator.startsWith("@xpath")) {
					webElement = driver.findElement(By.xpath(removeStart(locator, "@xpath=")));
				} else if (locator.startsWith("@css")) {
					webElement = driver.findElement(By.cssSelector(removeStart(locator, "@css=")));
				} else if (locator.startsWith("@className")) {
					webElement = driver.findElement(By.className(removeStart(locator, "@className=")));
				}
			} catch (NoSuchElementException e) {
			} catch (RuntimeException e) {
				e.printStackTrace();
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'",
						webElement);
			}
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'", webElement);
		return webElement;
	}		

	public static boolean isBlankOrNull(String str) {
		return str == null;
	}

	public static String removeStart(String str, String remove) {
		String returnStr = "";
		if (isBlankOrNull(str) || isBlankOrNull(remove)) {
			returnStr = str;
		}
		if (str.startsWith(remove))
			returnStr = str.substring(remove.length());
		return returnStr;
	}

	public void sendKeys(String locator, String textValue, String object) throws Exception {
		try
		{
			WebElement webElement = null;
			if (!isBlankOrNull(locator)) {
				webElement = findWebElement(locator);
				webElement.clear();
				webElement.sendKeys(textValue);
				Reports.passTest(object);
			}	
		}
		catch(Exception e)
		{
			Reports.failTest(object);
		}
	}

	public void click(String locator, String object) throws Exception {
		try
		{
			WebElement webElement = findWebElement(locator);
			webElement.click();
			Reports.passTest(object);
		}
		catch(Exception e)
		{
			Reports.failTest(object);
		}
	}

	public boolean isVisible(String locator) throws InterruptedException, IOException {
		boolean result = true;
		try 
		{
			if (findWebElement(locator).isDisplayed() == true) 
			{
				result = true;
			}
			else 
			{
				result = false;
			}
		}
		catch (Exception e) {
			result = false;
		}
		return result;
	}

	public void verifyElement(String locator, String object) throws Exception {
		if (isVisible(locator)) 
		{
			Reports.passTest(object);
		} 
		else 
		{
			Reports.failTest(object);
		}
	}

	public void waitForElementToAppear(String locator, int sec) throws InterruptedException, IOException 
	{
		for (int i = 0; i < sec; i++) 
		{
			if (isVisible(locator))
			{
				Thread.sleep(500);
				break;
			}
			else
			{
				Thread.sleep(1000);
			}
		}
		Thread.sleep(1000);
	}



}
