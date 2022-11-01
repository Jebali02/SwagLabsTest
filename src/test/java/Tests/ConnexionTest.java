package Tests;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.ConnexionPage;
import Pages.ProductPage;

public class ConnexionTest extends BasePage{

	ConnexionPage connexionPage =  new ConnexionPage();
	ProductPage  productPage =  new ProductPage();

	@Test (priority = 0)
	public void LoginLockedOutUser(){
		//login
		logger.info("I am inside LoginLockedOutUser test");
		connexionPage.AddUserName(readPropertiesFile("LockedOutUser"));
		connexionPage.AddPassword(readPropertiesFile("Password"));
		connexionPage.ClickLogin();
		assertTrue(connexionPage.IsErrorMsgVisible(readPropertiesFile("LoginLockedOutUserMsg")));
	}

	@Test (dependsOnMethods = "LoginLockedOutUser")
	public void LoginWithInvalidPasswordAndValidUsername(){
		logger.info("I am inside LoginWithInvalidPasswordAndValidUsername test");
		connexionPage.AddUserName(readPropertiesFile("UserName"));
		connexionPage.AddPassword(readPropertiesFile("InvalidPassword"));
		connexionPage.ClickLogin();
		assertTrue(connexionPage.IsPasswordOrUsernameValid(readPropertiesFile("InvalidPasswordOrUsernameMsg")));

	}
	@Test (dependsOnMethods = "LoginWithInvalidPasswordAndValidUsername")
	public void LoginWithValidPasswordAndInvalidUsename(){
		logger.info("I am inside LoginWithValidPasswordAndInvalidUsename test ");
		connexionPage.AddUserName(readPropertiesFile("InvalidUserName"));
		connexionPage.AddPassword(readPropertiesFile("Password"));
		connexionPage.ClickLogin();
		assertTrue(connexionPage.IsPasswordOrUsernameValid(readPropertiesFile("InvalidPasswordOrUsernameMsg")));
	}
	@Test (priority = 3)
	public void LoginWithInvalidUsernameAndInvalidPassword(){
		logger.info("I am inside LoginWithInvalidUsernameAndInvalidPassword test");
		connexionPage.AddUserName(readPropertiesFile("InvalidUserName"));
		connexionPage.AddPassword(readPropertiesFile("InvalidPassword"));
		connexionPage.ClickLogin();
		assertTrue(connexionPage.IsPasswordOrUsernameValid(readPropertiesFile("InvalidPasswordOrUsernameMsg")));

	}
	@Test (priority = 4)
	public void LoginWithValidUsernameAndValidPassword(){
		logger.info("I am inside LoginWithValidUsernameAndValidPassword test");
		connexionPage.AddUserName(readPropertiesFile("UserName"));
		connexionPage.AddPassword(readPropertiesFile("Password"));
		connexionPage.ClickLogin();
		assertTrue(productPage.isProductPageDisplayed());
	}
	@Test (dependsOnMethods = "LoginWithValidUsernameAndValidPassword")
	public void Logout(){
		logger.info("I am inside Logout test");
		productPage.ClickMenuBTN();
		productPage.ClickLogoutBTN();
		//To do assertion to verify logout
	}
}
