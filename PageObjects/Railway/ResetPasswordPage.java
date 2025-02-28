package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;


public class ResetPasswordPage extends GeneralPage {
	
	// Locators
		private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
		private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
		private final By _txtResetToken = By.xpath("//input[@id='resetToken']");
		private final By _btnResetPassword = By.xpath("//input[@value='Reset Password']");
		private final By _lblErrorMessage = By.xpath("//p[@class='message error']");
		private final By _lblResetTokenErrorMessage = By.xpath("//label[@for='resetToken'][@class='validation-error']");
		private final By _lblConfirmPasswordErrorMessage = By.xpath("//label[@for='confirmPassword'][@class='validation-error']");

		// Elements
		public WebElement getLblConfirmPasswordErrorMessage() {
			return Constant.WEBDRIVER.findElement(_lblConfirmPasswordErrorMessage);
		}

		public WebElement getLblResetTokenErrorMessage() {
			return Constant.WEBDRIVER.findElement(_lblResetTokenErrorMessage);
		}

		public WebElement getLblErrorMessage() {
			return Constant.WEBDRIVER.findElement(_lblErrorMessage);
		}

		public WebElement getTxtNewPassword() {
			return Constant.WEBDRIVER.findElement(_txtNewPassword);
		}

		public WebElement getTxtConfirmPassword() {
			return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
		}

		public WebElement getTxtResetToken() {
			return Constant.WEBDRIVER.findElement(_txtResetToken);
		}

		public WebElement getBtnResetPassword() {
			return Constant.WEBDRIVER.findElement(_btnResetPassword);
		}

		// Methods
		public String getTextConfirmPasswordErrorMessage() {
			return this.getLblConfirmPasswordErrorMessage().getText();
		}

		public String getTextResetTokenErrorMessage() {
				return this.getLblResetTokenErrorMessage().getText().trim();
		}

		public String getTextErrorMessage() {
				return this.getLblErrorMessage().getText().trim();
		}

		/**
		 * Clear after send key
		 * 
		 * @param WebElemnt we
		 * @param String keyToSend
		 */
		
		public void sendKeyActions(WebElement we, String keyToSend) {
			we.clear();
			we.sendKeys(keyToSend);
		}
		
		/**
		 * To reset account's password
		 * 
		 * @param newPassword
		 * @param confirmPassword
		 * @param token
		 * @return
		 */
		public ResetPasswordPage resetPassword(String newPassword, String confirmPassword, String token) {
			//Fill in
			sendKeyActions(this.getTxtNewPassword(),newPassword);
			sendKeyActions(getTxtConfirmPassword(), confirmPassword);
			sendKeyActions(this.getTxtResetToken(), token);
			//Click button
			this.getBtnResetPassword().click();
			return this;
		}
		
		/**
		 * To reset account's password
		 * 
		 * @param newPassword
		 * @param confirmPassword
		 * @return
		 */
		public ResetPasswordPage resetPassword(String newPassword, String confirmPassword) {
			//Fill in
			sendKeyActions(this.getTxtNewPassword(),newPassword);
			sendKeyActions(getTxtConfirmPassword(), confirmPassword);
			//Click button
			this.getBtnResetPassword().click();
			return this;
		}
		
		/**
		 * Check if reset password page is displayed
		 * @return boolean true | false
		 */
		public boolean isDisplayedResetPasswordPage() {
			return getPageTitle().trim().contains("Password Reset");
		}

}
