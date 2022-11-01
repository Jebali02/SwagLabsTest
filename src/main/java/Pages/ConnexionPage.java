package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;



public class ConnexionPage extends BasePage {
	By UserNameField= By.id("user-name");
	By PasswordField = By.id("password");
	By LoginBTN = By.id("login-button");
	By ErrorMsg= By.xpath("//h3[@data-test='error']");
	By InvalidPasswordOrUsername= By.xpath("//h3[@data-test='error']");

	@Test
	public void AddUserName (String username) {
		SendKey(username, UserNameField);
	}	
	public void AddPassword (String password) {
		SendKey(password, PasswordField);
	}	
	public void ClickLogin () {
		ClickOnElement(LoginBTN);
	}	
	public boolean IsErrorMsgVisible (String errormsg) {
		return getText(ErrorMsg).equalsIgnoreCase(errormsg);

	}
	public boolean IsPasswordOrUsernameValid (String passwordorusernameinvalid) {
		return getText(InvalidPasswordOrUsername).equalsIgnoreCase(passwordorusernameinvalid);

	}


}
