package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class ConfirmAccountPage extends GeneralPage{
	
	
	//Locators
	private final By _txtActivationCode = By.xpath("//form//input[@id='confirmationCode']");
	private final By _btnConfirm = By.xpath("//form//input[@type='submit']");
	private final By _lblConfirmSuccess = By.xpath("//div[@id='content']//p");
	
	//Elements
	protected WebElement getBtnConfirm() {
		return Constant.WEBDRIVER.findElement(_btnConfirm);
	}
	
	
	protected WebElement getTxtActivationCode() {
		return Constant.WEBDRIVER.findElement(_txtActivationCode);
	}
	
	protected WebElement getLblMessageConfirmSuccess() {
		return Constant.WEBDRIVER.findElement(_lblConfirmSuccess);
	}
	
	
	//Methods
	public void fillActivationCode(String code) {
		this.getTxtActivationCode().clear();
		this.getTxtActivationCode().sendKeys(code);
		this.getBtnConfirm().click();
	}
	
	public String getTextMessageConfirmSuccess() {
		return getLblMessageConfirmSuccess().getText().trim();
	}

}
