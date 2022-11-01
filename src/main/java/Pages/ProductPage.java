package Pages;

import org.openqa.selenium.By;

public class ProductPage extends BasePage{
	
	By Product = By.id("add-to-cart-sauce-labs-backpack");
	By productsITEM=By.xpath("//div[@class='inventory_item_name']");
	By BasketBTN = By.xpath("//a[@class='shopping_cart_link']");
	By PageName=By.xpath("//span[@class='title']");
	By MenuBTN=By.id("react-burger-menu-btn");
	By LogOutBTN = By.id("logout_sidebar_link");
	utils utils=new utils();
	

	public void ClickProduct(String productName) {
	  Boolean productFound=false;
	  for(int i=0;i<driver.findElements(productsITEM).size();i++)
	  {
		  logger.info("Product name :"+ driver.findElements(productsITEM).get(i).getText());
		  if(driver.findElements(productsITEM).get(i).getText().equalsIgnoreCase(productName))
		  {
			 productFound=true;
			 driver.findElements(productsITEM).get(i).click();
			 break; 
		  }
	  }
	  if(productFound==false)
	  {
		System.out.println("produit non existant dans la liste");
	  }
	}
	public boolean isProductPageDisplayed()
	{
	   return getText(PageName).equalsIgnoreCase("Products");
	}
	public void ClickOnBasket() {
		ClickOnElement(BasketBTN);
	}
	public void ClickMenuBTN () {
		ClickOnElement(MenuBTN);
	}
	
	public void ClickLogoutBTN () {
		ClickOnElement(LogOutBTN);
	}
}
