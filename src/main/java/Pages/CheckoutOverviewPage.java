package Pages;

import org.openqa.selenium.By;

public class CheckoutOverviewPage extends BasePage{

	By TitlePage = By.xpath("//span[@class='title']");
	By PriceOfOrder = By.xpath("//div[@class='summary_total_label']");
	By FinishBTN = By.xpath("//button[@class='btn btn_action btn_medium cart_button']");

	public boolean IsCheckoutOverviewPageVisible(String Title)
	{
	   return getText(TitlePage).equalsIgnoreCase(Title);
	}
	public boolean CheckPriceOfOrder(String Price)
	{
	   return getText(PriceOfOrder).contains(Price);
	}
	public void ClickFinishBTN() {
		ClickOnElement(FinishBTN);
	}
}
