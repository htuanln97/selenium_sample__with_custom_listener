package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class RegisterPage extends GeneralPage{
	
	//Locators
	private final By _txtEmail = By.xpath("//form[@id='RegisterForm']//input[@id='email']");
	private final By _txtPassword = By.xpath("//form[@id='RegisterForm']//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//form[@id='RegisterForm']//input[@id='confirmPassword']");
	private final By _txtPID = By.xpath("//form[@id='RegisterForm']//input[@id='pid']");
	private final By _btnRegister = By.xpath("//form[@id='RegisterForm']//p//input[@type='submit']");
	private final By _lblMessageError = By.xpath("//p[@class='message error']");
	private final By _lblPwdErrorMsg = By.xpath("//label[@for=\"password\"][@class=\"validation-error\"]");
	private final By _lblPidErrorMsg = By.xpath("//label[@for=\"pid\"][@class=\"validation-error\"]");
	private final By _aTagHereLink = By.xpath("//a[contains(@href,'Confirm')]");
	
	
	
	//Elements
	protected WebElement getTxtEmail() {
		return Constant.WEBDRIVER.findElement(_txtEmail);
	}
	
	protected WebElement getATagHereLink() {
		return Constant.WEBDRIVER.findElement(_aTagHereLink);
	}
	
	protected WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(_txtPassword);
	}
	
	protected WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
	}
	
	protected WebElement getTxtPID() {
		return Constant.WEBDRIVER.findElement(_txtPID);
	}
	
	protected WebElement getBtnRegister() {
		return Constant.WEBDRIVER.findElement(_btnRegister);
	}
	
	protected WebElement getLblMessageError() {
		return Constant.WEBDRIVER.findElement(_lblMessageError);
	}
	
	public WebElement getLblPidErrorMsg() {
		return Constant.WEBDRIVER.findElement(_lblPidErrorMsg);
	}

	public WebElement getLblPwdErrorMsg() {
		return Constant.WEBDRIVER.findElement(_lblPwdErrorMsg);
	}
	
	//Methods
	/**
	 * Clear and send key
	 * @param locator: locator
	 * @param keyToSend : key to send
	 */
	public void sendKeyActions(By locator, String keyToSend) {
		Constant.WEBDRIVER.findElement(locator).clear();;
		new WebDriverWait(Constant.WEBDRIVER, Constant.DEFAULT_TIMEOUT)
		.until(ExpectedConditions.visibilityOfElementLocated(locator))
		.sendKeys(keyToSend);
	}
	
	/**
	 * Get text error message
	 * 
	 * @return String text
	 */
	public String getTextMessageError() {
		return getLblMessageError().getText().trim();
	}
	
	/**
	 * Get text password error message
	 * 
	 * @return String text
	 */
	public String getTextPwdErrorMsg() {
		return this.getLblPwdErrorMsg().getText().trim();
	}
	
	/**
	 * Get text pid error message
	 * 
	 * @return String text
	 */
	public String getTextPidErrorMsg() {
		return this.getLblPidErrorMsg().getText().trim();
	}
	
	/**
	 * Create new account
	 * @param account: Object Account
	 * @return String text
	 */
	public void createNewAccountByObject(Account account) {
		sendKeyActions(_txtEmail, account.getUserName());
		sendKeyActions(_txtPassword, account.getPassword());
		sendKeyActions(_txtConfirmPassword, account.getPassword());
		sendKeyActions(_txtPID, account.getPassportNumber());
		this.getBtnRegister().click();
	}
	
	/**
	 * Create new account
	 * @param us: user name
	 * @param pw: password
	 * @param confirmPw:  confirm password
	 * @param pid: pid\passport number
	 */
	public void createNewAccountByStringValue(String us, String pw, String confirmPw, String pid) {
		sendKeyActions(_txtEmail, us);
		sendKeyActions(_txtPassword, pw);
		sendKeyActions(_txtConfirmPassword, confirmPw);
		sendKeyActions(_txtPID, pid);
		this.getBtnRegister().click();
	}
	
	/**
	 * Create new account success
	 * @param account: Object Account
	 * @return Thanks for registering account page
	 */
	public ThanksForRegisteringAccountPage createNewAccountSuccess(Account account) {
		createNewAccountByObject(account);
		return new ThanksForRegisteringAccountPage();
	}

	/**
	 * Create new account fail
	 * @param account: Object Account
	 * @return this page
	 */
	public RegisterPage createNewAccountFail(Account account) {
		createNewAccountByObject(account);
		return this;
	}
	
	/**
	 * Create new account fail
	 * @param us: user name
	 * @param pw: password
	 * @param confirmPw:  confirm password
	 * @param pid: pid\passport number
	 * @return this page
	 */
	public RegisterPage createNewAccountFail(String us, String pw, String confirmPw, String pid) {
		createNewAccountByStringValue(us, pw, confirmPw, pid);
		return this;
	}
	
	
	public ConfirmAccountPage clickHereLink() {
		new WebDriverWait(Constant.WEBDRIVER, Constant.DEFAULT_TIMEOUT)
		.until(ExpectedConditions.elementToBeClickable(getATagHereLink())).click();
		return new ConfirmAccountPage();
	}
	
}
