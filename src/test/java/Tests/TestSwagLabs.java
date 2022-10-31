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

public class TestSwagLabs extends BasePage{

	ConnexionPage connexionPage =  new ConnexionPage();
	ProductPage productPage = new ProductPage();
	YourCartPage yourcartpage = new YourCartPage();
	ProductDetailsPage productdetailpage = new ProductDetailsPage();
	CheckoutYouInformationPage checkoutinformationpage = new CheckoutYouInformationPage();
	CheckoutOverviewPage chekoutoverviewpage = new CheckoutOverviewPage();
	CheckoutCompletePage chekoutcompletepage =  new CheckoutCompletePage();

	SoftAssert softAssert = new SoftAssert();


	@Test (priority = 0)
	public void Login()
	{
		//login
		System.out.println("I am inside Test 1");
		connexionPage.AddUserName(readPropertiesFile("UserName"));
		connexionPage.AddPassword(readPropertiesFile("Password"));
		connexionPage.ClickLogin();
		assertTrue(productPage.isProductPageDisplayed());
	}

	@Test (dependsOnMethods = "Login")
	public void VerifyBasketContent(){
		System.out.println("I am inside Test 2");
		//select product 
		productPage.ClickProduct(readPropertiesFile("ProductName"));

		//add product to basket 
		productdetailpage.ClickAddToCartBTN();
		//softAssert1.assertEquals(true, false);

		//detail product page
		productdetailpage.ClickShoppingCartBTN();

		assertTrue(yourcartpage.isProductAddToBasket(readPropertiesFile("ProductName")));

	}

	@Test (dependsOnMethods = "VerifyBasketContent")
	public void PurchaseProduct(){
		System.out.println("I am inside Test 3");

		ArrayList<Boolean> expected = new ArrayList<>();
		ArrayList<Boolean> actual = new ArrayList<>();

		expected.add(true);
		expected.add(true);
		expected.add(true);
		expected.add(true);
		expected.add(true);


		//click checkoutBtn
		yourcartpage.ClickCheckoutBTN();
		System.out.println("I am inside Test 4");
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
		//softAssert1.assertEquals(true, false);

		//order finalized
		actual.add(chekoutcompletepage.IsThankYouMessageVisible(readPropertiesFile("ThankYouMessage")));
		actual.add(chekoutcompletepage.IsOrderDispatchedMessageVisible(readPropertiesFile("OrderDispatchedMessage")));

		assertEquals(actual, expected, "Le test d'achat produit est fail");
	}	

	@AfterTest 
	public void logout()
	{
		//click menu
		chekoutcompletepage.ClickMenuBTN();

		//logout
		chekoutcompletepage.ClickLogoutBTN();
	}
}
