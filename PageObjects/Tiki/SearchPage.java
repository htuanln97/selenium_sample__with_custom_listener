package Tiki;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import core.driver_wrapper.management.Driver;
import core.driver_wrapper.management.DriverManagement;
import element_wrapper.Element;

public class SearchPage {
	private final String sSearchSort ="//div[@class='sort-list']//a[@data-view-id='search_sort_item'][contains(text(),'%s')]";
	private final String sItem = "//span[contains(text(),'%s')]/ancestor::a[@class='product-item']";
	
	private final Element lblItemName = new Element("//a[@class='product-item']//div[@class='name']/span");
	private final String sItemNameIndex = "(//a[@class='product-item']//div[@class='name']/span)[%s]";
	private final String sItemPrice = "(//a[@class='product-item']//div[contains(@class,'price-discount')]/div[@class='price-discount__price'])[%s]";
	
	private final Element eleBreadCrumbItem = new Element("//div[@class='breadcrumb']/a[@class='breadcrumb-item']");
	private final Element eleSearchResultTitle = new Element("//div[@class='search-summary']/div[@class='title']/h1");
	private final String sLinkSupplier = "//div[@data-view-label='Nhà cung cấp']//a[contains(text(),'%s')]";
	private final Element lblTikiNowFastDelivery = new Element("//label[@data-view-id='search_checkbox_filter_item'][@class='filter-tikinow']");
	
	//Price section
	private final Element elePriceSection = new Element("//div[@data-view-label='Giá']");
	private final Element txtPriceFrom = new Element("//div[@data-view-label='Giá']//div[@class='input-group']/input[@placeholder='Giá từ']");
	private final Element txtPriceTo = new Element("//div[@data-view-label='Giá']//div[@class='input-group']/input[@placeholder='Giá đến']");
	private final Element btnPriceOK = new Element("//div[@data-view-label='Giá']//button[@data-view-id='search_filter_submit_button']");
	
	
	//Tag as Keyword
	private final Element eleTag = new Element("//div[@data-view-id='search_selected_filter_container']//p[@class='item']");
	private final Element eleLoading = new Element("//html[contains(@class,'nprogress-busy')]");

	private final Element getLinkSupplier(String name) {
		return new Element(String.format(sLinkSupplier, name));
	}
	
	private final Element getLblSearchSort(String name) {
		return new Element(String.format(sSearchSort, name));
	}

	private final Element getItem(String item) {
		return new Element(By.xpath(String.format(sItem, item)));
	}
	
	private final Element getItemNameIndex(String index) {
		return new Element(By.xpath(String.format(sItemNameIndex, index)));
	}
	
	private final Element getItemPrice(String index) {
		return new Element(By.xpath(String.format(sItemPrice, index)));
	}
	
	public boolean isSelectedSearchSort(String item) {
		boolean isSelected;
		getLblSearchSort(item).waitForVisibility();
		isSelected = getLblSearchSort(item).getAttribute("class").equals("active");
		return isSelected;
	}
	
	public ItemDetailPage clickItemBaseOnName(String name) {
		getItem(name).scrollToView();
		getItem(name).click();
		ItemDetailPage itemDetailPage = new ItemDetailPage();
		Element itemTitleInItemDetailPage = new Element(String.format("//h1[@class='title'][text()='%s']", name));
		return new ItemDetailPage();
	}
	
	public String[] clickItemRandomAndGetInfo() {
		Driver.waitForLoadingPage();
		waitForLoading();
		int totalItems = lblItemName.getSize();
		int random = Utilities.getRandomNumber(totalItems);
		
		if(random==0) {
			//if random = 0 then + 1
			random+=1;
		}
		
		String index = String.valueOf(random);
		getItemNameIndex(index).scrollToView();
		getItemNameIndex(index).waitForPositionNotChange();
		Driver.waitForLoadingPage();
		String itemName = getItemNameIndex(index).getText();
		String itemPrice = getItemPrice(index).getText();
		clickItemBaseOnName(itemName);
		return new String[] { itemName, itemPrice };
	}
	
	public boolean isDisplayedBreadCrumb(String value) {
		String[] sTemp = value.split(">");
		List<String> sActualBreadCrumbNames = new ArrayList<String>();
		List<WebElement> lstElements = eleBreadCrumbItem.waitForVisibilityOfAllElements(Constant.DEFAULT_TIMEOUT);
		for (WebElement ele : lstElements) {
			sActualBreadCrumbNames.add(ele.getText());
		}

		if ((sTemp[0].trim().equals(sActualBreadCrumbNames.get(0)))
				&& (sTemp[1].trim().equals(sActualBreadCrumbNames.get(1)))) {
			return true;
		} else
			return false;
	}
	
	public boolean isDisplayedBreadCrumbWithFourParameters(String value) {
		String[] sTemp = value.split(">");
		List<String> sActualBreadCrumbNames = new ArrayList<String>();
		List<WebElement> lstElements = eleBreadCrumbItem.waitForVisibilityOfAllElements(Constant.DEFAULT_TIMEOUT);
		for (WebElement ele : lstElements) {
			sActualBreadCrumbNames.add(ele.getText());
		}

		if ((sTemp[0].trim().equals(sActualBreadCrumbNames.get(0)))
				&& (sTemp[1].trim().equals(sActualBreadCrumbNames.get(1)))
				&& (sTemp[2].trim().equals(sActualBreadCrumbNames.get(2))) 
				&& (sTemp[3].trim().equals(sActualBreadCrumbNames.get(3)))) {
			return true;
		} else
			return false;
	}
	
	public String getTextSearchResultTitle() {
		String sActualTitle="";
		eleSearchResultTitle.waitForVisibility();
		sActualTitle = eleSearchResultTitle.getText();
		String[] sTextTitle = sActualTitle.split(":");
		return sTextTitle[0].trim();
	}
	
	public void selectSupplier(String supplierName) {
		getLinkSupplier(supplierName).waitForClickable(Constant.DEFAULT_TIMEOUT);
		getLinkSupplier(supplierName).waitForPositionNotChange();
		getLinkSupplier(supplierName).click();
	}
	
	public void checkOnTikiNowFastDelivery() {
		lblTikiNowFastDelivery.click();
	}
	
	public void inputPriceAndClickOKButton(int priceFrom, int priceTo) {
		//scroll to price section
		elePriceSection.waitForVisibility();
		elePriceSection.scrollToView();
		
		//input price
		txtPriceFrom.sendKeys(String.valueOf(priceFrom));
		txtPriceTo.sendKeys(String.valueOf(priceTo));
		
		//click Ok
		btnPriceOK.click();
	}
	
	public boolean isDisplayedKeyWordTag(String keywords) {
		String[] sExpectedValue = keywords.split(",");
		List<WebElement> lstElements = eleTag.waitForVisibilityOfAllElements(Constant.DEFAULT_TIMEOUT);
		List<String> lstActualValues = new ArrayList<String>();
		for(WebElement e: lstElements) {
			lstActualValues.add(e.getText());
		}
		if((lstActualValues.get(0).toString().contains(sExpectedValue[0].trim())) 
				&& (lstActualValues.get(1).toString().contains(sExpectedValue[1].trim())) 
				&& (lstActualValues.get(2).toString().contains(sExpectedValue[2].trim()))) {
			return true;
		}else
			return false;
			
	}
	
	public void waitForLoading() {
		eleLoading.waitForInvisibility(Constant.DEFAULT_TIMEOUT);
	}

}
