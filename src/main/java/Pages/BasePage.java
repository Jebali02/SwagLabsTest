package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class BasePage {
	
	static public WebDriver driver = null;
	static Properties prop = new Properties(); 
	public static Logger logger=LogManager.getLogger(BasePage.class);

	
	
	@Parameters({"browserName"})
	@BeforeTest
	public void setUp(String browserName) {
		logger.info("Starting test - setup");
		logger.info("Test runned on :" + browserName);
		
		String projectPath = System.getProperty("user.dir");
		switch (browserName) {
			case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath +readPropertiesFile("SourceChrome"));
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments("--headless");
			driver = new ChromeDriver(); 
			break;
			case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath +readPropertiesFile("SourceFireFox"));
			//FirefoxOptions optionsfirefox = new FirefoxOptions();
			//optionsfirefox.addArguments("--headless");
			driver = new FirefoxDriver(); 
			break;
			case "internetExplorer":
			System.setProperty("webdriver.ie.driver", projectPath +readPropertiesFile("SourceIe"));
			driver = new InternetExplorerDriver(); 
			break;

			default:
			System.setProperty("webdriver.chrome.driver", projectPath +readPropertiesFile("SourceChrome"));
			driver = new ChromeDriver();
			break;
		}
		
		driver.get(readPropertiesFile("URL"));

	}
	
	
	public static String readPropertiesFile(String property)  {
		
		String param = null;
		try {
			//renvoi le Workspace
			String projectPath =System.getProperty("user.dir");
			InputStream input = new FileInputStream(projectPath+"/src/test/resources/Config/ConfigProperties");
			prop.load(input);
		    param=prop.getProperty(property);   
		} catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		}
		return param;

	}
	
	
	public void waitVisiblityOfElement(By element){
		logger.debug("waiting visiblity for " + element);
		new WebDriverWait(driver, Duration.ofSeconds(10)).
		until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	public void waitClickableElement(By element){
		logger.debug("waiting visiblity for " + element);
		new WebDriverWait(driver, Duration.ofSeconds(10)).
		until(ExpectedConditions.elementToBeClickable(element));
	}
	public void ClickOnElement(By element)
	{
		waitClickableElement(element);
		logger.debug("Click on " + element);
		driver.findElement(element).click();	
	}
	public void SendKey(String txt, By element)
	{
		waitVisiblityOfElement(element);
		logger.debug("Send key to the " + element);
		driver.findElement(element).sendKeys(txt);
	}
	public String getText(By element){
		waitVisiblityOfElement(element);
		logger.debug("The text getted form " + element + "is " + driver.findElement(element).getText());
		return driver.findElement(element).getText();
	}
	
	
	

	
	@AfterTest
	public void teadown() {
		System.out.println("teadown");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
