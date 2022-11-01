package Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.List;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.devtools.v104.page.Page;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.BasePage;
import Pages.CheckoutCompletePage;
import Pages.CheckoutOverviewPage;
import Pages.CheckoutYouInformationPage;
import Pages.ConnexionPage;
import Pages.ProductDetailsPage;
import Pages.ProductPage;
import Pages.YourCartPage;

public class PurchaseProductTest extends BasePage{

	ConnexionPage connexionPage =  new ConnexionPage();
	ProductPage productPage = new ProductPage();
	YourCartPage yourcartpage = new YourCartPage();
	ProductDetailsPage productdetailpage = new ProductDetailsPage();
	CheckoutYouInformationPage checkoutinformationpage = new CheckoutYouInformationPage();
	CheckoutOverviewPage chekoutoverviewpage = new CheckoutOverviewPage();
	CheckoutCompletePage chekoutcompletepage =  new CheckoutCompletePage();
	SoftAssert softAssert = new SoftAssert();


	@Test 
	public void VerifyBasketContent(){
		logger.info("I am inside VerifyBasketContent test");

		connexionPage.AddUserName(readPropertiesFile("UserName"));
		connexionPage.AddPassword(readPropertiesFile("Password"));
		connexionPage.ClickLogin();
		//select product 
		productPage.ClickProduct(readPropertiesFile("ProductName"));

		//add product to basket 
		productdetailpage.ClickAddToCartBTN();


		//detail product page
		productdetailpage.ClickShoppingCartBTN();
		assertTrue(yourcartpage.isProductAddToBasket(readPropertiesFile("ProductName")));
	}

	@Test (dependsOnMethods = "VerifyBasketContent")
	public void PurchaseProduct(){
		logger.info("I am inside PurchaseProduct test");

		ArrayList<Boolean> expected = new ArrayList<>();
		ArrayList<Boolean> actual = new ArrayList<>();

		expected.add(true);
		expected.add(true);
		expected.add(true);
		expected.add(true);
		expected.add(true);


		//click checkoutBtn
		yourcartpage.ClickCheckoutBTN();
		actual.add(checkoutinformationpage.IsChekoutYourInformationPageVisible(readPropertiesFile("PageName")));

		//add information
		checkoutinformationpage.AddFirstName(readPropertiesFile("FirstName"));
		checkoutinformationpage.AddLastName(readPropertiesFile("LastName"));
		checkoutinformationpage.AddZipCode(readPropertiesFile("ZipCode"));

		// click continue
		checkoutinformationpage.ClickContinue();

		//assertion to check if we are redirected to the checkout overview page
		actual.add(chekoutoverviewpage.IsCheckoutOverviewPageVisible(readPropertiesFile("PageTitle")));
		//check price of order
		actual.add(chekoutoverviewpage.CheckPriceOfOrder(readPropertiesFile("Price")));


		//click finish button
		chekoutoverviewpage.ClickFinishBTN();

		//order finalized
		actual.add(chekoutcompletepage.IsThankYouMessageVisible(readPropertiesFile("ThankYouMessage")));
		actual.add(chekoutcompletepage.IsOrderDispatchedMessageVisible(readPropertiesFile("OrderDispatchedMessage")));
		assertEquals(actual, expected, "Le test d'achat produit est fail");
	}	

}
