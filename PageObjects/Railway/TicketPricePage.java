package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;


public class TicketPricePage {
	
	// Locators
	private final By _lblTicketPriceHeader = By
			.xpath("//table[@class=\"MyTable MedTable\"]//tr[@class=\"TableSmallHeader\"]/th");

	// Elements
	public WebElement getLblTicketPriceHeader() {
		return Constant.WEBDRIVER.findElement(_lblTicketPriceHeader);
	}

	// Methods

	/**
	 * To get ticket price table title
	 * 
	 * @return
	 */
	public String getTicketPriceHeader() {
		return this.getLblTicketPriceHeader().getText();
	}

	/**
	 * To get ticket price for each seattype
	 * 
	 * @param seatype
	 * @return
	 */
	public String getTicketPrice(String seatype) {
		By _ticketPrice = By.xpath(
				"//table[@class=\"MyTable MedTable\"]//tr//th[contains(.,\"Price \")]/..//td[count(//table//tr//td[.=\""
						+ seatype + "\"]/preceding-sibling::td)+1]");

		return Constant.WEBDRIVER.findElement(_ticketPrice).getText();
	}

}
