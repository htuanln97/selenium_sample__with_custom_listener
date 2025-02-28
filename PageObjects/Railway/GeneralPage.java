package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class GeneralPage {
	
	//Locators
	private final By tabHome = By.xpath("//div[@id='menu']//a[contains(@href,'..')][//span[contains(text(),'Home')]]");
	private final By tabFAQ = By.xpath("//div[@id='menu']//a[contains(@href,'FAQ')]");
	private final By tabContact = By.xpath("//div[@id='menu']//a[contains(@href,'Contact')]");
	private final By tabTimeTable = By.xpath("//div[@id='menu']//a[contains(@href,'TrainTimeList')]");
	private final By tabTicketPrice = By.xpath("//div[@id='menu']//a[contains(@href,'TrainPriceList')]");
	private final By tabBookTicket = By.xpath("//div[@id='menu']//a[contains(@href,'BookTicketPage')]");
	private final By tabMyTicket = By.xpath("//div[@id='menu']//a[contains(@href,'ManageTicket')]");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[contains(@href,'Register')]");
	private final By tabChangePassword = By.xpath("//div[@id='menu']//a[contains(@href,'ChangePassword')]");
	private final By tabLogin = By.xpath("//div[@id='menu']//a[contains(@href,'Login')]");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[contains(@href,'Logout')]");
	private final By lblWelcomeMessage = By.xpath("//div[@id='header']//div[@class='account']//strong");
	
	//Elements
	protected WebElement getTabHome() {
		return Constant.WEBDRIVER.findElement(tabHome);
	}
	
	protected WebElement getTabFAQ() {
		return Constant.WEBDRIVER.findElement(tabFAQ);
	}
	
	protected WebElement getTabContact() {
		return Constant.WEBDRIVER.findElement(tabContact);
	}
	
	protected WebElement getTabTimeTable() {
		return Constant.WEBDRIVER.findElement(tabTimeTable);
	}
	
	protected WebElement getTabTicketPrice() {
		return Constant.WEBDRIVER.findElement(tabTicketPrice);
	}
	
	
	protected WebElement getTabBookTicket() {
		return Constant.WEBDRIVER.findElement(tabBookTicket);
	}
	
	protected WebElement getTabMyTicket() {
		return Constant.WEBDRIVER.findElement(tabMyTicket);
	}
	
	protected WebElement getTabRegister() {
		return Constant.WEBDRIVER.findElement(tabRegister);
	}
	
	protected WebElement getTabChangePassword() {
		return Constant.WEBDRIVER.findElement(tabChangePassword);
	}
	
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}
	
	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}
	
	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}
	
	//Methods
	/**
	 * Gets Welcome message
	 *
	 * @author tuan.le
	 * 
	 */
	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}
	
	/**
	 * Go to Login page
	 *
	 * @author tuan.le
	 * 
	 */
	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}
	
	/**
	 * Go to Book Ticket page
	 *
	 * @author tuan.le
	 * 
	 */
	public BookTicketPage gotoBookTicketPage() {
		this.getTabBookTicket().click();
		return new BookTicketPage();
	}
	
	/**
	 * Go to Book Ticket without log in
	 *
	 * @author tuan.le
	 * 
	 */
	public LoginPage goToBookTicketWithoutLogin() {
		this.getTabBookTicket().click();
		return new LoginPage();
	}
	
	/**
	 * Go to My Ticket page
	 *
	 * @author tuan.le
	 * 
	 */
	public MyTicketPage gotoMyTicketPage() {
		this.getTabMyTicket().click();
		return new MyTicketPage();
	}
	
	/**
	 * Go to Change Password page
	 *
	 * @author tuan.le
	 * 
	 */
	public ChangePasswordPage gotoChangePasswordPage() {
		this.getTabChangePassword().click();
		return new ChangePasswordPage();
	}
	
	/**
	 * Go to Register page
	 *
	 * @author tuan.le
	 * 
	 */
	public RegisterPage gotoRegisterPage() {
		this.getTabRegister().click();
		return new RegisterPage();
	}
	
	/**
	 * Go to Register page
	 *
	 * @author tuan.le
	 * 
	 */
	public TimeTablePage gotoTimeTablePage() {
		this.getTabTimeTable().click();
		return new TimeTablePage();
	}
	
	/**
	 * Check Home Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isHomeTabDisplayed() {
		return this.getTabHome().isDisplayed();
	}
	
	/**
	 * Check FAQ Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isFQATabDisplayed() {
		return this.getTabFAQ().isDisplayed();
	}
	
	/**
	 * Check Contact Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isContactTabDisplayed() {
		return this.getTabContact().isDisplayed();
	}
	
	/**
	 * Check Time Table Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isTimeTableTabDisplayed() {
		return this.getTabTimeTable().isDisplayed();
	}
	
	/**
	 * Check Ticket Price Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isTicketPriceTabDisplayed() {
		return this.getTabTicketPrice().isDisplayed();
	}
	
	/**
	 * Check Book Ticket Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isBookTicketTabDisplayed() {
		return this.getTabBookTicket().isDisplayed();
	}
	
	/**
	 * Check My Ticket Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isMyTicketTabDisplayed() {
		return this.getTabMyTicket().isDisplayed();
	}
	
	/**
	 * Check Change Password Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isChangePasswordTabDisplayed() {
		return this.getTabChangePassword().isDisplayed();
	}
	
	/**
	 * Check Logout Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isLogOutTabDisplayed() {
		return this.getTabLogout().isDisplayed();
	}
	
	/**
	 * Check Register Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isRegisterTabDisplayed() {
		return this.getTabRegister().isDisplayed();
	}
	
	/**
	 * Check Login Tab is displayed
	 *
	 * @author tuan.le
	 * @return boolean true or fail
	 */
	public boolean isLoginTabDisplayed() {
		return this.getTabLogin().isDisplayed();
	}
	
	
	/**
	 * Get page title
	 * @author tuan.le
	 * return string name title
	 */
	
	public static String getPageTitle() {
		return Constant.WEBDRIVER.getTitle();
	}

}
