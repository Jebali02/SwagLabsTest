package Pages;

import org.openqa.selenium.By;

public class YourCartPage extends BasePage {
	
	By ProductAddToBasket= By.xpath("//div[@class='inventory_item_name']");
	By ChekoutBTN= By.xpath("//button[@class='btn btn_action btn_medium checkout_button']");
	

	public boolean isProductAddToBasket(String ProductName)
	{
	   return getText(ProductAddToBasket).equalsIgnoreCase(ProductName);
	}
	public void ClickCheckoutBTN () {
		ClickOnElement(ChekoutBTN);
	}	
}
