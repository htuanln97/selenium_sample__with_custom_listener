package GuruWebMail;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;
import Railway.ResetPasswordPage;

public class MailBoxPage {
	
	private final String srowMailContent = "//table[@id='messagelist']//span[@class='subject']/a/span[contains(text(),'%s')][contains(text(),'%s')]/ancestor::td";
	//Locators
	private final By aTagLinkActivate = By.xpath("//div[@id='messagebody']//a[contains(@href,'confirmationCode')]");
	private final By aTagLinkResetPassword = By.xpath("//div[@id='messagebody']//a[contains(@href,'resetToken')]");
	private final By iframeMailContent = By.xpath("//iframe[@id='messagecontframe']");
	private final By btnJunk = By.xpath("//li[contains(@class,'mailbox junk')]//a");
	private final By btnInbox = By.xpath("//li[contains(@class,'mailbox inbox')]//a");
	
	private final By getByRowMailContent(String value, String type) {
		return By.xpath(String.format(srowMailContent, value, type));
	}
	
	//Elements
	protected WebElement getRowMailContent(String mail, String type) {
		return Constant.WEBDRIVER.findElement(getByRowMailContent(mail, type));
	}
	
	protected WebElement getLinkMailActivate() {
		return Constant.WEBDRIVER.findElement(aTagLinkActivate);
	}
	
	protected WebElement getIframeMailContent() {
		return Constant.WEBDRIVER.findElement(iframeMailContent);
	}
	
	protected WebElement getBtnJunk() {
		return Constant.WEBDRIVER.findElement(btnJunk);
	}
	
	protected WebElement getBtnIbox() {
		return Constant.WEBDRIVER.findElement(btnInbox);
	}
	
	//Methods
	public void clickBtnInbox() {
		new WebDriverWait(Constant.WEBDRIVER,Constant.DEFAULT_TIMEOUT)
		.until(ExpectedConditions.elementToBeClickable(getBtnIbox()))
		.click();
	}
	
	public void switchMailIframe() {
		Constant.WEBDRIVER.switchTo().frame(getIframeMailContent());
	}
	
	public boolean isMailContentExists(String mail, String type) {
		try {
			if(getRowMailContent(mail, type) != null) {
				//System.out.println("Mail: " +mail+ " is displayed");
				return getRowMailContent(mail, type).isDisplayed();
			}
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("Mail: " +mail+ " is not displayed");
		}
		return false;
	}
	
	/**
	 * This function is used for click Mail
	 * 
	 * @param String mail
	 */
	public void clickMailContent(String mail, String type) {
		if(isMailContentExists(mail, type)) {
			getRowMailContent(mail, type).click();
		}
		else {
			//click button Junk
			new WebDriverWait(Constant.WEBDRIVER,Constant.DEFAULT_TIMEOUT)
			.until(ExpectedConditions.visibilityOfElementLocated(btnJunk)).click();
			//click on email
			new WebDriverWait(Constant.WEBDRIVER,Constant.DEFAULT_TIMEOUT)
			.until(ExpectedConditions.visibilityOfElementLocated(getByRowMailContent(mail, type))).click();
		}
		
	}
	
	/**
	 * This function is used for click link Mail to Active Account
	 * 
	 * 
	 */
	public void clickMailToActivate() {
		switchMailIframe();
		new WebDriverWait(Constant.WEBDRIVER,Constant.DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(aTagLinkActivate)).click();
	}
	
	/**
	 * This function is used for click Mail link to Reset Password
	 * 
	 * 
	 */
	public ResetPasswordPage clickMailToResetPassword() {
		switchMailIframe();
		new WebDriverWait(Constant.WEBDRIVER,Constant.DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(aTagLinkResetPassword)).click();
		return new ResetPasswordPage();
	}
	
	
	/**
	 * This function is used for click link Mail to Active Account
	 * 
	 * 
	 */
	public String getActivationCode() {
		switchMailIframe();
		String actual = new WebDriverWait(Constant.WEBDRIVER,Constant.DEFAULT_TIMEOUT).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='messagebody']//div[contains(text(),'confirmation')]"))).getText();
		String [] temp = actual.split(":");
		String afterPlit = temp[1].trim();
		return afterPlit.substring(0, 24);
		
	}
	
	
	
}
