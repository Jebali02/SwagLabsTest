package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class BasePage {

	static public WebDriver driver = null;
	static Properties prop = new Properties(); 
	public static String browser=System.getProperty("browser");
	public static String ENV=System.getProperty("env");
	public static Logger logger=LogManager.getLogger(BasePage.class);

	
	@BeforeClass
	public void setUp() {
		logger.info("Starting test - setup");
		logger.info("Test runned on :" + browser);
		logger.info("Test runned on the environnement  :" + ENV);
		String projectPath = System.getProperty("user.dir");
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath +readPropertiesFile("SourceChrome"));
			ChromeOptions optionsChrome = new ChromeOptions();
			optionsChrome.addArguments("--headless");
			driver = new ChromeDriver(optionsChrome); 
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath +readPropertiesFile("SourceFireFox"));
			FirefoxOptions optionsfirefox = new FirefoxOptions();
			optionsfirefox.addArguments("--headless");
			driver = new FirefoxDriver(optionsfirefox); 
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", projectPath +readPropertiesFile("SourceEdge"));
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--headless","--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
			driver = new EdgeDriver(edgeOptions); 
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
			InputStream input = null;
			switch (ENV) {
			case "dev":
				input=new FileInputStream(projectPath+"/src/test/resources/Config/ConfigProperties-dev");
				break;
			case "preprod":
				input=new FileInputStream(projectPath+"/src/test/resources/Config/ConfigProperties-preprod");
				break;
			case "prod":
				input=new FileInputStream(projectPath+"/src/test/resources/Config/ConfigProperties-prod");
				break;
			case "qa":
				input=new FileInputStream(projectPath+"/src/test/resources/Config/ConfigProperties-qa");
				break;
			default:
				input=new FileInputStream(projectPath+"/src/test/resources/Config/ConfigProperties-qa");
				break;
			}
			
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
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(txt);
	}
	public String getText(By element){
		waitVisiblityOfElement(element);
		logger.debug("The text getted form " + element + "is " + driver.findElement(element).getText());
		return driver.findElement(element).getText();
	}



	@AfterClass
	public void teadown()  {
		System.out.println("teadown");
		driver.quit();
	}
}
