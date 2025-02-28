package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class ThanksForRegisteringAccountPage extends GeneralPage {
	//Locators
	private final By lblCreateNewAccountSuccess = By.xpath("//div[@id='content']//h1");
	

	//Elements
	protected WebElement getLblCreateNewAccountSuccess() {
		return Constant.WEBDRIVER.findElement(lblCreateNewAccountSuccess);
	}
	
	//Methods
	
	/**
	 * Get text for creating account successfully
	 * @return String text
	 */
	public String getTextThanksForRegistingAccountSuccessMessage() {
		return getLblCreateNewAccountSuccess().getText();
	}
	
	/**
	 * Check if Thanks For Registering Account is displayed 
	 * @return String text
	 */
	public boolean isDisplayedThanksForRegistingAccountSuccessMessage() {
		return getPageTitle().contains("Thanks for registering");
	}
}
