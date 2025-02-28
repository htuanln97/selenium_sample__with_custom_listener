package Railway;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Constant.Constant;

public class BookTicketPage extends GeneralPage {
	
		// Locators
		private final By _sltDepartDate = By.xpath("//select[@name='Date']");
		private final By _sltDepartFrom = By.xpath("//select[@name='DepartStation']");
		private final By _sltArriveAt = By.xpath("//select[@name='ArriveStation']");
		private final By _sltSeatType = By.xpath("//select[@name='SeatType']");
		private final By _txtTicketAmount = By.xpath("//select[@name='TicketAmount']");
		private final By _btnBookTicket = By.xpath("//input[@type='submit'][@value='Book ticket']");

		// Elements
		protected WebElement getBtnBookTicket() {
			return Constant.WEBDRIVER.findElement(_btnBookTicket);
		}

		protected WebElement getsltDepartDate() {
			return Constant.WEBDRIVER.findElement(_sltDepartDate);
		}

		protected WebElement getsltDepartFrom() {
			return Constant.WEBDRIVER.findElement(_sltDepartFrom);
		}

		protected WebElement getsltArriveAt() {
			return Constant.WEBDRIVER.findElement(_sltArriveAt);
		}

		protected WebElement getsltSeatType() {
			return Constant.WEBDRIVER.findElement(_sltSeatType);
		}

		protected WebElement getTxtTicketAmount() {
			return Constant.WEBDRIVER.findElement(_txtTicketAmount);
		}

		// Methods
		
		/**
		 * Used to check element exists
		 * 
		 * @return boolean true | false
		 */
		public boolean doesElementExist(By locator) {
			if (Constant.WEBDRIVER.findElements(locator).size() > 0) {
				return true;
			}
			return false;
		}
		
		
		/**
		 * Wait for element exist
		 * @param locator: locator
		 * @param secs: time out
		 * @return String
		 */
		public void waitForElementExist(By locator, int secs) {
			int countSec = 0;
			while (countSec < secs) {
				if(doesElementExist(locator)){
					break;
				}
				countSec++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}


		/**
		 * To book a ticket
		 * 
		 * @param ticket
		 * @return
		 */
		public BookSuccessPage bookTicket(Ticket ticket) {
			// Select depart Date
			Select drpDepartDate = new Select(this.getsltDepartDate());
			drpDepartDate.selectByVisibleText(ticket.getDepartDate());

			// Select depart station
			Select drpDepartFrom = new Select(this.getsltDepartFrom());
			drpDepartFrom.selectByVisibleText(ticket.getDepartFrom());

			// Select seat type
			Select drpSeatType = new Select(this.getsltSeatType());
			drpSeatType.selectByVisibleText(ticket.getSeatType());

			// Select ticket amount
			Select drpTicketAmount = new Select(this.getTxtTicketAmount());
			drpTicketAmount.selectByVisibleText(ticket.getTicketAmount() + "");

			// Wait for destination available after selecting depart station
			waitForElementExist(this._sltArriveAt, Constant.SHORT_TIME);

			// Select destination
			if (doesElementExist(this._sltArriveAt)) {
				Select drpArriveAt = new Select(this.getsltArriveAt());
				waitForListItemExist(drpArriveAt, ticket.getArriveAt(), Constant.SHORT_TIME);
				drpArriveAt.selectByVisibleText(ticket.getArriveAt());
			}

			// Click button [book ticket]
			this.getBtnBookTicket().click();

			return new BookSuccessPage();
		}

		private void waitForListItemExist(Select _select, String listItemValue, int secs) {
			int countSec = 0;
			List<WebElement> listWebElements = _select.getOptions();
			while (countSec < secs) {
				for (int i = 0; i < listWebElements.size(); i++) {
					if (listWebElements.get(i).getText().equals(listItemValue)) {
						break;
					}
				}
				countSec++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * Check if Book Ticket Page is displayed
		 * 
		 * @return boolean true | false
		 */
		public boolean isDisplayedBookTicketPage() {
			return getPageTitle().contains("Book Ticket");
		}
		
		/**
		 * Get Text selected Depart From option
		 * 
		 * @return String
		 */
		public String getSelectedTextDepartFrom() {
			Select option = new Select(this.getsltDepartFrom());
			String actualValue = option.getFirstSelectedOption().getText().trim();
			return actualValue;
		}
		
		/**
		 * Get Text selected Arrive At option
		 * 
		 * @return String
		 */
		public String getSelectedTextArriveAt() {
			Select option = new Select(this.getsltArriveAt());
			String actualValue = option.getFirstSelectedOption().getText().trim();
			return actualValue;
		}
}
