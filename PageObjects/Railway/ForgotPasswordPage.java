package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class ForgotPasswordPage extends GeneralPage {
	
	// Locators
		private final By _txtEmail = By.xpath("//input[@id='email']");
		private final By _btnSendInstructions = By.xpath("//input[@type='submit']");

		// Elements
		protected WebElement getTxtEmail() {
			return Constant.WEBDRIVER.findElement(_txtEmail);
		}

		protected WebElement getBtnSendInstructions() {
			return Constant.WEBDRIVER.findElement(_btnSendInstructions);
		}

		// Methods

		/**
		 * This will send instructions to admin email for reseting password link
		 * 
		 * @param email
		 * @return
		 */
		public ForgotPasswordPage sendInstructions(String email) {
			this.getTxtEmail().sendKeys(email);
			this.getBtnSendInstructions().click();
			return this;
		}

}
