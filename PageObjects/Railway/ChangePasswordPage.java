package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class ChangePasswordPage extends GeneralPage {
	//Locators
	private final By _txtCurrentPassword = By.xpath("//form[@id='ChangePW']//input[@id='currentPassword']");
	private final By _txtNewPassword = By.xpath("//form[@id='ChangePW']//input[@id='newPassword']");
	private final By _txtConfirmNewPassword = By.xpath("//form[@id='ChangePW']//input[@id='confirmPassword']");
	private final By _btnChangePassWord = By.xpath("//form[@id='ChangePW']//input[@type='submit']");
	private final By _lblChangePwSuccess = By.xpath("//form[@id='ChangePW']//p[@class='message success']");
	
	
	//Elements
	
	public WebElement getTxtCurrentPassword() {
		return Constant.WEBDRIVER.findElement(_txtCurrentPassword);
	}
	
	public WebElement getTxtNewPassword(){
		return Constant.WEBDRIVER.findElement(_txtNewPassword);
	}
	
	public WebElement getTxtConfirmNewPassword(){
		return Constant.WEBDRIVER.findElement(_txtConfirmNewPassword);
	}
	
	public WebElement getBtnChangePassword() {
		return Constant.WEBDRIVER.findElement(_btnChangePassWord);
	}
	
	public WebElement getLblChangePassword() {
		return Constant.WEBDRIVER.findElement(_lblChangePwSuccess);
	}
	
	//Method
	
	/**
	 * Check if Change Password Page is displayed
	 * 
	 * @return boolean true | false
	 */
	public boolean isDisplayedChangePasswordPage() {
		return getPageTitle().contains("Change Password");
	}
	
	/**
	 * Clear and seand key to element
	 * @param we: WebElement
	 * @param keyToSend: String
	 *
	 */
	public void sendKeyActions(WebElement we, String keyToSend) {
		we.clear();
		we.sendKeys(keyToSend);
	}
	
	/**
	 * To change password
	 * @param currentPw: String
	 * @param newPw: String
	 * @return
	 */
	public ChangePasswordPage changePassword(String currentPw, String newPw) {
		sendKeyActions(this.getTxtCurrentPassword(), currentPw);
		sendKeyActions(this.getTxtNewPassword(), newPw);
		sendKeyActions(this.getTxtConfirmNewPassword(),newPw);
		this.getBtnChangePassword().click();
		return this;
	}
	
	/**
	 * To get text change password success
	 * @return String text
	 */
	public String getTextChangePasswordSucess() {
		return getLblChangePassword().getText().trim();
	}
	
}
