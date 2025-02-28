package Foody;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;
import core.driver_wrapper.management.Driver;
import core.driver_wrapper.management.DriverManagement;
import element_wrapper.Element;

public class HomePage {
	private final String lblTitle = "//div[@class='avatar-box']/div[@class='title' and contains(text(),'%s')]";
	
	private final Element eleSearchBox = new Element("//div[@id='FoodySearchApp']/form/div/input[@ng-model='Filter.Keyword']");
	private final Element btnSearch = new Element("//div[@id='FoodySearchApp']/a[@class='ico-search']");
	
	/*Element of Search Location Combo Box*/
	private final Element cbLocation = new Element(By.xpath("//div[@id='head-province']/div[@class='rn-nav-name']"));
	private final Element drOptionLocation = new Element(By.xpath("//li[contains(@ng-repeat,'item')]/a/label[@class='ng-binding']"));
	private final Element searchInput = new Element(By.xpath("//input[@ng-change='FilterLocation(Query)']"));
	private final Element popupLocation = new Element(By.xpath("//div[@id='popupLocation']"));
	
	/*Element of Search Category Combo Box*/
	private final Element cbCategory = new Element(By.xpath("//div[@class='root-cate']"));
	private final String drOptionCategory = "//div[@class='menu-frame-category']//ul[@class='menu-box']/li[@data-id]/a//span[contains(text(),'%s')]";
	private final String drOptionChildCategory = "//ul[@class='menu-box']//a[contains(@title,'%s')]";
	
	private final Element getDropdownOptionCategory(String category) {
		return new Element(By.xpath(String.format(drOptionCategory, category)));
	}
	
	private final Element getDropdownOptionChildCategory(String childCategory) {
		return new Element(By.xpath(String.format(drOptionChildCategory, childCategory)));
	}
	
	private final Element getLblTitle(String title) {
		return new Element(By.xpath(String.format(lblTitle, title)));
	}
	
	public HomePage open() {
		DriverManagement.getDriver().navigate().to(Constant.FOODY_URL);
		Driver.waitForLoadingPage();
		return this;
	}
	
	public boolean isDisplayedTitle(String title) {
		getLblTitle(title).waitForVisibility();
		getLblTitle(title).scrollToView();
		return getLblTitle(title).isDisplayed();
	}
	
	public void searchText(String text) {
		filterTextBox(text);
		clickSearchButton();
	}
	
	public void filterTextBox(String keysToSend) {
		eleSearchBox.sendKeys(keysToSend);
		
	}
	
	public SearchPage clickSearchButton() {
		btnSearch.click();
		return new SearchPage();
	}
	
	public void selectLocation(String location) {
		cbLocation.click();

		if(popupLocation.waitForVisibility().isDisplayed()) {
			searchInput.sendKeys(location);
			if(drOptionLocation.getText().equals(location)) {
				drOptionLocation.click();
			}
		}
	}
	
	public void selectCategory(String category, String childCategory) {
		cbCategory.click();
		getDropdownOptionCategory(category).hoverTo();
		getDropdownOptionChildCategory(childCategory).hoverTo();
		if(getDropdownOptionChildCategory(childCategory).getAttribute("title").equals(childCategory)) {
			getDropdownOptionChildCategory(childCategory).click();
		}
		
	}
	
}
