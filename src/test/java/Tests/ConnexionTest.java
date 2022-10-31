package Tests;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.ConnexionPage;

public class ConnexionTest extends BasePage{

	ConnexionPage connexionPage =  new ConnexionPage();
	
	public void LoginLockedOutUser()
	{
		//ArrayList<Boolean> expected = new ArrayList<>();
		//ArrayList<Boolean> actual = new ArrayList<>();

		//expected.add(true);
		//login
	
		connexionPage.AddUserName(readPropertiesFile("LockedOutUser"));
		connexionPage.AddPassword(readPropertiesFile("Password"));
		connexionPage.ClickLogin();
		//actual.add(connexionPage.IsErrorMsgVisible(readPropertiesFile("ErrorMsg")));
	}
		
}
