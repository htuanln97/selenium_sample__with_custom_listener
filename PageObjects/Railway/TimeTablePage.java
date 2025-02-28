package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;


public class TimeTablePage extends GeneralPage{
		String sBookTicket = "//table[@class='MyTable WideTable']//tr//td[.='%s']//following-sibling::td[.='%s']/..//a[contains(@href,'BookTicketPage')]";
		// Locators
		private final By getByBookTicket(String departStation, String arriveStation) {
			return By.xpath(String.format(sBookTicket, departStation, arriveStation));
		}
		// Elements
		
		protected WebElement getaLinkBookTicket(String departStation, String arriveStation) {
			return Constant.WEBDRIVER.findElement(getByBookTicket(departStation, arriveStation));
		}
		

		// Methods

		/**
		 * To go to check price page base on depart and arrive station
		 * 
		 * @param departStation
		 * @param arriveStation
		 * @return
		 */
		public BookTicketPage goToCheckPricePage(String departStation, String arriveStation) {
			this.getaLinkBookTicket(departStation, arriveStation).click();
			return new BookTicketPage();
		}

}
