package Tiki;

import Constant.Constant;
import element_wrapper.Element;

public class CartPage {
	/*Cart quantity section*/
	private final Element btnCartQuantityMinus = new Element("//div[contains(@class,'CartQty')]/span[contains(@class,'decrease')] ");
	private final Element btnCartQuantityPlus = new Element("//div[contains(@class,'CartQty')]/span[contains(@class,'increase')]");
	private final Element lblProductQuantity = new Element("//div[contains(@class,'CartQty')]/input[@class='qty-input'] ");
	
	/*Cart description section*/
	private final Element lblCartProductName = new Element("//a[@class='cart-products__name']");
	private final Element lblCartProductPrice = new Element("//div[@class='cart-products__pricess']/p[contains(@class,'real-prices')]");
	private final Element btnCartProductDelete = new Element("//span[@class='cart-products__del']");
	private final Element btnCartProductBuyLater = new Element("//span[contains(@class,'buy-later')]");
	
	public String getProductQuantity() {
		lblProductQuantity.waitForVisibility();
		return lblProductQuantity.getValue();
	}
	
	public void clickCartQuantityMinusButtoon(int numberOfClick) {
		int i=0;
		while(i<numberOfClick) {
			btnCartQuantityMinus.click();
			i++;
		}
		
	}
	
	public void clickCartQuantityPlusButton(int numberOfClick) {
		int i=0;
		while(i<numberOfClick) {
			btnCartQuantityPlus.click();
			i++;
		}
		
	}
	
	public String getCartProductPrice() {
		return lblCartProductPrice.getText();
	}
	
	public String getCartProductName() {
		return lblCartProductName.getText();
	}
	
	public void clickDeleteProductButton() {
		btnCartProductDelete.click();
	}
	
	public void clickCartProductBuyLaterButton() {
		btnCartProductBuyLater.click();
	}
}
