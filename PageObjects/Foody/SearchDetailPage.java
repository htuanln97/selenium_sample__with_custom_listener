package Foody;

import java.util.HashMap;

import org.openqa.selenium.By;

import Constant.Constant;
import core.driver_wrapper.management.Driver;
import element_wrapper.Element;

public class SearchDetailPage {
	private final Element lblItemName = new Element(By.xpath("//div[@class='main-info-title']//h1[@itemprop='name']"));
	private final Element lblAddress = new Element(By.xpath("//div[@itemprop='address']//span[@itemprop='streetAddress']"));
	private final Element lblDistrict = new Element(By.xpath("//div[@itemprop='address']//span[@itemprop='addressLocality']"));
	private final Element lblCity = new Element(By.xpath("//div[@itemprop='address']//span[@itemprop='addressRegion']"));
	
	public String getItemNameInfo() {
		return lblItemName.waitForVisibility(Constant.NORMAL_TIME).getText();
	}
	
	public String getAddressInfo() {
		return lblAddress.waitForVisibility(Constant.NORMAL_TIME).getText();
	}
	
	public String getDistrictInfo() {
		return lblDistrict.waitForVisibility(Constant.NORMAL_TIME).getText();
	}
	
	public String getCityInfo() {
		return lblCity.waitForVisibility(Constant.NORMAL_TIME).getText();
	}
	
	public HashMap<String, String> getItemDetails(){
		HashMap<String, String> data = new HashMap<String, String>();
		Driver.waitForLoadingPage();
		String Name = getItemNameInfo().toLowerCase();
		String allAddress = String.format("%s, %s, %s", getAddressInfo(), getDistrictInfo(), getCityInfo()).toLowerCase();
		data.put(Name, allAddress);
		return data;
	}
}
