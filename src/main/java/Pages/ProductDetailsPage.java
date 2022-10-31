package Pages;

import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage{
	
	By AddToCartBTN = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']");
	By ShoppingCartBTN = By.xpath("//a[@class='shopping_cart_link']");
	
	public void ClickAddToCartBTN () {
		
		ClickOnElement(AddToCartBTN);
	}	

	public void ClickShoppingCartBTN () {
		
		ClickOnElement(ShoppingCartBTN);
	}
}
