package Common;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class ElementUtilities {

	
	/**
	 * Click on element with default timeout
	 *
	 * @author tuan.le
	 * @param xpath: locator by xpath
	 * 
	 */
	
	public static void clickOnElement(String xpath) throws Exception {
		int i = 0;
		do {
			try {
				Constant.WEBDRIVER.findElement(By.xpath(xpath)).click();
				return;
			} catch (Exception e) {
				System.out.println("Attempting to click the element");
				i += 1;
				Thread.sleep(1000);
			}
		} while (i < Constant.DEFAULT_TIMEOUT);
	}
	
	/**
	 * Click on element with given timeout
	 *
	 * @author tuan.le
	 * @param xpath: locator by xpath
	 * @param timeount: given time out to run click
	 * 
	 */
	public static void clickOnElement(String xpath, int timeOut) throws Exception {
		int i = 0;
		do {
			try {
				Constant.WEBDRIVER.findElement(By.xpath(xpath)).click();
				return;
			} catch (Exception e) {
				System.out.println("Attempting to click the element");
				i += 1;
				Thread.sleep(1000);
			}
		} while (i < timeOut);
	}
	
	/**
	 * Click on element with WebElement
	 *
	 * @author tuan.le
	 * @param element: WebElement
	 * 
	 */
	
	public static void clickOnElement(WebElement element) throws Exception {
		int i = 0;
		do {
			try {
				element.click();
				return;
			} catch (Exception e) {
				System.out.println("Attempting to click the element: " + element.getAttribute("class"));
				i += 1;
				Thread.sleep(1000);
			}
		} while (i < Constant.DEFAULT_TIMEOUT);
	}
	
	/**
	 * Select drop down list with expected value
	 *
	 * @author tuan.le
	 * @param element: WebElement
	 * @param expectedValue: specific value
	 */
	
	public static void selectDropdownByValue(WebElement element, String expectedValue) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(expectedValue);
	}
	
	/**
	 * Select drop down list with random value
	 *
	 * @author tuan.le
	 * @param xpath: xpath
	 * 
	 */
	
	public static void selectRandomDropdown(String xpath) {
		//Note that: getOptions will return List<WebElement> 
		Select dropdown = new Select(Constant.WEBDRIVER.findElement(By.xpath(xpath)));
		Random random = new Random();
		int index = random.nextInt(dropdown.getOptions().size());
		dropdown.selectByIndex(index);
	}
	
	/**
	 * Wait until element to be click able
	 *
	 * @author tuan.le
	 * @param locator: locator
	 * @param timeout:  time out
	 * return boolean
	 */
	public static WebElement waitUntilElementToBeClickable(By locator, int timeout) {
		return new WebDriverWait(Constant.WEBDRIVER, timeout).until(ExpectedConditions.elementToBeClickable(locator)); 
	}
		
	/**
	 * Wait until element visibility
	 *
	 * @author tuan.le
	 * @param locator: locator
	 * @param timeout:  time out
	 * return boolean
	 */
	public static WebElement waitUntilVisibilityOfElementLocated(By locator, int timeout){
		return new WebDriverWait(Constant.WEBDRIVER, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * Wait until presence of element to be located
	 *
	 * @author tuan.le
	 * @param locator: locator
	 * @param timeout:  time out
	 * return boolean
	 */
	
	public static WebElement waitUtilPresenceOfElementLocated(By locator, int timeout) {
		return new WebDriverWait(Constant.WEBDRIVER, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * Click on element using JavaScript Executor
	 *
	 * @author tuan.le
	 * @param xpath: xpath
	 *
	 */
	public static void clickUsingJs(String xpath) {
		WebElement element = Constant.WEBDRIVER.findElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor) Constant.WEBDRIVER;
		executor.executeScript("arguments[0].click();", element);
	}
	
	/**
	 * Click on element with condition wait until element to be clickable
	 *
	 * @author tuan.le
	 * @param locator: locator
	 *@param: timeout : timeout
	 *
	 */
	public static void clickOnElement(By xpath, int timeout) {
		waitUntilElementToBeClickable(xpath, timeout).click();
	}
		
	/**
	 * Click on element with condition wait until visibility of element
	 *
	 * @author tuan.le
	 * @param locator: locator
	 *@param: timeout : timeout
	 *
	 */
	public void clickUsingActions(By locator, int timeout) {
		WebElement element = waitUntilVisibilityOfElementLocated(locator, timeout);
		Actions builder = new Actions(Constant.WEBDRIVER);
		builder.moveToElement(element).click(element);
		builder.build().perform();
		
	}
	
	/**
	 * Send keys function
	 *
	 * @author tuan.le
	 * @param locator: locator
	 * @param: keyToSend : key to send
	 * @param: timeout: time out
	 */
	public void performSendKeys(By locator, String keyToSend, int timeout) {
		WebElement element = waitUntilVisibilityOfElementLocated(locator, timeout);
		element.clear();
		element.sendKeys(keyToSend);
	}
	
	/**
	 * Hover to element
	 *
	 * @author tuan.le
	 * @param xpath: String xpath
	 * 
	 */
	public void hoverTo(String xpath) {
		WebElement element =  Constant.WEBDRIVER.findElement(By.xpath(xpath));
		Actions action = new Actions(Constant.WEBDRIVER);
		action.moveToElement(element).build().perform();
	}
	
	/**
	 * Scroll to view
	 *
	 * @author tuan.le
	 * @param xpath: String xpath
	 * 
	 */
	public void scrollToView(By locator) {
		WebElement element = Constant.WEBDRIVER.findElement(locator);
		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
}
