package element_wrapper;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import core.common.Constant;
import core.driver_wrapper.management.Driver;

public class Element extends BaseElement{
	
	private static final Logger Logger = Constant.createLogger(Element.class.getName());

	public Element(By locator) {
		super(locator);
	}
	
	public Element(String xpath) {
		super(xpath);
	}

	public void click() {
		try {
			Logger.info(String.format("Click on %s", this.getLocator().toString()));
			this.waitForClickable(Constant.LONG_TIMEOUT).click();
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", this.getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public void clickUsingJs() {
		try {
			Logger.info(String.format("Click on %s by Java Script", this.getLocator().toString()));
			Driver.executeScript("arguments[0].click();", getElement());
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", this.getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public void doubleClick() {
		Logger.info(String.format("Click on the control %s twice", this.getLocator().toString()));
		int count = 0;
		while(count < 2) {
			click();
			count++;
		}
	}
	
	public void sendKeys(String keysToSend) {
		try {
			Logger.info(String.format("Send keys to element %s", getLocator().toString()));
			this.waitForVisibility().clear();
			this.waitForVisibility().sendKeys(keysToSend);
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public void submit() {
		try {
			this.waitForClickable(Constant.LONG_TIMEOUT).submit();
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public void clear() {
		try {
			this.waitForVisibility(Constant.TIMEOUT).clear();
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public String getAttribute(String attributeName) {
		try {
			Logger.info(String.format("Get attribute '%s' of element %s", attributeName, getLocator().toString()));
			return getElement().getAttribute(attributeName);
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public String getText() {
		try {
			Logger.info(String.format("Get text of element %s", getLocator().toString()));
			return this.waitForVisibility().getText();
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public String getValue() {
		try {
			Logger.info(String.format("Get value of element %s", getLocator().toString()));
			return this.getElement().getAttribute("value");
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public String getCssValue(String propertyName) {
		String value ="";
		try {
			value = this.getElement().getCssValue(propertyName);
			return value;
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public int getSize() {
		int size = 0;
		try {
			if(this.getElements() != null){
				size = this.getElements().size();
			}
			return size;
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			return size;
		}
	}
	
	public void selectDropdownByValue(String expectedValue) {
		try {
			Logger.info(String.format("Select dropdown list with value: '%s'", expectedValue));
			Select dropdown = new Select(this.waitForClickable(Constant.TIMEOUT));
			dropdown.selectByVisibleText(expectedValue);
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public void hoverTo() {
		try {
			Logger.info(String.format("Hover to element %s", getLocator().toString()));
			WebElement element =  this.getElement();
			Actions action = new Actions(getDriver());
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	
	public void scrollToView() {
		try {
			Logger.info(String.format("Scroll to view of element %s", getLocator().toString()));
			String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
			Driver.executeScript(scrollElementIntoMiddle, this.getElement());
			//Driver.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 3);", this.getElement());
		} catch (Exception e) {
			Logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}
	}
	

}
