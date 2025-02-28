package Railway;

import GuruWebMail.LoginPage;
import GuruWebMail.MailBoxPage;


import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;


public class RegisterTest  extends TestBase{
	HomePage homePage = new HomePage();
	GeneralPage generalPage = new GeneralPage();
	private Railway.LoginPage loginPage;
	private LoginPage guruLoginPage = new LoginPage();
	private MailBoxPage guruMailBox;
	private RegisterPage registerPage;
	private ConfirmAccountPage confirmAccountPage;
	
  
  
  @Test
  public void FTTC02() {
	  System.out.println("FTTC02 - User can also activate new account using activation code");
	  
	  //Generate new account for activate and create new
	  
	  //Account for activate
	  Account accountGuru = new Account(Constant.USERNAMEMAILGURU, Constant.PASSWORDMAILGURU);
	  //Account for create new
	  Account newAccount = new Account("test" + Utilities.getDateWithTimeMils() +"@gmail.com",
			  Constant.PASSWORD, Constant.PID);
	  
	  //1. Navigate to QA Railway Website
	  homePage.open();
	  
	  //2 Register new account with valid value
	  registerPage = homePage.gotoRegisterPage();
	  registerPage.createNewAccountSuccess(newAccount);
	  //3. Open mailbox and the open activation email
	  //Activate account
	  Utilities.openNewTab(Constant.GURU_WEBMAIL_URL);
	  guruMailBox = guruLoginPage.login(accountGuru);
	  guruMailBox.clickMailContent(newAccount.getUserName(), "confirm");
	  
	  //4. Get the activation code
	  String activationCode = guruMailBox.getActivationCode();
	  
	  //5 Back to Register page, click on 'here' link to enter activation code
	  Utilities.switchToSpecificTab(0);
	  registerPage = registerPage.gotoRegisterPage();
	  confirmAccountPage =  registerPage.clickHereLink();
	  //6. Enter the activation code from step 4 and click on 'Confirm' button
	  confirmAccountPage.fillActivationCode(activationCode);
	  
	  //Verify point: Message 'Registration Confirmed! You can now log in to the site.' displays
	  
	  Assert.assertEquals(confirmAccountPage.getTextMessageConfirmSuccess(), 
			  "Registration Confirmed! You can now log in to the site.",
			  "Message 'Registration Confirmed! You can now log in to the site.' should be displayed");
	  
	  
	  //7. Login with this account
	  loginPage = confirmAccountPage.gotoLoginPage();
	  
	  //Verify point: User is logged into Railway. Welcome user message is displayed
	  homePage = loginPage.loginSuccess(newAccount);
	  
	  String actualMessage = loginPage.getWelcomeMessage();
	  String expectedMessage = "Welcome " + newAccount.getUserName();
	  
	  Assert.assertEquals(actualMessage, expectedMessage, "Welcome Message should be displayed as expected");
	  
	  
	  
  }
  
 
  
}
