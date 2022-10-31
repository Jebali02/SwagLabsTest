package Pages;

import org.openqa.selenium.By;

public class CheckoutCompletePage extends BasePage {
	By ThankYouMessage = By.xpath("//h2[@class='complete-header']");
	By OrderDispatchedMessage = By.xpath("//div[@class='complete-text']");
	By MenuBTN = By.id("react-burger-menu-btn");
	By LogOutBTN = By.id("logout_sidebar_link");
	
	public boolean IsThankYouMessageVisible(String thankyoumessage)
	{
	   return getText(ThankYouMessage).equalsIgnoreCase(thankyoumessage);

	}
	public boolean IsOrderDispatchedMessageVisible(String orderdispatchedmessage)
	{
	   return getText(OrderDispatchedMessage).equalsIgnoreCase(orderdispatchedmessage);

	}
	public void ClickMenuBTN () {
		ClickOnElement(MenuBTN);
	}
	
	public void ClickLogoutBTN () {
		ClickOnElement(LogOutBTN);
	}
	}

