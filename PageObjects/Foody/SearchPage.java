/**
 * 
 */
package Foody;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import core.driver_wrapper.management.Driver;
import core.driver_wrapper.management.DriverManagement;
import element_wrapper.Element;

/**
 * @author Tuan.Le
 * This is search page
 */

public class SearchPage {
	private final String lblLeftMenuItem = "//div[@id='directory-search-resulttab']//ul//a[contains(text(),'%s')]";
	private final String tabItem="//div[contains(@class,'foody-toolbar') "
			+ "and @data-bind=\"visible:documentSource()=='Restaurant'\"]//li//a[contains(text(),'%s')]";
	
	
	private final Element eleGridResultItems =  new Element(By.xpath("//div[@class='result-name']//div[@class='resname']//h2//a[@data-bind]"));
	private final Element eleLoadingSpinner = new Element(By.xpath("//div[@class='search-loading']"));
	
	private final String linkItem = "(//div[@class='result-name']//div[@class='resname']//h2//a[@data-bind])[%s]";
	private final String lblAddress = "(//div[@class='result-name']//div[@class='address']//span[@data-bind='text: Address'])[%s]";
	private final String lblDistrict = "(//div[@class='result-name']//div[@class='address']//a//span[@data-bind='text: District'])[%s]";
	private final String lblCity = "(//div[@class='result-name']//div[@class='address']//span[@data-bind='text: City'])[%s]";
	
	
	private final Element getLblLeftMenuItem(String category) {
		return new Element(By.xpath(String.format(lblLeftMenuItem, category)));
	}
	
	private final Element getTabItem(String category) {
		return new Element(By.xpath(String.format(tabItem, category)));
	}
	
	private final Element getLinkItem(String index) {
		return new Element(By.xpath(String.format(linkItem, index)));
	}
	
	private final Element getLblAddress(String index) {
		return new Element(By.xpath(String.format(lblAddress, index)));
	}
	
	private final Element getLblCity(String index) {
		return new Element(By.xpath(String.format(lblCity, index)));
	}
	
	private final Element getLblDistrict(String index) {
		return new Element(By.xpath(String.format(lblDistrict, index)));
	}
	
	public boolean isSelectedMenuItem(String item) {
		boolean isSelected;
		getLblLeftMenuItem(item).waitForVisibility();
		isSelected = getLblLeftMenuItem(item).getAttribute("class").equals("current");
		return isSelected;
	}
	
	public void clickOnTab(String tabName) {
		getTabItem(tabName).click();
	}
	
	
	public HashMap<String, String> clickOnRandomItemAndGetItemDetails() {
		HashMap<String, String> data = new HashMap<String, String>();
		
		//wait for loading spinner loading complete
		eleLoadingSpinner.waitForInvisibility(Constant.NORMAL_TIME);
		//This is handle for items which are visible on UI
		eleGridResultItems.handleStaleOfElements();
		int totalItems = eleGridResultItems.waitForVisibilityOfAllElements(Constant.NORMAL_TIME).size();
		int random = Utilities.getRandomNumber(totalItems);
		
		if(random==0) {
			//if random = 0 then + 1
			random+=1;
		}
		String item = String.valueOf(random);
		getLinkItem(item).scrollToView();
		Driver.waitForLoadingPage();
		
		String Name = getLinkItem(item).getText().toLowerCase();
		String Address = getLblAddress(item).getText();
		
		//get inner HTML becase the data is displayed but hidden by characters ... so we can not get text as normal
		String District = getLblDistrict(item).getAttribute("innerHTML");
		String City = getLblCity(item).getAttribute("innerHTML");
		
		String allAddress = String.format("%s, %s, %s", Address, District, City).toLowerCase();
		getLinkItem(item).click();
		data.put(Name, allAddress);
		return data;
	}
	
	public boolean areDisplayedAllItemsInTheResultGridContain(String keyword) {
		Driver.waitForLoadingPage();
		eleLoadingSpinner.waitForInvisibility(Constant.NORMAL_TIME);
		Driver.scrollTillEnd();
		eleLoadingSpinner.waitForInvisibility(Constant.NORMAL_TIME);
		Driver.scrollToUp();
		
		boolean check = false;
		List<WebElement> lstElements = eleGridResultItems.waitForVisibilityOfAllElements(Constant.NORMAL_TIME);
		System.out.println(lstElements.size());
		for(WebElement e: lstElements) {
			if((e.getText().toLowerCase().contains(keyword.split("/")[0].toLowerCase())) 
					|| (e.getText().toLowerCase().contains(keyword.split("/")[1].toLowerCase()))) {
				
				System.out.println(String.format("Actual: '%s' contains Expected: '%s/%s'", e.getText(),keyword.split("/")[0], keyword.split("/")[1]));
				check = true;
			}else {
				System.out.println(String.format("Actual: '%s' is not contains Expected: '%s/%s'", e.getText(),keyword.split("/")[0], keyword.split("/")[1]));
				check = false;
				break;
			}
		}
		return check;
	}

}
