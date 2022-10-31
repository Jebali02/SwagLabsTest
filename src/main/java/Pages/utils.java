package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class utils extends BasePage{

	public void Scroll()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(400,350)");
        
	}
	public void ScrollToElement(By element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 //Find element by link text and store in variable "Element"        		
        WebElement WebElement = driver.findElement(element);

        //This will scroll the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", WebElement);
        
	}
}
