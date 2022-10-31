package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;



public class ConnexionPage extends BasePage {
	By UserNameField= By.id("user-name");
	By PasswordField = By.id("password");
	By LoginBTN = By.id("login-button");
	
	public void AddUserName (String username) {
		SendKey(username, UserNameField);
	}	
	public void AddPassword (String password) {
		SendKey(password, PasswordField);
	}	
	public void ClickLogin () {
		ClickOnElement(LoginBTN);
	}	
	
}
