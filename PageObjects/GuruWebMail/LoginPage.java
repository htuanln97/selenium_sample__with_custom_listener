package GuruWebMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import Railway.*;

public class LoginPage {
	//Locators
	private final By _txtWebMailUsername = By.xpath("//form[@id='login-form']//input[@id='rcmloginuser']");
	private final By _txtWebMailPassword = By.xpath("//form[@id='login-form']//input[@id='rcmloginpwd']");
	private final By _btnWebMailLogin = By.xpath("//form[@id='login-form']//button[@id='rcmloginsubmit']");
	
	//Elements
	protected WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(_txtWebMailUsername);
	}
	
	protected WebElement getTxtPassword(){
		return Constant.WEBDRIVER.findElement(_txtWebMailPassword);
	}
	
	protected WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(_btnWebMailLogin);
	}
	
	//Method
	
	public LoginPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GURU_WEBMAIL_URL);
		return this;
	}
	
	public void loginByObject(Account account) {
		this.getTxtUsername().clear();
		this.getTxtUsername().sendKeys(account.getUserName());
		this.getTxtPassword().clear();
		this.getTxtPassword().sendKeys(account.getPassword());
		this.getBtnLogin().click();
	}
	
	public MailBoxPage login(Account account) {
		loginByObject(account);
		return new MailBoxPage();
		
	}
}
