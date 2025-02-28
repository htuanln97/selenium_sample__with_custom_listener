package Railway;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class BookSuccessPage extends GeneralPage{
	
		//Locators
		private final By _lblBookTicketSuccess = By.xpath("//div[@id='content']//h1");
		
		private final By tdDepartStation = By.xpath("//table//tr//td[count(//table//th[.='Depart Station']/preceding-sibling::th) + 1]");
		private final By tdArriveStation = By.xpath("//table//tr//td[count(//table//th[.='Arrive Station']/preceding-sibling::th) + 1]");
		private final By tdSeatType = By.xpath("//table//tr//td[count(//table//th[.='Seat Type']/preceding-sibling::th) + 1]");
		private final By tdDepartDate = By.xpath("//table//tr//td[count(//table//th[.='Depart Date']/preceding-sibling::th) + 1]");
		private final By tdAmount = By.xpath("//table//tr//td[count(//table//th[.='Amount']/preceding-sibling::th) + 1]");
	
		//Elements
		
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
		
		protected WebElement getLblBookTicketSuccess() {
			return Constant.WEBDRIVER.findElement(_lblBookTicketSuccess);
		}
	
		//Methods
		
		/**
		 * Get text when book ticket success
		 * 
		 * @return String
		 */
		public String getTextBookTicketSuccess() {
			return this.getLblBookTicketSuccess().getText().trim();
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
