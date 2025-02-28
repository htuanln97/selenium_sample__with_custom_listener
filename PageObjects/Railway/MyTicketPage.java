package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;
import Constant.Constant;


public class MyTicketPage extends GeneralPage {
	
	// Locators
	private final By _sltDepartStation = By.xpath("//div[@class='Filter']//select[@name='FilterDpStation']");
	private final By _sltArriveStation = By.xpath("//div[@class='Filter']//select[@name='FilterArStation']");
	private final By _tdDepartDate = By.xpath("//div[@class='Filter']//td//input[@name='FilterDpDate']");
	private final By _sltStatus = By.xpath("//div[@class='Filter']//select[@name='FilterStatus']");
	private final By _btnApplyFilter = By.xpath("//div[@class='Filter']//input[@type='submit']");
	
	private final By tdDepartStation = By.xpath("//table[@class='MyTable']//tr//td[count(//table[@class='MyTable']//th[.='Depart Station']/preceding-sibling::th) + 1]");
	private final By tdArriveStation = By.xpath("//table[@class='MyTable']//tr//td[count(//table[@class='MyTable']//th[.='Arrive Station']/preceding-sibling::th) + 1]");
	private final By tdSeatType = By.xpath("//table[@class='MyTable']//tr//td[count(//table[@class='MyTable']//th[.='Seat Type']/preceding-sibling::th) + 1]");
	private final By tdDepartDate = By.xpath("//table[@class='MyTable']//tr//td[count(//table[@class='MyTable']//th[.='Depart Date']/preceding-sibling::th) + 1]");
	private final By tdAmount = By.xpath("//table[@class='MyTable']//tr//td[count(//table[@class='MyTable']//th[.='Amount']/preceding-sibling::th) + 1]");
	// Elements
	
	protected WebElement getTdDepartStation() {
		return Constant.WEBDRIVER.findElement(tdDepartStation);
	}
	
	protected WebElement getTdArriveStation() {
		return Constant.WEBDRIVER.findElement(tdArriveStation);
	}
	
	protected WebElement getTdSeatType() {
		return Constant.WEBDRIVER.findElement(tdSeatType);
	}
	
	protected WebElement getTdDepartDate() {
		return Constant.WEBDRIVER.findElement(tdDepartDate);
	}
	
	protected WebElement getTdAmount() {
		return Constant.WEBDRIVER.findElement(tdAmount);
	}
	
	
	protected WebElement getsltDepartStation() {
		return Constant.WEBDRIVER.findElement(_sltDepartStation);
	}

	protected WebElement getsltArriveStation() {
		return Constant.WEBDRIVER.findElement(_sltArriveStation);
	}

	protected WebElement getTdtArriveStation() {
		return Constant.WEBDRIVER.findElement(_tdDepartDate);
	}

	protected WebElement getsltStatus() {
		return Constant.WEBDRIVER.findElement(_sltStatus);
	}
	
	protected WebElement getBtntApplyFilter() {
		return Constant.WEBDRIVER.findElement(_btnApplyFilter);
	}


	// Methods
	/**
	 * Check if My Ticket Page is displayed
	 * @return boolean true | false
	 */
	public boolean isDisplayedMyTicketPage() {
		return getPageTitle().contains("My Ticket");
	}

	/**
	* To cancel ticket booked
	* 
	* @param ticket
	* @return
	 */
	public MyTicketPage cancelTicket(Ticket ticket) {
			By _ticketPrice = By.xpath("//table[@class=\"MyTable\"]//tr//td[contains(.,\"" + ticket.getDepartFrom()
					+ "\")]/following-sibling::td[contains(.,\"" + ticket.getArriveAt()
					+ "\")]/following-sibling::td[contains(.,\"" + ticket.getSeatType()
					+ "\")]/following-sibling::td[contains(.,\"" + ticket.getDepartDate()
					+ "\")]/following-sibling::td[contains(.,\"" + ticket.getTicketAmount()
					+ "\")]/..//input[@type=\"button\"][@value=\"Cancel\"]");
			Constant.WEBDRIVER.findElement(_ticketPrice).click();

			Utilities.acceptAlert();

			return new MyTicketPage();
	}

	/**
	* To check if ticket exist in booked tickets table
	* 
	* @param ticket
	* @return
	*/
	public Boolean checkTicketExist(Ticket ticket) {
			By _ticket = By.xpath("//table[@class=\"MyTable\"]//tr//td[contains(.,\"" + ticket.getDepartFrom()
					+ "\")]/following-sibling::td[contains(.,\"" + ticket.getArriveAt()
					+ "\")]/following-sibling::td[contains(.,\"" + ticket.getSeatType()
					+ "\")]/following-sibling::td[contains(.,\"" + ticket.getDepartDate()
					+ "\")]/following-sibling::td[contains(.,\"" + ticket.getTicketAmount()
					+ "\")]/..//input[@type=\"button\"][@value=\"Cancel\"]");

			return Constant.WEBDRIVER.findElements(_ticket).size() > 0 ? true : false;
	}
	
	/**
	 * Select drop down list with expected value
	 *
	 * @author tuan.le
	 * @param element: WebElement
	 * @param expectedValue: specific value
	 */
	
	public static void selectDropdownByValue(WebElement element, String expectedValue) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(expectedValue);
	}
	
	/**
	 * Search ticket with ticket info
	 *
	 * @author tuan.le
	 * @param ticketInfo: Object ticket
	 * @param status: String
	 */
	public void searchFilter(Ticket ticketInfo, String status) {
		selectDropdownByValue(this.getsltDepartStation(), ticketInfo.getDepartFrom());
		selectDropdownByValue(this.getsltArriveStation(), ticketInfo.getArriveAt());
		selectDropdownByValue(this.getTdtArriveStation(), ticketInfo.getDepartDate());
		if(status.length()!=0) {
		selectDropdownByValue(this.getsltStatus(), status);
		}
		this.getBtntApplyFilter().click();
	}
	
	/**
	 * Search ticket with 1 field Depart Date
	 *
	 * @author tuan.le
	 * @param departDate: String
	 */
	public void searchFilterWithDepartDate(String departDate) {
		this.getTdtArriveStation().clear();
		this.getTdtArriveStation().sendKeys(departDate);
		this.getBtntApplyFilter().click();
	}
	
	
	/**
	 * Get text Depart From
	 * 
	 * @return String
	 */
	public String getDepartFromInfo() {
		return getTdDepartStation().getText().trim();
	}
	
	/**
	 * Get text Arrive At
	 * 
	 * @return String
	 */
	public String getArriveAtInfo() {
		return getTdArriveStation().getText().trim();
	}
	
	/**
	 * Get text Seat Type
	 * 
	 * @return String
	 */
	public String getSeatTypeInfo() {
		return getTdSeatType().getText().trim();
	}
	
	/**
	 * Get text Depart Date
	 * 
	 * @return String
	 */
	public String getDepartDateInfo() {
		return getTdDepartDate().getText().trim();
	}
	
	/**
	 * Get text number of Amount
	 * 
	 * @return integer
	 */
	public int getAmountInfo() {
		int actual = Integer.parseInt(getTdAmount().getText().trim());
		return actual;
	}

}
