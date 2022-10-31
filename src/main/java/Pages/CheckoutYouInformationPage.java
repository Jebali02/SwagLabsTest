package Pages;

import org.openqa.selenium.By;

public class CheckoutYouInformationPage extends BasePage{
	By FirstNameField= By.id("first-name");
	By LastNameField= By.id("last-name");
	By ZipCodeField= By.id("postal-code");
	By PageName = By.xpath("//span[@class='title']");
	By ContinueBTN = By.id("continue");
	
	public boolean IsChekoutYourInformationPageVisible(String pageName)
	{
	   return getText(PageName).equalsIgnoreCase(pageName);
	}
	public void AddFirstName (String firstname) {
		SendKey(firstname, FirstNameField);
	}
	public void AddLastName(String lastname) {
		SendKey(lastname, LastNameField);
	}
	public void AddZipCode (String zipcode) {
		SendKey(zipcode, ZipCodeField);
	}
	public void ClickContinue () {
		ClickOnElement(ContinueBTN);
	}
}
