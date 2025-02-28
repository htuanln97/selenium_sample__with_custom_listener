package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class LoginPage extends GeneralPage{
	
	//Locators
	private final By _txtUsername = By.xpath("//form[@class='LoginForm']//input[@id='username']");
	private final By _txtPassword = By.xpath("//form[@class='LoginForm']//input[@id='password']");
	private final By _btnLogin = By.xpath("//form[@class='LoginForm']//input[@type='submit']");
	private final By _lblLoginErrorMsg = By.xpath("//div[@id='content']//p[contains(@class,'message error')]");
	private final By _aTagLinkForgotPasswordPage = By.xpath("//a[contains(@href,'ForgotPassword')]");
	
	
	
	//Elements
	public WebElement getaTagForgotPassword() {
		return Constant.WEBDRIVER.findElement(_aTagLinkForgotPasswordPage);
	}
	
	public WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(_txtUsername);
	}
	
	public WebElement getTxtPassword(){
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}
	
	public WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(_btnLogin);
	}
	
	public WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
	}
		
	//Methods
	
	/**
	 * Go to Forgot Password Page
	 * @return ForgotPasswordPage
	 */
	public ForgotPasswordPage goToForgotPasswordPage() {
		new WebDriverWait(Constant.WEBDRIVER, Constant.DEFAULT_TIMEOUT)
		.until(ExpectedConditions.elementToBeClickable(getaTagForgotPassword()))
		.click();
		return new ForgotPasswordPage();
	}
	
	/**
	 * Login
	 * @param username: String
	 * @param password: String
	 * @return ForgotPasswordPage
	 */
	public void loginByValue(String username, String password) {
		this.getTxtUsername().clear();
		this.getTxtUsername().sendKeys(username);
		this.getTxtPassword().clear();
		this.getTxtPassword().sendKeys(password);
		this.getBtnLogin().click();
	}
	/**
	 * Login
	 * @param account: Object Account
	 */
	public void loginByObject(Account account) {
		this.getTxtUsername().clear();
		this.getTxtUsername().sendKeys(account.getUserName());
		this.getTxtPassword().clear();
		this.getTxtPassword().sendKeys(account.getPassword());
		this.getBtnLogin().click();
	}
	
	/**
	 * Login
	 * @param username: String
	 * @param password: String
	 * @return this page
	 */
	public LoginPage login(String username, String password) {
		loginByValue(username, password);
		return this;
		
	}
	
	/**
	 * Login success
	 * @param username: String
	 * @param password: String
	 * @return Home Page
	 */
	public HomePage loginSuccess(String username, String password) {
		loginByValue(username, password);
		return new HomePage();
		
	}
	
	/**
	 * Login
	 * @param account: Object Account
	 * @return this page
	 */
	public LoginPage login(Account account) {
		loginByObject(account);
		return this;
	}
	
	/**
	 * Login success
	 * @param account: Object Account
	 * @return Home Page
	 */
	public HomePage loginSuccess(Account account) {
		loginByObject(account);
		return new HomePage();
	}
	
	/**
	 * Get text error message
	 * @return String text
	 */
	public String getErrorMessage() {
		return getLblLoginErrorMsg().getText().trim();
	}
	/**
	 * Check if Login Page is displayed
	 * @return boolean true | false
	 */
	public boolean isDisplayedLoginPage() {
		return getPageTitle().contains("Login");
	}
}
