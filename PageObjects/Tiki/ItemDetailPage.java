package Tiki;

import Constant.Constant;
import core.driver_wrapper.management.Driver;
import element_wrapper.Element;

public class ItemDetailPage {
	private final Element lblItemTitle = new Element("//h1[@class='title']");
	private final Element lblItemPrice = new Element("//div[contains(@class,'product-price')]/div[contains(@class,'current-price')]");
	
	/*Quantity Section*/
	private final Element btnQuantityMinus = new Element("//div[@class='group-input']//button//img[contains(@src,'remove')]");
	private final Element btnQuantityPlus = new Element("//div[@class='group-input']//button//img[contains(@src,'add')]");
	private final Element lblQuantity = new Element("//div[@class='group-input']//input[@class='input']");
	
	
	private final Element btnAddToCart = new Element("//button[@class='btn btn-add-to-cart']");
	
	/*Add to cart successful pop up*/
	private final Element lblAddToCartNotification = new Element("//div[contains(@class,'CartNotification')]//p[@class='status']");
	private final Element btnCloseAddToCartNotification = new Element("//div[contains(@class,'CartNotification')]//a[@class='btn-close']/i[contains(@class,'icomoon-close')]");
	private final Element btnGotoCart = new Element("//div[contains(@class,'CartNotification')]//a[@class='btn-view-cart']");
	
	
	public String getItemTitleText() {
		return lblItemTitle.getText();
	}
	
	public String getItemPice() {
		return lblItemPrice.getText();
	}
	
	public String getQuantity() {
		return lblQuantity.getValue();
	}
	
	public String getAddToCartNotificationText() {
		return lblAddToCartNotification.getText();
	}
	
	public void clickQuantityPlusButton(int numberOfClick) {
		int i=0;
		btnQuantityPlus.waitForVisibility();
		btnQuantityPlus.scrollToView();
		while(i < numberOfClick) {
			btnQuantityPlus.click();
			i++;
		}
	}
	
	public void clickQuantityMinusButton(int numberOfClick) {
		int i=0;
		btnQuantityMinus.waitForVisibility();
		btnQuantityMinus.scrollToView();
		while(i < numberOfClick) {
			btnQuantityMinus.click();
			i++;
		}
	}
	
	public void clickAddToCartButton() {
		btnAddToCart.waitForVisibility(Constant.DEFAULT_TIMEOUT);
		btnAddToCart.scrollToView();
		btnAddToCart.click();
	}
	
	public void clickCloseAddToCartNotificationButton() {
		btnCloseAddToCartNotification.click();
	}
	
	public CartPage clickGoToCartButton() {
		btnGotoCart.click();
		return new CartPage();
	}
	
	public String[] getItemNameAndPrice() {
		return new String[] { getItemTitleText(), getItemPice() }; 
	}
	
}
