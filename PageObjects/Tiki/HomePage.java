package Tiki;


import Constant.Constant;
import Enums.Tiki.MenuItems;
import Tiki.SearchPage;
import core.driver_wrapper.management.Driver;
import core.driver_wrapper.management.DriverManagement;
import element_wrapper.Element;

public class HomePage {
	
	/*Header Section*/
	private final Element tikiLogo = new Element("//a[@class='tiki-logo']//img");
	private final Element tikiNowLogo = new Element("//a[@class='icon-tiki-now']//img");
	private final Element btnCart = new Element("//div[contains(@data-view-id,'cart')]//span[text()='Giỏ Hàng']/parent::div");
	
	private final Element tikiPopupPromotion = new Element("//div[@id='onesignal-slidedown-dialog']//div[@class='slidedown-body-message']");
	private final Element tikiPopupPromotionYes = new Element("//div[@id='onesignal-slidedown-dialog']//button[contains(@id,'allow-button')]");
	private final Element tikiPopupPromotionNo = new Element("//div[@id='onesignal-slidedown-dialog']//button[contains(@id,'cancel-button')]");
	
	private final String sQuickLinkItem = "//a[@data-view-id='header_quicklinks_item'][contains(text(),'%s')]";
	/*Login and Sign Up Section*/
	private final Element drpLoginAndSignUp = new Element("//div[@data-view-id='header_header_account_container']");
	private final Element btnLoginOption = new Element("//button[text()='Đăng nhập']");
	
	/*Login Popup Section*/
	private final Element txtUserName = new Element("//input[@id='email']");
	private final Element txtPassword = new Element("//input[@id='password']");
	private final Element btnLogin = new Element("//button[text()='Đăng nhập'][contains(@class,'UserModal')]");
	
	/*Search Section*/
	private final Element txtSearch = new Element("//input[@data-view-id='main_search_form_input']");
	private final Element btnSearch = new Element("//button[@data-view-id='main_search_form_button'][text()='Tìm Kiếm']");
	private final Element btnExpandSearchHistory = new Element("//div[contains(@class,'SearchAutocomplete')]//div[@data-view-id='search_history_expand_button']");
	
	private final String sSearchSuggestion = "//div[contains(@class,'SearchAutocomplete')]//a[@data-view-id='search_suggestion_keyword_item']//div[@class='keyword'][contains(text(),'%s')]";
	private final String sCommonSearchItem = "//div[contains(@class,'SearchAutocomplete')]//div[@data-view-id='home_top.search_product_container']//span[contains(text(),'%s')]/parent::a";
	private final String sFamousSearchItem = "//div[contains(@class,'SearchAutocomplete')]//div[@data-view-id='search_top.category_product_container']//span[contains(text(),'%s')]/parent::a ";
	
	/*Menu Section*/
	private final String sMenuItem = "//ul[@data-view-id='main_navigation']//span[contains(text(),'%s')]/parent::a"; 
	private String sItem = "//div[@data-view-id='main_navigation_item']//span[@data-view-id='main_navigation_sub_item']//a[contains(text(),'%s')]";
	
	/*Other*/
	private final Element lblFamousCategory = new Element("//div[@data-view-index='home_top.category_product_container']//div[text()='Danh Mục Nổi Bật']");
	private final Element lblFamousSearching = new Element("//div[@data-view-id='home_top.search_product_container']//span[text()='Tìm Kiếm Nổi Bật']");
	
	
	
	private final Element getLblSearchSuggestion(String name) {
		return new Element(String.format(sSearchSuggestion, name));
	}
	
	private final Element getLblCommonSearchItem(String name) {
		return new Element(String.format(sCommonSearchItem, name));
	}
	
	private final Element getLblFamousSearchItem(String name) {
		return new Element(String.format(sFamousSearchItem, name));
	}
	
	private final Element getLblMenuItem(String itemName) {
		return new Element(String.format(sMenuItem, itemName));
	}
	
	private final Element getLblItem(String item) {
		return new Element(String.format(sItem, item));
	}
	
	public HomePage open() {
		DriverManagement.getDriver().navigate().to(Constant.TIKI_URL);
		Driver.waitForLoadingPage();
		return this;
	}
	
	/*Header*/
	
	public HomePage clickTikiLogo() {
		tikiLogo.click();
		return this;
	}
	public void clickOnLoginOptionButton() {
		drpLoginAndSignUp.waitForVisibility();
		drpLoginAndSignUp.hoverTo();
		btnLoginOption.click();
	}
	
	/* Login */
	public void inputUserName(String username) {
		txtUserName.sendKeys(username);
	}
	
	public void inputPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLoginButton() {
		btnLogin.click();
	}
	
	public HomePage login(String username, String password) {
		inputUserName(username);
		inputPassword(password);
		clickLoginButton();
		return this;
	}
	
	
	/* Search */
	public void searchText(String text) {
		filterTextBox(text);
		clickSearchButton();
	}
	
	public void filterTextBox(String keysToSend) {
		txtSearch.sendKeys(keysToSend);
		
	}
	
	public String getSearchPlaceHolderText() {
		txtSearch.waitForVisibility();
		return txtSearch.getAttribute("placeholder");
	}
	
	public boolean isDisplayedSearchButton() {
		btnSearch.waitForClickable(Constant.DEFAULT_TIMEOUT);
		return btnSearch.isDisplayed();
	}
	
	public SearchPage clickSearchButton() {
		btnSearch.click();
		return new SearchPage();
	}
	
	public SearchPage clickExpandSearchHistory() {
		btnExpandSearchHistory.click();
		return new SearchPage();
	}
	
	public SearchPage clickSearchSuggestionItem(String itemName) {
		getLblSearchSuggestion(itemName).click();
		return new SearchPage();
	}
	
	public SearchPage clickCommonSearchItem(String itemName) {
		getLblCommonSearchItem(itemName).click();
		return new SearchPage();
	}
	
	public SearchPage clickFamousSearchItem(String itemName) {
		getLblFamousSearchItem(itemName).click();
		return new SearchPage();
	}
	
	
	/*Tiki Popup*/
	public boolean isDisplayedTikiPopup() {
		tikiPopupPromotion.waitForVisibility(Constant.SLEEP_TIME);
		return tikiPopupPromotion.isDisplayed();
	}
	
	public void turnOffTikiPopUp() {
		if(isDisplayedTikiPopup()) {
			tikiPopupPromotionNo.click();
		}
	}
	
	public void testClick() {
		//delete it after test is done
		tikiPopupPromotionNo.click();
	}
	
	/*Left Menu Item*/
	public SearchPage clickItemInMenu(MenuItems item) {
		getLblMenuItem(item.getItemName()).click();
		return new SearchPage();
	}
	
	public SearchPage clickSubItemInMenu(MenuItems item, String itemName) {
		//Hover to main menu
		getLblMenuItem(item.getItemName()).waitForVisibility();
		getLblMenuItem(item.getItemName()).hoverTo();
		
		//Click on item
		getLblItem(itemName).waitForPositionNotChange();
		getLblItem(itemName).click();
		return new SearchPage();
	}
	
	public boolean isDisplayedFamousCategoryTitle() {
		lblFamousCategory.waitForVisibility();
		lblFamousCategory.scrollToView();
		return lblFamousCategory.isDisplayed();
	}
	
	public boolean isDisplayedFamousSearching() {
		lblFamousSearching.waitForVisibility();
		lblFamousSearching.scrollToView();
		return lblFamousSearching.isDisplayed();
	}
	
}
